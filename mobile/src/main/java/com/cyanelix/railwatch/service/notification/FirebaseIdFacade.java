package com.cyanelix.railwatch.service.notification;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.function.Consumer;

import javax.inject.Inject;

public class FirebaseIdFacade {
    @Inject
    public FirebaseIdFacade() { }

    public void doWithFirebaseId(final Consumer<String> firebaseIdConsumer) {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onSuccess(final InstanceIdResult instanceIdResult) {
                new AsyncTask<String, Void, Void>() {
                    @Override
                    protected Void doInBackground(String... strings) {
                        firebaseIdConsumer.accept(instanceIdResult.getToken());
                        return null;
                    }
                }.execute();
            }
        });
    }
}
