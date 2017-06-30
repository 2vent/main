package com.example.win.a2vent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class user_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);
        TabHost mTabHost = (TabHost)findViewById(R.id.info_tabhost);
        mTabHost.setup();
        TabHost.TabSpec tab1 = mTabHost.newTabSpec("1").setContent(R.id.tab1).setIndicator("힝");
        TabHost.TabSpec tab2 = mTabHost.newTabSpec("2").setContent(R.id.tab2).setIndicator("흥");

        mTabHost.addTab(tab1);
        mTabHost.addTab(tab2);
    }
}
