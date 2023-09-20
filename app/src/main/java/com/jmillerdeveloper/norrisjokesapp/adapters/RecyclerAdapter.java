package com.jmillerdeveloper.norrisjokesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jmillerdeveloper.norrisjokesapp.R;
import com.jmillerdeveloper.norrisjokesapp.models.ChuckNorrisJokeData;
import com.jmillerdeveloper.norrisjokesapp.models.RecyclerDiffCallback;
import com.jmillerdeveloper.norrisjokesapp.models.RecyclerViewHolder;

import java.util.Collections;
import java.util.List;

import com.squareup.picasso.Picasso;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder>
        implements ItemTouchHelperInterface{ //this includes the funcs from the interface.

    private final List<ChuckNorrisJokeData> dataSet; //the array of info that will populate the recyclerView.

    public RecyclerAdapter(List<ChuckNorrisJokeData> dataSet){
        this.dataSet = dataSet;
    }

    public void updateListItems(List<ChuckNorrisJokeData> incomingDataSet){
        final RecyclerDiffCallback diffCallback =
                new RecyclerDiffCallback(this.dataSet, incomingDataSet);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.dataSet.clear();
        this.dataSet.addAll(incomingDataSet);
        diffResult.dispatchUpdatesTo(this);
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
        ChuckNorrisJokeData jokeClass = dataSet.get(position);
        holder.textView.setText(jokeClass.getValue());

        String imgUrl = jokeClass.getIconUrl();
        Picasso.get()
                .load(imgUrl)
                .placeholder(R.drawable.baseline_chat_24)
                .error(R.drawable.baseline_chat_bubble_24)
                .into(holder.icon);
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
