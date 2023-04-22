package cat.udl.gtidic.course2223.teacher.memory.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import cat.udl.gtidic.course2223.teacher.memory.R;
import cat.udl.gtidic.course2223.teacher.memory.databinding.ActivityForumBinding;
import cat.udl.gtidic.course2223.teacher.memory.viewmodels.GameViewModel;

public class ForumActivity extends AppCompatActivity {

    private GameViewModel game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        game = new ViewModelProvider(this).get(GameViewModel.class);
        ActivityForumBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_forum);
        binding.setGameViewModel(game);
        binding.setLifecycleOwner(this);
        game.setContext(this.getApplicationContext());
        game.enableForum();
    }
}