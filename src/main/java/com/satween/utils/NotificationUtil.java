package com.satween.utils;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

/**
 *
 * <p/>
 * https://github.com/satween/utils
 */

public class NotificationUtil {
    private static final int NO_RECOURSE_ID = -1;
    Context context;
    NotificationManager manager;

    public NotificationUtil(Context context) {
        this.context = context;
        this.manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }


    public static class ProgressbarNotification {

        private int maxProgress;
        private int notificationId;
        private NotificationCompat.Builder builder;
        private NotificationManager manager;
        private boolean isIndeterminate;


        ProgressbarNotification(int id, NotificationCompat.Builder builder, NotificationManager manager, int maxProgress) {
            this.notificationId = id;
            this.builder = builder;
            this.manager = manager;
            this.maxProgress = maxProgress;
        }

        public void updateProgress(int value) {
            this.builder.setProgress(maxProgress, value, isIndeterminate);
            show();
        }

        public void setIndertiminate() {
            this.isIndeterminate = true;
        }

        public void setMax(int max) {
            this.maxProgress = max;
        }

        public void show() {
            manager.notify(notificationId, builder.build());
        }


    }

    public static ProgressbarNotification EMPTY_PROGRESSBAR = new ProgressbarNotification(-1, null, null, NO_RECOURSE_ID) {

        @Override
        public void updateProgress(int value) {

        }

        @Override
        public void setMax(int max) {

        }

        @Override
        public void show() {

        }
    };


    public ProgressbarNotification notificationWithProgressBar(int id, String title, String description, int resourceId, int maxProgress) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle(title).setContentText(description);

        if (resourceId > 0) {
            builder.setSmallIcon(resourceId);
        }

        return new ProgressbarNotification(id, builder, manager, maxProgress);
    }

    public ProgressbarNotification notificationWithProgressBar(int id, String title, String description, int maxProgress) {
        return notificationWithProgressBar(id, title, description, NO_RECOURSE_ID);
    }


}
