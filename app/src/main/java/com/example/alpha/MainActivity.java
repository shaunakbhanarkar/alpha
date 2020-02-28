package com.example.alpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private Fragment selected_fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialise elements

        final BottomNavigationView bottom_nav = findViewById(R.id.bottom_nav);
        FrameLayout frame_layout = findViewById(R.id.frame_layout);

        //change font in action bar

        final ActionBar action_bar = getSupportActionBar();
        action_bar.setDisplayShowTitleEnabled(false);
        action_bar.setDisplayShowCustomEnabled(true);

//        LinearLayout action_bar_layout = new LinearLayout(this);
//        action_bar_layout.setOrientation(LinearLayout.VERTICAL);
//        action_bar_layout.setGravity(View.TEXT_ALIGNMENT_CENTER);

        final TextView action_bar_title = new TextView(this);

        if (bottom_nav.getSelectedItemId() == R.id.bottom_nav_dashboard)
            action_bar_title.setText("Dashboard");
        else if (bottom_nav.getSelectedItemId() == R.id.bottom_nav_explore)
            action_bar_title.setText("Explore");
        else if (bottom_nav.getSelectedItemId() == R.id.bottom_nav_account)
            action_bar_title.setText("Account");

        action_bar_title.setTextSize(20);
        action_bar_title.setTextColor(getResources().getColor(R.color.colorPrimary));
        action_bar_title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        Typeface typeface_medium = getResources().getFont(R.font.nexa_bold);
        action_bar_title.setTypeface(typeface_medium);

//        action_bar_layout.addView(action_bar_title);

        action_bar_title.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        action_bar_title.setPadding(0,50,0,0);

        action_bar.setCustomView(action_bar_title);


        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


        //change status bar colour
        window.setStatusBarColor(getResources().getColor(R.color.white));
        //change action bar colour
        action_bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        // set status bar contrast
        View decor = window.getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);




        // set bottom navigation menu background colour

        int[][] states = new int[][]{


                new int[]{android.R.attr.state_checked}, // checked
                new int[]{-android.R.attr.state_checked}  // unchecked
        };

        int[] colors = new int[]{

                getResources().getColor(R.color.colorPrimary),
                Color.parseColor("#546e7a")
        };


        ColorStateList colorStateList = new ColorStateList(states, colors);

        bottom_nav.setItemTextColor(colorStateList);
        bottom_nav.setItemIconTintList(colorStateList);


        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.bottom_nav_explore:
                        selected_fragment = new ExploreFragment();
                        action_bar_title.setText("Explore");
                        break;

                    case R.id.bottom_nav_account:
                        selected_fragment = new AccountFragment();
                        action_bar_title.setText("Account");
                        break;

                    default:
                        selected_fragment = new DashboardFragment();
                        action_bar_title.setText("Dashboard");
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

        bottom_nav.setSelectedItemId(R.id.bottom_nav_dashboard);

    }
}
