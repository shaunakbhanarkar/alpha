package com.example.alpha;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExploreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploreFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    //shared preferences
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedPrefEditor;




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExploreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExploreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExploreFragment newInstance(String param1, String param2) {
        ExploreFragment fragment = new ExploreFragment();
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
        Log.i(this.toString(),"started");

        View v = inflater.inflate(R.layout.fragment_explore, container, false);
        Log.d(this.toString(),"layout inflated");

        //shared preferences
        sharedPref = Objects.requireNonNull(getActivity()).getSharedPreferences("Shared Preferences", MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
        Log.d(this.toString(),"shared preferences opened for editing");

        sharedPrefEditor.putInt("Fragment",1);
        Log.d(this.toString(),"share preferences fragment value changed to 1");

        sharedPrefEditor.apply();
        Log.d(this.toString(),"shared preferences changes applied");


        final int theme = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);
        Log.d(this.toString(),"current app theme is "+theme);



        //initialise elements

        ListView listView = v.findViewById(R.id.list_view_explore);
        Log.d(this.toString(),"listView initialised");

        ArrayList<ExploreItem> exploreItemArrayList = new ArrayList<>();
        Log.d(this.toString(),"exploreItemArrayList created");

        exploreItemArrayList.add(new ExploreItem("GRE","Graduate Record Examinations"));
        Log.d(this.toString(),"item added to exploreItemArrayList - GRE");

        exploreItemArrayList.add(new ExploreItem("TOEFL","Test of English as a Foreign Language"));
        Log.d(this.toString(),"item added to exploreItemArrayList - TOEFL");

        exploreItemArrayList.add(new ExploreItem("IELTS","International English Language Testing System"));
        Log.d(this.toString(),"item added to exploreItemArrayList - IELTS");

        ExploreAdapter exploreAdapter = new ExploreAdapter(exploreItemArrayList, getContext());
        Log.d(this.toString(),"exploreAdapter created");

        listView.setAdapter(exploreAdapter);
        Log.d(this.toString(),"exploreAdapter set to listView");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0 ) //gre
                {
                    Log.d(this.toString(), "starting GREActivity...");

                    Intent intent = new Intent(getContext(),GREActivity.class);
                    startActivity(intent);
                }

            }
        });

        return v;
    }
}
