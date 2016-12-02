package com.markoproject.launcher;

import com.markoproject.model.Location;
import com.markoproject.services.LocationFinder;
import org.json.JSONException;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Marko on 02.12.2016.
 */
//class for starting application
public class Launcher {
    public static void main(final String[] args) {
        LocationFinder locationFinder = new LocationFinder("src/main/resources/cities.txt");
        Map<String, Location> locations = locationFinder.getLocations();//getting map with results where key is name of city and value is object of class Location
        Iterator entries = locations.entrySet().iterator();
        while (entries.hasNext()) {//iteration by the map "results" and printing them
            Map.Entry<String, Location> cityWithLocaton = (Map.Entry) entries.next();
            if (cityWithLocaton.getValue() == null) {
                System.out.println(cityWithLocaton.getKey() + " location not found  ");
            } else {
                System.out.println(cityWithLocaton);
            }
        }
    }
}
