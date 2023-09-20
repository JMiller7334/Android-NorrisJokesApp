package com.jmillerdeveloper.norrisjokesapp.models;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmillerdeveloper.norrisjokesapp.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;
    public ImageView icon;
    public ImageButton favButton;
    public ImageButton shareButton;
    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        icon = itemView.findViewById(R.id.imgItemIcon);
        textView = itemView.findViewById(R.id.tvItemTitle);
        favButton = itemView.findViewById(R.id.btnItemFav);
        shareButton = itemView.findViewById(R.id.btnItemShare);
    }
}
