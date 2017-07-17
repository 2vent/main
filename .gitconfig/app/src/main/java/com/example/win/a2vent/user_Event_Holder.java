package com.example.win.a2vent;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017-07-10.
 */

public class user_Event_Holder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView textView1,textView2,textView3,textView4,textView5,textView6;

    public user_Event_Holder(View itemView) {
        super(itemView);
//        imageView = (ImageView) itemView.findViewById(R.id.cardview_image);
        textView1 = (TextView) itemView.findViewById(R.id.cardview_text1);
        textView2 = (TextView) itemView.findViewById(R.id.cardview_text2);
        textView3 = (TextView) itemView.findViewById(R.id.cardview_text3);
        textView3.setPaintFlags(textView3.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        textView4 = (TextView) itemView.findViewById(R.id.cardview_text4);
        textView5 = (TextView) itemView.findViewById(R.id.cardview_text5);
        textView6 = (TextView) itemView.findViewById(R.id.cardview_text6);


    }
}
