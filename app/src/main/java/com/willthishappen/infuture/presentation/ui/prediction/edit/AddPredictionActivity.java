package com.willthishappen.infuture.presentation.ui.prediction.edit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.willthishappen.infuture.R;
import com.willthishappen.infuture.domain.PredictBean;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddPredictionActivity extends AppCompatActivity {
    private DatabaseReference database;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_predict);

        ButterKnife.bind(this);

        database = FirebaseDatabase.getInstance().getReference();
    }

    @OnClick(R.id.fabSave)
    public void onSaveClick() {
        Calendar predictDate = Calendar.getInstance();
        predictDate.add(Calendar.DAY_OF_MONTH, 2);

        PredictBean predictBean = new PredictBean(1, "How much wood would a woodchuck chuck?", predictDate.getTimeInMillis());

        database.child("predictions").child(String.valueOf(predictBean.getId())).setValue(predictBean)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                closeActivity();
            }
        });
    }

    private void closeActivity() {
        this.finish();
    }
}
