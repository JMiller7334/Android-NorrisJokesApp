package com.jmillerdeveloper.norrisjokesapp.adapters;

public interface ItemTouchHelperInterface {
    void onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
