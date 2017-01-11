package com.example.eddie.citymanager;

import java.util.ArrayList;

/**
 * Created by lp on 11/01/2017.
 */

public interface AsyncResponse {
    void processFinish(ArrayList<ApiRequestElement> output);
}
