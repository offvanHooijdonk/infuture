package com.willthishappen.infuture.presentation.ui.auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.willthishappen.infuture.R;
import com.willthishappen.infuture.app.InFutureApplication;
import com.willthishappen.infuture.presentation.presenter.auth.LoginPresenter;
import com.willthishappen.infuture.presentation.ui.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private static final int RC_SIGN_IN = 1;
    @Inject
    LoginPresenter presenter;

    @BindView(R.id.blockAuth)
    View blockAuth;
    @BindView(R.id.blockLogo)
    View blockLogo;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        presenter.onViewCreated();
    }

    @OnClick(R.id.btnChooseLogin)
    public void onChooseLogin() {
        navigateToMain();
    }

    @OnClick(R.id.btnGoogleAccount)
    public void onGoogleAccountClick() {

        presenter.onGoogleAuthSelected();
    }

    @Override
    public void startGoogleAuthView(GoogleApiClient apiClient) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void showAuthProgressDialog(boolean show) {
        if (show) {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setCancelable(false);
                progressDialog.setMessage(getString(R.string.login_progress_dialog_msg));
            }
            progressDialog.show();
        } else {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(InFutureApplication.LOG, "Result Code:" + requestCode);

        if (requestCode == RC_SIGN_IN) {
            presenter.onGoogleAuthReturn(data);
        }
    }

    @Override
    public void showAuthOptions(boolean show) {
        if (show) {
            blockLogo.setVisibility(View.GONE);
            blockAuth.setVisibility(View.VISIBLE);
        } else {
            blockAuth.setVisibility(View.GONE);
            blockLogo.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Connection Error " + connectionResult.getErrorMessage(), Toast.LENGTH_LONG).show(); // todo improve
    }
}
