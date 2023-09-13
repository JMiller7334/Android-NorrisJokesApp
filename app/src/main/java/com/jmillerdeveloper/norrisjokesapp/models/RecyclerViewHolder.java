package com.jmillerdeveloper.norrisjokesapp.models;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmillerdeveloper.norrisjokesapp.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;
    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tvItemTitle);
    }
}
