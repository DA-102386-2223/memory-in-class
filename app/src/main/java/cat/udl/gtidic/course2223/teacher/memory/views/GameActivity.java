package cat.udl.gtidic.course2223.teacher.memory.views;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import cat.udl.gtidic.course2223.teacher.memory.helpers.AppCompatActivityPlus;
import cat.udl.gtidic.course2223.teacher.memory.R;
import cat.udl.gtidic.course2223.teacher.memory.databinding.ActivityGameBinding;
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

        createListenersForButtons();

        game.setContext(this.getApplicationContext());
        game.saveGameIntoDB();
    }

    private void createListenersForButtons(){
//      TODO it have to be improved in the future! -> creating buttons dynamically ;)
        findViewById(R.id.button00).setOnClickListener(view -> buttonClicked(view, 0, 0));
        findViewById(R.id.button01).setOnClickListener(view -> buttonClicked(view, 0, 1));
        findViewById(R.id.button02).setOnClickListener(view -> buttonClicked(view, 0, 2));
        findViewById(R.id.button03).setOnClickListener(view -> buttonClicked(view, 0, 3));

        findViewById(R.id.button10).setOnClickListener(view -> buttonClicked(view, 1, 0));
        findViewById(R.id.button11).setOnClickListener(view -> buttonClicked(view, 1, 1));
        findViewById(R.id.button12).setOnClickListener(view -> buttonClicked(view, 1, 2));
        findViewById(R.id.button13).setOnClickListener(view -> buttonClicked(view, 1, 3));

        findViewById(R.id.button20).setOnClickListener(view -> buttonClicked(view, 2, 0));
        findViewById(R.id.button21).setOnClickListener(view -> buttonClicked(view, 2, 1));
        findViewById(R.id.button22).setOnClickListener(view -> buttonClicked(view, 2, 2));
        findViewById(R.id.button23).setOnClickListener(view -> buttonClicked(view, 2, 3));

        findViewById(R.id.button30).setOnClickListener(view -> buttonClicked(view, 3, 0));
        findViewById(R.id.button31).setOnClickListener(view -> buttonClicked(view, 3, 1));
        findViewById(R.id.button32).setOnClickListener(view -> buttonClicked(view, 3, 2));
        findViewById(R.id.button33).setOnClickListener(view -> buttonClicked(view, 3, 3));
    }

    private void buttonClicked(View view, int row, int column){
        Log.d(myClassTag, "button clicked. Row: " + row + ". Column: " + column);
        game.cardClicked(view, row, column);
    }
}