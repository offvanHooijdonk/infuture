package com.willthishappen.infuture.presentation.presenter.prediction.list;

import com.google.firebase.database.DatabaseReference;
import com.willthishappen.infuture.di.repository.RepositoryModule;
import com.willthishappen.infuture.presentation.ui.prediction.list.IPredictionListView;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Yahor_Fralou on 8/8/2017 7:29 PM.
 */

public class PredictionListPresenter {

    private IPredictionListView view;
    private DatabaseReference predictionsDB;

    @Inject
    public PredictionListPresenter(IPredictionListView view, @Named(RepositoryModule.DB_PREDICTIONS)DatabaseReference predictionsDB) {
        this.view = view;
        this.predictionsDB = predictionsDB;
    }
}
