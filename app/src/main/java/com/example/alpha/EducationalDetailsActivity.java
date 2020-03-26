package com.example.alpha;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class EducationalDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educational_details);

        Log.i(this.toString(),"started");

        //shared preferences

        final SharedPreferences sharedPref = Objects.requireNonNull(getSharedPreferences("Shared Preferences", MODE_PRIVATE));
        final int theme = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);
        Log.d(this.toString(),"current app theme is "+theme);

        //customise action bar

        ActionBar action_bar = getSupportActionBar();

        action_bar.setDisplayShowTitleEnabled(false);
        Log.d(this.toString(),"action bar display show title disabled");

        action_bar.setDisplayShowCustomEnabled(true);
        Log.d(this.toString(),"action bar custom display enabled");

        action_bar.setCustomView(R.layout.action_bar_with_buttons);
        Log.d(this.toString(),"action bar custom view set");

        TextView action_bar_title = action_bar.getCustomView().findViewById(R.id.action_bar_title);
        ImageButton back_button = action_bar.getCustomView().findViewById(R.id.back_button);
        ImageButton help_button = action_bar.getCustomView().findViewById(R.id.help_button);

        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        Log.d(this.toString(),"window flags cleared");

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        Log.d(this.toString(),"window flags added");


        action_bar_title.setText("Educational Details");
        Log.d(this.toString(),"action bar title set");

        int[][] states = new int[][]{


                new int[]{android.R.attr.state_enabled} // enabled
        };

        int[] colors= new int[]{

                getResources().getColor(R.color.colorPrimary)

        };



        if (theme == AppCompatDelegate.MODE_NIGHT_NO){


            action_bar_title.setTextColor(getResources().getColor(R.color.colorPrimary));
            Log.d(this.toString(),"action bar title text color set to color primary");

            colors= new int[]{

                    getResources().getColor(R.color.colorPrimary)

            };

            back_button.setBackgroundColor(getResources().getColor(R.color.white));
            help_button.setBackgroundColor(getResources().getColor(R.color.white));
            Log.d(this.toString(),"action bar icons background color set to white");


            //change status bar colour
            window.setStatusBarColor(getResources().getColor(R.color.white));
            Log.d(this.toString(),"status bar color set to white");

            //change action bar colour
            action_bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
            Log.d(this.toString(),"action bar color set to white");

            // set status bar contrast
            View decor = window.getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            Log.d(this.toString(),"contrast set");




        }
        else if (theme == AppCompatDelegate.MODE_NIGHT_YES){



            action_bar_title.setTextColor(getResources().getColor(R.color.darkHighlight));
            Log.d(this.toString(),"action bar title text color set to dark highlight");

            colors= new int[]{

                    getResources().getColor(R.color.darkHighlight)

            };

            back_button.setBackgroundColor(getResources().getColor(R.color.darkBackground));
            help_button.setBackgroundColor(getResources().getColor(R.color.darkBackground));
            Log.d(this.toString(),"action bar icons background color set to dark background");

            //change status bar colour
            window.setStatusBarColor(getResources().getColor(R.color.darkBackground));
            Log.d(this.toString(),"status bar color set to dark background");

            //change action bar colour
            action_bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkBackground)));
            Log.d(this.toString(),"action bar color set to dark background");

            // set status bar contrast
            View decor = window.getDecorView();
            LinearLayout background_layout = decor.findViewById(R.id.background_layout);
            background_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
            Log.d(this.toString(),"contrast set");




        }
        else {



            PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
            if (powerManager.isPowerSaveMode()) {

                action_bar_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                Log.d(this.toString(),"action bar title text color set to dark highlight");

                colors= new int[]{

                        getResources().getColor(R.color.darkHighlight)

                };

                back_button.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                help_button.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                Log.d(this.toString(),"action bar icons background color set to dark background");


                //change status bar colour
                window.setStatusBarColor(getResources().getColor(R.color.darkBackground));
                Log.d(this.toString(),"status bar color set to dark background");

                //change action bar colour
                action_bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkBackground)));
                Log.d(this.toString(),"action bar color set to dark background");

                // set status bar contrast
                View decor = window.getDecorView();
                LinearLayout background_layout = decor.findViewById(R.id.background_layout);
                background_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                Log.d(this.toString(),"contrast set");




            } else {

                action_bar_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                Log.d(this.toString(),"action bar title text color set to color primary");

                colors= new int[]{

                        getResources().getColor(R.color.colorPrimary)

                };

                back_button.setBackgroundColor(getResources().getColor(R.color.white));
                help_button.setBackgroundColor(getResources().getColor(R.color.white));
                Log.d(this.toString(),"action bar icons background color set to white");

                //change status bar colour
                window.setStatusBarColor(getResources().getColor(R.color.white));
                Log.d(this.toString(),"status bar color set to white");

                //change action bar colour
                action_bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
                Log.d(this.toString(),"action bar color set to white");

                // set status bar contrast
                View decor = window.getDecorView();
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                Log.d(this.toString(),"contrast set");




            }

        }


        ColorStateList colorStateList = new ColorStateList(states, colors);

        back_button.setImageTintList(colorStateList);
        help_button.setImageTintList(colorStateList);

        Log.d(this.toString(),"action bar buttons image tint list set");



        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(this.toString(),"back button pressed");

                onBackPressed();
            }
        });

        help_button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                Log.i(this.toString(),"help button pressed");

                AlertDialog alertDialog = null;

                LinearLayout alert_dialog_layout = new LinearLayout(v.getContext());
                alert_dialog_layout.setOrientation(LinearLayout.VERTICAL);
                alert_dialog_layout.setPadding(20, 20, 20, 20);
                alert_dialog_layout.setGravity(Gravity.CENTER);

                TextView alert_dialog_title = new TextView(alert_dialog_layout.getContext());
                alert_dialog_title.setText("Educational Details - Help");
                alert_dialog_title.setTextSize(22);
                alert_dialog_title.setPadding(40, 20, 20, 20);
                alert_dialog_title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                alert_dialog_title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                Log.w(this.toString(),"initialising nexa bold...(requires API level 26)");
                Typeface nexa_bold = getResources().getFont(R.font.nexa_bold);

                Log.w(this.toString(),"initialising nexa light...(requires API level 26)");
                Typeface nexa_light = getResources().getFont(R.font.nexa_light);

                alert_dialog_title.setTypeface(nexa_bold);


                TextView textView = new TextView(alert_dialog_layout.getContext());

                textView.setTypeface(nexa_light);
                textView.setTextSize(17);
                textView.setText("This page lets you view and edit your educational details.\n\nFor example, educational details might look like - \n\nCollege: IIT Delhi\nCourse: CS\nAggregate Score: 8.5 CGPA\nDuration: 4 years\nBacklogs: 0\n\nYour educational details are few of the many factors considered in the overall process of admission application.\n\nGood luck!");
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

        final ListView list_view_education = findViewById(R.id.list_view_education);
        Log.d(this.toString(), "list_view_education initialised");

        final ArrayList<EducationItem> educationItemArrayList = new ArrayList<>();
        Log.d(this.toString(), "educationItemArrayList initialised");

        //set the items of list view

        educationItemArrayList.add(new EducationItem("COLLEGE",sharedPref.getString("College","-")));
        Log.d(this.toString(),"item added to educationItemArrayList - college");

        educationItemArrayList.add(new EducationItem("COURSE",sharedPref.getString("Course","-")));
        Log.d(this.toString(),"item added to educationItemArrayList - course");

        educationItemArrayList.add(new EducationItem("AGGREGATE SCORE",sharedPref.getString("Aggregate Score","-")));
        Log.d(this.toString(),"item added to educationItemArrayList - score");

        educationItemArrayList.add(new EducationItem("DURATION",sharedPref.getString("Duration","-")));
        Log.d(this.toString(),"item added to educationItemArrayList - duration");

        educationItemArrayList.add(new EducationItem("BACKLOGS",sharedPref.getString("Backlogs","-")));
        Log.d(this.toString(),"item added to educationItemArrayList - backlogs");



        final EducationAdapter educationAdapter = new EducationAdapter(educationItemArrayList, getBaseContext());
        Log.d(this.toString(),"educationAdapter created");

        list_view_education.setAdapter(educationAdapter);
        Log.d(this.toString(),"educationAdapter set to list_view_education");



        //Google sign in and firebase database
        Log.d(this.toString(),"getting last signed in account...");
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        Log.d(this.toString(),"getting databaseReference...");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        Log.d(this.toString(),"getting college_reference...");
        final DatabaseReference college_reference = databaseReference.child("Data").child(account.getId()).child("Educational Details").child("College");

        Log.d(this.toString(),"getting course_reference...");
        final DatabaseReference course_reference = databaseReference.child("Data").child(account.getId()).child("Educational Details").child("Course");

        Log.d(this.toString(),"getting score_reference...");
        final DatabaseReference score_reference = databaseReference.child("Data").child(account.getId()).child("Educational Details").child("Aggregate Score");

        Log.d(this.toString(),"getting duration_reference...");
        final DatabaseReference duration_reference = databaseReference.child("Data").child(account.getId()).child("Educational Details").child("Duration");

        Log.d(this.toString(),"getting backlogs_reference...");
        final DatabaseReference backlogs_reference = databaseReference.child("Data").child(account.getId()).child("Educational Details").child("Backlogs");


        college_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d(this.toString(),"data at college_reference changed");

                String value = dataSnapshot.getValue(String.class);

                if (value == null){
                    Log.e(this.toString(), "data at college_reference is null");
                }

                else {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("College", value);
                    editor.apply();

                    Log.d(this.toString(), "data at college_reference is " + value);


                    String temp_course = educationItemArrayList.get(1).value;
                    Log.d(this.toString(), "temp_course is " + temp_course);

                    String temp_score = educationItemArrayList.get(2).value;
                    Log.d(this.toString(), "temp_score is " + temp_score);

                    String temp_duration = educationItemArrayList.get(3).value;
                    Log.d(this.toString(), "temp_duration is " + temp_duration);

                    String temp_backlogs = educationItemArrayList.get(4).value;
                    Log.d(this.toString(), "temp_backlogs is " + temp_backlogs);


                    educationItemArrayList.clear();
                    Log.d(this.toString(), "educationItemArrayList cleared");

                    educationItemArrayList.add(new EducationItem("COLLEGE",sharedPref.getString("College","-")));
                    Log.d(this.toString(),"item added to educationItemArrayList - college");

                    educationItemArrayList.add(new EducationItem("COURSE",temp_course));
                    Log.d(this.toString(),"item added to educationItemArrayList - course");

                    educationItemArrayList.add(new EducationItem("AGGREGATE SCORE",temp_score));
                    Log.d(this.toString(),"item added to educationItemArrayList - aggregate score");

                    educationItemArrayList.add(new EducationItem("DURATION",temp_duration));
                    Log.d(this.toString(),"item added to educationItemArrayList - duration");

                    educationItemArrayList.add(new EducationItem("BACKLOGS",temp_backlogs));
                    Log.d(this.toString(),"item added to educationItemArrayList - backlogs");



                    EducationAdapter temp_adapter = new EducationAdapter(educationItemArrayList, getBaseContext());
                    Log.d(this.toString(),"temp_adapter created");

                    list_view_education.setAdapter(temp_adapter);
                    Log.d(this.toString(),"temp_adapter set to list_view_education");


                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e(this.toString(),databaseError.getMessage());

            }
        });


        course_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d(this.toString(),"data at course_reference changed");

                String value = dataSnapshot.getValue(String.class);

                if (value == null){
                    Log.e(this.toString(), "data at course_reference is null");
                }

                else {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Course", value);
                    editor.apply();

                    Log.d(this.toString(), "data at course_reference is " + value);


                    String temp_college = educationItemArrayList.get(0).value;
                    Log.d(this.toString(), "temp_college is " + temp_college);

                    String temp_score = educationItemArrayList.get(2).value;
                    Log.d(this.toString(), "temp_score is " + temp_score);

                    String temp_duration = educationItemArrayList.get(3).value;
                    Log.d(this.toString(), "temp_duration is " + temp_duration);

                    String temp_backlogs = educationItemArrayList.get(4).value;
                    Log.d(this.toString(), "temp_backlogs is " + temp_backlogs);



                    educationItemArrayList.clear();
                    Log.d(this.toString(), "educationItemArrayList cleared");


                    educationItemArrayList.add(new EducationItem("COLLEGE",temp_college));
                    Log.d(this.toString(),"item added to educationItemArrayList - college");

                    educationItemArrayList.add(new EducationItem("COURSE",sharedPref.getString("Course","-")));
                    Log.d(this.toString(),"item added to educationItemArrayList - course");

                    educationItemArrayList.add(new EducationItem("AGGREGATE SCORE",temp_score));
                    Log.d(this.toString(),"item added to educationItemArrayList - aggregate score");

                    educationItemArrayList.add(new EducationItem("DURATION",temp_duration));
                    Log.d(this.toString(),"item added to educationItemArrayList - duration");

                    educationItemArrayList.add(new EducationItem("BACKLOGS",temp_backlogs));
                    Log.d(this.toString(),"item added to educationItemArrayList - backlogs");



                    EducationAdapter temp_adapter = new EducationAdapter(educationItemArrayList, getBaseContext());
                    Log.d(this.toString(),"temp_adapter created");

                    list_view_education.setAdapter(temp_adapter);
                    Log.d(this.toString(),"temp_adapter set to list_view_education");


                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e(this.toString(),databaseError.getMessage());

            }
        });


        score_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d(this.toString(),"data at score_reference changed");

                String value = dataSnapshot.getValue(String.class);

                if (value == null){
                    Log.e(this.toString(), "data at score_reference is null");
                }

                else {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Aggregate Score", value);
                    editor.apply();

                    Log.d(this.toString(), "data at score_reference is " + value);


                    String temp_college = educationItemArrayList.get(0).value;
                    Log.d(this.toString(), "temp_college is " + temp_college);

                    String temp_course = educationItemArrayList.get(1).value;
                    Log.d(this.toString(), "temp_course is " + temp_course);

                    String temp_duration = educationItemArrayList.get(3).value;
                    Log.d(this.toString(), "temp_duration is " + temp_duration);

                    String temp_backlogs = educationItemArrayList.get(4).value;
                    Log.d(this.toString(), "temp_backlogs is " + temp_backlogs);


                    educationItemArrayList.clear();
                    Log.d(this.toString(), "educationItemArrayList cleared");

                    educationItemArrayList.add(new EducationItem("COLLEGE",temp_college));
                    Log.d(this.toString(),"item added to educationItemArrayList - college");

                    educationItemArrayList.add(new EducationItem("COURSE",temp_course));
                    Log.d(this.toString(),"item added to educationItemArrayList - course");

                    educationItemArrayList.add(new EducationItem("AGGREGATE SCORE",sharedPref.getString("Aggregate Score","-")));
                    Log.d(this.toString(),"item added to educationItemArrayList - aggregate score");

                    educationItemArrayList.add(new EducationItem("DURATION",temp_duration));
                    Log.d(this.toString(),"item added to educationItemArrayList - duration");

                    educationItemArrayList.add(new EducationItem("BACKLOGS",temp_backlogs));
                    Log.d(this.toString(),"item added to educationItemArrayList - backlogs");


                    EducationAdapter temp_adapter = new EducationAdapter(educationItemArrayList, getBaseContext());
                    Log.d(this.toString(),"temp_adapter created");

                    list_view_education.setAdapter(temp_adapter);
                    Log.d(this.toString(),"temp_adapter set to list_view_education");


                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e(this.toString(),databaseError.getMessage());

            }
        });


        duration_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d(this.toString(),"data at duration_reference changed");

                String value = dataSnapshot.getValue(String.class);

                if (value == null){
                    Log.e(this.toString(), "data at duration_reference is null");
                }

                else {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Duration", value);
                    editor.apply();

                    Log.d(this.toString(), "data at duration_reference is " + value);


                    String temp_college = educationItemArrayList.get(0).value;
                    Log.d(this.toString(), "temp_college is " + temp_college);

                    String temp_course = educationItemArrayList.get(1).value;
                    Log.d(this.toString(), "temp_course is " + temp_course);

                    String temp_score = educationItemArrayList.get(2).value;
                    Log.d(this.toString(), "temp_score is " + temp_score);

                    String temp_backlogs = educationItemArrayList.get(4).value;
                    Log.d(this.toString(), "temp_backlogs is " + temp_backlogs);



                    educationItemArrayList.clear();
                    Log.d(this.toString(), "educationItemArrayList cleared");

                    educationItemArrayList.add(new EducationItem("COLLEGE",temp_college));
                    Log.d(this.toString(),"item added to educationItemArrayList - college");

                    educationItemArrayList.add(new EducationItem("COURSE",temp_course));
                    Log.d(this.toString(),"item added to educationItemArrayList - course");

                    educationItemArrayList.add(new EducationItem("AGGREGATE SCORE",temp_score));
                    Log.d(this.toString(),"item added to educationItemArrayList - aggregate score");

                    educationItemArrayList.add(new EducationItem("DURATION",sharedPref.getString("Duration","-")));
                    Log.d(this.toString(),"item added to educationItemArrayList - duration");

                    educationItemArrayList.add(new EducationItem("BACKLOGS",temp_backlogs));
                    Log.d(this.toString(),"item added to educationItemArrayList - backlogs");



                    EducationAdapter temp_adapter = new EducationAdapter(educationItemArrayList, getBaseContext());
                    Log.d(this.toString(),"temp_adapter created");

                    list_view_education.setAdapter(temp_adapter);
                    Log.d(this.toString(),"temp_adapter set to list_view_education");


                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e(this.toString(),databaseError.getMessage());

            }
        });


        backlogs_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d(this.toString(),"data at backlogs_reference changed");

                String value = dataSnapshot.getValue(String.class);

                if (value == null){
                    Log.e(this.toString(), "data at backlogs_reference is null");
                }

                else {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Backlogs", value);
                    editor.apply();

                    Log.d(this.toString(), "data at backlogs_reference is " + value);


                    String temp_college = educationItemArrayList.get(0).value;
                    Log.d(this.toString(), "temp_college is " + temp_college);

                    String temp_course = educationItemArrayList.get(1).value;
                    Log.d(this.toString(), "temp_course is " + temp_course);

                    String temp_score = educationItemArrayList.get(2).value;
                    Log.d(this.toString(), "temp_score is " + temp_score);

                    String temp_duration = educationItemArrayList.get(3).value;
                    Log.d(this.toString(), "temp_duration is " + temp_duration);



                    educationItemArrayList.clear();
                    Log.d(this.toString(), "educationItemArrayList cleared");

                    educationItemArrayList.add(new EducationItem("COLLEGE",temp_college));
                    Log.d(this.toString(),"item added to educationItemArrayList - college");

                    educationItemArrayList.add(new EducationItem("COURSE",temp_course));
                    Log.d(this.toString(),"item added to educationItemArrayList - course");

                    educationItemArrayList.add(new EducationItem("AGGREGATE SCORE",temp_score));
                    Log.d(this.toString(),"item added to educationItemArrayList - aggregate score");

                    educationItemArrayList.add(new EducationItem("DURATION",temp_duration));
                    Log.d(this.toString(),"item added to educationItemArrayList - duration");

                    educationItemArrayList.add(new EducationItem("BACKLOGS",sharedPref.getString("Backlogs","-")));
                    Log.d(this.toString(),"item added to educationItemArrayList - backlogs");



                    EducationAdapter temp_adapter = new EducationAdapter(educationItemArrayList, getBaseContext());
                    Log.d(this.toString(),"temp_adapter created");

                    list_view_education.setAdapter(temp_adapter);
                    Log.d(this.toString(),"temp_adapter set to list_view_education");


                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e(this.toString(),databaseError.getMessage());

            }
        });



        list_view_education.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (position == 0) //college
                {

                    Log.d(this.toString(),"college clicked");

                    AlertDialog alertDialog = null;

                    LinearLayout alert_dialog_layout = new LinearLayout(view.getContext());
                    alert_dialog_layout.setOrientation(LinearLayout.VERTICAL);
                    alert_dialog_layout.setPadding(20, 20, 20, 20);
                    alert_dialog_layout.setGravity(Gravity.CENTER);

                    TextView alert_dialog_title = new TextView(alert_dialog_layout.getContext());
                    alert_dialog_title.setText("College");
                    alert_dialog_title.setTextSize(22);
                    alert_dialog_title.setPadding(40, 20, 20, 20);
                    alert_dialog_title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    alert_dialog_title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    Log.w(this.toString(),"initialising nexa bold...(requires API level 26)");
                    Typeface nexa_bold = getResources().getFont(R.font.nexa_bold);

                    Log.w(this.toString(),"initialising nexa light...(requires API level 26)");
                    Typeface nexa_light = getResources().getFont(R.font.nexa_light);

                    alert_dialog_title.setTypeface(nexa_bold);


                    final EditText editText = new EditText(alert_dialog_layout.getContext());

                    editText.setTypeface(nexa_light);
                    editText.setTextSize(20);
                    editText.setText(sharedPref.getString("College",""));
                    editText.setPadding(20,20,20,20);
                    editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    editText.setSingleLine(true);


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
                        editText.setTextColor(getResources().getColor(R.color.black));
                        button.setTextColor(getResources().getColor(R.color.colorPrimary));

                        colors = new int[]{

                                getResources().getColor(R.color.colorPrimary)
                        };

                        Field f = null;
                        try {
                            f = TextView.class.getDeclaredField("mCursorDrawableRes");
                            f.setAccessible(true);
                            f.set(editText, R.drawable.cursor_light);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }



                    } else if (theme == AppCompatDelegate.MODE_NIGHT_YES) {
                        alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                        alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                        editText.setTextColor(getResources().getColor(R.color.white));
                        button.setTextColor(getResources().getColor(R.color.darkHighlight));

                        colors = new int[]{

                                getResources().getColor(R.color.darkHighlight)
                        };

                        Field f = null;
                        try {
                            f = TextView.class.getDeclaredField("mCursorDrawableRes");
                            f.setAccessible(true);
                            f.set(editText, R.drawable.cursor_dark);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    } else {
                        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
                        if (powerManager.isPowerSaveMode()) {
                            alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                            alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                            editText.setTextColor(getResources().getColor(R.color.white));
                            button.setTextColor(getResources().getColor(R.color.darkHighlight));

                            colors = new int[]{

                                    getResources().getColor(R.color.darkHighlight)
                            };


                            Field f = null;
                            try {
                                f = TextView.class.getDeclaredField("mCursorDrawableRes");
                                f.setAccessible(true);
                                f.set(editText, R.drawable.cursor_dark);
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                e.printStackTrace();
                            }

                        } else {
                            alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.white));
                            alert_dialog_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                            editText.setTextColor(getResources().getColor(R.color.black));
                            button.setTextColor(getResources().getColor(R.color.colorPrimary));

                            colors = new int[]{

                                    getResources().getColor(R.color.colorPrimary)
                            };

                            Field f = null;
                            try {
                                f = TextView.class.getDeclaredField("mCursorDrawableRes");
                                f.setAccessible(true);
                                f.set(editText, R.drawable.cursor_light);
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    ColorStateList colorStateList = new ColorStateList(states,colors);
                    editText.setBackgroundTintList(colorStateList);


                    Log.d(this.toString(), "adding views to alert dialog...");


                    alert_dialog_layout.addView(alert_dialog_title);
                    Log.d(this.toString(), "alert_dialog_title added to alert dialog");

                    alert_dialog_layout.addView(editText);
                    Log.d(this.toString(), "editText added to alert dialog");

                    alert_dialog_layout.addView(button);
                    Log.d(this.toString(), "button added to alert dialog");

                    Log.d(this.toString(), "building alert dialog...");

                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setView(alert_dialog_layout);
                    alertDialog = builder.create();
                    alertDialog.show();

                    Log.d(this.toString(), "alert dialog created and shown");

                    final AlertDialog finalAlertDialog = alertDialog;


                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getBaseContext());

                            if (editText.getText().toString().equals(""))
                                FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Educational Details").child("College").setValue("-");
                            else
                                FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Educational Details").child("College").setValue(editText.getText().toString());


                            finalAlertDialog.dismiss();
//                            finish();
//                            startActivity(getIntent());
                            Log.d(this.toString(), "alert dialog dismissed");



                        }
                    });


                }


                if (position == 1)  //course
                {

                    Log.d(this.toString(),"course clicked");

                    AlertDialog alertDialog = null;

                    LinearLayout alert_dialog_layout = new LinearLayout(view.getContext());
                    alert_dialog_layout.setOrientation(LinearLayout.VERTICAL);
                    alert_dialog_layout.setPadding(20, 20, 20, 20);
                    alert_dialog_layout.setGravity(Gravity.CENTER);

                    TextView alert_dialog_title = new TextView(alert_dialog_layout.getContext());
                    alert_dialog_title.setText("Course");
                    alert_dialog_title.setTextSize(22);
                    alert_dialog_title.setPadding(40, 20, 20, 20);
                    alert_dialog_title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    alert_dialog_title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    Log.w(this.toString(),"initialising nexa bold...(requires API level 26)");
                    Typeface nexa_bold = getResources().getFont(R.font.nexa_bold);

                    Log.w(this.toString(),"initialising nexa light...(requires API level 26)");
                    Typeface nexa_light = getResources().getFont(R.font.nexa_light);

                    alert_dialog_title.setTypeface(nexa_bold);


                    final EditText editText = new EditText(alert_dialog_layout.getContext());

                    editText.setTypeface(nexa_light);
                    editText.setTextSize(20);
                    editText.setText(sharedPref.getString("Course",""));
                    editText.setPadding(20,20,20,20);
                    editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    editText.setSingleLine(true);


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
                        editText.setTextColor(getResources().getColor(R.color.black));
                        button.setTextColor(getResources().getColor(R.color.colorPrimary));

                        colors = new int[]{

                                getResources().getColor(R.color.colorPrimary)
                        };

                        Field f = null;
                        try {
                            f = TextView.class.getDeclaredField("mCursorDrawableRes");
                            f.setAccessible(true);
                            f.set(editText, R.drawable.cursor_light);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }



                    } else if (theme == AppCompatDelegate.MODE_NIGHT_YES) {
                        alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                        alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                        editText.setTextColor(getResources().getColor(R.color.white));
                        button.setTextColor(getResources().getColor(R.color.darkHighlight));

                        colors = new int[]{

                                getResources().getColor(R.color.darkHighlight)
                        };

                        Field f = null;
                        try {
                            f = TextView.class.getDeclaredField("mCursorDrawableRes");
                            f.setAccessible(true);
                            f.set(editText, R.drawable.cursor_dark);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    } else {
                        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
                        if (powerManager.isPowerSaveMode()) {
                            alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                            alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                            editText.setTextColor(getResources().getColor(R.color.white));
                            button.setTextColor(getResources().getColor(R.color.darkHighlight));

                            colors = new int[]{

                                    getResources().getColor(R.color.darkHighlight)
                            };


                            Field f = null;
                            try {
                                f = TextView.class.getDeclaredField("mCursorDrawableRes");
                                f.setAccessible(true);
                                f.set(editText, R.drawable.cursor_dark);
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                e.printStackTrace();
                            }

                        } else {
                            alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.white));
                            alert_dialog_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                            editText.setTextColor(getResources().getColor(R.color.black));
                            button.setTextColor(getResources().getColor(R.color.colorPrimary));

                            colors = new int[]{

                                    getResources().getColor(R.color.colorPrimary)
                            };

                            Field f = null;
                            try {
                                f = TextView.class.getDeclaredField("mCursorDrawableRes");
                                f.setAccessible(true);
                                f.set(editText, R.drawable.cursor_light);
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    ColorStateList colorStateList = new ColorStateList(states,colors);
                    editText.setBackgroundTintList(colorStateList);


                    Log.d(this.toString(), "adding views to alert dialog...");


                    alert_dialog_layout.addView(alert_dialog_title);
                    Log.d(this.toString(), "alert_dialog_title added to alert dialog");

                    alert_dialog_layout.addView(editText);
                    Log.d(this.toString(), "editText added to alert dialog");

                    alert_dialog_layout.addView(button);
                    Log.d(this.toString(), "button added to alert dialog");

                    Log.d(this.toString(), "building alert dialog...");


                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setView(alert_dialog_layout);
                    alertDialog = builder.create();
                    alertDialog.show();

                    Log.d(this.toString(), "alert dialog created and shown");

                    final AlertDialog finalAlertDialog = alertDialog;


                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getBaseContext());

                            if (editText.getText().toString().equals(""))
                                FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Educational Details").child("Course").setValue("-");
                            else
                                FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Educational Details").child("Course").setValue(editText.getText().toString());


                            finalAlertDialog.dismiss();
//                            finish();
//                            startActivity(getIntent());
                            Log.d(this.toString(), "alert dialog dismissed");



                        }
                    });



                }


                if (position == 2)  //score
                {

                    Log.d(this.toString(),"score clicked");

                    AlertDialog alertDialog = null;

                    LinearLayout alert_dialog_layout = new LinearLayout(view.getContext());
                    alert_dialog_layout.setOrientation(LinearLayout.VERTICAL);
                    alert_dialog_layout.setPadding(20, 20, 20, 20);
                    alert_dialog_layout.setGravity(Gravity.CENTER);

                    TextView alert_dialog_title = new TextView(alert_dialog_layout.getContext());
                    alert_dialog_title.setText("Aggregate Score");
                    alert_dialog_title.setTextSize(22);
                    alert_dialog_title.setPadding(40, 20, 20, 20);
                    alert_dialog_title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    alert_dialog_title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    Log.w(this.toString(),"initialising nexa bold...(requires API level 26)");
                    Typeface nexa_bold = getResources().getFont(R.font.nexa_bold);

                    Log.w(this.toString(),"initialising nexa light...(requires API level 26)");
                    Typeface nexa_light = getResources().getFont(R.font.nexa_light);

                    alert_dialog_title.setTypeface(nexa_bold);


                    final EditText editText = new EditText(alert_dialog_layout.getContext());

                    editText.setTypeface(nexa_light);
                    editText.setTextSize(20);
                    editText.setText(sharedPref.getString("Aggregate Score",""));
                    editText.setPadding(20,20,20,20);
                    editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    editText.setSingleLine(true);


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
                        editText.setTextColor(getResources().getColor(R.color.black));
                        button.setTextColor(getResources().getColor(R.color.colorPrimary));

                        colors = new int[]{

                                getResources().getColor(R.color.colorPrimary)
                        };

                        Field f = null;
                        try {
                            f = TextView.class.getDeclaredField("mCursorDrawableRes");
                            f.setAccessible(true);
                            f.set(editText, R.drawable.cursor_light);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }



                    } else if (theme == AppCompatDelegate.MODE_NIGHT_YES) {
                        alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                        alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                        editText.setTextColor(getResources().getColor(R.color.white));
                        button.setTextColor(getResources().getColor(R.color.darkHighlight));

                        colors = new int[]{

                                getResources().getColor(R.color.darkHighlight)
                        };

                        Field f = null;
                        try {
                            f = TextView.class.getDeclaredField("mCursorDrawableRes");
                            f.setAccessible(true);
                            f.set(editText, R.drawable.cursor_dark);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    } else {
                        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
                        if (powerManager.isPowerSaveMode()) {
                            alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                            alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                            editText.setTextColor(getResources().getColor(R.color.white));
                            button.setTextColor(getResources().getColor(R.color.darkHighlight));

                            colors = new int[]{

                                    getResources().getColor(R.color.darkHighlight)
                            };


                            Field f = null;
                            try {
                                f = TextView.class.getDeclaredField("mCursorDrawableRes");
                                f.setAccessible(true);
                                f.set(editText, R.drawable.cursor_dark);
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                e.printStackTrace();
                            }

                        } else {
                            alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.white));
                            alert_dialog_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                            editText.setTextColor(getResources().getColor(R.color.black));
                            button.setTextColor(getResources().getColor(R.color.colorPrimary));

                            colors = new int[]{

                                    getResources().getColor(R.color.colorPrimary)
                            };

                            Field f = null;
                            try {
                                f = TextView.class.getDeclaredField("mCursorDrawableRes");
                                f.setAccessible(true);
                                f.set(editText, R.drawable.cursor_light);
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    ColorStateList colorStateList = new ColorStateList(states,colors);
                    editText.setBackgroundTintList(colorStateList);


                    Log.d(this.toString(), "adding views to alert dialog...");


                    alert_dialog_layout.addView(alert_dialog_title);
                    Log.d(this.toString(), "alert_dialog_title added to alert dialog");

                    alert_dialog_layout.addView(editText);
                    Log.d(this.toString(), "editText added to alert dialog");

                    alert_dialog_layout.addView(button);
                    Log.d(this.toString(), "button added to alert dialog");

                    Log.d(this.toString(), "building alert dialog...");

                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setView(alert_dialog_layout);
                    alertDialog = builder.create();
                    alertDialog.show();

                    Log.d(this.toString(), "alert dialog created and shown");

                    final AlertDialog finalAlertDialog = alertDialog;


                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getBaseContext());

                            if (editText.getText().toString().equals(""))
                                FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Educational Details").child("Aggregate Score").setValue("-");
                            else
                                FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Educational Details").child("Aggregate Score").setValue(editText.getText().toString());


                            finalAlertDialog.dismiss();
//                            finish();
//                            startActivity(getIntent());
                            Log.d(this.toString(), "alert dialog dismissed");



                        }
                    });



                }


                if (position == 3)  //duration
                {

                    Log.d(this.toString(),"duration clicked");

                    AlertDialog alertDialog = null;

                    LinearLayout alert_dialog_layout = new LinearLayout(view.getContext());
                    alert_dialog_layout.setOrientation(LinearLayout.VERTICAL);
                    alert_dialog_layout.setPadding(20, 20, 20, 20);
                    alert_dialog_layout.setGravity(Gravity.CENTER);

                    TextView alert_dialog_title = new TextView(alert_dialog_layout.getContext());
                    alert_dialog_title.setText("Duration");
                    alert_dialog_title.setTextSize(22);
                    alert_dialog_title.setPadding(40, 20, 20, 20);
                    alert_dialog_title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    alert_dialog_title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    Log.w(this.toString(),"initialising nexa bold...(requires API level 26)");
                    Typeface nexa_bold = getResources().getFont(R.font.nexa_bold);

                    Log.w(this.toString(),"initialising nexa light...(requires API level 26)");
                    Typeface nexa_light = getResources().getFont(R.font.nexa_light);

                    alert_dialog_title.setTypeface(nexa_bold);


                    final EditText editText = new EditText(alert_dialog_layout.getContext());

                    editText.setTypeface(nexa_light);
                    editText.setTextSize(20);
                    editText.setText(sharedPref.getString("Duration",""));
                    editText.setPadding(20,20,20,20);
                    editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    editText.setSingleLine(true);


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
                        editText.setTextColor(getResources().getColor(R.color.black));
                        button.setTextColor(getResources().getColor(R.color.colorPrimary));

                        colors = new int[]{

                                getResources().getColor(R.color.colorPrimary)
                        };

                        Field f = null;
                        try {
                            f = TextView.class.getDeclaredField("mCursorDrawableRes");
                            f.setAccessible(true);
                            f.set(editText, R.drawable.cursor_light);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }



                    } else if (theme == AppCompatDelegate.MODE_NIGHT_YES) {
                        alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                        alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                        editText.setTextColor(getResources().getColor(R.color.white));
                        button.setTextColor(getResources().getColor(R.color.darkHighlight));

                        colors = new int[]{

                                getResources().getColor(R.color.darkHighlight)
                        };

                        Field f = null;
                        try {
                            f = TextView.class.getDeclaredField("mCursorDrawableRes");
                            f.setAccessible(true);
                            f.set(editText, R.drawable.cursor_dark);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    } else {
                        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
                        if (powerManager.isPowerSaveMode()) {
                            alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                            alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                            editText.setTextColor(getResources().getColor(R.color.white));
                            button.setTextColor(getResources().getColor(R.color.darkHighlight));

                            colors = new int[]{

                                    getResources().getColor(R.color.darkHighlight)
                            };


                            Field f = null;
                            try {
                                f = TextView.class.getDeclaredField("mCursorDrawableRes");
                                f.setAccessible(true);
                                f.set(editText, R.drawable.cursor_dark);
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                e.printStackTrace();
                            }

                        } else {
                            alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.white));
                            alert_dialog_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                            editText.setTextColor(getResources().getColor(R.color.black));
                            button.setTextColor(getResources().getColor(R.color.colorPrimary));

                            colors = new int[]{

                                    getResources().getColor(R.color.colorPrimary)
                            };

                            Field f = null;
                            try {
                                f = TextView.class.getDeclaredField("mCursorDrawableRes");
                                f.setAccessible(true);
                                f.set(editText, R.drawable.cursor_light);
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    ColorStateList colorStateList = new ColorStateList(states,colors);
                    editText.setBackgroundTintList(colorStateList);


                    Log.d(this.toString(), "adding views to alert dialog...");


                    alert_dialog_layout.addView(alert_dialog_title);
                    Log.d(this.toString(), "alert_dialog_title added to alert dialog");

                    alert_dialog_layout.addView(editText);
                    Log.d(this.toString(), "editText added to alert dialog");

                    alert_dialog_layout.addView(button);
                    Log.d(this.toString(), "button added to alert dialog");

                    Log.d(this.toString(), "building alert dialog...");

                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setView(alert_dialog_layout);
                    alertDialog = builder.create();
                    alertDialog.show();

                    Log.d(this.toString(), "alert dialog created and shown");

                    final AlertDialog finalAlertDialog = alertDialog;


                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getBaseContext());

                            if (editText.getText().toString().equals(""))
                                FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Educational Details").child("Duration").setValue("-");
                            else
                                FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Educational Details").child("Duration").setValue(editText.getText().toString());


                            finalAlertDialog.dismiss();
//                            finish();
//                            startActivity(getIntent());
                            Log.d(this.toString(), "alert dialog dismissed");



                        }
                    });



                }

                if (position == 4)  //backlogs
                {

                    Log.d(this.toString(),"backlogs clicked");

                    AlertDialog alertDialog = null;

                    LinearLayout alert_dialog_layout = new LinearLayout(view.getContext());
                    alert_dialog_layout.setOrientation(LinearLayout.VERTICAL);
                    alert_dialog_layout.setPadding(20, 20, 20, 20);
                    alert_dialog_layout.setGravity(Gravity.CENTER);

                    TextView alert_dialog_title = new TextView(alert_dialog_layout.getContext());
                    alert_dialog_title.setText("Backlogs");
                    alert_dialog_title.setTextSize(22);
                    alert_dialog_title.setPadding(40, 20, 20, 20);
                    alert_dialog_title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    alert_dialog_title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    Log.w(this.toString(),"initialising nexa bold...(requires API level 26)");
                    Typeface nexa_bold = getResources().getFont(R.font.nexa_bold);

                    Log.w(this.toString(),"initialising nexa light...(requires API level 26)");
                    Typeface nexa_light = getResources().getFont(R.font.nexa_light);

                    alert_dialog_title.setTypeface(nexa_bold);


                    final EditText editText = new EditText(alert_dialog_layout.getContext());

                    editText.setTypeface(nexa_light);
                    editText.setTextSize(20);
                    editText.setText(sharedPref.getString("Backlogs",""));
                    editText.setPadding(20,20,20,20);
                    editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    editText.setSingleLine(true);


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
                        editText.setTextColor(getResources().getColor(R.color.black));
                        button.setTextColor(getResources().getColor(R.color.colorPrimary));

                        colors = new int[]{

                                getResources().getColor(R.color.colorPrimary)
                        };

                        Field f = null;
                        try {
                            f = TextView.class.getDeclaredField("mCursorDrawableRes");
                            f.setAccessible(true);
                            f.set(editText, R.drawable.cursor_light);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }



                    } else if (theme == AppCompatDelegate.MODE_NIGHT_YES) {
                        alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                        alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                        editText.setTextColor(getResources().getColor(R.color.white));
                        button.setTextColor(getResources().getColor(R.color.darkHighlight));

                        colors = new int[]{

                                getResources().getColor(R.color.darkHighlight)
                        };

                        Field f = null;
                        try {
                            f = TextView.class.getDeclaredField("mCursorDrawableRes");
                            f.setAccessible(true);
                            f.set(editText, R.drawable.cursor_dark);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    } else {
                        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
                        if (powerManager.isPowerSaveMode()) {
                            alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                            alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                            editText.setTextColor(getResources().getColor(R.color.white));
                            button.setTextColor(getResources().getColor(R.color.darkHighlight));

                            colors = new int[]{

                                    getResources().getColor(R.color.darkHighlight)
                            };


                            Field f = null;
                            try {
                                f = TextView.class.getDeclaredField("mCursorDrawableRes");
                                f.setAccessible(true);
                                f.set(editText, R.drawable.cursor_dark);
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                e.printStackTrace();
                            }

                        } else {
                            alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.white));
                            alert_dialog_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                            editText.setTextColor(getResources().getColor(R.color.black));
                            button.setTextColor(getResources().getColor(R.color.colorPrimary));

                            colors = new int[]{

                                    getResources().getColor(R.color.colorPrimary)
                            };

                            Field f = null;
                            try {
                                f = TextView.class.getDeclaredField("mCursorDrawableRes");
                                f.setAccessible(true);
                                f.set(editText, R.drawable.cursor_light);
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    ColorStateList colorStateList = new ColorStateList(states,colors);
                    editText.setBackgroundTintList(colorStateList);


                    Log.d(this.toString(), "adding views to alert dialog...");


                    alert_dialog_layout.addView(alert_dialog_title);
                    Log.d(this.toString(), "alert_dialog_title added to alert dialog");

                    alert_dialog_layout.addView(editText);
                    Log.d(this.toString(), "editText added to alert dialog");

                    alert_dialog_layout.addView(button);
                    Log.d(this.toString(), "button added to alert dialog");

                    Log.d(this.toString(), "building alert dialog...");

                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setView(alert_dialog_layout);
                    alertDialog = builder.create();
                    alertDialog.show();

                    Log.d(this.toString(), "alert dialog created and shown");

                    final AlertDialog finalAlertDialog = alertDialog;


                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getBaseContext());

                            if (editText.getText().toString().equals(""))
                                FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Educational Details").child("Backlogs").setValue("-");
                            else
                                FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Educational Details").child("Backlogs").setValue(editText.getText().toString());


                            finalAlertDialog.dismiss();
//                            finish();
//                            startActivity(getIntent());
                            Log.d(this.toString(), "alert dialog dismissed");



                        }
                    });



                }




            }
        });




    }
}
