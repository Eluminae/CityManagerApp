package com.example.eddie.citymanager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by lp on 11/01/2017.
 */

public class ArrayAdapterCity extends ArrayAdapter<City> {

    Context mContext;
    int layoutResourceId;
    City cities[] = null;

    public ArrayAdapterCity(Context mContext, int layoutResourceId, City[] cities) {

        super(mContext, layoutResourceId, cities);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.cities = cities;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /*
         * The convertView argument is essentially a "ScrapView" as described is Lucas post
         * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
         * It will have a non-null value when ListView is asking you recycle the row layout.
         * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
         */
        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // object item based on the position
        City cityObject = cities[position];

        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView textViewItem = (TextView) convertView.findViewById(R.id.textViewItem);
        textViewItem.setText(cityObject.toString());
        textViewItem.setTag(cityObject.id);

        return convertView;

    }

}
