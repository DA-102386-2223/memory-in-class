package cat.udl.gtidic.course2223.teacher.memory.models.Game;

import android.util.Log;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Arrays;
import java.util.List;

import cat.udl.gtidic.course2223.teacher.memory.models.Board;
import cat.udl.gtidic.course2223.teacher.memory.models.Card;
import cat.udl.gtidic.course2223.teacher.memory.models.Player.HumanPlayer;
import cat.udl.gtidic.course2223.teacher.memory.models.Player.Player;

@Entity
public class Game {
    @Ignore
    protected String myClassTag = this.getClass().getSimpleName();

    @PrimaryKey(autoGenerate = true)
    public long id;

    protected int POINTS_PER_MATCH = 10;
    protected int maxPoints = -1;
    protected int totalCardsReversed = 0;

    public static final int MULTIPLAYER_STATUS_PENDING = 1;
    public static final int MULTIPLAYER_STATUS_MATCHED = 2;
    public static final int MULTIPLAYER_TYPE_CREATE = 1;
    public static final int MULTIPLAYER_TYPE_CONNECT = 2;

    public static final String MULTIPLAYER_KEY = "multiplayer-key";
    public static final String MULTIPLAYER_GAME_KEY = "multiplayer-game-key";

    @Ignore
    private Player player1;
    @Ignore
    private Player player2;
    @Ignore
    private Player currentPlayer;
    @Ignore
    private Integer currentPlayerMultiplayer;

    @Ignore
    private Player winner = null;

    @Ignore
    int card1SelectedRow = -1;
    @Ignore
    int card1SelectedColumn = -1;

    @Ignore
    Card card2Selected;

    @Ignore
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

        cleanCard1Selected();
    }

    /**
     * fa el canvi de turn entre els jugadors
     * Cal cridar-la només quan es compleixin les condicions per canviar de torn
     */
    private void changeTurn(){
//        canviem el torn en local play
        currentPlayer = currentPlayer == player1 ? player2 : player1;
        Log.d(myClassTag, "He canviat de turn, ara li toca al jugador " + currentPlayer.getName());

//        canviem el torn del multiplayer
        if (currentPlayerMultiplayer != null){
            if (currentPlayerMultiplayer == Game.MULTIPLAYER_TYPE_CONNECT) currentPlayerMultiplayer = Game.MULTIPLAYER_TYPE_CREATE;
            else currentPlayerMultiplayer = Game.MULTIPLAYER_TYPE_CONNECT;
            Log.d(myClassTag, "He canviat de turn en multiplayer");
        }
    }

    public Player getCurrentPlayer(){ return currentPlayer; }

    public void cardClickedMVVM(int row, int column){
        Card p = board.getCard(row, column);
        totalCardsReversed++;
        Log.d(myClassTag, "He incrementat totalCardsRevers");
        p.setVisible(true);

        // assignant la piece clicada a la piece corresponent
        if (card1Selected() == null) setCard1Selected(row, column);
        else if (card2Selected == null) card2Selected = p;
        else Log.d(myClassTag, "Unexpected case, please check");

//        logging current selected cards
        if (card1Selected() != null) Log.d(myClassTag, "Card 1: " + card1Selected().getValue());
        else Log.d(myClassTag, "Card 1 not selected");
        if (card2Selected != null) Log.d(myClassTag, "Card 1: " + card2Selected.getValue());
        else Log.d(myClassTag, "Card 2 not selected");

        checkRoundEnded();
    }

    private void setCard1Selected(int row, int column) {
        card1SelectedColumn = column;
        card1SelectedRow = row;
    }

    private Card card1Selected() {
        return board.getCard(card1SelectedRow, card1SelectedColumn);
    }


    /**
     * Revisa si en aquesta Round s'ha acabat i aplica les accions corresponents al canvi de ronda
     */
    private void checkRoundEnded() {
        if (card1Selected() != null && card2Selected != null) {
            Log.d(myClassTag, "Fi de ronda");

            if (card1Selected().getValue().equals(card2Selected.getValue())){
                // revisa si match
                setMatch();

                // check winner
                if (board.isFull()){
                    winner = player1.getPoints() > player2.getPoints() ? player1 : player2;
                    maxPoints = winner.getPoints();
                    Log.d(myClassTag, "El winner is: " + winner.getName());
                }

            } else {
                // canvia de torn
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
        card1Selected().setAlreadyMatched(true);
        card2Selected.setAlreadyMatched(true);
    }

    /**
     * torna a inicialitzar la ronda de selecció de cartes
     */
    private void recoverRound(){
        if (card1Selected() != null && card2Selected != null){
            card1Selected().setVisible(false);
            card2Selected.setVisible(false);
//            card1Selected = null;
            cleanCard1Selected();
            card2Selected = null;
        }
    }

    private void cleanCard1Selected() {
        card1SelectedColumn = -1;
        card1SelectedRow = -1;
    }

    public Card getCard(int row, int col){
        return board.getCard(row, col);
    }

    public int getTotalCardsReversed() {
        return totalCardsReversed;
    }

    public Object getBoard() {
        return board;
    }

    public void setBoard(Board b) {
        this.board = b;
    }

    public void setCurrentPlayerMultiplayer(Integer turn){
        this.currentPlayerMultiplayer = turn;
    }

    public Integer getCurrentPlayerMultiplayer() {
        return currentPlayerMultiplayer;
    }
}
