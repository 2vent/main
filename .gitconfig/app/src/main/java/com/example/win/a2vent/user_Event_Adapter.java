package com.example.win.a2vent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by EUNJAESHIN on 2017-07-10.
 */

public class user_Event_Adapter extends RecyclerView.Adapter<user_Event_Holder> {

    private Context context;
    private ArrayList<user_Event_Item> mItems = new ArrayList<user_Event_Item>();
    private int lastPosition = -1;
    private String source_URI = "http://192.168.0.106/eventApp/";

    public user_Event_Adapter(ArrayList items, Context mContext) {
        mItems = items;
        context = mContext;
    }

    @Override
    public user_Event_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.user_event_cardview, parent, false);
        user_Event_Holder holder = new user_Event_Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(user_Event_Holder holder, int position) {
        Picasso.with(context).load(source_URI + mItems.get(position).event_URI)
                .placeholder(R.drawable.events_medium).into(holder.imageView);
        holder.textView1.setText(mItems.get(position).event_name);
        holder.textView2.setText(mItems.get(position).event_price);
        holder.textView3.setText(mItems.get(position).event_dis_price);
        holder.textView4.setText(mItems.get(position).event_startday);
        holder.textView5.setText(mItems.get(position).event_endday);

        setAnimation(holder.imageView, position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context,
                    android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}


