package com.example.administrator.myconnet.Function.Friends;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
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

import com.example.administrator.myconnet.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ManageCrowd extends AppCompatActivity {

    Intent intent = new Intent();
    Bundle bundle = new Bundle();
    MyCustomAdapter dataAdapter = null;
    ProgressDialog progressDialog;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Typeface noto = Typeface.createFromAsset(getAssets(),"fonts/notosanscjktcmedium.ttf");
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        setContentView(R.layout.activity_manage_crowd);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(typeface);
        toolbar_title.setText("管理群組");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOADING");
        progressDialog.show();

        // 設定特定字體
        TextView existed_crowd = (TextView) findViewById(R.id.textView80);
        CheckBox checkBox = (CheckBox) findViewById(R.id.cb_manage_crowd);
        existed_crowd.setTypeface(noto);
        checkBox.setTypeface(typeface);

        bundle = getIntent().getExtras();
        String course_name = bundle.getString("course_name");
        TextView textView = (TextView) findViewById(R.id.manage_crowd_course_name);
        textView.setText(course_name);      // 設定課程名稱
        textView.setTypeface(typeface,typeface.BOLD);

        restart restart = new restart(this);
        restart.start();

    }

    private void displayListView( String[] group_list ) {

        ArrayList<Country> countryList = new ArrayList<Country>();

        for (String name : group_list) {
            Country country = new Country(name , false);
            countryList.add(country);
        }

        //create an ArrayAdapter from the String Array
        dataAdapter = new MyCustomAdapter(this, R.layout.country_info, countryList);
        ListView listView = (ListView) findViewById(R.id.lv_manage_crowd);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        CheckBox checkBox = (CheckBox) findViewById(R.id.cb_manage_crowd);
        assert checkBox != null;
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAllSelected();                     // 全選 , count + 1
                dataAdapter.notifyDataSetChanged();     // 更新 Adapter , 重新跑一次 MyCustomAdapter , 就會檢查到 count 值的變動
            }
        });

        ImageButton imageButton1 = (ImageButton) findViewById(R.id.bt_manage_crowd_delete);
        assert imageButton1 != null;
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                alertDialogFragment.show(fragmentManager, "alert");

            }
        });

        ImageButton imageButton2 = (ImageButton) findViewById(R.id.ibt_create_crowd);
        assert imageButton2 != null;
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCrowd();
                dataAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Country country = (Country) parent.getItemAtPosition(position);
                String crowd_name = country.getName();  // 取得選取的課程 , 跑 queryCrowd();
                try {
                    queryCrowd(crowd_name);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public class AlertDialogFragment
            extends DialogFragment implements DialogInterface.OnClickListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle("刪除")
                    .setIcon(R.drawable.ic_logout_black_24dp)
                    .setMessage("確定刪除群組 ? ")
                    .setPositiveButton("YES", this)
                    .setNegativeButton("NO", this)
                    .create();
        }

        //執行選擇的選項

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    deleteCrowd();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.cancel();
                    break;
                default:
                    break;
            }
        }
    }

    private class MyCustomAdapter extends ArrayAdapter<Country> {

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        private ArrayList<Country> countryList;

        public MyCustomAdapter(Context context, int textViewResourceId, ArrayList<Country> countryList) {
            super(context, textViewResourceId, countryList);
            this.countryList = new ArrayList<Country>();
            this.countryList.addAll(countryList);
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
                convertView = vi.inflate(R.layout.new_friends_info, null);

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

            // 初始化 MyCustomAdapter
            Country country = countryList.get(position);        // countryList 陣列 , 利用 Adapter 取得位置
            holder.name.setText(country.getName());
            holder.name.setTypeface(typeface, typeface.BOLD);
            holder.name.setTag(country);

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

            progressDialog.dismiss();
            return convertView;

        }

    }

    public void checkAllSelected() { count = count + 1; }

    private void deleteCrowd() {

        ArrayList<String> delete_crowd_list = new ArrayList<String>();
        ArrayList<Country> countryList = dataAdapter.countryList;
            for(int i = 0; i < countryList.size(); i++){
                Country country = countryList.get(i);
                if(country.isSelected()){
                    delete_crowd_list.add(country.getName());
                }
            }

        // 10/12 - 將來把資料表連起來 , 連同 Crowd_Member 裡的成員一起刪除
        String method = "ManageCrowd_delete";
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't exist");
        String course_name = bundle.getString("course_name");

            for (int i = 0 ; i < delete_crowd_list.size(); i++) {
                BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(this);
                backgroundTask_new_course.execute(method, UID, course_name, delete_crowd_list.get(i) );
            }

        restart restart = new restart(this);
        restart.start();
    }

    public class restart extends Thread {

        Context context;

        public restart(Context context) {
            this.context = context;
        }

        @Override
        public void run() {

            super.run();

            String method = "CourseDetail_group_list";
            String course_name = bundle.getString("course_name");
            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID", "UID doesn't exist");
            BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(getApplicationContext());
                try {
                    String x = backgroundTask_new_course.execute( method , course_name , UID ).get();
                    String[] crowd_list = x.split(",");

                    handler.obtainMessage(1, 4, -1, crowd_list).sendToTarget();
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

    public void createCrowd() {

        intent.setClass(this, CreateGroup.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }

    public void queryCrowd (String crowd_name) throws ExecutionException, InterruptedException {

        String method = "StudentList_group_detail";
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't exist");
        String course_name = bundle.getString("course_name");
        BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(getApplicationContext());
        String crowd_detail = backgroundTask_new_course.execute(method ,UID ,course_name ,crowd_name ).get();

        intent.setClass(this, QueryCrowd.class);
        bundle.putString("crowd_name", crowd_name);
        bundle.putString("crowd_detail", crowd_detail);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}
