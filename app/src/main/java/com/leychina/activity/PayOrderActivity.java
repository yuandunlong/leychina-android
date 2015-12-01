package com.leychina.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.leychina.R;
import com.leychina.model.Address;
import com.leychina.model.Order;

/**
 * Created by ydl on 15-11-30.
 */
public class PayOrderActivity extends AppCompatActivity {

    Order order;
    Address address;
    TextView payOrderAddressTextView;


    public static void start(Order order, Address address, Context from) {

        Intent intent = new Intent(from, PayOrderActivity.class);
        intent.putExtra("order", order);
        intent.putExtra("address",address);
        from.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        order = (Order) intent.getSerializableExtra("order");
        address= (Address) intent.getSerializableExtra("address");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_order);
        getSupportActionBar().setTitle("订单");
        payOrderAddressTextView = (TextView) findViewById(R.id.pay_order_address_text_view);
        payOrderAddressTextView.setText(address.getDisplayText());


    }
}
