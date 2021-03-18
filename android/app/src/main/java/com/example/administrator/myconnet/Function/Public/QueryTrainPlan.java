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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.BackgroundTask;
import com.example.administrator.myconnet.Function.Friends.Country;
import com.example.administrator.myconnet.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class QueryTrainPlan extends AppCompatActivity {

    Intent intent = new Intent();
    Bundle bundle = new Bundle();
    MyCustomAdapter dataAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_train_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(typeface);
        toolbar_title.setText("選擇欲查看之訓練計畫");

        TextView textView = (TextView) findViewById(R.id.query_train_plan_title);
        textView.setTypeface(typeface,typeface.BOLD);

        bundle = getIntent().getExtras();
        String x = bundle.getString("schedule_list");   // 課表列表
        String[] schedule_list = x.split(",");

        if (schedule_list[0].equals("今日無訓練課表")) {

            String DATE = bundle.getString("date");
            TextView TITLE = (TextView) findViewById(R.id.query_train_plan_title);
            TITLE.setText(DATE + "    0個訓練計畫 ");
            Toast.makeText(this, schedule_list[0], Toast.LENGTH_SHORT).show();

        } else {
            restart restart = new restart(this);
            restart.start();
        }

    }

    private void displayListView(String[] schedule_list) {

        ArrayList<String> SCHEDULE_ID = new ArrayList<>();
        ArrayList<String> NAME = new ArrayList<>();

        for (String x : schedule_list) {
            String[] main = x.split(",");
            SCHEDULE_ID.add(main[0]);
            NAME.add(main[1]);
        }

        int num = schedule_list.length;
        String DATE = bundle.getString("date");
        TextView textView = (TextView) findViewById(R.id.query_train_plan_title);
        textView.setText(DATE + "    " + num + "個訓練計畫 ");

        ArrayList<Country> countryArrayList1 = new ArrayList<Country>();
        ArrayList<Country> countryArrayList2 = new ArrayList<Country>();

        for (int i = 0; i < NAME.size(); i++) {
            Country country1 = new Country(NAME.get(i), false);
            Country country2 = new Country(SCHEDULE_ID.get(i), false);
            countryArrayList1.add(country1);
            countryArrayList2.add(country2);
        }

        //create an ArrayAdapter from the String Array
        dataAdapter = new MyCustomAdapter(this, R.layout.country_info, countryArrayList1, countryArrayList2);
        ListView listView = (ListView) findViewById(R.id.lv_query_train_plan);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Country country = (Country) parent.getItemAtPosition(position);
                String schedule_name = country.getName();  // 取得選取的課程 , 跑 manage_course();
                try { queryItemList(schedule_name); }
                catch (ExecutionException | InterruptedException e) { e.printStackTrace(); }

            }
        });

    }

    private class MyCustomAdapter extends ArrayAdapter<Country> {

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        private ArrayList<Country> countryArrayList1;
        private ArrayList<Country> countryArrayList2;

        public MyCustomAdapter(Context context, int textViewResourceId, ArrayList<Country> countryList1, ArrayList<Country> countryList2) {
            super(context, textViewResourceId, countryList1);
            this.countryArrayList1 = new ArrayList<Country>();
            this.countryArrayList2 = new ArrayList<Country>();
            this.countryArrayList1.addAll(countryList1);
            this.countryArrayList2.addAll(countryList2);
        }

        private class ViewHolder {
            CheckBox name;      // holder 的 CheckBox , 用來放客製化的 CheckBox
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.public_info, null);

                holder = new ViewHolder();
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);  // 將 checkBox1 設給 holder.name
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
            holder.name.setText(country.getName());
            holder.name.setTag(country);
            holder.name.setTypeface(typeface, typeface.BOLD);

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

            String method = "查詢訓練課表";
            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID", "UID doesn't exist");
            String course_name = bundle.getString("course_name");
            String crowd_name = bundle.getString("crowd_name");
            String string = bundle.getString("date");
            BackgroundTask_public backgroundTask_public = new BackgroundTask_public(getApplicationContext());
            try {
                String x = backgroundTask_public.execute(method, UID, course_name, crowd_name, string).get();
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

    public void PlanDeleteAlert(View view) {

        DeleteAlertDialogFragment deleteAlertDialogFragment = new DeleteAlertDialogFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        deleteAlertDialogFragment.show(fragmentManager, "alert");

    }

    private void deleteSchedule() {

        ArrayList<String> delete_course_list = new ArrayList<String>();

        ArrayList<Country> countryList1 = dataAdapter.countryArrayList1;
        ArrayList<Country> countryList2 = dataAdapter.countryArrayList2;
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

        // 更新功能
        restart restart = new restart(this);
        restart.start();

    }

    private void queryItemList(String schedule_name) throws ExecutionException, InterruptedException {

        String method = "訓練課表細項";
        String date = bundle.getString("date");
        BackgroundTask_public backgroundTask_public = new BackgroundTask_public(this);
        String train_item_detail = backgroundTask_public.execute(method, schedule_name, date).get();

        System.out.println(train_item_detail);

        intent.setClass(this, AlterItemList.class);
        bundle.putString("train_item_detail", train_item_detail);
        bundle.putString("schedule_name", schedule_name);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}
