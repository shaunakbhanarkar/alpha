package com.example.alpha;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;
import java.util.Objects;

import javax.xml.namespace.QName;

import static android.content.Context.MODE_PRIVATE;

public class DashboardAdapter extends ArrayAdapter<DashboardItem> {

    private ArrayList<DashboardItem> data;
    Context mContext;




    private static class ViewHolder {
        TextView name;
        TextView step;
        ImageView image;
    }

    public DashboardAdapter(ArrayList<DashboardItem> data, Context context) {
        super(context, R.layout.dashboard_item, data);
        this.data = data;
        this.mContext=context;
    }


    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Get the data item for this position
        DashboardItem dashboardItem = getItem(position);


        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dashboard_item, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.text_view_name);
            viewHolder.step = (TextView) convertView.findViewById(R.id.text_view_step);
            viewHolder.image = convertView.findViewById(R.id.image_view_status);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        viewHolder.name.setText(dashboardItem.getName());
        viewHolder.step.setText("STEP " + dashboardItem.getStep());
        viewHolder.image.setImageDrawable(mContext.getDrawable(R.drawable.icon_checked));

        SharedPreferences sharedPref = Objects.requireNonNull(getContext().getSharedPreferences("Shared Preferences", MODE_PRIVATE));
        int theme = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);

        if (theme == AppCompatDelegate.MODE_NIGHT_NO){
            viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
            viewHolder.step.setTextColor(getContext().getResources().getColor(R.color.black));
        }
        else if (theme == AppCompatDelegate.MODE_NIGHT_YES){
            viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.darkHighlight));
            viewHolder.step.setTextColor(getContext().getResources().getColor(R.color.white));
        }
        else {
            PowerManager powerManager = (PowerManager) getContext().getSystemService(Context.POWER_SERVICE);
            if (powerManager.isPowerSaveMode()){
                viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.darkHighlight));
                viewHolder.step.setTextColor(getContext().getResources().getColor(R.color.white));
            }
            else
            {
                viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                viewHolder.step.setTextColor(getContext().getResources().getColor(R.color.black));
            }
        }

        return convertView;

    }
}
