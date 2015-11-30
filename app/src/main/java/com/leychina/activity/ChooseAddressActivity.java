package com.leychina.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.leychina.R;
import com.leychina.adapter.AddressAdapter;
import com.leychina.http.OkHttpClientManager;
import com.leychina.model.ResultAddresses;
import com.leychina.utils.ListUtils;
import com.leychina.value.Constant;
import com.squareup.okhttp.Request;

/**
 * Created by yuandunlong on 11/29/15.
 */
public class ChooseAddressActivity extends AppCompatActivity {

    public static void start(Context context){
        Intent intent=new Intent(context,ChooseAddressActivity.class);
        context.startActivity(intent);
    }

    RecyclerView addressRecylerView;
    AddressAdapter addressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_address);
        addressRecylerView= (RecyclerView) findViewById(R.id.address_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        addressRecylerView.setLayoutManager(layoutManager);

        addressAdapter=new AddressAdapter(this);
        addressRecylerView.setAdapter(addressAdapter);
        loadUserAddresses();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loadUserAddresses();

    }

    private void loadUserAddresses(){

        OkHttpClientManager.getAsyn(Constant.API_PATH.GET_USER_ADDRESSES + App.user.getAccessToken(), new OkHttpClientManager.ResultCallback<ResultAddresses>() {

            @Override
            public void onError(Request request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(ResultAddresses response) {
                if(response.isOK() && ListUtils.isNotEmpty(response.getAddresses())){

                    addressAdapter.addData(response.getAddresses());

                }

            }
        });
    }
}
