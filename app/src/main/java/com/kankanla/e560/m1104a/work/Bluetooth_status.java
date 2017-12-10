package com.kankanla.e560.m1104a.work;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.kankanla.e560.m1104a.R;

import java.util.List;

public class Bluetooth_status extends Service {
    private BluetoothAdapter bluetoothAdapter;
    private NotificationManager notificationManager;

    public Bluetooth_status() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (bluetoothAdapter != null) {
            if (!bluetoothAdapter.isEnabled()) {

            }
            bt_change();
            bt_info();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private void bt_change() {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                System.out.println("---------------------broadcastReceiver----------------------");
                System.out.println(intent.getAction());
                bt_info();
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothHeadset.ACTION_AUDIO_STATE_CHANGED);
        intentFilter.addAction(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private void bt_info() {
        BluetoothProfile.ServiceListener serviceListener = new BluetoothProfile.ServiceListener() {
            @Override
            public void onServiceConnected(int profile, BluetoothProfile proxy) {
                System.out.println("---------onServiceConnected-----------");

                if (profile == BluetoothProfile.HEADSET) {
                    HEADSET_noti(proxy.getConnectedDevices());
                }

                if (profile == BluetoothProfile.A2DP) {
                    A2DP_noti(proxy.getConnectedDevices());
                }
            }

            @Override
            public void onServiceDisconnected(int profile) {
                System.out.println("---------onServiceDisconnected-----------");
            }
        };

        bluetoothAdapter.getProfileProxy(getApplicationContext(), serviceListener, BluetoothProfile.HEADSET);
        bluetoothAdapter.getProfileProxy(getApplicationContext(), serviceListener, BluetoothProfile.A2DP);

    }

    private void HEADSET_noti(List<BluetoothDevice> devices) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "HEADSET");
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        builder.setSmallIcon(android.R.drawable.btn_default);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.f6146);
        builder.setLargeIcon(bitmap);
        for (BluetoothDevice bt : devices) {
            builder.setContentTitle("HEADSET >>" + bt.getName());
            inboxStyle.addLine("HEADSET >>" + bt.getName());
            inboxStyle.addLine("HEADSET >>" + bt.getAddress());
        }
        builder.setStyle(inboxStyle);
        Notification notification = builder.build();
        if (devices.size() > 0) {
            notificationManager.notify(12, notification);
        } else {
            notificationManager.cancel(12);
        }
    }

    private void A2DP_noti(List<BluetoothDevice> devices) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "A2DP");
        builder.setSmallIcon(android.R.drawable.btn_default);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.f6146);
        builder.setLargeIcon(bitmap);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        for (BluetoothDevice bt : devices) {
            builder.setContentTitle("A2DP_noti >>" + bt.getName());
            inboxStyle.addLine("A2DP_noti >>" + bt.getName());
            inboxStyle.addLine("A2DP_noti >>" + bt.getAddress());
        }
        builder.setStyle(inboxStyle);
        Notification notification = builder.build();
        if (devices.size() > 0) {
            notificationManager.notify(112, notification);
        } else {
            notificationManager.cancel(112);
        }
    }
}

