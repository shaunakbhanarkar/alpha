package com.example.alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {


    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedPrefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        Button sign_in_button = findViewById(R.id.sign_in_button);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        Log.e("gso", "started");


        final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Log.e("google sign in client", "started");

        //shared preferences
        sharedPref = Objects.requireNonNull(this).getSharedPreferences("Shared Preferences", MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();

        sharedPrefEditor.putInt("Fragment",0);
        sharedPrefEditor.apply();


        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 9001);

                Log.e("google sign in intent", "started");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 9001) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Log.e("request code 9001", "entered");
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            if (task == null)   Log.e("task","null");
            else    Log.e("task","not null");
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {

        try {
            GoogleSignInAccount account = task.getResult();
            Log.e("try block","entered");
            if(account != null){
                finishAffinity();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        } catch (Exception e) {
            Log.e("exception","caught");
            e.printStackTrace();
        }


    }

    @Override
    protected void onStart(){
        super.onStart();

        //shared preferences
        sharedPref = Objects.requireNonNull(this).getSharedPreferences("Shared Preferences", MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();

        sharedPrefEditor.putInt("Fragment",0);
        sharedPrefEditor.apply();


        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null){

            finishAffinity();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }


    }
}
