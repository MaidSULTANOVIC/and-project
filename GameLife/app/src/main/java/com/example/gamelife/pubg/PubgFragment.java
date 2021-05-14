package com.example.gamelife.pubg;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gamelife.R;
import com.example.gamelife.leagueoflegends.ui.viewmodel.LolSummonerViewModel;
import com.example.gamelife.pubg.ui.viewmodel.PubgViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PubgFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PubgFragment extends Fragment {

    private PubgViewModel viewModel;

    public PubgFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PubgFragment newInstance() {
        PubgFragment fragment = new PubgFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(PubgViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        viewModel.getSearchedAccount().observe(getViewLifecycleOwner(),playerInfo -> {
            Log.d("test",playerInfo.getPlayerId());
            viewModel.searchForPlayerStats(playerInfo.getPlayerId());
        });

        viewModel.getSearchedPlayerStats().observe(getViewLifecycleOwner(), pubgPlayerStats -> {
            Log.d("test", pubgPlayerStats.getSquad().getKills() + " ");
        });

        viewModel.searchForAccount("DaMowangIili");


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pubg, container, false);
    }
}