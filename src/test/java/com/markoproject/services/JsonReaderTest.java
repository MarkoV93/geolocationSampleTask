package com.markoproject.services;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Marko on 02.12.2016.
 */
public class JsonReaderTest {
    private static final Logger logger = LogManager.getLogger(LocationFinder.class);
    @Test
    public void getJsonResponseFromGoogle(){
        try {
           JSONObject response= JsonReader.read("https://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=Kiev ");
            Assert.assertEquals(response.getString("status"),"OK");
        } catch (JSONException e) {
           logger.error("bad url "+e.getMessage());
        }
    }
}
