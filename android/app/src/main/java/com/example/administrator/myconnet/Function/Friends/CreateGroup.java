package com.example.administrator.myconnet.Function.Friends;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.myconnet.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CreateGroup extends AppCompatActivity {

    private static final int resultCode = 2;
    ArrayList<String> O_CHOOSE = new ArrayList<>();
    Intent intent = new Intent();
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("新增群組");
        toolbar_title.setTypeface(typeface);

        TextView textView = (TextView) findViewById(R.id.textView46);
        textView.setTypeface(typeface);
        EditText editText = (EditText) findViewById(R.id.create_group_name);
        editText.setTypeface(typeface);

        bundle = getIntent().getExtras();

        ImageButton imageButton = (ImageButton) findViewById(R.id.imbt_create_group_add);
        assert imageButton != null;
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String method = "CourseDetail_student_list";
                SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
                String UID = sharedPreferences.getString("UID", "UID doesn't exist");
                String course_name = bundle.getString("course_name");
                BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(getApplicationContext());
                try {

                    String x = backgroundTask_new_course.execute(method, course_name, UID).get();
                    String[] student_list = x.split(",");
                    bundle.putStringArray("student_list", student_list);
                    intent.putExtras(bundle);
                    intent.setClass(getApplicationContext(), ChoosePlayer.class);
                    startActivityForResult(intent, resultCode);

                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            Bundle bundleResult = data.getExtras();
            ArrayList<String> arrayList = bundleResult.getStringArrayList("selected_player");
            O_CHOOSE.addAll(arrayList);
            ListView listView = (ListView) findViewById(R.id.Lv_create_group);
            ArrayAdapter<String> arrayAdapter = new CustomAdapter(this, O_CHOOSE, bundle);
            listView.setAdapter(arrayAdapter);

            // ChoosePlayer 完成後執行的動作 , 但若上述 ListView 未將資料放進 bundle 會造成誤判 , 因為 ListView 選手變更時就要儲存
            String[] selected_player = new String[O_CHOOSE.size()];
            selected_player = O_CHOOSE.toArray(selected_player);
            bundle.putStringArray("selected_player", selected_player);   // 放到 Bundle 內回傳給前一頁

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_group, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.create_group_done) {

            EditText editText = (EditText)findViewById(R.id.create_group_name);
            String group_name = editText.getText().toString();
            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID", "UID doesn't found");
            String[] selected_player = bundle.getStringArray("selected_player");
            String course_name = bundle.getString("course_name");

                if (group_name.equals("")) {

                    Toast.makeText(this,"群組名稱尚未填寫",Toast.LENGTH_SHORT).show();      // if 群組名稱 = "空"

                } else {
                    String method1 = "CreateGroup";
                        if (selected_player == null) {
                            String method2 = "CreateGroupNP";   // No Player
                            BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(this);
                            backgroundTask_new_course.execute(method2, UID, group_name, course_name);
                        } else if (selected_player.length == 0) {
                            String method2 = "CreateGroupNP";   // No Player
                            BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(this);
                            backgroundTask_new_course.execute(method2, UID, group_name, course_name);
                        } else {
                            for (int i = 0; i < selected_player.length; i++) {
                                BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(this);
                                backgroundTask_new_course.execute(method1, UID, group_name, selected_player[i], course_name);
                            }
                        }

                    Toast.makeText(this, "群組建立成功", Toast.LENGTH_SHORT).show();

                    intent.setClass(this, ManageCrowd.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class CustomAdapter extends ArrayAdapter<String> {

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        Bundle bundle = new Bundle();
        View customView;
        ArrayList<String> O_CHOOSE = new ArrayList<>();

        public CustomAdapter(Context context, ArrayList<String> O_CHOOSE, Bundle bundle) {

            super(context, R.layout.listview_for_create_group, O_CHOOSE);
            this.O_CHOOSE = O_CHOOSE;
            this.bundle = bundle;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            customView = inflater.inflate(R.layout.listview_for_create_group, parent, false);

            final TextView textView = (TextView) customView.findViewById(R.id.tv_style_for_create_group);
            final ImageView imageView = (ImageView) customView.findViewById(R.id.iv_style_for_create_group);
            final LinearLayout linearLayout = (LinearLayout) customView.findViewById(R.id.LL_style_for_create_group);

            textView.setId(position);
            imageView.setId(position);
            linearLayout.setId(position);

            String selectedItem = getItem(position);
            textView.setText(selectedItem);
            textView.setTypeface(typeface);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String selected = textView.getText().toString();       // 選手名稱
                    linearLayout.setVisibility(View.GONE);                // 將選手列表去除
                    O_CHOOSE.remove(selected);                             // 移除在陣列中的選手
                    String[] selected_player = new String[O_CHOOSE.size()];
                    selected_player = O_CHOOSE.toArray(selected_player);
                    bundle.putStringArray("selected_player", selected_player);  // 儲存至 bundle , 否則該 bundle 永遠是外面所儲存的變數

                }
            });

            return customView;
        }
    }

}
