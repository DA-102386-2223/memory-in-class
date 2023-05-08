package cat.udl.gtidic.course2223.teacher.memory.helpers.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.udl.gtidic.course2223.teacher.memory.R;
import cat.udl.gtidic.course2223.teacher.memory.models.Game.Game;
import cat.udl.gtidic.course2223.teacher.memory.models.MultiplayerMatch;
import cat.udl.gtidic.course2223.teacher.memory.views.GameActivity;

class MultiplayerMatchViewHolder extends RecyclerView.ViewHolder{

    View itemView;
    TextView username;
    public MultiplayerMatchViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.username = itemView.findViewById(R.id.rv_item_username);
        itemView.findViewById(R.id.item_rv).setOnClickListener(v -> jumpToGame());
    }

    private void jumpToGame() {

        String firebaseKey = username.getText().toString();

        Activity fake = (Activity)itemView.getContext();
        Intent i = new Intent(fake, GameActivity.class);
        i.putExtra(Game.MULTIPLAYER_KEY, Game.MULTIPLAYER_TYPE_CONNECT);
        i.putExtra(Game.MULTIPLAYER_GAME_KEY, firebaseKey);
        fake.startActivity(i);

//        startActivity(i);
    }

    public void render(MultiplayerMatch mm) {
        username.setText(mm.getUserCreator());
    }
}