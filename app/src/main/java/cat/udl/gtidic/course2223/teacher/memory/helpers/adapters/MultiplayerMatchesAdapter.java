package cat.udl.gtidic.course2223.teacher.memory.helpers.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.udl.gtidic.course2223.teacher.memory.models.MultiplayerMatch;

public class MultiplayerMatchesAdapter extends RecyclerView.Adapter<MultiplayerMatchViewHolder> {

    private List<MultiplayerMatch> llista;

    public MultiplayerMatchesAdapter(List<MultiplayerMatch> llista){
        this.llista = llista;
    }

    @NonNull
    @Override
    public MultiplayerMatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MultiplayerMatchViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
