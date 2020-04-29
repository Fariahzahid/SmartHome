package com.example.smart_home.Contact_Person_Screen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.smart_home.R;

public class MyAdapter extends ArrayAdapter<String> {

    String [] names;
    int [] flags;
    Context mcontext;

    public MyAdapter(@NonNull Context context, String[] countryNames,int[] countryFlags) {

        super(context, R.layout.recycleview_cardview);
        this.names = countryNames;
        this.flags = countryFlags;
        this.mcontext = context;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.recycleview_cardview,parent,false);
        return super.getView(position,convertView,parent);
    }
}
