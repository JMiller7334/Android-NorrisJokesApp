package com.jmillerdeveloper.norrisjokesapp.repositories;

import android.util.Log;

import com.jmillerdeveloper.norrisjokesapp.models.ChuckNorrisJokeData;
import com.jmillerdeveloper.norrisjokesapp.viewModels.MainViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChuckNorrisJokesRepository implements ChuckNorrisJokesApi.ApiCallback{
    //REPOSITORY INIT:
    private final ChuckNorrisJokesApi api;
    private final MainViewModel viewModel;
    private List<ChuckNorrisJokeData> receivedJokesArray;
    public ChuckNorrisJokesRepository(MainViewModel viewModel) {
        this.viewModel = viewModel;
        api = new ChuckNorrisJokesApi();
    }


    /*getJokeData
    * makes the call to the api class which will handle the request and return a JsonObject.*/
    public void getJokeData(String category){
        receivedJokesArray = new ArrayList<ChuckNorrisJokeData>();
        api.callAPI(null, this);
    }


    //API INTERFACE OVERRIDES:
    /*onAPiResult:
    * handles sending the data the class to be parsed
    * exceptions that occur while parsing are handled here
    * returns the joke data to the viewModel*/
    @Override
    public void onApiResult(JSONObject result) {
        Log.i("repo_debug", "app: received json: " + result);
        try {
            receivedJokesArray.add(new ChuckNorrisJokeData(result));
            viewModel.updateLiveData(receivedJokesArray);
        } catch (JSONException error){
            Log.e("parse_debug", "app repository: error parsing json " + error.getMessage());
        }
    }
    /*onApiError:
    * if the api fails this will fire.*/
    @Override
    public void onApiError(String error) {
        //does nothing.
    }
}
