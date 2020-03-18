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

public class WorkExperienceAdapter extends ArrayAdapter<WorkExperienceItem> {


    private ArrayList<WorkExperienceItem> data;
    Context mContext;

    private static class ViewHolder {
        TextView company;
        TextView role;
        TextView experience_duration;
    }

    public WorkExperienceAdapter(ArrayList<WorkExperienceItem> data, Context context) {
        super(context, R.layout.work_experience_item, data);
        this.data = data;
        this.mContext=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Get the data item for this position
        WorkExperienceItem workExperienceItem = getItem(position);


        // Check if an existing view is being reused, otherwise inflate the view
        WorkExperienceAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new WorkExperienceAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.work_experience_item, parent, false);
            viewHolder.company = (TextView) convertView.findViewById(R.id.text_view_company);
            viewHolder.role = (TextView) convertView.findViewById(R.id.text_view_role);
            viewHolder.experience_duration = (TextView) convertView.findViewById(R.id.text_view_experience_duration);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (WorkExperienceAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }


        viewHolder.company.setText(workExperienceItem.getCompany());
        viewHolder.role.setText(workExperienceItem.getRole());
        viewHolder.experience_duration.setText(workExperienceItem.getExperience_duration());

        SharedPreferences sharedPref = Objects.requireNonNull(getContext().getSharedPreferences("Shared Preferences", MODE_PRIVATE));
        int theme = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);


        viewHolder.company.setPadding(50,10,20,10);
        viewHolder.role.setPadding(50,10,20,10);
        viewHolder.experience_duration.setPadding(50,10,20,10);


        if (theme == AppCompatDelegate.MODE_NIGHT_NO) {
            viewHolder.company.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
            viewHolder.role.setTextColor(getContext().getResources().getColor(R.color.black));
            viewHolder.experience_duration.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
        }
        else if (theme == AppCompatDelegate.MODE_NIGHT_YES){
            viewHolder.company.setTextColor(getContext().getResources().getColor(R.color.darkHighlight));
            viewHolder.role.setTextColor(getContext().getResources().getColor(R.color.white));
            viewHolder.experience_duration.setTextColor(getContext().getResources().getColor(R.color.darkHighlight));
        }
        else{
            PowerManager powerManager = (PowerManager) getContext().getSystemService(Context.POWER_SERVICE);
            if (powerManager.isPowerSaveMode()){
                viewHolder.company.setTextColor(getContext().getResources().getColor(R.color.darkHighlight));
                viewHolder.role.setTextColor(getContext().getResources().getColor(R.color.white));
                viewHolder.experience_duration.setTextColor(getContext().getResources().getColor(R.color.darkHighlight));
            }
            else
            {
                viewHolder.company.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                viewHolder.role.setTextColor(getContext().getResources().getColor(R.color.black));
                viewHolder.experience_duration.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
            }
        }

        return convertView;

    }

}
