package cat.udl.gtidic.course2223.teacher.memory.models.Player;

public abstract class Player {
    private int points = 0;

    public abstract String getName();

    public void addPoints(int pointsToAdd){
        points += pointsToAdd;
    }

    public int getPoints(){
        return points;
    }
}
