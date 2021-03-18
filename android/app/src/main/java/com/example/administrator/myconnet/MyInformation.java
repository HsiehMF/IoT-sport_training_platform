package com.example.administrator.myconnet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.text.method.KeyListener;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.Function.Course.NewCourse;
import com.example.administrator.myconnet.Function.Friends.BackgroundTask_new_course;
import com.example.administrator.myconnet.Function.Friends.NewFriends;
import com.example.administrator.myconnet.Function.Public.Public;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MyInformation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(typeface);
        toolbar_title.setText("管理個人資訊");

        Button button = (Button) findViewById(R.id.button40);
        button.setTypeface(typeface);

        final EditText name = (EditText) findViewById(R.id.my_information_name);
        final EditText id = (EditText) findViewById(R.id.my_information_id);
        final EditText birth = (EditText) findViewById(R.id.my_information_birth);
        final EditText unit = (EditText) findViewById(R.id.my_information_unit);
        final EditText gender = (EditText) findViewById(R.id.my_information_gender);
        final EditText info = (EditText) findViewById(R.id.my_information_info);
        name.setTypeface(typeface,typeface.BOLD);
        id.setTypeface(typeface);
        birth.setTypeface(typeface);
        unit.setTypeface(typeface);
        gender.setTypeface(typeface);
        info.setTypeface(typeface);

        TextView textView1 = (TextView) findViewById(R.id.textView27);
        TextView textView2 = (TextView) findViewById(R.id.textView28);
        TextView textView3 = (TextView) findViewById(R.id.textView30);
        TextView textView4 = (TextView) findViewById(R.id.textView31);
        textView1.setTypeface(typeface,typeface.BOLD);
        textView2.setTypeface(typeface,typeface.BOLD);
        textView3.setTypeface(typeface,typeface.BOLD);
        textView4.setTypeface(typeface,typeface.BOLD);

        Bundle bundle = getIntent().getExtras();
        String x = bundle.getString("my_information");
        String[] my_information = x.split(",");

        name.setText(my_information[0]);
        id.setText(my_information[1]);
        birth.setText(my_information[2]);
        unit.setText(my_information[3]);
        gender.setText(my_information[4]);
        info.setText(my_information[5]);

        // 設定 EditText 的監聽 KeyListener
        name.setTag(name.getKeyListener());
        id.setTag(id.getKeyListener());
        birth.setTag(birth.getKeyListener());
        unit.setTag(unit.getKeyListener());
        gender.setTag(gender.getKeyListener());
        info.setTag(info.getKeyListener());

        // 取消 EditText 監聽編輯
        name.setKeyListener(null);
        id.setKeyListener(null);
        birth.setKeyListener(null);
        unit.setKeyListener(null);
        gender.setKeyListener(null);
        info.setKeyListener(null);

        FloatingActionButton fab_edit = (FloatingActionButton) findViewById(R.id.float_edit_information);
        FloatingActionButton fab_done = (FloatingActionButton) findViewById(R.id.float_done_information);
        fab_edit.setVisibility(fab_edit.VISIBLE);
        fab_done.setVisibility(fab_done.INVISIBLE);  // 先把完成按鈕隱藏


        fab_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FloatingActionButton fab_edit = (FloatingActionButton) findViewById(R.id.float_edit_information);
                FloatingActionButton fab_done = (FloatingActionButton) findViewById(R.id.float_done_information);
                fab_edit.setVisibility(fab_edit.INVISIBLE);
                fab_done.setVisibility(fab_done.VISIBLE);

                name.setBackground(getResources().getDrawable(R.drawable.course_detail_edit));
                birth.setBackground(getResources().getDrawable(R.drawable.course_detail_edit));
                unit.setBackground(getResources().getDrawable(R.drawable.course_detail_edit));
                gender.setBackground(getResources().getDrawable(R.drawable.course_detail_edit));
                info.setBackground(getResources().getDrawable(R.drawable.course_detail_edit));

                name.setKeyListener((KeyListener) name.getTag());
                birth.setKeyListener((KeyListener) birth.getTag());
                unit.setKeyListener((KeyListener) unit.getTag());
                gender.setKeyListener((KeyListener) gender.getTag());
                info.setKeyListener((KeyListener) info.getTag());

                Toast.makeText(getApplicationContext(), "編輯中", Toast.LENGTH_SHORT).show();
            }
        });

        fab_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FloatingActionButton fab_edit = (FloatingActionButton) findViewById(R.id.float_edit_information);
                FloatingActionButton fab_done = (FloatingActionButton) findViewById(R.id.float_done_information);
                fab_edit.setVisibility(fab_edit.VISIBLE);
                fab_done.setVisibility(fab_done.INVISIBLE);

                SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
                String UID = sharedPreferences.getString("UID" , "UID doesn't exist");

                EditText et_name = (EditText) findViewById(R.id.my_information_name);
                EditText et_birth = (EditText) findViewById(R.id.my_information_birth);
                EditText et_unit = (EditText) findViewById(R.id.my_information_unit);
                EditText et_gender = (EditText) findViewById(R.id.my_information_gender);
                EditText et_info = (EditText) findViewById(R.id.my_information_info);

                et_name.setBackground(getResources().getDrawable(R.drawable.customer_name_unit_done));
                et_birth.setBackground(getResources().getDrawable(R.drawable.course_detail_done));
                et_unit.setBackground(getResources().getDrawable(R.drawable.customer_name_unit_done));
                et_gender.setBackground(getResources().getDrawable(R.drawable.course_detail_done));
                et_info.setBackground(getResources().getDrawable(R.drawable.course_detail_done));

                String name = et_name.getText().toString();
                String birth = et_birth.getText().toString();
                String unit = et_unit.getText().toString();
                String gender = et_gender.getText().toString();
                String info = et_info.getText().toString();

                BackgroundTask_information_save backgroundTask_information_save = new BackgroundTask_information_save(getApplicationContext(),et_name,et_birth,et_unit,et_gender,et_info);
                backgroundTask_information_save.execute(name,birth,unit,gender,info,UID);

                // 取消 EditText 監聽編輯
                et_name.setKeyListener(null);
                et_birth.setKeyListener(null);
                et_unit.setKeyListener(null);
                et_gender.setKeyListener(null);
                et_info.setKeyListener(null);

                Toast.makeText(getApplicationContext(),"修改完成",Toast.LENGTH_SHORT).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't exist");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().clear();
          if ( UID.equals("2") || UID.equals("8") ) {
              navigationView.inflateMenu(R.menu.activity_lobby2_coach);
          } else {
              navigationView.inflateMenu(R.menu.activity_lobby2_drawer);
          }
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
        getMenuInflater().inflate(R.menu.my_information, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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

            // 已在此頁

        } else if (id == R.id.nav_today_train) {

            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID" , "UID doesn't exist");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date curDate = new Date(System.currentTimeMillis()); // 獲取當前時間
            String curDay = formatter.format(curDate);

            BackgroundTask_find backgroundTask_find = new BackgroundTask_find(this);
            backgroundTask_find.execute(UID, curDay);

            Intent intent = new Intent();
            intent.setClass(this, NewCourse.class);
            Bundle bundle1 = new Bundle();
            bundle1.putString("UID", UID);
            bundle1.putString("curDay", curDay);
            intent.putExtras(bundle1);
            startActivity(intent);

            finish();

        }  else if (id == R.id.nav_apply_coach) {

            String method = "Student";
            BackgroundTask_coach backgroundTask_coach = new BackgroundTask_coach(this, method);
            backgroundTask_coach.execute();     // 未來可以跟 catch 做整合

            finish();

        } else if (id == R.id.nav_manage_train_record) {

            startActivity(new Intent(this, webview.class));
            finish();

        } else if (id == R.id.nav_manage_train_plan) {

            startActivity(new Intent(this, Public.class));
            finish();

        } else if (id == R.id.nav_group) {

            String method = "ExistedCourse";
            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID" , "UID doesn't exist");
            BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(this);
            String course_name = null;
            try { course_name = backgroundTask_new_course.execute(method, UID).get(); }
            catch (InterruptedException | ExecutionException e) { e.printStackTrace(); }

            Intent intent = new Intent();
            intent.setClass(this, NewFriends.class);
            Bundle bundle1 = new Bundle();
            bundle1.putString("course_name", course_name);
            intent.putExtras(bundle1);
            startActivity(intent);

            finish();

        } else if (id == R.id.nav_manage_apply_player) {

            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID" , "UID doesn't exist");

            String method = "Coach";
            BackgroundTask_coach backgroundTask_coach = new BackgroundTask_coach(this, method);
            backgroundTask_coach.execute(UID);

            finish();

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void resetPassword (View view) {

        startActivity(new Intent(this, ResetPassWord.class));
    }
}
