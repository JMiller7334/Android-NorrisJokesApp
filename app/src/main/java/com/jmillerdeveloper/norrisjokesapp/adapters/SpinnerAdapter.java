package com.jmillerdeveloper.norrisjokesapp.adapters;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.LayoutRes;

import com.jmillerdeveloper.norrisjokesapp.viewModels.MainViewModel;

import java.util.List;

public class SpinnerAdapter {
    private final ArrayAdapter<String> spinnerAdapter;
    private final List<String> spinnerOptions;
    private final Spinner spinner;
    private final MainViewModel viewModel;

    public SpinnerAdapter(Context context, MainViewModel viewModel, Spinner spinner,
                          List<String> spinnerOptions, @LayoutRes int itemLayoutResId) {

        this.spinner = spinner;
        this.spinnerOptions = spinnerOptions;
        this.viewModel = viewModel; // Initialize the viewModel field

        spinnerAdapter = new ArrayAdapter<>(context, itemLayoutResId, spinnerOptions);
        spinnerAdapter.setDropDownViewResource(itemLayoutResId);
        spinner.setAdapter(spinnerAdapter);
    }

    public void setSpinnerListener(){
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedCategory = spinnerOptions.get(i);
                spinnerOptions.set(i, "Category: " + selectedCategory);
                spinnerAdapter.notifyDataSetChanged();

                viewModel.setSelectedCategory(i);
                spinner.setSelection(i); // Set the selection on the spinner
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Does nothing
            }
        });
    }
}
