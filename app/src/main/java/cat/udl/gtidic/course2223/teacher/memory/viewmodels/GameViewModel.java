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

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cat.udl.gtidic.course2223.teacher.memory.helpers.GlobalInfo;
import cat.udl.gtidic.course2223.teacher.memory.models.Game.Game;
import cat.udl.gtidic.course2223.teacher.memory.models.MemoryDatabase;

public class GameViewModel extends ViewModel {

    protected String myClassTag = this.getClass().getSimpleName();

    private final MutableLiveData<Game> game = new MutableLiveData<>();
    private final MutableLiveData<String> message = new MutableLiveData<>();

    private Context context;

    private DatabaseReference myRef;
    private DatabaseReference myFirebaseDBRefence;

    public GameViewModel(){
        Game internalGame = new Game();
        internalGame.init();
        game.setValue(internalGame);

        enableFirebaseDB();
    }

    private void enableFirebaseDB() {
        String url = GlobalInfo.getIntance().getFIREBASE_DB();
        FirebaseDatabase database = FirebaseDatabase.getInstance(url);
        myFirebaseDBRefence = database.getReference("multiplayer-board");
    }

    public void enableForum(){
        String url = GlobalInfo.getIntance().getFIREBASE_DB();
        FirebaseDatabase database = FirebaseDatabase.getInstance(url);
        myRef = database.getReference("forum-messages");
        myRef.orderByChild("time");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String output = "";
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String line = childSnapshot.child("message").getValue(String.class);
                    Date time = childSnapshot.child("time").getValue(Date.class);
//                    android.text.format.DateFormat df = new android.text.format.DateFormat();
//                    df.format("yyyy-MM-dd hh:mm:ss ", time);
                    String dateFormatted = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(time);
                    String finalLine = String.format("%s %s\n", dateFormatted, line);
                    output = finalLine + output;
                }
                message.setValue(output);
                Log.d(myClassTag, "Value is: " + output);
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

        String key = myRef.push().getKey();

        Map<String, Object> data = new HashMap<>();
        data.put("time", new Date());
        data.put("message", missatge);
        myRef.child(key).setValue(data);
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

        updateFirebaseDB(myGame);
    }

    private void updateFirebaseDB(Game g) {
        myFirebaseDBRefence.setValue(g.getBoard());
    }

    public MutableLiveData<String> getMessage() {
        return message;
    }
}
