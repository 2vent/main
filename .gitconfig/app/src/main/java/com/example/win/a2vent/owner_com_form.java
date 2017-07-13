package com.example.win.a2vent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class owner_com_form extends AppCompatActivity implements View.OnClickListener{

    TextView v_com_name;
    TextView v_com_addr;
    RadioButton v_com_radio_culture;
    RadioButton v_com_radio_food;
    RadioButton v_com_radio_beauty;
    RadioButton v_com_radio_fashion;
    RadioButton v_com_radio_travel;
    Spinner v_com_manager;
    TextView v_com_number;
    URI v_com_URI;
    ImageView v_com_iv;
    Button v_com_button;
    RadioGroup v_com_radio;


    String com_name;
    String com_addr;
    String com_category;
    String com_manager;
    String com_URI;
    //유저목록 불러와야됨
    //이미지 삽입 해야됨
    String ID;
    String com_number;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_owner_com_form);

        v_com_name=(TextView)findViewById(R.id.com_form_name);
        v_com_addr=(TextView)findViewById(R.id.com_form_addr);
        v_com_radio_culture=(RadioButton)findViewById(R.id.com_form_radio_culture);
        v_com_radio_food = (RadioButton)findViewById(R.id.com_form_radio_food);
        v_com_radio_beauty=(RadioButton)findViewById(R.id.com_form_radio_beauty);
        v_com_radio_fashion=(RadioButton)findViewById(R.id.com_form_radio_fashion);
        v_com_radio_travel=(RadioButton)findViewById(R.id.com_form_radio_travel);
        v_com_manager=(Spinner)findViewById(R.id.com_form_manager);
        v_com_number=(TextView)findViewById(R.id.com_form_number);
        v_com_radio=(RadioGroup)findViewById(R.id.com_form_radio);

//        v_com_URI
        v_com_iv=(ImageView)findViewById(R.id.com_form_iv);
        v_com_button = (Button) findViewById(R.id.com_button);

        v_com_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.com_form_radio_culture:
                        com_category="0";
                        break;
                    case R.id.com_form_radio_food:
                        com_category="1";
                        break;
                    case R.id.com_form_radio_beauty:
                        com_category="2";
                        break;
                    case R.id.com_form_radio_fashion:
                        com_category="3";
                        break;
                    case R.id.com_form_radio_travel:
                        com_category="4";
                        break;
                }
            }
        });

        com_URI="temp001";
        com_manager="1";





    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.com_button :
                com_name=v_com_name.getText().toString();
                com_addr=v_com_addr.getText().toString();
//                com_category
//                com_manager
//                com_URI
//                com_ID
                ID="1";
                com_number=v_com_number.getText().toString();
                Toast.makeText(this,com_number+" : "+com_name+" : "+com_addr+" : "+com_category+" : "+com_manager+" : "+com_URI+" : "+ID,Toast.LENGTH_LONG).show();
//                Toast.makeText(this,v_com_radio_culture.isChecked(),Toast.LENGTH_LONG).show();

                InsertData_com com_Task=new InsertData_com();
                com_Task.execute(com_number,com_name,com_addr,com_category,com_manager,com_URI,ID);
                break;

        }

    }

    class InsertData_com extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(owner_com_form.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected String doInBackground(String... params) {

            String com_number = (String) params[0];
            String com_name = (String) params[1];
            String com_addr = (String) params[2];
            String com_category = (String) params[3];
            String com_manager = (String) params[4];
            String com_URI = (String) params[5];
            String id = (String) params[6];

            String serverURL = "http://192.168.0.106/eventApp/2ventAddstore.php";
            String postParameters = "&com_number=" + com_number + "&com_name=" + com_name + "&com_addr=" + com_addr +
                    "&com_category=" + com_category + "&com_manager=" + com_manager + "&com_URI=" + com_URI + "&id=" + id ;

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

            Log.d("DB", "POST response  - " + result);
        }

    }
}
