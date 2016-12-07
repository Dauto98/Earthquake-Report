package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        Magitude.setText(Double.toString(currentEarthquake.getMagitude()));

        //set place
        TextView Place = (TextView) listItemView.findViewById(R.id.placeView);
        Place.setText(currentEarthquake.getPlace());

        //change date and time from UNIX time to normal time format
        long timeInMiliSec = Long.parseLong(currentEarthquake.getTime());
        Date dateObject = new Date(timeInMiliSec);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        String dateToDisplay = dateFormatter.format(dateObject);

        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
        String timeToDisplay = timeFormatter.format(dateObject);

        //set date
        TextView Date = (TextView) listItemView.findViewById(R.id.dateView);
        Date.setText(dateToDisplay);

        //set time
        TextView Time = (TextView) listItemView.findViewById(R.id.timeView);
        Time.setText(timeToDisplay);

        return listItemView;
    }
}
