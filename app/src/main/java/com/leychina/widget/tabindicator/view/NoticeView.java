package com.leychina.widget.tabindicator.view;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leychina.R;
import com.leychina.value.Constant;
import com.squareup.picasso.Picasso;

/**
 * Created by yuandunlong on 11/23/15.
 */
public class NoticeView {

    private View rootView;
    private Context context;

    private ImageView image;
    private TextView title;

    private TextView content;

    private String imageURL;

    private LayoutInflater layoutInflater;

    public NoticeView(Context context,String title,String content,String imageURL){

        this.context=context;
        this.layoutInflater=LayoutInflater.from(context);

        this.rootView=layoutInflater.inflate(R.layout.notice_view, null, false);
        this.imageURL=imageURL;

        this.content= (TextView) rootView.findViewById(R.id.notice_content);
        this.title= (TextView) rootView.findViewById(R.id.notice_title);
        this.image= (ImageView) rootView.findViewById(R.id.notice_image_view);

        this.content.setText(content);
        this.title.setText(title);



    }


    public void attach(ViewGroup viewGroup){
        viewGroup.addView(this.rootView);
        Picasso.with(this.context).load(Constant.DOMAIN+"/static/upload/"+this.imageURL).into(this.image);


    }
}
