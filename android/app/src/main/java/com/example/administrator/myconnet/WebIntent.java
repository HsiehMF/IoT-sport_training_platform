package com.example.administrator.myconnet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.myconnet.Function.Friends.BackgroundTask_new_course;
import com.example.administrator.myconnet.Function.Friends.NewFriends;
import com.example.administrator.myconnet.Function.Public.Public;

import java.util.concurrent.ExecutionException;

public class WebIntent extends AppCompatActivity {

    WebView mWebView;
    String[] idsToHide = { "section1", "section3", "section5" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_intent);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.webview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.quick_CreateNewSchedule) {

            Intent intent = new Intent();
            intent.setClass(this, Public.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.quick_ManagePlayer) {

            String method = "ExistedCourse";
            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID", "UID doesn't exist");
            BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(this);

                try {
                    String course_name = backgroundTask_new_course.execute(method, UID).get();
                    Intent intent = new Intent();
                    intent.setClass(this, NewFriends.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("course_name", course_name);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                }
                catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }

        }

        return super.onOptionsItemSelected(item);
    }

}
