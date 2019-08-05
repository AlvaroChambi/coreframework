package es.developer.achambi.coreframework.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Base fragment, can override onBack event and allows options menu on action bar
 */
public abstract class BaseFragment extends Fragment {
    private int timesViewCreated = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(getLayoutResource(), container, false);
        timesViewCreated++;
        return root;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if( !isViewRecreated() ) {
            onViewSetup(view, savedInstanceState);
        }
    }

    public boolean isViewRecreated() {
        return timesViewCreated > 1;
    }

    public abstract int getLayoutResource();
    public abstract void onViewSetup(View view, @Nullable Bundle savedInstanceState);
}
