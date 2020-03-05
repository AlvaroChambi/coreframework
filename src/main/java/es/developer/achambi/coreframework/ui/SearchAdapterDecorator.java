package es.developer.achambi.coreframework.ui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.SortedList;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import es.developer.achambi.coreframework.ui.presentation.SearchListData;

import static androidx.recyclerview.widget.RecyclerView.NO_POSITION;

public abstract class SearchAdapterDecorator<D extends SearchListData,VH extends RecyclerView.ViewHolder> {
    private static final int DEFAULT_VIEW_TYPE = 0;
    public interface OnItemClickedListener<D> {
        void onItemClicked( D item );
    }

    public interface OnViewClickedListener<D,VH> {
        void onViewClicked( D item, VH viewHolder );
    }

    protected SearchAdapterDecorator<D,VH> adapter;
    protected ArrayList<D> data;

    public SearchAdapterDecorator( SearchAdapterDecorator<D,VH> adapter ) {
        this.adapter = adapter;
        data = new ArrayList<>();
    }

    public SearchAdapterDecorator( ArrayList<D> data ) {
        this.data = data;
    }

    public SearchAdapterDecorator() {
        data = new ArrayList<>();
    }

    public SearchAdapterDecorator( ArrayList<D> data, SearchAdapterDecorator<D,VH> adapter  ) {
        this.adapter = adapter;
        this.data = data;
    }

    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType,
                                                      final SortedList<D> rootData ) {
        if( isValidAdapter( viewType ) ) {
            View rootView = LayoutInflater.from(parent.getContext())
                    .inflate(getLayoutResource(), parent, false);
            final VH viewHolder = createViewHolder( rootView, rootData );
            return viewHolder;
        }
        return adapter.onCreateViewHolder( parent, viewType, rootData );
    }

    void onBindViewHolder( RecyclerView.ViewHolder holder, final SearchListData item) {
        if( item.getViewType() == getAdapterViewType() ) {
            bindViewHolder( (VH)holder, (D)item );
        } else {
            adapter.onBindViewHolder( holder, item );
        }
    }

    /**
     * Build the data list adding any nested decorator's data if present
     * @return data list with all available decorators information, empty list if no data
     * is available for any decorator
     */
    public ArrayList<D> buildData() {
        ArrayList<D> tempData = new ArrayList<>();
        tempData.addAll( data );
        if( adapter != null ) {
            tempData.addAll( adapter.buildData() );

        }
        return tempData;
    }

    public void setData(ArrayList<D> dataList ) {
        this.data = dataList;
    }

    public void appendData(ArrayList<D> dataList) {
        this.data.addAll(dataList);
    }

    public ArrayList<D> getData() {
        return data;
    }

    public boolean isValidAdapter(int viewType ){
        if( getAdapterViewType() == viewType ) {
            return true;
        }
        return false;
    }

    public abstract int getLayoutResource();
    public abstract VH createViewHolder( @NonNull View rootView, @NonNull SortedList rootData );
    public abstract void bindViewHolder( @NonNull VH holder, @NonNull D item );
    public int getAdapterViewType() {
        return DEFAULT_VIEW_TYPE;
    }
    public void onItemSwipped( int position ) {}
}
