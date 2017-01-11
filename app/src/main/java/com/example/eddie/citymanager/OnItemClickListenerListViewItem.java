package com.example.eddie.citymanager;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lp on 11/01/2017.
 */

    public class OnItemClickListenerListViewItem implements OnItemClickListener {

    private City[] citiesTable;

    public OnItemClickListenerListViewItem(City[] citiesTable) {
        this.citiesTable = citiesTable;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Context context = view.getContext();

        TextView textViewItem = ((TextView) view.findViewById(R.id.textViewItem));

        // get the clicked item name
        String listItemText = textViewItem.getText().toString();

        // get the clicked item ID
        String listItemId = textViewItem.getTag().toString();

        Intent intent = new Intent(view.getContext(), UniqueCityActivity.class);
        intent.putExtra("cityId", listItemId);
        intent.putExtra("city", this.citiesTable[position]);
        ((MainActivity) context).startActivityForResult(intent, 0);

        // just toast it
        Toast.makeText(context, "Item: " + listItemText + ", Item ID: " + listItemId, Toast.LENGTH_SHORT).show();
    }
}
