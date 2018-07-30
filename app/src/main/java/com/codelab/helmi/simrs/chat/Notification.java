package com.codelab.helmi.simrs.chat;

import android.support.annotation.NonNull;
import android.util.Log;

import com.codelab.helmi.simrs.api.RestApi;
import com.codelab.helmi.simrs.api.RestServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notification {
    public void send(String deviceId) {
        RestServer.getNotification().create(RestApi.class).sendMessage(new FirebaseMessage(deviceId)).enqueue(new Callback<FirebaseMessage>() {
            @Override
            public void onResponse(@NonNull Call<FirebaseMessage> call, @NonNull Response<FirebaseMessage> response) {
                Log.e("Message Response","Send");
            }
            @Override
            public void onFailure(@NonNull Call<FirebaseMessage> call, @NonNull Throwable t) {
                Log.e("Message Response","Fail");
            }
        });
    }
}
