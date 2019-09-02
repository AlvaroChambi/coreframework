package es.developer.achambi.coreframework.ui.navigation;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import es.developer.achambi.coreframework.ui.BaseFragment;

public abstract class NavigationFragment extends BaseFragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle( getTitleResource() );
    }

    public abstract int getTitleResource();
}
