package com.willthishappen.infuture.di.presentation.main;

import com.willthishappen.infuture.presentation.ui.IMainView;
import com.willthishappen.infuture.presentation.ui.MainActivity;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Yahor_Fralou on 8/8/2017 4:25 PM.
 */

@Module()
public abstract class MainModule {
    @Binds
    abstract IMainView provideMainView(MainActivity mainActivity);

}
