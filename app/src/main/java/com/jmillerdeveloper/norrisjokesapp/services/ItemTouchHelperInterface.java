package com.jmillerdeveloper.norrisjokesapp.services;

public interface ItemTouchHelperInterface {
    void onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
