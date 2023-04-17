package cat.udl.gtidic.course2223.teacher.memory.viewmodels;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cat.udl.gtidic.course2223.teacher.memory.helpers.GlobalInfo;
import cat.udl.gtidic.course2223.teacher.memory.models.Game.Game;
import cat.udl.gtidic.course2223.teacher.memory.models.MemoryDatabase;

public class GameViewModel extends ViewModel {

    protected String myClassTag = this.getClass().getSimpleName();

    private MutableLiveData<Game> game = new MutableLiveData<>();

    private Context context;

    public GameViewModel(){
        Game internalGame = new Game();
        internalGame.init();
        game.setValue(internalGame);
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
        String url = GlobalInfo.getIntance().getFIREBASE_DB();
        FirebaseDatabase database = FirebaseDatabase.getInstance(url);
        DatabaseReference myRef = database.getReference("message");
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

}
