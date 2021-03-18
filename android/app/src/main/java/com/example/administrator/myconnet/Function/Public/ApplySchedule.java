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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.Function.Friends.BackgroundTask_new_course;
import com.example.administrator.myconnet.Function.Friends.Country;
import com.example.administrator.myconnet.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class ApplySchedule extends AppCompatActivity {

    Intent intent = new Intent();
    Bundle bundle = new Bundle();
    ArrayAdapter<String> adapter;
    MyCustomAdapter dataAdapter = null;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(typeface);
        toolbar_title.setText("選擇欲套用之訓練計畫");

        TextView textView = (TextView) findViewById(R.id.apply_schedule_title);
        textView.setTypeface(typeface,typeface.BOLD);
        Button button = (Button) findViewById(R.id.button46);
        button.setTypeface(typeface);

        Spinner spinner = (Spinner) findViewById(R.id.sp_classify);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.classify));
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getSelectedItem().toString();
                try {
                    Sort(selected);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }

                SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("CLASSIFY", selected);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bundle = getIntent().getExtras();
        restart restart = new restart(this, null);
        restart.start();

    }

    private void displayListView(String[] SAVED_SCHEDULE) {

        ArrayList<String> SCHEDULE_ID = new ArrayList<>();
        ArrayList<String> NAME = new ArrayList<>();
        ArrayList<String> DATE = new ArrayList<>();
        ArrayList<String> COURSE_NAME = new ArrayList<>();
        ArrayList<String> CROWD_NAME = new ArrayList<>();

        for (String m : SAVED_SCHEDULE) {
            String[] main = m.split(",");
            SCHEDULE_ID.add(main[0]);
            NAME.add(main[1]);
            DATE.add(main[2]);
            COURSE_NAME.add(main[3]);
            CROWD_NAME.add(main[4]);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate = new Date(System.currentTimeMillis()) ; // 獲取當前時間
        String apply_schedule_title = formatter.format(curDate);

        TextView textView = (TextView) findViewById(R.id.apply_schedule_title);
        textView.setText("套用至 " + apply_schedule_title);

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
        ListView listView = (ListView) findViewById(R.id.lv_apply_schedule);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Country country = (Country) parent.getItemAtPosition(position);
                String schedule_name = country.getName();  // 取得選取的課程 , 跑 manage_course();
                try {
                    applyItemList(schedule_name);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
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
            holder.name.setTag(country);
            holder.name.setTypeface(typeface, typeface.BOLD);
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

    public class AlertDialogFragment
            extends DialogFragment implements DialogInterface.OnClickListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.ic_logout_black_24dp)
                    .setMessage("確認送出課表?")
                    .setPositiveButton("是", this)
                    .setNegativeButton("否", this)
                    .create();
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    try {
                        if (apply_schedule()) {
                            getActivity().finish();
                        }
                    }       // 如果為真 , 結束該頁面
                    catch (ExecutionException | InterruptedException e) { e.printStackTrace(); }
                    Toast.makeText(getApplicationContext(), "套用成功", Toast.LENGTH_SHORT).show();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.cancel();
                    break;
                default:
                    break;
            }
        }
    }

    public class restart extends Thread {

        Context context;
        String selected;

        public restart(Context context, String selected) {

            this.context = context;
            this.selected = selected;
        }

        @Override
        public void run() {

            super.run();

            String method = "查詢已儲存訓練課表";
            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID", "UID doesn't exist");
            String course_name = bundle.getString("course_name");
            if (selected == null) {  selected = "預設";  }
            BackgroundTask_public backgroundTask_public = new BackgroundTask_public(getApplicationContext());
                try {
                    String x = backgroundTask_public.execute(method, UID, course_name, selected).get();
                    String[] SAVED_SCHEDULE = x.split("&");
                    handler.obtainMessage(1, 4, -1, SAVED_SCHEDULE).sendToTarget();
                } catch (InterruptedException | ExecutionException e) {  e.printStackTrace();  }

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

    public void submit_apply_plan(View view) {

        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        alertDialogFragment.show(fragmentManager,"alert");

    }

    public void deleteAlert(View view) {

        DeleteAlertDialogFragment deleteAlertDialogFragment = new DeleteAlertDialogFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        deleteAlertDialogFragment.show(fragmentManager, "alert");

    }

    private void deleteSchedule() {

        ArrayList<String> delete_course_list = new ArrayList<String>();

        ArrayList<Country> countryList1 = dataAdapter.countryArrayList1;
        ArrayList<Country> countryList2 = dataAdapter.countryArrayList5;
            for(int i=0;i<countryList1.size();i++){
                Country country = countryList1.get(i);
                Country id = countryList2.get(i);
                if(country.isSelected()){
                    delete_course_list.add(id.getName());
                    System.out.println(id.getName());
                }
            }

        String method = "刪除課表";
        for (int i = 0 ; i < delete_course_list.size(); i++) {
            BackgroundTask_integrate backgroundTask_integrate = new BackgroundTask_integrate(this);
            backgroundTask_integrate.execute(method, delete_course_list.get(i));
        }

        // 更新功能
        restart restart = new restart(this, null);
        restart.start();

    }

    private boolean apply_schedule() throws ExecutionException, InterruptedException {

        ArrayList<String> apply_schedule_list = new ArrayList<String>();
        ArrayList<Country> countryList = dataAdapter.countryArrayList1;
        for (int i = 0; i < countryList.size(); i++) {
            Country country = countryList.get(i);
            if (country.isSelected()) {
                apply_schedule_list.add(country.getName());
            }
        }

        if (apply_schedule_list.size() == 0) {

            Toast.makeText(this, "您尚未選擇任何課表", Toast.LENGTH_SHORT).show();
            return false;

        } else {

            String method1 = "新增課表-套用";
            String method2 = "查詢舊課表編號-套用";
            String method3 = "連結舊課表項目與新課表之間的關係";

            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID", "UID doesn't exist");
            String course_name = bundle.getString("course_name");
            String crowd_name = bundle.getString("crowd_name");
            String date = bundle.getString("date");     // 課表訓練之日期 - 選手何時訓練

            BackgroundTask_public backgroundTask_public1 = new BackgroundTask_public(this);     // 得到新增課表之編號
            String schedule_num = backgroundTask_public1.execute(method1, UID, apply_schedule_list.get(0), course_name, crowd_name, date).get();
            BackgroundTask_public backgroundTask_public2 = new BackgroundTask_public(this);     // 得到舊有課程編號和該訓練細項
            String old_schedule_num = backgroundTask_public2.execute(method2, UID, apply_schedule_list.get(0), course_name).get();
            BackgroundTask_public backgroundTask_public3 = new BackgroundTask_public(this);     // 連結其關係
            backgroundTask_public3.execute(method3, old_schedule_num, schedule_num);

            return true;
        }

    }

    private void applyItemList(String schedule_name) throws ExecutionException, InterruptedException {

        String method = "訓練課表細項-套用";
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't exist");
        String course_name = bundle.getString("course_name");
        BackgroundTask_public backgroundTask_public = new BackgroundTask_public(this);
        String train_item_detail = backgroundTask_public.execute(method, UID, schedule_name, course_name).get();    // 無法用 date 判斷 , 改用 course_num 判斷
        bundle.putString("train_item_detail", train_item_detail);
        bundle.putString("schedule_name", schedule_name);

        intent.setClass(this, ApplyAlterItemList.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void Sort(String selected) throws ExecutionException, InterruptedException {

        restart restart = new restart(this, selected);
        restart.start();

    }

}
