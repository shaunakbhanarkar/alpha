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

public class GREGeneralAdapter extends ArrayAdapter<GREGeneralItem> {

    private ArrayList<GREGeneralItem> data;
    Context mContext;

    private static class ViewHolder {
        TextView name;
    }

    public GREGeneralAdapter(ArrayList<GREGeneralItem> data, Context context) {
        super(context, R.layout.gre_general_item, data);
        this.data = data;
        this.mContext=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Get the data item for this position
        GREGeneralItem greGeneralItem = getItem(position);


        // Check if an existing view is being reused, otherwise inflate the view
        GREGeneralAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new GREGeneralAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.gre_general_item, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.text_view_name);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GREGeneralAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }


        viewHolder.name.setText(greGeneralItem.getName());

        SharedPreferences sharedPref = Objects.requireNonNull(getContext().getSharedPreferences("Shared Preferences", MODE_PRIVATE));
        int theme = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);



        viewHolder.name.setPadding(50,30,20,30);


        if (theme == AppCompatDelegate.MODE_NIGHT_NO) {
            viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.black));
        }
        else if (theme == AppCompatDelegate.MODE_NIGHT_YES){
            viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.white));
        }
        else{
            PowerManager powerManager = (PowerManager) getContext().getSystemService(Context.POWER_SERVICE);
            if (powerManager.isPowerSaveMode()){
                viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.white));
            }
            else
            {
                viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.black));
            }
        }

        return convertView;

    }
}
