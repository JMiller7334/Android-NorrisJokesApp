package com.jmillerdeveloper.norrisjokesapp.viewModels;

import com.jmillerdeveloper.norrisjokesapp.repositories.ChuckNorrisJokesRepository;

public class MainViewModel {

    private final ChuckNorrisJokesRepository jokesRepository;

    public MainViewModel(){
        jokesRepository = new ChuckNorrisJokesRepository();
    }

    public void getNorrisJokes(){
        jokesRepository.getJokeData();
    }
}
