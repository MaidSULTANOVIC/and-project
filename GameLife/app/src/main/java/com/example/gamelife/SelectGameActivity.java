package com.example.gamelife;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;

public class SelectGameActivity extends AppCompatActivity {

    static final String NEW_GAME_NAME = "com.example.gamelife.newGame";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);


        ActionBar bar = getSupportActionBar();
        bar.setTitle("Add new game");
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#312051")));

        Button button = findViewById(R.id.button2);
        Button buttonPubg = findViewById(R.id.buttonSelectPubg);
        Button buttonLol = findViewById(R.id.buttonSelectLol);

        button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra(NEW_GAME_NAME, "Fornite");
            setResult(RESULT_OK, intent);
            finish();
        });


        buttonPubg.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra(NEW_GAME_NAME, "PUBG");
            setResult(RESULT_OK, intent);
            finish();
        });

        buttonLol.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra(NEW_GAME_NAME, "League of legends");
            setResult(RESULT_OK, intent);
            finish();
        });


    }
}