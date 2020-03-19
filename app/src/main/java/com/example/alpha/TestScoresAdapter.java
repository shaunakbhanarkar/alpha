package com.example.alpha;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class TestScoresAdapter extends ArrayAdapter<TestScoresItem> {

    private ArrayList<TestScoresItem> data;
    Context mContext;

    private static class ViewHolder {
        TextView test_name;
        TextView test_score;
    }

    public TestScoresAdapter(ArrayList<TestScoresItem> data, Context context) {
        super(context, R.layout.test_scores_item, data);
        this.data = data;
        this.mContext=context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Get the data item for this position
        TestScoresItem testScoresItem = getItem(position);


        // Check if an existing view is being reused, otherwise inflate the view
        TestScoresAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new TestScoresAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.test_scores_item, parent, false);
            viewHolder.test_name = (TextView) convertView.findViewById(R.id.text_view_test_name);
            viewHolder.test_score = (TextView) convertView.findViewById(R.id.text_view_test_score);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (TestScoresAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }


        viewHolder.test_name.setText(testScoresItem.getTest_name());
        viewHolder.test_score.setText(testScoresItem.getTest_score());

        SharedPreferences sharedPref = Objects.requireNonNull(getContext().getSharedPreferences("Shared Preferences", MODE_PRIVATE));
        int theme = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);


        viewHolder.test_name.setPadding(50,10,20,10);
        viewHolder.test_score.setPadding(50,10,20,10);

        if (theme == AppCompatDelegate.MODE_NIGHT_NO) {
            viewHolder.test_score.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
            viewHolder.test_name.setTextColor(getContext().getResources().getColor(R.color.black));
        }
        else if (theme == AppCompatDelegate.MODE_NIGHT_YES){
            viewHolder.test_score.setTextColor(getContext().getResources().getColor(R.color.darkHighlight));
            viewHolder.test_name.setTextColor(getContext().getResources().getColor(R.color.white));

        }
        else{
            PowerManager powerManager = (PowerManager) getContext().getSystemService(Context.POWER_SERVICE);
            if (powerManager.isPowerSaveMode()){
                viewHolder.test_score.setTextColor(getContext().getResources().getColor(R.color.darkHighlight));
                viewHolder.test_name.setTextColor(getContext().getResources().getColor(R.color.white));

            }
            else
            {
                viewHolder.test_score.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                viewHolder.test_name.setTextColor(getContext().getResources().getColor(R.color.black));

            }
        }

        return convertView;

    }


}
