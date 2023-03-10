package cat.udl.gtidic.course2223.teacher.memory.viewmodels;

import android.view.View;
import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.udl.gtidic.course2223.teacher.memory.models.Game;

public class GameViewModel extends ViewModel {

    private MutableLiveData<Game> game = new MutableLiveData<>();

    public GameViewModel(){
        Game internalGame = new Game();
        internalGame.init();
        game.setValue(internalGame);
    }

    public LiveData<Game> getGame(){
        return game;
    }

//    TODO pending to remove view button here
    public void cardClicked(View button, int row, int column){
        Game myGame = game.getValue();
        myGame.cardClicked((Button) button, row, column);
        game.setValue(myGame);
    }

}
