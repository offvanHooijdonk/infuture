package com.willthishappen.infuture.presentation.presenter.prediction.list;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.willthishappen.infuture.app.InFutureApplication;
import com.willthishappen.infuture.di.repository.RepositoryModule;
import com.willthishappen.infuture.domain.PredictBean;
import com.willthishappen.infuture.domain.UserBean;
import com.willthishappen.infuture.presentation.ui.prediction.list.IPredictionListView;
import com.willthishappen.infuture.util.SessionHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

public class PredictionListPresenter {
    private static final String DB_LIKES_NODE = "likes";

    private IPredictionListView view;
    private DatabaseReference predictDB;
    private List<PredictBean> predictList = new ArrayList<>();
    private Integer processedPredictsCount = 0;

    @Inject
    public PredictionListPresenter(IPredictionListView view, @Named(RepositoryModule.DB_PREDICTIONS) DatabaseReference predictDB) {
        this.view = view;
        this.predictDB = predictDB;
    }

    public void onViewCreated() {
        view.showRefreshProcess(true);
        // TODO pagination
        predictDB.limitToFirst(30).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                predictList.clear();
                UserBean user = SessionHelper.getCurrentUser();

                for (DataSnapshot predictSnapshot : dataSnapshot.getChildren()) {
                    PredictBean predictBean = predictSnapshot.getValue(PredictBean.class);
                    if (predictBean != null) {
                        predictBean.setId(predictSnapshot.getKey());
                        predictBean.updateLikesInfo(user);
                        predictList.add(predictBean);
                    }
                }

                view.showRefreshProcess(false);
                view.updatePredictList(predictList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // TODO add common implementation for the DB error
            }
        });
    }

    public void onPredictLiked(String predictId, boolean like) {
        String userId = SessionHelper.getCurrentUser().getId();
        Log.i(InFutureApplication.LOG, "Triggered like for " + predictId + " , " + like);

        if (like) {
            Map<String, Object> likeMap = new HashMap<>(1);
            likeMap.put(userId, userId);
            predictDB.child(predictId + "/" + DB_LIKES_NODE).updateChildren(likeMap);
        } else {
            Map<String, Object> likeMap = new HashMap<>(1);
            likeMap.put(userId, null);
            predictDB.child(predictId + "/" + DB_LIKES_NODE).updateChildren(likeMap);
        }
    }
}
