package com.jmillerdeveloper.norrisjokesapp.repositories;

import android.util.Log;

import com.jmillerdeveloper.norrisjokesapp.enums.EnumJokeCategory;
import com.jmillerdeveloper.norrisjokesapp.models.ChuckNorrisJokeData;
import com.jmillerdeveloper.norrisjokesapp.viewModels.MainViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChuckNorrisJokesRepository implements ChuckNorrisJokesApi.ApiCallback{
    //REPOSITORY INIT:
    private final ChuckNorrisJokesApi api;
    private final MainViewModel viewModel;
    private List<ChuckNorrisJokeData> receivedJokesArray;
    private List<String> receivedJokesIdArray;
    public ChuckNorrisJokesRepository(MainViewModel viewModel) {
        this.viewModel = viewModel;
        api = new ChuckNorrisJokesApi();
    }

    /*getJokeData
    * clears the liveData and calls the api 10X to received
    so new jokes to fill the page.
    *
    * if user does not supply a category one will be randomly
    generated for the api to use, specifically ensuring that
    explicit jokes are not included.
    * */
    public void getJokeData(String category){
        receivedJokesArray = new ArrayList<>();
        receivedJokesIdArray = new ArrayList<>();

        String categoryParameter = category.toLowerCase();
        for (int i = 0; i < 10; i++){
            if (Objects.equals(categoryParameter.toLowerCase(), "random")){
                categoryParameter = EnumJokeCategory.getRandomCategory().toString();
            }
            api.callAPI(categoryParameter, this);
        }
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
            ChuckNorrisJokeData parsedResult = new ChuckNorrisJokeData(result);
            if (receivedJokesIdArray.contains(parsedResult.getId())){
                Log.i("repository_debug", "app: duplicate id received; omitting joke.");
                return;
            }
            receivedJokesIdArray.add(parsedResult.getId());
            receivedJokesArray.add(new ChuckNorrisJokeData(result));
            viewModel.updateLiveData(receivedJokesArray);
        } catch (JSONException error){
            Log.e("repository_debug", "app: error parsing json " + error.getMessage());
            //TODO: notify viewModel
        }
    }
    /*onApiError:
    * if the api fails this will fire.*/
    @Override
    public void onApiError(String error) {
        //TODO: NOTIFY VIEWMODEL.
        //does nothing.
    }
}
