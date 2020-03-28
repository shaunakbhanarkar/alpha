package com.example.alpha;

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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Objects;

public class GREGeneralComputerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_r_e_general_computer);


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



        action_bar_title.setText("Computer-delivered Test");
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
                alert_dialog_title.setText("GRE - Help");
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
        TextView gre_general_computer_sections_title = findViewById(R.id.gre_general_computer_sections_title);
        TextView gre_general_computer_order_title = findViewById(R.id.gre_general_computer_order_title);
        TextView gre_general_computer_unidentified_title = findViewById(R.id.gre_general_computer_unidentified_title);
        TextView gre_general_computer_break_title = findViewById(R.id.gre_general_computer_break_title);
        TextView gre_general_computer_time_title = findViewById(R.id.gre_general_computer_time_title);
        TextView gre_general_computer_features_title = findViewById(R.id.gre_general_computer_features_title);

        TextView gre_general_computer_sections_details = findViewById(R.id.gre_general_computer_sections_details);
        TextView gre_general_computer_order_details = findViewById(R.id.gre_general_computer_order_details);
        TextView gre_general_computer_unidentified_details = findViewById(R.id.gre_general_computer_unidentified_details);
        TextView gre_general_computer_break_details = findViewById(R.id.gre_general_computer_break_details);
        TextView gre_general_computer_time_details = findViewById(R.id.gre_general_computer_time_details);
        TextView gre_general_computer_features_details = findViewById(R.id.gre_general_computer_features_details);



        if (theme == AppCompatDelegate.MODE_NIGHT_NO)
        {
            gre_general_computer_sections_title.setTextColor(getResources().getColor(R.color.colorPrimary));
            Log.d(this.toString(),"text color of gre_general_computer_sections_title set to colorPrimary");

            gre_general_computer_order_title.setTextColor(getResources().getColor(R.color.colorPrimary));
            Log.d(this.toString(),"text color of gre_general_computer_order_title set to colorPrimary");

            gre_general_computer_unidentified_title.setTextColor(getResources().getColor(R.color.colorPrimary));
            Log.d(this.toString(),"text color of gre_general_computer_unidentified_title set to colorPrimary");

            gre_general_computer_break_title.setTextColor(getResources().getColor(R.color.colorPrimary));
            Log.d(this.toString(),"text color of gre_general_computer_break_title set to colorPrimary");

            gre_general_computer_time_title.setTextColor(getResources().getColor(R.color.colorPrimary));
            Log.d(this.toString(),"text color of gre_general_computer_time_title set to colorPrimary");

            gre_general_computer_features_title.setTextColor(getResources().getColor(R.color.colorPrimary));
            Log.d(this.toString(),"text color of gre_general_computer_features_title set to colorPrimary");

            gre_general_computer_sections_details.setTextColor(getResources().getColor(R.color.black));
            Log.d(this.toString(),"text color of gre_general_computer_sections_details set to black");

            gre_general_computer_order_details.setTextColor(getResources().getColor(R.color.black));
            Log.d(this.toString(),"text color of gre_general_computer_order_details set to black");

            gre_general_computer_unidentified_details.setTextColor(getResources().getColor(R.color.black));
            Log.d(this.toString(),"text color of gre_general_computer_unidentified_details set to black");

            gre_general_computer_break_details.setTextColor(getResources().getColor(R.color.black));
            Log.d(this.toString(),"text color of gre_general_computer_break_details set to black");

            gre_general_computer_time_details.setTextColor(getResources().getColor(R.color.black));
            Log.d(this.toString(),"text color of gre_general_computer_time_details set to black");

            gre_general_computer_features_details.setTextColor(getResources().getColor(R.color.black));
            Log.d(this.toString(),"text color of gre_general_computer_features_details set to black");
        }
        else if (theme == AppCompatDelegate.MODE_NIGHT_YES)
        {
            gre_general_computer_sections_title.setTextColor(getResources().getColor(R.color.darkHighlight));
            Log.d(this.toString(),"text color of gre_general_computer_sections_title set to darkHighlight");

            gre_general_computer_order_title.setTextColor(getResources().getColor(R.color.darkHighlight));
            Log.d(this.toString(),"text color of gre_general_computer_order_title set to darkHighlight");

            gre_general_computer_unidentified_title.setTextColor(getResources().getColor(R.color.darkHighlight));
            Log.d(this.toString(),"text color of gre_general_computer_unidentified_title set to darkHighlight");

            gre_general_computer_break_title.setTextColor(getResources().getColor(R.color.darkHighlight));
            Log.d(this.toString(),"text color of gre_general_computer_break_title set to darkHighlight");

            gre_general_computer_time_title.setTextColor(getResources().getColor(R.color.darkHighlight));
            Log.d(this.toString(),"text color of gre_general_computer_time_title set to darkHighlight");

            gre_general_computer_features_title.setTextColor(getResources().getColor(R.color.darkHighlight));
            Log.d(this.toString(),"text color of gre_general_computer_features_title set to darkHighlight");

            gre_general_computer_sections_details.setTextColor(getResources().getColor(R.color.white));
            Log.d(this.toString(),"text color of gre_general_computer_sections_details set to white");

            gre_general_computer_order_details.setTextColor(getResources().getColor(R.color.white));
            Log.d(this.toString(),"text color of gre_general_computer_order_details set to white");

            gre_general_computer_unidentified_details.setTextColor(getResources().getColor(R.color.white));
            Log.d(this.toString(),"text color of gre_general_computer_unidentified_details set to white");

            gre_general_computer_break_details.setTextColor(getResources().getColor(R.color.white));
            Log.d(this.toString(),"text color of gre_general_computer_break_details set to white");

            gre_general_computer_time_details.setTextColor(getResources().getColor(R.color.white));
            Log.d(this.toString(),"text color of gre_general_computer_time_details set to white");

            gre_general_computer_features_details.setTextColor(getResources().getColor(R.color.white));
            Log.d(this.toString(),"text color of gre_general_computer_features_details set to white");
        }
        else
        {
            PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
            if (powerManager.isPowerSaveMode())
            {
                gre_general_computer_sections_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                Log.d(this.toString(),"text color of gre_general_computer_sections_title set to darkHighlight");

                gre_general_computer_order_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                Log.d(this.toString(),"text color of gre_general_computer_order_title set to darkHighlight");

                gre_general_computer_unidentified_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                Log.d(this.toString(),"text color of gre_general_computer_unidentified_title set to darkHighlight");

                gre_general_computer_break_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                Log.d(this.toString(),"text color of gre_general_computer_break_title set to darkHighlight");

                gre_general_computer_time_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                Log.d(this.toString(),"text color of gre_general_computer_time_title set to darkHighlight");

                gre_general_computer_features_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                Log.d(this.toString(),"text color of gre_general_computer_features_title set to darkHighlight");

                gre_general_computer_sections_details.setTextColor(getResources().getColor(R.color.white));
                Log.d(this.toString(),"text color of gre_general_computer_sections_details set to white");

                gre_general_computer_order_details.setTextColor(getResources().getColor(R.color.white));
                Log.d(this.toString(),"text color of gre_general_computer_order_details set to white");

                gre_general_computer_unidentified_details.setTextColor(getResources().getColor(R.color.white));
                Log.d(this.toString(),"text color of gre_general_computer_unidentified_details set to white");

                gre_general_computer_break_details.setTextColor(getResources().getColor(R.color.white));
                Log.d(this.toString(),"text color of gre_general_computer_break_details set to white");

                gre_general_computer_time_details.setTextColor(getResources().getColor(R.color.white));
                Log.d(this.toString(),"text color of gre_general_computer_time_details set to white");

                gre_general_computer_features_details.setTextColor(getResources().getColor(R.color.white));
                Log.d(this.toString(),"text color of gre_general_computer_features_details set to white");
            }
            else
            {
                gre_general_computer_sections_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                Log.d(this.toString(),"text color of gre_general_computer_sections_title set to colorPrimary");

                gre_general_computer_order_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                Log.d(this.toString(),"text color of gre_general_computer_order_title set to colorPrimary");

                gre_general_computer_unidentified_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                Log.d(this.toString(),"text color of gre_general_computer_unidentified_title set to colorPrimary");

                gre_general_computer_break_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                Log.d(this.toString(),"text color of gre_general_computer_break_title set to colorPrimary");

                gre_general_computer_time_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                Log.d(this.toString(),"text color of gre_general_computer_time_title set to colorPrimary");

                gre_general_computer_features_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                Log.d(this.toString(),"text color of gre_general_computer_features_title set to colorPrimary");

                gre_general_computer_sections_details.setTextColor(getResources().getColor(R.color.black));
                Log.d(this.toString(),"text color of gre_general_computer_sections_details set to black");

                gre_general_computer_order_details.setTextColor(getResources().getColor(R.color.black));
                Log.d(this.toString(),"text color of gre_general_computer_order_details set to black");

                gre_general_computer_unidentified_details.setTextColor(getResources().getColor(R.color.black));
                Log.d(this.toString(),"text color of gre_general_computer_unidentified_details set to black");

                gre_general_computer_break_details.setTextColor(getResources().getColor(R.color.black));
                Log.d(this.toString(),"text color of gre_general_computer_break_details set to black");

                gre_general_computer_time_details.setTextColor(getResources().getColor(R.color.black));
                Log.d(this.toString(),"text color of gre_general_computer_time_details set to black");

                gre_general_computer_features_details.setTextColor(getResources().getColor(R.color.black));
                Log.d(this.toString(),"text color of gre_general_computer_features_details set to black");
            }
        }





    }
}
