package com.leychina.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.leychina.R;
import com.leychina.http.OkHttpClientManager;
import com.leychina.model.Payback;
import com.leychina.model.Project;
import com.leychina.model.ResultPaybacks;
import com.leychina.value.Constant;
import com.squareup.okhttp.Request;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yuandunlong on 11/6/15.
 */
public class ProjectDetailActivity2 extends AppCompatActivity {
    RecyclerView recyclerView;

    static final String KEY_PROJECT_ID = "project_id";
    static final String KEY_PROJECT = "project";

    Project project;
    ProjectDetailAdapter adapter;

    public static void start(Project project, Context from) {
        Intent intent = new Intent(from, ProjectDetailActivity2.class);
        intent.putExtra(KEY_PROJECT, project);
        from.startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        project = intent.getParcelableExtra(KEY_PROJECT);
        setContentView(R.layout.activity_project_detail_v2);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_project_detail);
        adapter=new ProjectDetailAdapter(this,project);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loadPaybacks();

    }


    void loadPaybacks(){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put(KEY_PROJECT_ID,project.getId());
            OkHttpClientManager.postAsyn(Constant.API_PATH.GET_PAYBACKS_BY_PROJECT_ID, jsonObject,new OkHttpClientManager.ResultCallback<ResultPaybacks>(){

                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(ResultPaybacks response) {

                    if(response!=null){
                        adapter.setPaybacks(response.getPaybacks());
                    }


                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    static class ProjectDetailAdapter extends RecyclerView.Adapter {
        private Activity activity;

        private List<Payback> paybacks=new ArrayList<>();

        public List<Payback> getPaybacks() {
            return paybacks;
        }

        public ProjectDetailAdapter(Activity activity,Project project){
            this.activity=activity;
            this.project=project;

        }

        public void setPaybacks(List<Payback> paybacks) {
            this.paybacks = paybacks;
            this.notifyDataSetChanged();
        }



        Project project;
        final int VIEW_TYPE_PROJECT_INFO = 1;
        final int VIEW_TYPE_PAYBACK_INFO = 2;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            switch (viewType) {
                case VIEW_TYPE_PROJECT_INFO:
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_project_info, parent, false);
                    return new ProjectInfoViewHolder(view);
                case VIEW_TYPE_PAYBACK_INFO:
                    View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_payback, parent, false);



                    return new PaybackViewHolder(view2);


            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            switch (getItemViewType(position)) {
                case VIEW_TYPE_PROJECT_INFO:
                    ProjectInfoViewHolder projectInfoViewHolder = (ProjectInfoViewHolder) holder;
                    Picasso.with((projectInfoViewHolder.rootView.getContext())).load(Constant.DOMAIN + "/static/upload/" + project.getCoverImage()).into(projectInfoViewHolder.imageViewProjectCover);
                    projectInfoViewHolder.textViewSummary.setText(project.getSummary());

                    String totalMoneyText="目标金额： <font color='#eb6000'><strong>￥%s</strong></font>";
                    totalMoneyText=String.format(totalMoneyText,project.getTotalMoney());
                    projectInfoViewHolder.textViewTotalMoney.setText(Html.fromHtml(totalMoneyText));

                    String supportTimesText="支持次数：<strong>%s</strong>";
                    supportTimesText=String.format(supportTimesText,project.getSupportTiems());
                    projectInfoViewHolder.textViewSupportTimes.setText(Html.fromHtml(supportTimesText));

                    String currentMoneyLabel = "已筹数额： <font  color='#eb6000'><strong>￥%s</strong></font>";
                    currentMoneyLabel = String.format(currentMoneyLabel, project.getCurrentMoney());
                    projectInfoViewHolder.textViewCurrentMoney.setText(Html.fromHtml(currentMoneyLabel));

                    String deadlineTimeText="剩余天数：<strong>%s</strong>";
                    deadlineTimeText=String.format(deadlineTimeText,"10");
                    projectInfoViewHolder.textViewDeadlineTime.setText(Html.fromHtml(deadlineTimeText));
                    projectInfoViewHolder.textViewTitle.setText(project.getTitle());
                    break;
                case VIEW_TYPE_PAYBACK_INFO:
                    final Payback payback=getPaybackAt(position);
                    PaybackViewHolder paybackViewHolder= (PaybackViewHolder) holder;
                    Picasso.with(paybackViewHolder.rootView.getContext()).load(Constant.DOMAIN + "/static/upload/"+payback.getCoverImage()).into(paybackViewHolder.imageViewCover);
                    paybackViewHolder.textViewTitle.setText(payback.getTitle());

                    String moneyText="<font  color='#eb6000'><strong>￥%s</strong></font>";
                    moneyText=String.format(moneyText,payback.getMoney());

                    paybackViewHolder.textViewMoney.setText(Html.fromHtml(moneyText));
                    paybackViewHolder.buttonSupport.setOnClickListener(new View.OnClickListener() {




                        @Override
                        public void onClick(View v) {

                            if(App.user.isSignIn()){
                                SupportActivity.start(activity,payback);
                            }else{
                                SignInActivity.start(activity);
                            }

                        }
                    });
                    break;

            }

        }

        @Override
        public int getItemCount() {
            return paybacks.size()+1;
        }

        public Payback getPaybackAt(int position){
            if(position==0){
                return null;
            }else{
                return paybacks.get(position-1);
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return VIEW_TYPE_PROJECT_INFO;

            } else {

                return VIEW_TYPE_PAYBACK_INFO;


            }
        }
    }

    static class ProjectInfoViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        ImageView imageViewProjectCover;
        TextView textViewCurrentMoney, textViewSupportTimes, textViewTotalMoney, textViewDeadlineTime, textViewSummary,textViewTitle;

        public ProjectInfoViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            imageViewProjectCover= (ImageView) rootView.findViewById(R.id.image_view_project_cover);
            textViewCurrentMoney = (TextView) rootView.findViewById(R.id.text_view_current_money);
            textViewSupportTimes = (TextView) rootView.findViewById(R.id.text_view_support_times);
            textViewTotalMoney = (TextView) rootView.findViewById(R.id.text_view_total_money);
            textViewDeadlineTime = (TextView) rootView.findViewById(R.id.text_view_deadline_time);
            textViewSummary = (TextView) rootView.findViewById(R.id.text_view_summary);
            textViewTitle= (TextView) rootView.findViewById(R.id.text_view_project_title);
        }
    }

    static class PaybackViewHolder extends RecyclerView.ViewHolder{
        View rootView;
        ImageView imageViewCover;
        TextView textViewTitle;
        TextView textViewMoney;
        Button buttonSupport;
        public PaybackViewHolder(View itemView) {
            super(itemView);
            rootView=itemView;

            imageViewCover= (ImageView) rootView.findViewById(R.id.image_view_payback_cover);
            textViewTitle= (TextView) rootView.findViewById(R.id.text_view_payback_title);
            textViewMoney= (TextView) rootView.findViewById(R.id.text_view_payback_money);
            buttonSupport= (Button) rootView.findViewById(R.id.button_support);
        }
    }
}
