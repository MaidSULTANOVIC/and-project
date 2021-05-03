package com.example.gamelife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder>{

    private ArrayList<Game> mGames;

    GameAdapter(ArrayList<Game> games){
        mGames = games;
    }



    @NonNull
    @Override
    public GameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.game_recyclerview, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GameAdapter.ViewHolder holder, int position) {
        holder.name.setText(mGames.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return mGames.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;

        ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.txtRv);
        }
    }
}
