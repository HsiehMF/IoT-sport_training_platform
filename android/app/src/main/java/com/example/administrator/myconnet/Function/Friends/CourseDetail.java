package com.example.administrator.myconnet.Function.Friends;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.KeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.MainActivity;
import com.example.administrator.myconnet.R;
import java.util.concurrent.ExecutionException;

public class CourseDetail extends AppCompatActivity {

    Bundle bundle = new Bundle();
    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(typeface);
        toolbar_title.setText("查看社團");

        bundle = getIntent().getExtras();
        String x = bundle.getString("course_detail");
        String[] detail = x.split("§");

        // 設定字體
        TextView textView1 = (TextView) findViewById(R.id.textView9);
        TextView textView2 = (TextView) findViewById(R.id.textView32);
        TextView textView3 = (TextView) findViewById(R.id.textView33);
        TextView textView4 = (TextView) findViewById(R.id.textView34);
        TextView textView5 = (TextView) findViewById(R.id.textView35);

        textView1.setTypeface(typeface,typeface.BOLD);
        textView2.setTypeface(typeface,typeface.BOLD);
        textView3.setTypeface(typeface,typeface.BOLD);
        textView4.setTypeface(typeface,typeface.BOLD);
        textView5.setTypeface(typeface,typeface.BOLD);

        // 設定資料
        EditText editText1 = (EditText) findViewById(R.id.et_course_detail_name);
        EditText editText2 = (EditText) findViewById(R.id.et_course_detail_date);
        EditText editText3 = (EditText) findViewById(R.id.et_course_detail_coach);
        EditText editText4 = (EditText) findViewById(R.id.et_course_detail_info);
        EditText editText5 = (EditText) findViewById(R.id.et_course_detail_mount);

        editText1.setText(detail[0]);
        editText2.setText(detail[1]);
        editText3.setText(detail[2]);
        editText4.setText(detail[3]);
        editText5.setText(detail[4] + " 人 ");
        editText1.setTypeface(typeface);
        editText2.setTypeface(typeface);
        editText3.setTypeface(typeface);
        editText4.setTypeface(typeface);
        editText5.setTypeface(typeface);

        bundle.putString("course_name", editText1.getText().toString());    // 方便後面用

            if (detail[4].equals("0")) {    // 如果社團人數為0，按鈕設為不顯示
                Toast.makeText(this, "您的社團內無任何選手，趕快通知選手加入吧！", Toast.LENGTH_SHORT).show();
            }

        editText1.setTag(editText1.getKeyListener());
        editText2.setTag(editText2.getKeyListener());
        editText3.setTag(editText3.getKeyListener());
        editText4.setTag(editText4.getKeyListener());
        editText5.setTag(editText5.getKeyListener());

        editText1.setKeyListener(null);
        editText2.setKeyListener(null);
        editText3.setKeyListener(null);
        editText4.setKeyListener(null);
        editText5.setKeyListener(null);

        FloatingActionButton fab_edit = (FloatingActionButton) findViewById(R.id.float_edit_course_detail);
        FloatingActionButton fab_done = (FloatingActionButton) findViewById(R.id.float_done_course_detail);
        fab_edit.setVisibility(fab_edit.VISIBLE);
        fab_done.setVisibility(fab_done.INVISIBLE);  // 先把完成按鈕隱藏

        fab_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FloatingActionButton fab_edit = (FloatingActionButton) findViewById(R.id.float_edit_course_detail);
                FloatingActionButton fab_done = (FloatingActionButton) findViewById(R.id.float_done_course_detail);
                fab_edit.setVisibility(fab_edit.INVISIBLE);
                fab_done.setVisibility(fab_done.VISIBLE);

                EditText editText1 = (EditText) findViewById(R.id.et_course_detail_name);
                EditText editText4 = (EditText) findViewById(R.id.et_course_detail_info);

                editText1.setBackground(getResources().getDrawable(R.drawable.course_detail_edit));
                editText4.setBackground(getResources().getDrawable(R.drawable.course_detail_edit));

                editText1.setKeyListener((KeyListener) editText1.getTag());
                editText4.setKeyListener((KeyListener) editText4.getTag());

                Toast.makeText(getApplicationContext(), "編輯中", Toast.LENGTH_SHORT).show();
            }
        });

        fab_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FloatingActionButton fab_edit = (FloatingActionButton) findViewById(R.id.float_edit_course_detail);
                FloatingActionButton fab_done = (FloatingActionButton) findViewById(R.id.float_done_course_detail);
                fab_edit.setVisibility(fab_edit.VISIBLE);
                fab_done.setVisibility(fab_done.INVISIBLE);

                EditText editText1 = (EditText) findViewById(R.id.et_course_detail_name);
                EditText editText4 = (EditText) findViewById(R.id.et_course_detail_info);

                editText1.setBackground(getResources().getDrawable(R.drawable.course_detail_done));
                editText4.setBackground(getResources().getDrawable(R.drawable.course_detail_done));

                String method = "CourseDetail_edit";
                String name = editText1.getText().toString();
                String info = editText4.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
                String UID = sharedPreferences.getString("UID", "UID doesn't exist");

                String course_name = bundle.getString("course_name");
                BackgroundTask_new_course backgroundTask_new_course2 = new BackgroundTask_new_course(getApplicationContext(),editText1,editText4);
                backgroundTask_new_course2.execute(method, course_name, UID, name, info);       // course_name 原本課程名稱 , name 修改後課程名稱

                editText1.setKeyListener(null);
                editText4.setKeyListener(null);

                Toast.makeText(getApplicationContext(), "修改完成", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void delete(Context context) {

        String method = "CourseDetail_delete";
        String course_name = bundle.getString("course_name");
        String UID = bundle.getString("UID");
        BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(this);
        backgroundTask_new_course.execute( method , course_name , UID );

    }

    public class AlertDialogFragment
            extends DialogFragment implements DialogInterface.OnClickListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle("刪除")
                    .setIcon(R.drawable.ic_logout_black_24dp)
                    .setMessage("確定刪除此課表 ? ")
                    .setPositiveButton("YES", this)
                    .setNegativeButton("NO", this)
                    .create();
        }

        //執行選擇的選項

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:

                    delete(getContext());
                    getActivity().finish();

                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.cancel();
                    break;
                default:
                    break;
            }
        }
    }

    public void course_detail_check_student (View view) throws ExecutionException, InterruptedException {

        String course_name = bundle.getString("course_name");

        String method1 = "CourseDetail_group_list";
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't exist");
        BackgroundTask_new_course backgroundTask_new_course1 = new BackgroundTask_new_course(this);
        String group_list = backgroundTask_new_course1.execute( method1 , course_name , UID ).get();

        bundle.putString("group_list" , group_list);
        intent.setClass(this, ManageCrowd.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
