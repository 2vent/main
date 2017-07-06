package com.example.win.a2vent;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class user_main extends AppCompatActivity {

    EditText et_id, et_pw;
    String sId, sPw;
    private long backKeyPressedTime = 0;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        toast = Toast.makeText(user_main.this,
                "'뒤로' 버튼을 한번 더 누르시면 종료됩니다", Toast.LENGTH_SHORT);
        et_id = (EditText) findViewById(R.id.eText_login_id);
        et_pw = (EditText) findViewById(R.id.eText_login_pw);
    }

    public void onClick_login(View view) {

        try {
            sId = et_id.getText().toString();
            sPw = et_pw.getText().toString();
        } catch (NullPointerException e) {
            Log.e("err", e.getMessage());
        }

        loginDB login_DB = new loginDB();
        login_DB.execute(sId, sPw);

        //Toast.makeText(user_main.this, "DB가 없다구욧!", Toast.LENGTH_SHORT).show();
    }

    public void onClick_join(View view) {
        Intent intent_join = new Intent(user_main.this, user_join.class);
        startActivity(intent_join);
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast.show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            toast.cancel();
            finish();
        }
    }

    class loginDB extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(user_main.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected String doInBackground(String... params) {

            String id = (String) params[0];
            String pw = (String) params[1];

            String serverURL = "http://192.168.0.106/eventApp/2ventLogin.php";
            String postParameters = "&id=" + id + "&pw=" + pw;

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                //httpURLConnection.setRequestProperty("content-type", "application/json");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d("DB", "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                bufferedReader.close();

                return sb.toString();

            } catch (Exception e) {
                Log.d("DB", "LoginDB: Error ", e);

                return new String("Error: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            if (result.equals("0")) {
                Toast.makeText(user_main.this, "패스워드 불일치", Toast.LENGTH_SHORT).show();
            } else if (result.equals("1")){
                Intent intent_Logindone = new Intent(user_main.this, event_user_main.class);
                startActivity(intent_Logindone);
            } else {
                Toast.makeText(user_main.this, "ID를 찾을 수 없습니다", Toast.LENGTH_SHORT).show();
            }

            Log.d("DB", "POST response  - " + result);
        }

    }

}
