package com.example.gamelife;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class SettingsFragment extends Fragment {

    private View rootView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public SettingsFragment() {
        // Required empty public constructor
    }


    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        Button btnLogout = rootView.findViewById(R.id.buttonLogout);
        Button btnLol = rootView.findViewById(R.id.buttonRmLol);
        Button btnPubg = rootView.findViewById(R.id.buttonRmPubg);

        ActionBar bar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        bar.setTitle("Settings");


        //When logout button is clicked, the user is sign out and the login page is displayed
        btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent myIntent = new Intent(this.getContext(), LoginActivity.class);
            startActivity(myIntent);
            this.getActivity().finish();

        });


        btnLol.setOnClickListener(v -> {
            db.collection("games").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    DocumentSnapshot doc = task.getResult();

                    //if the user's account contains the game league of legends
                    if(doc.contains("Le")){

                        //Delete the field for League of legends
                        db.collection("games").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).update(new HashMap<String, Object>() {
                            {
                                put("Le", FieldValue.delete());
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Snackbar snackbar = Snackbar.make(rootView.findViewById(R.id.positionSnackbar), "Game deleted", Snackbar.LENGTH_SHORT);
                                snackbar.setAction("Undo", new View.OnClickListener(){
                                    @Override
                                    public void onClick(View v) {
                                        db.collection("games").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).update(new HashMap<String, Object>() {
                                            {
                                                put("Le", "League of legends");
                                            }});
                                    }
                                })
                               .show();
                            }
                        });

                    }else{
                        Toast.makeText(getActivity(),"You don't have this game", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        });


        btnPubg.setOnClickListener(v -> {
            db.collection("games").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    DocumentSnapshot doc = task.getResult();

                    //if the user's account contains the game PUBG
                    if(doc.contains("PU")){

                        //Delete the field for PUBG
                        db.collection("games").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).update(new HashMap<String, Object>() {
                            {
                                put("PU", FieldValue.delete());
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Snackbar.make(rootView.findViewById(R.id.positionSnackbar), "Game deleted", Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener(){
                                    @Override
                                    public void onClick(View v) {
                                        db.collection("games").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).update(new HashMap<String, Object>() {
                                            {
                                                put("PU", "PUBG");
                                            }});
                                    }
                                })
                                        .show();
                            }
                        });

                    }else{
                        Toast.makeText(getActivity(),"You don't have this game", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        });

        // Inflate the layout for this fragment
        return rootView;
    }
}