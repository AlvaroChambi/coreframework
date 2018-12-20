package es.developer.achambi.coreframework.ui.navigation;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;import android.os.Bundle;
import android.support.annotation.Nullable;

import es.developer.achambi.coreframework.R;

public abstract class BaseNavigationActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity_layout);
        if( savedInstanceState == null ) {
            replaceFragment( provideEntryFragment(), null );
        }
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.inflateMenu(provideMenuResource());
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    public abstract int provideMenuResource();
    public abstract NavigationFragment provideEntryFragment();

    protected void replaceFragment( Fragment fragment, String tag ) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace( R.id.navigation_fragment_frame, fragment, tag )
                .commit();
    }
}
