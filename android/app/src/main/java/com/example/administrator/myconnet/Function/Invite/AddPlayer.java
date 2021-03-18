package com.example.administrator.myconnet.Function.Invite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.Function.Friends.BackgroundTask_new_course;
import com.example.administrator.myconnet.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class AddPlayer extends AppCompatActivity {

    Bundle bundle = new Bundle();
    ArrayAdapter<String> adapter;
    ArrayList<String> selected_player = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(typeface);
        toolbar_title.setText("管理加入選手");

        bundle = getIntent().getExtras();
        String x = bundle.getString("apply_player");
        String[] apply_player = x.split(",");

        if (apply_player[0].equals("")) {

            ListView listView = (ListView) findViewById(R.id.lv_add_player);
            listView.setVisibility(listView.INVISIBLE);
            TextView textView = (TextView) findViewById(R.id.add_player_none_player);
            textView.setVisibility(textView.VISIBLE);
            textView.setTypeface(typeface);

        } else {
            restart restart = new restart(this);
            restart.start();
        }

    }

    private void displayListView( String[] str ) {

        ListView listView = (ListView) findViewById(R.id.lv_add_player);
        adapter = new ArrayAdapter<String>(this,R.layout.ctv_choose_player ,R.id.ctv_choose_player, str);       // 使用ctv_choose_player的客製化listView
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                if (selected_player.contains(selectedItem)) {
                    selected_player.remove(selectedItem);       // selected_player 內有重複資料就移除
                } else { selected_player.add(selectedItem); }
            }
        });


    }

    public void accept(View view) {

        System.out.println(selected_player);
        String method = "核准選手加入社團";
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't founded");     // 取得登入的UID
        String course_name = bundle.getString("course_name");

        for (int i = 0 ; i < selected_player.size() ; i++ ) {
            BackgroundTask_course backgroundTask_course = new BackgroundTask_course(this);
            backgroundTask_course.execute(method, UID, course_name, selected_player.get(i));
        }

        Toast.makeText(this, "接受成功", Toast.LENGTH_SHORT).show();

        restart restart = new restart(this);
        restart.start();

    }

    public void refuse(View view) {

        String method = "拒絕選手加入社團";
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't founded");     // 取得登入的UID
        String course_name = bundle.getString("course_name");

        for (int i = 0 ; i < selected_player.size() ; i++ ) {
            BackgroundTask_course backgroundTask_course = new BackgroundTask_course(this);
            backgroundTask_course.execute(method, UID, course_name, selected_player.get(i));
        }

        Toast.makeText(this, "拒絕成功", Toast.LENGTH_SHORT).show();

        restart restart = new restart(this);
        restart.start();

    }

    public class restart extends Thread {

        Context context;

        public restart(Context context) {
            this.context = context;
        }

        @Override
        public void run() {

            super.run();

            String method = "catch_apply_player";
            String course_name = bundle.getString("course_name");
            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID", "UID doesn't founded");         // 取得登入的UID
            BackgroundTask_catch backgroundTask_catch = new BackgroundTask_catch(getApplicationContext());     // 取得審核的選手名單
                try {
                    String x = backgroundTask_catch.execute(method, course_name, UID).get();
                    String[] crowd_list = x.split(",");

                    handler.obtainMessage(1, 4, -1, crowd_list).sendToTarget();
                } catch (InterruptedException | ExecutionException e) {  e.printStackTrace();  }

        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 1) {
                String[] str = (String[]) msg.obj;
                displayListView(str);
            }
            super.handleMessage(msg);
        }
    };

}
