package com.example.administrator.myconnet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webview extends AppCompatActivity {
    WebView mWebView;
    String[] idsToHide = { "section1", "section3", "section5" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mWebView=(WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);

                //run 'disableSection' for all divs to hide/disable
                for (String s : idsToHide) {
                    String surveyId = s;
                    view.loadUrl("javascript:disableSection('" + surveyId + "');");
                }
            }
        });
        //load webpage from assets
        mWebView.loadUrl("http://140.127.218.198:8080/webapp/Records/");
    }

    public  void onBackPressed(){
        if(mWebView.canGoBack()){
            mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            mWebView.getSettings().setBuiltInZoomControls(true);
            mWebView.goBack();
            return;
        }
        super.onBackPressed();
    }

}
