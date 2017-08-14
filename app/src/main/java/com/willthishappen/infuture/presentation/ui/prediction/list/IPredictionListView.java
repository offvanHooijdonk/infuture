package com.willthishappen.infuture.presentation.ui.prediction.list;

import com.willthishappen.infuture.domain.PredictBean;

import java.util.List;

/**
 * Created by Yahor_Fralou on 8/8/2017 7:21 PM.
 */

public interface IPredictionListView {
    void updatePredictList(List<PredictBean> list);

    void showRefreshProcess(boolean show);
}
