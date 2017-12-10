package com.kankanla.e560.m1104a;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kankanla.e560.m1104a.work.Global_IP;

import org.json.JSONException;
import org.json.JSONObject;

public class global_ip extends AppCompatActivity {
    protected TextView gipaddress;
    protected TextView isp;
    protected TextView as;
    protected TextView org;
//    protected TextView city;
//    protected TextView zip;
//    protected TextView regionName;
//    protected TextView timezone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        gipaddress = findViewById(R.id.gipaddress);
        isp = findViewById(R.id.isp);
        as = findViewById(R.id.as);
        org = findViewById(R.id.org);

    }

    @Override
    protected void onStart() {
        super.onStart();
        show_ip();
    }

    protected void show_ip() {
        Global_IP global_ip = new Global_IP(getApplicationContext(), new Global_IP.CallBack() {
            @Override
            public void Show_Json(JSONObject jsonObject) {
                try {
                    System.out.println(jsonObject.toString());
                    System.out.println(jsonObject.getString("org"));
                    gipaddress.setText(jsonObject.getString("query"));
                    isp.setText(jsonObject.getString("isp"));
                    as.setText(jsonObject.getString("as"));
                    org.setText(jsonObject.getString("org"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        global_ip.t1();
    }

//        @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout);
//        setTitle("Global IP address");
//        gipaddress = findViewById(R.id.gipaddress);
//        country = findViewById(R.id.countryj);
//        city = findViewById(R.id.cityj);
//        zip = findViewById(R.id.zipj);
//        regionName = findViewById(R.id.regionNamej);
//        timezone = findViewById(R.id.timezonej);
//        isp = findViewById(R.id.ispj);
//        org = findViewById(R.id.orgj);
//        as = findViewById(R.id.asj);
//        t1();
//    }
//
//    protected void t1() {
//        Global_IP global_ip = new Global_IP(this, new Global_IP.CallBack() {
//            @Override
//            public void Show_Json(JSONObject jsonObject) {
//                try {
//                    System.out.println(jsonObject);
//                    gipaddress.setText(jsonObject.getString("query"));
//                    gipaddress.setTextSize(45);
//                    country.setText(jsonObject.getString("country"));
//                    city.setText(jsonObject.getString("city"));
//                    zip.setText(jsonObject.getString("zip"));
//                    regionName.setText(jsonObject.getString("regionName"));
//                    timezone.setText(jsonObject.getString("timezone"));
//                    isp.setText(jsonObject.getString("isp"));
//                    org.setText(jsonObject.getString("org"));
//                    as.setText(jsonObject.getString("as"));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        global_ip.t1();
//    }
}
