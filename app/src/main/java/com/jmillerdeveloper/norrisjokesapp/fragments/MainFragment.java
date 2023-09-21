package com.jmillerdeveloper.norrisjokesapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jmillerdeveloper.norrisjokesapp.R;
import com.jmillerdeveloper.norrisjokesapp.adapters.SpinnerAdapter;
import com.jmillerdeveloper.norrisjokesapp.services.ItemTouchHelperCallback;
import com.jmillerdeveloper.norrisjokesapp.adapters.RecyclerAdapter;
import com.jmillerdeveloper.norrisjokesapp.databinding.FragmentMainBinding;
import com.jmillerdeveloper.norrisjokesapp.enums.EnumJokeCategory;
import com.jmillerdeveloper.norrisjokesapp.models.ChuckNorrisJokeData;
import com.jmillerdeveloper.norrisjokesapp.viewModels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel viewModel;
    private FragmentMainBinding binding;

    //RECYCLER VARIABLES:
    private RecyclerAdapter recyclerAdapter;

    //SPINNER VARIABLES:
    private ArrayAdapter spinnerAdapter;
    private List<String> spinnerOptions;

    public MainFragment() {
        // Required empty public constructor
    }
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //on create
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //set up binding and view
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //use the viewModel Provider to setup viewModel; ties the view model to this fragments lifeCycle.
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.fetchJokeData();

        binding.btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.fetchJokeData();
            }
        });

        //onCreate code here.
        initRecyclerView(binding);
        initSpinner(binding);
        observeLiveData();
        return view;
    }

    private void observeLiveData(){
        LiveData<List<ChuckNorrisJokeData>> liveData = viewModel.getliveData();
        liveData.observe(getViewLifecycleOwner(), newData -> {
            if (newData != null){
                recyclerAdapter.updateListItems(newData);
            }
        });
    }

    private void initSpinner(FragmentMainBinding binding){
        spinnerOptions = EnumJokeCategory.enumAsList();
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(getContext(), viewModel, binding.spinCategory,
                spinnerOptions, com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
        spinnerAdapter.setSpinnerListener();
    }

    private void initRecyclerView(FragmentMainBinding binding){
        List<ChuckNorrisJokeData> emptyList = new ArrayList<>();

        //base recycler setup:
        RecyclerView mainRecycler = binding.rvMain;
        mainRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAdapter = new RecyclerAdapter(emptyList);
        mainRecycler.setAdapter(recyclerAdapter);

        /*Gesture Config:
         * create a new touchHelperCallback class and pass the adapter to it.
         * since the class created extends the ItemTouchHelper class create that and pass the new class to it.
         * Attack the new itemTouchHelper to the recycler adapter. This will enable it.
         * */
        ItemTouchHelperCallback itemTouchHelperCallback = new ItemTouchHelperCallback(recyclerAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(mainRecycler);
    }
}