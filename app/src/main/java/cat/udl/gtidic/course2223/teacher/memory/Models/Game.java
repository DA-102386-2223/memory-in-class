package cat.udl.gtidic.course2223.teacher.memory.Models;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

import cat.udl.gtidic.course2223.teacher.memory.Models.Player.Player;

public class Game {
    protected String myClassTag = this.getClass().getSimpleName();

    int maxPoints = -1;
    private int totalCardsReversed = 0;

    Player currentPlayer;
    Player winner;
    Board board;
    public void init(){
//        TODO Board size will be dinamic in the future
        int boardSize = 4;

//        TODO this list will be obtained from a DB or remote site in the future
        List<String> possibleValues = Arrays.asList("🐱", "🐶", "🦁", "🐒", "🐥", "😃", "🐢", "👻");

        board = new Board(4);
        board.shuffle(possibleValues);
    }

    public void cardClicked(Button button, int row, int column){
        Piece p = board.getPiece(row, column);
        button.setText(p.value);
        totalCardsReversed++;
        Log.d(myClassTag, "He incrementat totalCardsRevers");
    }

    public int getTotalCardsReversed() {
        return totalCardsReversed;
    }
}
