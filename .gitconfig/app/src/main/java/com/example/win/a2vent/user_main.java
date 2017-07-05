package com.example.win.a2vent;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class user_main extends AppCompatActivity {


    Context mContext;
    RecyclerView recyclerView;
    owner_com_adapter Adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_owner_com_list);
//        TabHost mTabHost = (TabHost)findViewById(R.id.info_tabhost);
//        mTabHost.setup();
//        TabHost.TabSpec tab1 = mTabHost.newTabSpec("1").setContent(R.id.tab1).setIndicator("힝");
//        TabHost.TabSpec tab2 = mTabHost.newTabSpec("2").setContent(R.id.tab2).setIndicator("흥");
//
//        mTabHost.addTab(tab1);
//        mTabHost.addTab(tab2);



        mContext=getApplicationContext();

        recyclerView=(RecyclerView) findViewById(R.id.com_recyclerview);
        recyclerView.setHasFixedSize(true);



        ArrayList<owner_com_item> items=new ArrayList<owner_com_item>();


        items.add(new owner_com_item("1","SKT","경북 구미시 우리집","10101","kjd99002","smt001","s"));
        items.add(new owner_com_item("2","LG","경북 구미시 우리집","10101","kjd99001","smt001","s"));
        items.add(new owner_com_item("3","지앤케이","대구 동대구역 앞","10101","kjd99003","smt001","s"));
        items.add(new owner_com_item("4","모름","경북 구미시 우리집","10101","kjd99004","smt001","s"));


//        layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);

        layoutManager= new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);

        Adapter = new owner_com_adapter(items,mContext);
        recyclerView.setAdapter(Adapter);
        Toast.makeText(this,"여기까진 됨",Toast.LENGTH_LONG).show();




    }

    public void onClick_login(View view) {

    }

    public void onClick_join(View view) {
//        Intent intent_join = new Intent(user_main.this, user_join.class);
//        startActivity(intent_join);
    }
}
