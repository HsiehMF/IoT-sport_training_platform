package com.example.administrator.myconnet.Function.Public;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.R;

import java.util.ArrayList;
import java.util.Collections;

public class AlterItemList extends AppCompatActivity {

    Bundle bundle = new Bundle();
    final String[] times ={ "請選擇訓練組數", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
    final String[] main_item = { "請選擇訓練項目", "跑步", "重訓" };
    final String[] sub_item_for_run ={ "請選擇訓練子項目", "100", "200", "400", "800", "1600", "3200", "6400", "10000" };
    ArrayList<String> arrayList_id = new ArrayList<>();
    ArrayList<String> arrayList_main = new ArrayList<>();
    ArrayList<String> arrayList_sub = new ArrayList<>();
    ArrayList<String> arrayList_times = new ArrayList<>();
    ArrayList<String> arrayList_time = new ArrayList<>();
    ArrayList<String> arrayList_note = new ArrayList<>();
    String schedule_num;
    private boolean change;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_item_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("修改訓練細項");
        toolbar_title.setTypeface(typeface);

        // 設定字體
        Button button1 = (Button) findViewById(R.id.button34);
        Button button2 = (Button) findViewById(R.id.button35);
        button1.setTypeface(typeface);
        button2.setTypeface(typeface);

        TextView textView1 = (TextView) findViewById(R.id.tv_alter_item_list_schedule_name);
        textView1.setTypeface(typeface, typeface.BOLD);
        TextView textView2 = (TextView) findViewById(R.id.textView56);
        textView2.setTypeface(typeface, typeface.BOLD);

        init();

    }

    private void init() {

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        bundle = getIntent().getExtras();

        // 設置課表名稱
        String schedule_name = bundle.getString("schedule_name");
        TextView tv_schedule_name = (TextView) findViewById(R.id.tv_alter_item_list_schedule_name);
        assert tv_schedule_name != null;
        tv_schedule_name.setText(schedule_name);

        // 儲存功能
        Button button = (Button) findViewById(R.id.button35);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                alertDialogFragment.show(fragmentManager, "alert");

            }
        });

        // 抓取課表細項資料
        String x = bundle.getString("train_item_detail");
        String[] train_item_detail = x.split("&");

        LinearLayout linearLayout_EnterItemList = (LinearLayout) findViewById(R.id.linearLayout_alter_item_list);

        for (String y : train_item_detail) {

            ArrayList<String> arrayList_1 = new ArrayList<>();
            ArrayList<String> arrayList_2 = new ArrayList<>();
            ArrayList<String> arrayList_3 = new ArrayList<>();

            Collections.addAll(arrayList_1, main_item);
            Collections.addAll(arrayList_2, sub_item_for_run);
            Collections.addAll(arrayList_3, times);

            String[] main = y.split(",");       // 切割所收到的課表資料

            LinearLayout linearLayout_main = new LinearLayout(this);
            linearLayout_main.setOrientation(LinearLayout.VERTICAL);
            LinearLayout linearLayout_1 = new LinearLayout(this);
            linearLayout_1.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout_1.setGravity(Gravity.CENTER_VERTICAL);
            LinearLayout linearLayout_2 = new LinearLayout(this);
            linearLayout_2.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout_2.setGravity(Gravity.CENTER_VERTICAL);
            LinearLayout linearLayout_3 = new LinearLayout(this);
            linearLayout_3.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout_3.setGravity(Gravity.CENTER_VERTICAL);

            // 設置 schedule_num , 若有新增項目即可使用
            schedule_num = main[0];

            // 用來檢查有無變更
            arrayList_id.add(main[1]);
            arrayList_main.add(main[2]);
            arrayList_sub.add(main[3]);
            arrayList_times.add(main[4]);
            arrayList_time.add(main[5]);
            arrayList_note.add(main[6]);

            // 用來設置 Spinner 的第一項為取得的舊有訓練項目
            arrayList_1.add(0, main[2]);
            arrayList_2.add(0, main[3]);
            arrayList_3.add(0, main[4]);

            ArrayAdapter<String> arrayAdapter_1 = new ArrayAdapter<String>(this, R.layout.spinner_style_for_advance_search , arrayList_1 );
            ArrayAdapter<String> arrayAdapter_2 = new ArrayAdapter<String>(this, R.layout.spinner_style_for_advance_search , arrayList_2 );
            ArrayAdapter<String> arrayAdapter_3 = new ArrayAdapter<String>(this, R.layout.spinner_style_for_advance_search , arrayList_3 );

            LinearLayout.LayoutParams sp_LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            sp_LLParams.height = 70;
            Spinner sp_main_item = new Spinner(this);
            sp_main_item.setLayoutParams(sp_LLParams);
            Spinner sp_sub_item = new Spinner(this);
            sp_sub_item.setLayoutParams(sp_LLParams);
            Spinner sp_item_times = new Spinner(this);
            sp_item_times.setLayoutParams(sp_LLParams);

            sp_main_item.setId(count);
            sp_sub_item.setId(count);
            sp_item_times.setId(count);
            sp_main_item.setAdapter(arrayAdapter_1);
            sp_sub_item.setAdapter(arrayAdapter_2);
            sp_item_times.setAdapter(arrayAdapter_3);

            LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            LinearLayout.LayoutParams tv_LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LLParams.height = 65;                   // 設定 LinearLayout 的高度
            tv_LLParams.setMargins(25, 0, 0, 0);    // 設定 TextView 的 Margin 距離
            TextView textView = new TextView(this);
            textView.setText("項目" + count);       // count 為項目數量
            textView.setTextColor(Color.BLACK);
            textView.setLayoutParams(tv_LLParams);
            textView.setTypeface(typeface, typeface.BOLD);

            linearLayout_1.setLayoutParams(LLParams);
            linearLayout_1.addView(textView);
            linearLayout_1.setBackgroundColor(Color.parseColor("#C3D69B"));

            LinearLayout.LayoutParams et_LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextView textView_1 = new TextView(this);
            textView_1.setText("秒數 : ");
            textView_1.setTextSize(16);
            textView_1.setTypeface(typeface, typeface.BOLD);
            tv_LLParams.setMargins(30, 0, 10, 0);
            textView_1.setLayoutParams(tv_LLParams);

            EditText editText_1 = new EditText(this);
            editText_1.setHint("請輸入訓練時限");
            editText_1.setTextSize(16);
            editText_1.setTypeface(typeface);
            editText_1.setLayoutParams(et_LLParams);
            editText_1.setBackgroundResource(R.drawable.enter_item_list_edittext_style);

            LinearLayout.LayoutParams et_LLParams_1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
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

            editText_1.setId(count);
            editText_2.setId(count);
            editText_1.setText(main[5]);
            editText_2.setText(main[6]);

            linearLayout_2.addView(textView_1);
            linearLayout_2.addView(editText_1);
            linearLayout_3.addView(textView_2);
            linearLayout_3.addView(editText_2);

            assert linearLayout_EnterItemList != null;
            linearLayout_main.addView(linearLayout_1);
            linearLayout_main.addView(sp_main_item);
            linearLayout_main.addView(sp_sub_item);
            linearLayout_main.addView(sp_item_times);
            linearLayout_main.addView(linearLayout_2);
            linearLayout_main.addView(linearLayout_3);
            linearLayout_EnterItemList.addView(linearLayout_main);

            listen(sp_main_item, sp_sub_item, sp_item_times, editText_1, editText_2);
            count++;

        }

    }

    public void listen(final Spinner sp_main_item, final Spinner sp_sub_item, final Spinner sp_item_times, final EditText et_time , final EditText et_note ) {

        System.out.println();

        sp_main_item.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String Id = String.valueOf(sp_main_item.getId());
                String main_item = parent.getSelectedItem().toString();
                bundle.putString(Id, main_item);
                System.out.println("項目" + Id + " - 主項目 " + " - " + main_item);

                if (sp_main_item.getId() > arrayList_main.size()) {
                    int size = arrayList_main.size();
                    if (!main_item.equals("請選擇訓練項目"))
                        arrayList_main.add(size, main_item);
                } else if (sp_main_item.getId() == arrayList_main.size() & change) {
                    if (!main_item.equals("請選擇訓練項目"))
                        arrayList_main.set(arrayList_main.size() - 1, main_item);
                }

                System.out.println(arrayList_main);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_sub_item.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String sub_item = parent.getSelectedItem().toString();
                String judge = "sub_item";
                String Id = String.valueOf(sp_sub_item.getId());
                bundle.putString(Id + judge, sub_item);
                System.out.println("項目" + Id + " - " + judge + " - " + sub_item);

                if (sp_sub_item.getId() > arrayList_sub.size()) {
                    int size = arrayList_sub.size();
                    if (!sub_item.equals("請選擇訓練子項目"))
                        arrayList_sub.add(size, sub_item);
                } else if (sp_sub_item.getId() == arrayList_sub.size() & change) {
                    if (!sub_item.equals("請選擇訓練子項目"))
                        arrayList_sub.set(arrayList_sub.size() - 1, sub_item);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_item_times.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item_times = parent.getSelectedItem().toString();
                String judge = "item_times";
                String Id = String.valueOf(sp_item_times.getId());
                bundle.putString(Id + judge, item_times);
                System.out.println("項目" + Id + " - " + judge + " - " + item_times);

                if (sp_item_times.getId() > arrayList_times.size()) {
                    int size = arrayList_times.size();
                    if (!item_times.equals("請選擇訓練組數"))
                        arrayList_times.add(size, item_times);
                } else if (sp_item_times.getId() == arrayList_times.size() & change) {
                    if (!item_times.equals("請選擇訓練組數"))
                        arrayList_times.set(arrayList_times.size()-1, item_times);
                }

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

                String judge = "item_time";
                String Id = String.valueOf(et_time.getId());
                String time = et_time.getText().toString();
                bundle.putString(Id + judge, time);

                // 當使用者新增項目時 , count(et_note) > arrayList
                if (et_time.getId() > arrayList_time.size()) {
                    int size = arrayList_time.size();
                    arrayList_time.add(size, time);
                }
                // 新增項目選取後 , 若使用者更改選項則覆蓋 , AddNewItem 發生觸發 change = true , 若無此行 , 則每個課表最後一項無法更新成功
                else if (et_time.getId() == arrayList_time.size() & change) {
                    arrayList_time.set(arrayList_time.size() - 1, time);
                }

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
            public void afterTextChanged(Editable s) {

                String judge = "item_note";
                String Id = String.valueOf(et_note.getId());
                String note = et_note.getText().toString();
                bundle.putString(Id + judge, note);

                // 當使用者新增項目時 , count(et_note) > arrayList
                if (et_note.getId() > arrayList_note.size()) {
                    int size = arrayList_note.size();
                    arrayList_note.add(size, note);
                }
                // 新增項目選取後 , 若使用者更改選項則覆蓋 , AddNewItem 發生觸發 change = true , 若無此行 , 則每個課表最後一項無法更新成功
                else if (et_note.getId() == arrayList_note.size() & change) {
                    arrayList_note.set(arrayList_note.size() - 1, note);
                }

            }
        });

    }

    public class AlertDialogFragment
            extends DialogFragment implements DialogInterface.OnClickListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle("儲存")
                    .setIcon(R.drawable.ic_logout_black_24dp)
                    .setMessage("確定儲存變更的課表細項 ? ")
                    .setPositiveButton("是", this)
                    .setNegativeButton("否", this)
                    .create();
        }

        //執行選擇的選項
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    saveAlter();
                    getActivity().finish();
                    Toast.makeText(getApplicationContext(), "儲存成功", Toast.LENGTH_SHORT).show();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.cancel();
                    break;
                default:
                    break;
            }
        }
    }

    public void alterCancel(View view) { finish(); }

    public void alterAddNewItem(View view) {

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        change = true;

        LinearLayout linearLayout_EnterItemList = (LinearLayout) findViewById(R.id.linearLayout_alter_item_list);
        LinearLayout linearLayout_main = new LinearLayout(this);
        linearLayout_main.setOrientation(LinearLayout.VERTICAL);
        LinearLayout linearLayout_1 = new LinearLayout(this);
        linearLayout_1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout_1.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout linearLayout_2 = new LinearLayout(this);
        linearLayout_2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout_2.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout linearLayout_3 = new LinearLayout(this);
        linearLayout_3.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout_3.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout.LayoutParams sp_LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        sp_LLParams.height = 70;
        Spinner sp_main_item = new Spinner(this);
        sp_main_item.setLayoutParams(sp_LLParams);
        Spinner sp_sub_item = new Spinner(this);
        sp_sub_item.setLayoutParams(sp_LLParams);
        Spinner sp_item_times = new Spinner(this);
        sp_item_times.setLayoutParams(sp_LLParams);

        sp_main_item.setId(count);
        sp_sub_item.setId(count);
        sp_item_times.setId(count);

        LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams tv_LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LLParams.height = 65;             // 設定 LinearLayout 的高度
        tv_LLParams.setMargins(25, 0, 0, 0);    // 設定 TextView 的 Margin 距離
        TextView textView = new TextView(this);
        textView.setText("項目" + count);        // count 為項目數量
        textView.setTextColor(Color.BLACK);
        textView.setLayoutParams(tv_LLParams);
        textView.setTypeface(typeface, typeface.BOLD);

        linearLayout_1.setLayoutParams(LLParams);
        linearLayout_1.addView(textView);
        linearLayout_1.setBackgroundColor(Color.parseColor("#C3D69B"));

        LinearLayout.LayoutParams et_LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView textView_1 = new TextView(this);
        textView_1.setText("秒數 : ");
        textView_1.setTextSize(16);
        textView_1.setTypeface(typeface, typeface.BOLD);
        tv_LLParams.setMargins(30, 0, 10, 0);
        textView_1.setLayoutParams(tv_LLParams);

        EditText editText_1 = new EditText(this);
        editText_1.setHint("請輸入訓練時限");
        editText_1.setTextSize(16);
        editText_1.setTypeface(typeface);
        editText_1.setLayoutParams(et_LLParams);
        editText_1.setBackgroundResource(R.drawable.enter_item_list_edittext_style);

        LinearLayout.LayoutParams et_LLParams_1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
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

        editText_1.setId(count);
        editText_2.setId(count);

        linearLayout_2.addView(textView_1);
        linearLayout_2.addView(editText_1);
        linearLayout_3.addView(textView_2);
        linearLayout_3.addView(editText_2);

        ArrayAdapter<String> adapter_main_item = new ArrayAdapter<String>(this,
                R.layout.spinner_style_for_advance_search, main_item);
        ArrayAdapter<String> adapter_sub_item = new ArrayAdapter<String>(this,
                R.layout.spinner_style_for_advance_search, sub_item_for_run);
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

        listen(sp_main_item, sp_sub_item, sp_item_times, editText_1, editText_2);
        count++;
    }

    public void saveAlter() {

        String SUB = "sub_item";
        String TIMES = "item_times";
        String TIME = "item_time";
        String NOTE = "item_note";

        // 先取得介面上的資料 , 接著比對與舊資料有無不同
        for (int i = 1; i < count; i++) {

            String ID = String.valueOf(i);
            String main_item = bundle.getString(ID);
            String sub_item = bundle.getString(ID + SUB);
            String item_times = bundle.getString(ID + TIMES);
            String item_time = bundle.getString(ID + TIME);
            String item_note = bundle.getString(ID + NOTE);

            // 新增項目時 , i 會大於 arrayList.size , 否則 i 小於 arrayList.size
            if (i > arrayList_id.size()) {
                // 當 EditText 未輸入任何值時 , 會是 null , 給 item_time, item_note 原本的值
                if (item_time == null) { item_time = ""; }
                if (item_note == null) { item_note = "null"; }
            } else {
                if (item_time == null) { item_time = arrayList_time.get(i - 1); }
                if (item_note == null) { item_note = arrayList_note.get(i - 1); }
            }

            // 如果項目有不同 , 做更新的動作
            if (check(main_item, sub_item, item_times, item_time, item_note, i)) {

                // 當未出現新項目時 , arrayList 溢出
                if (i > arrayList_id.size()) {
                    String method = "增加新項目-套用";
                    BackgroundTask_public backgroundTask_public = new BackgroundTask_public(this);
                    backgroundTask_public.execute(method, schedule_num, main_item, sub_item, item_times, item_time, item_note);
                }
                // 當未出現新項目時 , arrayList 不會溢出
                if (i <= arrayList_id.size()) {
                    String method = "更改套用課表之項目";
                    String item_id = arrayList_id.get(i - 1);
                    BackgroundTask_public backgroundTask_public = new BackgroundTask_public(this);
                    backgroundTask_public.execute(method, main_item, sub_item, item_times, item_time, item_note, item_id);
                }
            }
        }

    }

    private boolean check(String main_item, String sub_item, String item_times, String item_time, String item_note, int i) {

        // 如果創新項目時 , size 會大於原本容量造成錯誤 , 所以一但增加項目時直接回傳 true , 表示有變更
        if (i <= arrayList_id.size()) {
            if (!main_item.equals(arrayList_main.get(i - 1)) || !sub_item.equals(arrayList_sub.get(i - 1)) || !item_times.equals(arrayList_times.get(i - 1)) || !item_time.equals(arrayList_time.get(i - 1)) || !item_note.equals(arrayList_note.get(i - 1))) {
                return true;
            }
        } else if (i > arrayList_id.size()) { return true; }

        return false;
    }

}
