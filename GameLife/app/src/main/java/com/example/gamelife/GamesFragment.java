package com.example.gamelife;

import android.os.Bundle;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GamesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GamesFragment extends Fragment implements GameAdapter.OnListItemClickListener{

    RecyclerView mGameList;
    GameAdapter mGameAdapter;

    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference dataGame = myRef.child("condition");
    TextView txt;
    Object temp;

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

        txt = rootView.findViewById(R.id.textView2);
        temp = this;


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("games").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        ArrayList<Game> games = new ArrayList<>();
                        Iterator it = document.getData().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry)it.next();
                            games.add(new Game((String) pair.getValue()));
                            Log.d("oui", "DocumentSnapshot data: " + games.size());
                        }



                        mGameList = rootView.findViewById(R.id.rv);
                        mGameList.hasFixedSize();
                        mGameList.setLayoutManager(new LinearLayoutManager(getActivity()));
                        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
                        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.separator_rv));

                        mGameList.addItemDecoration(itemDecorator);
                        mGameAdapter = new GameAdapter(games,GamesFragment.this::onListItemClick);
                        mGameList.setAdapter(mGameAdapter);

                    } else {
                        Log.d("oui", "No such document");
                    }
                } else {
                    Log.d("oui", "get failed with ", task.getException());
                }

            }
        });

        Map<String, Object> gamesData = new HashMap<>();
        gamesData.put("lol", "League of legends");
        gamesData.put("pubg", "P.U.B.G");
        gamesData.put("ow", "Overwatch");

        // Add a new document with a UID
       // db.collection("games").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(gamesData);







        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if(clickedItemIndex == 0){
            Navigation.findNavController(getView()).navigate(R.id.action_gamesFragment_to_lolFragment);
        }else if(clickedItemIndex == 1 ){
            Navigation.findNavController(getView()).navigate(R.id.action_gamesFragment_to_pubgFragment);

        }
        Toast.makeText(getContext(), "c parti " + clickedItemIndex, Toast.LENGTH_SHORT).show();

    }
}