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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {


    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedPrefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.i("Login Activity","started");


        getSupportActionBar().hide();
        Log.d("Login Activity","action bar hidden");


        Button sign_in_button = findViewById(R.id.sign_in_button);
        Log.d("Login Activity","sign in button initialised");


        sign_in_button.setTextColor(getResources().getColor(R.color.black));
        Log.d("Login Activity","sign in button text color set to black");

        Log.d("Login Activity","building google sign in options...");
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        Log.d("Login Activity","creating google sign in client...");
        final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //shared preferences
        sharedPref = Objects.requireNonNull(this).getSharedPreferences("Shared Preferences", MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
        Log.d("Login Activity","shared preferences opened for editing");

        sharedPrefEditor.putInt("Fragment",0);
        Log.d("Account Fragment","shared preferences fragment value changed to 0");

        sharedPrefEditor.apply();
        Log.d("Account Fragment","shared preferences changes applied");


        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Login Activity","sign in button clicked, initiating sign in...");

                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 9001);

                Log.d("Login Activity", "google sign in intent started");
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
            Log.d("Login Activity", "request code 9001 entered");

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            Log.d("Login Activity","google sign in task created");

            if (task == null){
                Log.e("Login Activity","task is null");
            }

            Log.d("Login Activity","handling the sign in result...");
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {

        try {

            Log.d("Login Activity","getting account...");
            GoogleSignInAccount account = task.getResult();

            if(account != null){

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                Log.d("Login Activity","created database reference");

                Log.d("Login Activity","creating node for the user...");

                databaseReference.child("Users").child(account.getId()).child("Name").setValue(account.getDisplayName());
                Log.d("Login Activity","created node for name");

                databaseReference.child("Users").child(account.getId()).child("Email").setValue(account.getEmail());
                Log.d("Login Activity","created node for id");

                finishAffinity();
                Log.d("Login Activity","affinity finished");

                Log.d("Login Activity","starting MainActivity...");
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);

                Log.i("Login Activity","login successful");
            }
            else
            {
                Log.e("Login Activity","account is null");
            }
        } catch (Exception e) {
            Log.e("Login Activity","exception caught during handle sign in result");
            e.printStackTrace();
        }


    }

    @Override
    protected void onStart(){
        super.onStart();

        //shared preferences
        sharedPref = Objects.requireNonNull(this).getSharedPreferences("Shared Preferences", MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
        Log.d("Login Activity","shared preferences opened for editing");

        sharedPrefEditor.putInt("Fragment",0);
        Log.d("Account Fragment","shared preferences fragment value changed to 0");

        sharedPrefEditor.apply();
        Log.d("Account Fragment","shared preferences changes applied");


        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null){

            finishAffinity();
            Log.d("Login Activity","affinity finished");

            Log.d("Login Activity","starting MainActivity...");
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Log.e("Login Activity","account is null");
        }

    }
}
