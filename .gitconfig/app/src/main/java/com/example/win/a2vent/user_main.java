package com.example.win.a2vent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.view.View;



public class user_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_form);
//        TabHost mTabHost = (TabHost)findViewById(R.id.info_tabhost);
//        mTabHost.setup();
//        TabHost.TabSpec tab1 = mTabHost.newTabSpec("1").setContent(R.id.tab1).setIndicator("힝");
//        TabHost.TabSpec tab2 = mTabHost.newTabSpec("2").setContent(R.id.tab2).setIndicator("흥");
//
//        mTabHost.addTab(tab1);
//        mTabHost.addTab(tab2);
    }

    public void onClick_login(View view) {

    }

    public void onClick_join(View view) {
//        Intent intent_join = new Intent(user_main.this, user_join.class);
//        startActivity(intent_join);
    }
}
