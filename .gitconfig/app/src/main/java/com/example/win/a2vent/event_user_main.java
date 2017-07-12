package com.example.win.a2vent;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by EUNJAESHIN on 2017-07-10.
 */

public class event_user_main extends AppCompatActivity {

    Context mContext;
    RecyclerView.Adapter rAdapter1,rAdapter2;
    RecyclerView rView1,rView2;

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

        ArrayList category_all = new ArrayList<>();
        ArrayList category_fashion = new ArrayList<>();

        category_all.add(new event_user_item((R.drawable.events_medium), "ㅎㅇ"));
        category_all.add(new event_user_item((R.drawable.events_medium), "ㅎㅇ2"));
        category_all.add(new event_user_item((R.drawable.events_medium), "ㅎㅇ3"));
        category_fashion.add(new event_user_item((R.drawable.events_medium), "패션"));

        rAdapter1 = new event_user_adapter(category_all, mContext);
        rAdapter2 = new event_user_adapter(category_fashion, mContext);
        rView1.setAdapter(rAdapter1);
        rView2.setAdapter(rAdapter2);
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
        tabSpec2.setIndicator("문화");
        tabHost.addTab(tabSpec2);

        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("Tab Spec 3");
        tabSpec3.setContent(R.id.content3);
        tabSpec3.setIndicator("외식");
        tabHost.addTab(tabSpec3);

        TabHost.TabSpec tabSpec4 = tabHost.newTabSpec("Tab Spec 4");
        tabSpec4.setContent(R.id.content4);
        tabSpec4.setIndicator("패션");
        tabHost.addTab(tabSpec4);

        TabHost.TabSpec tabSpec5 = tabHost.newTabSpec("Tab Spec 5");
        tabSpec5.setContent(R.id.content5);
        tabSpec5.setIndicator("뷰티");
        tabHost.addTab(tabSpec5);
    }


}
