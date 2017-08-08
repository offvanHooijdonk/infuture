package com.willthishappen.infuture.di;

import android.content.Context;

import com.willthishappen.infuture.app.InFutureApplication;
import com.willthishappen.infuture.di.presentation.main.MainComponent;
import com.willthishappen.infuture.di.presentation.prediction.list.PredictionListComponent;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = {MainComponent.class, PredictionListComponent.class})
public class AppModule {

    @Provides
    Context provideContext(InFutureApplication application) {
        return application.getApplicationContext();
    }
}
