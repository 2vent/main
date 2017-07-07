package com.example.win.a2vent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by win on 2017-07-05.
 */

public class owner_event_adapter extends RecyclerView.Adapter<owner_event_holer> {

    private ArrayList<owner_event_item> event_list=new ArrayList<owner_event_item>();

    public owner_event_adapter(ArrayList<owner_event_item> mevent_list){
        event_list=mevent_list;
    }

    @Override
    public owner_event_holer onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_owner_event_item,parent,false);
        owner_event_holer vh=new owner_event_holer(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(owner_event_holer holder, int position) {

        holder.event_name.setText(event_list.get(position).event_name);
        holder.event_addr.setText("동구");
        holder.event_price.setText(String.valueOf(event_list.get(position).event_price));
        holder.event_dis_price.setText(String.valueOf(event_list.get(position).getEvent_dis_price()));
        holder.event_day.setText("동구");
//        holder.event_time.setText("동구");
//        holder.event_main);

    }

    @Override
    public int getItemCount() {
        return event_list.size();
    }
}
