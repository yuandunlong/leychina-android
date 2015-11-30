package com.leychina.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.leychina.R;
import com.leychina.fragment.ProjectDescriptionFragment;
import com.leychina.http.OkHttpClientManager;
import com.leychina.model.Project;
import com.leychina.model.ResultPaybacks;
import com.leychina.value.Constant;
import com.squareup.okhttp.Request;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

/**
 * Created by yuandunlong on 10/31/15.
 */
public class ProjectDetailActivity extends AppCompatActivity {

    static final String KEY_PROJECT_ID="project_id";
    static final String KEY_PROJECT="project";

    Project project;

    ImageView imageViewProjectCover;
    TabLayout tabLayoutDetail;
    ViewPager viewPagerDetail;

    public static void start(Project project,Activity from){
        Intent intent=new Intent(from, ProjectDetailActivity.class);
        intent.putExtra(KEY_PROJECT,project);
        from.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        project=intent.getParcelableExtra(KEY_PROJECT);
        setContentView(R.layout.activity_project_detail);

        tabLayoutDetail= (TabLayout) findViewById(R.id.tab_layout_detail);
        viewPagerDetail= (ViewPager) findViewById(R.id.view_pager_detail);

        viewPagerDetail.setAdapter(new TabPagerAdapter(getSupportFragmentManager()));

        tabLayoutDetail.setupWithViewPager(viewPagerDetail);

        imageViewProjectCover= (ImageView) findViewById(R.id.image_view_project_cover);
        Picasso.with(this).load(Constant.DOMAIN + "/static/upload/" + project.getCoverImage()).into(imageViewProjectCover);


    }

    void loadPaybacksInfo(){

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put(KEY_PROJECT_ID,project.getId());

            OkHttpClientManager.postAsyn(Constant.API_PATH.GET_PAYBACKS_BY_PROJECT_ID, jsonObject, new OkHttpClientManager.ResultCallback<ResultPaybacks>() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(ResultPaybacks response) {

                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    class TabPagerAdapter extends FragmentPagerAdapter {

        public   final String [] titles={"简介","权益"};

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {

            return new ProjectDescriptionFragment();
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}
