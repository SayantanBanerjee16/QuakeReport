package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Earthquake> {

    public ListAdapter(Context context , List<Earthquake> earthquakes){
        super(context,0 , earthquakes);
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magnitude = (TextView) listItemView.findViewById(R.id.magnitude);
        TextView location = (TextView) listItemView.findViewById(R.id.place);
        TextView date = (TextView) listItemView.findViewById(R.id.date);
        TextView time = (TextView) listItemView.findViewById(R.id.time);

        magnitude.setText(currentEarthquake.getMagnitude());
        location.setText(currentEarthquake.getLocation());
        date.setText(currentEarthquake.getDate());
        time.setText(currentEarthquake.getTime());


        return listItemView;
    }
}
