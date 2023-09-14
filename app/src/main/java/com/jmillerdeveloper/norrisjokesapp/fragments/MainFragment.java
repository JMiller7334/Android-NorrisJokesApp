package com.jmillerdeveloper.norrisjokesapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jmillerdeveloper.norrisjokesapp.adapters.ItemTouchHelperCallback;
import com.jmillerdeveloper.norrisjokesapp.adapters.RecyclerAdapter;
import com.jmillerdeveloper.norrisjokesapp.databinding.FragmentMainBinding;
import com.jmillerdeveloper.norrisjokesapp.viewModels.MainViewModel;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private RecyclerView mainRecycler;
    private RecyclerAdapter recyclerAdapter;

    public MainFragment() {
        // Required empty public constructor
    }
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //set up binding and view
        FragmentMainBinding binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        MainViewModel viewModel = new MainViewModel();
        viewModel.getNorrisJokes();

        //onCreate code here.
        ArrayList<String> testData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            testData.add("Item " + i);
        }

        //base recycler setup:
        mainRecycler = binding.rvMain;
        mainRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAdapter = new RecyclerAdapter(testData);
        mainRecycler.setAdapter(recyclerAdapter);

        /*Gesture Config:
        * create a new touchHelperCallback class and pass the adapter to it.
        * since the class created extends the ItemTouchHelper class create that and pass the new class to it.
        * Attack the new itemTouchHelper to the recycler adapter. This will enable it.
        * */
        ItemTouchHelperCallback itemTouchHelperCallback = new ItemTouchHelperCallback(recyclerAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(mainRecycler);


        return view;
    }
}