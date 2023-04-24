package cat.udl.gtidic.course2223.teacher.memory.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Board {
    protected String myClassTag = this.getClass().getSimpleName();

    public ArrayList<ArrayList<Card>> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<ArrayList<Card>> board) {
        this.board = board;
    }

    ArrayList<ArrayList<Card>> board;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    int size;

    public Board(){
        ;
    }
    /**
     * Init the Board with the size * size received
     * @param size rows and columns of the board
     */
    public Board(int size){
        this.size = size;
        board = new ArrayList<>(size);
        for (int i = 0; i<size; i++){
            ArrayList<Card> line = new ArrayList<>(size);
            board.add(line);
            for (int j = 0; j<size; j++){
                line.add(new Card());
            }
        }
    }

    /**
     * assign possibleValues to the Board Pieces in a random way
     * @param possibleValues list of strings to be assigned to the Pieces
     */
    public void shuffle(List<String> possibleValues){
//        creating a list of pieces pending to be assigned
        List<Card> pendingToAssign = new LinkedList<>();
        for (int i = 0; i<size; i++){
            for (int j = 0; j<size; j++){
                pendingToAssign.add(board.get(i).get(j));
            }
        }

//        assigning pieces in pack of 2 using possibleValues
        Random r = new Random();
        int intPiece1, intPiece2, intPositionValues;
//        I create a copy of the parameter in order to manipulate it
        List<String> internalPossibleValues = new ArrayList<>(possibleValues);
        while (pendingToAssign.size() > 0){
            intPiece1 = r.nextInt(pendingToAssign.size());
            Card card1 = pendingToAssign.get(intPiece1);
            pendingToAssign.remove(intPiece1);
            intPiece2 = r.nextInt(pendingToAssign.size());
            Card card2 = pendingToAssign.get(intPiece2);
            pendingToAssign.remove(intPiece2);

            intPositionValues = r.nextInt(internalPossibleValues.size());
            String value = internalPossibleValues.get(intPositionValues);
            card1.setValue(value);
            card2.setValue(value);
            internalPossibleValues.remove(intPositionValues);

            Log.d(myClassTag, "Assigned value: " + value + " to pieces: " + card1 + " " + card2);
        }

        Log.d(myClassTag, "Pintant l'assignació realitzada");
        for (int i = 0; i<size; i++){
            String line = "| ";
            for (int j = 0; j<size; j++){
                line += board.get(i).get(j).getValue() + " | ";
            }
            Log.d(myClassTag, line);
        }
    }

    public boolean isCellEmpty(int row, int col){
        // TOOD pending
        return true;
    }

    /**
     * Revisa si totes les cartes del Board han estat girades
     * @return boolean
     */
    public boolean isFull(){
        for (int i = 0; i<size; i++){
            for (int j = 0; j<size; j++){
                if (!board.get(i).get(j).isAlreadyMatched()) return false;
            }
        }
        return true;
    }

    public Card getCard(int row, int column){
        return board.get(row).get(column);
    }
}
