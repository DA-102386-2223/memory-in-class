package cat.udl.gtidic.course2223.teacher.memory.helpers;

public class GlobalInfo {
    private final int SPLASH_SCREEN_TIMEOUT = 500;

    private static GlobalInfo instance = new GlobalInfo();

    private GlobalInfo(){
//        return new GlobalInfo();
    }

    public static GlobalInfo getIntance(){
        return instance;
    }

    public int getSplashScreenTimeout(){
        return SPLASH_SCREEN_TIMEOUT;
    }
}
