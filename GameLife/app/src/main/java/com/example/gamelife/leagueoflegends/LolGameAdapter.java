package com.example.gamelife.leagueoflegends;

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
import com.example.gamelife.leagueoflegends.models.LolGame;

import java.util.ArrayList;

/**
 * LolGameAdapter is RecyclerView adapter used to display each game
 */
public class LolGameAdapter extends RecyclerView.Adapter<LolGameAdapter.ViewHolder>{

    //The list of games
    private ArrayList<LolGame> mGames;


    public LolGameAdapter(ArrayList<LolGame> games){
        mGames = games;
    }

    @NonNull
    @Override
    public LolGameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.lol_recyclerview, parent, false);
        return new LolGameAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LolGameAdapter.ViewHolder holder, int position) {
        holder.type.setText(mGames.get(position).getType());

        //Depending the champion, it will display a different picture
        if(mGames.get(position).getChampion() == 0){
            holder.champ.setImageResource(R.drawable.nochamp);
        }else{
            holder.champ.setImageResource(mGames.get(position).getChampion());
        }

        //Set the data in the layout
        holder.condition.setText(mGames.get(position).getCondition());
        holder.kda.setText(mGames.get(position).getKda());
        holder.ratio.setText(mGames.get(position).getKdaRatio());

        //If the user won the game, the background color of the container will change
        if(mGames.get(position).getCondition().equals("Victory")){
            holder.roundedShape.setTint(0xDE482F75);
        }else{

            holder.roundedShape.setTint(0x73D61111);

        }
    }

    public void addGame(LolGame lg){
        mGames.add(lg);
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
        TextView type;
        ImageView champ;
        TextView condition;
        TextView kda;
        TextView ratio;
        ConstraintLayout container;
        Drawable roundedShape;

        ViewHolder(View itemView){
            super(itemView);
            type = itemView.findViewById(R.id.txtTypeGame);
            champ = itemView.findViewById(R.id.txtChamp);
            condition = itemView.findViewById(R.id.txtWin);
            kda = itemView.findViewById(R.id.txtKda);
            ratio = itemView.findViewById(R.id.txtKdaRatio);
            container = itemView.findViewById(R.id.recyclerLolContainer);
            roundedShape = container.getBackground();
        }



    }
}
