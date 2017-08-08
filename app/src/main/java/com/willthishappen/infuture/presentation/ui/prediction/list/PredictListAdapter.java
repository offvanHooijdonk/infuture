package com.willthishappen.infuture.presentation.ui.prediction.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.willthishappen.infuture.R;
import com.willthishappen.infuture.domain.PredictBean;

import java.util.List;

public class PredictListAdapter extends RecyclerView.Adapter<PredictListAdapter.ViewHolder> {
    private Context ctx;
    private List<PredictBean> predicts;

    public PredictListAdapter(@NonNull Context context, @NonNull List<PredictBean> predicts) {
        this.ctx = context;
        this.predicts = predicts;
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
    }

    @Override
    public int getItemCount() {
        return predicts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtPredictTitle;

        public ViewHolder(View v) {
            super(v);

            txtPredictTitle = (TextView) v.findViewById(R.id.txtPredictText);
        }
    }
}
