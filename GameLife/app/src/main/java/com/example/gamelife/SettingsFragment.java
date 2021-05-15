package com.example.gamelife;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class SettingsFragment extends Fragment {

    private View rootView;

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



        btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent myIntent = new Intent(this.getContext(), LoginActivity.class);
            startActivity(myIntent);
            this.getActivity().finish();

        });



        // Inflate the layout for this fragment
        return rootView;
    }
}