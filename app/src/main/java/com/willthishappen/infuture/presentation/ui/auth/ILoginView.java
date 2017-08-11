package com.willthishappen.infuture.presentation.ui.auth;

import android.support.annotation.NonNull;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Yahor_Fralou on 8/9/2017 2:47 PM.
 */

public interface ILoginView {
    void startGoogleAuthView(GoogleApiClient apiClient);

    void showAuthProgressDialog(boolean show);

    void showAuthOptions(boolean show);

    void showError(String msg);

    void navigateToMain();

    void onConnectionFailed(@NonNull ConnectionResult connectionResult);
}
