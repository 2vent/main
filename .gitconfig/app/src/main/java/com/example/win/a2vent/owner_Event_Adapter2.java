package com.example.win.a2vent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by win on 2017-07-05.
 */

public class owner_Event_Adapter2 extends RecyclerView.Adapter<owner_Event_Holder> {

    String test;

    private ArrayList<owner_Event_Item2> event_list=new ArrayList<owner_Event_Item2>();

    public owner_Event_Adapter2(ArrayList<owner_Event_Item2> mevent_list){
        event_list=mevent_list;
    }

    @Override
    public owner_Event_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.owner_event_cardview,parent,false);
        owner_Event_Holder vh=new owner_Event_Holder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(owner_Event_Holder holder, int position) {

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
