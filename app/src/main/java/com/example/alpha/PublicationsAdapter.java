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

public class PublicationsAdapter extends ArrayAdapter<PublicationsItem> {

    private ArrayList<PublicationsItem> data;
    Context mContext;

    private static class ViewHolder {
        TextView title;
        TextView publication;
    }


    public PublicationsAdapter(ArrayList<PublicationsItem> data, Context context) {
        super(context, R.layout.publications_item, data);
        this.data = data;
        this.mContext=context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Get the data item for this position
        PublicationsItem publicationsItem = getItem(position);


        // Check if an existing view is being reused, otherwise inflate the view
        PublicationsAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new PublicationsAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.publications_item, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.text_view_title);
            viewHolder.publication = (TextView) convertView.findViewById(R.id.text_view_publication);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (PublicationsAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }


        viewHolder.title.setText(publicationsItem.getTitle());
        viewHolder.publication.setText(publicationsItem.getPublication());

        SharedPreferences sharedPref = Objects.requireNonNull(getContext().getSharedPreferences("Shared Preferences", MODE_PRIVATE));
        int theme = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);


        viewHolder.title.setPadding(50,10,20,10);
        viewHolder.publication.setPadding(50,10,20,10);

        if (theme == AppCompatDelegate.MODE_NIGHT_NO) {
            viewHolder.title.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
            viewHolder.publication.setTextColor(getContext().getResources().getColor(R.color.black));
        }
        else if (theme == AppCompatDelegate.MODE_NIGHT_YES){
            viewHolder.title.setTextColor(getContext().getResources().getColor(R.color.darkHighlight));
            viewHolder.publication.setTextColor(getContext().getResources().getColor(R.color.white));

        }
        else{
            PowerManager powerManager = (PowerManager) getContext().getSystemService(Context.POWER_SERVICE);
            if (powerManager.isPowerSaveMode()){
                viewHolder.title.setTextColor(getContext().getResources().getColor(R.color.darkHighlight));
                viewHolder.publication.setTextColor(getContext().getResources().getColor(R.color.white));

            }
            else
            {
                viewHolder.title.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                viewHolder.publication.setTextColor(getContext().getResources().getColor(R.color.black));

            }
        }

        return convertView;

    }


}
