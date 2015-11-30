package com.leychina.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.leychina.R;
import com.leychina.http.OkHttpClientManager;
import com.leychina.model.Address;
import com.leychina.model.Payback;
import com.leychina.model.ResultAddress;
import com.leychina.utils.StringUtil;
import com.leychina.value.Constant;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yuandunlong on 11/18/15.
 */
public class SupportActivity extends AppCompatActivity implements View.OnClickListener{

    private Payback payback;
    TextView paybackInfo;

    Button plusAmountBtn;
    Button minusAmountBtn;
    Address address;
    TextView amountTextView,supportMoneyTextView,totalMoneyTextView,addressTextView;

    int currentAmout;

    public static  void start(Context from,Payback payback){
        Intent intent=new Intent(from,SupportActivity.class);
        intent.putExtra("pay_back",payback);
        from.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        payback= (Payback) intent.getSerializableExtra("pay_back");
        setContentView(R.layout.activity_support);
        paybackInfo= (TextView) findViewById(R.id.support_payback_info);
        paybackInfo.setText(payback.getDetail());

        plusAmountBtn= (Button) findViewById(R.id.plus_btn);
        plusAmountBtn.setOnClickListener(this);
        minusAmountBtn= (Button) findViewById(R.id.minus_btn);
        minusAmountBtn.setOnClickListener(this);
        amountTextView= (TextView) findViewById(R.id.text_amount);
        supportMoneyTextView= (TextView) findViewById(R.id.support_money_text_view);
        supportMoneyTextView.setText(StringUtil.getMoneyText(payback.getMoney()));

        totalMoneyTextView= (TextView) findViewById(R.id.total_money_text_view);
        totalMoneyTextView.setText(StringUtil.getMoneyText(payback.getMoney()));

        addressTextView= (TextView) findViewById(R.id.address_text_view);
        addressTextView.setOnClickListener(this);
        loadUserDefaultAddress();



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.plus_btn:
                increaseAmount();
                break;
            case R.id.minus_btn:
                decreaseAmount();
                break;
            case R.id.address_text_view:
                ChooseAddressActivity.start(this);
                break;
        }

    }

    private void increaseAmount(){

        currentAmout++;
        float supportMoney= payback.getMoney()*currentAmout;
        supportMoneyTextView.setText(StringUtil.getMoneyText(supportMoney));
        amountTextView.setText(currentAmout+"");

    }

    private void decreaseAmount(){
        if(currentAmout>1){
            currentAmout--;
            amountTextView.setText(currentAmout+"");
            float supportMoney= payback.getMoney()*currentAmout;
            supportMoneyTextView.setText(StringUtil.getMoneyText(supportMoney));
        }

    }

    private void loadUserDefaultAddress(){

        OkHttpClientManager.getAsyn(Constant.API_PATH.GET_USER_DEFAULT_ADDRESS + App.user.getAccessToken(), new OkHttpClientManager.ResultCallback<ResultAddress>() {

            @Override
            public void onError(Request request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(ResultAddress response) {
                if(response.isOK() && response.getAddress()!=null){
                    addressTextView.setText(response.getAddress().getDisplayText());
                    address=response.getAddress();
                }
            }
        });



        
    }

    private void submitOrder(){

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("payback_id",payback.getId());
            jsonObject.put("project_id",payback.getProjectId());
            jsonObject.put("amount",currentAmout);
            jsonObject.put("address_id",address.get)
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
