package com.leychina.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.leychina.R;

/**
 * Created by yuandunlong on 11/6/15.
 */
public class ProjectDescriptionFragment extends Fragment {
    View rootView;
    WebView webView;
    int webViewHeight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView= inflater.inflate(R.layout.fragment_project_description,container,false);
        webView= (WebView) rootView.findViewById(R.id.web_view_project_detail);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new ProjectDescWebViewClient());
        webView.loadUrl("http://www.baidu.com");
        return  rootView;
    }

    class ProjectDescWebViewClient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            int height=view.getContentHeight();
            webViewHeight=height;
        }
    }
}
