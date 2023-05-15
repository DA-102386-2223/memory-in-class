package cat.udl.gtidic.course2223.teacher.memory.views;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import cat.udl.gtidic.course2223.teacher.memory.R;
import cat.udl.gtidic.course2223.teacher.memory.helpers.AppCompatActivityPlus;
import cat.udl.gtidic.course2223.teacher.memory.helpers.GlobalInfo;
import cat.udl.gtidic.course2223.teacher.memory.helpers.adapters.MultiplayerMatchesAdapter;
import cat.udl.gtidic.course2223.teacher.memory.helpers.providers.MultiplayerMatchesProvider;
import cat.udl.gtidic.course2223.teacher.memory.models.Game.Game;
import cat.udl.gtidic.course2223.teacher.memory.models.MultiplayerMatch;

public class MultiplayerGameSelector extends AppCompatActivityPlus {

    private MultiplayerMatchesProvider provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_game_selector);
        
//        getFirstGameAvailable();
        initRecylerView();
    }

    private void initRecylerView() {
        provider = new MultiplayerMatchesProvider();

        MultiplayerMatchesAdapter adapter =  new MultiplayerMatchesAdapter(provider.getLaMevaLlista(), multiplayerMatch -> jumpToGamev2(multiplayerMatch));
        provider.setAdapter(adapter);
        provider.getFromFirebase();

        RecyclerView rv = findViewById(R.id.rv_matches);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);

        rv.setAdapter(adapter);
//        pending things to do
    }

    /**
     * Connecta (de moment) amb el primer game creat que no hagi estat matchat
     */
    public void getFirstGameAvailable(){
        DatabaseReference myFirebaseDBGames = GlobalInfo.getIntance().getFirebaseGames();
        Query q = myFirebaseDBGames.orderByChild("status").equalTo(Game.MULTIPLAYER_STATUS_PENDING).limitToFirst(1);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot gameSelected : snapshot.getChildren()){
                    String key = gameSelected.getKey();
                    jumpToGame(key);
                    finish();
                    return;
                }
                Toast.makeText(getApplicationContext(), R.string.NoGamesAvailable, Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(myClassTag, "Problemes de connexio");
            }
        });
    }

    private void jumpToGame(String firebaseKey){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra(Game.MULTIPLAYER_KEY, Game.MULTIPLAYER_TYPE_CONNECT);
        i.putExtra(Game.MULTIPLAYER_GAME_KEY, firebaseKey);
        startActivity(i);
    }

    private void jumpToGamev2(MultiplayerMatch multiplayerMatch) {
        String firebaseKey = multiplayerMatch.getMatchKey();
        Intent i = new Intent(this.getApplicationContext(), GameActivity.class);
        i.putExtra(Game.MULTIPLAYER_KEY, Game.MULTIPLAYER_TYPE_CONNECT);
        i.putExtra(Game.MULTIPLAYER_GAME_KEY, firebaseKey);

        finish();
        provider.detach();

        startActivity(i);
    }
}