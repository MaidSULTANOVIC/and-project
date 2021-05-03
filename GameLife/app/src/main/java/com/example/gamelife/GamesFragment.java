package com.example.gamelife;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GamesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GamesFragment extends Fragment {

    RecyclerView mGameList;
    GameAdapter mGameAdapter;

    public GamesFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static GamesFragment newInstance() {
        GamesFragment fragment = new GamesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_games, container, false);

        mGameList = rootView.findViewById(R.id.rv);
        mGameList.hasFixedSize();
        mGameList.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.separator_rv));

        mGameList.addItemDecoration(itemDecorator);

        ArrayList<Game> games = new ArrayList<>();
        games.add(new Game("League of legends"));
        games.add(new Game("PUBG"));
        games.add(new Game("OVERWATCH"));
        games.add(new Game("FORTNITE"));



        mGameAdapter = new GameAdapter(games);
        mGameList.setAdapter(mGameAdapter);

        // Inflate the layout for this fragment
        return rootView;
    }
}