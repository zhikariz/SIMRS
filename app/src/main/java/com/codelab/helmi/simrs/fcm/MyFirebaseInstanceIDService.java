package com.codelab.helmi.simrs.fcm;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //SharedPref pref = new SharedPref(getApplicationContext());
        //pref.setFcmRegId(refreshedToken);
//        if(pref.getProfile().id != null) {
//            sendRegistrationToServer(pref.getProfile().id, refreshedToken);
//        }
        Intent registrationComplete = new Intent("COMPLETED");
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }


    private void sendRegistrationToServer(String uid, String token) {
//
//        API api = RestAdapter.createAPI();
//        Call<CallbackDevice> callbackCall = api.registerDevice(deviceInfo);
//        callbackCall.enqueue(new Callback<CallbackDevice>() {
//            @Override
//            public void onResponse(Call<CallbackDevice> call, Response<CallbackDevice> response) {
//                CallbackDevice resp = response.body();
//                if (resp != null && resp.status.equals("success")) {
//                   // sharedPref.setOpenAppCounter(0);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CallbackDevice> call, Throwable t) {
//                Log.e("onFailure", t.getMessage());
//            }
//        });
    }
}


