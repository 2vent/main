package com.example.win.a2vent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by EUNJAESHIN on 2017-07-10.
 */

public class event_user_adapter extends RecyclerView.Adapter<event_user_holder> {

    private Context context;
    private ArrayList<event_user_item> mItems = new ArrayList<event_user_item>();
    private int lastPosition = -1;

    public event_user_adapter(ArrayList items, Context mContext) {
        mItems = items;
        context = mContext;
    }

    @Override
    public event_user_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.event_user_cardview, parent, false);
        event_user_holder holder = new event_user_holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(event_user_holder holder, int position) {
        holder.imageView.setImageResource(mItems.get(position).image);
        holder.textView.setText(mItems.get(position).imagetitle);

        setAnimation(holder.imageView, position);
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
            Animation animation = AnimationUtils.loadAnimation(context,
                    android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}