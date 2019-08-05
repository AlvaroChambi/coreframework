package es.developer.achambi.coreframework.ui.profile;

import android.net.Uri;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import es.developer.achambi.coreframework.ui.Presenter;

public class BaseProfilePresenter{
    private static final String PROFILE_NAME_SAVED_STATE_KEY = "profile_name_key";
    private String name;

    public String getProfileName() {
        if( name == null ) {
            name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        }
        return name;
    }

    public Uri getPhotoUrl() {
        return FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();
    }
}
