package cat.udl.gtidic.course2223.teacher.memory.helpers.providers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cat.udl.gtidic.course2223.teacher.memory.helpers.GlobalInfo;
import cat.udl.gtidic.course2223.teacher.memory.helpers.adapters.MultiplayerMatchesAdapter;
import cat.udl.gtidic.course2223.teacher.memory.models.Game.Game;
import cat.udl.gtidic.course2223.teacher.memory.models.MultiplayerMatch;

public class MultiplayerMatchesProvider {

    MultiplayerMatchesAdapter adapter;
    public List<MultiplayerMatch> getLaMevaLlista() {
        return laMevaLlista;
    }

    private ValueEventListener listener;
    private DatabaseReference myFirebaseDBGames;

    List<MultiplayerMatch> laMevaLlista = new ArrayList<>();

    public MultiplayerMatchesProvider(){
        laMevaLlista.add(new MultiplayerMatch("-asdfasdfas", "Albert", "albert@albert.cat"));
    }

    public void getFromFirebase(){
        myFirebaseDBGames = GlobalInfo.getIntance().getFirebaseGames();
        Query q = myFirebaseDBGames.orderByChild("status").equalTo(Game.MULTIPLAYER_STATUS_PENDING);
        listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                refreshData(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Provider", "Problemes de connexio");
            }
        };
        q.addValueEventListener(listener);
    }

    private void refreshData(DataSnapshot snapshot){
        laMevaLlista.clear();
        for (DataSnapshot gameSelected : snapshot.getChildren()){
            String key = gameSelected.getKey();
            String userName = gameSelected.child("userName").getValue(String.class);
            String userEmail = gameSelected.child("userEmail").getValue(String.class);
            MultiplayerMatch mm = new MultiplayerMatch(key, userName, userEmail);
            laMevaLlista.add(mm);
        }
        adapter.notifyItemRangeChanged(0, laMevaLlista.size());
    }

    public void setAdapter(MultiplayerMatchesAdapter adapter) {
        this.adapter = adapter;
    }

    public void detach() {
        myFirebaseDBGames.removeEventListener(listener);
    }
}
