package com.example.win.a2vent;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by win on 2017-07-03.
 */

public class owner_com_holder extends RecyclerView.ViewHolder{
    TextView com_name;
    TextView com_manager;
    TextView com_addr;
    TextView com_category;


    public owner_com_holder(View itemView) {
        super(itemView);
        com_name=(TextView) itemView.findViewById(R.id.com_name);
        com_manager=(TextView) itemView.findViewById(R.id.com_manager);
        com_addr=(TextView) itemView.findViewById(R.id.com_addr);
        com_category=(TextView)itemView.findViewById(R.id.com_category);
    }
}
