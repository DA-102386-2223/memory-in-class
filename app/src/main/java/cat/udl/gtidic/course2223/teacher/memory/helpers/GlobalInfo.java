package cat.udl.gtidic.course2223.teacher.memory.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GlobalInfo {
    private final int SPLASH_SCREEN_TIMEOUT = 500;
    private final String FIREBASE_DB = "https://dam-memory-default-rtdb.europe-west1.firebasedatabase.app/";

    private SharedPreferences myPreferences;
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

    public String getFIREBASE_DB() {
        return FIREBASE_DB;
    }

    public DatabaseReference getFirebaseGames(){
        String url = this.getFIREBASE_DB();
        FirebaseDatabase database = FirebaseDatabase.getInstance(url);
        return database.getReference("matches");
    }

    public void enableSharedPreferences(Context context) {
        this.myPreferences = context.getSharedPreferences("myPreferences", 0);
    }

    public void setSharedPreferencesString(String key, String value) {
        myPreferences.edit().putString(key, value).apply();
    }

    public String getSharedPreferencesString(String key) {
        return myPreferences.getString(key, null);
    }
}
