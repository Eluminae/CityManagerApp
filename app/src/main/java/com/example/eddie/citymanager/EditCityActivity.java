package com.example.eddie.citymanager;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;

public class EditCityActivity extends AppCompatActivity {

    City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_city);

        System.out.println("test");

        this.city = (City) getIntent().getSerializableExtra("city");

        EditText codePostal = (EditText) findViewById(R.id.codePostal);
        codePostal.setText(String.valueOf(this.city.codePostal));

        EditText codeINSEE = (EditText) findViewById(R.id.codeINSEE);
        codeINSEE.setText(String.valueOf(this.city.codeInsee));

        EditText codeRegion = (EditText) findViewById(R.id.codeRegion);
        codeRegion.setText(String.valueOf(this.city.codeRegion));

        EditText latitude = (EditText) findViewById(R.id.latitude);
        latitude.setText(String.valueOf(this.city.latitude));

        EditText longitude = (EditText) findViewById(R.id.longitude);
        longitude.setText(String.valueOf(this.city.longitude));

        EditText eloignement = (EditText) findViewById(R.id.eloignement);
        eloignement.setText(String.valueOf(this.city.eloignement));

        TextView nomVille = (TextView) findViewById(R.id.nomVille);
        nomVille.setText(this.city.maj);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton myButtonEdit= (FloatingActionButton) findViewById(R.id.ActionButtonEdit);
        myButtonEdit.setOnClickListener(editClickListener);

    }

    View.OnClickListener editClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            EditText codePostal = (EditText) findViewById(R.id.codePostal);
            EditText codeINSEE = (EditText) findViewById(R.id.codeINSEE);
            EditText codeRegion = (EditText) findViewById(R.id.codeRegion);
            EditText latitude = (EditText) findViewById(R.id.latitude);
            EditText longitude = (EditText) findViewById(R.id.longitude);
            EditText eloignement = (EditText) findViewById(R.id.eloignement);
            TextView nomVille = (TextView) findViewById(R.id.nomVille);

            // TODO creer city avec nouvelles valeurs et la return
            // this.city = new City(cityDataJson);

            Intent data = new Intent();
            City city = (City) getIntent().getSerializableExtra("city");
            data.putExtra("city", city);
            setResult(RESULT_OK, data);
            finish();
        }
    };
}
