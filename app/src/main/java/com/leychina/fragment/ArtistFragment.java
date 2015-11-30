package com.leychina.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leychina.R;
import com.leychina.activity.ArtistGridViewActivity;
import com.leychina.http.OkHttpClientManager;
import com.leychina.model.ActivityNotice;
import com.leychina.model.ArtistCategory;
import com.leychina.model.ResultActivityNotice;
import com.leychina.model.ResultArtistCategory;
import com.leychina.utils.ListUtils;
import com.leychina.utils.LogUtil;
import com.leychina.value.Constant;
import com.leychina.widget.tabindicator.flipper.ViewFlipperView;
import com.leychina.widget.tabindicator.fragment.LazyFragment;
import com.leychina.widget.tabindicator.view.NoticeView;
import com.litesuits.android.async.Log;
import com.squareup.okhttp.Request;

import java.util.List;

/**
 * Created by yuandunlong on 11/7/15.
 */
public class ArtistFragment extends LazyFragment implements View.OnClickListener {
    LinearLayout artistPost,mainContent;
    TextView t1,t2,t3,t4,t5,t6,t7,t8;
    List<ArtistCategory> cats;
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_artist);
        artistPost= (LinearLayout) findViewById(R.id.liner_layout_artist_post);
        mainContent= (LinearLayout) findViewById(R.id.liner_layout_main_content);
        artistPost.addView(new ViewFlipperView(getActivity()));
        t1= (TextView) findViewById(R.id.t1);
        t2= (TextView) findViewById(R.id.t2);
        t3= (TextView) findViewById(R.id.t3);
        t4= (TextView) findViewById(R.id.t4);
        t5= (TextView) findViewById(R.id.t5);
        t6= (TextView) findViewById(R.id.t6);
        t7= (TextView) findViewById(R.id.t7);
        t8= (TextView) findViewById(R.id.t8);

        t1.setOnClickListener(this);
        t2.setOnClickListener(this);
        t3.setOnClickListener(this);
        t4.setOnClickListener(this);
        t5.setOnClickListener(this);
        t6.setOnClickListener(this);
        t7.setOnClickListener(this);
        t8.setOnClickListener(this);

        loadArtistCatgory();

        loadActivityNotice();
    }

    private  void loadActivityNotice(){

        OkHttpClientManager.getAsyn(Constant.API_PATH.GET_ACTIVITY_NOTICE,new OkHttpClientManager.ResultCallback<ResultActivityNotice>(){

            @Override
            public void onError(Request request, Exception e) {
                LogUtil.e(e);

            }

            @Override
            public void onResponse(ResultActivityNotice response) {
                if(response.isOK()&&ListUtils.isNotEmpty(response.getActivityNotices())){
                    for(ActivityNotice activityNotice:response.getActivityNotices()){

                        NoticeView noticeView=new NoticeView(ArtistFragment.this.getContext(),activityNotice.getTitle(),activityNotice.getContent(),activityNotice.getImageURL());
                        noticeView.attach(mainContent);
                    }

                }

            }
        });



    }

    private void loadArtistCatgory(){

        OkHttpClientManager.getAsyn(Constant.API_PATH.GET_ARTIST_CATEGORY,new OkHttpClientManager.ResultCallback<ResultArtistCategory>(){

            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(ResultArtistCategory response) {

                if(response.isOK()&& ListUtils.getSize(response.getArtCategories())>=8){
                    List<ArtistCategory> cats=response.getArtCategories();
                    ArtistFragment.this.cats=cats;
                    t1.setText(cats.get(0).getName());
                    t1.setTag(cats.get(0).getId());

                    t2.setText(cats.get(1).getName());
                    t2.setTag(cats.get(1).getId());

                    t3.setText(cats.get(2).getName());
                    t3.setTag(cats.get(2).getId());

                    t4.setText(cats.get(3).getName());
                    t4.setTag(cats.get(3).getId());

                    t5.setText(cats.get(4).getName());
                    t5.setTag(cats.get(4).getId());

                    t6.setText(cats.get(5).getName());
                    t6.setTag(cats.get(5).getId());

                    t7.setText(cats.get(6).getName());
                    t7.setTag(cats.get(6).getId());

                    t8.setText(cats.get(7).getName());
                    t8.setTag(cats.get(7).getId());


                }

            }
        });

    }


    @Override
    public void onClick(View v) {


        long catId= (long) v.getTag();
        ArtistGridViewActivity.start(getActivity(),catId);
    }
}
