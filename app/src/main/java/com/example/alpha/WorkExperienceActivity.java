package com.example.alpha;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

public class WorkExperienceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_experience);


        //shared preferences

        final SharedPreferences sharedPref = Objects.requireNonNull(getSharedPreferences("Shared Preferences", MODE_PRIVATE));
        final int theme = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);


        //initialise add button

        Button button_add_work_experience = findViewById(R.id.button_add_work_experience);


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


        action_bar_title.setText("Work Experience");

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

            button_add_work_experience.setTextColor(getResources().getColor(R.color.white));


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

            button_add_work_experience.setTextColor(getResources().getColor(R.color.darkBackground));


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


                button_add_work_experience.setTextColor(getResources().getColor(R.color.darkBackground));


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

                button_add_work_experience.setTextColor(getResources().getColor(R.color.white));


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


        button_add_work_experience.setBackgroundTintList(colorStateList);



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
                alert_dialog_title.setText("Work Experience - Help");
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
                textView.setText("This page lets you view and list your Work Experiences for your application. You can add a Work Experience by mentioning the Company in which you worked, your Role and Duration for the same.\n\nFor example, Work Experience might look like:\n\nCompany: Google India\nRole: UX Designer\nExperience Duration: 2 years\n\nYour work experience relevant to the area in which you are applying is one of the many factors that contribute towards strengthening your application.\n\nGood luck!");
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


        //Google sign in and firebase database

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        final DatabaseReference work_experience_reference = databaseReference.child("Data").child(account.getId()).child("Work Experience");


        //initialise elements

        final ListView list_view_work_experience = findViewById(R.id.list_view_work_experience);

        final ArrayList<WorkExperienceItem> workExperienceItemArrayList = new ArrayList<>();    //store items for listview

        final ArrayList<String> keys = new ArrayList<>();   //store keys


        final WorkExperienceAdapter workExperienceAdapter = new WorkExperienceAdapter(workExperienceItemArrayList, getBaseContext());
        list_view_work_experience.setAdapter(workExperienceAdapter);





        button_add_work_experience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog alertDialog = null;

                LinearLayout alert_dialog_layout = new LinearLayout(v.getContext());
                alert_dialog_layout.setOrientation(LinearLayout.VERTICAL);
                alert_dialog_layout.setPadding(20, 20, 20, 20);
                alert_dialog_layout.setGravity(Gravity.CENTER);

                TextView alert_dialog_title = new TextView(alert_dialog_layout.getContext());
                alert_dialog_title.setText("Add Work Experience");
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
                textView.setText("Company:");
                textView.setPadding(20,20,20,20);
                textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


                final EditText editText = new EditText(alert_dialog_layout.getContext());


                editText.setTypeface(nexa_light);
                editText.setTextSize(20);
                editText.setPadding(20,20,20,20);
                editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText.setSingleLine(true);


                final TextView textView2 = new TextView(alert_dialog_layout.getContext());

                textView2.setTypeface(nexa_bold);
                textView2.setTextSize(15);
                textView2.setText("Role:");
                textView2.setPadding(20,20,20,20);
                textView2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


                final EditText editText2 = new EditText(alert_dialog_layout.getContext());


                editText2.setTypeface(nexa_light);
                editText2.setTextSize(20);
                editText2.setPadding(20,20,20,20);
                editText2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText2.setSingleLine(true);

                final TextView textView3 = new TextView(alert_dialog_layout.getContext());

                textView3.setTypeface(nexa_bold);
                textView3.setTextSize(15);
                textView3.setText("Experience Duration:");
                textView3.setPadding(20,20,20,20);
                textView3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


                final EditText editText3 = new EditText(alert_dialog_layout.getContext());


                editText3.setTypeface(nexa_light);
                editText3.setTextSize(20);
                editText3.setPadding(20,20,20,20);
                editText3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText3.setSingleLine(true);


                Button button = new Button(alert_dialog_layout.getContext());
                button.setPadding(20,20,20,20);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(10,10,10,10);
                button.setLayoutParams(layoutParams);
                button.setTextSize(15);
                button.setTypeface(nexa_bold);
                button.setText("SAVE");
                button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


                TextView button2 = new TextView(alert_dialog_layout.getContext());
                button2.setPadding(20,20,20,20);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams2.setMargins(10,10,10,10);
                button2.setLayoutParams(layoutParams);
                button2.setTextSize(15);
                button2.setTypeface(nexa_bold);
                button2.setText("CANCEL");
                button2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


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
                    textView3.setTextColor(getResources().getColor(R.color.black));
                    editText.setTextColor(getResources().getColor(R.color.black));
                    editText2.setTextColor(getResources().getColor(R.color.black));
                    editText3.setTextColor(getResources().getColor(R.color.black));
                    button.setTextColor(getResources().getColor(R.color.white));
                    button2.setTextColor(getResources().getColor(R.color.colorPrimary));

                    colors = new int[]{

                            getResources().getColor(R.color.colorPrimary)
                    };

                    Field f = null;
                    try {
                        f = TextView.class.getDeclaredField("mCursorDrawableRes");
                        f.setAccessible(true);
                        f.set(editText, R.drawable.cursor_light);
                        f.set(editText2, R.drawable.cursor_light);
                        f.set(editText3, R.drawable.cursor_light);

                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }



                } else if (theme == AppCompatDelegate.MODE_NIGHT_YES) {
                    alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                    alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                    textView.setTextColor(getResources().getColor(R.color.white));
                    textView2.setTextColor(getResources().getColor(R.color.white));
                    textView3.setTextColor(getResources().getColor(R.color.white));
                    editText.setTextColor(getResources().getColor(R.color.white));
                    editText2.setTextColor(getResources().getColor(R.color.white));
                    editText3.setTextColor(getResources().getColor(R.color.white));
                    button.setTextColor(getResources().getColor(R.color.darkBackground));
                    button2.setTextColor(getResources().getColor(R.color.darkHighlight));

                    colors = new int[]{

                            getResources().getColor(R.color.darkHighlight)
                    };

                    Field f = null;
                    try {
                        f = TextView.class.getDeclaredField("mCursorDrawableRes");
                        f.setAccessible(true);
                        f.set(editText, R.drawable.cursor_dark);
                        f.set(editText2, R.drawable.cursor_dark);
                        f.set(editText3, R.drawable.cursor_dark);


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
                        textView3.setTextColor(getResources().getColor(R.color.white));
                        editText.setTextColor(getResources().getColor(R.color.white));
                        editText2.setTextColor(getResources().getColor(R.color.white));
                        editText3.setTextColor(getResources().getColor(R.color.white));
                        button.setTextColor(getResources().getColor(R.color.darkBackground));
                        button2.setTextColor(getResources().getColor(R.color.darkHighlight));

                        colors = new int[]{

                                getResources().getColor(R.color.darkHighlight)
                        };


                        Field f = null;
                        try {
                            f = TextView.class.getDeclaredField("mCursorDrawableRes");
                            f.setAccessible(true);
                            f.set(editText, R.drawable.cursor_dark);
                            f.set(editText2, R.drawable.cursor_dark);
                            f.set(editText3, R.drawable.cursor_dark);



                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    } else {
                        alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.white));
                        alert_dialog_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                        textView.setTextColor(getResources().getColor(R.color.black));
                        textView2.setTextColor(getResources().getColor(R.color.black));
                        textView3.setTextColor(getResources().getColor(R.color.black));
                        editText.setTextColor(getResources().getColor(R.color.black));
                        editText2.setTextColor(getResources().getColor(R.color.black));
                        editText3.setTextColor(getResources().getColor(R.color.black));
                        button.setTextColor(getResources().getColor(R.color.white));
                        button2.setTextColor(getResources().getColor(R.color.colorPrimary));

                        colors = new int[]{

                                getResources().getColor(R.color.colorPrimary)
                        };

                        Field f = null;
                        try {
                            f = TextView.class.getDeclaredField("mCursorDrawableRes");
                            f.setAccessible(true);
                            f.set(editText, R.drawable.cursor_light);
                            f.set(editText2, R.drawable.cursor_light);
                            f.set(editText3, R.drawable.cursor_light);


                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    }
                }

                ColorStateList colorStateList = new ColorStateList(states,colors);
                editText.setBackgroundTintList(colorStateList);
                editText2.setBackgroundTintList(colorStateList);
                editText3.setBackgroundTintList(colorStateList);


                button.setBackgroundTintList(colorStateList);


                alert_dialog_layout.addView(alert_dialog_title);
                alert_dialog_layout.addView(textView);
                alert_dialog_layout.addView(editText);
                alert_dialog_layout.addView(textView2);
                alert_dialog_layout.addView(editText2);
                alert_dialog_layout.addView(textView3);
                alert_dialog_layout.addView(editText3);
                alert_dialog_layout.addView(button);
                alert_dialog_layout.addView(button2);

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setView(alert_dialog_layout);
                alertDialog = builder.create();
                alertDialog.show();

                final AlertDialog finalAlertDialog = alertDialog;

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(!editText.getText().toString().equals("") && !editText2.getText().toString().equals("") && !editText3.getText().toString().equals(""))
                        {

                            String company = editText.getText().toString();
                            String role = editText2.getText().toString();
                            String experience_duration = editText3.getText().toString();

                            WorkExperienceItem workExperienceItem = new WorkExperienceItem(company,role,experience_duration);

                            work_experience_reference.push().setValue(workExperienceItem);



                        }

                        finalAlertDialog.dismiss();
                    }
                });

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        finalAlertDialog.dismiss();
                    }
                });

            }
        });





        work_experience_reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                WorkExperienceItem workExperienceItem = dataSnapshot.getValue(WorkExperienceItem.class);

                workExperienceItemArrayList.add(workExperienceItem);
                workExperienceAdapter.notifyDataSetChanged();

                keys.add(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                int index = keys.indexOf(dataSnapshot.getKey());
                workExperienceItemArrayList.set(index,dataSnapshot.getValue(WorkExperienceItem.class));

                workExperienceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                int index = keys.indexOf(dataSnapshot.getKey());
                workExperienceItemArrayList.remove(index);

                keys.remove(index);
                workExperienceAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        list_view_work_experience.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                AlertDialog alertDialog = null;

                LinearLayout alert_dialog_layout = new LinearLayout(view.getContext());
                alert_dialog_layout.setOrientation(LinearLayout.VERTICAL);
                alert_dialog_layout.setPadding(20, 20, 20, 20);
                alert_dialog_layout.setGravity(Gravity.CENTER);

                TextView alert_dialog_title = new TextView(alert_dialog_layout.getContext());
                alert_dialog_title.setText("Edit Work Experience");
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
                textView.setText("Company:");
                textView.setPadding(20,20,20,20);
                textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


                final EditText editText = new EditText(alert_dialog_layout.getContext());


                editText.setTypeface(nexa_light);
                editText.setTextSize(20);
                editText.setPadding(20,20,20,20);
                editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText.setSingleLine(true);


                final TextView textView2 = new TextView(alert_dialog_layout.getContext());

                textView2.setTypeface(nexa_bold);
                textView2.setTextSize(15);
                textView2.setText("Role:");
                textView2.setPadding(20,20,20,20);
                textView2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


                final EditText editText2 = new EditText(alert_dialog_layout.getContext());


                editText2.setTypeface(nexa_light);
                editText2.setTextSize(20);
                editText2.setPadding(20,20,20,20);
                editText2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText2.setSingleLine(true);

                final TextView textView3 = new TextView(alert_dialog_layout.getContext());

                textView3.setTypeface(nexa_bold);
                textView3.setTextSize(15);
                textView3.setText("Experience Duration:");
                textView3.setPadding(20,20,20,20);
                textView3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


                final EditText editText3 = new EditText(alert_dialog_layout.getContext());


                editText3.setTypeface(nexa_light);
                editText3.setTextSize(20);
                editText3.setPadding(20,20,20,20);
                editText3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText3.setSingleLine(true);


                Button button = new Button(alert_dialog_layout.getContext());
                button.setPadding(20,20,20,20);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(10,10,10,10);
                button.setLayoutParams(layoutParams);
                button.setTextSize(15);
                button.setTypeface(nexa_bold);
                button.setText("SAVE");
                button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


                TextView button2 = new TextView(alert_dialog_layout.getContext());
                button2.setPadding(20,20,20,20);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams2.setMargins(10,10,10,10);
                button2.setLayoutParams(layoutParams);
                button2.setTextSize(15);
                button2.setTypeface(nexa_bold);
                button2.setText("DELETE");
                button2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


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
                    textView3.setTextColor(getResources().getColor(R.color.black));
                    editText.setTextColor(getResources().getColor(R.color.black));
                    editText2.setTextColor(getResources().getColor(R.color.black));
                    editText3.setTextColor(getResources().getColor(R.color.black));
                    button.setTextColor(getResources().getColor(R.color.white));
                    button2.setTextColor(getResources().getColor(R.color.colorPrimary));

                    colors = new int[]{

                            getResources().getColor(R.color.colorPrimary)
                    };

                    Field f = null;
                    try {
                        f = TextView.class.getDeclaredField("mCursorDrawableRes");
                        f.setAccessible(true);
                        f.set(editText, R.drawable.cursor_light);
                        f.set(editText2, R.drawable.cursor_light);
                        f.set(editText3, R.drawable.cursor_light);

                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }



                } else if (theme == AppCompatDelegate.MODE_NIGHT_YES) {
                    alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                    alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                    textView.setTextColor(getResources().getColor(R.color.white));
                    textView2.setTextColor(getResources().getColor(R.color.white));
                    textView3.setTextColor(getResources().getColor(R.color.white));
                    editText.setTextColor(getResources().getColor(R.color.white));
                    editText2.setTextColor(getResources().getColor(R.color.white));
                    editText3.setTextColor(getResources().getColor(R.color.white));
                    button.setTextColor(getResources().getColor(R.color.darkBackground));
                    button2.setTextColor(getResources().getColor(R.color.darkHighlight));

                    colors = new int[]{

                            getResources().getColor(R.color.darkHighlight)
                    };

                    Field f = null;
                    try {
                        f = TextView.class.getDeclaredField("mCursorDrawableRes");
                        f.setAccessible(true);
                        f.set(editText, R.drawable.cursor_dark);
                        f.set(editText2, R.drawable.cursor_dark);
                        f.set(editText3, R.drawable.cursor_dark);


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
                        textView3.setTextColor(getResources().getColor(R.color.white));
                        editText.setTextColor(getResources().getColor(R.color.white));
                        editText2.setTextColor(getResources().getColor(R.color.white));
                        editText3.setTextColor(getResources().getColor(R.color.white));
                        button.setTextColor(getResources().getColor(R.color.darkBackground));
                        button2.setTextColor(getResources().getColor(R.color.darkHighlight));

                        colors = new int[]{

                                getResources().getColor(R.color.darkHighlight)
                        };


                        Field f = null;
                        try {
                            f = TextView.class.getDeclaredField("mCursorDrawableRes");
                            f.setAccessible(true);
                            f.set(editText, R.drawable.cursor_dark);
                            f.set(editText2, R.drawable.cursor_dark);
                            f.set(editText3, R.drawable.cursor_dark);



                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    } else {
                        alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.white));
                        alert_dialog_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                        textView.setTextColor(getResources().getColor(R.color.black));
                        textView2.setTextColor(getResources().getColor(R.color.black));
                        textView3.setTextColor(getResources().getColor(R.color.black));
                        editText.setTextColor(getResources().getColor(R.color.black));
                        editText2.setTextColor(getResources().getColor(R.color.black));
                        editText3.setTextColor(getResources().getColor(R.color.black));
                        button.setTextColor(getResources().getColor(R.color.white));
                        button2.setTextColor(getResources().getColor(R.color.colorPrimary));

                        colors = new int[]{

                                getResources().getColor(R.color.colorPrimary)
                        };

                        Field f = null;
                        try {
                            f = TextView.class.getDeclaredField("mCursorDrawableRes");
                            f.setAccessible(true);
                            f.set(editText, R.drawable.cursor_light);
                            f.set(editText2, R.drawable.cursor_light);
                            f.set(editText3, R.drawable.cursor_light);


                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    }
                }

                ColorStateList colorStateList = new ColorStateList(states,colors);
                editText.setBackgroundTintList(colorStateList);
                editText2.setBackgroundTintList(colorStateList);
                editText3.setBackgroundTintList(colorStateList);


                button.setBackgroundTintList(colorStateList);

                editText.setText(workExperienceItemArrayList.get(position).getCompany());
                editText2.setText(workExperienceItemArrayList.get(position).getRole());
                editText3.setText(workExperienceItemArrayList.get(position).getExperience_duration());


                alert_dialog_layout.addView(alert_dialog_title);
                alert_dialog_layout.addView(textView);
                alert_dialog_layout.addView(editText);
                alert_dialog_layout.addView(textView2);
                alert_dialog_layout.addView(editText2);
                alert_dialog_layout.addView(textView3);
                alert_dialog_layout.addView(editText3);
                alert_dialog_layout.addView(button);
                alert_dialog_layout.addView(button2);

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(alert_dialog_layout);
                alertDialog = builder.create();
                alertDialog.show();

                final AlertDialog finalAlertDialog = alertDialog;

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(!editText.getText().toString().equals("") && !editText2.getText().toString().equals("") && !editText3.getText().toString().equals(""))
                        {

                            String company = editText.getText().toString();
                            String role = editText2.getText().toString();
                            String experience_duration = editText3.getText().toString();

                            WorkExperienceItem workExperienceItem = new WorkExperienceItem(company,role,experience_duration);

                            String key = keys.get(position);
                            work_experience_reference.child(key).setValue(workExperienceItem);



                        }

                        finalAlertDialog.dismiss();
                    }
                });

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String key = keys.get(position);
                        work_experience_reference.child(key).removeValue();


                        finalAlertDialog.dismiss();
                    }
                });


            }
        });



    }
}
