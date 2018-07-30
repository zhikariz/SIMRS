package com.codelab.helmi.simrs.fcm;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;

import com.codelab.helmi.simrs.MainActivity;
import com.codelab.helmi.simrs.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        showNotification(getResources().getString(R.string.app_name));
    }

    private void showNotification(String title) {
        playNotificationSound();
        NotificationUtils mNotificationUtils = new NotificationUtils(this);
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(i);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder nb =  new Notification.Builder(getApplicationContext(), NotificationUtils.ANDROID_CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText("Pesan baru")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setAutoCancel(true)
                    .setGroup(NotificationUtils.ANDROID_CHANNEL_ID)
                    .setContentIntent(pendingIntent);
            mNotificationUtils.getManager().notify(101, nb.build());
        }else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NotificationUtils.ANDROID_CHANNEL_ID)
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setContentText("Pesan baru")
                    .setGroup(NotificationUtils.ANDROID_CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentIntent(pendingIntent);
            NotificationManagerCompat manager = NotificationManagerCompat.from(this);
            //NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(0, builder.build());
        }
    }


    public void playNotificationSound() {
        try {
            Uri alarmSound = RingtoneManager.getActualDefaultRingtoneUri(getApplicationContext(), RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), alarmSound);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
