package com.example.win.a2vent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by win on 2017-07-03.
 */

public class owner_com_adapter extends RecyclerView.Adapter<owner_com_holder>{
    private ArrayList<owner_com_item> mItems= new ArrayList<owner_com_item>();


    public owner_com_adapter(ArrayList<owner_com_item> items){
        mItems=items;

    }


    @Override
    public owner_com_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_owner_com_item,parent,false);
        owner_com_holder holder= new owner_com_holder(v);



        return holder;

    }

    @Override
    public void onBindViewHolder(owner_com_holder holder, int position) {
        Log.d(TAG, "onBindViewHolder: 안됨");
        Log.d(TAG,mItems.get(position).com_name.toString());
        holder.com_name.setText(mItems.get(position).com_name);
        holder.com_addr.setText(mItems.get(position).com_addr);
        holder.com_manager.setText(mItems.get(position).com_manager);
        holder.com_category.setText(mItems.get(position).com_category);



    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
