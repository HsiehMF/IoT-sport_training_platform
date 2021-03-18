package com.example.administrator.myconnet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import com.example.administrator.myconnet.Function.Course.NewCourse;

import java.text.SimpleDateFormat;
import java.util.Date;

public class calender extends AppCompatActivity {
    private CalendarView cv;
    int Myear,Mmonth,Mday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        cv=(CalendarView)findViewById(R.id.calendarView2);
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
        if(Myear==0){
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String strDate = sdFormat.format(date);
            string=strDate;
        }else{
            string = str(Myear) + "-" + str(Mmonth+1) + "-" + str(Mday);
        }

        Intent intent=new Intent();
        intent.setClass(calender.this, NewCourse.class);
        Bundle bundle = new Bundle();
        bundle.putString("curDay", string);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }

    public String str(int i){
        String str = "";
        if(i<10){
         str= "0"+i;
        }else {
            str+=i;
        }
        return str;
    }

}
