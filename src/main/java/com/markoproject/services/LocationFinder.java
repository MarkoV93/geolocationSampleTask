package com.markoproject.services;


import com.markoproject.model.Location;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.*;

/**
 * Created by Marko on 02.12.2016.
 */
//class for finding locations of cities readed  from the file
public class LocationFinder {

    private File cities;
    private static final Logger logger = LogManager.getLogger(LocationFinder.class);

    //Constructor of this class. Creating new Object of file by the address of this file
    public LocationFinder(String file) {
        cities = new File(file);
    }

    //method for creating part of url to googleapis
    private static String encodeParams(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator entries = params.entrySet().iterator();
        while (entries.hasNext()) {//iteration by all elements of map with parameters
            Map.Entry<String, String> thisEntry = (Map.Entry) entries.next();
            String key = thisEntry.getKey();
            stringBuilder.append(key + "=");//adding to stringBuilder key=value
            String value = thisEntry.getValue();
            stringBuilder.append(value);
            if (entries.hasNext()) {
                stringBuilder.append("&");
            }
        }
        return stringBuilder.toString();//retuning string like :"sensor=false&address=Kiev "
    }

    //method for returning location of city by the city name
    private Location getLocationByCity(String city) {
        Location cityLocation = new Location();
        final String baseUrl = "http://maps.googleapis.com/maps/api/geocode/json";// link to Geocoding API by HTTP
        final Map<String, String> params = new HashMap();
        params.put("sensor", "false");// is request from the device with the location sensor
        params.put("address", city);// name of city
         String url = baseUrl + '?' + encodeParams(params);// generating path with parameters
         JSONObject response;
        try {
            response = JsonReader.read(url);//getting response from google api
            JSONObject location = response.getJSONArray("results").getJSONObject(0);
            location = location.getJSONObject("geometry");
            location = location.getJSONObject("location");
            cityLocation.setLongitude(location.getDouble("lng"));
            cityLocation.setLatitude(location.getDouble("lat"));
        } catch (JSONException e) {
            logger.warn("Can not parse JSON in LocationFinder.Location getLocationByCity(String city) with city: " + city + " " + e.getMessage());
            return null;
        }
        return cityLocation;
    }

    public Map<String, Location> getLocations() {
        Map<String, Location> results = new HashMap<String, Location>();//creating Map with key- name of city and value - Object of location class
        try ( FileReader fileReader = new FileReader(cities)){
            BufferedReader reader = new BufferedReader(fileReader);
            String city = null;
            while ((city = reader.readLine()) != null) {//reading by line cities from file
                results.put(city, getLocationByCity(city));
            }
        } catch (FileNotFoundException ex) {
            logger.error("File not found in LocationFinder.getLocations() " + ex.getMessage());
        } catch (IOException ex) {
            logger.error("Can not read line in LocationFinder.getLocations() " + ex.getMessage());
        }
        return results;
    }
}
