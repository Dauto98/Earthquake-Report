/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private static final String USGS_URL =
            "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        new EarthquakeAsyncTask().execute(USGS_URL);
    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>>{
        @Override
        protected ArrayList<Earthquake> doInBackground(String... stringUrl) {
            if (stringUrl.length < 1 || stringUrl[0] == null){
                return null;
            }

            URL url = QueryUtils.createUrl(stringUrl[0]);

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

        @Override
        protected void onPostExecute(final ArrayList<Earthquake> earthquakes) {
            // Find a reference to the {@link ListView} in the layout
            ListView earthquakeListView = (ListView) findViewById(R.id.list);

            // Create a new {@link ArrayAdapter} of earthquakes
            EarthquakeAdapter adapter = new EarthquakeAdapter(EarthquakeActivity.this, earthquakes);

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            earthquakeListView.setAdapter(adapter);

            //set intent to go to the web page about earthquake
            earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Earthquake earthquakeView = earthquakes.get(i);
                    Intent goToWeb = new Intent(Intent.ACTION_VIEW, Uri.parse(earthquakeView.getURL()));
                    if (goToWeb.resolveActivity(getPackageManager()) != null) {
                        startActivity(goToWeb);
                    }
                }
            });
        }
    }
}