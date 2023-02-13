package cat.udl.gtidic.course2223.teacher.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle extra = getIntent().getExtras();
        String nomJugador1 = extra.getString("nomDelJugador1");
        Toast.makeText(this, nomJugador1, Toast.LENGTH_LONG).show();

        finish();
    }
}