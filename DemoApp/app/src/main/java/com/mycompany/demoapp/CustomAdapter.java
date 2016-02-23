package com.mycompany.demoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, String[] subjects) {
        super(context, R.layout.custom_item, subjects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.custom_item, parent, false);

        String singleSubjectItem = getItem(position);
        TextView itemText = (TextView)customView.findViewById(R.id.itemText);
        ImageView itemImage = (ImageView)customView.findViewById(R.id.itemImage);

        itemText.setText(singleSubjectItem);
        itemImage.setImageResource(R.mipmap.math);

        return customView;
    }
}

