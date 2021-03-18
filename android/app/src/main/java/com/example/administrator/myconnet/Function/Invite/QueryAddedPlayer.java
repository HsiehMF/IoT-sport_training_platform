package com.example.administrator.myconnet.Function.Invite;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.Function.Friends.Country;
import com.example.administrator.myconnet.R;

import java.util.ArrayList;

public class QueryAddedPlayer extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    Bundle bundle = new Bundle();
    MyCustomAdapter dataAdapter = null;
    SwipeRefreshLayout mSwipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_added_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("查看已審核選手");
        toolbar_title.setTypeface(typeface);

        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeColors(Color.BLUE); //重整的圖示用藍色

        init();

    }

    private void init(){

        bundle = getIntent().getExtras();
        String x = bundle.getString("added_player");
        String[] added_player = x.split(",");

        ArrayList<Country> countryList = new ArrayList<Country>();

        for (String name : added_player) {
            Country country = new Country(name , false);
            countryList.add(country);
        }

        ListView listView = (ListView) findViewById(R.id.lv_query_added_player);
        dataAdapter = new MyCustomAdapter(this, R.layout.query_added_player_info, countryList);
        listView.setAdapter(dataAdapter);

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
                convertView = vi.inflate(R.layout.query_added_player_info, null);

                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.tvStyle_query_added_player);  // 將 checkBox1 設給 holder.name
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

    @Override
    public void onRefresh() {

        // loadData();

        mSwipeLayout.setRefreshing(false); //結束更新動畫
    }



}
