package es.developer.achambi.coreframework.ui.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;

import es.developer.achambi.coreframework.R;
import es.developer.achambi.coreframework.ui.navigation.NavigationFragment;

public abstract class BaseProfileFragment extends NavigationFragment implements View.OnClickListener {

    private BaseProfilePresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BaseProfilePresenter();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.profile_fragment_layout;
    }

    @Override
    public void onViewSetup(View view) {
        View logoutButton = view.findViewById(R.id.profile_logout_button);
        final TextView profileName = view.findViewById(R.id.profile_user_name_text);
        ImageView profilePhoto = view.findViewById(R.id.profile_photo_image_view);
        profileName.setText( presenter.getProfileName() );
        Glide.with(getActivity())
                .load(presenter.getPhotoUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(profilePhoto);
        logoutButton.setOnClickListener(this);
    }

    @Override
    public int getTitleResource() {
        return R.string.profile_activity_title;
    }

    @Override
    public void onClick(View view) {
        if( view.getId() == R.id.profile_logout_button ) {
            FirebaseAuth.getInstance().signOut();
            onUserLoggedOut();
        }
    }

    public abstract void onUserLoggedOut();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
