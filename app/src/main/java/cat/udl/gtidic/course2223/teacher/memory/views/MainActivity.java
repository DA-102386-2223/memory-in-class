package cat.udl.gtidic.course2223.teacher.memory.views;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cat.udl.gtidic.course2223.teacher.memory.helpers.AppCompatActivityPlus;
import cat.udl.gtidic.course2223.teacher.memory.R;
import cat.udl.gtidic.course2223.teacher.memory.models.MemoryDatabase;

public class MainActivity extends AppCompatActivityPlus {

    // class property
    ActivityResultLauncher<Intent> someActivityResultLauncher;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.infoButton).setOnClickListener(view -> openWindow());

        someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) doSomeOperationsWithReturnedData(data);
                    }
                }
        );

        findViewById(R.id.playButton).setOnClickListener(view -> {
//          play();
//          playWithResult();
            playInitial();
        });

        findViewById(R.id.clearButton).setOnClickListener(view -> clearDB());
        findViewById(R.id.readDBButton).setOnClickListener(view -> getPoints());
        findViewById(R.id.forumButton).setOnClickListener(view -> openForum());

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();
        Toast.makeText(this, "Hola Usuari " + user.getUid() + " " + email, Toast.LENGTH_SHORT).show();

        findViewById(R.id.btLogout).setOnClickListener(v -> logout());
    }

    private void openForum() {
        Intent i = new Intent(this, ForumActivity.class);
        startActivity(i);
    }

    private void logout() {
        mAuth.signOut();
        finish();
    }

    private void clearDB() {
        String dbName = "memory.db";
        MemoryDatabase dbRoom = Room.databaseBuilder(this.getApplicationContext(), MemoryDatabase.class, dbName).allowMainThreadQueries().build();
        dbRoom.gameDAO().deleteAll();
        dbRoom.close();
    }

    private void getPoints() {
        String dbName = "memory.db";
        MemoryDatabase dbRoom = Room.databaseBuilder(this.getApplicationContext(), MemoryDatabase.class, dbName).allowMainThreadQueries().build();
        int lastPoints = dbRoom.gameDAO().getLastGamePoints();
        int maxPoints = dbRoom.gameDAO().getMaxPoints();
        String missatge = String.format("Max punts: %d. Ultims punts: %d", maxPoints, lastPoints );
        Log.d(myClassTag, missatge );
        dbRoom.close();
    }

    /*  Open a new chrome browser webpage
     */
    private void openWindow(){
        Uri uri = Uri.parse("http://www.google.com");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }

    /** Open the new activity game */
    private void play(){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("nomDelJugador1", "Albert");
        startActivity(i);
    }

    /** Open the new activity game v2 */
    private void playWithResult(){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("nomDelJugador1", "Albert");
        someActivityResultLauncher.launch(i);
    }

    /** Open the new activity game v3 */
    private void playInitial(){
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }

    private void doSomeOperationsWithReturnedData(Intent data){
        String info = data.getStringExtra(GameActivity.EXTRA_REPLY);
        int points = data.getIntExtra(GameActivity.EXTRA_REPLY_POINTS, -1);
//        Toast.makeText(this, "Informació rebuda: " + info, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "Ha fet " + points + " punts.", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", "Informació rebuda: " + info);
        Log.d("MainActivity", "Ha fet " + points + " punts.");
//        Log.banana(); error done expressament

    }
}