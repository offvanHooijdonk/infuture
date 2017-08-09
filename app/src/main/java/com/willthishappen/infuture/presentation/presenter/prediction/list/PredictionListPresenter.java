package com.willthishappen.infuture.presentation.presenter.prediction.list;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.willthishappen.infuture.di.repository.RepositoryModule;
import com.willthishappen.infuture.domain.PredictBean;
import com.willthishappen.infuture.presentation.ui.prediction.list.IPredictionListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class PredictionListPresenter {

    private IPredictionListView view;
    private DatabaseReference predictionsDB;
    private List<PredictBean> predictList = new ArrayList<>();

    @Inject
    public PredictionListPresenter(IPredictionListView view, @Named(RepositoryModule.DB_PREDICTIONS) DatabaseReference predictionsDB) {
        this.view = view;
        this.predictionsDB = predictionsDB;
    }

    public void onViewCreated() {
        predictionsDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                predictList.clear();
                for (DataSnapshot predictSnapshot : dataSnapshot.getChildren()) {
                    PredictBean predictBean = predictSnapshot.getValue(PredictBean.class);
                    if (predictBean != null) {
                        predictBean.setId(predictSnapshot.getKey());
                        predictList.add(predictBean);
                    }
                }

                view.updatePredictList(predictList);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // TODO add common implementation for the DB error
            }
        });
    }
}
