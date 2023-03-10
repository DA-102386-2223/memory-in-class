package cat.udl.gtidic.course2223.teacher.memory.models.Player;

public class HumanPlayer extends Player {
    private final String name;

    public String getName() {
        return name;
    }

    public HumanPlayer(String name){
        this.name = name;
    }
}
