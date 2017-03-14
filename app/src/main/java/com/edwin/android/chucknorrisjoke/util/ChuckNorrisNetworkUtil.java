package com.edwin.android.chucknorrisjoke.util;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Edwin Ramirez Ventur on 3/13/2017.
 */

public class ChuckNorrisNetworkUtil {

    private static String CHUCK_NORRIS_BASE_URL = "https://api.chucknorris.io/jokes/";

    public static final String PARAM_QUERY = "query";


    public static URL buildQueryJokeUrl(String wordToSearch) {
        Uri build = Uri.parse(CHUCK_NORRIS_BASE_URL+"search").buildUpon()
                .appendQueryParameter(PARAM_QUERY, wordToSearch).build();

        URL url = null;
        try {
            url = new URL(build.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static List<String> getJokes(String wordToSearch) {
        URL url = ChuckNorrisNetworkUtil.buildQueryJokeUrl(wordToSearch);
        List<String> jokes = new ArrayList<>();
        try {
            String pageResponse = getResponseFromHttpUrl(url);
            JSONObject jsonObject = new JSONObject(pageResponse);
            JSONArray jsonJokes = jsonObject.getJSONArray("result");

            for(int i = 0; i< jsonJokes.length();i++) {
                jokes.add(jsonJokes.getJSONObject(i).getString("value"));
            }

            return jokes;

        } catch (IOException | JSONException e) {
            return jokes;
        }
    }



}
