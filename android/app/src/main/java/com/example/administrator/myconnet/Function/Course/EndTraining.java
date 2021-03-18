package com.example.administrator.myconnet.Function.Course;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.Lobby2;
import com.example.administrator.myconnet.Lobby3;
import com.example.administrator.myconnet.R;

public class EndTraining extends AppCompatActivity {
    String grades="";
    String Date,UID;
    String [] x=new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_training);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        String filename=bundle.getString("filename");
        String []split=filename.split("_");
        UID=split[0];
        Date=split[1];
//        UID=bundle.getString("UID");
        x[0]="NULL";
        x[1]="NULL";
        x[2]="NULL";

        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("自評表");

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);    // 0 是外框， 2 是星星顏色
        RatingBar ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);
        LayerDrawable stars2 = (LayerDrawable) ratingBar2.getProgressDrawable();
        stars2.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);    // 0 是外框， 2 是星星顏色
        RatingBar ratingBar3 = (RatingBar) findViewById(R.id.ratingBar3);
        LayerDrawable stars3 = (LayerDrawable) ratingBar3.getProgressDrawable();
        stars3.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);    // 0 是外框， 2 是星星顏色

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String text = rating + " star(s)";
                x[0]=String.valueOf(rating);
                Toast.makeText(EndTraining.this, text, Toast.LENGTH_SHORT).show();
            }
        });
        ratingBar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String text = rating + " star(s)";
                x[1]=String.valueOf(rating);
                Toast.makeText(EndTraining.this, text, Toast.LENGTH_SHORT).show();
            }
        });
        ratingBar3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String text = rating + " star(s)";
                x[2]=String.valueOf(rating);
                Toast.makeText(EndTraining.this, text, Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void send(View view){
        String flag="No";
//        Toast.makeText(EndTraining.this, grades, Toast.LENGTH_SHORT).show();
        grades=x[0]+","+x[1]+","+x[2];
        for(String a:x){
            if(a.equals("NULL")){
                break;
            }else {
                flag="Yes";
            }
        }
        if (flag.equals("No")){
            Toast.makeText(EndTraining.this, "評分未完成", Toast.LENGTH_SHORT).show();
        }else {
            String filename=UID+"_"+Date+"_"+grades;
            Toast.makeText(EndTraining.this, filename, Toast.LENGTH_SHORT).show();
            selfevaluation selfevaluation=new selfevaluation(filename);
            Intent intent = new Intent(Intent.ACTION_MAIN);
            Bundle bundle = new Bundle();
            bundle.putString("UID", UID);
            intent.putExtras(bundle);
            intent.setClass(this, Lobby3.class);
            startActivity(intent);
            finish();
        }

//        Toast.makeText(EndTraining.this, UID+"\t"+Date+"\t"+grades, Toast.LENGTH_SHORT).show();
    }
    public  void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        Bundle bundle = new Bundle();
        bundle.putString("curDay", Date);
        intent.putExtras(bundle);
        intent.setClass(this, NewCourse.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
