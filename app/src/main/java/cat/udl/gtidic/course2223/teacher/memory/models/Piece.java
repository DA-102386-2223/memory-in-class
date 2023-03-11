package cat.udl.gtidic.course2223.teacher.memory.models;

public class Piece {
    private String value;
    private boolean alreadyMatched = false;

    public void setValue(String value) {
        this.value = value;
    }
    public String getValue(){
        return value;
    }

    @Override
    public String toString(){
        return "Peça amb valor: " + value;
    }

    public void setAlreadyMatched(boolean alreadyMatched) {
        this.alreadyMatched = alreadyMatched;
    }

    public boolean isAlreadyMatched() {
        return alreadyMatched;
    }
}
