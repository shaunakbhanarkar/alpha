package com.example.alpha;

import android.animation.TypeEvaluator;
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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class AccountAdapter extends ArrayAdapter<AccountItem> {

    private ArrayList<AccountItem> data;
    Context mContext;

    private static class ViewHolder {
        TextView name;

    }

    public AccountAdapter(ArrayList<AccountItem> data, Context context) {
        super(context, R.layout.account_item, data);
        this.data = data;
        this.mContext=context;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Get the data item for this position
        AccountItem accountItem = getItem(position);


        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.account_item, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.text_view_name);


            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        viewHolder.name.setText(accountItem.getName());

        SharedPreferences sharedPref = Objects.requireNonNull(getContext().getSharedPreferences("Shared Preferences", MODE_PRIVATE));
        int theme = sharedPref.getInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);

        if (position == 0 || position == 9){
            Typeface nexa_bold = getContext().getResources().getFont(R.font.nexa_bold);
            viewHolder.name.setTypeface(nexa_bold);
            viewHolder.name.setTextSize(15);

            if (theme == AppCompatDelegate.MODE_NIGHT_NO)
                viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
            else if (theme == AppCompatDelegate.MODE_NIGHT_YES)
                viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.darkHighlight));
            else {
                PowerManager powerManager = (PowerManager) getContext().getSystemService(Context.POWER_SERVICE);
                if (powerManager.isPowerSaveMode()){
                    viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.darkHighlight));
                }
                else
                {
                    viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                }
            }

            viewHolder.name.setPadding(50,50,50,30);
            viewHolder.name.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        else {
            Typeface nexa_light = getContext().getResources().getFont(R.font.nexa_light);
            viewHolder.name.setTypeface(nexa_light);
            viewHolder.name.setPadding(50,30,20,30);

            if (theme == AppCompatDelegate.MODE_NIGHT_NO)
                viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.black));
            else if (theme == AppCompatDelegate.MODE_NIGHT_YES)
                viewHolder.name.setTextColor(getContext().getResources().getColor(R.color.white));
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
        }
        return convertView;

    }

    @Override
    public boolean isEnabled(int position){
        if (position == 0 || position == 9)
            return false;
        return true;
    }
}
