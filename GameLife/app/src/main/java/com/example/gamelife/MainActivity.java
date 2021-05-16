package com.example.gamelife;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {


    NavController navController;
    BottomNavigationView bottomNavigationView;



    ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(FirebaseAuth.getInstance().getCurrentUser() == null){

            Intent myIntent = new Intent(this, LoginActivity.class);
            startActivity(myIntent);
            finish();

            //setContentView(R.layout.activity_login);
       }else{
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