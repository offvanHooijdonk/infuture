package com.willthishappen.infuture.di.presentation.auth;

import com.willthishappen.infuture.presentation.ui.auth.LoginActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = {AuthModule.class})
public interface AuthComponent extends AndroidInjector<LoginActivity> {

    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<LoginActivity> {}

}
