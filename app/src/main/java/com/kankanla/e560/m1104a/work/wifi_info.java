package com.kankanla.e560.m1104a.work;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.kankanla.e560.m1104a.R;
import com.kankanla.e560.m1104a.noti;

public class wifi_info extends Service {
    private NotificationManager notificationManager;
    private WifiManager wifiManager;
    private WifiInfo wifiInfo;
    private String CID = "3721";
    private int NID = 3721;
    private Intent wifi_set_intent;

    public wifi_info() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        wifi_set_intent = new Intent();
        wifi_set_intent.setAction(Settings.ACTION_WIFI_SETTINGS);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        wifi_action();
        return super.onStartCommand(intent, flags, startId);
    }

    protected void wifi_action() {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            wifi_info_notifi();
                        }
                    }).start();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);
    }


    protected void wifi_info_notifi() {
        wifiInfo = wifiManager.getConnectionInfo();

        String ip = IP_addr(wifiInfo.getIpAddress());
        String SSID = wifiInfo.getSSID();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CID);
        builder.setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        builder.setSmallIcon(R.drawable.f6146);
        builder.setContentTitle(getString(R.string.Wi_Fi_SPOT));
        builder.setContentText("WiFiを接続していません");

        if (wifiInfo.getIpAddress() != 0) {
            notificationManager.cancel(NID);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.f6146);
            builder.setLargeIcon(bitmap);
            builder.setContentTitle(getString(R.string.Wi_Fi_SPOT));
            builder.setContentText("SSID:" + SSID + "   IP:" + ip);


            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
            inboxStyle.addLine("ネットワーク名:" + wifiInfo.getSSID());
            inboxStyle.addLine("IPアドレス:" + ip);
            if (wifiInfo.getLinkSpeed() > 0) {
                inboxStyle.addLine("リンク速度:" + wifiInfo.getLinkSpeed() + "Mbps");
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (wifiManager.is5GHzBandSupported()) {
                    inboxStyle.addLine("周波数:" + "5GHz");
                } else {
                    inboxStyle.addLine("周波数:" + "2.4GHz");
                }
            }
            inboxStyle.addLine(Thread.currentThread().getName());
            builder.setStyle(inboxStyle);
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
            stackBuilder.addParentStack(noti.class);
            stackBuilder.addNextIntent(wifi_set_intent);
            PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);
            builder.setContentIntent(pendingIntent);
        }

        Notification notification = builder.build();
        notification.flags = Notification.FLAG_NO_CLEAR;
        notificationManager.notify(NID, notification);
    }

    protected String IP_addr(int ipAddress) {
        return ((ipAddress >> 0 & 0xFF) + "." +
                ((ipAddress >> 8) & 0xFF) + "." +
                ((ipAddress >> 16) & 0xFF) + "." +
                ((ipAddress >> 24) & 0xFF));
    }
}
