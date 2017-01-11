package com.example.eddie.citymanager;

import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by lp on 02/12/2016.
 */

public class GetApi {

    private String url = "http://android.misterbanal.net/";
    private String getter;
    private RequestTask apiRequest = null;

    public GetApi(String getter, HashMap params, HashMap attributs){
        this.getter = getter;

        this.url += getter + '/';

        // Add parameters
        /* Display content using Iterator*/
        Set set = params.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            this.url += mentry.getKey() + "/" + mentry.getValue() + "/";
        }

        // Add attributs
        /* Display content using Iterator*/
        set = attributs.entrySet();
        iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            this.url += "?" + mentry.getKey() + "=" + mentry.getValue() + "&";
        }
    }

    public void execute(RequestTask apiRequest) {
        setApiRequest(apiRequest);
        this.apiRequest.execute(this.url, this.getter);
    }

    private void setApiRequest(RequestTask apiRequest) {
        this.apiRequest = apiRequest;
    }
}
