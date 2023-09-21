package com.jmillerdeveloper.norrisjokesapp.services;

//imports
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.jmillerdeveloper.norrisjokesapp.adapters.RecyclerAdapter;

import javax.crypto.spec.PSource;

public class ItemTouchHelperCallback extends androidx.recyclerview.widget.ItemTouchHelper.Callback {


    private final RecyclerAdapter adapter;

    //constructor for the variable
    public ItemTouchHelperCallback(RecyclerAdapter adapter) {
        this.adapter = adapter;
    }

    /*getMovementFlags
    * This function gets and returns the direction (as int) that
    * the user has swiped. This is ued to determine what the user did.*/
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder source, @NonNull RecyclerView.ViewHolder target) {
        if (source.getItemViewType() != target.getItemViewType()){
            return false; //prevents moving of items with different types
        }

        //Notify the adapter of the move:
        adapter.onItemMove(source.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
    //Notify adapter of swipe
        adapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
