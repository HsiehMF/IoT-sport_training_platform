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
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.administrator.myconnet.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class ApplySelectDate extends AppCompatActivity {

    Intent intent=new Intent();
    Bundle bundle = new Bundle();
    int Year,Month,Day;
    CalendarView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_select_date);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("選擇日期");
        toolbar_title.setTypeface(typeface);

        TextView textView = (TextView) findViewById(R.id.textView75);
        textView.setTypeface(typeface);
        Button button = (Button) findViewById(R.id.button44);
        button.setTypeface(typeface);

        cv = (CalendarView) findViewById(R.id.calendarView4);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Year = year;
                Month = month + 1;
                Day = dayOfMonth;

                System.out.println(Year + "-" + Month + "-" + Day);
            }
        });
    }

    public void getDate(View view) throws ExecutionException, InterruptedException {

        bundle = getIntent().getExtras();
        String string;
        if(Year == 0) {
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String strDate = sdFormat.format(date);
            string = strDate;
        } else { string = Year + "-" + Month + "-" + Day; }

        String method = "查詢已儲存訓練課表";
        String selected = "預設";
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't exist");
        String course_name = bundle.getString("course_name");
        BackgroundTask_public backgroundTask_public = new BackgroundTask_public(this);
        String x = backgroundTask_public.execute(method, UID, course_name, selected).get();
        String[] SAVED_SCHEDULE = x.split("&");

        System.out.println(SAVED_SCHEDULE);

        intent.setClass(this, ApplySchedule.class);
        bundle.putStringArray("SAVED_SCHEDULE", SAVED_SCHEDULE);
        bundle.putString("date", string);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}