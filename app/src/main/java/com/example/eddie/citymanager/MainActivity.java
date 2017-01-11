package com.example.eddie.citymanager;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncResponse{

    private ListView listview;

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

        // our adapter instance
        ArrayAdapterCity adapter = new ArrayAdapterCity(this, R.layout.list_view, citiesTable);

        // create a new ListView, set the adapter and item click listener

        this.listview.setAdapter(adapter);
        this.listview.setOnItemClickListener(new OnItemClickListenerListViewItem(citiesTable));

    }
}




