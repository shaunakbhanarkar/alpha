package com.example.alpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PowerManager;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Fragment selected_fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Main Activity","started");

        //Initialise elements

        final BottomNavigationView bottom_nav = findViewById(R.id.bottom_nav);
        Log.d("Main Activity","bottom nav initialised");

        FrameLayout frame_layout = findViewById(R.id.frame_layout);
        Log.d("Main Activity","frame layout initialised");

        SharedPreferences sharedPref = Objects.requireNonNull(getSharedPreferences("Shared Preferences", MODE_PRIVATE));
        int theme = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);
        Log.d("Main Activity","current app theme is "+theme);

        Log.d("Main Activity","setting the appropriate fragment...");

        int fragment = sharedPref.getInt("Fragment",0);
        if (fragment == 1) {
            bottom_nav.setSelectedItemId(R.id.bottom_nav_explore);
            Log.d("Main Activity","explore fragment selected");

        }
        else if (fragment == 2) {
            bottom_nav.setSelectedItemId(R.id.bottom_nav_account);
            Log.d("Main Activity","account fragment selected");

        }
        else {
            bottom_nav.setSelectedItemId(R.id.bottom_nav_dashboard);
            Log.d("Main Activity","dashboard fragment selected");

        }

        //customise action bar

        Log.d("Main Activity","customising action bar...");

        final ActionBar action_bar = getSupportActionBar();

        action_bar.setDisplayShowTitleEnabled(false);
        Log.d("Main Activity","action bar display show title disabled");

        action_bar.setDisplayShowCustomEnabled(true);
        Log.d("Main Activity","action bar custom display enabled");

        action_bar.setCustomView(R.layout.action_bar);
        Log.d("Main Activity","action bar custom view set");

        final TextView action_bar_title = action_bar.getCustomView().findViewById(R.id.action_bar_title);

        if (theme == AppCompatDelegate.MODE_NIGHT_NO) {
            action_bar_title.setTextColor(getResources().getColor(R.color.colorPrimary));
            Log.d("Main Activity","action bar title color set to color primary");

        }
        else if (theme == AppCompatDelegate.MODE_NIGHT_YES) {
            action_bar_title.setTextColor(getResources().getColor(R.color.darkHighlight));
            Log.d("Main Activity","action bar title color set to dark highlight");

        }
        else {

            PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
            if (powerManager.isPowerSaveMode()) {

                action_bar_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                Log.d("Main Activity","action bar title color set to dark highlight");

            } else {

                action_bar_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                Log.d("Main Activity","action bar title color set to color primary");

            }

        }

        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        Log.d("Main Activity","window flags cleared");

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        Log.d("Main Activity","window flags added");



        if (theme == AppCompatDelegate.MODE_NIGHT_NO) {
            //change status bar colour
            window.setStatusBarColor(getResources().getColor(R.color.white));
            Log.d("Main Activity","status bar color set to white");


            //change action bar colour
            action_bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
            Log.d("Main Activity","action bar color set to white");

            // set status bar contrast
            View decor = window.getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            Log.d("Main Activity","contrast set");

            //set bottom navigation view background colour
            bottom_nav.setBackgroundColor(getResources().getColor(R.color.white));
            Log.d("Main Activity","bottom nav background color set to white");

        }
        else if (theme == AppCompatDelegate.MODE_NIGHT_YES) {
            //change status bar colour
            window.setStatusBarColor(getResources().getColor(R.color.darkBackground));
            Log.d("Main Activity","status bar color set to dark background");

            //change action bar colour
            action_bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkBackground)));
            Log.d("Main Activity","action bar color set to dark background");

            // set status bar contrast
            View decor = window.getDecorView();
            LinearLayout background_layout = decor.findViewById(R.id.background_layout);
            background_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
            Log.d("Main Activity","contrast set");

            //set bottom navigation view background colour
            bottom_nav.setBackgroundColor(getResources().getColor(R.color.darkBackground));
            Log.d("Main Activity","bottom nav background color set to dark background");

        }
        else {

            PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
            if (powerManager.isPowerSaveMode()) {

                //change status bar colour
                window.setStatusBarColor(getResources().getColor(R.color.darkBackground));
                Log.d("Main Activity","status bar color set to dark background");

                //change action bar colour
                action_bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkBackground)));
                Log.d("Main Activity","action bar color set to dark background");

                // set status bar contrast
                View decor = window.getDecorView();
                LinearLayout background_layout = decor.findViewById(R.id.background_layout);
                background_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                Log.d("Main Activity","contrast set");

                //set bottom navigation view background colour
                bottom_nav.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                Log.d("Main Activity","bottom nav background color set to dark background");


            } else {

                //change status bar colour
                window.setStatusBarColor(getResources().getColor(R.color.white));
                Log.d("Main Activity","status bar color set to white");

                //change action bar colour
                action_bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
                Log.d("Main Activity","action bar color set to white");

                // set status bar contrast
                View decor = window.getDecorView();
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                Log.d("Main Activity","contrast set");

                //set bottom navigation view background colour
                bottom_nav.setBackgroundColor(getResources().getColor(R.color.white));
                Log.d("Main Activity","bottom nav background color set to white");

            }

        }


        // set bottom navigation menu background colour

        int[][] states = new int[][]{


                new int[]{android.R.attr.state_checked}, // checked
                new int[]{-android.R.attr.state_checked}  // unchecked
        };

        int[] colors= new int[]{

                    getResources().getColor(R.color.checkedBottomNavItemLight),
                    getResources().getColor(R.color.uncheckedBottomNavItem)
            };

        if (theme == AppCompatDelegate.MODE_NIGHT_NO){

            colors= new int[]{

                    getResources().getColor(R.color.checkedBottomNavItemLight),
                    getResources().getColor(R.color.uncheckedBottomNavItem)
            };

        }

        else if (theme == AppCompatDelegate.MODE_NIGHT_YES){
            colors = new int[]{

                    getResources().getColor(R.color.checkedBottomNavItemDark),
                    getResources().getColor(R.color.uncheckedBottomNavItem)
            };
        }

        else {

            PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
            if (powerManager.isPowerSaveMode()) {

                colors = new int[]{

                        getResources().getColor(R.color.checkedBottomNavItemDark),
                        getResources().getColor(R.color.uncheckedBottomNavItem)
                };

            }
            else {

                colors= new int[]{

                        getResources().getColor(R.color.checkedBottomNavItemLight),
                        getResources().getColor(R.color.uncheckedBottomNavItem)
                };

            }

        }

        ColorStateList colorStateList = new ColorStateList(states, colors);

        bottom_nav.setItemTextColor(colorStateList);
        bottom_nav.setItemIconTintList(colorStateList);

        Log.d("Main Activity","bottom nav color state lists set");

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.bottom_nav_explore:
                        selected_fragment = new ExploreFragment();
                        action_bar_title.setText("Explore");
                        Log.d("Main Activity","bottom nav - explore fragment selected");

                        break;

                    case R.id.bottom_nav_account:
                        selected_fragment = new AccountFragment();
                        action_bar_title.setText("Account");
                        Log.d("Main Activity","bottom nav - account fragment selected");

                        break;

                    default:
                        selected_fragment = new DashboardFragment();
                        action_bar_title.setText("Dashboard");
                        Log.d("Main Activity","bottom nav - dashboard fragment selected");

                        break;
                }
                FragmentTransaction transaction = MainActivity.this.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selected_fragment);
                transaction.commit();
                return true;
            }
        });




    }


    @Override
    protected void onStart() {

        super.onStart();

        BottomNavigationView bottom_nav = findViewById(R.id.bottom_nav);
        Log.d("Main Activity","bottom nav initialised");

//        bottom_nav.setSelectedItemId(R.id.bottom_nav_dashboard);

        SharedPreferences sharedPref = Objects.requireNonNull(getSharedPreferences("Shared Preferences", MODE_PRIVATE));
        int fragment = sharedPref.getInt("Fragment",0);
        if (fragment == 1) {
            bottom_nav.setSelectedItemId(R.id.bottom_nav_explore);
            Log.d("Main Activity","explore fragment selected");

        }
        else if (fragment == 2) {
            bottom_nav.setSelectedItemId(R.id.bottom_nav_account);
            Log.d("Main Activity","account fragment selected");

        }
        else {
            bottom_nav.setSelectedItemId(R.id.bottom_nav_dashboard);
            Log.d("Main Activity","dashboard fragment selected");

        }

        SharedPreferences.Editor sharedPrefEditor = sharedPref.edit();
        Log.d("Main Activity","shared preferences opened for editing");

        sharedPrefEditor.putInt("Fragment" , 0);
        Log.d("Main Activity","share preferences fragment value changed to 0");

        sharedPrefEditor.apply();
        Log.d("Account Fragment","shared preferences changes applied");

    }
}
