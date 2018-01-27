package com.kankanla.e560.m1104a;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.kankanla.e560.m1104a.work.Bluetooth_status;
import com.kankanla.e560.m1104a.work.wifi_info;


public class MainActivity extends AppCompatActivity {
    private String TAG = "--------------MainActivity------------------------";
    private ImageButton imageButton, imageButton1;
    private Button button;
    private Point Display_point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app_seting();
        setImageButton();
        setImageButton1();
        setBt();
    }


    protected void app_seting() {
        setTitle(R.string.app_name2);
        Display_point = new Point();
        getWindowManager().getDefaultDisplay().getSize(Display_point);
    }

    protected void setImageButton() {
        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), wifi_info.class);
                startService(intent);
                Intent intent1 = new Intent(getApplicationContext(), Bluetooth_status.class);
                startService(intent1);
            }
        });
    }

    protected void setImageButton1() {
        imageButton1 = findViewById(R.id.imageButton1);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), global_ip.class);
                startActivity(intent);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    protected void setBt() {
        button = findViewById(R.id.button3);
//        button.setBackground(getResources().getDrawable(R.drawable.text2784));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
