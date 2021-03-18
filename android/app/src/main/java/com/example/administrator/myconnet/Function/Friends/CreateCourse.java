package com.example.administrator.myconnet.Function.Friends;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateCourse extends AppCompatActivity {

    Intent intent = new Intent();
    Bundle bundle = new Bundle();
    String[] crowd_amount_num = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("新增社團");
        toolbar_title.setTypeface(typeface);

        TextView textView1 = (TextView) findViewById(R.id.textView2);
        TextView textView2 = (TextView) findViewById(R.id.textView61);
        TextView textView3 = (TextView) findViewById(R.id.textView4);
        textView1.setTypeface(typeface,typeface.BOLD);
        textView2.setTypeface(typeface,typeface.BOLD);
        textView3.setTypeface(typeface,typeface.BOLD);

        EditText editText1 = (EditText) findViewById(R.id.et_create_course_name);
        EditText editText2 = (EditText) findViewById(R.id.et_create_course_info);
        editText1.setTypeface(typeface);
        editText2.setTypeface(typeface);

        Button button1 = (Button) findViewById(R.id.button4);
        Button button2 = (Button) findViewById(R.id.button5);
        button1.setTypeface(typeface);
        button2.setTypeface(typeface);

        bundle = getIntent().getExtras();

        Spinner spinner = (Spinner) findViewById(R.id.sp_create_course);
        ArrayAdapter<String> adapter_crowd_amount = new ArrayAdapter<String>(this,
                R.layout.spinner_style_for_advance_search, crowd_amount_num);
        spinner.setAdapter(adapter_crowd_amount);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String crowd_amount = parent.getSelectedItem().toString();
                bundle.putString("crowd_amount", crowd_amount);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void createCourseSubmit (View view) {

        EditText editText1 = (EditText) findViewById(R.id.et_create_course_name);
        EditText editText2 = (EditText) findViewById(R.id.et_create_course_info);
        String new_course_name = editText1.getText().toString();
        String new_course_info = editText2.getText().toString();

        if ( new_course_name.equals("") || new_course_info.equals("")) {

            Toast.makeText( this , "您有資料尚未填寫" , Toast.LENGTH_SHORT ).show();

        } else {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date curDate = new Date(System.currentTimeMillis()) ;
            String curDay = formatter.format(curDate);

            String method = "CreateCourse";
            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID", "UID doesn't exist");
            String crowd_amount = bundle.getString("crowd_amount");
            BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(this);
            backgroundTask_new_course.execute(method, UID, new_course_name, new_course_info, curDay, crowd_amount);

            intent.setClass(this, NewFriends.class);
            intent.putExtras(bundle);
            startActivity(intent);
            Toast.makeText( this , "建立成功" , Toast.LENGTH_SHORT ).show();
            finish();

        }
    }

    public void createCourseCancel (View view) {  finish(); }

}
