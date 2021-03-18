package com.example.administrator.myconnet.Function.Public;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.R;

import java.util.ArrayList;

public class EnterItemList extends AppCompatActivity {

    Intent intent = new Intent();
    Bundle bundle = new Bundle();
    public static EnterItemList instance = null;
    final String[] times ={ "請選擇訓練組數", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
    final String[] item_list = { "請選擇訓練項目", "跑步", "重訓" };
    final String[] item_sub_list_for_run ={ "請選擇訓練子項目", "100", "200", "400", "800", "1600", "3200", "6400", "10000" };
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_item_list);
        instance = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("建立訓練細項");
        toolbar_title.setTypeface(typeface);

        TextView textView1 = (TextView) findViewById(R.id.textView13);
        TextView textView2 = (TextView) findViewById(R.id.textView62);
        TextView textView3 = (TextView) findViewById(R.id.textView63);
        TextView textView4 = (TextView) findViewById(R.id.textView65);
        TextView textView5 = (TextView) findViewById(R.id.textView51);
        textView1.setTypeface(typeface, typeface.BOLD);
        textView2.setTypeface(typeface, typeface.BOLD);
        textView3.setTypeface(typeface, typeface.BOLD);
        textView4.setTypeface(typeface, typeface.BOLD);
        textView5.setTypeface(typeface, typeface.BOLD);

        EditText editText1 = (EditText) findViewById(R.id.et_enter_item_list_training_time);
        EditText editText2 = (EditText) findViewById(R.id.et_enter_item_list_note);
        editText1.setTypeface(typeface);
        editText2.setTypeface(typeface);

        Button button1 = (Button) findViewById(R.id.button27);
        Button button2 = (Button) findViewById(R.id.button28);
        button1.setTypeface(typeface);
        button2.setTypeface(typeface);

        init();

        ImageButton imageButton = (ImageButton) findViewById(R.id.ibt_addNewItem);
        assert imageButton != null;
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                addNewItem(count);
            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        instance = null;
    }

    private void init() {

        bundle = getIntent().getExtras();

        Spinner main_item  = (Spinner) findViewById(R.id.sp_enter_item_list_main_item);
        Spinner sub_item   = (Spinner) findViewById(R.id.sp_enter_item_list_sub_item);
        Spinner item_times = (Spinner) findViewById(R.id.sp_enter_item_list_times);
        EditText time = (EditText) findViewById(R.id.et_enter_item_list_training_time);
        EditText note = (EditText) findViewById(R.id.et_enter_item_list_note);

        ImageButton imageButton = new ImageButton(this);

        main_item.setId(count);
        sub_item.setId(count);
        item_times.setId(count);
        time.setId(count);
        note.setId(count);

        ArrayAdapter<String> adapter_main_item = new ArrayAdapter<String>(this,
                R.layout.spinner_style_for_advance_search, item_list);
        ArrayAdapter<String> adapter_sub_item = new ArrayAdapter<String>(this,
                R.layout.spinner_style_for_advance_search, item_sub_list_for_run);
        ArrayAdapter<String> adapter_item_times = new ArrayAdapter<String>(this,
                R.layout.spinner_style_for_advance_search, times);

        main_item.setAdapter(adapter_main_item);
        sub_item.setAdapter(adapter_sub_item);
        item_times.setAdapter(adapter_item_times);      // 功能：灰色預設值

        listen(main_item, sub_item, item_times, time, note, imageButton);

    }

    private void addNewItem(final int count) {

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");

        LinearLayout linearLayout_EnterItemList = (LinearLayout) findViewById(R.id.linearLayout_enter_item_list);
        LinearLayout linearLayout_main = new LinearLayout(this);
        linearLayout_main.setOrientation(LinearLayout.VERTICAL);
        linearLayout_main.setId(count);
        LinearLayout linearLayout_1 = new LinearLayout(this);
        linearLayout_1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout_1.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout linearLayout_2 = new LinearLayout(this);
        linearLayout_2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout_2.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout linearLayout_3 = new LinearLayout(this);
        linearLayout_3.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout_3.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout linearLayout_4 = new LinearLayout(this);
        linearLayout_4.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout_4.setGravity(Gravity.RIGHT);

        LinearLayout.LayoutParams sp_LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        sp_LLParams.height = 70;
        Spinner sp_main_item = new Spinner(this);
        sp_main_item.setLayoutParams(sp_LLParams);
        Spinner sp_sub_item = new Spinner(this);
        sp_sub_item.setLayoutParams(sp_LLParams);
        Spinner sp_item_times = new Spinner(this);
        sp_item_times.setLayoutParams(sp_LLParams);

        LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams tv_LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams et_LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams et_LLParams_1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LLParams.height = 65;             // 設定 LinearLayout 的高度
        tv_LLParams.setMargins(25, 0, 0, 0);    // 設定 TextView 的 Margin 距離

        TextView textView = new TextView(this);
        textView.setText("項目" + count);        // count 為項目數量
        textView.setTextColor(Color.BLACK);
        textView.setLayoutParams(tv_LLParams);
        textView.setTypeface(typeface, typeface.BOLD);

        // 取消項目
        linearLayout_4.setLayoutParams(LLParams);
        final ImageButton bt_item_cancel = new ImageButton(this);
        bt_item_cancel.setId(count);
        bt_item_cancel.setBackgroundColor(Color.parseColor("#C3D69B"));
        bt_item_cancel.setImageDrawable(getResources().getDrawable(R.drawable.item_cancel));
        linearLayout_4.addView(bt_item_cancel);

        linearLayout_1.setLayoutParams(LLParams);
        linearLayout_1.addView(textView);
        linearLayout_1.addView(linearLayout_4);
        linearLayout_1.setBackgroundColor(Color.parseColor("#C3D69B"));

        TextView textView_1 = new TextView(this);
        textView_1.setText("秒數 : ");
        textView_1.setTextSize(16);
        textView_1.setTypeface(typeface, typeface.BOLD);
        tv_LLParams.setMargins(30, 0, 10, 0);
        textView_1.setLayoutParams(tv_LLParams);

        EditText editText_1 = new EditText(this);
        editText_1.setHint("請輸入訓練時限");
        editText_1.setTypeface(typeface);
        editText_1.setTextSize(16);
        editText_1.setBackgroundResource(R.drawable.enter_item_list_edittext_style);
        editText_1.setLayoutParams(et_LLParams);

        TextView textView_2 = new TextView(this);
        textView_2.setText("備註 : ");
        textView_2.setTextSize(16);
        textView_2.setTypeface(typeface, typeface.BOLD);
        tv_LLParams.setMargins(30, 0, 10, 0);
        textView_2.setLayoutParams(tv_LLParams);

        EditText editText_2 = new EditText(this);
        editText_2.setTextSize(16);
        editText_2.setTypeface(typeface);
        editText_2.setLayoutParams(et_LLParams_1);
        editText_2.setBackgroundResource(R.drawable.enter_item_list_edittext_style);

        linearLayout_2.addView(textView_1);
        linearLayout_2.addView(editText_1);
        linearLayout_3.addView(textView_2);
        linearLayout_3.addView(editText_2);

        sp_main_item.setId(count);
        sp_sub_item.setId(count);
        sp_item_times.setId(count);
        editText_1.setId(count);
        editText_2.setId(count);

        ArrayAdapter<String> adapter_main_item = new ArrayAdapter<String>(this,
                R.layout.spinner_style_for_advance_search, item_list);
        ArrayAdapter<String> adapter_sub_item = new ArrayAdapter<String>(this,
                R.layout.spinner_style_for_advance_search, item_sub_list_for_run);
        ArrayAdapter<String> adapter_item_times = new ArrayAdapter<String>(this,
                R.layout.spinner_style_for_advance_search, times);
        sp_main_item.setAdapter(adapter_main_item);
        sp_sub_item.setAdapter(adapter_sub_item);
        sp_item_times.setAdapter(adapter_item_times);

        assert linearLayout_EnterItemList != null;
        linearLayout_main.addView(linearLayout_1);
        linearLayout_main.addView(sp_main_item);
        linearLayout_main.addView(sp_sub_item);
        linearLayout_main.addView(sp_item_times);
        linearLayout_main.addView(linearLayout_2);
        linearLayout_main.addView(linearLayout_3);

        linearLayout_EnterItemList.addView(linearLayout_main);
        listen(sp_main_item, sp_sub_item, sp_item_times, editText_1, editText_2, bt_item_cancel);

    }

    private void listen(final Spinner sp_main, final Spinner sp_sub_item, final Spinner sp_item_times, final EditText et_time, final EditText et_note, final ImageButton bt_item_cancel) {

        sp_main.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String main_item = parent.getSelectedItem().toString();
                String Id = String.valueOf(sp_main.getId());
                bundle.putString(Id, main_item);
                System.out.println("項目" + Id + " - 主項目 " + " - " + main_item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_sub_item.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String sub_item = parent.getSelectedItem().toString();
                String judge = "sub_item";
                String Id = String.valueOf(sp_sub_item.getId());
                bundle.putString(Id + judge, sub_item);
                System.out.println("項目" + Id + " - " + judge + " - " + sub_item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_item_times.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String item_times = parent.getSelectedItem().toString();
                String judge = "item_times";
                String Id = String.valueOf(sp_item_times.getId());
                bundle.putString(Id + judge, item_times);
                System.out.println("項目" + Id + " - " + judge + " - " + item_times);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        et_time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String judge = "time";
                String Id = String.valueOf(et_time.getId());
                String time = et_time.getText().toString();
                bundle.putString(Id + judge, time);
            }
        });

        et_note.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                String judge = "note";
                String Id = String.valueOf(et_note.getId());
                String note = et_note.getText().toString();
                bundle.putString(Id + judge, note);
            }
        });

        bt_item_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout_enter_item_list);
                LinearLayout linearLayout_main = (LinearLayout) findViewById(bt_item_cancel.getId());
                linearLayout.removeView(linearLayout_main);

            }
        });

    }

    private boolean check() {

        int error = 0;
        String SUB = "sub_item";
        String TIMES = "item_times";
        String TIME = "time";

        for (int i = 1; i <= count; i++) {

            String ID = String.valueOf(i);
            String main_item = bundle.getString(ID);
            String sub_item = bundle.getString(ID + SUB);
            String item_times = bundle.getString(ID + TIMES);
            String time = bundle.getString(ID + TIME);

            if (main_item.equals("請選擇訓練項目")) {
                Toast.makeText(this, "尚未選擇訓練項目", Toast.LENGTH_SHORT).show();
                error++;
            } else if (sub_item.equals("請選擇訓練子項目")) {
                Toast.makeText(this, "尚未選擇子訓練項目", Toast.LENGTH_SHORT).show();
                error++;
            } else if (item_times.equals("請選擇訓練組數")) {
                Toast.makeText(this, "尚未選擇訓練組數", Toast.LENGTH_SHORT).show();
                error++;
            } else if (time == null) {
                Toast.makeText(this, "尚未填寫訓練秒數", Toast.LENGTH_SHORT).show();
                error++;
            }
        }

        return error == 0;  // error是0 , 則 return true

    }

    public void cancel(View view) {
        SelectCourse.instance.finish();
        SelectCrowd.instance.finish();
        CreateSelectDate.instance.finish();
        CreateNewPlan.instance.finish();
        EnterItemList.instance.finish();
    }

//    public void itemCancel(LinearLayout linearLayout) {
//
//        CANCEL = true;
//        count = count -1;
//        LinearLayout linearLayout_enter_item_list = (LinearLayout) findViewById(R.id.linearLayout_enter_item_list);
//        linearLayout_enter_item_list.removeView(linearLayout);
//
//        restart();
//
//    }

    public void submit_train_plan(View view) {

        if (check()) {
            intent.setClass(this, SubmitTrainPlan.class);
            bundle.putInt("count", count);  // 項目數量
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}