package cat.udl.gtidic.course2223.teacher.memory.views;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import cat.udl.gtidic.course2223.teacher.memory.helpers.AppCompatActivityPlus;
import cat.udl.gtidic.course2223.teacher.memory.R;
import cat.udl.gtidic.course2223.teacher.memory.databinding.ActivityGameBinding;
import cat.udl.gtidic.course2223.teacher.memory.models.Game.Game;
import cat.udl.gtidic.course2223.teacher.memory.viewmodels.GameViewModel;

public class GameActivity extends AppCompatActivityPlus {
    public static final String EXTRA_REPLY = "returnKey"; // Create an intent
    public static final String EXTRA_REPLY_POINTS = "keyPoints"; // Create an intent

//    TODO it should be created using a desing pattern like MVVM
    private GameViewModel game;

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

        game = new ViewModelProvider(this).get(GameViewModel.class);
        ActivityGameBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        binding.setGameViewModel(game);
        binding.setLifecycleOwner(this);

//        creating Multiplayer game if sent
        if (extra != null){
            int multiplayerType = extra.getInt(Game.MULTIPLAYER_KEY);
            if (multiplayerType == Game.MULTIPLAYER_TYPE_CREATE) game.multiplayerCreate();
            if (multiplayerType == Game.MULTIPLAYER_TYPE_CONNECT) game.multiplayerConnect(extra.getString(Game.MULTIPLAYER_GAME_KEY));
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

        game.setContext(this.getApplicationContext());
        game.saveGameIntoDB();
    }
}