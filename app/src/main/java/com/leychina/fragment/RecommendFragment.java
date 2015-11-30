package com.leychina.fragment;

import android.os.Bundle;

import com.leychina.R;
import com.leychina.adapter.ProjectPostPagerAdapter;
import com.leychina.http.OkHttpClientManager;
import com.leychina.model.ProjectPost;
import com.leychina.model.ResultProjectPost;
import com.leychina.utils.ListUtils;
import com.leychina.value.Constant;
import com.leychina.widget.tabindicator.flipper.ViewFlipperView;
import com.leychina.widget.tabindicator.fragment.LazyFragment;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * Created by yuandunlong on 10/31/15.
 */
public class RecommendFragment extends LazyFragment {
    private List<ProjectPost> projectPosts;

    AutoScrollViewPager autoScrollViewPager;
    ProjectPostPagerAdapter adapter;
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
//
//
//        ViewFlipperView view=new ViewFlipperView(getActivity());
//        setContentView(new ViewFlipperView(getActivity()));

        setContentView(R.layout.fragment_project_recommend);
        autoScrollViewPager= (AutoScrollViewPager) findViewById(R.id.project_auto_scroll_view_pager);
        projectPosts = new ArrayList<ProjectPost>();
        adapter=new ProjectPostPagerAdapter(getActivity(),projectPosts);

        autoScrollViewPager.setAdapter(adapter.setInfiniteLoop(false));
        //autoScrollViewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        autoScrollViewPager.setInterval(3000);


        loadProjectPost();
    }


    private void loadProjectPost(){


        OkHttpClientManager.getAsyn(Constant.API_PATH.GET_PROJECT_POST,new OkHttpClientManager.ResultCallback<ResultProjectPost>(){

            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(ResultProjectPost response) {

                if(response.getCode()==0){
                   projectPosts= response.getProjectPosts();
                    adapter.setProjectPosts(projectPosts);
                    autoScrollViewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % ListUtils.getSize(projectPosts));
                    autoScrollViewPager.startAutoScroll();

                }

            }
        });
    }


}
