package com.example.administrator.myconnet.Function.Invite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.BackgroundTask_coach;
import com.example.administrator.myconnet.BackgroundTask_find;
import com.example.administrator.myconnet.BackgroundTask_my_information;
import com.example.administrator.myconnet.Function.Course.NewCourse;
import com.example.administrator.myconnet.Function.Friends.Country;
import com.example.administrator.myconnet.R;
import com.example.administrator.myconnet.webview;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SearchCoach extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MyCustomAdapter dataAdapter = null;
    Intent intent = new Intent();
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_coach);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(typeface);
        toolbar_title.setText("搜尋教練");

        TextView textView1 = (TextView) findViewById(R.id.textView58);
        textView1.setTypeface(typeface);

        Bundle bundle1 = getIntent().getExtras();
        String x = bundle1.getString("coach_name,coach_unit");
        String[] coach_list = x.split("。");

        ArrayList<Country> countryList = new ArrayList<Country>();

        for (String name : coach_list) {
            Country country = new Country(name , false);
            countryList.add(country);
        }

        ListView listView = (ListView)findViewById(R.id.SeachCoach_listview);
        dataAdapter = new MyCustomAdapter(this, R.layout.search_coach_info, countryList);
        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Country country = (Country) parent.getItemAtPosition(position);
                String y = country.getName();
                String[] which_coach = y.split(",");
                String NAME = which_coach[0];
                String UNIT = which_coach[1];
                bundle.putString("NAME", NAME);
                bundle.putString("UNIT", UNIT);
                intent.putExtras(bundle);
                intent.setClass(getApplicationContext(), CoachCourse.class);
                startActivity(intent);      // 看以後能不能解決在監聽器內使用 AsyncTask

            }
        });

        SearchView sv = (SearchView) findViewById(R.id.searchView);     // 先找到 SearchView 的 ID，以用來搜尋
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

    private class MyCustomAdapter extends ArrayAdapter<Country> {

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        private ArrayList<Country> countryList;

        public MyCustomAdapter(Context context, int textViewResourceId, ArrayList<Country> countryList) {
            super(context, textViewResourceId, countryList);
            this.countryList = new ArrayList<Country>();
            this.countryList.addAll(countryList);
        }

        private class ViewHolder {
            TextView name;      // holder 的 CheckBox , 用來放客製化的 CheckBox
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.search_coach_info, null);

                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.tvStyle_search_coach);  // 將 checkBox1 設給 holder.name
                convertView.setTag(holder);     // 給 covertView 一個標誌 , 方便下次取得

            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            // 初始化 MyCustomAdapter
            Country country = countryList.get(position);        // countryList 陣列 , 利用 Adapter 取得位置
            holder.name.setText(country.getName());
            holder.name.setTypeface(typeface, typeface.BOLD);
            holder.name.setTag(country);

            return convertView;

        }

    }

}
