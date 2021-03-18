package com.example.administrator.myconnet.Function.Public;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.administrator.myconnet.BackgroundTask_coach;
import com.example.administrator.myconnet.BackgroundTask_my_information;
import com.example.administrator.myconnet.Function.Friends.BackgroundTask_new_course;
import com.example.administrator.myconnet.Function.Friends.NewFriends;
import com.example.administrator.myconnet.MainActivity;
import com.example.administrator.myconnet.R;
import com.example.administrator.myconnet.webview;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class Public extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent = new Intent();
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(typeface);
        toolbar_title.setText("管理訓練計畫");

        Button button1 = (Button) findViewById(R.id.bt_CreateNewPlan);
        Button button2 = (Button) findViewById(R.id.bt_ApplyTrainPlan);
        Button button3 = (Button) findViewById(R.id.bt_QueryTrainPlan);
        button1.setTypeface(typeface);
        button2.setTypeface(typeface);
        button3.setTypeface(typeface);

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

    public static class AlertDialogFragment
            extends DialogFragment implements DialogInterface.OnClickListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle("Exit")
                    .setIcon(R.drawable.ic_logout_black_24dp)
                    .setMessage("您確定登出?")
                    .setPositiveButton("YES", this)
                    .setNegativeButton("NO", this)
                    .create();
        }

        //執行選擇的選項

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    getActivity().finish();
                    startActivity(new Intent(getActivity(),MainActivity.class));
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

            // 已在此頁

        } else if (id == R.id.nav_group) {

            String method = "ExistedCourse";
            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID", "UID doesn't exist");
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

    public void applyCourse(View view) throws ExecutionException, InterruptedException {

        String method = "catch_course";
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't founded");     // 取得登入的UID
        BackgroundTask_integrate backgroundTask_integrate = new BackgroundTask_integrate(this);
        String total_course_name = backgroundTask_integrate.execute(method, UID).get();
        bundle.putString("total_course_name", total_course_name);

        System.out.println(total_course_name);
        intent.setClass(this, ApplySelectCourse.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void createNewPlan (View view) throws ExecutionException, InterruptedException {

        String method = "catch_course";
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't founded");     // 取得登入的UID
        BackgroundTask_integrate backgroundTask_integrate = new BackgroundTask_integrate(this);
        String total_course_name = backgroundTask_integrate.execute(method, UID).get();
        bundle.putString("total_course_name", total_course_name);

        intent.setClass(this, SelectCourse.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void queryTrainPlan(View view) throws ExecutionException, InterruptedException {

        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String selected = item.getTitle().toString();

                if (selected.equals("列出今日所有訓練")) {

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
                        intent.setClass(getApplicationContext(), QueryTodayTrainPlan.class);
                        bundle.putStringArray("TODAY_TRAIN_CROWD", TODAY_TRAIN_CROWD);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } catch (InterruptedException | ExecutionException e) {  e.printStackTrace();  }
                }

                if (selected.equals("選擇日期查詢訓練")) {

                    String method = "catch_course";
                    SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
                    String UID = sharedPreferences.getString("UID", "UID doesn't founded");     // 取得登入的UID
                    BackgroundTask_integrate backgroundTask_integrate = new BackgroundTask_integrate(getApplicationContext());
                    try {
                        String total_course_name = backgroundTask_integrate.execute(method, UID).get();
                        bundle.putString("total_course_name", total_course_name);
                        intent.setClass(getApplicationContext(), QuerySelectCourse.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } catch (InterruptedException | ExecutionException e) {  e.printStackTrace();  }
                }

                return true;
            }
        });
        popupMenu.show();


    }

}
