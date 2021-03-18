package com.example.administrator.myconnet.Function.Friends;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.myconnet.R;

public class QueryCrowd extends AppCompatActivity {

    Intent intent = new Intent();
    Bundle bundle = new Bundle();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_crowd);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("查看群組");
        toolbar_title.setTypeface(typeface);

        TextView textView1 = (TextView) findViewById(R.id.textView66);
        TextView textView2 = (TextView) findViewById(R.id.textView67);
        TextView textView3 = (TextView) findViewById(R.id.textView68);
        textView1.setTypeface(typeface,typeface.BOLD);
        textView2.setTypeface(typeface,typeface.BOLD);
        textView3.setTypeface(typeface,typeface.BOLD);

        Button button1 = (Button) findViewById(R.id.button42);
        Button button2 = (Button) findViewById(R.id.button43);
        button1.setTypeface(typeface);
        button2.setTypeface(typeface);

        bundle = getIntent().getExtras();   // 直接傳給下一頁
        String crowd_name = bundle.getString("crowd_name");
        EditText editText1 = (EditText) findViewById(R.id.et_query_crowd_course_name);
        editText1.setText(crowd_name);
        editText1.setTypeface(typeface);
        EditText editText2 = (EditText) findViewById(R.id.et_query_crowd_date);
        editText2.setTypeface(typeface);
        EditText editText3 = (EditText) findViewById(R.id.et_query_crowd_amount);
        editText3.setTypeface(typeface);

    }

    public void manageCrowdPlayers (View view) {

        bundle = getIntent().getExtras();   // 直接傳給下一頁
        intent.setClass(this, ManageCrowdPlayers.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
