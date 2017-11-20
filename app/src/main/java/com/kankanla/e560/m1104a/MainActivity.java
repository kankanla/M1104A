package com.kankanla.e560.m1104a;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kankanla.e560.m1104a.work.Bluetooth_status;
import com.kankanla.e560.m1104a.work.wifi_info;


public class MainActivity extends AppCompatActivity {
    private String TAG = "--------------MainActivity--------------------------";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent1 = new Intent(this, Bluetooth_status.class);
        startService(intent1);

        Intent intent = new Intent(this, wifi_info.class);
        startService(intent);

    }
}
