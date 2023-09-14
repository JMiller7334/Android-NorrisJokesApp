package com.jmillerdeveloper.norrisjokesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.jmillerdeveloper.norrisjokesapp.databinding.ActivityMainBinding;
import com.jmillerdeveloper.norrisjokesapp.fragments.MainFragment;
import com.jmillerdeveloper.norrisjokesapp.viewModels.MainViewModel;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set up binding
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(R.layout.activity_main);

        //check savedInstance, avoid recreation on config changes
        if (savedInstanceState == null) {
            MainFragment mainFrag = MainFragment.newInstance();

            //replace the fragment container with the main fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, mainFrag, "MainFragment").commit();

        }
    }
}