package com.example.eddie.citymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class UniqueCityActivity extends AppCompatActivity implements OnMapReadyCallback {

    City city;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unique_city);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.city = (City) getIntent().getSerializableExtra("city");

        TextView codePostal = (TextView) findViewById(R.id.codePostal);
        codePostal.setText(String.valueOf(this.city.codePostal));

        TextView codeINSEE = (TextView) findViewById(R.id.codeINSEE);
        codeINSEE.setText(String.valueOf(this.city.codeInsee));

        TextView codeRegion = (TextView) findViewById(R.id.codeRegion);
        codeRegion.setText(String.valueOf(this.city.codeRegion));

        TextView latitude = (TextView) findViewById(R.id.latitude);
        latitude.setText(String.valueOf(this.city.latitude));

        TextView longitude = (TextView) findViewById(R.id.longitude);
        longitude.setText(String.valueOf(this.city.longitude));

        TextView eloignement = (TextView) findViewById(R.id.eloignement);
        eloignement.setText(String.valueOf(this.city.eloignement));

        TextView nomVille = (TextView) findViewById(R.id.nomVille);
        nomVille.setText(this.city.maj);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Set listener on material flats buttons
        //Retrieve the button object
        FloatingActionButton myButtonDelete = (FloatingActionButton) findViewById(R.id.ActionButtonDelete);
        //Attach the listener
        myButtonDelete.setOnClickListener(deleteClickListener);
        //Retrieve the button object
        FloatingActionButton myButtonEdit = (FloatingActionButton)findViewById(R.id.ActionButtonEdit);
        //Attach the listener
        myButtonEdit.setOnClickListener(editClickListener);
    }

    //Listener object to handle the click events
    View.OnClickListener deleteClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            // TODO: delete from BDD
            // Delete from mainActivity list
            Intent data = new Intent();
            City city = (City) getIntent().getSerializableExtra("city");
            data.putExtra("cityId", String.valueOf(city.id));
            data.putExtra("action", "delete");
            // Activity finished ok, return the data
            setResult(RESULT_OK, data);
            finish();
        }
    };

    //Listener object to handle the click events
    View.OnClickListener editClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), EditCityActivity.class);
            intent.putExtra("city", city);
            startActivityForResult(intent, 0);
        }
    };

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ville = new LatLng((double) this.city.latitude, (double) this.city.longitude);
        mMap.addMarker(new MarkerOptions().position(ville).title(this.city.nomVille+" - "+this.city.codePostal));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ville, 10));
    }

}
