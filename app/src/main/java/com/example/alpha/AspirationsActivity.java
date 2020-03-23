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
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
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

public class AspirationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspirations);

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



        action_bar_title.setText("Aspirations");
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
            @Override
            public void onClick(View v) {

                Log.i(this.toString(),"help button pressed");

                AlertDialog alertDialog = null;

                LinearLayout alert_dialog_layout = new LinearLayout(v.getContext());
                alert_dialog_layout.setOrientation(LinearLayout.VERTICAL);
                alert_dialog_layout.setPadding(20, 20, 20, 20);
                alert_dialog_layout.setGravity(Gravity.CENTER);

                TextView alert_dialog_title = new TextView(alert_dialog_layout.getContext());
                alert_dialog_title.setText("Aspirations - Help");
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
                textView.setText("This page lets you view and edit your aspirations in accordance with your desired goals.\n\nFor example, aspirations might look like - \n\nDream University: Georgia Tech\nDream Program: MSHCI\nDream Semester: Fall 2021\n\nCarefully think and choose your Dream University, Dream Program and corresponding semester in which you wish to enroll yourself. Aspirations keep you hooked to achieve your dream goal.\n\nGood luck!");
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

        final ListView list_view_aspirations = findViewById(R.id.list_view_aspirations);
        Log.d(this.toString(), "list_view_aspirations initialised");

        final ArrayList<AspirationsItem> aspirationsItemArrayList = new ArrayList<>();
        Log.d(this.toString(), "aspirationsItemArrayList initialised");

        //set the items of list view

        aspirationsItemArrayList.add(new AspirationsItem("DREAM UNIVERSITY",sharedPref.getString("Dream University","-")));
        Log.d(this.toString(),"item added to accountItemArrayList - dream university");

        aspirationsItemArrayList.add(new AspirationsItem("DREAM PROGRAM",sharedPref.getString("Dream Program","-")));
        Log.d(this.toString(),"item added to accountItemArrayList - dream program");

        aspirationsItemArrayList.add(new AspirationsItem("DREAM SEMESTER",sharedPref.getString("Dream Semester","-")));
        Log.d(this.toString(),"item added to accountItemArrayList - dream semester");



        AspirationsAdapter aspirationsAdapter = new AspirationsAdapter(aspirationsItemArrayList, getBaseContext());
        Log.d(this.toString(),"aspirationsAdapter created");

        list_view_aspirations.setAdapter(aspirationsAdapter);
        Log.d(this.toString(),"aspirationsAdapter set to list_view_aspirations");



        //Google sign in and firebase database

        Log.d(this.toString(),"getting last signed in account...");
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        Log.d(this.toString(),"getting databaseReference...");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        Log.d(this.toString(),"getting dream_university_reference...");
        final DatabaseReference dream_university_reference = databaseReference.child("Data").child(account.getId()).child("Aspirations").child("Dream University");

        Log.d(this.toString(),"getting dream_program_reference...");
        final DatabaseReference dream_program_reference = databaseReference.child("Data").child(account.getId()).child("Aspirations").child("Dream Program");

        Log.d(this.toString(),"getting dream_semester_reference...");
        final DatabaseReference dream_semester_reference = databaseReference.child("Data").child(account.getId()).child("Aspirations").child("Dream Semester");



        dream_university_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d(this.toString(),"data at dream_university_reference changed");

                String value = dataSnapshot.getValue(String.class);

                if (value == null){
                    Log.e(this.toString(), "data at dream_university_reference is null");
                }

                else {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Dream University", value);
                    editor.apply();

                    Log.d(this.toString(), "data at dream_university_reference is " + value);


                    String temp_program = aspirationsItemArrayList.get(1).value;
                    Log.d(this.toString(), "temp_program is " + temp_program);

                    String temp_semester = aspirationsItemArrayList.get(2).value;
                    Log.d(this.toString(), "temp_semester is " + temp_semester);

                    aspirationsItemArrayList.clear();
                    Log.d(this.toString(), "aspirationsItemArrayList cleared");

                    aspirationsItemArrayList.add(new AspirationsItem("DREAM UNIVERSITY",sharedPref.getString("Dream University","-")));
                    Log.d(this.toString(),"item added to accountItemArrayList - dream university");

                    aspirationsItemArrayList.add(new AspirationsItem("DREAM PROGRAM",temp_program));
                    Log.d(this.toString(),"item added to accountItemArrayList - dream program");

                    aspirationsItemArrayList.add(new AspirationsItem("DREAM SEMESTER",temp_semester));
                    Log.d(this.toString(),"item added to accountItemArrayList - dream semester");



                    AspirationsAdapter temp_adapter = new AspirationsAdapter(aspirationsItemArrayList, getBaseContext());
                    Log.d(this.toString(),"temp_adapter created");

                    list_view_aspirations.setAdapter(temp_adapter);
                    Log.d(this.toString(),"temp_adapter set to list_view_aspirations");


                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e(this.toString(),databaseError.getMessage());

            }
        });

        dream_program_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d(this.toString(),"data at dream_program_reference changed");

                String value = dataSnapshot.getValue(String.class);

                if (value == null){
                    Log.e(this.toString(), "data at dream_program_reference is null");

                }

                else {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Dream Program", value);
                    editor.apply();

                    Log.d(this.toString(), "data at dream_program_reference is " + value);


                    String temp_university = aspirationsItemArrayList.get(0).value;
                    Log.d(this.toString(), "temp_university is " + temp_university);

                    String temp_semester = aspirationsItemArrayList.get(2).value;
                    Log.d(this.toString(), "temp_semester is " + temp_semester);

                    aspirationsItemArrayList.clear();
                    Log.d(this.toString(), "aspirationsItemArrayList cleared");


                    aspirationsItemArrayList.add(new AspirationsItem("DREAM UNIVERSITY",temp_university));
                    Log.d(this.toString(),"item added to accountItemArrayList - dream university");

                    aspirationsItemArrayList.add(new AspirationsItem("DREAM PROGRAM",sharedPref.getString("Dream Program","-")));
                    Log.d(this.toString(),"item added to accountItemArrayList - dream program");

                    aspirationsItemArrayList.add(new AspirationsItem("DREAM SEMESTER",temp_semester));
                    Log.d(this.toString(),"item added to accountItemArrayList - dream semester");


                    AspirationsAdapter temp_adapter = new AspirationsAdapter(aspirationsItemArrayList, getBaseContext());
                    Log.d(this.toString(),"temp_adapter created");

                    list_view_aspirations.setAdapter(temp_adapter);
                    Log.d(this.toString(),"temp_adapter set to list_view_aspirations");


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e(this.toString(),databaseError.getMessage());

            }
        });


        dream_semester_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d(this.toString(),"data at dream_semester_reference changed");

                String value = dataSnapshot.getValue(String.class);

                if (value == null){
                    Log.e(this.toString(), "data at dream_semester_reference is null");
                }

                else {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Dream Semester", value);
                    editor.apply();

                    Log.d(this.toString(), "data at dream_semester_reference is " + value);


                    String temp_university = aspirationsItemArrayList.get(0).value;
                    Log.d(this.toString(), "temp_university is " + temp_university);

                    String temp_program = aspirationsItemArrayList.get(1).value;
                    Log.d(this.toString(), "temp_program is " + temp_program);

                    aspirationsItemArrayList.clear();
                    Log.d(this.toString(), "aspirationsItemArrayList cleared");

                    aspirationsItemArrayList.add(new AspirationsItem("DREAM UNIVERSITY",temp_university));
                    Log.d(this.toString(),"item added to accountItemArrayList - dream university");

                    aspirationsItemArrayList.add(new AspirationsItem("DREAM PROGRAM",temp_program));
                    Log.d(this.toString(),"item added to accountItemArrayList - dream program");

                    aspirationsItemArrayList.add(new AspirationsItem("DREAM SEMESTER",sharedPref.getString("Dream Semester","-")));
                    Log.d(this.toString(),"item added to accountItemArrayList - dream semester");



                    AspirationsAdapter temp_adapter = new AspirationsAdapter(aspirationsItemArrayList, getBaseContext());
                    Log.d(this.toString(),"temp_adapter created");

                    list_view_aspirations.setAdapter(temp_adapter);
                    Log.d(this.toString(),"temp_adapter set to list_view_aspirations");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e(this.toString(),databaseError.getMessage());

            }
        });




        list_view_aspirations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (position == 0) //dream university
                {

                    Log.d(this.toString(),"dream university clicked");

                    AlertDialog alertDialog = null;

                    LinearLayout alert_dialog_layout = new LinearLayout(view.getContext());
                    alert_dialog_layout.setOrientation(LinearLayout.VERTICAL);
                    alert_dialog_layout.setPadding(20, 20, 20, 20);
                    alert_dialog_layout.setGravity(Gravity.CENTER);

                    TextView alert_dialog_title = new TextView(alert_dialog_layout.getContext());
                    alert_dialog_title.setText("Dream University");
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
                    editText.setText(sharedPref.getString("Dream University",""));
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
                                FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Aspirations").child("Dream University").setValue("-");
                            else
                                FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Aspirations").child("Dream University").setValue(editText.getText().toString());


                            finalAlertDialog.dismiss();
//                            finish();
//                            startActivity(getIntent());

                            Log.d(this.toString(), "alert dialog dismissed");


                        }
                    });


                }


                    if (position == 1)  //dream program
                    {

                        Log.d(this.toString(),"dream program clicked");

                        AlertDialog alertDialog = null;

                    LinearLayout alert_dialog_layout = new LinearLayout(view.getContext());
                    alert_dialog_layout.setOrientation(LinearLayout.VERTICAL);
                    alert_dialog_layout.setPadding(20, 20, 20, 20);
                    alert_dialog_layout.setGravity(Gravity.CENTER);

                    TextView alert_dialog_title = new TextView(alert_dialog_layout.getContext());
                    alert_dialog_title.setText("Dream Program");
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
                    editText.setText(sharedPref.getString("Dream Program",""));
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
                                FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Aspirations").child("Dream Program").setValue("-");
                            else
                                FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Aspirations").child("Dream Program").setValue(editText.getText().toString());


                            finalAlertDialog.dismiss();
//                            finish();
//                            startActivity(getIntent());

                            Log.d(this.toString(), "alert dialog dismissed");


                        }
                    });



                }


                    if (position == 2) //dream semester
                    {

                        Log.d(this.toString(),"dream semester clicked");

                        AlertDialog alertDialog = null;

                        LinearLayout alert_dialog_layout = new LinearLayout(view.getContext());
                        alert_dialog_layout.setOrientation(LinearLayout.VERTICAL);
                        alert_dialog_layout.setPadding(20, 20, 20, 20);
                        alert_dialog_layout.setGravity(Gravity.CENTER);

                        TextView alert_dialog_title = new TextView(alert_dialog_layout.getContext());
                        alert_dialog_title.setText("Dream Semester");
                        alert_dialog_title.setTextSize(22);
                        alert_dialog_title.setPadding(40, 20, 20, 20);
                        alert_dialog_title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        alert_dialog_title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                        Log.w(this.toString(),"initialising nexa bold...(requires API level 26)");
                        Typeface nexa_bold = getResources().getFont(R.font.nexa_bold);

                        Log.w(this.toString(),"initialising nexa light...(requires API level 26)");
                        Typeface nexa_light = getResources().getFont(R.font.nexa_light);

                        alert_dialog_title.setTypeface(nexa_bold);


                        RadioGroup radioGroup = new RadioGroup(alert_dialog_layout.getContext());
                        radioGroup.setOrientation(RadioGroup.VERTICAL);
                        radioGroup.setPadding(20, 20, 20, 40);

                        final RadioButton radio_button_spring = new RadioButton(alert_dialog_layout.getContext());
                        final RadioButton radio_button_fall = new RadioButton(alert_dialog_layout.getContext());

                        radio_button_spring.setText("Spring");
                        radio_button_fall.setText("Fall");

                        radio_button_spring.setTextSize(20);
                        radio_button_fall.setTextSize(20);


                        radio_button_spring.setTypeface(nexa_light);
                        radio_button_fall.setTypeface(nexa_light);


                        radio_button_spring.setPadding(20, 20, 20, 20);
                        radio_button_fall.setPadding(20, 20, 20, 20);

                        radioGroup.addView(radio_button_spring);
                        radioGroup.addView(radio_button_fall);


                        int[][] states = new int[][]{


                                new int[]{android.R.attr.state_checked}, // checked
                                new int[]{-android.R.attr.state_checked}  // unchecked
                        };

                        int[] colors = new int[]{

                                getResources().getColor(R.color.checkedRadioButtonLight),
                                getResources().getColor(R.color.uncheckedRadioButton)
                        };



                        TextView button = new TextView(alert_dialog_layout.getContext());
                        button.setPadding(20,20,20,20);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(10,10,10,10);
                        button.setLayoutParams(layoutParams);
                        button.setTextSize(15);
                        button.setTypeface(nexa_bold);
                        button.setText("SAVE");


                        if (theme == AppCompatDelegate.MODE_NIGHT_NO) {
                            alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.white));
                            alert_dialog_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                            button.setTextColor(getResources().getColor(R.color.colorPrimary));

                            radio_button_spring.setTextColor(getResources().getColor(R.color.black));
                            radio_button_fall.setTextColor(getResources().getColor(R.color.black));

                            colors= new int[]{

                                    getResources().getColor(R.color.checkedRadioButtonLight),
                                    getResources().getColor(R.color.uncheckedRadioButton)
                            };




                        } else if (theme == AppCompatDelegate.MODE_NIGHT_YES) {
                            alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                            alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                            button.setTextColor(getResources().getColor(R.color.darkHighlight));

                            radio_button_spring.setTextColor(getResources().getColor(R.color.white));
                            radio_button_fall.setTextColor(getResources().getColor(R.color.white));


                            colors = new int[]{

                                    getResources().getColor(R.color.checkedRadioButtonDark),
                                    getResources().getColor(R.color.uncheckedRadioButton)
                            };



                        } else {
                            PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
                            if (powerManager.isPowerSaveMode()) {
                                alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                                alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                                button.setTextColor(getResources().getColor(R.color.darkHighlight));

                                radio_button_spring.setTextColor(getResources().getColor(R.color.white));
                                radio_button_fall.setTextColor(getResources().getColor(R.color.white));

                                colors = new int[]{

                                        getResources().getColor(R.color.checkedRadioButtonDark),
                                        getResources().getColor(R.color.uncheckedRadioButton)
                                };




                            } else {
                                alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.white));
                                alert_dialog_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                                button.setTextColor(getResources().getColor(R.color.colorPrimary));

                                radio_button_spring.setTextColor(getResources().getColor(R.color.black));
                                radio_button_fall.setTextColor(getResources().getColor(R.color.black));

                                colors= new int[]{

                                        getResources().getColor(R.color.checkedRadioButtonLight),
                                        getResources().getColor(R.color.uncheckedRadioButton)
                                };


                            }
                        }

                        ColorStateList colorStateList = new ColorStateList(states,colors);

                        radio_button_spring.setButtonTintList(colorStateList);
                        radio_button_fall.setButtonTintList(colorStateList);


                        String dream_semester = sharedPref.getString("Dream Semester","-");

                        if (dream_semester.equals("Spring"))    radio_button_spring.setChecked(true);
                        else if (dream_semester.equals("Fall")) radio_button_fall.setChecked(true);

                        Log.d(this.toString(), "adding views to alert dialog...");


                        alert_dialog_layout.addView(alert_dialog_title);
                        Log.d(this.toString(), "alert_dialog_title added to alert dialog");

                        alert_dialog_layout.addView(radioGroup);
                        Log.d(this.toString(), "radioGroup added to alert dialog");

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

                                if(radio_button_spring.isChecked())
                                    FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Aspirations").child("Dream Semester").setValue("Spring");
                                else if (radio_button_fall.isChecked())
                                    FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Aspirations").child("Dream Semester").setValue("Fall");
                                else
                                    FirebaseDatabase.getInstance().getReference().child("Data").child(account.getId()).child("Aspirations").child("Dream Semester").setValue("-");


                                finalAlertDialog.dismiss();
//                                finish();
//                                startActivity(getIntent());

                                Log.d(this.toString(), "alert dialog dismissed");


                            }
                        });






                    }
            }
        });


    }
}
