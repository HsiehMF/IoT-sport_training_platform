package com.example.administrator.myconnet.Function.Public;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.MainActivity;
import com.example.administrator.myconnet.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class SubmitTrainPlan extends AppCompatActivity {

    Bundle bundle = new Bundle();
    String SUB = "sub_item";
    String TIMES = "item_times";
    String TIME = "time";
    String NOTE = "note";
    String SAVE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_train_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("送出訓練計畫");
        toolbar_title.setTypeface(typeface);

        TextView textView1 = (TextView) findViewById(R.id.textView52);
        TextView textView2 = (TextView) findViewById(R.id.tv_submit_train_plan_name);
        textView1.setTypeface(typeface, typeface.BOLD);
        textView2.setTypeface(typeface, typeface.BOLD);

        Button button1 = (Button) findViewById(R.id.button29);
        Button button2 = (Button) findViewById(R.id.button30);
        button1.setTypeface(typeface);
        button2.setTypeface(typeface);

        bundle = getIntent().getExtras();
        int count = bundle.getInt("count");

        String schedule_name = bundle.getString("schedule_name");
//        String date = bundle.getString("date");
        TextView tv_submit_train_plan_name = (TextView) findViewById(R.id.tv_submit_train_plan_name);
        tv_submit_train_plan_name.setText(schedule_name);
//        日期 群組 放在框框裡

        for (int i = 1 ; i <= count ; i++) {

            String ID = String.valueOf(i);
            String main_item = bundle.getString(ID);
            String sub_item = bundle.getString(ID + SUB);
            String item_times = bundle.getString(ID + TIMES);
            String time = bundle.getString(ID + TIME);

            LinearLayout linear_layout_submit_train_plan = (LinearLayout) findViewById(R.id.linear_layout_submit_train_plan);
            LinearLayout.LayoutParams tv_LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout linearLayout1 = new LinearLayout(this);
            linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout1.setBackgroundColor(Color.parseColor("#D7E4BD"));

            TextView NUMBER = new TextView(this);
            TextView textView_1 = new TextView(this);
            TextView textView_2 = new TextView(this);
            TextView textView_3 = new TextView(this);
            TextView textView_4 = new TextView(this);

            NUMBER.setText("項目" + ID + " : ");
            NUMBER.setTextColor(Color.BLACK);
            textView_1.setText(main_item);
            textView_2.setText(sub_item + "公尺");
            textView_3.setText(item_times + "組");
            textView_4.setText(time + "秒");
            tv_LLParams.setMargins(40, 15, 0, 15);
            NUMBER.setLayoutParams(tv_LLParams);
            textView_1.setLayoutParams(tv_LLParams);
            textView_2.setLayoutParams(tv_LLParams);
            textView_3.setLayoutParams(tv_LLParams);
            textView_4.setLayoutParams(tv_LLParams);
            linearLayout1.addView(NUMBER);
            linearLayout1.addView(textView_1);
            linearLayout1.addView(textView_2);
            linearLayout1.addView(textView_3);
            linearLayout1.addView(textView_4);

            assert linear_layout_submit_train_plan != null;
            linear_layout_submit_train_plan.addView(linearLayout1);

        }
    }

    // 儲存計畫
    public void save_train_plan(View view) {

        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        alertDialogFragment.show(fragmentManager,"alert");

    }

    // 放棄計畫
    public void giveUp(View view) {

        GiveUpAlertDialogFragment giveUpAlertDialogFragment = new GiveUpAlertDialogFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        giveUpAlertDialogFragment.show(fragmentManager,"alert");

    }

    //設定提問視窗
    public static class GiveUpAlertDialogFragment
            extends DialogFragment implements DialogInterface.OnClickListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle("Exit")
                    .setIcon(R.drawable.ic_logout_black_24dp)
                    .setMessage("您所輸入的資料也將遺失，是否放棄?")
                    .setPositiveButton("是", this)
                    .setNegativeButton("否", this)
                    .create();
        }

        //執行選擇的選項

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    SelectCourse.instance.finish();
                    SelectCrowd.instance.finish();
                    CreateSelectDate.instance.finish();
                    CreateNewPlan.instance.finish();
                    EnterItemList.instance.finish();
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

    public class AlertDialogFragment
            extends DialogFragment implements DialogInterface.OnClickListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle("Exit")
                    .setIcon(R.drawable.ic_logout_black_24dp)
                    .setMessage("將送出訓練計畫給選手。是否儲存訓練計畫，以供日後使用?")
                    .setPositiveButton("儲存並送出", this)
                    .setNegativeButton("否，直接送出", this)
                    .create();
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {

            switch (which) {

                case DialogInterface.BUTTON_POSITIVE:
                    SAVE = "1";
                    try { create(SAVE); }
                    catch (ExecutionException | InterruptedException e) { e.printStackTrace(); }
                    SelectCourse.instance.finish();
                    SelectCrowd.instance.finish();
                    CreateSelectDate.instance.finish();
                    CreateNewPlan.instance.finish();
                    EnterItemList.instance.finish();
                    getActivity().finish();
                    Toast.makeText(getApplicationContext(), "新增課表成功，並儲存至常用課表", Toast.LENGTH_SHORT).show();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    SAVE = "0";
                    try { create(SAVE); }
                    catch (ExecutionException | InterruptedException e) { e.printStackTrace(); }
                    SelectCourse.instance.finish();
                    SelectCrowd.instance.finish();
                    CreateSelectDate.instance.finish();
                    CreateNewPlan.instance.finish();
                    EnterItemList.instance.finish();
                    getActivity().finish();
                    Toast.makeText(getApplicationContext(), "新增課表成功", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

    // 新增課表
    public void create(String SAVE) throws ExecutionException, InterruptedException {

        String method1 = "新增課表";
        String method2 = "連結項目與之間關係";
        String method3 = "建立歷史紀錄";

        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't exist");
        BackgroundTask_public backgroundTask_public1 = new BackgroundTask_public(this);
        String schedule_name = bundle.getString("schedule_name");
        String course_name = bundle.getString("course_name");
        String date = bundle.getString("date");
        String crowd_name = bundle.getString("crowd_name");
        String schedule_num = backgroundTask_public1.execute(method1, UID, schedule_name, course_name, crowd_name, date, SAVE).get();      // 從 BackgroundTask 中取得 schedule_num , 讓 Line 跟 Item 填入相同的 schedule_num

        int count = bundle.getInt("count");
            for (int i = 1 ; i <= count ; i++) {

                String ID = String.valueOf(i);
                String main_item = bundle.getString(ID);
                String sub_item = bundle.getString(ID + SUB);
                String item_times = bundle.getString(ID + TIMES);
                String time = bundle.getString(ID + TIME);
                String note = bundle.getString(ID + NOTE);

                // 沒有輸入時要給定 note 一個值 , 否則修改課表時會出現例外狀況
                if (note == null) {  note = "null";  }

                // 連結 Line 與 Item 之間的關係
                BackgroundTask_public backgroundTask_public2 = new BackgroundTask_public(this);
                backgroundTask_public2.execute(method2, schedule_num, main_item, sub_item, item_times, time, note);

            }

        BackgroundTask_public backgroundTask_public3 = new BackgroundTask_public(this);
        backgroundTask_public3.execute(method3, schedule_num, crowd_name, UID);

    }

}
