package com.example.alpha;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
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

public class LettersOfRecommendationAdapter extends ArrayAdapter<LettersOfRecommendationItem> {

    private ArrayList<LettersOfRecommendationItem> data;
    Context mContext;

    private static class ViewHolder {
        TextView recommender;
        TextView designation;
    }


    public LettersOfRecommendationAdapter(ArrayList<LettersOfRecommendationItem> data, Context context) {
        super(context, R.layout.letters_of_recommendation_item, data);
        this.data = data;
        this.mContext=context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Get the data item for this position
        LettersOfRecommendationItem lettersOfRecommendationItem = getItem(position);


        // Check if an existing view is being reused, otherwise inflate the view
        LettersOfRecommendationAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new LettersOfRecommendationAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.letters_of_recommendation_item, parent, false);
            viewHolder.recommender = (TextView) convertView.findViewById(R.id.text_view_recommender);
            viewHolder.designation = (TextView) convertView.findViewById(R.id.text_view_designation);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (LettersOfRecommendationAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }


        viewHolder.recommender.setText(lettersOfRecommendationItem.getRecommender());
        viewHolder.designation.setText(lettersOfRecommendationItem.getDesignation());

        SharedPreferences sharedPref = Objects.requireNonNull(getContext().getSharedPreferences("Shared Preferences", MODE_PRIVATE));
        int theme = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);


        viewHolder.recommender.setPadding(50,10,20,10);
        viewHolder.designation.setPadding(50,10,20,10);

        if (theme == AppCompatDelegate.MODE_NIGHT_NO) {
            viewHolder.recommender.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
            viewHolder.designation.setTextColor(getContext().getResources().getColor(R.color.black));
        }
        else if (theme == AppCompatDelegate.MODE_NIGHT_YES){
            viewHolder.recommender.setTextColor(getContext().getResources().getColor(R.color.darkHighlight));
            viewHolder.designation.setTextColor(getContext().getResources().getColor(R.color.white));

        }
        else{
            PowerManager powerManager = (PowerManager) getContext().getSystemService(Context.POWER_SERVICE);
            if (powerManager.isPowerSaveMode()){
                viewHolder.recommender.setTextColor(getContext().getResources().getColor(R.color.darkHighlight));
                viewHolder.designation.setTextColor(getContext().getResources().getColor(R.color.white));

            }
            else
            {
                viewHolder.recommender.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                viewHolder.designation.setTextColor(getContext().getResources().getColor(R.color.black));

            }
        }

        return convertView;

    }


}
