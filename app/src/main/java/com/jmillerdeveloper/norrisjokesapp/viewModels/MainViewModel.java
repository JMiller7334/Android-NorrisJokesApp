package com.jmillerdeveloper.norrisjokesapp.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jmillerdeveloper.norrisjokesapp.enums.EnumJokeCategory;
import com.jmillerdeveloper.norrisjokesapp.models.ChuckNorrisJokeData;
import com.jmillerdeveloper.norrisjokesapp.repositories.ChuckNorrisJokesRepository;

import java.util.Arrays;
import java.util.List;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<List<ChuckNorrisJokeData>> liveJokeData = new MutableLiveData<>();
    private final ChuckNorrisJokesRepository jokesRepository;
    private String selectedCategory;

    public MainViewModel() {
        jokesRepository = new ChuckNorrisJokesRepository(this);
        selectedCategory = EnumJokeCategory.SpinnerCategory.Random.toString();
        fetchJokeData();
    }

    public LiveData<List<ChuckNorrisJokeData>> getliveData(){
        return liveJokeData;
    }
    public void updateLiveData(List<ChuckNorrisJokeData> parsedResult) {
        Log.i("mainViewModel_debug", "app: live data posted.");
        liveJokeData.postValue(parsedResult);
    }

    public void setSelectedCategory(Integer position) {
        this.selectedCategory = EnumJokeCategory.enumAsList().get(position);
        fetchJokeData();
    }

    public void fetchJokeData() {
        jokesRepository.getJokeData(selectedCategory);
    }
}
