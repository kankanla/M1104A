package com.kankanla.e560.m1104a.work;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import java.util.Set;

public class Bluetooth_status extends Service {
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice bluetoothDevice;
    private BluetoothManager bluetoothManager;

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
        BluetoothAdapter temp = BluetoothAdapter.getDefaultAdapter();
        bluetoothAdapter = temp;
        BluetoothManager temp2 = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        bluetoothManager = temp2;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bt_action();
        return super.onStartCommand(intent, flags, startId);
    }


    protected void bt_action() {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                System.out.println("-----------------------------------------------8899-----------");

                Set<BluetoothDevice> bluetoothDevice = bluetoothAdapter.getBondedDevices();
                for(BluetoothDevice b: bluetoothDevice){
                    System.out.println(b.getName());
                    System.out.println(b.getAddress());
                }


                switch (intent.getAction()) {
                    case BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED:
                        System.out.println("BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED");

                        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                        switch (intent.getIntExtra(BluetoothAdapter.EXTRA_CONNECTION_STATE, 0)) {
                            case BluetoothAdapter.STATE_DISCONNECTED:
                                System.out.println("public static final int STATE_DISCONNECTED = 0");
                                break;
                            case BluetoothAdapter.STATE_CONNECTING:
                                System.out.println("public static final int STATE_CONNECTING = 1");
                                break;
                            case BluetoothAdapter.STATE_CONNECTED:
                                System.out.println("public static final int STATE_CONNECTED = 2");
                                System.out.println(device.getName());
                                System.out.println(device.getAddress());
                                break;
                            case BluetoothAdapter.STATE_DISCONNECTING:
                                System.out.println("public static final int STATE_DISCONNECTING = 3");
                                break;
                        }


                        break;
                    case BluetoothAdapter.ACTION_STATE_CHANGED:
                        System.out.println("BluetoothAdapter.ACTION_STATE_CHANGED");
                        break;
                    case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                        System.out.println("BluetoothAdapter.ACTION_DISCOVERY_FINISHED");
                        break;
                    case BluetoothAdapter.ACTION_DISCOVERY_STARTED:
                        System.out.println("BluetoothAdapter.ACTION_DISCOVERY_STARTED");
                        break;
                    case BluetoothAdapter.ACTION_LOCAL_NAME_CHANGED:
                        System.out.println("BluetoothAdapter.ACTION_LOCAL_NAME_CHANGED");
                        break;
                    case BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE:
                        System.out.println("BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE");
                        break;
                    case BluetoothAdapter.ACTION_REQUEST_ENABLE:
                        System.out.println("BluetoothAdapter.ACTION_REQUEST_ENABLE");
                        break;
                    case BluetoothAdapter.ACTION_SCAN_MODE_CHANGED:
                        System.out.println("BluetoothAdapter.ACTION_SCAN_MODE_CHANGED");
                        break;
                    case BluetoothDevice.ACTION_BOND_STATE_CHANGED:
                        System.out.println("BluetoothDevice.ACTION_BOND_STATE_CHANGED");
                        break;
                    case BluetoothDevice.ACTION_ACL_CONNECTED:
                        System.out.println("BluetoothDevice.ACTION_ACL_CONNECTED");
                        break;
                    case BluetoothDevice.ACTION_FOUND:
                        System.out.println("BluetoothDevice.ACTION_FOUND");
                        break;
                    case BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED:
                        System.out.println("BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED");
                        break;
                    case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                        System.out.println("BluetoothDevice.ACTION_ACL_DISCONNECTED");
                        break;
                    case BluetoothDevice.ACTION_CLASS_CHANGED:
                        System.out.println("BluetoothDevice.ACTION_CLASS_CHANGED");
                        break;
                    case BluetoothDevice.ACTION_NAME_CHANGED:
                        System.out.println("BluetoothDevice.ACTION_NAME_CHANGED");
                        break;
                    case BluetoothDevice.ACTION_PAIRING_REQUEST:
                        System.out.println("BluetoothDevice.ACTION_PAIRING_REQUEST");
                        break;
                    case BluetoothDevice.ACTION_UUID:
                        System.out.println("BluetoothDevice.ACTION_UUID");
                        break;
                }
                System.out.println("-----------------------------------------------8899-----------");
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_LOCAL_NAME_CHANGED);
        intentFilter.addAction(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        intentFilter.addAction(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        intentFilter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);

        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        intentFilter.addAction(BluetoothDevice.ACTION_CLASS_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_NAME_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_PAIRING_REQUEST);
        intentFilter.addAction(BluetoothDevice.ACTION_UUID);

        registerReceiver(broadcastReceiver, intentFilter);
    }


}
