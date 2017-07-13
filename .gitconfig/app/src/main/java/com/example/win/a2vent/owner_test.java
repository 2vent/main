package com.example.win.a2vent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by win on 2017-07-06.
 */
public class owner_test extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    Context mContext;
    RecyclerView recyclerView;
    owner_event_adapter Adapter;
    owner_com_adapter Adapter_com;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<owner_event_item> event_list=new ArrayList<owner_event_item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_owner_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_event_menu);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                    Intent intent =new Intent(getBaseContext(),owner_com_form.class);
//                    Intent intent = new Intent(getBaseContext(), owner_event_form.class);
                    startActivity(intent);



                
                
                
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","문","ad"));
        event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","동","ad"));
        event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","이이이이이이","ad"));
        event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","상","ad"));
        event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","사","ad"));

        recyclerView=(RecyclerView) findViewById(R.id.event_owner_main);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Adapter=new owner_event_adapter(event_list);
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.event_owner_menu, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            Toast.makeText(this,"이거 눌림",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_company) {

            ArrayList<owner_com_item> items=new ArrayList<owner_com_item>();
            items.add(new owner_com_item("1","SKT","경북 구미시 우리집","10101","kjd99002","smt001","s"));
            items.add(new owner_com_item("2","LG","경북 구미시 우리집","10101","kjd99001","smt001","s"));
            items.add(new owner_com_item("3","지앤케이","대구 동대구역 앞","10101","kjd99003","smt001","s"));
            items.add(new owner_com_item("4","모름","경북 구미시 우리집","10101","kjd99004","smt001","s"));

            layoutManager= new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            Adapter_com = new owner_com_adapter(items);
            recyclerView.setAdapter(Adapter_com);
            // Handle the camera action
        } else if (id == R.id.on_event) {
            event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","문","ad"));
            event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","동","ad"));
            event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","이이이이이이","ad"));
            event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","상","ad"));
            event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","사","ad"));

            recyclerView=(RecyclerView) findViewById(R.id.event_owner_main);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            Adapter=new owner_event_adapter(event_list);
            recyclerView.setAdapter(Adapter);

        } else if (id == R.id.temp_event) {

            event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","문","ad"));
            event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","동","ad"));
            event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","이이이이이이","ad"));
            event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","상","ad"));
            event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","사","ad"));

            recyclerView=(RecyclerView) findViewById(R.id.event_owner_main);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            Adapter=new owner_event_adapter(event_list);
            recyclerView.setAdapter(Adapter);

        } else if (id == R.id.end_event) {

            event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","문","ad"));
            event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","동","ad"));
            event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","이이이이이이","ad"));
            event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","상","ad"));
            event_list.add(new owner_event_item(1,1,1,"fiwj",5000,4000,4,"20170707","20170707","5018","5018",0,1,59,59,1,"대구","3","던파","사","ad"));

            recyclerView=(RecyclerView) findViewById(R.id.event_owner_main);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            Adapter=new owner_event_adapter(event_list);
            recyclerView.setAdapter(Adapter);


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
