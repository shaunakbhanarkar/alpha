package com.example.alpha;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.os.PowerManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //shared preferences
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedPrefEditor;


    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("Account Fragment","started");

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, container, false);
        Log.d("Account Fragment","layout inflated");

        //shared preferences
        sharedPref = Objects.requireNonNull(getActivity()).getSharedPreferences("Shared Preferences", MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
        Log.d("Account Fragment","shared preferences opened for editing");

        sharedPrefEditor.putInt("Fragment",2);
        Log.d("Account Fragment","share preferences fragment value changed to 2");

        sharedPrefEditor.apply();
        Log.d("Account Fragment","shared preferences changes applied");


        CharSequence[] app_themes = {"Light", "Dark", "Set by Batter Saver"};

        final int theme = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);
        Log.d("Account Fragment","current app theme is "+theme);

        final ListView listView = v.findViewById(R.id.list_view_account);
        Log.d("Account Fragment","listView initialised");

        ArrayList<AccountItem> accountItemArrayList = new ArrayList<>();
        Log.d("Account Fragment","accountItemArrayList created");

        accountItemArrayList.add(new AccountItem("PROFILE"));
        Log.d("Account Fragment","item added to accountItemArrayList - PROFILE");

        accountItemArrayList.add(new AccountItem("Basic Info"));
        Log.d("Account Fragment","item added to accountItemArrayList - Basic Info");

        accountItemArrayList.add(new AccountItem("Aspirations"));
        Log.d("Account Fragment","item added to accountItemArrayList - Aspirations");

        accountItemArrayList.add(new AccountItem("Educational Details"));
        Log.d("Account Fragment","item added to accountItemArrayList - Educational Details");

        accountItemArrayList.add(new AccountItem("Letters of Recommendation"));
        Log.d("Account Fragment","item added to accountItemArrayList - Letters of Recommendation");

        accountItemArrayList.add(new AccountItem("Work Experience"));
        Log.d("Account Fragment","item added to accountItemArrayList - Work Experience");

        accountItemArrayList.add(new AccountItem("Test Scores"));
        Log.d("Account Fragment","item added to accountItemArrayList - Test Scores");

        accountItemArrayList.add(new AccountItem("Publications"));
        Log.d("Account Fragment","item added to accountItemArrayList - Publications");

        accountItemArrayList.add(new AccountItem("Extra Curricular"));
        Log.d("Account Fragment","item added to accountItemArrayList - Extra Curricular");

        accountItemArrayList.add(new AccountItem("SETTINGS"));
        Log.d("Account Fragment","item added to accountItemArrayList - SETTINGS");

        accountItemArrayList.add(new AccountItem("Theme"));
        Log.d("Account Fragment","item added to accountItemArrayList - Theme");

        accountItemArrayList.add(new AccountItem("About"));
        Log.d("Account Fragment","item added to accountItemArrayList - About");

        accountItemArrayList.add(new AccountItem("Log Out"));
        Log.d("Account Fragment","item added to accountItemArrayList - Log Out");




        AccountAdapter accountAdapter = new AccountAdapter(accountItemArrayList, getContext());
        Log.d("Account Fragment","accountAdapter created");

        listView.setAdapter(accountAdapter);
        Log.d("Account Fragment","accountAdapter set to listView");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0)  //profile
                {
                    Log.d("Account Fragment","(position = 0) non-clickable PROFILE heading clicked");

                }

                if (position == 1)  //basic info
                {
                    Log.d("Account Fragment","(position = 1) starting BasicInfoActivity...");

                    Intent intent = new Intent(getContext(),BasicInfoActivity.class);
                    startActivity(intent);
                }

                if (position == 2) //aspirations
                {
                    Log.d("Account Fragment","(position = 2) starting AspirationsActivity...");

                    Intent intent = new Intent(getContext(),AspirationsActivity.class);
                    startActivity(intent);
                }

                if (position == 3) //educational details
                {
                    Log.d("Account Fragment","(position = 3) starting EducationalDetailsActivity...");

                    Intent intent = new Intent(getContext(),EducationalDetailsActivity.class);
                    startActivity(intent);
                }

                if (position == 4)  //letters of recommendation
                {
                    Log.d("Account Fragment","(position = 4) starting LettersOfRecommendationActivity...");

                    Intent intent = new Intent(getContext(),LettersOfRecommendationActivity.class);
                    startActivity(intent);
                }

                if (position == 5)  //work experience
                {
                    Log.d("Account Fragment","(position = 5) starting WorkExperienceActivity...");

                    Intent intent = new Intent(getContext(),WorkExperienceActivity.class);
                    startActivity(intent);
                }

                if (position == 6)  //test scores
                {
                    Log.d("Account Fragment","(position = 6) starting TestScoresActivity...");

                    Intent intent = new Intent(getContext(),TestScoresActivity.class);
                    startActivity(intent);
                }

                if (position == 7)  //publications
                {
                    Log.d("Account Fragment","(position = 7) starting PublicationsActivity...");

                    Intent intent = new Intent(getContext(),PublicationsActivity.class);
                    startActivity(intent);
                }

                if (position == 8)  //extra curricular
                {
                    Log.d("Account Fragment","(position = 8) starting ExtraCurricularActivity...");

                }

                if (position == 9)  //settings
                {
                    Log.d("Account Fragment","(position = 9) non-clickable SETTINGS heading clicked");

                }

                if (position == 10) //theme
                {

                    Log.d("Account Fragment","(position = 10) theme clicked");

                    int checked_item = 0;

                    if (sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO) - 1 >= 0)
                        checked_item = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO) - 1;

                    Log.d("Account Fragment","checked item for theme alert dialog set");

                    AlertDialog alertDialog = null;

                    LinearLayout alert_dialog_layout = new LinearLayout(getContext());
                    alert_dialog_layout.setOrientation(LinearLayout.VERTICAL);
                    alert_dialog_layout.setPadding(20, 20, 20, 20);

                    TextView alert_dialog_title = new TextView(alert_dialog_layout.getContext());
                    alert_dialog_title.setText("Choose App Theme");
                    alert_dialog_title.setTextSize(22);
                    alert_dialog_title.setPadding(40, 20, 20, 20);
                    alert_dialog_title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    alert_dialog_title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    Log.w("Account Fragment","initialising nexa bold...(requires API level 26)");
                    Typeface nexa_bold = getResources().getFont(R.font.nexa_bold);

                    alert_dialog_title.setTypeface(nexa_bold);

                    RadioGroup radioGroup = new RadioGroup(alert_dialog_layout.getContext());
                    radioGroup.setOrientation(RadioGroup.VERTICAL);
                    radioGroup.setPadding(20, 20, 20, 40);

                    RadioButton radio_button_light = new RadioButton(alert_dialog_layout.getContext());
                    RadioButton radio_button_dark = new RadioButton(alert_dialog_layout.getContext());
                    RadioButton radio_button_set_by_battery_saver = new RadioButton(alert_dialog_layout.getContext());

                    radio_button_light.setText("Light");
                    radio_button_dark.setText("Dark");
                    radio_button_set_by_battery_saver.setText("Set by Battery Saver");

                    radio_button_light.setTextSize(20);
                    radio_button_dark.setTextSize(20);
                    radio_button_set_by_battery_saver.setTextSize(20);

                    Log.w("Account Fragment","initialising nexa light...(requires API level 26)");
                    Typeface nexa_light = getResources().getFont(R.font.nexa_light);

                    radio_button_light.setTypeface(nexa_light);
                    radio_button_dark.setTypeface(nexa_light);
                    radio_button_set_by_battery_saver.setTypeface(nexa_light);

                    radio_button_light.setPadding(20, 20, 20, 20);
                    radio_button_dark.setPadding(20, 20, 20, 20);
                    radio_button_set_by_battery_saver.setPadding(20, 20, 20, 20);

                    radioGroup.addView(radio_button_light);
                    radioGroup.addView(radio_button_dark);
                    radioGroup.addView(radio_button_set_by_battery_saver);


                    int[][] states = new int[][]{


                            new int[]{android.R.attr.state_checked}, // checked
                            new int[]{-android.R.attr.state_checked}  // unchecked
                    };

                    int[] colors = new int[]{

                            getResources().getColor(R.color.checkedRadioButtonLight),
                            getResources().getColor(R.color.uncheckedRadioButton)
                    };

                    if (theme == AppCompatDelegate.MODE_NIGHT_NO) {

                        colors= new int[]{

                                getResources().getColor(R.color.checkedRadioButtonLight),
                                getResources().getColor(R.color.uncheckedRadioButton)
                        };

                    }
                    else if (theme == AppCompatDelegate.MODE_NIGHT_YES){
                        colors = new int[]{

                                getResources().getColor(R.color.checkedRadioButtonDark),
                                getResources().getColor(R.color.uncheckedRadioButton)
                        };
                    }
                    else {

                        PowerManager powerManager = (PowerManager) getActivity().getSystemService(Context.POWER_SERVICE);
                        if (powerManager.isPowerSaveMode()) {

                            colors = new int[]{

                                    getResources().getColor(R.color.checkedRadioButtonDark),
                                    getResources().getColor(R.color.uncheckedRadioButton)
                            };


                        } else {

                            colors= new int[]{

                                    getResources().getColor(R.color.checkedRadioButtonLight),
                                    getResources().getColor(R.color.uncheckedRadioButton)
                            };

                        }
                    }

                    ColorStateList colorStateList = new ColorStateList(states, colors);

                    radio_button_light.setButtonTintList(colorStateList);
                    radio_button_dark.setButtonTintList(colorStateList);
                    radio_button_set_by_battery_saver.setButtonTintList(colorStateList);

                    if (theme == AppCompatDelegate.MODE_NIGHT_NO) {
                        alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.white));
                        alert_dialog_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                        radio_button_light.setTextColor(getResources().getColor(R.color.black));
                        radio_button_dark.setTextColor(getResources().getColor(R.color.black));
                        radio_button_set_by_battery_saver.setTextColor(getResources().getColor(R.color.black));
                    } else if (theme == AppCompatDelegate.MODE_NIGHT_YES) {
                        alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                        alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                        radio_button_light.setTextColor(getResources().getColor(R.color.white));
                        radio_button_dark.setTextColor(getResources().getColor(R.color.white));
                        radio_button_set_by_battery_saver.setTextColor(getResources().getColor(R.color.white));
                    } else {
                        PowerManager powerManager = (PowerManager) getActivity().getSystemService(Context.POWER_SERVICE);
                        if (powerManager.isPowerSaveMode()) {
                            alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.darkBackground));
                            alert_dialog_title.setTextColor(getResources().getColor(R.color.darkHighlight));
                            radio_button_light.setTextColor(getResources().getColor(R.color.white));
                            radio_button_dark.setTextColor(getResources().getColor(R.color.white));
                            radio_button_set_by_battery_saver.setTextColor(getResources().getColor(R.color.white));
                        } else {
                            alert_dialog_layout.setBackgroundColor(getResources().getColor(R.color.white));
                            alert_dialog_title.setTextColor(getResources().getColor(R.color.colorPrimary));
                            radio_button_light.setTextColor(getResources().getColor(R.color.black));
                            radio_button_dark.setTextColor(getResources().getColor(R.color.black));
                            radio_button_set_by_battery_saver.setTextColor(getResources().getColor(R.color.black));
                        }
                    }

                    Log.d("Account Fragment","setting the appropriate radio button for theme...");

                    if (checked_item == 0) {
                        radio_button_light.setChecked(true);
                        Log.d("Account Fragment","radio_button_light checked");
                    }
                    else if (checked_item == 1) {
                        radio_button_dark.setChecked(true);
                        Log.d("Account Fragment", "radio_button_dark checked");
                    }
                    else {
                        radio_button_set_by_battery_saver.setChecked(true);
                        Log.d("Account Fragment", "radio_button_set_by_battery_saver checked");
                    }

                    Log.d("Account Fragment", "adding views to alert dialog for theme...");


                    alert_dialog_layout.addView(alert_dialog_title);
                    Log.d("Account Fragment", "alert_dialog_title added to alert_dialog_layout for theme");

                    alert_dialog_layout.addView(radioGroup);
                    Log.d("Account Fragment", "radioGroup added to alert_dialog_layout for theme");

                    Log.d("Account Fragment", "building alert dialog for theme...");

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setView(alert_dialog_layout);
                    alertDialog = builder.create();
                    alertDialog.show();

                    Log.d("Account Fragment", "alert dialog created and shown");


                    final AlertDialog finalAlertDialog = alertDialog;


                    radio_button_light.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Log.d("Account Fragment", "radio_button_light clicked");

                            Log.d("Account Fragment", "changing theme to light...");
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                            sharedPrefEditor.putInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);
                            Log.d("Account Fragment","share preferences theme value changed to AppCompatDelegate.MODE_NIGHT_NO");

//                            sharedPrefEditor.putInt("Fragment",2);

                            sharedPrefEditor.apply();
                            Log.d("Account Fragment","shared preferences changes applied");

                            Log.d("Account Fragment","fragment value is"+sharedPref.getInt("Fragment",-1));

                            finalAlertDialog.dismiss();
                            Log.d("Account Fragment","alert dialog for theme dismissed");

                            Log.i("Account Fragment","light theme selected");

                        }
                    });

                    radio_button_dark.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Log.d("Account Fragment", "radio_button_dark clicked");

                            Log.d("Account Fragment", "changing theme to dark...");
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                            sharedPrefEditor.putInt("Theme", AppCompatDelegate.MODE_NIGHT_YES);
                            Log.d("Account Fragment","share preferences theme value changed to AppCompatDelegate.MODE_NIGHT_YES");

//                            sharedPrefEditor.putInt("Fragment",2);

                            sharedPrefEditor.apply();
                            Log.d("Account Fragment","shared preferences changes applied");

                            Log.d("Account Fragment","fragment value is"+sharedPref.getInt("Fragment",-1));

                            finalAlertDialog.dismiss();
                            Log.d("Account Fragment","alert dialog for theme dismissed");

                            Log.i("Account Fragment","dark theme selected");

                        }
                    });

                    radio_button_set_by_battery_saver.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Log.d("Account Fragment", "radio_button_set_by_battery_saver clicked");

                            Log.d("Account Fragment", "changing theme to set by battery saver...");
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);

                            sharedPrefEditor.putInt("Theme", AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                            Log.d("Account Fragment","share preferences theme value changed to AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY");

//                            sharedPrefEditor.putInt("Fragment",2);

                            sharedPrefEditor.apply();
                            Log.d("Account Fragment","shared preferences changes applied");

                            Log.d("Account Fragment","fragment value is"+sharedPref.getInt("Fragment",-1));

                            finalAlertDialog.dismiss();
                            Log.d("Account Fragment","alert dialog for theme dismissed");

                            Log.i("Account Fragment","set by battery saver theme selected");

                        }
                    });


                }

                if (position == 11) //about
                {
                    Log.d("Account Fragment","(position = 11) starting AboutActivity...");

                    Intent intent = new Intent(getContext(),AboutActivity.class);
                    startActivity(intent);
                }


                if (position == 12) //logout
                {
                    Log.d("Account Fragment","(position = 12) log out clicked, initiating log out...");

                    Log.d("Account Fragment","getting last signed in account...");
                    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());

                    if (account != null) {
                        Log.d("Account Fragment","account id is " + account.getId());
                        Log.d("Account Fragment", "account display name is " + account.getDisplayName());
                        Log.d("Account Fragment", "account email is " + account.getEmail());
                    }
                    else {
                        Log.e("Account Fragment","account is null");
                    }

                    Log.d("Account Fragment","building google sign in options...");
                    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestEmail()
                            .build();

                    Log.d("Account Fragment","creating google sign in client...");
                    GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);

                    Log.d("Account Fragment","proceeding with sign out...");
                    mGoogleSignInClient.signOut();

                    Log.d("Account Fragment","successfully signed out");

                    sharedPrefEditor.clear();
                    sharedPrefEditor.apply();
                    Log.d("Account Fragment","shared preferences cleared");

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    Log.d("Account Fragment","theme set to light");

                    Log.d("Account Fragment","finishing MainActivity...");
                    getActivity().finishAffinity();

                    Log.d("Account Fragment","starting LoginActivity...");
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);

                    Log.i("Account Fragment","log out successful");
                }


            }




        });


        return v;
    }
}
