package com.example.win.a2vent;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by EUNJAESHIN on 2017-07-10.
 */

public class event_user_main extends AppCompatActivity {

    Context mContext;
    RecyclerView.Adapter rAdapter;
    RecyclerView rView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_user_main);
        set_TabHost(this);

        mContext = getApplicationContext();

        rView1 = (RecyclerView) findViewById(R.id.rview_content1);
        rView1.setHasFixedSize(true);

        ArrayList items = new ArrayList<>();

        items.add(new event_user_item((R.drawable.events_medium), "ㅎㅇ"));
        items.add(new event_user_item((R.drawable.events_medium), "ㅎㅇ2"));
        items.add(new event_user_item((R.drawable.events_medium), "ㅎㅇ3"));

        rAdapter = new rAdapter(items, mContext);
    }

    public void onClick_Accountinfo(View v) {
        Toast.makeText(event_user_main.this, "이거 누르면 계정정보로", Toast.LENGTH_SHORT).show();
    }

    public void onClick_goMap(View v) {
        Toast.makeText(event_user_main.this, "이거 누르면 지도뷰로", Toast.LENGTH_SHORT).show();
    }

    public void set_TabHost(Activity a) {
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost_usermain);
        tabHost.setup();

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("Tab Spec 1");
        tabSpec1.setContent(R.id.content1);
        tabSpec1.setIndicator("패션");
        tabHost.addTab(tabSpec1);

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("Tab Spec 2");
        tabSpec2.setContent(R.id.content2);
        tabSpec2.setIndicator("외식");
        tabHost.addTab(tabSpec2);

        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("Tab Spec 3");
        tabSpec3.setContent(R.id.content3);
        tabSpec3.setIndicator("기타");
        tabHost.addTab(tabSpec3);

        TabHost.TabSpec tabSpec4 = tabHost.newTabSpec("Tab Spec 4");
        tabSpec4.setContent(R.id.content4);
        tabSpec4.setIndicator("등등");
        tabHost.addTab(tabSpec4);
    }

    class mAdapter extends RecyclerView.Adapter {

        private Context context;
        private ArrayList<event_user_item> mItems;
        private int lastPosition = -1;

        public mAdapter(ArrayList items, Context mContext) {
            mItems = items;
            context = mContext;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.event_user_cardview, parent, false);
            ViewHolder holder = new ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {

            public ImageView imageView;
            public TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.cardview_image);
                textView = (TextView) itemView.findViewById(R.id.cardview_text1);
            }
        }

        private void setAnimation(View viewToAnimate, int position) {
            if (position > lastPosition) {
                Animation animation = AnimationUtils.loadAnimation(mContext,
                        android.R.anim.slide_in_left);
                viewToAnimate.startAnimation(animation);
                lastPosition = position;
            }
        }

    }

}
