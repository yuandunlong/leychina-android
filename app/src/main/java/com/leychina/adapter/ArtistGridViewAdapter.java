package com.leychina.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leychina.R;
import com.leychina.activity.ArtistDetailActivity;
import com.leychina.model.Artist;
import com.leychina.value.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuandunlong on 11/7/15.
 */
public class ArtistGridViewAdapter extends BaseAdapter {


    LayoutInflater layoutInflater;
    List<Artist> artists=new ArrayList<>();
    Context context;

    public void setDatas(List<Artist> datas){
        this.artists=datas;
        notifyDataSetChanged();
    }
  public   ArtistGridViewAdapter(Context context){
      this.context=context;

    }


    @Override
    public int getCount() {
        return artists.size();
    }

    @Override
    public Object getItem(int position) {
        return artists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return artists.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ArtistViewHolder artistViewHolder;
        final Artist artist= (Artist) getItem(position);
        if(convertView==null){
            if(layoutInflater==null){
                layoutInflater=LayoutInflater.from(context);
            }
            convertView=layoutInflater.inflate(R.layout.view_holder_artist,parent,false);
            artistViewHolder=new ArtistViewHolder(convertView);
            convertView.setTag(artistViewHolder);

        }
        artistViewHolder= (ArtistViewHolder) convertView.getTag();
        Picasso.with(convertView.getContext()).load(Constant.DOMAIN+"/static/upload/"+artist.getPhoto()).into(artistViewHolder.imageView);
        artistViewHolder.textView.setText("人气值:" + artist.getPopularity() + "");

        artistViewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArtistDetailActivity.start(context, artist);
            }
        });

        artistViewHolder.realNameTextView.setText(artist.getRealName());

        return convertView;
    }


    class ArtistViewHolder{

        View rootView;
        ImageView imageView;
        TextView textView,realNameTextView;


        public ArtistViewHolder(View rootView){

            this.rootView=rootView;

            imageView= (ImageView) rootView.findViewById(R.id.image_view_artist_cover);
            textView= (TextView) rootView.findViewById(R.id.text_view_artist);
            realNameTextView= (TextView) rootView.findViewById(R.id.text_view_name);




        }
    }
}
