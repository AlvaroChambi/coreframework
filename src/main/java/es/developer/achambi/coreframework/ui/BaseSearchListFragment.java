package es.developer.achambi.coreframework.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.developer.achambi.coreframework.R;
import es.developer.achambi.coreframework.utils.GlideApp;
import es.developer.achambi.coreframework.utils.GlideRequests;
import es.developer.achambi.coreframework.utils.SwipeTouchHelperCallback;

public abstract class BaseSearchListFragment extends BaseRequestFragment {
    private static int NO_ID = -1;
    protected RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;
    private ViewGroup header;
    private BaseSearchAdapter adapter;
    protected GlideRequests requestManager;

    @Override
    public int getLayoutResource() {
        return R.layout.base_search_fragment_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestManager = GlideApp.with(this);
        adapter = new BaseSearchAdapter( provideAdapter() );
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = ((ViewGroup) super.onCreateView(inflater, container, savedInstanceState));
        if( getHeaderLayoutResource() != NO_ID ) {
            header = root.findViewById(R.id.base_search_header_frame);
            header.setVisibility(View.VISIBLE);
            inflater.inflate( getHeaderLayoutResource(), header );
        }
        return root;
    }

    @Override
    public void onViewSetup(View view) {
        if( getHeaderLayoutResource() != NO_ID ) {
            onHeaderSetup( header );
        }
        recyclerView = view.findViewById(R.id.base_search_recycler_view);
        ViewGroup.LayoutParams marginParams =
                (ViewGroup.LayoutParams) recyclerView.getLayoutParams();
        SwipeTouchHelperCallback touchHelper = provideItemTouchHelper(adapter);
        if( touchHelper != null ) {
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper( touchHelper );
            itemTouchHelper.attachToRecyclerView( recyclerView );
        }

        overrideRecyclerViewMargins( marginParams );
        recyclerView.setLayoutParams( marginParams );
        layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setAdapter( adapter );
    }

    protected SwipeTouchHelperCallback provideItemTouchHelper(BaseSearchAdapter adapter) {
        return null;
    }

    protected void overrideRecyclerViewMargins( ViewGroup.LayoutParams marginParams ) {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.overview_search_action)
                .getActionView();
        searchView.setQueryHint(getResources().getString(getSearchHintResource()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                onQueryTextSubmitted( query );
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                onQueryTextChanged( newText );
                return true;
            }
        });
        MenuItem item = menu.findItem(R.id.overview_search_action);
        item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                onSearchFinished();
                return true;
            }
        });
    }

    @Override
    public int getLoadingFrame() {
        return R.id.base_request_loading_frame;
    }

    public int getSearchHintResource() {
        return R.string.search_default_hint;
    }

    public void onQueryTextSubmitted(@NonNull String query){}

    public void onQueryTextChanged(String newText){}

    public void onSearchFinished(){}

    public void presentAdapterData( ) {
        adapter.updateData();
    }

    public abstract SearchAdapterDecorator provideAdapter();

    public int getHeaderLayoutResource() {
        return NO_ID;
    }

    public void onHeaderSetup( View header ) {
        return;
    }
}
