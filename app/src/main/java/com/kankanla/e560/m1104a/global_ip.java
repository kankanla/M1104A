package com.kankanla.e560.m1104a;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kankanla.e560.m1104a.work.Global_IP;

import org.json.JSONException;
import org.json.JSONObject;

public class global_ip extends AppCompatActivity {
    protected TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_ip);
        textView = findViewById(R.id.iptextview);
        t1();
    }

    protected void t1(){
        Global_IP global_ip = new Global_IP(this, new Global_IP.CallBack() {
            @Override
            public void Show_Json(JSONObject jsonObject) {
                try {
                    System.out.println(jsonObject);
                    System.out.println("-------------------------------------------------");
                    System.out.println("-------------------------------------------------");
                    textView.setText(jsonObject.getString("query"));
                    System.out.println("-------------------------------------------------");
                    System.out.println("-------------------------------------------------");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        global_ip.t1();

    }
}
