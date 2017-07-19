//package com.example.win.a2vent;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
///**
// * Created by Administrator on 2017-07-03.
// */
//
//public class user_iteminfo extends AppCompatActivity {
//    private TextView tv_name;
//    private TextView tv_discount;
//    private TextView tv_money;
//    private TextView tv_date;
//    private ImageView iv_image;
//    private Button bt_do;
//    private Button bt_back;
//
//    private Bitmap mBitmap;
//
//    private int event_number=0;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.info);
//        Intent intent=getIntent();
//        event_number=intent.getIntExtra("event_number",0);
//
//        tv_name=(TextView)findViewById(R.id.info_name);
//        tv_discount=(TextView)findViewById(R.id.info_discount);
//        tv_money=(TextView)findViewById(R.id.info_money);
//        tv_date=(TextView)findViewById(R.id.info_date);
//        iv_image=(ImageView) findViewById(R.id.info_image);
//        bt_do=(Button)findViewById(R.id.info_do);
//        bt_back=(Button)findViewById(R.id.info_back);
//
//        ButtonListener buttonListener=new ButtonListener();
//
//        bt_back.setOnClickListener(buttonListener);
//        bt_do.setOnClickListener(buttonListener);
//
//
//        getEventInfo getEventInfo=new getEventInfo();
//        getEventInfo.execute(String.valueOf(event_number));
//
//    }
//    @Override
//    protected void onStart(){
//        super.onStart();
//        LoadImage loadImage=new LoadImage();
//        loadImage.execute("http://192.168.0.9/php1.php");
//    }
//
//
//    //버튼 리스너
//    class ButtonListener implements View.OnClickListener{
//        @Override
//        public void onClick(View v){
//            switch (v.getId()){
//                case R.id.info_back:
//                    finish();
//                    break;
//                case R.id.info_do:
//                    Intent intent= new Intent(getBaseContext(),user_payment.class);
//                    startActivity(intent);
//                    break;
//            }
//        }
//    }
//
//    //이벤트 이미지 다운로드
//    private class LoadImage extends AsyncTask<String, String, Bitmap> {
//
//        ProgressDialog pDialog;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            pDialog = new ProgressDialog(user_iteminfo.this);
//            pDialog.setMessage("이미지 로딩중입니다...");
//            pDialog.show();
//        }
//
//        protected Bitmap doInBackground(String... args) {
//            try {
//                mBitmap = BitmapFactory
//                        .decodeStream((InputStream) new URL(args[0])
//                                .getContent());
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return mBitmap;
//        }
//
//        protected void onPostExecute(Bitmap image) {
//
//            if (image != null) {
//                iv_image.setImageBitmap(image);
//                Log.i("이미지 크기",image.getHeight() +"," + image.getWidth());
//                iv_image.setAdjustViewBounds(true);
//                pDialog.dismiss();
//
//            } else {
//                pDialog.dismiss();
//                Toast.makeText(user_iteminfo.this, "이미지가 존재하지 않습니다.",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//
//
//        }
//    }
//
//    //이벤트 상세정보 받아오기
//    private class getEventInfo extends AsyncTask<String, String, String> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//
//        }
//
//        protected String doInBackground(String... args) {
//            StringBuilder sb = null;
//            try {
//
//
//                URL url = new URL("http://192.168.0.106/EventApp/2ventGetEventOnItemInfo.php");
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.setRequestMethod("POST");
//
//                httpURLConnection.setDoOutput(true);
//                httpURLConnection.setDoInput(true);
//
//                OutputStream os = httpURLConnection.getOutputStream();
//
//                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF8"));
//                writer.write("?event_number="+args[0]);
//                writer.flush();
//                writer.close();
//                os.close();
//
//                httpURLConnection.connect();
//
//                BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF8")); //캐릭터셋 설정
//
//                sb = new StringBuilder();
//                String line = null;
//                while ((line = br.readLine()) != null) {
//                    if (sb.length() > 0) {
//                        sb.append("\n");
//                    }
//                    sb.append(line);
//                }
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return sb.toString();
//        }
//
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            Log.i("받은 json ",s);
//            jsonParser(s);
//
//
//        }
//
//    }
//
//    public void jsonParser(String s) {
//        JSONObject jsonObject = null;
//        try {
//            jsonObject = new JSONObject(s);
//
//            JSONArray jsonArray = jsonObject.getJSONArray("eventInfo");
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//
//                JSONObject item = jsonArray.getJSONObject(i);
//
//                String event_name = item.getString("event_name");
//                String event_URI = item.getString("event_URI");
//                String event_price = item.getString("event_price");
//                String event_dis_price = item.getString("event_dis_price");
//                String event_people = item.getString("event_people");
//                String event_startday = item.getString("event_startday");
//                String event_endday = item.getString("event_endday");
//                String event_starttime = item.getString("event_starttime");
//                String event_endtime = item.getString("event_endtime");
//                String event_payment = item.getString("event_payment");
//                String com_number = item.getString("com_number");
//
//                //
//                tv_name.setText(event_name);
//                Log.i("모징",event_name);
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//}