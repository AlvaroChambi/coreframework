package es.developer.achambi.coreframework.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import es.developer.achambi.coreframework.ui.BaseSearchAdapter;

public class TouchHelper extends ItemTouchHelper.Callback {
    private BaseSearchAdapter adapter;

    public TouchHelper( BaseSearchAdapter adapter ) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView,
                                @NonNull RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeFlag(ItemTouchHelper.ACTION_STATE_SWIPE, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        adapter.onItemSwipped( viewHolder.getAdapterPosition() );
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }
}
