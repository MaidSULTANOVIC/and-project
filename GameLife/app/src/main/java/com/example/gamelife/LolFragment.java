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
 * Use the {@link LolFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LolFragment extends Fragment {

    RecyclerView mGameList;
    LolGameAdapter mLolGameAdapter;
    View rootView;

    public LolFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static LolFragment newInstance() {
        LolFragment fragment = new LolFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_lol, container, false);

        ArrayList<LolGame> games = new ArrayList<LolGame>();
        games.add(new LolGame("Ranked","Kassadin","Defeat","22/8/12","3.12 KDA"));
        games.add(new LolGame("Ranked","Zed","Victory","12/2/6","6.12 KDA"));

        mGameList = rootView.findViewById(R.id.rvLol);
        mGameList.hasFixedSize();
        mGameList.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.separator_rv));

        mGameList.addItemDecoration(itemDecorator);
        mLolGameAdapter = new LolGameAdapter(games);
        mGameList.setAdapter(mLolGameAdapter);


        // Inflate the layout for this fragment
        return rootView;
    }
}