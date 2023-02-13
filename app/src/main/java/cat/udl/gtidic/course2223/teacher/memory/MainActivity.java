package cat.udl.gtidic.course2223.teacher.memory;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // class property
    ActivityResultLauncher<Intent> someActivityResultLauncher;

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

        someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) doSomeOperationsWithReturnedData(data);
                    }
                }
            }
            );

        findViewById(R.id.playButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                play();
                playWithResult();
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

    private void playWithResult(){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("nomDelJugador1", "Albert");
        someActivityResultLauncher.launch(i);
    }

    private void doSomeOperationsWithReturnedData(Intent data){
        String info = data.getStringExtra(GameActivity.EXTRA_REPLY);
        int points = data.getIntExtra(GameActivity.EXTRA_REPLY_POINTS, -1);
        Toast.makeText(this, "Informació rebuda: " + info, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Ha fet " + points + " punts.", Toast.LENGTH_SHORT).show();
    }
}