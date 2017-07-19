package com.example.win.a2vent;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.win.a2vent.databinding.UserEventMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by EUNJAESHIN on 2017-07-10.
 * 사용자 메인 화면
 */

public class user_Event_Main extends AppCompatActivity {
    private static String TAG = "JSON으로 데이터 가져오기";
    private static final String TAG_JSON = "Event";
    private static final String TAG_NAME = "event_name";
    private static final String TAG_URI = "event_URI";
    private static final String TAG_PRICE = "event_price";
    private static final String TAG_DISPRICE = "event_dis_price";
    private static final String TAG_STARTDAY = "event_startday";
    private static final String TAG_ENDDAY = "event_endday";

    UserEventMainBinding binding_UserMain;
    static RecyclerView mRecyclerView; // 어댑터에서 쓸 인스턴스
    Context mContext;
    RecyclerView.Adapter rAdapter1;
    ArrayList category_all;
    String mJsonString;
    getEventDB getEventDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding_UserMain = DataBindingUtil.setContentView(this, R.layout.user_event_main);
        set_TabHost(this);

        mContext = getApplicationContext();
        mRecyclerView = binding_UserMain.rviewContent1;

        getEventDB = new getEventDB();
        getEventDB.execute("http://192.168.0.106/EventApp/2ventGetEvent.php");
    }

    public void onClick_Accountinfo(View v) {
        Toast.makeText(user_Event_Main.this, "이거 누르면 계정정보로", Toast.LENGTH_SHORT).show();
    }

    public void onClick_goMap(View v) {
        Toast.makeText(user_Event_Main.this, "이거 누르면 지도뷰로", Toast.LENGTH_SHORT).show();
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

    private class getEventDB extends AsyncTask<String, Void, String> { // 이벤트 받아오기
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(user_Event_Main.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);

            if (result == null) {

            } else {
                mJsonString = result;
                addItemInCategory();
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
                Log.d(TAG, "getEventDB Error : ", e);
                errorString = e.toString();

                return null;
            }
        }

    }

    private void addItemInCategory() {
        category_all = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject item = jsonArray.getJSONObject(i);

                String event_name = item.getString(TAG_NAME);
                String event_URI = item.getString(TAG_URI);
                String event_price = item.getString(TAG_PRICE);
                String event_dis_price = item.getString(TAG_DISPRICE);
                String event_startday = item.getString(TAG_STARTDAY);
                String event_endday = item.getString(TAG_ENDDAY);


                category_all.add(new user_Event_Item(event_name, event_URI,
                        event_price, event_dis_price, event_startday, event_endday));
            }

            rAdapter1 = new user_Event_Adapter(category_all, mContext);
            binding_UserMain.rviewContent1.setAdapter(rAdapter1);
            binding_UserMain.rviewContent1.setHasFixedSize(true);

            rAdapter1.notifyDataSetChanged();

        } catch (JSONException e) {
            Log.d(TAG, "addItemInCategory Error : ", e);
        }
    }

    @Override
    protected void onDestroy() {
        if (getEventDB != null) {
            getEventDB.cancel(true);
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (getEventDB != null) {
            getEventDB.cancel(true);
        }
        super.onPause();
    }

}
