package cat.udl.gtidic.course2223.teacher.memory.models;

import android.util.Log;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

import cat.udl.gtidic.course2223.teacher.memory.models.Player.HumanPlayer;
import cat.udl.gtidic.course2223.teacher.memory.models.Player.Player;

public class Game {
    protected String myClassTag = this.getClass().getSimpleName();

    int maxPoints = -1;
    private int totalCardsReversed = 0;

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player winner = null;

    Board board;
    public void init(){
//        TODO Board size will be dinamic in the future
        int boardSize = 4;

//        TODO this list will be obtained from a DB or remote site in the future
        List<String> possibleValues = Arrays.asList("🐱", "🐶", "🦁", "🐒", "🐥", "😃", "🐢", "👻");

        board = new Board(4);
        board.shuffle(possibleValues);

        player1 = new HumanPlayer("Spiderman \uD83D\uDD77️");
        player2 = new HumanPlayer("Starlight ⚡️");
        currentPlayer = player1;
    }

    public Player getCurrentPlayer(){ return currentPlayer; }

    public void cardClicked(Button button, int row, int column){
        Piece p = board.getPiece(row, column);
        button.setText(p.value);
        totalCardsReversed++;
        Log.d(myClassTag, "He incrementat totalCardsRevers");

//        TODO tmp changing tour every click
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    public int getTotalCardsReversed() {
        return totalCardsReversed;
    }
}
