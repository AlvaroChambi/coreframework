package es.developer.achambi.coreframework.utils;

import android.graphics.Canvas;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;

import es.developer.achambi.coreframework.ui.BaseSearchAdapter;

public abstract class SwipeTouchHelperCallback extends ItemTouchHelper.Callback {
    private BaseSearchAdapter adapter;
    protected abstract int getViewHolderForegroundResource();
    protected abstract void onItemSwiped(RecyclerView.ViewHolder viewHolder);

    public SwipeTouchHelperCallback(BaseSearchAdapter adapter ) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView,
                                @NonNull RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = ItemTouchHelper.END;
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
        onItemSwiped( viewHolder );
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder,
                                  int actionState) {
        if( viewHolder != null ) {
            getDefaultUIUtil().onSelected(viewHolder.itemView.findViewById(
                    getViewHolderForegroundResource() ));
        }
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder) {
        getDefaultUIUtil().clearView(viewHolder.itemView.findViewById(
                getViewHolderForegroundResource() ));
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        getDefaultUIUtil().onDraw(c, recyclerView,
                viewHolder.itemView.findViewById( getViewHolderForegroundResource() ),
                dX,dY, actionState, isCurrentlyActive );
    }
}
