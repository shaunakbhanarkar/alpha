package com.example.alpha;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;

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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, container, false);

        //shared preferences
        sharedPref = Objects.requireNonNull(getActivity()).getSharedPreferences("Shared Preferences", MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();

        sharedPrefEditor.putInt("Fragment",2);
        sharedPrefEditor.apply();

        CharSequence[] app_themes = {"Light", "Dark", "Set by Batter Saver"};

        final int theme = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);



        final ListView listView = v.findViewById(R.id.list_view_account);

        ArrayList<AccountItem> accountItemArrayList = new ArrayList<>();

        accountItemArrayList.add(new AccountItem("PROFILE"));
        accountItemArrayList.add(new AccountItem("Basic Info"));
        accountItemArrayList.add(new AccountItem("Aspirations"));
        accountItemArrayList.add(new AccountItem("Educational Details"));
        accountItemArrayList.add(new AccountItem("Letters of Recommendation"));
        accountItemArrayList.add(new AccountItem("Work Experience"));
        accountItemArrayList.add(new AccountItem("Publications"));
        accountItemArrayList.add(new AccountItem("Extra Curriculars"));
        accountItemArrayList.add(new AccountItem("SETTINGS"));
        accountItemArrayList.add(new AccountItem("Theme"));
        accountItemArrayList.add(new AccountItem("About"));
        accountItemArrayList.add(new AccountItem("Log Out"));



        AccountAdapter accountAdapter = new AccountAdapter(accountItemArrayList, getContext());
        listView.setAdapter(accountAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 1)  //basic info
                {
                    Intent intent = new Intent(getContext(),BasicInfoActivity.class);
                    startActivity(intent);
                }

                if (position == 2) //aspirations
                {
                    Intent intent = new Intent(getContext(),AspirationsActivity.class);
                    startActivity(intent);
                }

                if (position == 3) //educational details
                {
                    Intent intent = new Intent(getContext(),EducationalDetailsActivity.class);
                    startActivity(intent);
                }

                if (position == 4)  //letters of recommendation
                {
                    Intent intent = new Intent(getContext(),LettersOfRecommendationActivity.class);
                    startActivity(intent);
                }

                if (position == 5)  //work experience
                {
                    Intent intent = new Intent(getContext(),WorkExperienceActivity.class);
                    startActivity(intent);
                }

                if (position == 9) //theme
                {
                    int checked_item = 0;

                    if (sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO) - 1 >= 0)
                        checked_item = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO) - 1;

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

                    if (checked_item == 0)
                        radio_button_light.setChecked(true);
                    else if (checked_item == 1)
                        radio_button_dark.setChecked(true);
                    else
                        radio_button_set_by_battery_saver.setChecked(true);


                    alert_dialog_layout.addView(alert_dialog_title);
                    alert_dialog_layout.addView(radioGroup);

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setView(alert_dialog_layout);
                    alertDialog = builder.create();
                    alertDialog.show();

                    final AlertDialog finalAlertDialog = alertDialog;


                    radio_button_light.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            sharedPrefEditor.putInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);
//                            sharedPrefEditor.putInt("Fragment",2);
                            sharedPrefEditor.apply();
                            Log.e("fragment",""+sharedPref.getInt("Fragment",-1));
                            finalAlertDialog.dismiss();

                        }
                    });

                    radio_button_dark.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            sharedPrefEditor.putInt("Theme", AppCompatDelegate.MODE_NIGHT_YES);
//                            sharedPrefEditor.putInt("Fragment",2);
                            sharedPrefEditor.apply();
                            Log.e("fragment",""+sharedPref.getInt("Fragment",-1));
                            finalAlertDialog.dismiss();

                        }
                    });

                    radio_button_set_by_battery_saver.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                            sharedPrefEditor.putInt("Theme", AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
//                            sharedPrefEditor.putInt("Fragment",2);
                            sharedPrefEditor.apply();
                            Log.e("fragment",""+sharedPref.getInt("Fragment",-1));
                            finalAlertDialog.dismiss();


                        }
                    });


                }

                if (position == 10) //about
                {

                    Intent intent = new Intent(getContext(),AboutActivity.class);
                    startActivity(intent);
                }


                if (position == 11) //logout
                {
                    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
                    if (account != null)
                        Log.e("username",account.getDisplayName());

                    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestEmail()
                            .build();
                    GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
                    mGoogleSignInClient.signOut();

                    sharedPrefEditor.clear();
                    sharedPrefEditor.apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


                    getActivity().finishAffinity();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }


            }




        });


        return v;
    }
}
