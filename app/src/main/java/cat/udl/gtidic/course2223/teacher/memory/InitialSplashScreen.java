package cat.udl.gtidic.course2223.teacher.memory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

public class InitialSplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setting splash screen for api >= 31
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            SplashScreen s = SplashScreen.installSplashScreen(this);
            s.setKeepOnScreenCondition(() -> true);
        }else{
            setContentView(R.layout.activity_initial_splash_screen);
        }

        // do heavy load things here
        // example case wait for N seconds and end this activity
        new Handler().postDelayed(() -> endActivity(), 5000);   //5 seconds
    }

    private void endActivity(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}