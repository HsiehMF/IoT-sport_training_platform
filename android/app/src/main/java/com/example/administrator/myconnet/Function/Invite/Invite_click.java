package com.example.administrator.myconnet.Function.Invite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.myconnet.R;

import java.util.concurrent.ExecutionException;

public class Invite_click extends AppCompatActivity {


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_click);

        TextView tv_name = (TextView) findViewById(R.id.tv_invite_click_name);
        TextView tv_info = (TextView) findViewById(R.id.tv_invite_click_info);
        Bundle bundle = getIntent().getExtras();
        String selected_student_name = bundle.getString("selected_student_name");
        String method = "selected_student_information";
        BackgroundTask_course backgroundTask_course = new BackgroundTask_course(this);
        try {

            String x = backgroundTask_course.execute(method,selected_student_name).get();
            String[] info = x.split(",");
            tv_name.setText(info[0]);
            tv_info.setText(info[1]);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Button bt_apply = (Button) findViewById(R.id.bt_invite_click_apply);
        Button bt_cancel = (Button) findViewById(R.id.bt_invite_click_cancel);

        assert bt_apply != null;
        bt_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle1 = getIntent().getExtras();
                String student_name = bundle1.getString("selected_student_name");
                String course_name = bundle1.getString("selected_course_name");
                SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
                String UID = sharedPreferences.getString( "UID" , "UID doesn't existed" );
                System.out.println(student_name + course_name);

                String method = "Invite_click_add";
                BackgroundTask_course backgroundTask_course1 = new BackgroundTask_course(getApplicationContext());
                backgroundTask_course1.execute(method, course_name, student_name, UID);      // 利用教練 UID 與 所點擊的課程名稱，找到該課程編號，更改學生 course_num

                //startActivity(new Intent(getApplicationContext(),CoachAddStudent.class));       // 未來要改生命週期
            }
        });
        assert bt_cancel != null;
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle2 = getIntent().getExtras();
                String student_name = bundle2.getString("selected_student_name");
                System.out.println(student_name);
                String method = "Invite_click_cancel";
                BackgroundTask_course backgroundTask_course1 = new BackgroundTask_course(getApplicationContext());
                backgroundTask_course1.execute(method,student_name);

            }
        });
    }
}
