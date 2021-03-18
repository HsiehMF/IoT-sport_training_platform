package com.example.administrator.myconnet.Function.Reply;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.administrator.myconnet.Function.Course.NewCourse;
import com.example.administrator.myconnet.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarForCoach extends AppCompatActivity {

    private CalendarView cv;
    int Myear,Mmonth,Mday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_for_coach);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("選擇日期");

        cv = (CalendarView)findViewById(R.id.calendarView5);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Myear = year;
                Mmonth = month;
                Mday = dayOfMonth;
            }
        });

    }
    public void selection(View view) {
        String string;
        if(Myear == 0) {
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String strDate = sdFormat.format(date);
            string=strDate;
        } else {
            string = str(Myear) + "-" + str(Mmonth+1) + "-" + str(Mday);
        }

        Intent intent = new Intent();
        intent.setClass(this, NewCourseForCoach.class);
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        bundle.putString("curDay", string);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }

    public String str(int i){
        String str = "";
            if(i < 10) {
                str = "0"+i;
            } else {
                str += i;
            }
        return str;
    }

}
