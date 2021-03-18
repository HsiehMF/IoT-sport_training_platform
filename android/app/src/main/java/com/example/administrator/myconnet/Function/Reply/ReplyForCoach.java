package com.example.administrator.myconnet.Function.Reply;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.myconnet.Function.Course.BackgroundTask_talk;
import com.example.administrator.myconnet.R;

public class ReplyForCoach extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_for_coach);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("意見回覆");

    }

    private void init() {

        SharedPreferences sharedPreferences = getSharedPreferences("prefs",MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't exist");
        BackgroundTask_talk backgroundTask_talk = new BackgroundTask_talk(this);

    }

}
