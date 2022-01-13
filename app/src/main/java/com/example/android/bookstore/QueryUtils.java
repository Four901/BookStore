package com.example.android.bookstore;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }


    public static  List<Book> fetchEarthquakeData(String requestUrl) {

        URL url = createUrl(requestUrl);
        List<Book> books = null;

        try {
            String jsonResponse = makeHttpRequest(url);
            books = extractFeaturesFromJson(jsonResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return books;
    }

    /**
     * Return a list of {@link Book} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Book> extractFeaturesFromJson(String jsonResponse) {

        if(jsonResponse == null) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Book> books = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

            JSONObject object = new JSONObject(jsonResponse);//firstly we are converting the jsonResponse into jsonobject
            JSONArray itemss = object.getJSONArray("items");//from the json object we are extracting the array with the name of features you can see that in api

            for(int i = 0; i < itemss.length(); i++) {


                JSONObject properties = itemss.getJSONObject(i).getJSONObject("volumeInfo");
                String title=properties.getString("title");

                JSONArray arr=properties.getJSONArray("authors");

                ArrayList<String>authors=new ArrayList<>();
                for(int j=0;j<arr.length();j++)
                {
                    Log.d("BookStoreActivity","Here "+" "+arr.getString(j));
                    authors.add(arr.getString(j));
                }
                for(int j=0;j<authors.size();j++)
                {
                    Log.d("BookStoreActivity","Here "+" "+authors.get(j));
                }

                int pageCount=properties.getInt("pageCount");
                Log.d("BookStoreActivity","Here "+" "+String.valueOf(pageCount));
                double averageRating;
                int ratingCount;
                if(properties.has("averageRating"))
                {
                     averageRating=properties.getDouble("averageRating");
                }
                else
                {
                     averageRating=0.0;
                }
                if(properties.has("ratingsCount"))
                {
                     ratingCount=properties.getInt("ratingsCount");
                }
                else
                {
                     ratingCount=0;
                }

                String description=properties.getString("description");
                String pulshDate=properties.getString("publishedDate");
                String language=properties.getString("language");
                String previewLink=properties.getString("previewLink");
                String infoLink=properties.getString("infoLink");

                JSONObject obj1=properties.getJSONObject("readingModes");

                boolean isTextAvailable=obj1.getBoolean("text");
                boolean isImageAvailable=obj1.getBoolean("image");

                JSONObject obj2=properties.getJSONObject("imageLinks");
                String smallThumbnailLink=obj2.getString("smallThumbnail");
                String thumbnailLink=obj2.getString("thumbnail");




                Log.d("BookStoreActivity","Here is the book"+title+" "+description+" "+pulshDate);

                Book book = new Book(title,authors,pageCount, averageRating,ratingCount,description,pulshDate,language,previewLink,infoLink,isTextAvailable,isImageAvailable,smallThumbnailLink,thumbnailLink);

                books.add(book);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the Books JSON results", e);
        }

        // Return the list of earthquakes
        //Log.e("QueryUtils", "Problem parsing the Books JSON results"+String.valueOf(books.size()));
               return books;
    }

    private static URL createUrl(String stringUrl) {

        URL url = null;

        if(stringUrl == null) {
            return url;
        }
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;


        if(url == null) {
            return jsonResponse;
        }

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
            if(inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String readFromStream (InputStream inputStream) {
        InputStreamReader streamReader = null;
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();

        if(inputStream == null) {
            return null;
        }

        streamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        reader = new BufferedReader(streamReader);
        try {
            String line = reader.readLine();
            while (line != null) {

                result.append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

}
