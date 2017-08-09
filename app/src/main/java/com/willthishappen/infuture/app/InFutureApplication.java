package com.willthishappen.infuture.app;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import com.willthishappen.infuture.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasFragmentInjector;

public class InFutureApplication extends Application implements HasActivityInjector, HasFragmentInjector {
    public static final String LOG = "InFutureLog";

    @Inject
    DispatchingAndroidInjector<Activity> androidActivityInjector;
    @Inject
    DispatchingAndroidInjector<Fragment> androidFragmentInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return androidActivityInjector;
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return androidFragmentInjector;
    }
}
