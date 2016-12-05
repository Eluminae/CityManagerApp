package com.example.eddie.citymanager;

import org.json.JSONObject;

/**
 * Created by lp on 02/12/2016.
 */

public class ApiRequestElement {
    JSONObject json;

    public ApiRequestElement() {}

    public ApiRequestElement(JSONObject json) {
        this.json = json;
    }
}
