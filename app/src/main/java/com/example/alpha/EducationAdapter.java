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

public class EducationAdapter extends ArrayAdapter<EducationItem> {

    private ArrayList<EducationItem> data;
    Context mContext;

    private static class ViewHolder {
        TextView name;
        TextView value;
    }

    public EducationAdapter(ArrayList<EducationItem> data, Context context) {
        super(context, R.layout.education_item, data);
        this.data = data;
        this.mContext=context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Get the data item for this position
        EducationItem educationItem = getItem(position);


        // Check if an existing view is being reused, otherwise inflate the view
        EducationAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new EducationAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.education_item, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.text_view_name);
            viewHolder.value = (TextView) convertView.findViewById(R.id.text_view_value);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (EducationAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }


        viewHolder.name.setText(educationItem.getName());
        viewHolder.value.setText(educationItem.getValue());

        SharedPreferences sharedPref = Objects.requireNonNull(getContext().getSharedPreferences("Shared Preferences", MODE_PRIVATE));
        int theme = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);


        Typeface nexa_bold = getContext().getResources().getFont(R.font.nexa_bold);
        viewHolder.name.setTypeface(nexa_bold);
        viewHolder.name.setPadding(50,30,20,30);

        Typeface nexa_light = getContext().getResources().getFont(R.font.nexa_light);
        viewHolder.value.setTypeface(nexa_light);
        viewHolder.value.setPadding(50,30,20,30);

        if (theme == AppCompatDelegate.MODE_NIGHT_NO) {
            viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.black));
            viewHolder.value.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
        }
        else if (theme == AppCompatDelegate.MODE_NIGHT_YES){
            viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.white));
            viewHolder.value.setTextColor(getContext().getResources().getColor(R.color.darkHighlight));
        }
        else{
            PowerManager powerManager = (PowerManager) getContext().getSystemService(Context.POWER_SERVICE);
            if (powerManager.isPowerSaveMode()){
                viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.white));
                viewHolder.value.setTextColor(getContext().getResources().getColor(R.color.darkHighlight));
            }
            else
            {
                viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.black));
                viewHolder.value.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
            }
        }

        return convertView;

    }


}
