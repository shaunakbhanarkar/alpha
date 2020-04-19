package com.example.alpha;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;

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
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
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

        Log.i("Dashboard Fragment","started");

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        Log.d("Dashboard Fragment","layout inflated");

        SharedPreferences sharedPref = Objects.requireNonNull(getActivity().getSharedPreferences("Shared Preferences", MODE_PRIVATE));
        int theme = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);
        Log.d("Dashboard Fragment","current app theme is "+theme);

        SharedPreferences.Editor sharedPrefEditor = sharedPref.edit();
        Log.d("Dashboard Fragment","shared preferences opened for editing");

        sharedPrefEditor.putInt("Fragment", 0);
        Log.d("Dashboard Fragment","share preferences fragment value changed to 0");

        sharedPrefEditor.apply();
        Log.d("Dashboard Fragment","shared preferences changes applied");

        //initialise elements
        Log.d("Dashboard Fragment","initialising elements...");
        LinearLayout progress_box = v.findViewById(R.id.progress_box);
        TextView text_view_progress_bar = v.findViewById(R.id.text_view_progress_bar);
        ProgressBar progress_bar = v.findViewById(R.id.progress_bar);

        int[][] states = new int[][]{


                new int[]{android.R.attr.state_enabled} // enabled
        };

        int[] colors= new int[]{

                getResources().getColor(R.color.white)

        };

        if (theme == AppCompatDelegate.MODE_NIGHT_NO){
            progress_box.setBackground(getResources().getDrawable(R.drawable.rounded_corners_light));
            Log.d("Dashboard Fragment","progress box background set to rounded_corners_light");

            text_view_progress_bar.setTextColor(getResources().getColor(R.color.white));
            Log.d("Dashboard Fragment","progress bar text color set to white");


            colors= new int[]{

                    getResources().getColor(R.color.white)

            };
        }
        else if (theme == AppCompatDelegate.MODE_NIGHT_YES){
            progress_box.setBackground(getResources().getDrawable(R.drawable.rounded_corners_dark));
            Log.d("Dashboard Fragment","progress box background set to rounded_corners_dark");

            text_view_progress_bar.setTextColor(getResources().getColor(R.color.darkBackground));
            Log.d("Dashboard Fragment","progress bar text color set to dark background");


            colors= new int[]{

                    getResources().getColor(R.color.darkBackground)

            };
        }
        else {
            PowerManager powerManager = (PowerManager) getContext().getSystemService(Context.POWER_SERVICE);
            if (powerManager.isPowerSaveMode()){
                progress_box.setBackground(getResources().getDrawable(R.drawable.rounded_corners_dark));
                Log.d("Dashboard Fragment","progress box background set to rounded_corners_dark");

                text_view_progress_bar.setTextColor(getResources().getColor(R.color.darkBackground));
                Log.d("Dashboard Fragment","progress bar text color set to dark background");


                colors= new int[]{

                        getResources().getColor(R.color.darkBackground)

                };
            }
            else
            {
                progress_box.setBackground(getResources().getDrawable(R.drawable.rounded_corners_light));
                Log.d("Dashboard Fragment","progress box background set to rounded_corners_light");

                text_view_progress_bar.setTextColor(getResources().getColor(R.color.white));
                Log.d("Dashboard Fragment","progress bar text color set to white");

                colors= new int[]{

                        getResources().getColor(R.color.white)

                };
            }
        }

        ColorStateList colorStateList = new ColorStateList(states, colors);
        progress_bar.setProgressTintList(colorStateList);
        progress_bar.setProgressBackgroundTintList(colorStateList);
        Log.d("Dashboard Fragment","progress bar tints set");

        ListView listView = v.findViewById(R.id.list_view_dashboard);
        Log.d("Dashboard Fragment","listView initialised");

        ArrayList<DashboardItem> dashboardItemArrayList = new ArrayList<>();
        Log.d("Dashboard Fragment","dashboardItemArrayList created");

        dashboardItemArrayList.add(new DashboardItem(1,"abc"));
        Log.d("Dashboard Fragment","item added to dashboardItemArrayList - step 1");

        dashboardItemArrayList.add(new DashboardItem(2,"loiabc"));
        Log.d("Dashboard Fragment","item added to dashboardItemArrayList - step 2");

        dashboardItemArrayList.add(new DashboardItem(3,"zasdabc"));
        Log.d("Dashboard Fragment","item added to dashboardItemArrayList - step 3");

        dashboardItemArrayList.add(new DashboardItem(4,"abnkjhjbc"));
        Log.d("Dashboard Fragment","item added to dashboardItemArrayList - step 4");

        dashboardItemArrayList.add(new DashboardItem(5,"abckh"));
        Log.d("Dashboard Fragment","item added to dashboardItemArrayList - step 5");

        dashboardItemArrayList.add(new DashboardItem(6,"abcasf"));
        Log.d("Dashboard Fragment","item added to dashboardItemArrayList - step 6");

        dashboardItemArrayList.add(new DashboardItem(7,"abcfd"));
        Log.d("Dashboard Fragment","item added to dashboardItemArrayList - step 7");

        dashboardItemArrayList.add(new DashboardItem(8,"qpeirtygnbdl"));
        Log.d("Dashboard Fragment","item added to dashboardItemArrayList - step 8");

        dashboardItemArrayList.add(new DashboardItem(9,"omnjf"));
        Log.d("Dashboard Fragment","item added to dashboardItemArrayList - step 9");


        DashboardAdapter dashboardAdapter = new DashboardAdapter(dashboardItemArrayList, getContext());
        Log.d("Dashboard Fragment","dashboardAdapter created");

        listView.setAdapter(dashboardAdapter);
        Log.d("Dashboard Fragment","dashboardAdapter set to listView");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0)
                {
                    Log.d(this.toString(),"starting Step1Activity...");

                    Intent intent = new Intent(getContext(),Step1Activity.class);
                    startActivity(intent);
                }
                else if (position == 1)
                {
                    Log.d(this.toString(),"starting Step2Activity...");

                    Intent intent = new Intent(getContext(),Step2Activity.class);
                    startActivity(intent);
                }

                else if (position == 2)
                {
                    Log.d(this.toString(),"starting Step3Activity...");

                    Intent intent = new Intent(getContext(),Step3Activity.class);
                    startActivity(intent);
                }

                else if (position == 3)
                {
                    Log.d(this.toString(),"starting Step4Activity...");

                    Intent intent = new Intent(getContext(),Step4Activity.class);
                    startActivity(intent);
                }

                else if (position == 4)
                {
                    Log.d(this.toString(),"starting Step5Activity...");

                    Intent intent = new Intent(getContext(),Step5Activity.class);
                    startActivity(intent);
                }

                else if (position == 5)
                {
                    Log.d(this.toString(),"starting Step6Activity...");

                    Intent intent = new Intent(getContext(),Step6Activity.class);
                    startActivity(intent);
                }

                else if (position == 6)
                {
                    Log.d(this.toString(),"starting Step7Activity...");

                    Intent intent = new Intent(getContext(),Step7Activity.class);
                    startActivity(intent);
                }

                else if (position == 7)
                {
                    Log.d(this.toString(),"starting Step8Activity...");

                    Intent intent = new Intent(getContext(),Step8Activity.class);
                    startActivity(intent);
                }

                else if (position == 8)
                {
                    Log.d(this.toString(),"starting Step9Activity...");

                    Intent intent = new Intent(getContext(),Step9Activity.class);
                    startActivity(intent);
                }

            }
        });

        return v;
    }
}
