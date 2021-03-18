package com.example.administrator.myconnet.Function.Friends;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.administrator.myconnet.R;

public class UpdatePlayer extends AppCompatActivity {

    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("選手資訊");

        bundle = getIntent().getExtras();
        String[] player_detail = bundle.getStringArray("player_detail");

        TextView textView1 = (TextView) findViewById(R.id.update_player_name);
        TextView textView2 = (TextView) findViewById(R.id.update_player_unit);
        TextView textView3 = (TextView) findViewById(R.id.update_player_birth);
        TextView textView4 = (TextView) findViewById(R.id.update_player_gender);
        TextView textView5 = (TextView) findViewById(R.id.update_player_info);

        assert player_detail != null;
        textView1.setText(player_detail[0]);
        textView2.setText(player_detail[1]);
        textView3.setText(player_detail[2]);
        textView4.setText(player_detail[3]);
        textView5.setText(player_detail[4]);

    }
}
