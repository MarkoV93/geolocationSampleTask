package com.markoproject.services;

import com.markoproject.model.Location;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Created by Marko on 02.12.2016.
 */
public class LocationFinderTest {

    @Test
    public void fndingLocations(){
        LocationFinder locationFinder=new LocationFinder("src/main/resources/cities.txt");
        Map<String,Location> locations=locationFinder.getLocations();
        Location kievLocation=locations.get("Kiev");
        Assert.assertEquals(kievLocation.getLatitude(), 50.45,0.001);
    }
}
