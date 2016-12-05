package com.example.eddie.citymanager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lp on 02/12/2016.
 */

public class City extends ApiRequestElement {

    String nomVille;
    String maj;
    int codePostal;
    int codeInsee;
    String codeRegion;
    float latitude;
    float longitude;
    float eloignement;
    int id;

    public City(JSONObject json) throws JSONException {
        super(json);

        this.nomVille = json.getString("Nom_Ville");
        this.maj = json.getString("MAJ");
        this.codePostal = json.getInt("Code_Postal");
        this.codeInsee = json.getInt("Code_INSEE");
        this.codeRegion = json.getString("Code_Region");
        this.latitude = json.getLong("Latitude");
        this.longitude = json.getLong("Longitude");
        this.eloignement = json.getLong("Eloignement");
        this.id = json.getInt("id");
    }

    @Override
    public String toString() {
        return this.maj;
    }
}
