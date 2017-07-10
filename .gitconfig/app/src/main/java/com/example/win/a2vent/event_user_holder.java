package com.example.win.a2vent;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017-07-10.
 */

public class event_user_holder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView textView;

    public event_user_holder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.cardview_image);
        textView = (TextView) itemView.findViewById(R.id.cardview_text1);
    }
}
