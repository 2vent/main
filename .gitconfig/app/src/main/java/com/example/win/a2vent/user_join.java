package com.example.win.a2vent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class user_join extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_join);
    }

    public void onClick_joinOK(View view) {
        Toast.makeText(this, "DB없어서 가입 안됨 ㅅㄱ", Toast.LENGTH_SHORT).show();

        Intent intent_joinOK = new Intent(user_join.this, user_main.class);
        startActivity(intent_joinOK);
    }
}
