package com.example.win.a2vent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;


/**
 * Created by Administrator on 2017-07-03.
 */

public class user_map extends AppCompatActivity implements MapView.MapViewEventListener,MapView.POIItemEventListener {

  private MapView mapView;

    // CalloutBalloonAdapter 인터페이스 구현
    class CustomCalloutBalloonAdapter implements CalloutBalloonAdapter {
        private final View mCalloutBalloon;

        public CustomCalloutBalloonAdapter() {
            mCalloutBalloon = getLayoutInflater().inflate(R.layout.custom_callout_balloon, null);
        }

        @Override
        public View getCalloutBalloon(MapPOIItem poiItem) {
//            ((ImageView) mCalloutBalloon.findViewById(R.id.badge)).setImageResource(R.drawable.ic_launcher);
            ((TextView) mCalloutBalloon.findViewById(R.id.balloon_title)).setText("제목이얌>_<");
            ((TextView) mCalloutBalloon.findViewById(R.id.balloon_text)).setText("내용이얌>_<");
            return mCalloutBalloon;
        }

        @Override
        public View getPressedCalloutBalloon(MapPOIItem poiItem) {
            return null;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        mapView = new MapView(this);
        mapView.setDaumMapApiKey("65414811e931909ad72eda29477bcf4f");
        RelativeLayout container = (RelativeLayout) findViewById(R.id.map_view);



        mapView.setMapViewEventListener(this);
        mapView.setPOIItemEventListener(this);

        // 구현한 CalloutBalloonAdapter 등록
        mapView.setCalloutBalloonAdapter(new CustomCalloutBalloonAdapter());
        container.addView(mapView);

    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    public void onClick_joinOK(MapView map) {
        MapPoint DEFAULT_MARKER_POINT= MapPoint.mapPointWithGeoCoord(37.4020737, 127.1086766);

        MapPOIItem mDefaultMarker = new MapPOIItem();
        String name = "Default Marker";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(1);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT);

        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);

        mDefaultMarker.setCustomImageResourceId(R.drawable.custom_poi_marker);
        mDefaultMarker.setCustomImageAutoscale(true);
        mDefaultMarker.setCustomImageAnchor(0.5f, 1.0f);

        map.addPOIItem(mDefaultMarker);
        mapView.selectPOIItem(mDefaultMarker, true);
        map.setMapCenterPoint(DEFAULT_MARKER_POINT, false);

    }
    public void create_marker(MapView map){
        MapPoint DEFAULT_MARKER_POINT= MapPoint.mapPointWithGeoCoord(37.4021700, 127.1085700);

        MapPOIItem mDefaultMarker = new MapPOIItem();
        String name = "Default Marker";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(1);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT);

        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);

        mDefaultMarker.setCustomImageResourceId(R.drawable.custom_poi_marker);
        mDefaultMarker.setCustomImageAutoscale(true);
        mDefaultMarker.setCustomImageAnchor(0.5f, 1.0f);

        map.addPOIItem(mDefaultMarker);
//        mapView.selectPOIItem(mDefaultMarker, true);
        map.setMapCenterPoint(DEFAULT_MARKER_POINT, false);
    }

    @Override
    public void onMapViewInitialized(MapView mapView) {
        onClick_joinOK(mapView);
        create_marker(mapView);
    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
        Intent intent = new Intent(this,user_iteminfo.class);
        startActivity(intent);

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }
}
