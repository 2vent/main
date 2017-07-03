package com.example.win.a2vent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class user_main extends AppCompatActivity {

    private long backKeyPressedTime = 0;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        toast = Toast.makeText(user_main.this,
                "'뒤로' 버튼을 한번 더 누르시면 종료됩니다", Toast.LENGTH_SHORT);

    }

    public void onClick_login(View view) {
        Toast.makeText(user_main.this, "DB가 없다구욧!", Toast.LENGTH_SHORT).show();
    }

    public void onClick_join(View view) {
        Intent intent_join = new Intent(user_main.this, user_join.class);
        startActivity(intent_join);
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast.show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            toast.cancel();
            finish();
        }
    }
}
