package com.example.alpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

public class LettersOfRecommendationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letters_of_recommendation);


        //shared preferences

        final SharedPreferences sharedPref = Objects.requireNonNull(getSharedPreferences("Shared Preferences", MODE_PRIVATE));
        final int theme = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);

        //customise action bar

        ActionBar action_bar = getSupportActionBar();

        action_bar.setDisplayShowTitleEnabled(false);
        action_bar.setDisplayShowCustomEnabled(true);

        action_bar.setCustomView(R.layout.action_bar_with_buttons);

        TextView action_bar_title = action_bar.getCustomView().findViewById(R.id.action_bar_title);
        ImageButton back_button = action_bar.getCustomView().findViewById(R.id.back_button);
        ImageButton help_button = action_bar.getCustomView().findViewById(R.id.help_button);

        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


        action_bar_title.setText("Letters of Recommendation");

        int[][] states = new int[][]{


                new int[]{android.R.attr.state_enabled} // enabled
        };

        int[] colors= new int[]{

                getResources().getColor(R.color.colorPrimary)

        };



        if (theme == AppCompatDelegate.MODE_NIGHT_NO){

            Log.e("entered","light theme");

            action_bar_title.setTextColor(getResources().getColor(R.color.colorPrimary));
            colors= new int[]{

                    getResources().getColor(R.color.colorPrimary)

            };

            back_button.setBackgroundColor(getResources().getColor(R.color.white));
            help_button.setBackgroundColor(getResources().getColor(R.color.white));


            //change status bar colour
            window.setStatusBarColor(getResources().getColor(R.color.white));
            //change action bar colour
            action_bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
            // set status bar contrast
            View decor = window.getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);




        }
        else if (theme == AppCompatDelegate.MODE_NIGHT_YES){

            Log.e("entered","dark theme");


            action_bar_title.setTextColor(getResources().getColor(R.color.darkHighlight));
            colors= new int[]{

                    getResources().getColor(R.color.darkHighlight)

            };

            back_button.setBackgroundColor(getResources().getColor(R.color.darkBackground));
            help_button.setBackgroundColor(getResources().getColor(R.color.darkBackground));

            //change status bar colour
            window.setStatusBarColor(getResources().getColor(R.color.darkBackground));
            //change action bar colour
            action_bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkBackground)));
            // set status bar contrast
            View decor = window.getDecorView();
            LinearLayout background_layout = decor.findViewById(R.id.background_layout);
            background_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));




        }
        else {

            Log.e("entered","battery saver theme");


            PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
            if (powerManager.isPowerSaveMode()) {

                action_bar_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                colors= new int[]{

                        getResources().getColor(R.color.darkHighlight)

                };

                back_button.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                help_button.setBackgroundColor(getResources().getColor(R.color.darkBackground));


                //change status bar colour
                window.setStatusBarColor(getResources().getColor(R.color.darkBackground));
                //change action bar colour
                action_bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkBackground)));
                // set status bar contrast
                View decor = window.getDecorView();
                LinearLayout background_layout = decor.findViewById(R.id.background_layout);
                background_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));




            } else {

                action_bar_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                colors= new int[]{

                        getResources().getColor(R.color.colorPrimary)

                };

                back_button.setBackgroundColor(getResources().getColor(R.color.white));
                help_button.setBackgroundColor(getResources().getColor(R.color.white));

                //change status bar colour
                window.setStatusBarColor(getResources().getColor(R.color.white));
                //change action bar colour
                action_bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
                // set status bar contrast
                View decor = window.getDecorView();
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);




            }

        }


        ColorStateList colorStateList = new ColorStateList(states, colors);

        back_button.setImageTintList(colorStateList);
        help_button.setImageTintList(colorStateList);




        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        help_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog alertDialog = null;

                LinearLayout alert_dialog_layout = new LinearLayout(v.getContext());
                alert_dialog_layout.setOrientation(LinearLayout.VERTICAL);
                alert_dialog_layout.setPadding(20, 20, 20, 20);
                alert_dialog_layout.setGravity(Gravity.CENTER);

                TextView alert_dialog_title = new TextView(alert_dialog_layout.getContext());
                alert_dialog_title.setText("Letters of Recommendation - Help");
                alert_dialog_title.setTextSize(22);
                alert_dialog_title.setPadding(40, 20, 20, 20);
                alert_dialog_title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                alert_dialog_title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                Typeface nexa_bold = getResources().getFont(R.font.nexa_bold);
                Typeface nexa_light = getResources().getFont(R.font.nexa_light);

                alert_dialog_title.setTypeface(nexa_bold);


                TextView textView = new TextView(alert_dialog_layout.getContext());

                textView.setTypeface(nexa_light);
                textView.setTextSize(17);
                textView.setText("This page lets you view and list your Letters of Recommendation for your application. You can list the name and designation of upto 5 recommenders, which is usually good to go. This gives you an idea about the recommendations you currently have, and what you need to do next.\n\nGood luck!");
                textView.setPadding(30,30,30,30);
                textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


                TextView button = new TextView(alert_dialog_layout.getContext());
                button.setPadding(20,20,20,20);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(10,10,10,10);
                button.setLayoutParams(layoutParams);
                button.setTextSize(15);
                button.setTypeface(nexa_bold);
                button.setText("OKAY");



                if (theme == AppCompatDelegate.MODE_NIGHT_NO) {
                    alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.white));
                    alert_dialog_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                    textView.setTextColor(getResources().getColor(R.color.black));
                    button.setTextColor(getResources().getColor(R.color.colorPrimary));






                } else if (theme == AppCompatDelegate.MODE_NIGHT_YES) {
                    alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                    alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                    textView.setTextColor(getResources().getColor(R.color.white));
                    button.setTextColor(getResources().getColor(R.color.darkHighlight));





                } else {
                    PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
                    if (powerManager.isPowerSaveMode()) {
                        alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                        alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                        textView.setTextColor(getResources().getColor(R.color.white));
                        button.setTextColor(getResources().getColor(R.color.darkHighlight));





                    } else {
                        alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.white));
                        alert_dialog_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                        textView.setTextColor(getResources().getColor(R.color.black));
                        button.setTextColor(getResources().getColor(R.color.colorPrimary));





                    }
                }




                alert_dialog_layout.addView(alert_dialog_title);
                alert_dialog_layout.addView(textView);
                alert_dialog_layout.addView(button);

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setView(alert_dialog_layout);
                alertDialog = builder.create();
                alertDialog.show();

                final AlertDialog finalAlertDialog = alertDialog;

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        finalAlertDialog.dismiss();

                    }
                });


            }
        });




        //initialise elements

        final ListView list_view_lor = findViewById(R.id.list_view_lor);

        final ArrayList<LettersOfRecommendationItem> lettersOfRecommendationItemArrayList = new ArrayList<>();

        //set the items of list view

        lettersOfRecommendationItemArrayList.add(new LettersOfRecommendationItem("1",sharedPref.getString("LOR 1 Recommender","-"), sharedPref.getString("LOR 1 Designation","-")));
        lettersOfRecommendationItemArrayList.add(new LettersOfRecommendationItem("2",sharedPref.getString("LOR 2 Recommender","-"), sharedPref.getString("LOR 2 Designation","-")));
        lettersOfRecommendationItemArrayList.add(new LettersOfRecommendationItem("3",sharedPref.getString("LOR 3 Recommender","-"), sharedPref.getString("LOR 3 Designation","-")));
        lettersOfRecommendationItemArrayList.add(new LettersOfRecommendationItem("4",sharedPref.getString("LOR 4 Recommender","-"), sharedPref.getString("LOR 4 Designation","-")));
        lettersOfRecommendationItemArrayList.add(new LettersOfRecommendationItem("5",sharedPref.getString("LOR 5 Recommender","-"), sharedPref.getString("LOR 5 Designation","-")));


        LettersOfRecommendationAdapter lettersOfRecommendationAdapter = new LettersOfRecommendationAdapter(lettersOfRecommendationItemArrayList, getBaseContext());
        list_view_lor.setAdapter(lettersOfRecommendationAdapter);


        //Google sign in and firebase database

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        final DatabaseReference lor1_reference = databaseReference.child("Data").child(account.getId()).child("Letters of Recommendation").child("LOR 1");
        final DatabaseReference lor2_reference = databaseReference.child("Data").child(account.getId()).child("Letters of Recommendation").child("LOR 2");
        final DatabaseReference lor3_reference = databaseReference.child("Data").child(account.getId()).child("Letters of Recommendation").child("LOR 3");
        final DatabaseReference lor4_reference = databaseReference.child("Data").child(account.getId()).child("Letters of Recommendation").child("LOR 4");
        final DatabaseReference lor5_reference = databaseReference.child("Data").child(account.getId()).child("Letters of Recommendation").child("LOR 5");

        final DatabaseReference lor1_recommender = lor1_reference.child("Recommender");
        final DatabaseReference lor2_recommender = lor2_reference.child("Recommender");
        final DatabaseReference lor3_recommender = lor3_reference.child("Recommender");
        final DatabaseReference lor4_recommender = lor4_reference.child("Recommender");
        final DatabaseReference lor5_recommender = lor5_reference.child("Recommender");

        final DatabaseReference lor1_designation = lor1_reference.child("Designation");
        final DatabaseReference lor2_designation = lor2_reference.child("Designation");
        final DatabaseReference lor3_designation = lor3_reference.child("Designation");
        final DatabaseReference lor4_designation = lor4_reference.child("Designation");
        final DatabaseReference lor5_designation = lor5_reference.child("Designation");



        lor1_recommender.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                if (value == null){
                    Log.e("LOR 1 Recommender", "null");
                }

                else {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("LOR 1 Recommender", value);
                    editor.apply();

                    Log.e("LOR 1 Recommender", value);


                    LettersOfRecommendationItem lor2 = lettersOfRecommendationItemArrayList.get(1);
                    LettersOfRecommendationItem lor3 = lettersOfRecommendationItemArrayList.get(2);
                    LettersOfRecommendationItem lor4 = lettersOfRecommendationItemArrayList.get(3);
                    LettersOfRecommendationItem lor5 = lettersOfRecommendationItemArrayList.get(4);


                    lettersOfRecommendationItemArrayList.clear();

                    lettersOfRecommendationItemArrayList.add(new LettersOfRecommendationItem("1",sharedPref.getString("LOR 1 Recommender","-"),sharedPref.getString("LOR 1 Designation","-")));
                    lettersOfRecommendationItemArrayList.add(lor2);
                    lettersOfRecommendationItemArrayList.add(lor3);
                    lettersOfRecommendationItemArrayList.add(lor4);
                    lettersOfRecommendationItemArrayList.add(lor5);


                    LettersOfRecommendationAdapter temp_adapter = new LettersOfRecommendationAdapter(lettersOfRecommendationItemArrayList, getBaseContext());
                    list_view_lor.setAdapter(temp_adapter);


                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("error","database error");

            }
        });


        lor1_designation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                if (value == null){
                    Log.e("LOR 1 Designation", "null");
                }

                else {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("LOR 1 Designation", value);
                    editor.apply();

                    Log.e("LOR 1 Designation", value);


                    LettersOfRecommendationItem lor2 = lettersOfRecommendationItemArrayList.get(1);
                    LettersOfRecommendationItem lor3 = lettersOfRecommendationItemArrayList.get(2);
                    LettersOfRecommendationItem lor4 = lettersOfRecommendationItemArrayList.get(3);
                    LettersOfRecommendationItem lor5 = lettersOfRecommendationItemArrayList.get(4);


                    lettersOfRecommendationItemArrayList.clear();

                    lettersOfRecommendationItemArrayList.add(new LettersOfRecommendationItem("1",sharedPref.getString("LOR 1 Recommender","-"),sharedPref.getString("LOR 1 Designation","-")));
                    lettersOfRecommendationItemArrayList.add(lor2);
                    lettersOfRecommendationItemArrayList.add(lor3);
                    lettersOfRecommendationItemArrayList.add(lor4);
                    lettersOfRecommendationItemArrayList.add(lor5);


                    LettersOfRecommendationAdapter temp_adapter = new LettersOfRecommendationAdapter(lettersOfRecommendationItemArrayList, getBaseContext());
                    list_view_lor.setAdapter(temp_adapter);


                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("error","database error");

            }
        });



        lor2_recommender.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                if (value == null){
                    Log.e("LOR 2 Recommender", "null");
                }

                else {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("LOR 2 Recommender", value);
                    editor.apply();

                    Log.e("LOR 2 Recommender", value);


                    LettersOfRecommendationItem lor1 = lettersOfRecommendationItemArrayList.get(0);
                    LettersOfRecommendationItem lor3 = lettersOfRecommendationItemArrayList.get(2);
                    LettersOfRecommendationItem lor4 = lettersOfRecommendationItemArrayList.get(3);
                    LettersOfRecommendationItem lor5 = lettersOfRecommendationItemArrayList.get(4);


                    lettersOfRecommendationItemArrayList.clear();

                    lettersOfRecommendationItemArrayList.add(lor1);
                    lettersOfRecommendationItemArrayList.add(new LettersOfRecommendationItem("2",sharedPref.getString("LOR 2 Recommender","-"),sharedPref.getString("LOR 2 Designation","-")));
                    lettersOfRecommendationItemArrayList.add(lor3);
                    lettersOfRecommendationItemArrayList.add(lor4);
                    lettersOfRecommendationItemArrayList.add(lor5);


                    LettersOfRecommendationAdapter temp_adapter = new LettersOfRecommendationAdapter(lettersOfRecommendationItemArrayList, getBaseContext());
                    list_view_lor.setAdapter(temp_adapter);


                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("error","database error");

            }
        });


        lor2_designation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                if (value == null){
                    Log.e("LOR 2 Designation", "null");
                }

                else {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("LOR 2 Designation", value);
                    editor.apply();

                    Log.e("LOR 2 Designation", value);


                    LettersOfRecommendationItem lor1 = lettersOfRecommendationItemArrayList.get(0);
                    LettersOfRecommendationItem lor3 = lettersOfRecommendationItemArrayList.get(2);
                    LettersOfRecommendationItem lor4 = lettersOfRecommendationItemArrayList.get(3);
                    LettersOfRecommendationItem lor5 = lettersOfRecommendationItemArrayList.get(4);


                    lettersOfRecommendationItemArrayList.clear();

                    lettersOfRecommendationItemArrayList.add(lor1);
                    lettersOfRecommendationItemArrayList.add(new LettersOfRecommendationItem("2",sharedPref.getString("LOR 2 Recommender","-"),sharedPref.getString("LOR 2 Designation","-")));
                    lettersOfRecommendationItemArrayList.add(lor3);
                    lettersOfRecommendationItemArrayList.add(lor4);
                    lettersOfRecommendationItemArrayList.add(lor5);


                    LettersOfRecommendationAdapter temp_adapter = new LettersOfRecommendationAdapter(lettersOfRecommendationItemArrayList, getBaseContext());
                    list_view_lor.setAdapter(temp_adapter);


                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("error","database error");

            }
        });



        lor3_recommender.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                if (value == null){
                    Log.e("LOR 3 Recommender", "null");
                }

                else {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("LOR 3 Recommender", value);
                    editor.apply();

                    Log.e("LOR 3 Recommender", value);


                    LettersOfRecommendationItem lor1 = lettersOfRecommendationItemArrayList.get(0);
                    LettersOfRecommendationItem lor2 = lettersOfRecommendationItemArrayList.get(1);
                    LettersOfRecommendationItem lor4 = lettersOfRecommendationItemArrayList.get(3);
                    LettersOfRecommendationItem lor5 = lettersOfRecommendationItemArrayList.get(4);


                    lettersOfRecommendationItemArrayList.clear();

                    lettersOfRecommendationItemArrayList.add(lor1);
                    lettersOfRecommendationItemArrayList.add(lor2);
                    lettersOfRecommendationItemArrayList.add(new LettersOfRecommendationItem("3",sharedPref.getString("LOR 3 Recommender","-"),sharedPref.getString("LOR 3 Designation","-")));
                    lettersOfRecommendationItemArrayList.add(lor4);
                    lettersOfRecommendationItemArrayList.add(lor5);


                    LettersOfRecommendationAdapter temp_adapter = new LettersOfRecommendationAdapter(lettersOfRecommendationItemArrayList, getBaseContext());
                    list_view_lor.setAdapter(temp_adapter);


                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("error","database error");

            }
        });


        lor3_designation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                if (value == null){
                    Log.e("LOR 3 Designation", "null");
                }

                else {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("LOR 3 Designation", value);
                    editor.apply();

                    Log.e("LOR 3 Designation", value);


                    LettersOfRecommendationItem lor1 = lettersOfRecommendationItemArrayList.get(0);
                    LettersOfRecommendationItem lor2 = lettersOfRecommendationItemArrayList.get(1);
                    LettersOfRecommendationItem lor4 = lettersOfRecommendationItemArrayList.get(3);
                    LettersOfRecommendationItem lor5 = lettersOfRecommendationItemArrayList.get(4);


                    lettersOfRecommendationItemArrayList.clear();

                    lettersOfRecommendationItemArrayList.add(lor1);
                    lettersOfRecommendationItemArrayList.add(lor2);
                    lettersOfRecommendationItemArrayList.add(new LettersOfRecommendationItem("3",sharedPref.getString("LOR 3 Recommender","-"),sharedPref.getString("LOR 3 Designation","-")));
                    lettersOfRecommendationItemArrayList.add(lor4);
                    lettersOfRecommendationItemArrayList.add(lor5);


                    LettersOfRecommendationAdapter temp_adapter = new LettersOfRecommendationAdapter(lettersOfRecommendationItemArrayList, getBaseContext());
                    list_view_lor.setAdapter(temp_adapter);


                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("error","database error");

            }
        });


        lor4_recommender.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                if (value == null){
                    Log.e("LOR 4 Recommender", "null");
                }

                else {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("LOR 4 Recommender", value);
                    editor.apply();

                    Log.e("LOR 4 Recommender", value);


                    LettersOfRecommendationItem lor1 = lettersOfRecommendationItemArrayList.get(0);
                    LettersOfRecommendationItem lor2 = lettersOfRecommendationItemArrayList.get(1);
                    LettersOfRecommendationItem lor3 = lettersOfRecommendationItemArrayList.get(2);
                    LettersOfRecommendationItem lor5 = lettersOfRecommendationItemArrayList.get(4);


                    lettersOfRecommendationItemArrayList.clear();

                    lettersOfRecommendationItemArrayList.add(lor1);
                    lettersOfRecommendationItemArrayList.add(lor2);
                    lettersOfRecommendationItemArrayList.add(lor3);
                    lettersOfRecommendationItemArrayList.add(new LettersOfRecommendationItem("4",sharedPref.getString("LOR 4 Recommender","-"),sharedPref.getString("LOR 4 Designation","-")));
                    lettersOfRecommendationItemArrayList.add(lor5);


                    LettersOfRecommendationAdapter temp_adapter = new LettersOfRecommendationAdapter(lettersOfRecommendationItemArrayList, getBaseContext());
                    list_view_lor.setAdapter(temp_adapter);


                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("error","database error");

            }
        });


        lor4_designation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                if (value == null){
                    Log.e("LOR 4 Designation", "null");
                }

                else {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("LOR 4 Designation", value);
                    editor.apply();

                    Log.e("LOR 4 Designation", value);


                    LettersOfRecommendationItem lor1 = lettersOfRecommendationItemArrayList.get(0);
                    LettersOfRecommendationItem lor2 = lettersOfRecommendationItemArrayList.get(1);
                    LettersOfRecommendationItem lor3 = lettersOfRecommendationItemArrayList.get(2);
                    LettersOfRecommendationItem lor5 = lettersOfRecommendationItemArrayList.get(4);


                    lettersOfRecommendationItemArrayList.clear();

                    lettersOfRecommendationItemArrayList.add(lor1);
                    lettersOfRecommendationItemArrayList.add(lor2);
                    lettersOfRecommendationItemArrayList.add(lor3);
                    lettersOfRecommendationItemArrayList.add(new LettersOfRecommendationItem("4",sharedPref.getString("LOR 4 Recommender","-"),sharedPref.getString("LOR 4 Designation","-")));
                    lettersOfRecommendationItemArrayList.add(lor5);


                    LettersOfRecommendationAdapter temp_adapter = new LettersOfRecommendationAdapter(lettersOfRecommendationItemArrayList, getBaseContext());
                    list_view_lor.setAdapter(temp_adapter);


                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("error","database error");

            }
        });


        lor5_recommender.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                if (value == null){
                    Log.e("LOR 5 Recommender", "null");
                }

                else {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("LOR 5 Recommender", value);
                    editor.apply();

                    Log.e("LOR 5 Recommender", value);


                    LettersOfRecommendationItem lor1 = lettersOfRecommendationItemArrayList.get(0);
                    LettersOfRecommendationItem lor2 = lettersOfRecommendationItemArrayList.get(1);
                    LettersOfRecommendationItem lor3 = lettersOfRecommendationItemArrayList.get(2);
                    LettersOfRecommendationItem lor4 = lettersOfRecommendationItemArrayList.get(3);


                    lettersOfRecommendationItemArrayList.clear();

                    lettersOfRecommendationItemArrayList.add(lor1);
                    lettersOfRecommendationItemArrayList.add(lor2);
                    lettersOfRecommendationItemArrayList.add(lor3);
                    lettersOfRecommendationItemArrayList.add(lor4);
                    lettersOfRecommendationItemArrayList.add(new LettersOfRecommendationItem("5",sharedPref.getString("LOR 5 Recommender","-"),sharedPref.getString("LOR 5 Designation","-")));


                    LettersOfRecommendationAdapter temp_adapter = new LettersOfRecommendationAdapter(lettersOfRecommendationItemArrayList, getBaseContext());
                    list_view_lor.setAdapter(temp_adapter);


                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("error","database error");

            }
        });


        lor5_designation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);

                if (value == null){
                    Log.e("LOR 5 Designation", "null");
                }

                else {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("LOR 5 Designation", value);
                    editor.apply();

                    Log.e("LOR 5 Designation", value);


                    LettersOfRecommendationItem lor1 = lettersOfRecommendationItemArrayList.get(0);
                    LettersOfRecommendationItem lor2 = lettersOfRecommendationItemArrayList.get(1);
                    LettersOfRecommendationItem lor3 = lettersOfRecommendationItemArrayList.get(2);
                    LettersOfRecommendationItem lor4 = lettersOfRecommendationItemArrayList.get(3);


                    lettersOfRecommendationItemArrayList.clear();

                    lettersOfRecommendationItemArrayList.add(lor1);
                    lettersOfRecommendationItemArrayList.add(lor2);
                    lettersOfRecommendationItemArrayList.add(lor3);
                    lettersOfRecommendationItemArrayList.add(lor4);
                    lettersOfRecommendationItemArrayList.add(new LettersOfRecommendationItem("5",sharedPref.getString("LOR 5 Recommender","-"),sharedPref.getString("LOR 5 Designation","-")));


                    LettersOfRecommendationAdapter temp_adapter = new LettersOfRecommendationAdapter(lettersOfRecommendationItemArrayList, getBaseContext());
                    list_view_lor.setAdapter(temp_adapter);


                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("error","database error");

            }
        });

        list_view_lor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog alertDialog = null;

                LinearLayout alert_dialog_layout = new LinearLayout(view.getContext());
                alert_dialog_layout.setOrientation(LinearLayout.VERTICAL);
                alert_dialog_layout.setPadding(20, 20, 20, 20);
                alert_dialog_layout.setGravity(Gravity.CENTER);

                TextView alert_dialog_title = new TextView(alert_dialog_layout.getContext());
                alert_dialog_title.setText("LOR "+(position+1));
                alert_dialog_title.setTextSize(22);
                alert_dialog_title.setPadding(40, 20, 20, 20);
                alert_dialog_title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                alert_dialog_title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                Typeface nexa_bold = getResources().getFont(R.font.nexa_bold);
                Typeface nexa_light = getResources().getFont(R.font.nexa_light);

                alert_dialog_title.setTypeface(nexa_bold);

                final TextView textView = new TextView(alert_dialog_layout.getContext());

                textView.setTypeface(nexa_bold);
                textView.setTextSize(15);
                textView.setText("Recommender:");
                textView.setPadding(20,20,20,20);
                textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


                final EditText editText = new EditText(alert_dialog_layout.getContext());


                editText.setTypeface(nexa_light);
                editText.setTextSize(20);
                editText.setText(sharedPref.getString("LOR "+(position+1)+" Recommender",""));
                editText.setPadding(20,20,20,20);
                editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText.setSingleLine(true);


                final TextView textView2 = new TextView(alert_dialog_layout.getContext());

                textView2.setTypeface(nexa_bold);
                textView2.setTextSize(15);
                textView2.setText("Designation:");
                textView2.setPadding(20,20,20,20);
                textView2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


                final EditText editText2 = new EditText(alert_dialog_layout.getContext());


                editText2.setTypeface(nexa_light);
                editText2.setTextSize(20);
                editText2.setText(sharedPref.getString("LOR "+(position+1)+" Designation",""));
                editText2.setPadding(20,20,20,20);
                editText2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText2.setSingleLine(true);


                TextView button = new TextView(alert_dialog_layout.getContext());
                button.setPadding(20,20,20,20);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(10,10,10,10);
                button.setLayoutParams(layoutParams);
                button.setTextSize(15);
                button.setTypeface(nexa_bold);
                button.setText("SAVE");

                int[][] states = new int[][]{


                        new int[]{android.R.attr.state_enabled} // enabled
                };

                int[] colors = new int[]{

                        getResources().getColor(R.color.colorPrimary)
                };

                if (theme == AppCompatDelegate.MODE_NIGHT_NO) {
                    alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.white));
                    alert_dialog_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                    textView.setTextColor(getResources().getColor(R.color.black));
                    textView2.setTextColor(getResources().getColor(R.color.black));
                    editText.setTextColor(getResources().getColor(R.color.black));
                    editText2.setTextColor(getResources().getColor(R.color.black));
                    button.setTextColor(getResources().getColor(R.color.colorPrimary));

                    colors = new int[]{

                            getResources().getColor(R.color.colorPrimary)
                    };

                    Field f = null;
                    try {
                        f = TextView.class.getDeclaredField("mCursorDrawableRes");
                        f.setAccessible(true);
                        f.set(editText, R.drawable.cursor_light);
                        f.set(editText2, R.drawable.cursor_light);

                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }



                } else if (theme == AppCompatDelegate.MODE_NIGHT_YES) {
                    alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                    alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                    textView.setTextColor(getResources().getColor(R.color.white));
                    textView2.setTextColor(getResources().getColor(R.color.white));
                    editText.setTextColor(getResources().getColor(R.color.white));
                    editText2.setTextColor(getResources().getColor(R.color.white));
                    button.setTextColor(getResources().getColor(R.color.darkHighlight));

                    colors = new int[]{

                            getResources().getColor(R.color.darkHighlight)
                    };

                    Field f = null;
                    try {
                        f = TextView.class.getDeclaredField("mCursorDrawableRes");
                        f.setAccessible(true);
                        f.set(editText, R.drawable.cursor_dark);
                        f.set(editText2, R.drawable.cursor_dark);

                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }

                } else {
                    PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
                    if (powerManager.isPowerSaveMode()) {
                        alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                        alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                        textView.setTextColor(getResources().getColor(R.color.white));
                        textView2.setTextColor(getResources().getColor(R.color.white));
                        editText.setTextColor(getResources().getColor(R.color.white));
                        editText2.setTextColor(getResources().getColor(R.color.white));
                        button.setTextColor(getResources().getColor(R.color.darkHighlight));

                        colors = new int[]{

                                getResources().getColor(R.color.darkHighlight)
                        };


                        Field f = null;
                        try {
                            f = TextView.class.getDeclaredField("mCursorDrawableRes");
                            f.setAccessible(true);
                            f.set(editText, R.drawable.cursor_dark);
                            f.set(editText2, R.drawable.cursor_dark);


                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    } else {
                        alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.white));
                        alert_dialog_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                        textView.setTextColor(getResources().getColor(R.color.black));
                        textView2.setTextColor(getResources().getColor(R.color.black));
                        editText.setTextColor(getResources().getColor(R.color.black));
                        editText2.setTextColor(getResources().getColor(R.color.black));
                        button.setTextColor(getResources().getColor(R.color.colorPrimary));

                        colors = new int[]{

                                getResources().getColor(R.color.colorPrimary)
                        };

                        Field f = null;
                        try {
                            f = TextView.class.getDeclaredField("mCursorDrawableRes");
                            f.setAccessible(true);
                            f.set(editText, R.drawable.cursor_light);
                            f.set(editText2, R.drawable.cursor_light);

                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    }
                }

                ColorStateList colorStateList = new ColorStateList(states,colors);
                editText.setBackgroundTintList(colorStateList);
                editText2.setBackgroundTintList(colorStateList);



                alert_dialog_layout.addView(alert_dialog_title);
                alert_dialog_layout.addView(textView);
                alert_dialog_layout.addView(editText);
                alert_dialog_layout.addView(textView2);
                alert_dialog_layout.addView(editText2);
                alert_dialog_layout.addView(button);

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(alert_dialog_layout);
                alertDialog = builder.create();
                alertDialog.show();

                final AlertDialog finalAlertDialog = alertDialog;


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getBaseContext());

                        if (editText.getText().toString().equals(""))
                            FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Letters of Recommendation").child("LOR "+(position+1)).child("Recommender").setValue("-");
                        else
                            FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Letters of Recommendation").child("LOR "+(position+1)).child("Recommender").setValue(editText.getText().toString());

                        if (editText2.getText().toString().equals(""))
                            FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Letters of Recommendation").child("LOR "+(position+1)).child("Designation").setValue("-");
                        else
                            FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Letters of Recommendation").child("LOR "+(position+1)).child("Designation").setValue(editText2.getText().toString());


                        finalAlertDialog.dismiss();
//                            finish();
//                            startActivity(getIntent());



                    }
                });



            }
        });

    }
}
