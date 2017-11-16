package com.kankanla.e560.m1104a;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kankanla.e560.m1104a.work.wifi_info;


public class MainActivity extends AppCompatActivity {
    private String TAG = "--------------MainActivity--------------------------";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, wifi_info.class);
        startService(intent);


        ///////////////// test
        bttest();
    }

    protected void bttest() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED:
                        System.out.println("--------------ACTION_CONNECTION_STATE_CHANGED------4-----");
                        System.out.println(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
                        int x = intent.getIntExtra(BluetoothAdapter.EXTRA_CONNECTION_STATE, 99);

                        if (x == BluetoothAdapter.STATE_CONNECTED) {
                            System.out.println("--------------2222222222222222------4-----");
                            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                            System.out.println(device.getAddress());
                            System.out.println(device.getName());
                            System.out.println(device.getBondState());
                            System.out.println(device.getBluetoothClass());
                        }

                        break;
                    case BluetoothAdapter.ACTION_STATE_CHANGED:
                        //Bluetooth has been turned on or off.
                        System.out.println("---------ACTION_STATE_CHANGED---------------------------------------------");
                        System.out.println(intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 22));
                        break;
                    case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                        System.out.println("--------本地蓝牙适配器已完成设备发现过程。----------------");
                        break;
                    case BluetoothAdapter.ACTION_DISCOVERY_STARTED:
                        System.out.println("-------------本地蓝牙适配器已启动远程设备发现过程。------------");
                        break;
                    case BluetoothDevice.ACTION_FOUND:
                        System.out.println("---------------------发现远程设备。---------------------");
                        System.out.println(intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE));
                        BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                        System.out.println(bluetoothDevice.getName());
                        System.out.println(bluetoothDevice.getAddress());
                        break;
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        IntentFilter intentFilter1 = new IntentFilter(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
        IntentFilter intentFilter2 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        IntentFilter intentFilter3 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        IntentFilter intentFilter4 = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(broadcastReceiver, intentFilter);
        registerReceiver(broadcastReceiver, intentFilter1);
        registerReceiver(broadcastReceiver, intentFilter2);
        registerReceiver(broadcastReceiver, intentFilter3);
        registerReceiver(broadcastReceiver, intentFilter4);
    }


}
