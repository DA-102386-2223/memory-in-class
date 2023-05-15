package cat.udl.gtidic.course2223.teacher.memory.views;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import cat.udl.gtidic.course2223.teacher.memory.helpers.GlobalInfo;

public class InitialActivity extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        GlobalInfo.getIntance().enableSharedPreferences(this.getApplicationContext());
//        Log.d("albert", "hola soc InitialActivity");

        saveStartApplication();
    }

    private void saveStartApplication() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalTime time = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            String currentTime = time.format(formatter);
            String lastStart = GlobalInfo.getIntance().getSharedPreferencesString("startApplication");
            if (lastStart != null) {
                Toast.makeText(this.getApplicationContext(), "Últim start at:" + lastStart, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.getApplicationContext(), "No s'ha obert mai l'aplicació", Toast.LENGTH_SHORT).show();
            }
            GlobalInfo.getIntance().setSharedPreferencesString("startApplication", currentTime);
        }else{
            Log.d("InitialActivity", "No puc guardar info en API < 26");
        }
    }
}
