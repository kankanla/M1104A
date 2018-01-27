package com.kankanla.e560.m1104a;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.kankanla.e560.m1104a.work.Global_IP;

import org.json.JSONException;
import org.json.JSONObject;

public class global_ip extends AppCompatActivity {
    protected TextView gipaddress;
    protected TextView isp;
    protected TextView as;
    protected TextView org;
    protected TextView zip;
    protected TextView country;
    protected TextView city;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        gipaddress = findViewById(R.id.gipaddress);
        isp = findViewById(R.id.isp);
        as = findViewById(R.id.as);
        org = findViewById(R.id.org);
        zip = findViewById(R.id.zip);
        country = findViewById(R.id.country);
        city = findViewById(R.id.city);
    }

    @Override
    protected void onStart() {
        super.onStart();
        admo();
        setTitle(getString(R.string.user_ip));
        show_ip();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Saher_IP");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("9999", String.valueOf(item.getTitle()));
        return super.onOptionsItemSelected(item);
    }

    protected void show_ip() {
        Global_IP global_ip = new Global_IP(getApplicationContext(), new Global_IP.CallBack() {
            @Override
            public void Show_Json(JSONObject jsonObject) {
                try {
                    System.out.println(jsonObject.toString());
                    gipaddress.setText(jsonObject.getString("query"));
                    gipaddress.setTextSize(45);
                    isp.setText(jsonObject.getString("isp"));
                    as.setText(jsonObject.getString("as"));
                    org.setText(jsonObject.getString("org"));
                    zip.setText(jsonObject.getString("zip"));
                    zip.setText(jsonObject.getString("zip"));
                    country.setText(jsonObject.getString("country"));
                    city.setText(jsonObject.getString("city"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        global_ip.t1();
    }

    protected void admo() {
//        xmlns:ads="http://schemas.android.com/apk/res-auto"
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.layout);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        AdView adView = new AdView(this);
        adView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            adView.setBackground(getDrawable(R.drawable.l142946));
        }
        adView.setLayoutParams(layoutParams);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        viewGroup.addView(adView);
    }
}
