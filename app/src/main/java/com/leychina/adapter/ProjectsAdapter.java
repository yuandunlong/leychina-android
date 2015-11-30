package com.leychina.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leychina.R;
import com.leychina.model.Project;
import com.leychina.value.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuandunlong on 10/26/15.
 */
public class ProjectsAdapter extends RecyclerView.Adapter {


    private List<Project> projects = new ArrayList<>();

    View.OnClickListener itemOnClickListener;

    public void setProjects(List<Project> datas) {
        this.projects = datas;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_project, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Project project = projects.get(position);

        ProjectViewHolder projectViewHolder = (ProjectViewHolder) holder;
        projectViewHolder.title.setText(project.getTitle());
        String currentMoneyLabel = "完成 <font  color='#eb6000'><strong>￥%s</strong></font>";
        currentMoneyLabel = String.format(currentMoneyLabel, project.getCurrentMoney());
        projectViewHolder.currentMoneyText.setText(Html.fromHtml(currentMoneyLabel));
        projectViewHolder.remainDaysTextView.setText("剩余天20");
        String rateTextHeader = "完成 <font  color='#eb6000'><strong>￥%s</strong></font>";
        rateTextHeader = String.format(rateTextHeader, project.getComplishRate() + "%");
        projectViewHolder.complishRateTextView.setText(Html.fromHtml(rateTextHeader));
        Log.d("dd", Constant.DOMAIN + "/upload/" + project.getCoverImage());

        if (itemOnClickListener != null) {
            projectViewHolder.rootView.setOnClickListener(itemOnClickListener);
        }
        Picasso.with(projectViewHolder.rootView.getContext()).load(Constant.DOMAIN + "/static/upload/" + project.getCoverImage()).into(projectViewHolder.imageView);


    }

    public Project getProjectAt(int position) {
        return projects.get(position);
    }

    @Override
    public int getItemCount() {
        return this.projects.size();
    }

    public View.OnClickListener getItemOnClickListener() {
        return itemOnClickListener;
    }

    public void setItemOnClickListener(View.OnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {

        View rootView;
        ImageView imageView;
        TextView title;
        TextView currentMoneyText;
        TextView remainDaysTextView;
        TextView complishRateTextView;


        public ProjectViewHolder(View itemView) {
            super(itemView);

            rootView = itemView;
            imageView = (ImageView) rootView.findViewById(R.id.project_logo_image);
            title = (TextView) rootView.findViewById(R.id.project_title);
            currentMoneyText = (TextView) rootView.findViewById(R.id.project_current_money);

            remainDaysTextView = (TextView) rootView.findViewById(R.id.project_remain_days);
            complishRateTextView = (TextView) rootView.findViewById(R.id.project_complish_rate);
        }


    }
}
