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

public class CreateSelectDate extends AppCompatActivity {

    Intent intent=new Intent();
    Bundle bundle = new Bundle();
    int Year,Month,Day;
    CalendarView cv;
    String NEW_DAY;
    public static CreateSelectDate instance = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_create_select_date);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("選擇日期");
        toolbar_title.setTypeface(typeface);

        TextView textView = (TextView) findViewById(R.id.textView81);
        textView.setTypeface(typeface);
        Button button = (Button) findViewById(R.id.button52);
        button.setTypeface(typeface);

        cv = (CalendarView) findViewById(R.id.calendarView6);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                Year = year;
                Month = month + 1;
                Day = dayOfMonth;
                if( Day < 10 ) { NEW_DAY = "0" + Day; }

                System.out.println(Year + "-" + Month + "-" + Day);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        instance = null;
    }

    public void getDate(View view) throws ExecutionException, InterruptedException {

        bundle = getIntent().getExtras();
        String string = null;

        if(Year == 0) {
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String strDate = sdFormat.format(date);
            string = strDate;
        } else if (Day < 10) {
            string = Year + "-" + Month + "-" + NEW_DAY;
        } else if (Day >= 10) {
            string = Year + "-" + Month + "-" + Day;
        }

        intent.setClass(this, CreateNewPlan.class);
        bundle.putString("date", string);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}