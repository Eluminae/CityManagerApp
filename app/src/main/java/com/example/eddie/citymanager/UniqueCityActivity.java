package com.example.eddie.citymanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class UniqueCityActivity extends AppCompatActivity {

    City city;

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
        nomVille.setText(this.city.nomVille);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
