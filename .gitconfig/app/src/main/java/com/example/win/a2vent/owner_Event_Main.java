package com.example.win.a2vent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by win on 2017-07-06.
 */

public class owner_Event_Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private int nType=1;
    Context mContext;
    RecyclerView recyclerView;
    owner_Event_Adapter Adapter;
    FloatingActionButton fab;
    owner_Addstore_Adapter Adapter_com;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<owner_Event_Item> event_list = new ArrayList<owner_Event_Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_event_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.owner_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nType == 1) {
                    Intent intent_eventform = new Intent(getBaseContext(), owner_Event_Addevent.class);
                    startActivity(intent_eventform);
                } else {
                    Intent intent_addstore = new Intent(getBaseContext(), owner_AddStore.class);
                    startActivity(intent_addstore);
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "문", "ad"));
        event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "동", "ad"));
        event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "이이이이이이", "ad"));
        event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "상", "ad"));
        event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "사", "ad"));

        recyclerView = (RecyclerView) findViewById(R.id.event_owner_main);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Adapter = new owner_Event_Adapter(event_list);
        recyclerView.setAdapter(Adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_company) {
            nType=0;
            ArrayList<owner_Addstore_Item> items = new ArrayList<owner_Addstore_Item>();
            items.add(new owner_Addstore_Item("1", "SKT", "경북 구미시 우리집", "10101", "kjd99002", "smt001", "s"));
            items.add(new owner_Addstore_Item("2", "LG", "경북 구미시 우리집", "10101", "kjd99001", "smt001", "s"));
            items.add(new owner_Addstore_Item("3", "지앤케이", "대구 동대구역 앞", "10101", "kjd99003", "smt001", "s"));
            items.add(new owner_Addstore_Item("4", "모름", "경북 구미시 우리집", "10101", "kjd99004", "smt001", "s"));

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            Adapter_com = new owner_Addstore_Adapter(items);
            recyclerView.setAdapter(Adapter_com);

        } else if (id == R.id.on_event) {
            nType=1;
            event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "문", "ad"));
            event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "동", "ad"));
            event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "이이이이이이", "ad"));
            event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "상", "ad"));
            event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "사", "ad"));

            recyclerView = (RecyclerView) findViewById(R.id.event_owner_main);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            Adapter = new owner_Event_Adapter(event_list);
            recyclerView.setAdapter(Adapter);

        } else if (id == R.id.temp_event) {
            nType=1;
            event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "문", "ad"));
            event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "동", "ad"));
            event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "이이이이이이", "ad"));
            event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "상", "ad"));
            event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "사", "ad"));

            recyclerView = (RecyclerView) findViewById(R.id.event_owner_main);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            Adapter = new owner_Event_Adapter(event_list);
            recyclerView.setAdapter(Adapter);

        } else if (id == R.id.end_event) {
            nType=1;
            event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "문", "ad"));
            event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "동", "ad"));
            event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "이이이이이이", "ad"));
            event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "상", "ad"));
            event_list.add(new owner_Event_Item(1, 1, 1, "fiwj", 5000, 4000, 4, "20170707", "20170707", "5018", "5018", 0, 1, 59, 59, 1, "대구", "3", "던파", "사", "ad"));

            recyclerView = (RecyclerView) findViewById(R.id.event_owner_main);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            Adapter = new owner_Event_Adapter(event_list);
            recyclerView.setAdapter(Adapter);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
