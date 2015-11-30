package com.leychina.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.leychina.activity.ProjectDetailActivity2;
import com.leychina.http.OkHttpClientManager;
import com.leychina.model.ProjectPost;
import com.leychina.model.ResultProject;
import com.leychina.utils.ListUtils;
import com.leychina.value.Constant;
import com.leychina.widget.tabindicator.view.viewpager.RecyclingPagerAdapter;
import com.squareup.okhttp.Request;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by yuandunlong on 11/21/15.
 */
public class ProjectPostPagerAdapter extends RecyclingPagerAdapter {
    private Context       context;
    private List<ProjectPost> projectPosts;

    private int           size;
    private boolean       isInfiniteLoop;


    public ProjectPostPagerAdapter(Context context, List<ProjectPost> projectPosts) {
        this.context = context;
        this.projectPosts = projectPosts;
        this.size = ListUtils.getSize(projectPosts);
        isInfiniteLoop = false;
    }

    @Override
    public int getCount() {
        // Infinite loop
        return isInfiniteLoop ? Integer.MAX_VALUE : ListUtils.getSize(projectPosts);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        ViewHolder holder;
        final ProjectPost projectPost=projectPosts.get(getPosition(position));
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = holder.imageView = new ImageView(context);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(projectPost.isLink()){
                    Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(projectPost.getLink()));
                    it.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                    context.startActivity(it);
                }else if(projectPost.isProject()){
                    jumpToProjectDetail(projectPost.getProjectId());
                }


            }
        });

        Picasso.with(context).load(Constant.DOMAIN + "/static/upload/" +projectPosts.get(getPosition(position)).getImageURL()).into(holder.imageView);


        return convertView;
    }


    private int getPosition(int position) {
        return isInfiniteLoop ? position % size : position;
    }
    private static class ViewHolder {

        ImageView imageView;
    }

    /**
     * @return the isInfiniteLoop
     */
    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    /**
     * @param isInfiniteLoop the isInfiniteLoop to set
     */
    public ProjectPostPagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }

    public List<ProjectPost> getProjectPosts() {
        return projectPosts;
    }

    public void setProjectPosts(List<ProjectPost> projectPosts) {
        this.projectPosts = projectPosts;
        this.notifyDataSetChanged();
    }


    private void jumpToProjectDetail(long projectId){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("project_id",projectId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpClientManager.postAsyn(Constant.API_PATH.GET_PROJECT_BY_ID,jsonObject, new OkHttpClientManager.ResultCallback< ResultProject>(){

            @Override
            public void onError(Request request, Exception e) {

                e.printStackTrace();

            }

            @Override
            public void onResponse(ResultProject response) {

                if(response.isOK()){
                    ProjectDetailActivity2.start(response.getProject(),context);
                }

            }
        });


    }
}
