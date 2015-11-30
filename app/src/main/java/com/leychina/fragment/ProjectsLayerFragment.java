package com.leychina.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.leychina.R;
import com.leychina.activity.ProjectDetailActivity2;
import com.leychina.adapter.ProjectsAdapter;
import com.leychina.http.OkHttpClientManager;
import com.leychina.model.Project;
import com.leychina.model.ResultProjects;
import com.leychina.value.Constant;
import com.leychina.widget.tabindicator.fragment.LazyFragment;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class ProjectsLayerFragment extends LazyFragment {
	public static final String INTENT_STRING_TABNAME = "intent_String_tabName";
	public static final String INTENT_INT_POSITION = "intent_int_position";
	private String tabName;
	private int position;
	private TextView textView;
	private ProgressBar progressBar;

	private SwipyRefreshLayout swipyRefreshLayout;
	private RecyclerView recyclerView;
	private ProjectsAdapter adapter;
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		tabName = getArguments().getString(INTENT_STRING_TABNAME);
		position = getArguments().getInt(INTENT_INT_POSITION);
		setContentView(R.layout.fragment_progect_list);
		progressBar = (ProgressBar) findViewById(R.id.fragment_mainTab_item_progressBar);

		recyclerView= (RecyclerView) findViewById(R.id.project_recycler_view);
        adapter=new ProjectsAdapter();
		adapter.setItemOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				int position=recyclerView.getChildAdapterPosition(v);
				Project project=adapter.getProjectAt(position);

				ProjectDetailActivity2.start(project, getActivity());



			}
		});
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
		swipyRefreshLayout= (SwipyRefreshLayout) findViewById(R.id.swipe_refresh_widget);



        loadProjects();

		//handler.sendEmptyMessageDelayed(1, 3000);
	}

	@Override
	protected void onDestroyViewLazy() {
		super.onDestroyViewLazy();
		handler.removeMessages(1);
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			textView.setVisibility(View.VISIBLE);
			progressBar.setVisibility(View.GONE);
		};
	};



    public void loadProjects(){

        JSONObject requestJson=new JSONObject();
        try {
            requestJson.put("order_by","update");
            requestJson.put("page",1);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpClientManager.postAsyn(Constant.API_PATH.GET_PROJECTS_BY_PAGE, requestJson, new OkHttpClientManager.ResultCallback<ResultProjects>() {



            @Override
            public void onError(Request request, Exception e) {
                Log.i("project",e.getMessage());
                e.printStackTrace();

            }

            @Override
            public void onResponse(ResultProjects response) {
                Log.v("pp",response.toString());

                adapter.setProjects(response.getProjects());

            }





        });
    }


}
