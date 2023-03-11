package cat.udl.gtidic.course2223.teacher.memory.models;

import android.util.Log;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

import cat.udl.gtidic.course2223.teacher.memory.models.Player.HumanPlayer;
import cat.udl.gtidic.course2223.teacher.memory.models.Player.Player;

public class Game {
    protected String myClassTag = this.getClass().getSimpleName();

    final int POINTS_PER_MATCH = 10;
    int maxPoints = -1;
    private int totalCardsReversed = 0;

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player winner = null;

    Piece piece1Selected;
    Piece piece2Selected;

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

    /**
     * fa el canvi de turn entre els jugadors
     * Cal cridar-la només quan es compleixin les condicions per canviar de torn
     */
    private void changeTurn(){
        currentPlayer = currentPlayer == player1 ? player2 : player1;
        Log.d(myClassTag, "He canviat de turn, ara li toca al jugador " + currentPlayer.getName());
    }

    public Player getCurrentPlayer(){ return currentPlayer; }

    public void cardClicked(Button button, int row, int column){
        Piece p = board.getPiece(row, column);
        button.setText(p.getValue());
        totalCardsReversed++;
        Log.d(myClassTag, "He incrementat totalCardsRevers");

        // assignant la piece clicada a la piece corresponent
        if (piece1Selected == null) piece1Selected = p;
        else if (piece2Selected == null) piece2Selected = p;
        else Log.d(myClassTag, "Unexpected case, please check");

//        logging current selected cards
        if (piece1Selected != null) Log.d(myClassTag, "Card 1: " + piece1Selected.getValue());
        else Log.d(myClassTag, "Card 1 not selected");
        if (piece2Selected != null) Log.d(myClassTag, "Card 1: " + piece2Selected.getValue());
        else Log.d(myClassTag, "Card 2 not selected");

        checkRoundEnded();
        recoverRound();
    }


    /**
     * Revisa si en aquesta Round s'ha acabat i aplica les accions corresponents
     */
    private void checkRoundEnded() {
        if (piece1Selected != null && piece2Selected != null){
            Log.d(myClassTag, "Fi de ronda");

//            revisa si match
            if (piece1Selected.getValue().equals(piece2Selected.getValue())){
                setMatch();
            }else{
//            canvia de torn
                changeTurn();
            }

            recoverRound();
        }
    }

    /**
     * Aplica les accions en cas de match entre cartes
     */
    private void setMatch(){
        Log.d(myClassTag, "Match realitzat i comptant punts");
        currentPlayer.addPoints(POINTS_PER_MATCH);
        setCurrentPiecesAsMatched();
    }

    /**
     * Assigna les 2 peces que actualment estan seleccionades com que ja han fet match
     * Això farà que no siguin seleccionables
     */
    private void setCurrentPiecesAsMatched(){
        piece1Selected.setAlreadyMatched(true);
        piece2Selected.setAlreadyMatched(true);
    }

    /**
     * torna a inicialitzar la ronda de selecció de cartes
     */
    private void recoverRound(){
        if (piece1Selected != null && piece2Selected != null){
            piece1Selected = null;
            piece2Selected = null;
        }
    }

    public int getTotalCardsReversed() {
        return totalCardsReversed;
    }
}
