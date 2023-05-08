package cat.udl.gtidic.course2223.teacher.memory.helpers.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.udl.gtidic.course2223.teacher.memory.R;
import cat.udl.gtidic.course2223.teacher.memory.models.MultiplayerMatch;

class MultiplayerMatchViewHolder extends RecyclerView.ViewHolder{

    View itemView;
    TextView username;
    public MultiplayerMatchViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.username = itemView.findViewById(R.id.rv_item_username);
    }

    public void render(MultiplayerMatch mm) {
        username.setText(mm.getUserCreator());
    }
}