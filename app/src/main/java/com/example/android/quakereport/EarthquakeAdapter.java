package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bach Lap on 12/3/2016.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquake) {
        super(context, 0, earthquake);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.eathquake_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        //set magitude
        TextView Magitude = (TextView) listItemView.findViewById(R.id.magitudeView);
        Magitude.setText(Float.toString(currentEarthquake.getMagitude()));

        //set place
        TextView Place = (TextView) listItemView.findViewById(R.id.placeView);
        Place.setText(currentEarthquake.getPlace());

        //set time
        TextView Time = (TextView) listItemView.findViewById(R.id.timeView);
        Time.setText(currentEarthquake.getTime());

        return listItemView;
    }
}
