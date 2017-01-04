package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by dauto98 on 04/01/2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Earthquake>> {

    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    private String stringUrl;

    public EarthquakeLoader(Context context, String url){
        super(context);
        stringUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Earthquake> loadInBackground() {
            if (stringUrl == null){
                return null;
            }

            URL url = QueryUtils.createUrl(stringUrl);

            String JSONresponse = null;
            try {
                JSONresponse = QueryUtils.makeHttpRequest(url);
            } catch (IOException e) {
                Log.e(LOG_TAG, "problem on creating JSON response from input stream");
            }

            // Create a list of earthquakes.
            ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes(JSONresponse);
            return earthquakes;
    }


}
