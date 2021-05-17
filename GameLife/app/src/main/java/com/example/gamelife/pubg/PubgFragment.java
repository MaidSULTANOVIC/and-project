package com.example.gamelife.pubg;

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

        rootView = inflater.inflate(R.layout.fragment_pubg, container, false);

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



        ActionBar bar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        bar.setTitle("PUBG");

        viewModel.getSearchedAccount().observe(getViewLifecycleOwner(),playerInfo -> {
            Log.d("test",playerInfo.getPlayerId());

            accountId = playerInfo.getPlayerId();
            viewModel.searchForPlayerStats(playerInfo.getPlayerId());
            Log.d("Test", "Game data : " + playerInfo.getGameList().get(2).getId());
            viewModel.searchForSeason();


            for(int i = 0;i<20;i++){
                Log.d("test","Match cherché "+ i);
                viewModel.searchForMatchData(playerInfo.getGameList().get(i).getId());
            }
        });

        viewModel.getSearchedPlayerStats().observe(getViewLifecycleOwner(), pubgPlayerStats -> {
            pubgStats =  pubgPlayerStats;

            //Squad
                txtMode.setText("Squad");
                txtDamage.setText(String.format("%.2f",pubgPlayerStats.getSquad().getDamageDealt()));
                txtKills.setText(pubgPlayerStats.getSquad().getKills()+"");
                txtBoosts.setText(pubgPlayerStats.getSquad().getBoosts()+"");
                txtWins.setText(pubgPlayerStats.getSquad().getKills()+"");

        });

        viewModel.getSearchedMatchData().observe(getViewLifecycleOwner(),pubgMatchData -> {


                    boolean inSearch = true;
                    for(int j = 0; j<pubgMatchData.getPlayerList().size() && inSearch;j++) {
                        Log.d("test","Joueur exploré :  "+ j);
                        try{
                            if (pubgMatchData.getPlayerList().get(j).getAttributes().getStats().getName().equals(userName)) {
                                PubgMatchPlayerStats player = pubgMatchData.getPlayerList().get(j).getAttributes().getStats();
                                inSearch = false;

                                mPubgGameAdapter.addGame(new PubgGame(pubgMatchData.getGameMode(), pubgMatchData.getMatchType(), pubgMatchData.getDuration(),player));
                                mPubgGameAdapter.notifyItemInserted(mPubgGameAdapter.getItemCount() - 1);

                            }
                        }catch(Exception e){
                            //continue
                        }
                    }


        });

        viewModel.getSearchedSeason().observe(getViewLifecycleOwner(), pubgSeason -> {
            viewModel.searchForRanked(accountId,pubgSeason.getId());

        });

        viewModel.getSearchedRanked().observe(getViewLifecycleOwner(), pubgRanked -> {
            txtPoint.setText(pubgRanked.getSquad().getCurrentRankPoint()+"");
            txtKda.setText(String.format("%.2f", pubgRanked.getSquad().getKda()));
            txtWinPerc.setText(String.format("%.2f",(pubgRanked.getSquad().getWinRatio()*100)));
            txtTopTen.setText(String.format("%.2f",(pubgRanked.getSquad().getTop10Ratio()*100)));
            txtRank.setText(pubgRanked.getTier()+pubgRanked.getSubTier());
        });



        //Declaration and init of the recyclerView and Game Adapter
        games = new ArrayList<PubgGame>();



        mGameList = rootView.findViewById(R.id.rvPubg);
        mGameList.hasFixedSize();
        mGameList.setLayoutManager(new LinearLayoutManager(getActivity()));

        //A separator is set for each item in the recyclerView
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.separator_rv));

        mGameList.addItemDecoration(itemDecorator);
        mPubgGameAdapter = new PubgGameAdapter(games);
        mGameList.setAdapter(mPubgGameAdapter);

        searchAccount();


        buttonRefresh.setOnClickListener(v -> {

            SharedPreferences prefs = this.getActivity().getSharedPreferences("MyPreferences", this.getActivity().MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("pubgName", txtUsername.getText().toString());
            editor.apply();

            txtUsername.clearFocus();
            searchAccount();

        });



        buttonSquad.setOnClickListener(v -> {

            refreshGameMode(0);
        });

        buttonDuo.setOnClickListener(v -> {

            refreshGameMode(2);
        });

        buttonSolo.setOnClickListener(v -> {

            refreshGameMode(1);
        });


        // Inflate the layout for this fragment
        return rootView;
    }


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

    private void searchAccount(){
        //I retrieve in local storage the username for this game
        SharedPreferences prefs = this.getActivity().getSharedPreferences("MyPreferences", 0);
        userName = prefs.getString("pubgName", "Enter your username");
        txtUsername.setText(userName);

        viewModel.searchForAccount(userName);
    }
}