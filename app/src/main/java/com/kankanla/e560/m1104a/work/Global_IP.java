package com.kankanla.e560.m1104a.work;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by E560 on 2017/11/30.
 */

public class Global_IP {
    public static String GIP_API_URL = "http://ip-api.com/json";
    protected Context context;
    protected JSONObject jsonObject;
    private CallBack callBack;

    public Global_IP(Context context, CallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        jsonObject = new JSONObject();
    }

    public interface CallBack {
        void Show_Json(JSONObject jsonObject);
    }

    public void t1() {
        new GIP_info().execute(GIP_API_URL);
    }


    private class GIP_info extends AsyncTask<String, Integer, JSONObject> {

        private JSONObject jsonObject;
        private InputStream inputStream;
        private ByteArrayOutputStream byteArrayOutputStream;

        public GIP_info() {
            jsonObject = new JSONObject();
            byteArrayOutputStream = new ByteArrayOutputStream();
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(10 * 1000);
                httpURLConnection.setConnectTimeout(10 * 1000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }

                if (inputStream != null) {
                    byte[] buffer = new byte[32];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        byteArrayOutputStream.write(buffer, 0, len);
                    }
                }

                if (byteArrayOutputStream != null) {
                    try {
                        jsonObject = new JSONObject(byteArrayOutputStream.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (MalformedURLException e) {
                //URL
                e.printStackTrace();
                try {
                    jsonObject.put("error", "URL Exception-----------------------------------------------");
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException e) {
                //HttpURLConnection
                e.printStackTrace();
                try {
                    jsonObject.put("error", "HttpURLConnection Exception-----------------------------------");
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {

            callBack.Show_Json(jsonObject);
            super.onPostExecute(jsonObject);
        }
    }

}
