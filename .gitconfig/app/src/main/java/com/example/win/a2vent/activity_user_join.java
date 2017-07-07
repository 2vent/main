package com.example.win.a2vent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class activity_user_join extends AppCompatActivity {

    private EditText et_joinId, et_joinPw, et_joinName, et_joinAddr, et_joinBirth, et_joinPhone;
    private EditText et_joinAccnum;
    private RadioButton rBt_sex0, rBt_sex1, rBt_user0, rBt_user1, rBt_user2;
    private RadioGroup radioGroup_sex, radioGroup_user;
    String sex,user_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_join);

        et_joinId = (EditText) findViewById(R.id.eText_join_id);
        et_joinPw = (EditText) findViewById(R.id.eText_join_pw);
        et_joinName = (EditText) findViewById(R.id.eText_join_name);
        et_joinAddr = (EditText) findViewById(R.id.eText_join_addr);
        et_joinBirth = (EditText) findViewById(R.id.eText_join_birth);
        et_joinPhone = (EditText) findViewById(R.id.eText_join_phone);
        et_joinAccnum = (EditText) findViewById(R.id.eText_join_accountnumber);
        rBt_sex0 = (RadioButton) findViewById(R.id.rBt_sex0);
        rBt_sex1 = (RadioButton) findViewById(R.id.rBt_sex1);
        rBt_user0 = (RadioButton) findViewById(R.id.rBt_user0);
        rBt_user1 = (RadioButton) findViewById(R.id.rBt_user1);
        rBt_user2 = (RadioButton) findViewById(R.id.rBt_user2);
        radioGroup_sex = (RadioGroup) findViewById(R.id.rGroup_join1);
        radioGroup_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rBt_sex0:
                        Toast.makeText(activity_user_join.this,
                                rBt_sex0.getText().toString(), Toast.LENGTH_SHORT).show();
                        sex = "0";
                        break;
                    case R.id.rBt_sex1:
                        Toast.makeText(activity_user_join.this,
                                rBt_sex1.getText().toString(), Toast.LENGTH_SHORT).show();
                        sex = "1";
                        break;
                }
            }
        });
        radioGroup_user = (RadioGroup) findViewById(R.id.rGroup_join2);
        radioGroup_user.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rBt_user0:
                        Toast.makeText(activity_user_join.this,
                                rBt_user0.getText().toString(), Toast.LENGTH_SHORT).show();
                        user_type = "1";
                        break;
                    case R.id.rBt_user1:
                        Toast.makeText(activity_user_join.this,
                                rBt_user1.getText().toString(), Toast.LENGTH_SHORT).show();
                        user_type = "2";
                        break;
                    case R.id.rBt_user2:
                        Toast.makeText(activity_user_join.this,
                                rBt_user2.getText().toString(), Toast.LENGTH_SHORT).show();
                        user_type = "2";
                        break;
                }
            }
        });

    }

    public void onClick_joinOK(View view) {
        String id = et_joinId.getText().toString();
        String pw = et_joinPw.getText().toString();
        String name = et_joinName.getText().toString();
        String addr = et_joinAddr.getText().toString();
        String birth = et_joinBirth.getText().toString();
        String phone = et_joinPhone.getText().toString();
        String accountnumber = et_joinAccnum.getText().toString();

        InsertData joinTask = new InsertData();
        joinTask.execute(id,pw,name,addr,birth,sex,phone,user_type,accountnumber);
    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(activity_user_join.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected String doInBackground(String... params) {

            String id = (String) params[0];
            String pw = (String) params[1];
            String name = (String) params[2];
            String addr = (String) params[3];
            String birth = (String) params[4];
            String sex = (String) params[5];
            String phone = (String) params[6];
            String user_type = (String) params[7];
            String account_number = (String) params[8];

            String serverURL = "http://192.168.0.106/eventApp/2ventRegister.php";
            String postParameters = "&id=" + id + "&pw=" + pw + "&name=" + name +
                    "&addr=" + addr + "&birthday=" + birth + "&sex=" + sex + "&phone=" + phone +
                    "&user_type=" + user_type + "&account_number=" + account_number;

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
                Log.d("DB", "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Intent intent_Joindone = new Intent(activity_user_join.this, activity_user_main.class);
            startActivity(intent_Joindone);
            Log.d("DB", "POST response  - " + result);
        }

    }

}

