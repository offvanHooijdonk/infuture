package com.willthishappen.infuture.di.presentation.prediction.list;

import com.willthishappen.infuture.presentation.ui.prediction.list.PredictListFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by Yahor_Fralou on 8/8/2017 7:24 PM.
 */

@Subcomponent(modules = {PredictionListModule.class})
public interface PredictionListComponent extends AndroidInjector<PredictListFragment> {

    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<PredictListFragment> {}
}
