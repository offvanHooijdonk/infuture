package com.willthishappen.infuture.presentation.ui.prediction.edit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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

        PredictBean predictBean = new PredictBean(null, "How much wood would a woodchuck chuck?", predictDate.getTimeInMillis());

        DatabaseReference push = database.child("predictions").push();
        push.setValue(predictBean)
                .addOnCompleteListener(task ->{
                        push.getKey();
                    closeActivity();});
    }

    private void closeActivity() {
        this.finish();
    }
}
