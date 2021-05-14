package com.example.gamelife.leagueoflegends.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.gamelife.R;
import com.example.gamelife.leagueoflegends.Champions;
import com.example.gamelife.leagueoflegends.LolGameAdapter;
import com.example.gamelife.leagueoflegends.models.LolGame;
import com.example.gamelife.leagueoflegends.models.Participant;
import com.example.gamelife.leagueoflegends.ui.viewmodel.LolSummonerViewModel;


import java.text.DecimalFormat;
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
    private LolSummonerViewModel viewModel;
    private String id;
    private String accountId;

    private TextView txtTier;
    private TextView txtLP;
    private TextView txtPercentage;
    private ImageView imageRank;

    private Button buttonRefresh;
    private EditText userNameTxt;
    private String userName;

    public LolFragment() {
        // Required empty public constructor
    }

    // Create a new instance for this object
    public static LolFragment newInstance() {
        LolFragment fragment = new LolFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init viewModel
        viewModel = new ViewModelProvider(this).get(LolSummonerViewModel.class);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_lol, container, false);

        //Init the different view and component of the page
        txtTier = rootView.findViewById(R.id.txtRank);
        txtLP = rootView.findViewById(R.id.txtLp);
        txtPercentage = rootView.findViewById(R.id.txtRatio);
        imageRank = rootView.findViewById(R.id.imageRank);
        buttonRefresh = rootView.findViewById(R.id.buttonRefreshLol);
        userNameTxt = rootView.findViewById(R.id.editTextTextPersonName2);


        //An observer that will start the search for a lolAccount when triggered
        viewModel.getSearchedLolAccount().observe(getViewLifecycleOwner(), lolAccount -> {
            id = lolAccount.getmId();
            accountId = lolAccount.getmAccountId();

            //Then, the summoner data is searched
            searchSummoner();

            //And the user matchlist is retrieved
            searchMatchList();
        });

        //This function will search for the data for a specific game
        viewModel.getSearchedMatchDetail().observe(getViewLifecycleOwner(),matchDetail -> {

            int playerIndex = 0;
            boolean inSearch = true;

            //For every participants in the game, I save the index for the player that has the same userName that the user
            for(int j = 0; j<matchDetail.getmParticipantIdentities().size() && inSearch;j++){
                if(matchDetail.getmParticipantIdentities().get(j).getPlayer().getSummonerName().equals(userName)){
                    playerIndex= matchDetail.getmParticipantIdentities().get(j).getParticipantId()-1;
                    inSearch = false;
                }
            }

            //Then, the user's player is saved
            Participant player = matchDetail.getmParticipants().get(playerIndex);

            //Needed data is retrieved
            String gameMode = matchDetail.getmGameMode();
            int champId = player.getChampionId();

            //If the user won the game, the condition will be Victory, else it will stay Defeat
            String win = "Defeat";
            if(player.getStats().isWin()){
                win = "Victory";
            }

            //The data needed to be displayed is stored in variables
            String kda = player.getStats().getKills()+"/"+player.getStats().getDeaths()+"/"+player.getStats().getAssists();
            double ratio;
            String kdaRatio;

            //The player KDA is calculated, if the user didn't die, he has 0 death, but in order to calculate the KDA, I need to divide by death, so if a division by zero happens
            //it will catch an exception and "Perfect KDA" will be shown
            try {
                ratio = (double)((double)player.getStats().getKills()+(double)player.getStats().getAssists())/(double)player.getStats().getDeaths();
                ratio = Double.parseDouble(new DecimalFormat("##.##").format(ratio));
                kdaRatio = ratio+" KDA";
            }catch(NumberFormatException e){
                kdaRatio = "Perfect KDA";
            }

            //Here, the specific image/drawable is selected according to the champion the player played
            int drawableId = getActivity().getResources().getIdentifier(Champions.getChampion(champId).toLowerCase()+"_0","drawable",getActivity().getPackageName());

            //The game is added in the list of games in gameAdapter and the adapter is notified of the insert
            mLolGameAdapter.addGame(new LolGame(gameMode,drawableId,win,kda,kdaRatio));
            mLolGameAdapter.notifyItemInserted(mLolGameAdapter.getItemCount()-1);
        });

        //This function will get the 20 last games of the user and call searchedMatchDetail for each of them
        viewModel.getSearchedMatchList().observe(getViewLifecycleOwner(),matchList -> {
            //Instead of retrieved all the last games available, I only retrieved the 20 last games
            for(int i = 0;i<matchList.getmMatches().size() - 80;i++){
                searchMatchDetail(matchList.getmMatches().get(i).getGameId());
            }
        });

        // This function will retrieve data of the user summoner stats
        viewModel.getSearchedSummoner().observe(getViewLifecycleOwner(), summoner -> {
            //It will display the data in the layout
            txtTier.setText(summoner.getmTier()+ " " +summoner.getmRank());
            txtLP.setText(summoner.getmLeaguePoints()+" LP");

            int win = summoner.getmWins();
            int losse = summoner.getmLosses();
            int ratio = (int)(((double)win/(double)(win+losse))*100);

            txtPercentage.setText(ratio+"%");

            //The picture of the rank will change according to user's rank
            int drawableId = getActivity().getResources().getIdentifier("emblem_"+summoner.getmTier().toLowerCase(),"drawable",getActivity().getPackageName());
            imageRank.setImageResource(drawableId);
        });

        // SearchAccount start when the user is on the lol page
        searchAccount();


        //Declaration and init of the recyclerView and Game Adapter
        ArrayList<LolGame> games = new ArrayList<LolGame>();

        mGameList = rootView.findViewById(R.id.rvLol);
        mGameList.hasFixedSize();
        mGameList.setLayoutManager(new LinearLayoutManager(getActivity()));

        //A separator is set for each item in the recyclerView
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.separator_rv));

        mGameList.addItemDecoration(itemDecorator);
        mLolGameAdapter = new LolGameAdapter(games);
        mGameList.setAdapter(mLolGameAdapter);


        //When the user clicks on the refresh button, I store the new userName in local storage so it can be used later
        buttonRefresh.setOnClickListener(v -> {
            SharedPreferences prefs = this.getActivity().getSharedPreferences("MyPreferences", this.getActivity().MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("lolName", userNameTxt.getText().toString());
            editor.apply();

            //The recyclerview is cleared
            mLolGameAdapter.clearGames();

            //And asynchronous function is called again to retrieve the data for the new username
            searchAccount();

            userNameTxt.clearFocus();

        });


        // Inflate the layout for this fragment
        return rootView;
    }

    public void searchAccount(){
        //I retrieve in local storage the username for this game
        SharedPreferences prefs = this.getActivity().getSharedPreferences("MyPreferences", 0);
        userName = prefs.getString("lolName", "Enter your username");
        userNameTxt.setText(userName);

        //Async call to retrieve data for this account
        viewModel.searchForLolAccount(userName);
    }

    //All the different call for async functions that will retrieve data via League of legends's API

    public void searchSummoner(){
        viewModel.searchForSummoner(id);
    }

    public void searchMatchList(){
        viewModel.searchForMatchList(accountId);
    }

    public void searchMatchDetail(long matchId){
        viewModel.searchForMatchDetail(matchId);
    }



}