package es.developer.achambi.coreframework.ui.login;

import android.content.Intent;

import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import es.developer.achambi.coreframework.R;
import es.developer.achambi.coreframework.ui.BaseFragment;

import static android.app.Activity.RESULT_OK;

public abstract class BaseLoginFragment extends BaseFragment implements View.OnClickListener {
    private static final int SIGN_IN_REQUEST_CODE = 101;

    @Override
    public int getLayoutResource() {
        return R.layout.login_fragment_layout;
    }

    @Override
    public void onViewSetup(@NotNull View view) {
        View loginButton = view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);

        if( FirebaseAuth.getInstance().getCurrentUser() != null ) {
            startActivity( getNextScreenIntent() );
            getActivity().finish();
        }
    }

    public abstract Intent getNextScreenIntent();

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == SIGN_IN_REQUEST_CODE ) {
            IdpResponse response = IdpResponse.fromResultIntent( data );

            if( resultCode == RESULT_OK ) {
                startActivity( getNextScreenIntent() );
                getActivity().finish();
            } else {
                if( response != null ){
                    Snackbar snackbar = Snackbar.make( getView(),
                            response.getError().getMessage(), Snackbar.LENGTH_INDEFINITE );
                    snackbar.show();
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build() );
        startActivityForResult( AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders( providers )
                .build(), SIGN_IN_REQUEST_CODE);
    }
}
