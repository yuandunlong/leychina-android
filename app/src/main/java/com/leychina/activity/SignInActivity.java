package com.leychina.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.leychina.R;
import com.leychina.http.OkHttpClientManager;
import com.leychina.manager.PrefManager;
import com.leychina.model.ResultSignUp;
import com.leychina.model.ResultUser;
import com.leychina.utils.StringUtil;
import com.leychina.value.Constant;
import com.squareup.okhttp.Request;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yuandunlong on 10/27/15.
 */
public class SignInActivity extends Activity implements View.OnClickListener{

    EditText accountEditText;
    EditText passwordEditText;

    Button loginBtn,goSignUpBtn;

    public static void start(Activity from){

        Intent intent=new Intent(from,SignInActivity.class);

        from.startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sigin);
        accountEditText = (EditText) findViewById(R.id.edit_text_mobile);
        passwordEditText = (EditText) findViewById(R.id.edit_text_pwd);
        loginBtn= (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);
        goSignUpBtn= (Button) findViewById(R.id.go_to_signup);
        goSignUpBtn.setOnClickListener(this);

    }

    void doLogin(){
        if(StringUtils.isBlank(accountEditText.getText().toString())){
            Toast.makeText(this,"请输入手机号码",Toast.LENGTH_SHORT);
            return;
        }
        if(StringUtils.isBlank(passwordEditText.getText().toString())){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT);
            return;
        }
        JSONObject jsonObject=new JSONObject();

        try {
            jsonObject.put("challenge","leychina");
            jsonObject.put("client_id","android-app");
            jsonObject.put("mobile",accountEditText.getText().toString());
            jsonObject.put("pass_code",StringUtil.MD5(StringUtil.MD5(passwordEditText.getText().toString()) + "leychina"));
            OkHttpClientManager.postAsyn(Constant.API_PATH.GET_ACESS_TOKEN,jsonObject,new OkHttpClientManager.ResultCallback<ResultSignUp>(){
                @Override
                public void onError(Request request, Exception e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(ResultSignUp response) {

                    if(response.isOK()){
                        PrefManager.getInstance().putString("access_token",response.getAccessToken());
                        PrefManager.getInstance().putBoolean("is_sign_in", true);

                        loadUserInfo(response.getAccessToken());
                        App.user.setIsSignIn(true);
                        App.user.setAccessToken(response.getAccessToken());
                        SignInActivity.this.finish();


                    }else{
                        PrefManager.getInstance().putBoolean("is_sign_in",false);
                        Toast.makeText(SignInActivity.this,"用户名或者密码错误",Toast.LENGTH_LONG).show();

                    }

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // OkHttpClientManager.postAsyn();

    }

    void loadUserInfo(final String token){

        OkHttpClientManager.getAsyn(Constant.API_PATH.GET_USER_INFO + token, new OkHttpClientManager.ResultCallback<ResultUser>() {

            @Override
            public void onError(Request request, Exception e) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(ResultUser response) {
               if(response.isOK()){
                   App.user=response.getUser();
                   App.user.setAccessToken(token);
                   App.user.setIsSignIn(true);
                   App.user.save2Pref();
               }


            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                doLogin();
                break;
            case R.id.go_to_signup:
                SignUpActivity.start(this);
                break;

        }
    }

}
