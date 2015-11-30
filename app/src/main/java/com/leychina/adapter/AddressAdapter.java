package com.leychina.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.leychina.R;
import com.leychina.activity.AddAddressActivity;
import com.leychina.model.Address;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuandunlong on 11/29/15.
 */
public class AddressAdapter extends RecyclerView.Adapter {

    public static int VIEW_TYPE_ADDRESS = 1;
    public static int VIEW_TYPE_BTN = 2;

    public AddressAdapter(Context context){
        this.context=context;

    }

    public void addData(List<Address> data){
        this.addresses.addAll(data);
        notifyDataSetChanged();
    }

    private Context context;

    List<Address> addresses = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_address, parent, false);
                AddressViewHolder addressViewHolder = new AddressViewHolder(view);
                return addressViewHolder;

            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_add_address_btn, parent, false);
                return new BtnViewHolder(view);
            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(getItemViewType(position)==VIEW_TYPE_BTN){
            BtnViewHolder btnViewHolder= (BtnViewHolder) holder;
            btnViewHolder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddAddressActivity.start((Activity) context);

                }
            });

        }else{
            Address address=this.addresses.get(position);
            AddressViewHolder addressViewHolder= (AddressViewHolder) holder;
            addressViewHolder.phoneTextView.setText(address.getPhone());
            addressViewHolder.addressTextView.setText(address.getAddress());
            addressViewHolder.manTextView.setText(address.getRecieveMan());

        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position >=addresses.size()) {
            return VIEW_TYPE_BTN;
        } else {
            return VIEW_TYPE_ADDRESS;

        }
    }

    @Override
    public int getItemCount() {
        return addresses.size() + 1;
    }

    class AddressViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        TextView manTextView, addressTextView, phoneTextView;
        ImageView statusImageView;

        public AddressViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            this.manTextView = (TextView) rootView.findViewById(R.id.address_man_text_view);
            this.addressTextView = (TextView) rootView.findViewById(R.id.address_text_view);
            this.phoneTextView = (TextView) rootView.findViewById(R.id.address_phone_text_view);
            this.statusImageView = (ImageView) rootView.findViewById(R.id.address_status_image_view);
        }
    }

    class BtnViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        Button btn;

        public BtnViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            btn = (Button) rootView.findViewById(R.id.address_add);
        }
    }
}
