package com.example.administrator.myconnet.Function.Friends;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChoosePlayer extends AppCompatActivity {

    ArrayList<String> selected_player = new ArrayList<>();
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(typeface);
        toolbar_title.setText("選擇選手");

        bundle = getIntent().getExtras();
        String[] student_list = bundle.getStringArray("student_list");

            if (bundle.getStringArray("selected_player") != null || !student_list[0].equals("")) {

                // 將資料轉換成陣列 , 以便於下一步比較
                ArrayList<String> arrayList1 = new ArrayList<String>();
                Collections.addAll(arrayList1, student_list);

                    if (bundle.getStringArray("selected_player") != null) {
                        String[] list = bundle.getStringArray("selected_player");
                        ArrayList<String> arrayList2 = new ArrayList<String>();
                        Collections.addAll(arrayList2, list);
                        arrayList1.removeAll(arrayList2);   // 兩陣列比較後移除重複的選手
                    }

                ListView listView = (ListView) findViewById(R.id.lv_choose_player_list);
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.ctv_choose_player, R.id.ctv_choose_player , arrayList1 );
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedItem = ((TextView) view).getText().toString();
                        if (selected_player.contains(selectedItem)) {
                            selected_player.remove(selectedItem);       // selected_player 內有重複資料就移除
                        } else {
                            selected_player.add(selectedItem);
                        }
                    }
                });

            } else if (bundle.getStringArray("selected_player") == null || student_list[0].equals("")) {

                TextView textView = (TextView) findViewById(R.id.tv_choose_no_player);
                textView.setVisibility(View.VISIBLE);
                textView.setTypeface(typeface);

            }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.choose_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.choose_player_done) {

            Intent intent = new Intent();
            bundle = getIntent().getExtras();
            bundle.putStringArrayList("selected_player", selected_player);
            intent.putExtras(bundle);
            setResult( RESULT_OK , intent );
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
