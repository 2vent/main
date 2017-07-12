package com.example.win.a2vent;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by EUNJAESHIN on 2017-07-10.
 */

public class event_user_main extends AppCompatActivity {
    private static String TAG = "JSON으로 데이터 가져오기";

    Context mContext;
    RecyclerView.Adapter rAdapter1,rAdapter2;
    RecyclerView rView1,rView2;
    String mJsonString;
    ArrayList category_all,category_fashion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_user_main);
        set_TabHost(this);

        mContext = getApplicationContext();

        rView1 = (RecyclerView) findViewById(R.id.rview_content1);
        rView1.setHasFixedSize(true);
        rView2 = (RecyclerView) findViewById(R.id.rview_content2);
        rView2.setHasFixedSize(true);

        category_all = new ArrayList<>();
        category_fashion = new ArrayList<>();

//        category_all.add(new event_user_item((R.drawable.events_medium), "ㅎㅇ"));
//        category_all.add(new event_user_item((R.drawable.events_medium), "ㅎㅇ2"));
//        category_all.add(new event_user_item((R.drawable.events_medium), "ㅎㅇ3"));
//        category_fashion.add(new event_user_item((R.drawable.events_medium), "패션"));

        rAdapter1 = new event_user_adapter(category_all, mContext);
        rAdapter2 = new event_user_adapter(category_fashion, mContext);
//        rView1.setAdapter(rAdapter1);
//        rView2.setAdapter(rAdapter2);
    }

    public void onClick_Accountinfo(View v) {
        Toast.makeText(event_user_main.this, "이거 누르면 계정정보로", Toast.LENGTH_SHORT).show();
    }

    public void onClick_goMap(View v) {
        Toast.makeText(event_user_main.this, "이거 누르면 지도뷰로", Toast.LENGTH_SHORT).show();
    }

    public void set_TabHost(Activity a) {
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost_usermain);
        tabHost.setup();

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("Tab Spec 1");
        tabSpec1.setContent(R.id.content1);
        tabSpec1.setIndicator("전체");
        tabHost.addTab(tabSpec1);

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("Tab Spec 2");
        tabSpec2.setContent(R.id.content2);
        tabSpec2.setIndicator("패션");
        tabHost.addTab(tabSpec2);

        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("Tab Spec 3");
        tabSpec3.setContent(R.id.content3);
        tabSpec3.setIndicator("외식");
        tabHost.addTab(tabSpec3);

        TabHost.TabSpec tabSpec4 = tabHost.newTabSpec("Tab Spec 4");
        tabSpec4.setContent(R.id.content4);
        tabSpec4.setIndicator("뷰티");
        tabHost.addTab(tabSpec4);

        TabHost.TabSpec tabSpec5 = tabHost.newTabSpec("Tab Spec 5");
        tabSpec5.setContent(R.id.content5);
        tabSpec5.setIndicator("문화");
        tabHost.addTab(tabSpec5);
    }
/*
    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(event_user_main.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "response  - " + result);

            if (result == null) {

                mTextViewResult.setText(errorString);
            } else {

                mJsonString = result;
                showResult();
            }
        }

        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString().trim();

            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }

    private void showResult() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject item = jsonArray.getJSONObject(i);

                String id = item.getString(TAG_ID);
                String name = item.getString(TAG_NAME);
                String address = item.getString(TAG_BIRTH);

                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put(TAG_ID, id);
                hashMap.put(TAG_NAME, name);
                hashMap.put(TAG_BIRTH, address);

                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    ListActivity.this, mArrayList, R.layout.item_list,
                    new String[]{TAG_ID, TAG_NAME, TAG_BIRTH},
                    new int[]{R.id.textView_list_id, R.id.textView_list_name, R.id.textView_list_birth}
            );


            mlistView.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }

*/
}
