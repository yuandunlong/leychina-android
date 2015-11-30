package com.leychina.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.leychina.R;
import com.leychina.http.OkHttpClientManager;
import com.leychina.manager.PrefManager;
import com.leychina.model.ResultSignUp;
import com.leychina.utils.StringUtil;
import com.leychina.value.Constant;
import com.squareup.okhttp.Request;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

/**
 * Created by yuandunlong on 10/27/15.
 */
public class SignUpActivity extends Activity implements View.OnClickListener{

    Handler hander=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            btnSendSmsCode.setText(msg.what+"s");
            if(msg.what==0){
                btnSendSmsCode.setText("再次获取");
                btnSendSmsCode.setClickable(true);
            }
            super.handleMessage(msg);
        }
    };
    EditText editTextMobile,editTextSmsCode,editTextPwd,editTextPwdAgain;
    Button btnSignup,btnSendSmsCode;

    String mobile,pwd,pwdAgain,smsCode;

    public static void start(Activity from){

        Intent intent=new Intent(from,SignUpActivity.class);

        from.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextMobile= (EditText) findViewById(R.id.edit_text_mobile);
        editTextSmsCode= (EditText) findViewById(R.id.edit_text_sms_code);
        editTextPwd= (EditText) findViewById(R.id.edit_text_pwd);
        editTextPwdAgain= (EditText) findViewById(R.id.edit_text_pwd_again);
        btnSignup= (Button) findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(this);
        btnSendSmsCode= (Button) findViewById(R.id.btn_send_sms_code);
        btnSendSmsCode.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send_sms_code:
                sendSmsCode();
                break;
            case R.id.btn_signup:
                signUp();

                break;


        }
    }

    private void startCountDownThread(){

        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();
                for(int i=120;i>=0;i--){
                    Message message=new Message();
                    message.what=i;
                    hander.sendMessage(message);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        thread.start();
    }

    private void sendSmsCode(){

        String mobile=editTextMobile.getText().toString();
       if( StringUtils.isBlank(mobile)){
           Toast.makeText(this,"请输入手机号码！",Toast.LENGTH_SHORT).show();

        }else{
           btnSendSmsCode.setClickable(false);
           startCountDownThread();
           OkHttpClientManager.getAsyn(Constant.API_PATH.SEND_SMS_CODE + "?mobile=" + mobile, new OkHttpClientManager.ResultCallback<ResultSignUp>() {
               @Override
               public void onError(Request request, Exception e) {

               }

               @Override
               public void onResponse(ResultSignUp response) {

                   if(response.getCode()==0){
                       PrefManager.getInstance().putString("access_token",response.getAccessToken());
                       PrefManager.getInstance().putBoolean("is_sign_in", true);
                       Toast.makeText(SignUpActivity.this, "恭喜你注册成功", Toast.LENGTH_SHORT);
                       SignUpActivity.this.finish();


                   }else{
                       PrefManager.getInstance().putBoolean("is_sign_in", false);
                       Toast.makeText(SignUpActivity.this,"注册失败",Toast.LENGTH_SHORT);


                   }

               }



           });
       }


    }

    private void signUp()  {

         mobile=editTextMobile.getText().toString();
         smsCode=editTextSmsCode.getText().toString();
         pwd=editTextPwd.getText().toString();
         pwdAgain=editTextPwdAgain.getText().toString();
        if(validateInput()){
            JSONObject jsonObject=new JSONObject();
            try{
                jsonObject.put("mobile", mobile);
                jsonObject.put("sms_code",smsCode);
                jsonObject.put("pwd", StringUtil.MD5(pwd));
            }catch (Exception e){

            }
            OkHttpClientManager.postAsyn(Constant.API_PATH.SIGN_UP, jsonObject, new OkHttpClientManager.ResultCallback<ResultSignUp>() {

                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(ResultSignUp response) {

                    if (response.getCode() == 0) {
                        Log.d("SignUpActivity",response.getAccessToken());

                        PrefManager.getInstance().putString("access_token",response.getAccessToken());



                    } else {
                        Log.d("SignUpActivity", response.getMsg());
                    }

                }
            });

        }


    }

    private boolean validateInput(){

        if(StringUtils.isBlank(mobile)){
            Toast.makeText(this,"请输入手机号码",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(StringUtils.isBlank(smsCode)){
            Toast.makeText(this,"请输入短信验证码",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(StringUtils.isBlank(pwd)){
            Toast.makeText(this,"请输入注册密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(StringUtils.isBlank(pwdAgain)){
            Toast.makeText(this,"请再次输入密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!pwd.equals(pwdAgain)){
            Toast.makeText(this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
            return false;

        }

        return true;

    }
}
