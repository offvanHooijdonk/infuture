package com.willthishappen.infuture.di.presentation.prediction.list;

import com.willthishappen.infuture.presentation.ui.prediction.list.IPredictionListView;
import com.willthishappen.infuture.presentation.ui.prediction.list.PredictListFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Yahor_Fralou on 8/8/2017 7:20 PM.
 */

@Module
public abstract class PredictionListModule {
    @Binds
    abstract IPredictionListView providePredictionListView(PredictListFragment fragment);
}
