package com.jmillerdeveloper.norrisjokesapp.repositories;

import android.util.Log;

import com.jmillerdeveloper.norrisjokesapp.api.ChuckNorrisJokesApi;
import com.jmillerdeveloper.norrisjokesapp.models.JokeData;

public class ChuckNorrisJokesRepository implements ChuckNorrisJokesApi.ApiCallback{

    private ChuckNorrisJokesApi api;
    public ChuckNorrisJokesRepository() {
        api = new ChuckNorrisJokesApi();
    }

    private JokeData parseJokeData(String json){
        //TODO: parse data here
        return null;
    }

    public void getJokeData(){
        api.callAPI(null, this);
    }

    @Override
    public void onApiResult(String result) {
        Log.i("repo_debug", "app: received json: " + result);
    }

    @Override
    public void onApiError(String error) {

    }
}
