package com.jmillerdeveloper.norrisjokesapp.api;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/*ChuckNorrisJokesApi:
* This class handles receiving Json and managing errors from the api.
* A json will be returned as string from the is class if the call is successful
* Otherwise null will be returned.
* */

public class ChuckNorrisJokesApi {

    public interface ApiCallback {
        void onApiResult(String result);
        void onApiError(String error);
    }

    public void callAPI(String category, ApiCallback callback){
        Log.i("API_debug", "app: calling NorrisJokesAPI from class.");

        String apiUrl;
        //if category was received use a different apiUrl to take advantage of that.
        if (category != null){
            apiUrl = "https://api.chucknorris.io/jokes/random?category="+category;
        } else {
            apiUrl = "https://api.chucknorris.io/jokes/random";
        }

        //create a thread for the api to run on.
        new Thread(() -> {
            String result = getResultFromAPI(apiUrl);
            if (result != null) {
                callback.onApiResult(result);
            } else {
                callback.onApiError(null);
            }
        }).start();
    }
    private String getResultFromAPI(String apiUrl){
        Log.i("API_debug", "app: getting result from api.");

        //variable to hold the received response from the api
        StringBuilder apiResponse = new StringBuilder();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null){
                apiResponse.append(line);
            }
            reader.close(); //disable the reader when it finishes
            connection.disconnect(); //close the connection as it is no longer needed.

        } catch (IOException error) {
            //exception handling for the api call.
            error.printStackTrace();
            Log.e("api_debug", "Error occurred during NorrisJokeApi call - getResult(): " + error.getMessage());

            return null; //return null to indicate api failure to the repository.
        }
        return apiResponse.toString();
    }
}
