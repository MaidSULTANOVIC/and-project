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

    private String accountId;

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

            accountId = playerInfo.getPlayerId();
            viewModel.searchForPlayerStats(playerInfo.getPlayerId());
            Log.d("Test", "Game data : " + playerInfo.getGameList().get(2).getId());
            viewModel.searchForMatchData(playerInfo.getGameList().get(2).getId());
        });

        viewModel.getSearchedPlayerStats().observe(getViewLifecycleOwner(), pubgPlayerStats -> {
            Log.d("test", pubgPlayerStats.getSquad().getKills() + " Kills");
        });

        viewModel.getSearchedMatchData().observe(getViewLifecycleOwner(),pubgMatchData -> {
            Log.d("test",pubgMatchData.getPlayerList().get(1).getAttributes().getStats().getName());
        });

        viewModel.getSearchedSeason().observe(getViewLifecycleOwner(), pubgSeason -> {
            Log.d("test",pubgSeason.getId() + " SEASON ID");
            viewModel.searchForRanked(accountId,pubgSeason.getId());

        });

        viewModel.getSearchedRanked().observe(getViewLifecycleOwner(), pubgRanked -> {
            Log.d("Test", pubgRanked.getSubTier() + " RANK");
        });

        viewModel.searchForAccount("DaMowangIili");
        viewModel.searchForSeason();





        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pubg, container, false);
    }
}