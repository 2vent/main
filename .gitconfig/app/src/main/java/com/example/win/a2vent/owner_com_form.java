package com.example.win.a2vent;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.URI;

public class owner_com_form extends AppCompatActivity {

    TextView v_com_name;
    TextView v_com_addr;
    RadioButton v_com_radio_culture;
    RadioButton v_com_radio_food;
    RadioButton v_com_radio_butty;
    RadioButton v_com_radio_fashion;
    RadioButton v_com_radio_travel;
    Spinner v_com_manager;
    TextView v_com_number;
    URI v_com_URI;
    ImageView v_com_iv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_owner_com_form);

        v_com_name=(TextView)findViewById(R.id.com_form_name);
        v_com_addr=(TextView)findViewById(R.id.com_form_addr);
        v_com_radio_culture=(RadioButton)findViewById(R.id.com_form_radio_culture);
        v_com_radio_food = (RadioButton)findViewById(R.id.com_form_radio_food);
        v_com_radio_butty=(RadioButton)findViewById(R.id.com_form_radio_butty);
        v_com_radio_fashion=(RadioButton)findViewById(R.id.com_form_radio_fashion);
        v_com_radio_travel=(RadioButton)findViewById(R.id.com_form_radio_travel);
        v_com_manager=(Spinner)findViewById(R.id.com_form_manager);
        v_com_number=(TextView)findViewById(R.id.com_form_number);
//        v_com_URI
//        v_com_iv;





    }
}
