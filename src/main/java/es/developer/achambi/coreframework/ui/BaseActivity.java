package es.developer.achambi.coreframework.ui;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import es.developer.achambi.coreframework.R;

public abstract class BaseActivity extends AppCompatActivity {
    public static final String BASE_ARGUMENTS = "base_arguments";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        setTitle(getScreenTitle());
        if(savedInstanceState == null) {
            attachFragment();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public abstract int getScreenTitle();

    public void attachFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Bundle args = getIntent().getBundleExtra(BASE_ARGUMENTS);
        transaction.add(R.id.fragment_frame, getFragment(args));
        transaction.commit();
    }

    public abstract BaseFragment getFragment( Bundle args );

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
