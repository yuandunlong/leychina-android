package com.leychina.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;

import com.leychina.R;
import com.leychina.adapter.ArtistGridViewAdapter;
import com.leychina.http.OkHttpClientManager;
import com.leychina.model.Artist;
import com.leychina.model.ResultArtists;
import com.leychina.utils.ListUtils;
import com.leychina.value.Constant;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuandunlong on 11/9/15.
 */
public class ArtistGridViewActivity extends AppCompatActivity {
    GridView gridView;
    ArtistGridViewAdapter artistGridViewAdapter;
    SwipyRefreshLayout swipyRefreshLayout;
    List<Artist> artists;
    private int currentPage=1;
    private long catId=-1;

    public static void start(Context from,long catId){

        Intent intent=new Intent(from,ArtistGridViewActivity.class);
        intent.putExtra("cat_id", catId);
        from.startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        long catId=intent.getLongExtra("cat_id",-1);
        if(catId>=0){
            this.catId=catId;
        }
        setContentView(R.layout.activity_grid_view_artist);

        swipyRefreshLayout= (SwipyRefreshLayout) findViewById(R.id.swipyrefreshlayout);
        gridView= (GridView) findViewById(R.id.grid_view_artist);
        artistGridViewAdapter=new ArtistGridViewAdapter(this);
        gridView.setAdapter(artistGridViewAdapter);



        List<Artist> data=new ArrayList<>();
        artistGridViewAdapter.setDatas(data);
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                Log.d("MainActivity", "Refresh triggered at "
                        + (direction == SwipyRefreshLayoutDirection.TOP ? "top" : "bottom"));

                if (direction == SwipyRefreshLayoutDirection.TOP) {
                    currentPage = 1;
                    loadNewArtist();

                } else {
                    currentPage += 1;
                    loadMoreArtist();


                }
            }
        });

        loadNewArtist();


    }

    private void loadNewArtist(){

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("page",currentPage);
            jsonObject.put("page_size",20);
            if(catId>=0){
                jsonObject.put("cat_id",catId);
            }
            jsonObject.put("order_by","updated_time desc");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpClientManager.postAsyn(Constant.API_PATH.GET_ARTIST_BY_PAGE,jsonObject,new OkHttpClientManager.ResultCallback<ResultArtists>(){

            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(ResultArtists response) {
                if(response.isOK()){
                    artists=response.getArtists();
                    if(ListUtils.isNotEmpty(artists)){
                        artistGridViewAdapter.setDatas(artists);
                    }

                }
                swipyRefreshLayout.setRefreshing(false);

            }
        });


    }

    private void loadMoreArtist(){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("page",currentPage);
            jsonObject.put("page_size",20);
            if(catId>=0){
                jsonObject.put("cat_id",catId);
            }
            jsonObject.put("order_by","updated_time desc");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpClientManager.postAsyn(Constant.API_PATH.GET_ARTIST_BY_PAGE,jsonObject,new OkHttpClientManager.ResultCallback<ResultArtists>(){

            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(ResultArtists response) {
                if(response.isOK()){
                    artists=response.getArtists();
                    if(ListUtils.isNotEmpty(artists)){
                        artistGridViewAdapter.setDatas(artists);
                    }

                }
                swipyRefreshLayout.setRefreshing(false);

            }
        });






    }


}
