package com.willthishappen.infuture.presentation.presenter.auth;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.willthishappen.infuture.R;
import com.willthishappen.infuture.app.InFutureApplication;
import com.willthishappen.infuture.di.repository.RepositoryModule;
import com.willthishappen.infuture.domain.UserBean;
import com.willthishappen.infuture.presentation.ui.auth.ILoginView;

import javax.inject.Inject;
import javax.inject.Named;

public class LoginPresenter {
    private ILoginView view;
    private DatabaseReference userDB;
    private Context ctx;

    @Inject
    public LoginPresenter(ILoginView loginView, @Named(RepositoryModule.DB_USERS) DatabaseReference userDB, Context context) {
        this.userDB = userDB;
        this.view = loginView;
        this.ctx = context;
    }

    public void onViewCreated() {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            // TODO show up login capabilities
        } else {
            view.navigateToMain();
        }
    }

    public void onGoogleAuthSelected() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(ctx.getString(R.string.web_client_id))
                .requestEmail()
                .build();

        GoogleApiClient apiClient = new GoogleApiClient.Builder(ctx)
                .enableAutoManage((FragmentActivity) view, view::onConnectionFailed)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        Log.i(InFutureApplication.LOG, "Starting Google Sign-in Activity");

        view.startGoogleAuthView(apiClient);
    }

    public void onGoogleAuthReturn(Intent data) {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        Log.i(InFutureApplication.LOG, "Result success:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            firebaseAuthWithGoogle(account);
        } else {
            // todo Google Sign In failed, update UI appropriately
            Log.i(InFutureApplication.LOG, "Error picking account! " + result.getStatus().getStatusCode() + result.getStatus().getStatusMessage());
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.i(InFutureApplication.LOG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        //showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.i(InFutureApplication.LOG, "signInWithCredential:success");
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        updateAppUser(toUserBean(user));
                    } else {
                        Log.e(InFutureApplication.LOG, "signInWithCredential:failure", task.getException());

                        view.showError("Failed to authenticate: " + task.getException());
                    }

                    // [START_EXCLUDE]
                    //hideProgressDialog();
                    // [END_EXCLUDE]
                });
    }

    private UserBean toUserBean(FirebaseUser firebaseUser) {

        return new UserBean(firebaseUser.getUid(),
                firebaseUser.getDisplayName(),
                firebaseUser.getPhotoUrl() != null ? firebaseUser.getPhotoUrl().toString() : null,
                firebaseUser.getEmail());
    }

    private void updateAppUser(UserBean userBean) {
        userDB.child(userBean.getId()).setValue(userBean).addOnCompleteListener(task -> view.navigateToMain());
    }
}
