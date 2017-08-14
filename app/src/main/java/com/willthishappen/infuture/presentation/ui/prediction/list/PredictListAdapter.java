package com.willthishappen.infuture.presentation.ui.prediction.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.willthishappen.infuture.R;
import com.willthishappen.infuture.app.InFutureApplication;
import com.willthishappen.infuture.domain.PredictBean;

import java.util.List;

import by.offvanhooijdonk.views.BadgeNumbersView;

public class PredictListAdapter extends RecyclerView.Adapter<PredictListAdapter.ViewHolder> {
    private Context ctx;
    private List<PredictBean> predicts;
    private OnPredictEventListener listener;

    public PredictListAdapter(@NonNull Context context, @NonNull List<PredictBean> predicts, OnPredictEventListener l) {
        this.ctx = context;
        this.predicts = predicts;
        this.listener = l;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.item_predict_small, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        PredictBean predict = predicts.get(position);

        vh.txtPredictTitle.setText(predict.getText());
        vh.badgeLikes.setNumberValue(predict.getLikeNumber());
        vh.badgeLikes.setState(predict.isLikedByCurrentUser() ? BadgeNumbersView.STATE_ACTIVE : BadgeNumbersView.STATE_INACTIVE);
        vh.badgeLikes.setOnClickListener(v -> {
            boolean likeNewValue = !predict.isLikedByCurrentUser();
            Log.i(InFutureApplication.LOG, "Clicked 'like' " + predict.getId() + " , " + likeNewValue);


            if (listener != null) {
                listener.onLikeClick(predict.getId(), likeNewValue);
            }
            /*vh.badgeLikes.setState(pre)*/;
        });
    }

    @Override
    public int getItemCount() {
        return predicts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtPredictTitle;
        BadgeNumbersView badgeLikes;

        public ViewHolder(View v) {
            super(v);

            txtPredictTitle = (TextView) v.findViewById(R.id.txtPredictText);
            badgeLikes = (BadgeNumbersView) v.findViewById(R.id.badgeLiked);
        }
    }

    public interface OnPredictEventListener {
        void onLikeClick(String predictId, boolean like);
    }
}
