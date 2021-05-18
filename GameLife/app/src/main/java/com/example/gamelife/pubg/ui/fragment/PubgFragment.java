package com.example.gamelife.pubg.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gamelife.R;
import com.example.gamelife.pubg.PubgGameAdapter;
import com.example.gamelife.pubg.models.PubgGame;
import com.example.gamelife.pubg.models.gameData.PubgMatchPlayerStats;
import com.example.gamelife.pubg.models.playerStats.PubgPlayerStats;
import com.example.gamelife.pubg.ui.viewmodel.PubgViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PubgFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PubgFragment extends Fragment {

    private View rootView;
    private PubgViewModel viewModel;
    RecyclerView mGameList;
    PubgGameAdapter mPubgGameAdapter;

    private TextView txtUsername;

    private TextView txtPoint;
    private TextView txtRank;
    private TextView txtWinPerc;
    private TextView txtTopTen;
    private TextView txtKda;

    private TextView txtMode;
    private TextView txtDamage;
    private TextView txtKills;
    private TextView txtBoosts;
    private TextView txtWins;

    private Button buttonSquad;
    private Button buttonSolo;
    private Button buttonDuo;

    private PubgPlayerStats pubgStats;

    private String accountId;
    private String userName;
    private ArrayList<PubgGame> games;

    public PubgFragment() {
        // Required empty public constructor
    }


    public static PubgFragment newInstance() {
        PubgFragment fragment = new PubgFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Init ViewModel
        viewModel = new ViewModelProvider(this).get(PubgViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_pubg, container, false);

        //Init every components
        Button buttonRefresh = rootView.findViewById(R.id.buttonRefreshPubg);
        txtUsername = rootView.findViewById(R.id.editTextPubg);

        txtPoint = rootView.findViewById(R.id.txtRankPointPubg);
        txtRank = rootView.findViewById(R.id.txtRankPubg);
        txtWinPerc = rootView.findViewById(R.id.txtPercPubg);
        txtTopTen = rootView.findViewById(R.id.txtTop10Pubg);
        txtKda = rootView.findViewById(R.id.txtKdaPubg);

        txtMode = rootView.findViewById(R.id.txtModePubg);
        txtDamage = rootView.findViewById(R.id.txtDamagePubg);
        txtKills = rootView.findViewById(R.id.txtKillsPubg);
        txtBoosts = rootView.findViewById(R.id.txtBoostsPubg);
        txtWins = rootView.findViewById(R.id.txtWinsPubg);

        buttonSquad = rootView.findViewById(R.id.buttonSquadPubg);
        buttonSolo = rootView.findViewById(R.id.buttonSoloPubg);
        buttonDuo = rootView.findViewById(R.id.buttonDuoPubg);



        //init ActionBar and change its title
        ActionBar bar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        bar.setTitle("PUBG");

        // When the api call "searchAccount" is called, this function is triggered
        viewModel.getSearchedAccount().observe(getViewLifecycleOwner(),playerInfo -> {

            // Save accountId of the user
            accountId = playerInfo.getPlayerId();

            // Begin a new api request with the playerId
            viewModel.searchForPlayerStats(playerInfo.getPlayerId());

            // Begin a new api request to find actual season
            viewModel.searchForSeason();


            //If the player played more than 20 games in the last days, it will call searchForMatchData api call for the 20 last games
            if(playerInfo.getGameList().size() > 20){
                for(int i = 0;i<20;i++){
                    viewModel.searchForMatchData(playerInfo.getGameList().get(i).getId());
                }
            }
            //Else, it will call for every last games played
            else{
                for(int i = 0;i<playerInfo.getGameList().size();i++){
                    viewModel.searchForMatchData(playerInfo.getGameList().get(i).getId());
                }
            }

        });

        // Set squad stats and save all the other stats
        viewModel.getSearchedPlayerStats().observe(getViewLifecycleOwner(), pubgPlayerStats -> {
            pubgStats =  pubgPlayerStats;

            //Squad
                txtMode.setText("Squad");
                txtDamage.setText(String.format("%.2f",pubgPlayerStats.getSquad().getDamageDealt()));
                txtKills.setText(pubgPlayerStats.getSquad().getKills()+"");
                txtBoosts.setText(pubgPlayerStats.getSquad().getBoosts()+"");
                txtWins.setText(pubgPlayerStats.getSquad().getKills()+"");

        });

        // Get data for one match
        viewModel.getSearchedMatchData().observe(getViewLifecycleOwner(),pubgMatchData -> {


                    boolean inSearch = true;
                    // Search the current player data
                    for(int j = 0; j<pubgMatchData.getPlayerList().size() && inSearch;j++) {

                        try{
                            if (pubgMatchData.getPlayerList().get(j).getAttributes().getStats().getName().equals(userName)) {
                                PubgMatchPlayerStats player = pubgMatchData.getPlayerList().get(j).getAttributes().getStats();
                                inSearch = false;

                                // Add game in gameAdapter
                                mPubgGameAdapter.addGame(new PubgGame(pubgMatchData.getGameMode(), pubgMatchData.getMatchType(), pubgMatchData.getDuration(),player));
                                mPubgGameAdapter.notifyItemInserted(mPubgGameAdapter.getItemCount() - 1);

                            }
                        }catch(Exception e){
                            //continue
                        }
                    }


        });


        // Search Season, when this function is triggered, it wil lcall for searchForRanked stats with the current season
        viewModel.getSearchedSeason().observe(getViewLifecycleOwner(), pubgSeason -> {
            viewModel.searchForRanked(accountId,pubgSeason.getId());

        });

        // Update UI with ranked stats
        viewModel.getSearchedRanked().observe(getViewLifecycleOwner(), pubgRanked -> {
            txtPoint.setText(pubgRanked.getSquad().getCurrentRankPoint()+"");
            txtKda.setText(String.format("%.2f", pubgRanked.getSquad().getKda()));
            txtWinPerc.setText(String.format("%.2f",(pubgRanked.getSquad().getWinRatio()*100)));
            txtTopTen.setText(String.format("%.2f",(pubgRanked.getSquad().getTop10Ratio()*100)));
            txtRank.setText(pubgRanked.getTier()+pubgRanked.getSubTier());
        });



        //Init of the recyclerView and Game Adapter
        games = new ArrayList<PubgGame>();


        //Init of the recyclerView
        mGameList = rootView.findViewById(R.id.rvPubg);
        mGameList.hasFixedSize();
        mGameList.setLayoutManager(new LinearLayoutManager(getActivity()));

        //A separator is set for each item in the recyclerView
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.separator_rv));

        mGameList.addItemDecoration(itemDecorator);

        //Init Adapter and set it to the recyclerView
        mPubgGameAdapter = new PubgGameAdapter(games);
        mGameList.setAdapter(mPubgGameAdapter);

        // Init call to begin api fetch
        searchAccount();

        // Function called when the refresh button is clicked
        buttonRefresh.setOnClickListener(v -> {

            //Take value from editText and put it in SharedPreferences
            SharedPreferences prefs = this.getActivity().getSharedPreferences("MyPreferences", this.getActivity().MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("pubgName", txtUsername.getText().toString());
            editor.apply();

            //Clear focus and call searchAccount api function
            txtUsername.clearFocus();
            searchAccount();

        });

        // Show Squad stats
        buttonSquad.setOnClickListener(v -> {

            refreshGameMode(0);
        });

        // Show Duo stats
        buttonDuo.setOnClickListener(v -> {

            refreshGameMode(2);
        });


        // Show Solo stats
        buttonSolo.setOnClickListener(v -> {

            refreshGameMode(1);
        });


        // Inflate the layout for this fragment
        return rootView;
    }


    // Change stats that is displayed in relation with what user clicked
    private void refreshGameMode(int index){
        //Squad
        if(index == 0){
            txtMode.setText("Squad");
            txtDamage.setText(String.format("%.2f",pubgStats.getSquad().getDamageDealt()));
            txtKills.setText(pubgStats.getSquad().getKills()+"");
            txtBoosts.setText(pubgStats.getSquad().getBoosts()+"");
            txtWins.setText(pubgStats.getSquad().getKills()+"");

        }//Solo
        else if(index == 1){
            txtMode.setText("Solo");
            txtDamage.setText(String.format("%.2f", pubgStats.getSolo().getDamageDealt()));
            txtKills.setText(pubgStats.getSolo().getKills()+"");
            txtBoosts.setText(pubgStats.getSolo().getBoosts()+"");
            txtWins.setText(pubgStats.getSolo().getWins()+"");

        }//Duo
        else{
            txtMode.setText("Duo");
            txtDamage.setText(String.format("%.2f",pubgStats.getDuo().getDamageDealt()));
            txtKills.setText(pubgStats.getDuo().getKills()+"");
            txtBoosts.setText(pubgStats.getDuo().getBoosts()+"");
            txtWins.setText(pubgStats.getDuo().getWins()+"");

        }
    }

    //Function to search a new account
    private void searchAccount(){
        //It retrieves in local storage the username for this game
        SharedPreferences prefs = this.getActivity().getSharedPreferences("MyPreferences", 0);
        userName = prefs.getString("pubgName", "Enter your username");
        txtUsername.setText(userName);

        //And call api function that will retrieve data
        viewModel.searchForAccount(userName);
    }
}