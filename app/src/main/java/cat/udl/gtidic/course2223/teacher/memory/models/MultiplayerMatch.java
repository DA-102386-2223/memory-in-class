package cat.udl.gtidic.course2223.teacher.memory.models;

public class MultiplayerMatch {
    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getMatchKey() {
        return matchKey;
    }

    String userName;
    String userEmail;
    String matchKey;

    public MultiplayerMatch(String matchKey, String userName, String userEmail){
        this.matchKey = matchKey;
        this.userName = userName;
        this.userEmail = userEmail;
    }
}