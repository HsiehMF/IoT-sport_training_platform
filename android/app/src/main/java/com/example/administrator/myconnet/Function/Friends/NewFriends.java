package com.example.administrator.myconnet.Function.Friends;

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
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.BackgroundTask_coach;
import com.example.administrator.myconnet.BackgroundTask_my_information;
import com.example.administrator.myconnet.Function.Course.NewCourse;
import com.example.administrator.myconnet.Function.Public.Public;
import com.example.administrator.myconnet.Function.Records.Records;
import com.example.administrator.myconnet.R;
import com.example.administrator.myconnet.webview;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class NewFriends extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent = new Intent();
    Bundle bundle = new Bundle();
    MyCustomAdapter dataAdapter = null;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Typeface noto = Typeface.createFromAsset(getAssets(),"fonts/notosanscjktcmedium.ttf");
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        setContentView(R.layout.activity_new_friends);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("管理社團");
        toolbar_title.setTypeface(typeface);

        TextView textView = (TextView) findViewById(R.id.textView59);
        CheckBox checkBox = (CheckBox) findViewById(R.id.cb_new_friends);
        textView.setTypeface(noto);
        checkBox.setTypeface(typeface);

        bundle = getIntent().getExtras();

        restart restart = new restart(this);
        restart.start();

        SearchView sv = (SearchView) findViewById(R.id.searchView9);     // 先找到 SearchView 的 ID，以用來搜尋
        assert sv != null;
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                dataAdapter.getFilter().filter(text);       // 過濾 adapter 的內容
                return false;
            }

        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().clear();
        navigationView.inflateMenu(R.menu.activity_lobby2_coach);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_friends, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_course) {

            intent.setClass(getApplicationContext(), CreateCourse.class);
            intent.putExtras(bundle);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_information) {

            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID", "UID doesn't exist");
            BackgroundTask_my_information my_information = new BackgroundTask_my_information(this);
            my_information.execute(UID);

            finish();

        } else if (id == R.id.nav_manage_train_record) {

            startActivity(new Intent(this, webview.class));
            finish();

        } else if (id == R.id.nav_manage_train_plan) {

            startActivity(new Intent(this, Public.class));
            finish();

        } else if (id == R.id.nav_group) {

            // 已在此頁

        } else if (id == R.id.nav_manage_apply_player) {

            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID", "UID doesn't exist");

            String method = "Coach";
            BackgroundTask_coach backgroundTask_coach = new BackgroundTask_coach(this, method);
            backgroundTask_coach.execute(UID);

            finish();

        } else if (id == R.id.nav_send) {

            // 使用AlertDialog 問使用者是否登出
            AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            alertDialogFragment.show(fragmentManager, "alert");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void manage_course( String course_name ) throws ExecutionException, InterruptedException {

        String method = "CourseDetail";
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't exist");
        BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(this);
        String course_detail = backgroundTask_new_course.execute(method, course_name, UID).get();

        intent.setClass(this, CourseDetail.class);
        bundle.putString("course_detail", course_detail);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    private void displayListView( String[] course_name ) {

        ArrayList<Country> countryList = new ArrayList<Country>();

        for (String name : course_name) {
            Country country = new Country(name , false);
            countryList.add(country);
        }

        //create an ArrayAdapter from the String Array
        dataAdapter = new MyCustomAdapter(this, R.layout.country_info, countryList);
        ListView listView = (ListView) findViewById(R.id.lv_new_friends);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        CheckBox checkBox = (CheckBox) findViewById(R.id.cb_new_friends);
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
                String course_name = country.getName();  // 取得選取的課程 , 跑 manage_course();
                try {
                    manage_course(course_name);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

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
            ImageButton imagebutton = (ImageButton) findViewById(R.id.bt_new_friends_delete);
            assert imagebutton != null;
            imagebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    alertDialogFragment.show(fragmentManager, "alert");

                }
            });

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

            return convertView;

        }

    }

    public void checkAllSelected() { count = count + 1; }

    public class AlertDialogFragment
            extends DialogFragment implements DialogInterface.OnClickListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle("刪除")
                    .setIcon(R.drawable.ic_logout_black_24dp)
                    .setMessage("確定刪除課程 ? ")
                    .setPositiveButton("YES", this)
                    .setNegativeButton("NO", this)
                    .create();
        }

        //執行選擇的選項
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    deleteCourse();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.cancel();
                    break;
                default:
                    break;
            }
        }
    }

    private void deleteCourse() {

        ArrayList<String> delete_course_list = new ArrayList<String>();

        ArrayList<Country> countryList = dataAdapter.countryList;
        for(int i=0;i<countryList.size();i++){
            Country country = countryList.get(i);
            if(country.isSelected()){
                delete_course_list.add(country.getName());
            }
        }

        String method = "CourseDetail_delete";
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't exist");
        for (int i = 0 ; i < delete_course_list.size(); i++) {
            BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(this);
            backgroundTask_new_course.execute(method, delete_course_list.get(i), UID);
        }

        // 更新功能
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

            String method = "ExistedCourse";
            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID", "UID doesn't found");
            BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(getApplicationContext());
            try {
                String x = backgroundTask_new_course.execute(method, UID).get();
                String[] course_name = x.split(",");

                handler.obtainMessage(1, 4, -1, course_name).sendToTarget();
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

}
