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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Step2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);


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



        action_bar_title.setText("Research");
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
            ScrollView background_layout = decor.findViewById(R.id.background_layout);
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
                Log.d(this.toString(),"action bar icons background color set to dark" +
                        " background");


                //change status bar colour
                window.setStatusBarColor(getResources().getColor(R.color.darkBackground));
                Log.d(this.toString(),"status bar color set to dark background");

                //change action bar colour
                action_bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkBackground)));
                Log.d(this.toString(),"action bar color set to dark background");

                // set status bar contrast
                View decor = window.getDecorView();
                ScrollView background_layout = decor.findViewById(R.id.background_layout);
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
                alert_dialog_title.setText("Help");
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

        final Button button_step2 = findViewById(R.id.button_step2);

        TextView step2_intro = findViewById(R.id.step2_intro);
        TextView step2_body1 = findViewById(R.id.step2_body1);
        TextView step2_body2 = findViewById(R.id.step2_body2);
        TextView step2_body3 = findViewById(R.id.step2_body3);
        TextView step2_body4 = findViewById(R.id.step2_body4);
        TextView step2_body5 = findViewById(R.id.step2_body5);
        TextView step2_body6 = findViewById(R.id.step2_body6);


        TextView step2_heading1 = findViewById(R.id.step2_heading1);
        TextView step2_heading2 = findViewById(R.id.step2_heading2);
        TextView step2_heading3 = findViewById(R.id.step2_heading3);
        TextView step2_heading4 = findViewById(R.id.step2_heading4);
        TextView step2_heading5 = findViewById(R.id.step2_heading5);
        TextView step2_heading6 = findViewById(R.id.step2_heading6);

        if (theme == AppCompatDelegate.MODE_NIGHT_NO)
        {
            button_step2.setTextColor(getResources().getColor(R.color.white));
            Log.d(this.toString(), "text color of button_step2 set to white");

            step2_intro.setTextColor(getResources().getColor(R.color.black));
            Log.d(this.toString(),"text color of step2_intro set to black");

            step2_body1.setTextColor(getResources().getColor(R.color.black));
            Log.d(this.toString(),"text color of step2_body1 set to black");

            step2_body2.setTextColor(getResources().getColor(R.color.black));
            Log.d(this.toString(),"text color of step2_body2 set to black");

            step2_body3.setTextColor(getResources().getColor(R.color.black));
            Log.d(this.toString(),"text color of step2_body3 set to black");

            step2_body4.setTextColor(getResources().getColor(R.color.black));
            Log.d(this.toString(),"text color of step2_body4 set to black");

            step2_body5.setTextColor(getResources().getColor(R.color.black));
            Log.d(this.toString(),"text color of step2_body5 set to black");

            step2_body6.setTextColor(getResources().getColor(R.color.black));
            Log.d(this.toString(),"text color of step2_body6 set to black");

            step2_heading1.setTextColor(getResources().getColor(R.color.colorPrimary));
            Log.d(this.toString(),"text color of step2_heading1 set to colorPrimary");

            step2_heading2.setTextColor(getResources().getColor(R.color.colorPrimary));
            Log.d(this.toString(),"text color of step2_heading2 set to colorPrimary");

            step2_heading3.setTextColor(getResources().getColor(R.color.colorPrimary));
            Log.d(this.toString(),"text color of step2_heading3 set to colorPrimary");

            step2_heading4.setTextColor(getResources().getColor(R.color.colorPrimary));
            Log.d(this.toString(),"text color of step2_heading4 set to colorPrimary");

            step2_heading5.setTextColor(getResources().getColor(R.color.colorPrimary));
            Log.d(this.toString(),"text color of step2_heading5 set to colorPrimary");

            step2_heading6.setTextColor(getResources().getColor(R.color.colorPrimary));
            Log.d(this.toString(),"text color of step2_heading6 set to colorPrimary");
        }
        else if (theme == AppCompatDelegate.MODE_NIGHT_YES)
        {
            button_step2.setTextColor(getResources().getColor(R.color.darkBackground));
            Log.d(this.toString(), "text color of button_step2 set to darkBackground");

            step2_intro.setTextColor(getResources().getColor(R.color.white));
            Log.d(this.toString(),"text color of step2_intro set to white");

            step2_body1.setTextColor(getResources().getColor(R.color.white));
            Log.d(this.toString(),"text color of step2_body1 set to white");

            step2_body2.setTextColor(getResources().getColor(R.color.white));
            Log.d(this.toString(),"text color of step2_body2 set to white");

            step2_body3.setTextColor(getResources().getColor(R.color.white));
            Log.d(this.toString(),"text color of step2_body3 set to white");

            step2_body4.setTextColor(getResources().getColor(R.color.white));
            Log.d(this.toString(),"text color of step2_body4 set to white");

            step2_body5.setTextColor(getResources().getColor(R.color.white));
            Log.d(this.toString(),"text color of step2_body5 set to white");

            step2_body6.setTextColor(getResources().getColor(R.color.white));
            Log.d(this.toString(),"text color of step2_body6 set to white");

            step2_heading1.setTextColor(getResources().getColor(R.color.darkHighlight));
            Log.d(this.toString(),"text color of step2_heading1 set to darkHighlight");

            step2_heading2.setTextColor(getResources().getColor(R.color.darkHighlight));
            Log.d(this.toString(),"text color of step2_heading2 set to darkHighlight");

            step2_heading3.setTextColor(getResources().getColor(R.color.darkHighlight));
            Log.d(this.toString(),"text color of step2_heading3 set to darkHighlight");

            step2_heading4.setTextColor(getResources().getColor(R.color.darkHighlight));
            Log.d(this.toString(),"text color of step2_heading4 set to darkHighlight");

            step2_heading5.setTextColor(getResources().getColor(R.color.darkHighlight));
            Log.d(this.toString(),"text color of step2_heading5 set to darkHighlight");

            step2_heading6.setTextColor(getResources().getColor(R.color.darkHighlight));
            Log.d(this.toString(),"text color of step2_heading6 set to darkHighlight");
        }
        else
        {
            PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
            if (powerManager.isPowerSaveMode())
            {
                button_step2.setTextColor(getResources().getColor(R.color.darkBackground));
                Log.d(this.toString(), "text color of button_step2 set to darkBackground");

                step2_intro.setTextColor(getResources().getColor(R.color.white));
                Log.d(this.toString(),"text color of step2_intro set to white");

                step2_body1.setTextColor(getResources().getColor(R.color.white));
                Log.d(this.toString(),"text color of step2_body1 set to white");

                step2_body2.setTextColor(getResources().getColor(R.color.white));
                Log.d(this.toString(),"text color of step2_body2 set to white");

                step2_body3.setTextColor(getResources().getColor(R.color.white));
                Log.d(this.toString(),"text color of step2_body3 set to white");

                step2_body4.setTextColor(getResources().getColor(R.color.white));
                Log.d(this.toString(),"text color of step2_body4 set to white");

                step2_body5.setTextColor(getResources().getColor(R.color.white));
                Log.d(this.toString(),"text color of step2_body5 set to white");

                step2_body6.setTextColor(getResources().getColor(R.color.white));
                Log.d(this.toString(),"text color of step2_body6 set to white");

                step2_heading1.setTextColor(getResources().getColor(R.color.darkHighlight));
                Log.d(this.toString(),"text color of step2_heading1 set to darkHighlight");

                step2_heading2.setTextColor(getResources().getColor(R.color.darkHighlight));
                Log.d(this.toString(),"text color of step2_heading2 set to darkHighlight");

                step2_heading3.setTextColor(getResources().getColor(R.color.darkHighlight));
                Log.d(this.toString(),"text color of step2_heading3 set to darkHighlight");

                step2_heading4.setTextColor(getResources().getColor(R.color.darkHighlight));
                Log.d(this.toString(),"text color of step2_heading4 set to darkHighlight");

                step2_heading5.setTextColor(getResources().getColor(R.color.darkHighlight));
                Log.d(this.toString(),"text color of step2_heading5 set to darkHighlight");

                step2_heading6.setTextColor(getResources().getColor(R.color.darkHighlight));
                Log.d(this.toString(),"text color of step2_heading6 set to darkHighlight");
            }
            else
            {
                button_step2.setTextColor(getResources().getColor(R.color.white));
                Log.d(this.toString(), "text color of button_step2 set to white");

                step2_intro.setTextColor(getResources().getColor(R.color.black));
                Log.d(this.toString(),"text color of step2_intro set to black");

                step2_body1.setTextColor(getResources().getColor(R.color.black));
                Log.d(this.toString(),"text color of step2_body1 set to black");

                step2_body2.setTextColor(getResources().getColor(R.color.black));
                Log.d(this.toString(),"text color of step2_body2 set to black");

                step2_body3.setTextColor(getResources().getColor(R.color.black));
                Log.d(this.toString(),"text color of step2_body3 set to black");

                step2_body4.setTextColor(getResources().getColor(R.color.black));
                Log.d(this.toString(),"text color of step2_body4 set to black");

                step2_body5.setTextColor(getResources().getColor(R.color.black));
                Log.d(this.toString(),"text color of step2_body5 set to black");

                step2_body6.setTextColor(getResources().getColor(R.color.black));
                Log.d(this.toString(),"text color of step2_body6 set to black");

                step2_heading1.setTextColor(getResources().getColor(R.color.colorPrimary));
                Log.d(this.toString(),"text color of step2_heading1 set to colorPrimary");

                step2_heading2.setTextColor(getResources().getColor(R.color.colorPrimary));
                Log.d(this.toString(),"text color of step2_heading2 set to colorPrimary");

                step2_heading3.setTextColor(getResources().getColor(R.color.colorPrimary));
                Log.d(this.toString(),"text color of step2_heading3 set to colorPrimary");

                step2_heading4.setTextColor(getResources().getColor(R.color.colorPrimary));
                Log.d(this.toString(),"text color of step2_heading4 set to colorPrimary");

                step2_heading5.setTextColor(getResources().getColor(R.color.colorPrimary));
                Log.d(this.toString(),"text color of step2_heading5 set to colorPrimary");

                step2_heading6.setTextColor(getResources().getColor(R.color.colorPrimary));
                Log.d(this.toString(),"text color of step2_heading6 set to colorPrimary");
            }
        }



        button_step2.setBackgroundTintList(colorStateList);
        Log.d(this.toString(), "background color of button_step2 set");

        //Google sign in and firebase database

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        final DatabaseReference step2_reference = databaseReference.child("Data").child(account.getId()).child("Steps").child("Step 2");



        button_step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(this.toString(),"button_step2 clicked");

                step2_reference.setValue(button_step2.getText());

            }
        });


        step2_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d(this.toString(),"data at step2_reference changed");

                String value = dataSnapshot.getValue(String.class);

                if (value == null){
                    Log.e(this.toString(), "data at step2_reference is null");

                    button_step2.setText(getResources().getString(R.string.button_done));
                }

                else
                {

                    Log.d(this.toString(),"data at step2_reference is - "+value);

                    if (value.equals(getResources().getString(R.string.button_done)))
                    {
                        button_step2.setText(getResources().getString(R.string.button_pending));
                    }
                    else
                    {
                        button_step2.setText(getResources().getString(R.string.button_done));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e(this.toString(),databaseError.getMessage());

            }
        });



    }
}
