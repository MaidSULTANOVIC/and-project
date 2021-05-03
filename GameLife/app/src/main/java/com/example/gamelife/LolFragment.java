package com.example.gamelife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LolFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LolFragment extends Fragment {



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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lol, container, false);
    }
}