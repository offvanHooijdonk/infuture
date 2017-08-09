package com.willthishappen.infuture.presentation.ui.prediction.list;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.willthishappen.infuture.R;
import com.willthishappen.infuture.domain.PredictBean;
import com.willthishappen.infuture.presentation.presenter.prediction.list.PredictionListPresenter;
import com.willthishappen.infuture.presentation.ui.prediction.edit.AddPredictionActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class PredictListFragment extends Fragment implements IPredictionListView {

    @BindView(R.id.listPredicts)
    RecyclerView listPredicts;
    @BindView(R.id.fabAddPredict)
    FloatingActionButton fabAddPredict;

    private Context ctx;
    private List<PredictBean> predictList = new ArrayList<>();
    private PredictListAdapter adapter;

    @Inject
    PredictionListPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        View v = super.onCreateView(inflater, container, savedInstanceState);
        if (v == null) {
            v = inflater.inflate(R.layout.f_predict_list, container, false);
        }
        ctx = getActivity();

        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        //initStubList();
        adapter = new PredictListAdapter(ctx, predictList);

        listPredicts.setLayoutManager(new LinearLayoutManager(ctx));
        listPredicts.setAdapter(adapter);
        listPredicts.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    fabAddPredict.hide();
                } else {
                    fabAddPredict.show();
                }
            }
        });

        presenter.onViewCreated();
    }

    @OnClick(R.id.fabAddPredict)
    public void onAddPredictClick() {
        startAddPredictActivity();
    }

    @Override
    public void updatePredictList(List<PredictBean> list) {
        predictList.clear();
        predictList.addAll(list);

        adapter.notifyDataSetChanged();
    }

    private void startAddPredictActivity() {
        startActivity(new Intent(ctx, AddPredictionActivity.class));
    }

    /*private void initStubList() {
        predictList.clear();
        for (int i = 0; i < 7; i++) {
            predictList.add(new PredictBean());
        }
    }*/
}
