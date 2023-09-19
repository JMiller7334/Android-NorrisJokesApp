package com.jmillerdeveloper.norrisjokesapp.models;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;
import java.util.Objects;

public class RecyclerDiffCallback extends DiffUtil.Callback {
    private final List<ChuckNorrisJokeData> newData;
    private final List<ChuckNorrisJokeData> oldData;

    public RecyclerDiffCallback(List<ChuckNorrisJokeData> oldData, List<ChuckNorrisJokeData> newData) {
        this.oldData = oldData;
        this.newData = newData;
    }

    @Override
    public int getOldListSize() {
        return oldData.size();
    }

    @Override
    public int getNewListSize() {
        return newData.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return Objects.equals(oldData.get(oldItemPosition).getId(),
                newData.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final ChuckNorrisJokeData oldClass = oldData.get(oldItemPosition);
        final ChuckNorrisJokeData newClass = newData.get(newItemPosition);

        return oldClass.getValue().equals(newClass.getValue());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
