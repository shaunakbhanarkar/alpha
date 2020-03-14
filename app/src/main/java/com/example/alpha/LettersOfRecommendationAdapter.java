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
        TextView name;
        TextView recommender;
        TextView desgination;
    }


    public LettersOfRecommendationAdapter(ArrayList<LettersOfRecommendationItem> data, Context context) {
        super(context, R.layout.aspirations_item, data);
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
            viewHolder.name = (TextView) convertView.findViewById(R.id.text_view_name);
            viewHolder.recommender = (TextView) convertView.findViewById(R.id.text_view_recommender);
            viewHolder.desgination = (TextView) convertView.findViewById(R.id.text_view_designation);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (LettersOfRecommendationAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }


        viewHolder.name.setText(lettersOfRecommendationItem.getName());
        viewHolder.recommender.setText(lettersOfRecommendationItem.getRecommender());
        viewHolder.desgination.setText(lettersOfRecommendationItem.getDesignation());

        SharedPreferences sharedPref = Objects.requireNonNull(getContext().getSharedPreferences("Shared Preferences", MODE_PRIVATE));
        int theme = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);


//        Typeface nexa_bold = getContext().getResources().getFont(R.font.nexa_bold);
//        viewHolder.name.setTypeface(nexa_bold);
//        viewHolder.name.setPadding(50,30,20,30);
//
//        Typeface nexa_light = getContext().getResources().getFont(R.font.nexa_light);
//
//        viewHolder.recommender.setTypeface(nexa_light);
//        viewHolder.recommender.setPadding(50,30,20,30);

        if (theme == AppCompatDelegate.MODE_NIGHT_NO) {
            viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.black));
            viewHolder.recommender.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
            viewHolder.desgination.setTextColor(getContext().getResources().getColor(R.color.black));
        }
        else if (theme == AppCompatDelegate.MODE_NIGHT_YES){
            viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.white));
            viewHolder.recommender.setTextColor(getContext().getResources().getColor(R.color.darkHighlight));
            viewHolder.desgination.setTextColor(getContext().getResources().getColor(R.color.white));

        }
        else{
            PowerManager powerManager = (PowerManager) getContext().getSystemService(Context.POWER_SERVICE);
            if (powerManager.isPowerSaveMode()){
                viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.white));
                viewHolder.recommender.setTextColor(getContext().getResources().getColor(R.color.darkHighlight));
                viewHolder.desgination.setTextColor(getContext().getResources().getColor(R.color.white));

            }
            else
            {
                viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.black));
                viewHolder.recommender.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                viewHolder.desgination.setTextColor(getContext().getResources().getColor(R.color.black));

            }
        }

        return convertView;

    }


}
