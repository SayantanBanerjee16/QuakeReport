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

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.UserHandle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    ListView earthquakeListView;

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    public void ArrayListFun(List<Earthquake> earthquakes2){


        final ListAdapter adapter = new ListAdapter(EarthquakeActivity.this,earthquakes2);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake currentEarthquake = adapter.getItem(position);
                /* Other method by calling Implicit Intent (Website Intent)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(websiteIntent); */

                Intent intent = new Intent(EarthquakeActivity.this,WebsiteActivity.class);
                intent.putExtra("url",currentEarthquake.getUrl());
                startActivity(intent);
            }
        });
    }

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    public class DownloadTask extends AsyncTask<String, Void , List<Earthquake>>{

        @Override
        protected List<Earthquake> doInBackground(String... strings) {
            if (strings.length < 1 || strings[0] == null) {
                return null;
            }
            List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(strings[0]);
            return earthquakes;
        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakes) {
            super.onPostExecute(earthquakes);
//            if (earthquakes == null) {
//                return;
//            }
            ArrayListFun(earthquakes);

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        earthquakeListView =  (ListView) findViewById(R.id.list);

        DownloadTask task = new DownloadTask();
        try{
            task.execute(USGS_REQUEST_URL);
        }
        catch (Exception e ){
            e.printStackTrace();
        }
    }
}
