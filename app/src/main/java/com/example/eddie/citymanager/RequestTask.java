package com.example.eddie.citymanager;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by lp on 02/12/2016.
 */

class RequestTask extends AsyncTask<Object, Void, ArrayList<ApiRequestElement>> {

    private Exception exception;
    public AsyncResponse delegate = null;

    protected ArrayList<ApiRequestElement> doInBackground(Object... urls) {

        ArrayList<ApiRequestElement> elements = new ArrayList<ApiRequestElement>();

        try {
            URL url = new URL((String)urls[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            /*
             * InputStreamOperations est une classe complémentaire:
             * Elle contient une méthode InputStreamToString.
             */
            String result = InputStreamOperations.InputStreamToString(inputStream);

            // On récupère le tableau d'objets qui nous concernent
            JSONArray array = new JSONArray(result);
            // Pour tous les objets on récupère les infos
            for (int i = 0; i < array.length(); i++) {
                // On récupère un objet JSON du tableau
                JSONObject obj = new JSONObject(array.getString(i));
                // On fait le lien ApiRequestElement - Objet JSON
                ApiRequestElement requestElement = generateObject((String)urls[1], obj);
                // On ajoute la personne à la liste
                elements.add(requestElement);
                //System.out.println(requestElement);
            }

        } catch (Exception e) {
            this.exception = e;
            return null;
        }
        // On retourne la liste
        return elements;
    }

    @Override
    protected void onPostExecute(ArrayList<ApiRequestElement> result) {
        delegate.processFinish(result);
    }

    public ApiRequestElement generateObject(String className, JSONObject json) throws JSONException {
        switch (className) {
            case "city" : return new City(json);
            //case "user" : return new User(json);
        }
        return null;
    }
}
