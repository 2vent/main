package com.example.win.a2vent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.MapView;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapView;

/**
 * Created by Administrator on 2017-07-03.
 */

public class user_map extends NMapActivity{

    private NMapView mMapView;// 지도 화면 View
    private final String CLIENT_ID = "GLaeetDFAq3tfl_1J50O";// 애플리케이션 클라이언트 아이디 값
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);


        mapView=(MapView) findViewById(R.id.mapView);

        mMapView=new NMapView(this);
        mapView.addView(mMapView);
        mMapView.setClientId(CLIENT_ID); // 클라이언트 아이디 값 설정
        mMapView.setClickable(true);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.requestFocus();





    }

}
