package com.jmillerdeveloper.norrisjokesapp.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jmillerdeveloper.norrisjokesapp.models.ChuckNorrisJokeData;
import com.jmillerdeveloper.norrisjokesapp.repositories.ChuckNorrisJokesRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<List<ChuckNorrisJokeData>> liveJokeData = new MutableLiveData<>();

    private final ChuckNorrisJokesRepository jokesRepository;

    public MainViewModel() {
        jokesRepository = new ChuckNorrisJokesRepository(this);
    }
    public LiveData<List<ChuckNorrisJokeData>> getliveData(){
        return liveJokeData;
    }

    public void updateLiveData(List<ChuckNorrisJokeData> parsedResult) {
        Log.i("mainViewModel_debug", "app: live data posted.");
        liveJokeData.postValue(parsedResult);
    }

    //API INTERFACE OVERRIDES:
    public void fetchJokeData(String category) {
        jokesRepository.getJokeData(category);
    }
    //REPOSITORY INTERFACE OVERRIDES
}
