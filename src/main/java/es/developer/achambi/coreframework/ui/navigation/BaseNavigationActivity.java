package es.developer.achambi.coreframework.ui.navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;import android.os.Bundle;
import androidx.annotation.Nullable;

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
    public abstract Fragment provideEntryFragment();

    protected void replaceFragment( Fragment fragment, String tag ) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace( R.id.navigation_fragment_frame, fragment, tag )
                .commit();
    }
}
