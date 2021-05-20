package com.example.gamelife.ui.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gamelife.MainActivity;
import com.example.gamelife.R;
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

        //Change ActionBar title and color
        ActionBar bar = getSupportActionBar();
        bar.setTitle("Welcome to GameLife !");
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#312051")));

        context = this.getApplicationContext();

        mAuth = FirebaseAuth.getInstance();

        EditText mailTxt = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText passwordTxt = (EditText) findViewById(R.id.editTextTextPassword);

        txtError = (TextView) findViewById(R.id.txtError);

        Button registerButton = (Button) findViewById(R.id.buttonRegister);
        Button loginButton = (Button) findViewById(R.id.buttonLogin);


        //If the user clicks on loginButton
        loginButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAccount(mailTxt.getText().toString(),passwordTxt.getText().toString());
            }
        });

        //If the user clicks on Register Button
        registerButton.setOnClickListener(v -> {
            createAccount(mailTxt.getText().toString(), passwordTxt.getText().toString());
        });
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    //This function allows the user to create a new account
    public void createAccount(String email, String password){
        try{
            //It will call a function from FirebaseAuthentication
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
            //Show the error message in case the user triggered Exception
            txtError.setText("Username/Password error");
            txtError.setVisibility(VISIBLE);
        }

    }

    //This function allows the user to login with his credentials
    public void loginAccount(String email, String password){
        try{
            //With the username and password the user wrote, it will connect him
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //If he successfully logged in, it will change the view and show him the home page
                        Intent myIntent = new Intent(context, MainActivity.class);
                        startActivity(myIntent);
                        finish();

                    } else {
                        //Else it will show message error
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
