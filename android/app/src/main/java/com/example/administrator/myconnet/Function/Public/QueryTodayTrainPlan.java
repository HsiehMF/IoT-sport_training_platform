package com.example.administrator.myconnet.Function.Public;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.BackgroundTask;
import com.example.administrator.myconnet.Function.Friends.BackgroundTask_new_course;
import com.example.administrator.myconnet.Function.Friends.Country;
import com.example.administrator.myconnet.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class QueryTodayTrainPlan extends AppCompatActivity {

    Intent intent = new Intent();
    Bundle bundle = new Bundle();

    MyCustomAdapter dataAdapter = null;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_today_train_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(typeface);
        toolbar_title.setText("選擇欲查看之訓練計畫");

        CheckBox checkBox = (CheckBox) findViewById(R.id.cb_query_today_train_plan);
        checkBox.setTypeface(typeface);

        bundle = getIntent().getExtras();
        String[] schedule_list  = bundle.getStringArray("TODAY_TRAIN_CROWD");

        if (schedule_list[0].equals("今日無訓練課表")) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date curDate = new Date(System.currentTimeMillis()) ; // 獲取當前時間
            String date = formatter.format(curDate);
            TextView TITLE = (TextView) findViewById(R.id.query_today_train_plan_title);
            TITLE.setText(date + "    0個訓練課表");
            TITLE.setTypeface(typeface, typeface.BOLD);

            Toast.makeText(this, schedule_list[0], Toast.LENGTH_SHORT).show();

        } else {
            restart restart = new restart(this);
            restart.start();
        }

    }

    private void displayListView(String[] schedule_list) {

        ArrayList<String> SCHEDULE_ID = new ArrayList<>();
        ArrayList<String> NAME = new ArrayList<>();
        ArrayList<String> DATE = new ArrayList<>();
        ArrayList<String> COURSE_NAME = new ArrayList<>();
        ArrayList<String> CROWD_NAME = new ArrayList<>();

        for (String x : schedule_list) {
            String[] main = x.split(",");
            SCHEDULE_ID.add(main[0]);
            NAME.add(main[1]);
            DATE.add(main[2]);
            COURSE_NAME.add(main[3]);
            CROWD_NAME.add(main[5]);
        }

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/wind.ttf");
        TextView textView = (TextView) findViewById(R.id.query_today_train_plan_title);
        textView.setText("今日訓練計畫 ");
        textView.setTypeface(typeface, typeface.BOLD);

        ArrayList<Country> countryArrayList1 = new ArrayList<Country>();
        ArrayList<Country> countryArrayList2 = new ArrayList<Country>();
        ArrayList<Country> countryArrayList3 = new ArrayList<Country>();
        ArrayList<Country> countryArrayList4 = new ArrayList<Country>();
        ArrayList<Country> countryArrayList5 = new ArrayList<Country>();

        for (int i = 0; i < NAME.size(); i++) {
            Country country1 = new Country(NAME.get(i), false);
            Country country2 = new Country(CROWD_NAME.get(i), false);
            Country country3 = new Country(DATE.get(i), false);
            Country country4 = new Country(COURSE_NAME.get(i), false);
            Country country5 = new Country(SCHEDULE_ID.get(i), false);
            countryArrayList1.add(country1);
            countryArrayList2.add(country2);
            countryArrayList3.add(country3);
            countryArrayList4.add(country4);
            countryArrayList5.add(country5);
        }

        //create an ArrayAdapter from the String Array
        dataAdapter = new MyCustomAdapter(this, R.layout.country_info, countryArrayList1, countryArrayList2, countryArrayList3, countryArrayList4, countryArrayList5);
        ListView listView = (ListView) findViewById(R.id.lv_query_today_train_plan);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        CheckBox checkBox = (CheckBox) findViewById(R.id.cb_query_today_train_plan);
        assert checkBox != null;
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAllSelected();                     // 全選 , count + 1
                dataAdapter.notifyDataSetChanged();     // 更新 Adapter , 重新跑一次 MyCustomAdapter , 就會檢查到 count 值的變動
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Country country = (Country) parent.getItemAtPosition(position);
                String schedule_name = country.getName();  // 取得選取的課程 , 跑 manage_course();
                try {  queryItemList(schedule_name);  }
                catch (ExecutionException | InterruptedException e) {  e.printStackTrace();  }

            }
        });

    }

    private class MyCustomAdapter extends ArrayAdapter<Country> {

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        private ArrayList<Country> countryArrayList1;
        private ArrayList<Country> countryArrayList2;
        private ArrayList<Country> countryArrayList3;
        private ArrayList<Country> countryArrayList4;
        private ArrayList<Country> countryArrayList5;

        public MyCustomAdapter(Context context, int textViewResourceId, ArrayList<Country> countryList1, ArrayList<Country> countryList2, ArrayList<Country> countryList3, ArrayList<Country> countryList4, ArrayList<Country> countryList5) {
            super(context, textViewResourceId, countryList1);
            this.countryArrayList1 = new ArrayList<Country>();
            this.countryArrayList2 = new ArrayList<Country>();
            this.countryArrayList3 = new ArrayList<Country>();
            this.countryArrayList4 = new ArrayList<Country>();
            this.countryArrayList5 = new ArrayList<Country>();
            this.countryArrayList1.addAll(countryList1);
            this.countryArrayList2.addAll(countryList2);
            this.countryArrayList3.addAll(countryList3);
            this.countryArrayList4.addAll(countryList4);
            this.countryArrayList5.addAll(countryList5);
        }

        private class ViewHolder {
            CheckBox name;      // holder 的 CheckBox , 用來放客製化的 CheckBox
            TextView textView1;
            TextView textView2;
            TextView textView3;
            TextView textView4;
            TextView textView5;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.custom_info, null);

                holder = new ViewHolder();
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);  // 將 checkBox1 設給 holder.name
                holder.textView1 = (TextView) convertView.findViewById(R.id.ci_crowd_name);
                holder.textView2 = (TextView) convertView.findViewById(R.id.ci_schedule_date);
                holder.textView3 = (TextView) convertView.findViewById(R.id.ci_course_name);
                holder.textView4 = (TextView) convertView.findViewById(R.id.tv_unit);
                holder.textView5 = (TextView) convertView.findViewById(R.id.tv_date);
                convertView.setTag(holder);     // 給 covertView 一個標誌 , 方便下次取得

                holder.name.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        Country country = (Country) cb.getTag();
                        country.setSelected(cb.isChecked());
                    }
                });

            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            Country country = countryArrayList1.get(position);        // countryList 陣列 , 利用 Adapter 取得位置
            Country country1 = countryArrayList2.get(position);
            Country country2 = countryArrayList3.get(position);
            Country country3 = countryArrayList4.get(position);
            holder.name.setText(country.getName());
            holder.name.setTypeface(typeface, typeface.BOLD);
            holder.name.setTag(country);
            holder.textView1.setText(country1.getName());
            holder.textView1.setTypeface(typeface);
            holder.textView2.setText(country2.getName());
            holder.textView3.setText(country3.getName());
            holder.textView3.setTypeface(typeface);
            holder.textView4.setTypeface(typeface);
            holder.textView5.setTypeface(typeface);

            if (count % 2 != 0)                                  // 檢查 count % 2 != 0 , 全部選項勾選 , 否則全部選項取消
            {
                holder.name.setChecked(true);                   // 將該 CheckBox 設為 勾選
                country.setSelected(true);                      // 將該 country  設為 true
            }
            else
            {
                holder.name.setChecked(false);                  // 將該 CheckBox 設為 取消
                country.setSelected(false);                     // 將該 country  設為 false
            }

            return convertView;

        }

    }

    public class restart extends Thread {

        Context context;

        public restart(Context context) {
            this.context = context;
        }

        @Override
        public void run() {

            super.run();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date curDate = new Date(System.currentTimeMillis()); // 獲取當前時間
            String date = formatter.format(curDate);

            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID", "UID doesn't founded");     // 取得登入的UID

            String method = "抓取今日該訓練之群組";
            BackgroundTask_integrate backgroundTask_integrate = new BackgroundTask_integrate(getApplicationContext());
                try {
                    String x = backgroundTask_integrate.execute(method, UID, date).get();
                    String[] TODAY_TRAIN_CROWD = x.split("&");
                    handler.obtainMessage(1, 4, -1, TODAY_TRAIN_CROWD).sendToTarget();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();  }
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 1) {
                String[] str = (String[]) msg.obj;
                displayListView(str);
            }
            super.handleMessage(msg);
        }
    };

    public class DeleteAlertDialogFragment
            extends DialogFragment implements DialogInterface.OnClickListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.ic_logout_black_24dp)
                    .setMessage("確認刪除課表?")
                    .setPositiveButton("是", this)
                    .setNegativeButton("否", this)
                    .create();
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    deleteSchedule();
                    Toast.makeText(getApplicationContext(), "刪除成功", Toast.LENGTH_SHORT).show();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.cancel();
                    break;
                default:
                    break;
            }
        }
    }

    public void TodayDeleteAlert(View view) {

        DeleteAlertDialogFragment deleteAlertDialogFragment = new DeleteAlertDialogFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        deleteAlertDialogFragment.show(fragmentManager, "alert");

    }

    public void checkAllSelected() { count = count + 1; }

    private void deleteSchedule() {

        ArrayList<String> delete_course_list = new ArrayList<String>();

        ArrayList<Country> countryList1 = dataAdapter.countryArrayList1;
        ArrayList<Country> countryList2 = dataAdapter.countryArrayList5;
        for(int i=0;i<countryList1.size();i++){
            Country country = countryList1.get(i);
            Country id = countryList2.get(i);
            if(country.isSelected()){
                delete_course_list.add(id.getName());
            }
        }

        String method = "刪除課表";
        for (int i = 0 ; i < delete_course_list.size(); i++) {
            BackgroundTask_integrate backgroundTask_integrate = new BackgroundTask_integrate(this);
            backgroundTask_integrate.execute(method, delete_course_list.get(i));
        }

        restart restart = new restart(this);
        restart.start();

    }

    private void queryItemList(String schedule_name) throws ExecutionException, InterruptedException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis()) ; // 獲取當前時間
        String date = formatter.format(curDate);

        String method = "訓練課表細項";
        BackgroundTask_public backgroundTask_public = new BackgroundTask_public(this);
        String train_item_detail = backgroundTask_public.execute(method, schedule_name, date).get();

        System.out.println(train_item_detail);
        bundle.putString("train_item_detail", train_item_detail);
        bundle.putString("schedule_name", schedule_name);

        intent.setClass(this, AlterItemList.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}
