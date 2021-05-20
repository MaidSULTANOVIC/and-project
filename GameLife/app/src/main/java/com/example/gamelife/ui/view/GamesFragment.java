package com.example.gamelife.ui.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gamelife.Game;
import com.example.gamelife.GameAdapter;
import com.example.gamelife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * This fragment contains the homepage, so it will display the user's selected games
 */
public class GamesFragment extends Fragment implements GameAdapter.OnListItemClickListener {

    private RecyclerView mGameList;
    private GameAdapter mGameAdapter;
    private View rootView;

    //Init database reference
    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    private TextView txt;
    private Object temp;
    private ArrayList<Game> games;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

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

        rootView = inflater.inflate(R.layout.fragment_games, container, false);
        games = new ArrayList<>();

        //Init actionBar and change title
        ActionBar bar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        bar.setTitle("Games");

        //Init components of the fragment
        FloatingActionButton fab = rootView.findViewById(R.id.floating_action_button);
        txt = rootView.findViewById(R.id.textView2);
        temp = this;

        mGameAdapter = new GameAdapter(games,this::onListItemClick);

        //Create a new document for the user collection (if it doesn't exist yet)
        db.collection("games").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(new HashMap<String,Object>(),SetOptions.merge());

        // Retrieves user's games
        db.collection("games").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        // For each game that the user has, it will add it to a list of games to be displayed in recyclerView
                        Iterator it = document.getData().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry)it.next();
                            games.add(new Game((String) pair.getValue()));
                            Log.d("oui", "DocumentSnapshot data: " + games.size());
                        }


                        // Then, init recyclerView and set adapter in it.
                        mGameList = rootView.findViewById(R.id.rv);
                        mGameList.hasFixedSize();
                        mGameList.setLayoutManager(new LinearLayoutManager(getActivity()));

                        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
                        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.separator_rv));

                        mGameList.addItemDecoration(itemDecorator);
                        mGameAdapter = new GameAdapter(games,GamesFragment.this::onListItemClick);
                        mGameList.setAdapter(mGameAdapter);


                    } else {
                        Log.d("Firebase", "No such document");
                    }
                } else {
                    Log.d("Firebase", "get failed with ", task.getException());
                }

            }
        });


        // When the user clicks on the floating action button, it will change activity waiting for a result
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectGameActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == -1) {

                //When the result come back, the game that the user selected wll be inserted in recycler view
                String strGame = data.getStringExtra(SelectGameActivity.NEW_GAME_NAME);
                mGameAdapter.addGame(new Game(strGame));
                mGameAdapter.notifyItemInserted(mGameAdapter.getItemCount()-1);

                Map<String, Object> newGame = new HashMap<>();
                newGame.put(strGame.substring(0,2),strGame);

                //Then, this game will be added to the database of the user profile
                db.collection("games").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(
                        newGame, SetOptions.merge()
                );

                // To finish, a toast is displayed to notify the user that a game has been added
                Toast.makeText(getActivity(), "Game added", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onListItemClick(int clickedItemIndex) {

        //When the user click on an item of the recyclerView, it will change the view in relation of the item clicked
        String mGameName = mGameAdapter.getGame(clickedItemIndex).getName();

        // if League of legends is clicked, it will show him lol fragment
        if(mGameName.equals("League of legends")){
            Navigation.findNavController(getView()).navigate(R.id.action_gamesFragment_to_lolFragment);

        }else if(mGameName.equals("PUBG")){ // if PUBG is clicked, it will show him pubg fragment
            Navigation.findNavController(getView()).navigate(R.id.action_gamesFragment_to_pubgFragment);
        }
    }

}