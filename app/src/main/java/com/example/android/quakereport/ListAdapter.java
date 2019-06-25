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

    private static final String LOCATION_SEPARATOR = " of ";

    public ListAdapter(Context context , List<Earthquake> earthquakes){
        super(context,0 , earthquakes);
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        String primaryLocation;
        String locationOffset;



        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magnitude = (TextView) listItemView.findViewById(R.id.magnitude);
        TextView date = (TextView) listItemView.findViewById(R.id.date);
        TextView time = (TextView) listItemView.findViewById(R.id.time);

        magnitude.setText(currentEarthquake.getMagnitude());
        date.setText(currentEarthquake.getDate());
        time.setText(currentEarthquake.getTime());

        if (currentEarthquake.getLocation().contains(LOCATION_SEPARATOR)) {
            String[] parts = currentEarthquake.getLocation().split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = currentEarthquake.getLocation();
        }

        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.offset_location);
        locationOffsetView.setText(locationOffset);


        return listItemView;
    }
}
