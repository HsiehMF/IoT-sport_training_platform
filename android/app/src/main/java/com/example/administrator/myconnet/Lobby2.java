package com.example.administrator.myconnet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.Function.Course.NewCourse;
import com.example.administrator.myconnet.Function.Friends.BackgroundTask_new_course;
import com.example.administrator.myconnet.Function.Friends.NewFriends;
import com.example.administrator.myconnet.Function.Public.BackgroundTask_integrate;
import com.example.administrator.myconnet.Function.Public.Public;
import com.example.administrator.myconnet.Function.Records.Calendar;
import com.example.administrator.myconnet.Function.Records.Records;
import com.example.administrator.myconnet.Function.Reply.ReplySelectCourse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class Lobby2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("首頁");
        toolbar_title.setTypeface(typeface);

        Bundle bundle = getIntent().getExtras();
        String UID = bundle.getString("UID");
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UID", UID);
        editor.commit();

        TextView textView1 = (TextView) findViewById(R.id.tv_write);
        TextView textView2 = (TextView) findViewById(R.id.textView6);
        TextView textView3 = (TextView) findViewById(R.id.tv_my_information);
        TextView textView4 = (TextView) findViewById(R.id.tv_friends);
        TextView textView5 = (TextView) findViewById(R.id.tv_add);

        textView1.setTypeface(typeface,typeface.BOLD);
        textView2.setTypeface(typeface,typeface.BOLD);
        textView3.setTypeface(typeface,typeface.BOLD);
        textView4.setTypeface(typeface,typeface.BOLD);
        textView5.setTypeface(typeface,typeface.BOLD);

        Toast.makeText(this, "教練，歡迎登入", Toast.LENGTH_LONG).show();
        ImageButton imageButton_write = (ImageButton) findViewById(R.id.imageButton_write);
        TextView tv_write = (TextView) findViewById(R.id.tv_write);     /* 將處方管理與公佈欄調換 */

        assert tv_write != null;
        tv_write.setText("管理訓練計畫");
        assert imageButton_write != null;
        imageButton_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lobby2.this, Public.class));
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

    //設定提問視窗
    public class AlertDialogFragment
            extends DialogFragment implements DialogInterface.OnClickListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle("Exit")
                    .setIcon(R.drawable.ic_logout_black_24dp)
                    .setMessage("您確定登出?")
                    .setPositiveButton("是", this)
                    .setNegativeButton("否", this)
                    .create();
        }

        //執行選擇的選項

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    Logout();
                    getActivity().finish();
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.cancel();
                    break;
                default:
                    break;
            }
        }
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
        getMenuInflater().inflate(R.menu.lobby2, menu);
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
        } else if (id == R.id.nav_reply_comment) {
            String method = "catch_course";
            Bundle bundle = getIntent().getExtras();
            String UID = bundle.getString("UID");
            BackgroundTask_integrate backgroundTask_integrate = new BackgroundTask_integrate(this);
            String total_course_name = null;
            try { total_course_name = backgroundTask_integrate.execute(method, UID).get(); }
            catch (InterruptedException | ExecutionException e) { e.printStackTrace(); }

            Intent intent = new Intent();
            intent.setClass(this, ReplySelectCourse.class);
            bundle.putString("total_course_name", total_course_name);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    //側拉欄功能的執行動作

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_information) {

            Bundle bundle = getIntent().getExtras();
            String UID = bundle.getString("UID");
            BackgroundTask_my_information my_information = new BackgroundTask_my_information(this);
            my_information.execute(UID);

        } else if (id == R.id.nav_today_train) {

            Bundle bundle = getIntent().getExtras();
            String UID = bundle.getString("UID");
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

        }  else if (id == R.id.nav_apply_coach) {

            String method = "Student";
            BackgroundTask_coach backgroundTask_coach = new BackgroundTask_coach(this, method);
            backgroundTask_coach.execute();     // 未來可以跟 catch 做整合

        } else if (id == R.id.nav_manage_train_record) {

            startActivity(new Intent(this, webview.class));

        } else if (id == R.id.nav_manage_train_plan) {

            startActivity(new Intent(this, Public.class));

        } else if (id == R.id.nav_group) {

            String method = "ExistedCourse";
            Bundle bundle = getIntent().getExtras();
            String UID = bundle.getString("UID");
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

        } else if (id == R.id.nav_manage_apply_player) {

            Bundle bundle = getIntent().getExtras();
            String UID = bundle.getString("UID");

            String method = "Coach";
            BackgroundTask_coach backgroundTask_coach = new BackgroundTask_coach(this, method);
            backgroundTask_coach.execute(UID);

        } else if (id == R.id.nav_logout){
            // 使用AlertDialog 問使用者是否登出
            AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            alertDialogFragment.show(fragmentManager, "alert");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // 使用Button執行跳轉頁面
    public void userFriends(View view) throws ExecutionException, InterruptedException {

        String method = "ExistedCourse";
        Bundle bundle = getIntent().getExtras();
        String UID = bundle.getString("UID");
        BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(this);
        String course_name = backgroundTask_new_course.execute(method, UID).get();

        Intent intent = new Intent();
        intent.setClass(this, NewFriends.class);
        Bundle bundle1 = new Bundle();
        bundle1.putString("course_name", course_name);
        intent.putExtras(bundle1);
        startActivity(intent);

    }

    public void userRecords(View view)
    {
        startActivity(new Intent(this, WebIntent.class));
    }

    public void userCourse(View view) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis()) ; // 獲取當前時間
        String curDay = formatter.format(curDate);
        Bundle bundle = getIntent().getExtras();
        String UID = bundle.getString("UID");

        Intent intent = new Intent();
        intent.setClass(this, NewCourse.class);
        Bundle bundle1 = new Bundle();
        bundle1.putString("UID" , UID);
        bundle1.putString("curDay",curDay);
        intent.putExtras(bundle1);
        startActivity(intent);

    }

    /* -- userInvite -- 用 UID 判斷選手及教練，如果是學生，做背景作業Bundle到 SearchCoach ；教練則是先到 CoachAddStudent 做背景作業，取得回傳資料 */
    public void userInvite(View view) {

        Bundle bundle = getIntent().getExtras();
        String UID = bundle.getString("UID");
        int judge = Integer.parseInt(UID);

        if ( judge!=2 && judge!=8 ) {

            String method = "Student";
            BackgroundTask_coach backgroundTask_coach = new BackgroundTask_coach(this, method);
            backgroundTask_coach.execute();     // 未來可以跟 catch 做整合

        } else {

            String method = "Coach";
            BackgroundTask_coach backgroundTask_coach = new BackgroundTask_coach(this, method);
            backgroundTask_coach.execute(UID);

        }

    }

    public void userInformation(View view) {

        Bundle bundle = getIntent().getExtras();
        String UID = bundle.getString("UID");
        BackgroundTask_my_information my_information = new BackgroundTask_my_information(this);
        my_information.execute(UID);

    }

    public void Logout() {

        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("UID");
        editor.commit();

    }

}
