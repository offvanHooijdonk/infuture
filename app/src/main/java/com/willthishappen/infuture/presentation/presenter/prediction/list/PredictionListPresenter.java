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
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

public class PredictionListPresenter {
    private static final String DB_PREDICT_LIKES_NODE = "likes";
    private static final String DB_USER_LIKES_NODE = "likes";

    private IPredictionListView view;
    private DatabaseReference predictDB;
    private DatabaseReference userDB;
    private List<PredictBean> predictList = new ArrayList<>();
    private Integer processedPredictsCount = 0;
    Map<String, Object> predictLikeMap = new HashMap<>(1);
    Map<String, Object> userLikeMap = new HashMap<>(1);

    @Inject
    public PredictionListPresenter(IPredictionListView view,
                                   @Named(RepositoryModule.DB_PREDICTIONS) DatabaseReference predictDB,
                                   @Named(RepositoryModule.DB_USERS) DatabaseReference userDB) {
        this.view = view;
        this.predictDB = predictDB;
        this.userDB = userDB;
    }

    public void onViewCreated() {
        view.showRefreshProcess(true);
        UserBean user = SessionHelper.getCurrentUser();
        // TODO pagination
        predictDB.limitToFirst(30).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                predictList.clear();

                for (DataSnapshot predictSnapshot : dataSnapshot.getChildren()) {
                    PredictBean predictBean = predictSnapshot.getValue(PredictBean.class);
                    if (predictBean != null) {
                        predictBean.setId(predictSnapshot.getKey());
                        //predictBean.updateLikesInfo(user);
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

        // fixme temp
        predictDB.orderByChild("likes").equalTo(user.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(InFutureApplication.LOG, Objects.toString(dataSnapshot));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onPredictLiked(String predictId, boolean like) {
        String userId = SessionHelper.getCurrentUser().getId();
        Log.i(InFutureApplication.LOG, "Triggered like for " + predictId + " , " + like);
        
        // TODO update user likes list, retrieve it for current user in Main Activity

        if (like) {
            predictLikeMap.clear();
            predictLikeMap.put(userId, userId);
            predictDB.child(predictId + "/" + DB_PREDICT_LIKES_NODE).updateChildren(predictLikeMap);
            userLikeMap.clear();
            userLikeMap.put(predictId, predictId);
            userDB.child(userId + "/" + DB_USER_LIKES_NODE).updateChildren(userLikeMap);
        } else {
            predictLikeMap.clear();
            predictLikeMap.put(userId, null);
            predictDB.child(predictId + "/" + DB_PREDICT_LIKES_NODE).updateChildren(predictLikeMap);
            userLikeMap.clear();
            userLikeMap.put(predictId, null);
            userDB.child(userId + "/" + DB_USER_LIKES_NODE).updateChildren(userLikeMap);
        }
    }
}
