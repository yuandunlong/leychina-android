package com.leychina.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.leychina.R;
import com.leychina.http.OkHttpClientManager;
import com.leychina.model.Result;
import com.leychina.utils.StringUtil;
import com.leychina.value.Constant;
import com.squareup.okhttp.Request;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yuandunlong on 11/29/15.
 */
public class AddAddressActivity extends AppCompatActivity {

    public static void start(Activity context) {
        Intent intent = new Intent(context, AddAddressActivity.class);
        context.startActivity(intent);
    }

    public static void startForResult(Activity context){
        Intent intent = new Intent(context, AddAddressActivity.class);
        context.startActivityForResult(intent,RESULT_OK);
    }

    EditText phoneEditText, receiveManEditText, addressEditText;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        phoneEditText = (EditText) findViewById(R.id.phone_edit_text);
        receiveManEditText = (EditText) findViewById(R.id.receive_man_edit_text);
        addressEditText = (EditText) findViewById(R.id.address_edit_text);

        saveBtn = (Button) findViewById(R.id.address_save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    saveAddress();
                }

            }
        });


    }

    private boolean validateInput() {
        if (StringUtils.isBlank(phoneEditText.getText().toString())) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT);
            return false;
        }
        if (StringUtils.isBlank(receiveManEditText.getText().toString())) {
            Toast.makeText(this, "请输入收件人信息", Toast.LENGTH_SHORT);
            return false;
        }
        if (StringUtils.isBlank(addressEditText.getText().toString())) {
            Toast.makeText(this, "请输入地址信息", Toast.LENGTH_SHORT);

            return false;
        }
        return true;

    }

    private void saveAddress() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("recieve_man", receiveManEditText.getText().toString());
            jsonObject.put("phone", phoneEditText.getText().toString());
            jsonObject.put("address", addressEditText.getText().toString());
            OkHttpClientManager.postAsyn(Constant.API_PATH.ADD_USER_ADDRESS + App.user.getAccessToken(), jsonObject, new OkHttpClientManager.ResultCallback<Result>() {

                @Override
                public void onError(Request request, Exception e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Result response) {
                    if (response.isOK()) {
                        AddAddressActivity.this.finish();
                    }

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
