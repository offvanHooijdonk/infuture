package com.willthishappen.infuture.app;

import android.app.Activity;
import android.app.Application;

import com.willthishappen.infuture.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Yahor_Fralou on 7/31/2017 6:32 PM.
 */

public class InFutureApplication extends Application implements HasActivityInjector {
    public static final String LOG = "InFutureLog";

    @Inject
    DispatchingAndroidInjector<Activity> androidInjector;

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
        return androidInjector;
    }
}
