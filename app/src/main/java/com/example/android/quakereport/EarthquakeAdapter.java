package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
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
        DecimalFormat formatter = new DecimalFormat("0.0");
        String magOutput = formatter.format(currentEarthquake.getMagitude());
        Magitude.setText(magOutput);

        //set color for magnitude
        GradientDrawable magnitudeCircle = (GradientDrawable) Magitude.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagitude());
        magnitudeCircle.setColor(ContextCompat.getColor(getContext(), magnitudeColor));

        //split place string into 2 substrings
        String mainLocation;
        String offSet;
        String place = currentEarthquake.getPlace();
        int ofIndex = place.indexOf("of");
        if (ofIndex < 0) {
            offSet = "Near of";
            mainLocation = place;
        } else {
            offSet = place.substring(0, ofIndex + 2);
            mainLocation = place.substring(ofIndex + 3);
        }

        //set offset place
        TextView OffSet = (TextView) listItemView.findViewById(R.id.offSetPlaceView);
        OffSet.setText(offSet);

        //set place
        TextView Place = (TextView) listItemView.findViewById(R.id.placeView);
        Place.setText(mainLocation);

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

    private int getMagnitudeColor(double magnitude) {
        switch ((int) magnitude) {
            case 0:
                return R.color.magnitude1;
            case 1:
                return R.color.magnitude1;
            case 2:
                return R.color.magnitude2;
            case 3:
                return R.color.magnitude3;
            case 4:
                return R.color.magnitude4;
            case 5:
                return R.color.magnitude5;
            case 6:
                return R.color.magnitude6;
            case 7:
                return R.color.magnitude7;
            case 8:
                return R.color.magnitude8;
            case 9:
                return R.color.magnitude9;
            default:
                return R.color.magnitude10plus;
        }
    }
}
