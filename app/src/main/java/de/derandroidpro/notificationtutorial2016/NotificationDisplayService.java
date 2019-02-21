package de.derandroidpro.notificationtutorial2016;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class NotificationDisplayService extends Service {

    public static final int NOTIFICATION_ID = 01;

    public static final String TAG = "NotificationDisplayS";

    public NotificationDisplayService() {}

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        displayNotification("Você sabia?", "BlaBlaBlaBla!");
        stopSelf();
        Log.d(TAG, "Chamou onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void displayNotification(String title, String text){

        Intent notifyIntent = new Intent(this, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                this,
                0,
                notifyIntent,
                PendingIntent.FLAG_ONE_SHOT);

        Intent learnMoreIntent = new Intent(this, LearnMoreActivity.class);
        PendingIntent learnMorePendingIntent = PendingIntent.getActivity(
                this,
                0,
                learnMoreIntent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Action learnMoreAction =
                new NotificationCompat.Action.Builder(
                        0,
                        "SAIBA MAIS",
                        learnMorePendingIntent)
                        .build();

        Intent gotItIntent = new Intent(this, GotItActivity.class);
        PendingIntent gotItPendingIntent = PendingIntent.getActivity(
                this,
                0,
                gotItIntent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Action gotItAction =
                new NotificationCompat.Action.Builder(
                        0,
                        "Ok",
                        gotItPendingIntent)
                        .build();

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_android_white_36dp)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.android_icon))
                .setColor(getResources().getColor(R.color.colorAccent))
                .setVibrate(new long[]{0, 300, 300, 300})
                .setLights(Color.WHITE, 1000, 5000)
                //.setWhen(System.currentTimeMillis())
                .setContentIntent(learnMorePendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(text))
                .addAction(gotItAction)
                .addAction(learnMoreAction);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification.build());
    }
}
