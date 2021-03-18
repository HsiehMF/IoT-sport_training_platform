package com.example.administrator.myconnet.Function.Public;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.administrator.myconnet.Function.Friends.Country;
import com.example.administrator.myconnet.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SelectCrowd extends AppCompatActivity {

    Intent intent = new Intent();
    Bundle bundle = new Bundle();
    MyCustomAdapter dataAdapter = null;
    public static SelectCrowd instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_select_crowd);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("選擇群組");
        toolbar_title.setTypeface(typeface);

        TextView textView = (TextView) findViewById(R.id.textView12);
        textView.setTypeface(typeface);

        init();

    }

    @Override
    public void finish() {
        super.finish();
        instance = null;
    }

    private void init() {

        bundle = getIntent().getExtras();
        String x = bundle.getString("total_crowd_name");
        String[] total_crowd_name = x.split(",");

        ArrayList<Country> countryList = new ArrayList<Country>();

        for (String name : total_crowd_name) {
            Country country = new Country(name , false);
            countryList.add(country);
        }

        ListView listView = (ListView) findViewById(R.id.lv_select_crowd);
        dataAdapter = new MyCustomAdapter(this, R.layout.normal_public_info, countryList);      // ListView 存放 total_crowd_name
        assert listView != null;
        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String crowd_name =((TextView)view.findViewById(R.id.tvStyle_public)).getText().toString();
                enterItemList(crowd_name);
            }
        });

        SearchView searchView = (SearchView) findViewById(R.id.searchView3);     // 先找到 SearchView 的 ID，以用來搜尋
        assert searchView != null;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

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
                convertView = vi.inflate(R.layout.normal_public_info, null);

                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.tvStyle_public);  // 將 checkBox1 設給 holder.name
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

    public void enterItemList(String crowd_name) {

        bundle.putString("crowd_name", crowd_name);     // 儲存選取的 crowd_name
        intent.setClass(this, CreateSelectDate.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
