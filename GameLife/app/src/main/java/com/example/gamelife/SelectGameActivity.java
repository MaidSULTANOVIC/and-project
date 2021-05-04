package com.example.gamelife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SelectGameActivity extends AppCompatActivity {

    static final String NEW_GAME_NAME = "com.example.gamelife.newGame";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);


        Button button = findViewById(R.id.button2);
        button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra(NEW_GAME_NAME, "Fornite");
            setResult(RESULT_OK, intent);
            finish();
        });


    }
}