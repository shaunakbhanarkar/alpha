package com.example.alpha;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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


        return convertView;

    }
}
