package com.jmillerdeveloper.norrisjokesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmillerdeveloper.norrisjokesapp.R;
import com.jmillerdeveloper.norrisjokesapp.models.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder>
        implements ItemTouchHelperInterface{ //this includes the funcs from the interface.

    private ArrayList<String> dataSet; //the array of info that will populate the recyclerView.

    public RecyclerAdapter(ArrayList<String> dataSet){
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        String item = dataSet.get(position);
        holder.textView.setText(item);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
    // update the dataset/array to reflect changes made from onDrag funcs
        Collections.swap(dataSet, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        dataSet.remove(position);
        notifyItemRemoved(position);
    }
}
