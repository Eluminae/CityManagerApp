package com.example.eddie.citymanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncResponse{

    private ListView listview;
    private City[] cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listview = (ListView) findViewById(R.id.listview);

        HashMap params = new HashMap();
        HashMap attributs = new HashMap();
        GetApi apiRequest = new GetApi("city", params, attributs);
        // On créer l'async task
        RequestTask apiRequestTask = new RequestTask();
        apiRequestTask.delegate = this;
        apiRequest.execute(apiRequestTask);


    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

    //this override the implemented method from asyncTask
    @Override
    public void processFinish(ArrayList<ApiRequestElement> cities){
        if (cities == null) cities = new ArrayList<ApiRequestElement>();

        City[] citiesTable = new City[cities.size()];
        for (int i=0; i<cities.size(); i++) {
            citiesTable[i] = (City) cities.get(i);
        }

        this.cities = citiesTable;

        // our adapter instance
        ArrayAdapterCity adapter = new ArrayAdapterCity(this, R.layout.list_view, citiesTable);

        // create a new ListView, set the adapter and item click listener

        this.listview.setAdapter(adapter);
        this.listview.setOnItemClickListener(new OnItemClickListenerListViewItem(citiesTable));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (data.hasExtra("action")) {
                //Toast.makeText(this, data.getExtras().getString("action"), Toast.LENGTH_SHORT).show();
                String action = data.getExtras().getString("action");

                switch(action) {
                    case "delete":
                        if (data.hasExtra("cityId")) {
                            int cityId = Integer.valueOf(data.getExtras().getString("cityId"));
                            City[] cities = new City[this.cities.length - 1];
                            int j =0;
                            City tempCity = null;
                            for (int i = 0; i < this.cities.length; i++) {
                                if (this.cities[i].id != cityId) {
                                    cities[j] = this.cities[i];
                                    j++;
                                } else {
                                    tempCity = this.cities[i];
                                }
                            }
                            this.cities = cities;
                            refreshListView();
                            Toast.makeText(this, "Ville "+tempCity.maj+" supprimée", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "update":
                        if (data.hasExtra("city")) {
                            City city = (City) data.getSerializableExtra("city");
                            for (int i = 0; i < this.cities.length - 1; i++) {
                                if (this.cities[i].id == city.id) {
                                    this.cities[i] = city;
                                }
                            }
                            refreshListView();
                            Toast.makeText(this, "Ville modifiée", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        }
    }

    public void refreshListView() {
        // our adapter instance
        ArrayAdapterCity adapter = new ArrayAdapterCity(this, R.layout.list_view, this.cities);
        // create a new ListView, set the adapter and item click listener
        this.listview.setAdapter(adapter);
        this.listview.setOnItemClickListener(new OnItemClickListenerListViewItem(this.cities));
    }
}




