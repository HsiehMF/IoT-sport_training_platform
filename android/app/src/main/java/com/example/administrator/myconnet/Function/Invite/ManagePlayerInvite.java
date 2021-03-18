package com.example.administrator.myconnet.Function.Invite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.TextView;

import com.example.administrator.myconnet.BackgroundTask_coach;
import com.example.administrator.myconnet.BackgroundTask_my_information;
import com.example.administrator.myconnet.Function.Friends.BackgroundTask_new_course;
import com.example.administrator.myconnet.Function.Friends.NewFriends;
import com.example.administrator.myconnet.Function.Public.BackgroundTask_integrate;
import com.example.administrator.myconnet.Function.Public.Public;
import com.example.administrator.myconnet.Function.Public.SelectCourse;
import com.example.administrator.myconnet.Function.Public.SelectCrowd;
import com.example.administrator.myconnet.R;
import com.example.administrator.myconnet.webview;

import java.util.concurrent.ExecutionException;

public class ManagePlayerInvite extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent = new Intent();
    Bundle bundle = new Bundle(); // 儲存 catch_course 的值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_player_invite);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(typeface);
        toolbar_title.setText("管理選手申請");

        Button button1 = (Button) findViewById(R.id.button36);
        Button button2 = (Button) findViewById(R.id.button37);
        button1.setTypeface(typeface);
        button2.setTypeface(typeface);

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

            // 已在此頁

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void selectCourse_Invite(View view) throws ExecutionException, InterruptedException {

        String method = "catch_course";
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't founded");     // 取得登入的UID
        BackgroundTask_catch backgroundTask_catch = new BackgroundTask_catch(this);
        String total_course_name = backgroundTask_catch.execute(method, UID).get();

        intent.setClass(this, InviteSelectCourse.class);
        bundle.putString("total_course_name", total_course_name);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void queryAddedPlayer (View view) throws ExecutionException, InterruptedException {

        String method = "catch_added_player";
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't founded");     // 取得登入的UID
        BackgroundTask_catch backgroundTask_catch = new BackgroundTask_catch(this);
        String added_player = backgroundTask_catch.execute(method, UID).get();

        intent.setClass(this, QueryAddedPlayer.class);
        bundle.putString("added_player", added_player);
        intent.putExtras(bundle);
        startActivity(intent);
        System.out.println(added_player);
    }
}
