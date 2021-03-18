package com.example.administrator.myconnet.Function.Records;

        import android.content.Intent;
        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CalendarView;
        import android.widget.TextView;

        import com.example.administrator.myconnet.R;

        import java.text.SimpleDateFormat;
        import java.util.Date;

public class Calendar extends AppCompatActivity {
    private CalendarView cv;
    int Myear,Mmonth,Mday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("選擇日期");
        toolbar_title.setTypeface(typeface);

        Button button = (Button) findViewById(R.id.button6);
        button.setTypeface(typeface);

        cv=(CalendarView)findViewById(R.id.calendarView);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Myear=year;
                Mmonth=month;
                Mday=dayOfMonth;
            }
        });
    }
    public void Getdate(View view) {
        String string;
        if(Myear==0){
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy_MM_dd");
            Date date = new Date();
            String strDate = sdFormat.format(date);
            string=strDate;
        }else{
            string = Myear + "_" + Mmonth + "_" + Mday;
        }
        Intent intent=new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("folder", string);
        intent.setClass(Calendar.this, PlayerRecords.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
