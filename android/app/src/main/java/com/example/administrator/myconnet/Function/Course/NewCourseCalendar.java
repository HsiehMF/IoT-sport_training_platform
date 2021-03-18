package com.example.administrator.myconnet.Function.Course;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.administrator.myconnet.R;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewCourseCalendar extends AppCompatActivity {
    private CalendarView cv;
    int Myear,Mmonth,Mday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("選擇日期");
        toolbar_title.setTypeface(typeface);

        Button button = (Button) findViewById(R.id.button24);
        button.setTypeface(typeface);

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
        intent.setClass(this, NewCourse.class);
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