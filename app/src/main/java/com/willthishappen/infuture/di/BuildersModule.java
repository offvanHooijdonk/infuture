package com.willthishappen.infuture.di;

import android.app.Activity;
import android.app.Fragment;

import com.willthishappen.infuture.di.presentation.auth.AuthComponent;
import com.willthishappen.infuture.di.presentation.main.MainComponent;
import com.willthishappen.infuture.di.presentation.prediction.list.PredictionListComponent;
import com.willthishappen.infuture.presentation.ui.MainActivity;
import com.willthishappen.infuture.presentation.ui.auth.LoginActivity;
import com.willthishappen.infuture.presentation.ui.prediction.list.PredictListFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.FragmentKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class BuildersModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    public abstract AndroidInjector.Factory<? extends Activity> buildMainActivityInjectorFactory(MainComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(PredictListFragment.class)
    public abstract AndroidInjector.Factory<? extends Fragment> buildPredictListFragmentInjectorFactory(PredictionListComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(LoginActivity.class)
    public abstract AndroidInjector.Factory<? extends Activity> buildLoginActivityInjectorFactory(AuthComponent.Builder builder);

}
