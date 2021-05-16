package com.example.gamelife.pubg;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gamelife.R;
import com.example.gamelife.pubg.models.PubgGame;
import com.example.gamelife.pubg.models.gameData.PubgMatchPlayerStats;
import com.example.gamelife.pubg.models.playerStats.PubgPlayerStats;

import java.util.ArrayList;


public class PubgGameAdapter extends RecyclerView.Adapter<PubgGameAdapter.ViewHolder>{

    //The list of games
    private ArrayList<PubgGame> mGames;

    public PubgGameAdapter(ArrayList<PubgGame> games){
        mGames = games;
    }


    @NonNull
    @Override
    public PubgGameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.pubg_recyclerview, parent, false);
        return new PubgGameAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PubgGameAdapter.ViewHolder holder, int position) {
        PubgMatchPlayerStats player = mGames.get(position).getPlayer();

        holder.gameMode.setText(mGames.get(position).getGameMode());

        holder.damage.setText(String.format("%.2f",player.getDamageDealt()));
        holder.kill.setText(player.getKills()+"");
        holder.position.setText("#"+player.getWinPlace()+"");
    }

    public void addGame(PubgGame pg){
        mGames.add(pg);
    }

    public void clearGames(){
        int size = mGames.size();
        mGames.clear();
        notifyItemRangeRemoved(0,size);
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView gameMode;
        TextView position;
        TextView kill;
        TextView damage;

        ViewHolder(View itemView){
            super(itemView);
            gameMode = itemView.findViewById(R.id.pubg_rv_type);
            position = itemView.findViewById(R.id.pubg_rv_position);
            kill = itemView.findViewById(R.id.pubg_rv_kill);
            damage = itemView.findViewById(R.id.pubg_rv_damage);

        }
    }
}
