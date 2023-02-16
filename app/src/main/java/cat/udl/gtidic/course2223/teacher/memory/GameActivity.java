package cat.udl.gtidic.course2223.teacher.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "returnKey"; // Create an intent
    public static final String EXTRA_REPLY_POINTS = "keyPoints"; // Create an intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // collecting data from Intent - Bundle from previous Activity
        Bundle extra = getIntent().getExtras();
//        protected if no data received in Extra
        if (extra != null) {
            String nomJugador1 = extra.getString("nomDelJugador1");
            if (nomJugador1 != null) {
                Toast.makeText(this, nomJugador1, Toast.LENGTH_LONG).show();
            }
        }

        findViewById(R.id.finishButon).setOnClickListener(view -> {
            // Define a return Key Value
            Intent intent = new Intent();

            // Put the data to return into the extra
            String reply = "Ha guanyat el jugador 1";
            intent.putExtra(EXTRA_REPLY, reply);
            intent.putExtra(EXTRA_REPLY_POINTS, 100);

            // Set the activity's result to RESULT_OK
            setResult(RESULT_OK, intent);

            // Finish the current activity
            finish();
        });
    }
}