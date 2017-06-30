package com.example.win.a2vent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class user_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
    }

    public void onClick_login(View view) {

    }

    public void onClick_join(View view) {
        Intent intent_join = new Intent(user_main.this, user_join.class);
        startActivity(intent_join);
    }
}
