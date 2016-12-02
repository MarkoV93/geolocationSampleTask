package com.markoproject.services;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Marko on 02.12.2016.
 */
//Class for reading of response from google api and returning Json object
public class JsonReader {

    private static final Logger logger = LogManager.getLogger(LocationFinder.class);
//method for reading from the reader object and returning String from it
    private static String readAll(Reader rd) {
        final StringBuilder stringBuilder = new StringBuilder();
        int count;
        try {
            while ((count = rd.read()) != -1) {
                stringBuilder.append((char) count);
            }
        } catch (IOException e) {
            logger.error("Can not read from reader in JsonReader.readAll()");
        }
        return stringBuilder.toString();
    }

    //static method for reading of response from google api and returning Json object
    public static JSONObject read(String url) throws JSONException {
        JSONObject json = null;
        try (InputStream inputStream = new URL(url).openStream()) {//opening inputStream in "try with resources" block
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String jsonText = readAll(bufferedReader);//getting  Sting from the static private method of this class
            json = new JSONObject(jsonText);//creating a new json object from previous string
        } catch (IOException e) {
            logger.error("can not read url in JsonReader.read()" + e.getMessage());
        }
        return json;
    }
}
