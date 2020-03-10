package com.example.alpha;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.Objects;

public class BasicInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_info);

        //shared preferences

        final SharedPreferences sharedPref = Objects.requireNonNull(getSharedPreferences("Shared Preferences", MODE_PRIVATE));
        final int theme = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);

        //initialise elements

        TextView text_view_profile_picture = findViewById(R.id.text_view_profile_picture);
        TextView text_view_display_name = findViewById(R.id.text_view_display_name);
        TextView text_view_email = findViewById(R.id.text_view_email);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);


        if (account.getDisplayName() != null) {

            char [] temp = account.getDisplayName().toCharArray();
            text_view_profile_picture.setText(String.valueOf(temp[0]));

        }
        text_view_profile_picture.setBackground(getResources().getDrawable(R.drawable.profile_picture_light));

        text_view_display_name.setText(account.getDisplayName());

        text_view_email.setText(account.getEmail());

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


        action_bar_title.setText("Basic Info");

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


            text_view_profile_picture.setBackground(getResources().getDrawable(R.drawable.profile_picture_light));
            text_view_profile_picture.setTextColor(getResources().getColor(R.color.white));
            text_view_display_name.setTextColor(getResources().getColor(R.color.colorPrimary));
            text_view_email.setTextColor(getResources().getColor(R.color.black));





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



            text_view_profile_picture.setBackground(getResources().getDrawable(R.drawable.profile_picture_dark));
            text_view_profile_picture.setTextColor(getResources().getColor(R.color.darkBackground));
            text_view_display_name.setTextColor(getResources().getColor(R.color.darkHighlight));
            text_view_email.setTextColor(getResources().getColor(R.color.white));


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



                text_view_profile_picture.setBackground(getResources().getDrawable(R.drawable.profile_picture_dark));
                text_view_profile_picture.setTextColor(getResources().getColor(R.color.darkBackground));
                text_view_display_name.setTextColor(getResources().getColor(R.color.darkHighlight));
                text_view_email.setTextColor(getResources().getColor(R.color.white));


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




                text_view_profile_picture.setBackground(getResources().getDrawable(R.drawable.profile_picture_light));
                text_view_profile_picture.setTextColor(getResources().getColor(R.color.white));
                text_view_display_name.setTextColor(getResources().getColor(R.color.colorPrimary));
                text_view_email.setTextColor(getResources().getColor(R.color.black));

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






    }
}
