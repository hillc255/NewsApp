package com.example.android.newsapp;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /** Tag for the log messages */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }


    /**
     * Query the Guardian dataset and return a list of {@link News} objects.
     */
    public static List<News> fetchNewsData(String requestUrl) {

        Log.i(LOG_TAG,"TEST: fetchNewsData() called...");

//        //test loader is working
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        // Create URL object
        URL url = createUrl(requestUrl);

        Log.i(LOG_TAG,"TEST: createUrl...");

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);

            Log.i(LOG_TAG,"TEST: try to makeHTTPRequest..");

        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        Log.i(LOG_TAG,"TEST: finished makeHTTPRequest..");

        List<News> newslist = extractFeatureFromJson(jsonResponse);

        Log.i(LOG_TAG,"TEST: finished extractFeatureFromJson..");

        // Return the list of {@link Earthquake}s
        return newslist;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
            Log.i(LOG_TAG,"Test: get url");
            Log.i(LOG_TAG,url.toString());

        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {

            Log.i(LOG_TAG,"TEST: jsonResponse is empty..");
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the news JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }

        Log.i(LOG_TAG,"TEST: finish reading from url");
        return output.toString();
    }

    /**
     * Return a list of {@link News} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<News> extractFeatureFromJson(String newsJSON){
        if (TextUtils.isEmpty(newsJSON)) {

            Log.i(LOG_TAG,"TEST empty extractFeatureFromJson()");
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        List<News> newsList = new ArrayList<>();

        Log.i(LOG_TAG,"TEST empty new ArrayList created");

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.


        try {

            Log.i(LOG_TAG,"TEST: inside try - parse");

            // Create a JSONObject from the JSON response string
          JSONObject baseJsonResponse = new JSONObject(newsJSON);

            Log.i(LOG_TAG,"TEST: created news Json object");

            //Create the JSONObject with the key "response"
            JSONObject responseJSONObject = baseJsonResponse.getJSONObject("response");
            // Extract the JSONArray associated with the key called "results",
            // which represents a list of news stories.
            JSONArray newsArray = responseJSONObject.getJSONArray("results");

            // Extract the JSONArray associated with the key called "features",
            // which represents a list of features (or earthquakes).
       //  JSONArray newsArray = baseJsonResponse.getJSONArray("results");

         //   JSONArray newsArray = baseJsonResponse.getJSONArray("");

       //     JSONArray newsArray = new JSONArray();


            Log.i(LOG_TAG,"TEST: created JSONArray()..");

            // For each earthquake in the earthquakeArray, create an {@link News} object
            for (int i = 0; i < newsArray.length(); i++) {

                Log.i(LOG_TAG,"TEST: inside newArray loop");

                // Get a single newsStory at position i within the list of news stories
                JSONObject currentNews = newsArray.getJSONObject(i);

                // Get a single earthquake at position i within the list of earthquakes
             //   JSONObject currentNews = newsArray.getJSONObject(i);

                Log.i(LOG_TAG,"TEST: get a single story ");


                // For a given earthquake, extract the JSONObject associated with the
                // key called "properties", which represents a list of all properties
                // for that earthquake.
//                JSONObject fields = currentNews.getJSONObject("fields");
//
//                String imageUrl = fields.getString("thumbnail");
//
//                // *******Extract the value for the key called "place"
//                String section = currentNews.getString("sectionName");

                // ************Extract the value for the key called "time"
                // long time = properties.getLong("time");
                String title = currentNews.getString("webTitle");

                // Extract the value for the key called "sectionName"
                String section = currentNews.getString("sectionName");

                // Extract the value for the key called "webPublicationDate"
                String date = currentNews.getString("webPublicationDate");

                //Extract the JSONArray with the key "tag"
                JSONArray tagsArray = currentNews.getJSONArray("tags");
                //Declare String variable to hold author name
                String contributor = null;
                if (tagsArray.length() == 1) {
                    JSONObject contributorTag = (JSONObject) tagsArray.get(0);
                    contributor = contributorTag.getString("webTitle");
                }

//                // Extract the value for the key called "thumbnail"
//                String imageUrl = currentNews.getString("thumbnail");

                //********************************************************************

//                //For imageUrl Extract the JSONArray with the key "fields"
//                JSONArray fieldsArray = currentNews.getJSONArray("fields");
//                String imageUrl = null;
//                JSONArray imageUrlFields = (JSONArray) fieldsArray.get(0);
//                imageUrl = imageUrlFields.getString("thumbnail");

              //  String imageUrl = null;

//              //  if (fieldsArray.length() == 1) {
//                  JSONObject imageUrlFields = (JSONObject) fieldsArray.get(0);
//                   // JSONArray imageUrlFields = (JSONArray) fieldsArray.get(0);
//                    imageUrl = imageUrlFields.getString("thumbnail");
//                }

                // *******Extract the value for the key called "url"
                // String url = properties.getString("url");


//                String contributor = fields.getString("contributor");
//
//                // ******Extract the value for the key called "mag"
//               // double magnitude = properties.getDouble("mag");
//                String newsDate = currentNews.getString("webPublicationDate");


//                // Create a new {@link Earthquake} object with the magnitude, location, time,
//                //******** and url from the JSON response.
//               // News news = new News(magnitude, location, time, url);
//                News news = new News(imageUrl, section, title, contributor, newsDate);
               // News news = new News(title, section, date, contributor, imageUrl);
                News news = new News(title, section, date, contributor);

                // Add the new {@link Earthquake} to the list of earthquakes.
                newsList.add(news);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }

        // Return the list of news items
        return newsList;
    }

}