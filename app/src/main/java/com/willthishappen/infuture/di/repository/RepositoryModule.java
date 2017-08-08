package com.willthishappen.infuture.di.repository;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Yahor_Fralou on 8/8/2017 7:15 PM.
 */

@Module
public class RepositoryModule {

    public static final String DB_PREDICTIONS = "database_reference_predictions";
    private static final String NODE_NAME_PREDICTIONS = "predictions";

    @Provides
    @Named(DB_PREDICTIONS)
    DatabaseReference providePredictionDatabase() {
        return FirebaseDatabase.getInstance().getReference(NODE_NAME_PREDICTIONS);
    }
}
