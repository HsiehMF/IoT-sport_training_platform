package com.example.administrator.myconnet.Function.Course;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.BackgroundTask_coach;
import com.example.administrator.myconnet.BackgroundTask_find;
import com.example.administrator.myconnet.BackgroundTask_my_information;
import com.example.administrator.myconnet.R;
import com.example.administrator.myconnet.webview;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class NewCourse extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    ArrayAdapter<String> adapter;
    Map<String, String> condition1 = new HashMap<String,String>();
    ScrollView scrollView;
    String Date = "null";
    String string,path1,path2,path3,flag_p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView = (TextView) findViewById(R.id.new_course_noCourse);
        textView.setTypeface(typeface);
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setTypeface(typeface);

        Button button1 = (Button) findViewById(R.id.button19);
        Button button2 = (Button) findViewById(R.id.button8);
        Button button3 = (Button) findViewById(R.id.bt_MESSAGE_SEND);
        button1.setTypeface(typeface);
        button2.setTypeface(typeface);
        button3.setTypeface(typeface);

        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't exist");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis()) ; // 獲取當前時間
        String nowTime = formatter.format(curDate);//        setTitle( "公佈欄 ( " + nowTime + " ) ");  // 設定 title ，以今天日期為標題
        Bundle bundle = getIntent().getExtras();

        Date = bundle.getString("curDay");
        if (Date == null){
            Date=nowTime;
        }

//-----------------------------------------------------------------------------------------------------演算法

        string = bundle.getString("Location");
        if (string != null){
            String []x=string.split("@");
            flag_p=x[5];
            path1=x[0];
            path2=x[1];
            Date=x[2];
            final String filename=x[3];
            path3=x[4];
            if (string != null){
                if (path2.contains("sec")){
                    Algo_convert_1 algo_convert_1=new Algo_convert_1(filename+"_1",path1,this);
                    algo_convert_1.start();
                    Algo_convert_1 algo_convert_2=new Algo_convert_1(filename+"_0",path2,this);
                    algo_convert_2.start();
                    if(path3.contains("third")){
                        Algo_convert_2 algo_convert_21=new Algo_convert_2(filename+"_heartrate",path3,this);
                        algo_convert_21.start();
                    }
                }else {
                    Algo_convert_1 algo_convert_1=new Algo_convert_1(filename+"_1",path1,this);
                    algo_convert_1.start();
                    Algo_convert_1 algo_convert_2=new Algo_convert_1(filename+"_0",path2,this);
                    algo_convert_2.start();
                    if(path3.contains("third")){
                        Algo_convert_2 algo_convert_21=new Algo_convert_2(filename+"_heartrate",path3,this);
                        algo_convert_21.start();
                    }

                }
            }
            try {
                File dir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                File file = new File( dir.getParent() + "/" + dir.getName() + "/MyAndroid");
                file.delete();
            }catch (Exception e){

            }
        }

//---------------------------------------------------------------------------------------------------------------------

        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("公佈欄 ( " + Date + " ) ");
        toolbar_title.setTypeface(typeface);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec1 = tabHost.newTabSpec("tab1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("今日課表");
        tabHost.addTab(spec1);

        TabHost.TabSpec spec2 = tabHost.newTabSpec("tab2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("意見回覆");
        tabHost.addTab(spec2);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equals("tab2")) {
                    scrollView = (ScrollView) findViewById(R.id.scrollView2);
                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.fullScroll(View.FOCUS_DOWN);
                        }
                    });
                }
            }
        });

        /* -----------------取得課表-------------- */
        getcourse getcourse=new getcourse(this,Date,UID);
        getcourse.start();
 
        /* -----------------意見回覆-------------- */
        Chat chat=new Chat(this,Date);
        chat.start();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_course, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//  -----------------------------------------------------------------------------------------------------------------------進入日曆
            Intent intent=new Intent();
            intent.setClass( NewCourse.this, NewCourseCalendar.class);
            startActivity(intent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
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

            // 已在此頁

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
    //    ------------------------------------------------------  自評
    public void userSelf(View view) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis()) ; // 獲取當前時間
        String nowTime = formatter.format(curDate);//        setTitle( "公佈欄 ( " + nowTime + " ) ");  // 設定 title ，以今天日期為標題
        if (Date.equals(nowTime)) {
            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String uid = sharedPreferences.getString("UID", "UID doesn't exist");
            String filename = uid + "_" + nowTime;
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("filename", filename);
            intent.putExtras(bundle);
            intent.setClass(this, EndTraining.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(getApplicationContext(), " 時機不對，當日課程才能做自評 ", Toast.LENGTH_SHORT).show();
        }
    }

    public void SendingContent(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't exist");
        String content;
        EditText editText=(EditText)findViewById(R.id.editText);
        content = editText.getText().toString();
        InsertTalk insertTalk = new InsertTalk(this, UID, Date, content);
        insertTalk.run();
        editText.setText("");
    }

    public class Chat extends Thread {

        String nowTime;
        Context context;

        public Chat(Context c,String date) {

            context = c;
            nowTime = date;

        }

        public void run() {
            int i = 0;
            while(true) {
                invalidateOptionsMenu();
                String method = "player_talk";
                SharedPreferences sharedPreferences = getSharedPreferences("prefs",MODE_PRIVATE);
                String UID = sharedPreferences.getString("UID", "UID doesn't exist");
                BackgroundTask_talk backgroundTask_talk = new BackgroundTask_talk(context);
                try {
                    String result = backgroundTask_talk.execute(method, UID, nowTime).get();
                    String[] talk = result.split("◎");
                        while (i < talk.length - 1) {
                            condition1.put("data" + "i", talk[i]);
                            i++;
                            System.out.println(talk[i]);
                            handler.obtainMessage(1, 4, -1, talk[i])
                                    .sendToTarget();
                        }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
            if (msg.what == 1) {

                String str = (String)msg.obj;
                str = str.trim();
                System.out.println(str);
                SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
                String UID = sharedPreferences.getString("UID", "UID doesn't exist");

                // 取得 ID
                String data[] = str.split("◆");
                LinearLayout.LayoutParams tv_LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                tv_LLParams.setMargins(0, 0, 0, 20);    // 設定 TextView 的 Margin 距離

                // 取得姓名與內容
                String CONTENT[] =data[1].split(" : ");

                TextView textView = new TextView(getApplicationContext());
                textView.setTextSize(15);
                textView.setTextColor(Color.BLACK);
                textView.setTypeface(typeface, typeface.BOLD);

                if (data[0].equals(UID)) {
                    textView.setText(CONTENT[1]);
                    textView.setGravity(Gravity.CENTER_VERTICAL);
                    textView.setPadding(25, 0, 25, 0);
                    textView.setBackground(getResources().getDrawable(R.drawable.message_style));
                    tv_LLParams.gravity = Gravity.RIGHT;
                    tv_LLParams.height = 80;
                    textView.setLayoutParams(tv_LLParams);
                } else {
                    textView.setText(CONTENT[0] + " : " + CONTENT[1]);
                    tv_LLParams.gravity = Gravity.LEFT;
                    textView.setLayoutParams(tv_LLParams);
                }

                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.talk_LinearLayout);
                linearLayout.addView(textView);

                // 自動跳到最底層
                scrollView = (ScrollView) findViewById(R.id.scrollView2);
                scrollView.post(new Runnable() {   //捲軸元件scrollView
                    @Override
                    public void run() {
                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
            super.handleMessage(msg);
        }

    };

    public class getcourse extends Thread{
        Context c;
        String Date;
        String UID;
        public getcourse(Context context,String date,String uid){
            c=context;
            Date=date;
            UID=uid;
        }
        public void run(){

            while (true){

                String string= null;

                try {

                    String method = "選手抓課表";
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date curDate = new Date(System.currentTimeMillis()) ; // 獲取當前時間
                    String nowTime = formatter.format(curDate);        // setTitle( "公佈欄 ( " + nowTime + " ) ");  // 設定 title ，以今天日期為標題
                    if (Date == null){
                        Date = nowTime;
                    }

                    BackgroundTask_find backgroundTask_find = new BackgroundTask_find(c);
                    string = backgroundTask_find.execute(method, UID, Date).get();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                handler1.obtainMessage(1, 4, -1, string.trim()).sendToTarget();

                try {
                    sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    Handler handler1 = new Handler() {

        int []count=new int[20];
        String UID;
        @Override
        public void handleMessage(Message msg) {
            final ArrayList<String> Course_list = new ArrayList<String>();
            SharedPreferences sharedPreferences = getSharedPreferences("prefs",MODE_PRIVATE);
            String uid = sharedPreferences.getString("UID", "UID doesn't exist");
            UID=uid;

            if (msg.what==1) {
                String x = msg.obj.toString();
                String[] courseArr = x.split("。");      // 用 。 做為區隔
                for (int i = 0 ; i < courseArr.length ; i++) {// 從[0]開始，否則會出錯
                    int temp=0;
                    String [] strings=courseArr[i].split("<");

                    for(int k=1;k<strings.length;k+=2){
                        String[] str=strings[k].split(",");
                        int T=0;
                        for(int L=0;L<str.length;L++){
                            if (str[L].equals(UID)){
                                T++;
                                temp=T;
                            }
                        }
                    }
                    count[i]=temp;
                    for(int j=0;j<strings.length;j+=2){
                        if(count[i]==1){
                            Course_list.add(strings[j]+"已完成");
                        }
                        else if(count[i]>=2){
                            Course_list.add(strings[j]+"已重做");
                        }else{
                            Course_list.add(strings[j]);
                        }
                    }
                }

                if ( courseArr[0].equals("") ) {      // 可以查看看如何判斷 ArrayList = 空值

                    TextView new_course_noCourse = (TextView) findViewById(R.id.new_course_noCourse);
                    new_course_noCourse.setVisibility(new_course_noCourse.VISIBLE);     // 如果今日無課程，通知選手

                }else {
                    listView = (ListView) findViewById(R.id.list_view1);
                    adapter = new ArrayAdapter<String>(NewCourse.this, android.R.layout.simple_list_item_1, Course_list);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            if(count[position]<2)
                            {
                                Intent intent = new Intent();
                                Bundle bundle=new Bundle();
//                            bundle.putString("training_content", parent.getItemAtPosition(position).toString()+","+position);
                                bundle.putString("training_content", parent.getItemAtPosition(position).toString());
                                bundle.putString("itemname", Course_list.get(position));
                                bundle.putString("curDay",Date);
                                bundle.putString("UID" , UID);
                                System.out.println("Course_list.get(position)= =" + Course_list.get(position));
                                intent.putExtras(bundle);
                                intent.setClass(getApplicationContext(), StartTraining.class);
                                try {
                                    File dir =NewCourse.this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                                    File file = new File( dir.getParent() + "/" + dir.getName() + "/MyAndroid");
                                    File[] fList=file.listFiles();
                                    if (fList.length != 0){
                                        for (File file1:fList){
                                            file1.delete();
                                        }
                                    }
                                }catch (Exception e){

                                }
                                startActivity(intent);
                                finish();
                                Toast.makeText(getBaseContext(), " 查看 " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getBaseContext(), " 已經做過了 " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
//
            }
            super.handleMessage(msg);
        }

    };
    public class InsertTalk extends Thread {

        Context c;
        String Date1;
        String UID;
        String Content;

        public InsertTalk(Context context,String uid,String date,String content) {

            c = context;
            Date1 = date;
            UID = uid;
            Content = content;

        }

        public void run() {

            String method = "選手插入對話";
            BackgroundTask_Insert backgroundTask_insert=new BackgroundTask_Insert(c);
            System.out.println("UID= "+UID+"\t DATE= "+Date1+"\tContent= "+Content);
            backgroundTask_insert.execute(method, UID, Date1, Content);

        }
    }

    public void update_data(View v){
        Intent intent = new Intent();
        intent.setClass(this,update.class);
        startActivity(intent);
        finish();
    }

}