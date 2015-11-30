package com.leychina.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.leychina.R;
import com.leychina.activity.SignInActivity;
import com.leychina.activity.SignUpActivity;
import com.leychina.manager.PrefManager;
import com.leychina.widget.tabindicator.fragment.LazyFragment;

/**
 * Created by yuandunlong on 10/31/15.
 */
public class UserCenterFragment extends LazyFragment implements View.OnClickListener{

    View myOrder,mySetting,artistApply;

    Button goToLoginBtn;

    boolean isSignIn=false;
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_user_center);
        myOrder=findViewById(R.id.my_order);
        myOrder.setOnClickListener(this);
        mySetting=findViewById(R.id.my_setting);
        mySetting.setOnClickListener(this);

        goToLoginBtn= (Button) findViewById(R.id.go_to_login_btn);
        goToLoginBtn.setOnClickListener(this);

        artistApply=findViewById(R.id.apply_artist);

        artistApply.setOnClickListener(this);

    }

    @Override
    protected void onFragmentStartLazy() {
        super.onFragmentStartLazy();

        if(PrefManager.getInstance().getBoolean("sign_in")){
            this.isSignIn=true;
            goToLoginBtn.setVisibility(View.GONE);

        }else{
            this.isSignIn=false;
            goToLoginBtn.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onClick(View v) {
        if(!isSignIn){
            SignInActivity.start(getActivity());

        }else{
            switch (v.getId()){
                case R.id.my_order:

                    break;
                case R.id.my_setting:
                    break;
                case R.id.go_to_login_btn:
                    break;
                case R.id.apply_artist:

                    break;
            }

        }

    }
}
