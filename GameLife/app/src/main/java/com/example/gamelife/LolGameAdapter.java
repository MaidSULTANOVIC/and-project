package com.example.gamelife;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LolGameAdapter extends RecyclerView.Adapter<LolGameAdapter.ViewHolder>{

    private ArrayList<LolGame> mGames;

    LolGameAdapter(ArrayList<LolGame> games){
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
        holder.champ.setText(mGames.get(position).getChampion());
        holder.condition.setText(mGames.get(position).getCondition());
        holder.kda.setText(mGames.get(position).getKda());
        holder.ratio.setText(mGames.get(position).getKdaRatio());
        if(mGames.get(position).getCondition().equals("Victory")){
            holder.roundedShape.setTint(0xDE482F75);
        }else{

            holder.roundedShape.setTint(0x73D61111);

        }
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView type;
        TextView champ;
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
