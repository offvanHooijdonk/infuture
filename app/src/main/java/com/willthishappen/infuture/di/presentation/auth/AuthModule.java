package com.willthishappen.infuture.di.presentation.auth;


import com.willthishappen.infuture.presentation.ui.auth.ILoginView;
import com.willthishappen.infuture.presentation.ui.auth.LoginActivity;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AuthModule {
    @Binds
    abstract ILoginView provideLoginView(LoginActivity loginActivity);
}
