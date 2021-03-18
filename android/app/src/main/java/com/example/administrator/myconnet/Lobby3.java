package com.example.administrator.myconnet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.TextView;

import com.example.administrator.myconnet.Function.Course.NewCourse;
import com.example.administrator.myconnet.Function.Friends.BackgroundTask_new_course;
import com.example.administrator.myconnet.Function.Friends.NewFriends;
import com.example.administrator.myconnet.Function.Public.Public;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class Lobby3 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby3);
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

        TextView textView1 = (TextView) findViewById(R.id.tv_today);
        TextView textView2 = (TextView) findViewById(R.id.tv_my_information);
        TextView textView3 = (TextView) findViewById(R.id.tv_write);
        TextView textView4 = (TextView) findViewById(R.id.tv_today_train);
        textView1.setTypeface(typeface,typeface.BOLD);
        textView2.setTypeface(typeface,typeface.BOLD);
        textView3.setTypeface(typeface,typeface.BOLD);
        textView4.setTypeface(typeface,typeface.BOLD);

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

        } else if(id == R.id.nav_setting){

            Bundle bundle = getIntent().getExtras();
            String UID = bundle.getString("UID");

            Intent intent = new Intent();
            Bundle bundle1=new Bundle();
            bundle1.putString("UID",UID);
            intent.putExtras(bundle1);
            intent.setClass(this, Setting.class);
            startActivity(intent);


//            Intent intent = new Intent();
//            intent.setClass(this, Setting.class);
//            startActivity(intent);

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

    public void userRecords(View view) {  startActivity(new Intent(this, webview.class));  }

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

        String method = "Student";
        BackgroundTask_coach backgroundTask_coach = new BackgroundTask_coach(this, method);
        backgroundTask_coach.execute();     // 未來可以跟 catch 做整合

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
