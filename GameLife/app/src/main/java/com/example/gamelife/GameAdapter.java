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
    final private OnListItemClickListener mOnListItemClickListener;

    GameAdapter(ArrayList<Game> games, OnListItemClickListener listener){
        mGames = games;
        mOnListItemClickListener = listener;

    }

    public void setList(ArrayList<Game> game){
        mGames = game;
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

    public Game getGame(int position){
        return mGames.get(position);
    }

    public void addGame(Game game){
        mGames.add(game);
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;

        ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.txtRv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }

    }
}
