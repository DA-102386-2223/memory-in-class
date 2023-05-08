package cat.udl.gtidic.course2223.teacher.memory.models;

public class MultiplayerMatch {
    public String getUserCreator() {
        return userCreator;
    }

    String userCreator;
    int status = 1;

    public MultiplayerMatch(String user){
        this.userCreator = user;
    }
}