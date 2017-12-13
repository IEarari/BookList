package com.example.android.booklist;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

class API {
    private static final String LOG_TAG = API.class.getSimpleName();
    private static final String apiURI = "https://www.googleapis.com/books/v1/volumes?q=";


    public API() {
    }

    public String callAPI(String searchCriteria, int maxResults) {
        String querySearchCriteria;
        String queryURI;
        try {
            querySearchCriteria = java.net.URLEncoder.encode(searchCriteria, "UTF-8");
            queryURI = apiURI + querySearchCriteria + "&maxResults=" + maxResults;
        } catch (UnsupportedEncodingException e) {
            return null;
        }

        String results = null;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            Log.e(LOG_TAG, "buildTheURL " + true);
            Uri builtUri = Uri.parse(queryURI).buildUpon().build();
            URL url = new URL(builtUri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                results = null;
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (buffer.length() == 0) {
                results = null;
            }
            results = buffer.toString();
        } catch (IOException v) {
            results = null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return results;
    }
}
