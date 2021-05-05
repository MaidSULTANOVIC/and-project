package com.example.gamelife.leagueoflegends;

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
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.gamelife.Game;
import com.example.gamelife.R;
import com.example.gamelife.ServiceGenerator;


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
        viewModel = new ViewModelProvider(this).get(LolSummonerViewModel.class);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_lol, container, false);

        txtTier = rootView.findViewById(R.id.txtRank);
        txtLP = rootView.findViewById(R.id.txtLp);
        txtPercentage = rootView.findViewById(R.id.txtRatio);

        viewModel.getSearchedLolAccount().observe(getViewLifecycleOwner(), lolAccount -> {
            id = lolAccount.getmId();
            accountId = lolAccount.getmAccountId();
            searchSummoner();
            searchMatchList();
        });

        viewModel.getSearchedMatchDetail().observe(getViewLifecycleOwner(),matchDetail -> {
            Log.i("ARRIVER",matchDetail.getmParticipantIdentities().get(0).getPlayer().getSummonerName()+"");
            int playerIndex = 0;
            boolean inSearch = true;


            for(int j = 0; j<matchDetail.getmParticipantIdentities().size() && inSearch;j++){
                if(matchDetail.getmParticipantIdentities().get(j).getPlayer().getSummonerName().equals("Ermelinda83F")){
                    playerIndex= matchDetail.getmParticipantIdentities().get(j).getParticipantId()-1;
                    inSearch = false;
                }
            }
            Participant player = matchDetail.getmParticipants().get(playerIndex);

            String gameMode = matchDetail.getmGameMode();
            int champId = player.getChampionId();
            String win = "Defeat";
            if(player.getStats().isWin()){
                win = "Victory";
            }
            String kda = player.getStats().getKills()+"/"+player.getStats().getDeaths()+"/"+player.getStats().getAssists();
            double ratio;
            String kdaRatio;
            try {
                ratio = (double)((double)player.getStats().getKills()+(double)player.getStats().getAssists())/(double)player.getStats().getDeaths();
                ratio = Double.parseDouble(new DecimalFormat("##.##").format(ratio));
                kdaRatio = ratio+" KDA";
            }catch(NumberFormatException e){
                kdaRatio = "Perfect KDA";
            }


            mLolGameAdapter.addGame(new LolGame(gameMode,champId+"",win,kda,kdaRatio));
            mLolGameAdapter.notifyItemInserted(mLolGameAdapter.getItemCount()-1);
        });

        viewModel.getSearchedMatchList().observe(getViewLifecycleOwner(),matchList -> {
            Log.i("ARRIVER","ON A REUSSI A AVOIR QQCHOSE  "+ matchList.getmMatches().get(0).getGameId());
            for(int i = 0;i<matchList.getmMatches().size() - 80;i++){
                searchMatchDetail(matchList.getmMatches().get(i).getGameId());
            }
        });

        viewModel.getSearchedSummoner().observe(getViewLifecycleOwner(), summoner -> {
            txtTier.setText(summoner.getmTier()+ " " +summoner.getmRank());
            txtLP.setText(summoner.getmLeaguePoints()+" LP");

            int win = summoner.getmWins();
            int losse = summoner.getmLosses();
            int ratio = (int)(((double)win/(double)(win+losse))*100);

            txtPercentage.setText(ratio+"%");

            //TODO Change picture with rank
        });

        searchAccount();


        ArrayList<LolGame> games = new ArrayList<LolGame>();

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

    public void searchAccount(){
        viewModel.searchForLolAccount("Ermelinda83F");
    }

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