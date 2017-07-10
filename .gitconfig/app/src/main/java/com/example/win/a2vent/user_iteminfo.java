package com.example.win.a2vent;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017-07-03.
 */

public class user_iteminfo extends AppCompatActivity {
    private TextView tv_juso;
    private TextView tv_discount;
    private TextView tv_money;
    private TextView tv_date;
    private ImageView iv_image;
    private Button bt_do;
    private Button bt_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        tv_juso=(TextView)findViewById(R.id.info_juso);
        tv_discount=(TextView)findViewById(R.id.info_discount);
        tv_money=(TextView)findViewById(R.id.info_money);
        tv_date=(TextView)findViewById(R.id.info_date);
        iv_image=(ImageView) findViewById(R.id.info_image);
        bt_do=(Button)findViewById(R.id.info_do);
        bt_back=(Button)findViewById(R.id.info_back);

        ButtonListener buttonListener=new ButtonListener();

        bt_back.setOnClickListener(buttonListener);
        bt_do.setOnClickListener(buttonListener);

    }
    class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.info_back:
                    finish();
                    break;
                case R.id.info_do:
                    Snackbar.make(v.getRootView(),"응모,결제",2000).show();
                    break;
            }
        }
    }
}
