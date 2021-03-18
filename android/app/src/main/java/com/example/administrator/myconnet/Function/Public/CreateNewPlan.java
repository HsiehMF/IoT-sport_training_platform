package com.example.administrator.myconnet.Function.Public;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class CreateNewPlan extends AppCompatActivity {

    Intent intent = new Intent();
    Bundle bundle = new Bundle(); // 儲存 catch_course 的值
    public static CreateNewPlan instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_create_new_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("新增訓練計畫");
        toolbar_title.setTypeface(typeface);

        TextView textView = (TextView) findViewById(R.id.textView10);
        textView.setTypeface(typeface, typeface.BOLD);
        EditText editText = (EditText) findViewById(R.id.et_create_new_plan_name);
        editText.setTypeface(typeface);
        Button button = (Button) findViewById(R.id.button26);
        button.setTypeface(typeface);

        init();

    }

    @Override
    public void finish() {
        super.finish();
        instance = null;
    }

    private void init() {

        bundle = getIntent().getExtras();
        String crowd_name = bundle.getString("crowd_name");
        String date = bundle.getString("date");

        EditText editText = (EditText) findViewById(R.id.et_create_new_plan_name);
        editText.setHint(date + " - " + crowd_name);

    }

    public void selectCourse(View view) throws ExecutionException, InterruptedException {

        // 儲存欲新增課表之名稱
        EditText editText = (EditText) findViewById(R.id.et_create_new_plan_name);
        String et_schedule_name = editText.getText().toString();
        String crowd_name = bundle.getString("crowd_name");

            if (et_schedule_name.equals("")) {
                // 若沒有輸入課表名稱 , 用預設課表名稱
                String date = bundle.getString("date");
                bundle.putString("schedule_name", date + " - " + crowd_name);
            } else {
                // 若有輸入課表名稱 , 就用此值
                bundle.putString("schedule_name", et_schedule_name);
            }

        // 檢查課表名稱有無重複 , 以及同天群組不能出兩張課表
        String method = "檢查課表機制";
        String schedule_name = bundle.getString("schedule_name");   // 取得 schedule_name
        String course_name = bundle.getString("course_name");
        String date = bundle.getString("date");
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't exist");
        BackgroundTask_public backgroundTask_public = new BackgroundTask_public(this);
        String x = backgroundTask_public.execute(method, UID, schedule_name, course_name, crowd_name, date).get();
        String[] MESSAGE = x.split(",");

        if (!MESSAGE[0].equals("通過")) {
            int i = 0;
            while (i < MESSAGE.length) {
                Toast.makeText(this, MESSAGE[i], Toast.LENGTH_SHORT).show();
                i++;
            }
        } else {
            intent.setClass(this, EnterItemList.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }

}
