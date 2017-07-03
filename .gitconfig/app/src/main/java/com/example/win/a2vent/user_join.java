package com.example.win.a2vent;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static com.example.win.a2vent.R.id.rBt_sex0;

public class user_join extends AppCompatActivity {

    RadioButton rBt_sex0, rBt_sex1;
    RadioGroup radioGroup_sex, radioGroup_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_join);

        rBt_sex0 = (RadioButton) findViewById(R.id.rBt_sex0);
        rBt_sex1 = (RadioButton) findViewById(R.id.rBt_sex1);
        radioGroup_sex = (RadioGroup) findViewById(R.id.rGroup_join1);
        radioGroup_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rBt_sex0:
                        Toast.makeText(user_join.this,
                                rBt_sex0.getText().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rBt_sex1:
                        Toast.makeText(user_join.this,
                                rBt_sex1.getText().toString(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        radioGroup_user = (RadioGroup) findViewById(R.id.rGroup_join2);
    }

    public void onClick_joinOK(View view) {
        Toast.makeText(this, "DB없어서 가입 안됨 ㅅㄱ", Toast.LENGTH_SHORT).show();

        Intent intent_joinOK = new Intent(user_join.this, user_main.class);
        startActivity(intent_joinOK);
    }
}
