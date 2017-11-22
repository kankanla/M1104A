package com.kankanla.e560.m1104a.work;

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
import android.os.IBinder;

import java.util.List;

public class Bluetooth_status extends Service {
    private BluetoothAdapter bluetoothAdapter;

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
        bluetoothAdapter.enable();
        bt_change();
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
//        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
//        intentFilter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
//        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
//        intentFilter.addAction(BluetoothDevice.ACTION_NAME_CHANGED);
//        intentFilter.addAction(BluetoothAdapter.ACTION_LOCAL_NAME_CHANGED);
        intentFilter.addAction(BluetoothHeadset.ACTION_AUDIO_STATE_CHANGED);
        intentFilter.addAction(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothA2dp.ACTION_PLAYING_STATE_CHANGED);
        registerReceiver(broadcastReceiver, intentFilter);
    }


    private void bt_info() {


        BluetoothProfile.ServiceListener serviceListener = new BluetoothProfile.ServiceListener() {
            @Override
            public void onServiceConnected(int profile, BluetoothProfile proxy) {
                System.out.println("---------onServiceConnected-----------");
                System.out.println(profile);
                if (profile == BluetoothProfile.A2DP) {
                    List<BluetoothDevice> bluetoothDevices = proxy.getConnectedDevices();
                    for (BluetoothDevice bt : bluetoothDevices) {
                        System.out.println(bt.getName());
                        System.out.println(bt.getAddress());
                        System.out.println(bt.getBondState());
                        System.out.println(bt.getAddress());
                        System.out.println("getConnectionState-----  " + proxy.getConnectionState(bt));
                    }
                }

                if (profile == BluetoothProfile.HEADSET) {
                    List<BluetoothDevice> bluetoothDevices = proxy.getConnectedDevices();
                    for (BluetoothDevice bt : bluetoothDevices) {
                        System.out.println(bt.getName());
                        System.out.println(bt.getAddress());
                        System.out.println(bt.getBondState());
                        System.out.println(bt.getAddress());
                        System.out.println("getConnectionState-----  " + proxy.getConnectionState(bt));
                    }
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

}

