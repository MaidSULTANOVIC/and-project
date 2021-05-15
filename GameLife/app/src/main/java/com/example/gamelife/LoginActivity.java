package com.example.gamelife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.view.View.VISIBLE;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView txtError;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this.getApplicationContext();

        mAuth = FirebaseAuth.getInstance();

        EditText mailTxt = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText passwordTxt = (EditText) findViewById(R.id.editTextTextPassword);

        txtError = (TextView) findViewById(R.id.txtError);

        Button registerButton = (Button) findViewById(R.id.buttonRegister);
        Button loginButton = (Button) findViewById(R.id.buttonLogin);


        loginButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAccount(mailTxt.getText().toString(),passwordTxt.getText().toString());
            }
        });

        registerButton.setOnClickListener(v -> {
            createAccount(mailTxt.getText().toString(), passwordTxt.getText().toString());
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {


        }

    }

    public void createAccount(String email, String password){
        try{
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Intent myIntent = new Intent(context, MainActivity.class);
                                startActivity(myIntent);
                                finish();

                            } else {
                                // If sign in fails, display a message to the user.
                                txtError.setText(task.getException().getMessage());
                                txtError.setVisibility(VISIBLE);
                            }

                            // ...
                        }
                    });
        }catch (Exception e){
            txtError.setText("Username/Password error");
            txtError.setVisibility(VISIBLE);
        }

    }

    public void loginAccount(String email, String password){
        try{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent myIntent = new Intent(context, MainActivity.class);
                        startActivity(myIntent);
                        finish();

                    } else {
                        txtError.setText(task.getException().getMessage());
                        txtError.setVisibility(VISIBLE);
                    }

                }

            });
        }catch (Exception e){
            txtError.setText("Username/Password error");
            txtError.setVisibility(VISIBLE);
        }

    }
}
