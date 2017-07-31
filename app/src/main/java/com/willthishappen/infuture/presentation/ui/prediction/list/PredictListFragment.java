package com.willthishappen.infuture.presentation.ui.prediction.list;

import android.app.Fragment;
import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PredictListFragment extends Fragment {

    @Bind(R.id.listPredicts)
    RecyclerView listPredicts;
    @Bind(R.id.fabAddPredict)
    FloatingActionButton fabAddPredict;

    private Context ctx;
    private List<PredictBean> predictList = new ArrayList<>();
    private PredictListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
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

        initStubList();
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
    }

    private void initStubList() {
        predictList.clear();
        for (int i = 0; i < 7; i++) {
            predictList.add(new PredictBean());
        }
    }
}
