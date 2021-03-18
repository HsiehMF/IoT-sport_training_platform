package com.example.administrator.myconnet.Function.Invite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.TextView;

import com.example.administrator.myconnet.BackgroundTask_coach;
import com.example.administrator.myconnet.BackgroundTask_find;
import com.example.administrator.myconnet.BackgroundTask_my_information;
import com.example.administrator.myconnet.Function.Course.NewCourse;
import com.example.administrator.myconnet.R;
import com.example.administrator.myconnet.webview;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class InviteApply extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_apply);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("查看社團");
        toolbar_title.setTypeface(typeface);

        bundle = getIntent().getExtras();
            try {
                checkHadCourse();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

        TextView textView_1 = (TextView) findViewById(R.id.textView5);
        TextView textView_2 = (TextView) findViewById(R.id.textView7);
        TextView textView_3 = (TextView) findViewById(R.id.textView61);
        TextView textView_4 = (TextView) findViewById(R.id.textView64);
        TextView textView_5 = (TextView) findViewById(R.id.textView8);
        textView_1.setTypeface(typeface, typeface.BOLD);
        textView_2.setTypeface(typeface, typeface.BOLD);
        textView_3.setTypeface(typeface, typeface.BOLD);
        textView_4.setTypeface(typeface, typeface.BOLD);
        textView_5.setTypeface(typeface, typeface.BOLD);

        Button button_1 = (Button) findViewById(R.id.invite_cancel_bt);
        Button button_2 = (Button) findViewById(R.id.invite_apply_bt);
        button_1.setTypeface(typeface);
        button_2.setTypeface(typeface);

        String course_name = bundle.getString("course_name");
        String NAME = bundle.getString("NAME");
        String UNIT = bundle.getString("UNIT");
        bundle.putString( "coach_name" , NAME);     // 審核需要教練名稱

        String method = "InviteApply";
        BackgroundTask_course backgroundTask_course = new BackgroundTask_course(this);
            try {
                String x = backgroundTask_course.execute(method,course_name,NAME,UNIT).get();
                String[] result = x.split(",");

                TextView textView1 = (TextView) findViewById(R.id.course_name_tv);
                TextView textView2 = (TextView) findViewById(R.id.course_date_tv);
                TextView textView3 = (TextView) findViewById(R.id.course_info_tv);
                TextView textView4 = (TextView) findViewById(R.id.course_coach_name_tv);
                TextView textView5 = (TextView) findViewById(R.id.course_amount_tv);

                textView1.setText(result[1]);
                textView2.setText(result[2]);
                textView3.setText(result[3]);
                textView4.setText(NAME);
                textView5.setText(result[4] + " 人 ");
                textView1.setTypeface(typeface);
                textView2.setTypeface(typeface);
                textView3.setTypeface(typeface);
                textView4.setTypeface(typeface);
                textView5.setTypeface(typeface);

                bundle.putString("course_num", result[0]);      // 審核需要課程編號

            } catch (InterruptedException | ExecutionException e) { e.printStackTrace(); }

        String method1 = "Invite_Apply_check";
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't founded");     // 取得登入的UID
        String course_num = bundle.getString("course_num");
        BackgroundTask_course backgroundTask_check = new BackgroundTask_course(this);
            try {

                String x = backgroundTask_check.execute(method1,UID,course_num,NAME).get();      // 教練 Name 從 bundle1 得來

                    if( x.equals("審核中") ){

                        Button button = (Button) findViewById(R.id.invite_apply_bt);
                        Button button1 = (Button) findViewById(R.id.invite_cancel_bt);

                        button.setText("審核中");
                        button.setTypeface(typeface);
                        button.setAlpha((float) 0.95);
                        button.setEnabled(false);

                        button1.setAlpha(1);
                        button1.setTypeface(typeface);
                        button1.setEnabled(true);

                    }

            } catch (InterruptedException | ExecutionException e) { e.printStackTrace(); }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().clear();
        navigationView.inflateMenu(R.menu.activity_lobby2_drawer);
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
        getMenuInflater().inflate(R.menu.invite_apply, menu);
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

            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID", "UID doesn't exist");

            BackgroundTask_my_information my_information = new BackgroundTask_my_information(this);
            my_information.execute(UID);

            finish();

        } else if (id == R.id.nav_today_train) {

            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID", "UID doesn't exist");

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

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void checkHadCourse() throws ExecutionException, InterruptedException {

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");

        Button button1 = (Button) findViewById(R.id.invite_apply_bt);
        Button button2 = (Button) findViewById(R.id.invite_cancel_bt);
        Button button3 = (Button) findViewById(R.id.bt_haveCourse);
        button1.setTypeface(typeface);
        button2.setTypeface(typeface);
        button3.setTypeface(typeface);

        String method = "檢查是否有社團";
        String course_name = bundle.getString("course_name");
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't founded");     // 取得登入的UID
        BackgroundTask_catch backgroundTask_catch = new BackgroundTask_catch(this);
        String result = backgroundTask_catch.execute(method, UID, course_name).get();

          switch (result) {
              case "無社團狀態":
                  button1.setEnabled(true);
                  button2.setEnabled(true);
                  break;
              case "審核中":
                  button1.setText("審核中");
                  button1.setAlpha((float) 0.95);
                  button1.setEnabled(false);
                  button2.setAlpha(1);
                  button2.setEnabled(true);
                  break;
              case "您已加入其他社團":
                  button1.setVisibility(View.GONE);
                  button2.setVisibility(View.GONE);
                  button3.setVisibility(View.VISIBLE);
                  button3.setText(result);
                  break;
              default:
                  System.out.println("社團編號 : " + result);
                  button1.setVisibility(View.GONE);
                  button2.setVisibility(View.GONE);
                  button3.setVisibility(View.VISIBLE);
                  button3.setText(result);
                  break;
          }

    }

    public void sendApply(View view) {      // 送出申請社團

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");

        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't founded");     // 取得登入的UID
        String course_num = bundle.getString("course_num");
        String coach_name = bundle.getString("coach_name");

        String method = "InviteApply_submit";   //// 多加一個course_name
        BackgroundTask_course backgroundTask_course = new BackgroundTask_course(this);
        backgroundTask_course.execute( method , UID , course_num , coach_name);

        Button button = (Button) findViewById(R.id.invite_apply_bt);
        Button button1 = (Button) findViewById(R.id.invite_cancel_bt);

        button.setText("審核中");
        button.setAlpha((float) 0.95);
        button.setEnabled(false);
        button.setTypeface(typeface);
        button1.setAlpha(1);
        button1.setEnabled(true);
        button1.setTypeface(typeface);

    }

    public void cancelApply(View view) {

        String method = "申請取消";   //// 多加一個course_name
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't founded");     // 取得登入的UID
        BackgroundTask_course backgroundTask_course = new BackgroundTask_course(this);
        backgroundTask_course.execute(method, UID);

        Button button = (Button) findViewById(R.id.invite_apply_bt);
        Button button1 = (Button) findViewById(R.id.invite_cancel_bt);

        button.setText("申請社團");
        button.setAlpha(1);
        button.setEnabled(true);
        button1.setAlpha((float)0.95);
        button1.setEnabled(true);

    }

    public void exitCourse(View view) {

        Button button1 = (Button) findViewById(R.id.invite_apply_bt);
        Button button2 = (Button) findViewById(R.id.invite_cancel_bt);
        Button button3 = (Button) findViewById(R.id.bt_haveCourse);

        String method = "申請取消";
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't founded");     // 取得登入的UID
        BackgroundTask_course backgroundTask_course = new BackgroundTask_course(this);
        backgroundTask_course.execute(method, UID);

        button1.setText("申請社團");
        button1.setVisibility(View.VISIBLE);
        button1.setAlpha(1);
        button1.setEnabled(true);
        button2.setText("取消");
        button2.setAlpha((float) 0.95);
        button2.setVisibility(View.VISIBLE);
        button2.setEnabled(true);
        button3.setVisibility(View.GONE);

    }

}
