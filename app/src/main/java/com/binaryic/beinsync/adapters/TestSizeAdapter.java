package com.binaryic.beinsync.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.binaryic.beinsync.R;

import java.util.ArrayList;

public class TestSizeAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> array_Data;

    public TestSizeAdapter(Activity context, int textViewResourceId, ArrayList<String> array_Data) {
            super(context, textViewResourceId, array_Data);
        this.context = context;
        this.array_Data = array_Data;
    }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater= context.getLayoutInflater();
            View row=inflater.inflate(R.layout.text_size_list_view, parent, false);
            TextView tv_Size=(TextView)row.findViewById(R.id.tv_Size);
            tv_Size.setText(array_Data.get(position));
            return row;
            }
        }