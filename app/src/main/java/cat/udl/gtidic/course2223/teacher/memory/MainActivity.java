package cat.udl.gtidic.course2223.teacher.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.infoButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWindow();
            }
        });

        findViewById(R.id.playButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });
    }

    /*  Open a new chrome browser webpage
     */
    private void openWindow(){
        Uri uri = Uri.parse("http://www.google.com");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }

    /** Open the new activity game
     */
    private void play(){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("nomDelJugador1", "Albert");
        startActivity(i);
    }
}