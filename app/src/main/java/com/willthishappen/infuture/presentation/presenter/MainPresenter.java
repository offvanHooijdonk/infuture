package com.willthishappen.infuture.presentation.presenter;

import com.google.firebase.auth.FirebaseAuth;
import com.willthishappen.infuture.presentation.ui.IMainView;

import javax.inject.Inject;

/**
 * Created by Yahor_Fralou on 8/8/2017 5:26 PM.
 */

public class MainPresenter {

    private IMainView view;

    @Inject
    public MainPresenter(IMainView view) {
        this.view = view;
    }

    public void onViewCreated() {

    }

    public void onLogoutClick() {
        FirebaseAuth.getInstance().signOut();

        view.navigateToLogin();
    }
}
