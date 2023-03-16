package cat.udl.gtidic.course2223.teacher.memory.models;

import android.util.Log;

public class Card {
    protected String myClassTag = this.getClass().getSimpleName();
    private String value;
    private boolean alreadyMatched = false;
    private boolean visible = false;

    public void setValue(String value) {
        this.value = value;
    }
    public String getValue(){
        return value;
    }

    @Override
    public String toString(){
        return "Card amb valor: " + value;
    }

    public void setAlreadyMatched(boolean alreadyMatched) {
        this.alreadyMatched = alreadyMatched;
    }

    public boolean isAlreadyMatched() {
        return alreadyMatched;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String publicValue(){
        return visible || alreadyMatched ? value : "";
    }
}
