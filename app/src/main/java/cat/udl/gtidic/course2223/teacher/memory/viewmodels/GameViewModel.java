package cat.udl.gtidic.course2223.teacher.memory.viewmodels;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import cat.udl.gtidic.course2223.teacher.memory.helpers.GlobalInfo;
import cat.udl.gtidic.course2223.teacher.memory.models.Game.Game;
import cat.udl.gtidic.course2223.teacher.memory.models.MemoryDatabase;

public class GameViewModel extends ViewModel {

    protected String myClassTag = this.getClass().getSimpleName();

    private final MutableLiveData<Game> game = new MutableLiveData<>();
    private final MutableLiveData<String> message = new MutableLiveData<>();

    private Context context;

    private DatabaseReference myRef;

    public GameViewModel(){
        Game internalGame = new Game();
        internalGame.init();
        game.setValue(internalGame);
    }

    public void enableForum(){
        String url = GlobalInfo.getIntance().getFIREBASE_DB();
        FirebaseDatabase database = FirebaseDatabase.getInstance(url);
        myRef = database.getReference("message");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                message.setValue(value);
                Log.d(myClassTag, "Value is: " + value);
            }
            @Override
            public void onCancelled(@NotNull DatabaseError error) { // Failed to read value
                Log.w(myClassTag, "Failed to read value.", error.toException());
            }
        });
    }

    public void setContext(Context context){
        this.context = context;
    }

    public void saveGameIntoDB(){
        String dbName = "memory.db";
        MemoryDatabase dbRoom = Room.databaseBuilder(context, MemoryDatabase.class, dbName).allowMainThreadQueries().build();
        Game g = game.getValue();

        g.id = dbRoom.gameDAO().insert(g);

        String missatge = String.format("He guardat el meu joc amb id: %d", g.id );
        Log.d(myClassTag, missatge);
        dbRoom.close();
    }

    public void updateGameInDB(){
        String dbName = "memory.db";
        MemoryDatabase dbRoom = Room.databaseBuilder(context, MemoryDatabase.class, dbName).allowMainThreadQueries().build();
        Game g = game.getValue();
        dbRoom.gameDAO().update(g);
        dbRoom.close();
    }

    public void sendMessage(EditText etMessage){
        String missatge = etMessage.getText().toString();
        etMessage.setText("");

        myRef.setValue(missatge);
    }

    public LiveData<Game> getGame(){
        return game;
    }

//    TODO pending to remove view button here
    public void cardClicked(int row, int column){
        Game myGame = game.getValue();
        myGame.cardClickedMVVM(row, column);
        game.setValue(myGame);

//        updating game
        updateGameInDB();
    }

    public MutableLiveData<String> getMessage() {
        return message;
    }
}
