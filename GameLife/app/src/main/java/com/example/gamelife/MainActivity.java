package com.example.gamelife;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.gamelife.ui.view.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {


    private NavController navController;
    private BottomNavigationView bottomNavigationView;
    ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // I check if the user is already connected, if not, I show him login activity
        if(FirebaseAuth.getInstance().getCurrentUser() == null){

            Intent myIntent = new Intent(this, LoginActivity.class);
            startActivity(myIntent);
            finish();

       }else{ // Else, he can see his games
            setContentView(R.layout.activity_main);

            bar = getSupportActionBar();
            bar.setTitle("Games");
            bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#312051")));

            bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
            navController = Navigation.findNavController(this, R.id.fragment_container);

            NavigationUI.setupWithNavController(bottomNavigationView,navController);
        }




    }

    @Override
    protected void onStart() {
        super.onStart();


    }

}