package com.leychina.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.leychina.R;
import com.leychina.model.Artist;
import com.leychina.value.Constant;
import com.leychina.widget.tabindicator.TouchyWebView;
import com.squareup.picasso.Picasso;

/**
 * Created by yuandunlong on 11/21/15.
 */
public class ArtistDetailActivity extends AppCompatActivity {

    TouchyWebView webview;
    Artist artist;
    ImageView imageView;
    TextView weight, height, name, birthday;

    public static void start(Context from, Artist artist) {
        Intent intent = new Intent(from, ArtistDetailActivity.class);
        intent.putExtra("artist", artist);
        from.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        this.artist = (Artist) intent.getSerializableExtra("artist");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_detail);
        webview = (TouchyWebView) findViewById(R.id.artist_webview);

        imageView = (ImageView) findViewById(R.id.image_view_artist_cover);

        Picasso.with(this).load(Constant.DOMAIN+"/static/upload/"+artist.getPhoto()).into(imageView);

        name = (TextView) findViewById(R.id.text_view_name);
        height = (TextView) findViewById(R.id.text_view_height);
        weight = (TextView) findViewById(R.id.text_view_weight);
        birthday= (TextView) findViewById(R.id.text_view_birthday);


        // name.setText(artist.get);
        weight.setText(artist.getWeight() + "");
        height.setText(artist.getHeight() + "");
        birthday.setText(artist.getBlood());
        webview.setWebChromeClient(new WebChromeClient());
        webview.getSettings().setDefaultTextEncodingName("utf-8");

        webview.loadDataWithBaseURL(Constant.DOMAIN, artist.getDescription(), "text/html", "utf-8","");
    }
}
