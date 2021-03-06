package com.example.administrator.myconnet.Function.Friends;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.BackgroundTask;
import com.example.administrator.myconnet.Function.Invite.AddPlayer;
import com.example.administrator.myconnet.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ManageCrowdPlayers extends AppCompatActivity {

    Intent intent = new Intent();
    Bundle bundle = new Bundle();
    ArrayList listOne = new ArrayList();
    ArrayList listTwo = new ArrayList();
    MyCustomAdapter dataAdapter = null;
    private static final int resultCode = 1;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_crowd_players);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("??????????????????");
        toolbar_title.setTypeface(typeface);

        TextView textView1 = (TextView) findViewById(R.id.textView69);
        TextView textView2 = (TextView) findViewById(R.id.tv_manage_crowd_players_crowd_name);
        textView1.setTypeface(typeface);
        textView2.setTypeface(typeface,typeface.BOLD);

        Button button1 = (Button) findViewById(R.id.bt_dialog);
        Button button2 = (Button) findViewById(R.id.bt_manage_crowd_players_kick_out);
        button1.setTypeface(typeface);
        button2.setTypeface(typeface);

        CheckBox checkBox = (CheckBox) findViewById(R.id.cb_manage_crowd_players);
        checkBox.setTypeface(typeface);

        bundle = getIntent().getExtras();
        String crowd_name = bundle.getString("crowd_name");     // ??????????????????
        TextView textView = (TextView) findViewById(R.id.tv_manage_crowd_players_crowd_name);
        textView.setText(crowd_name);

        String x = bundle.getString("crowd_detail");
        String[] crowd_player_list = x.split("&");                      // ????????????
        bundle.putStringArray("crowd_player_list", crowd_player_list);  // ?????? , ????????????????????????

        displayListView(crowd_player_list);

        SearchView sv = (SearchView) findViewById(R.id.searchView7);     // ????????? SearchView ??? ID??????????????????
        assert sv != null;
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                dataAdapter.getFilter().filter(text);       // ?????? adapter ?????????
                return false;
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manage_crowd_players, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {       // ?????? menu ???????????????

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.manage_crowd_players_add_players) {

            try {
                addPlayers();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayListView(String[] crowd_player_list) {

        ArrayList<String> NAME = new ArrayList<>();
        ArrayList<String> MARK = new ArrayList<>();

        for (String m : crowd_player_list) {
            String[] main = m.split(",");
            NAME.add(main[0]);
            MARK.add(main[1]);
        }

        ArrayList<Country> countryArrayList1 = new ArrayList<Country>();
        ArrayList<Country> countryArrayList2 = new ArrayList<Country>();

        for (int i = 0; i < NAME.size(); i++) {
            Country country1 = new Country(NAME.get(i), false);
            Country country2 = new Country(MARK.get(i), false);
            countryArrayList1.add(country1);
            countryArrayList2.add(country2);
        }

        //create an ArrayAdapter from the String Array
        dataAdapter = new MyCustomAdapter(this, R.layout.country_info, countryArrayList1, countryArrayList2);
        ListView listView = (ListView) findViewById(R.id.lv_manage_crowd_players);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        CheckBox checkBox = (CheckBox) findViewById(R.id.cb_manage_crowd_players);
        assert checkBox != null;
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAllSelected();                     // ?????? , count + 1
                dataAdapter.notifyDataSetChanged();     // ?????? Adapter , ??????????????? MyCustomAdapter , ??????????????? count ????????????
            }
        });

        Button button1 = (Button) findViewById(R.id.bt_manage_crowd_players_kick_out);
        assert button1 != null;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                alertDialogFragment.show(fragmentManager, "alert");

            }
        });

    }

    private class MyCustomAdapter extends ArrayAdapter<Country> {

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        private ArrayList<Country> countryArrayList1;
        private ArrayList<Country> countryArrayList2;

        public MyCustomAdapter(Context context, int textViewResourceId, ArrayList<Country> countryList1, ArrayList<Country> countryList2) {
            super(context, textViewResourceId, countryList1);
            this.countryArrayList1 = new ArrayList<Country>();
            this.countryArrayList2 = new ArrayList<Country>();
            this.countryArrayList1.addAll(countryList1);
            this.countryArrayList2.addAll(countryList2);
        }

        private class ViewHolder {
            CheckBox name;      // holder ??? CheckBox , ????????????????????? CheckBox
            TextView textView;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.manage_crowd_player_info, null);

                holder = new ViewHolder();
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);  // ??? checkBox1 ?????? holder.name
                holder.textView = (TextView) convertView.findViewById(R.id.ci_mark);
                convertView.setTag(holder);     // ??? covertView ???????????? , ??????????????????

                holder.name.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        Country country = (Country) cb.getTag();
                        country.setSelected(cb.isChecked());
                    }
                });
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            // ????????? MyCustomAdapter

            Country country1 = countryArrayList1.get(position);        // countryList ?????? , ?????? Adapter ????????????
            Country country2 = countryArrayList2.get(position);
            holder.name.setText(country1.getName());
            holder.name.setTypeface(typeface, typeface.BOLD);
            holder.name.setTag(country1);
                // ?????? mark ??? null , ???????????? TextView
                if (country2.getName().equals("null")) {
                    holder.textView.setText("");
                } else {
                    holder.textView.setText("???" + country2.getName());
                    holder.textView.setTag(country2);
                    holder.textView.setTypeface(typeface, typeface.BOLD);
                }

                if (count % 2 != 0) {                               // ?????? count % 2 != 0 , ?????????????????? , ????????????????????????
                    holder.name.setChecked(true);                   // ?????? CheckBox ?????? ??????
                    country1.setSelected(true);                     // ?????? country  ?????? true
                } else {
                    holder.name.setChecked(false);                  // ?????? CheckBox ?????? ??????
                    country1.setSelected(false);                    // ?????? country  ?????? false
                }

            return convertView;
        }
    }

    public void checkAllSelected() {
        count = count + 1;
    }

    public class AlertDialogFragment
            extends DialogFragment implements DialogInterface.OnClickListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle("????????????")
                    .setIcon(R.drawable.ic_logout_black_24dp)
                    .setMessage("?????????????????? ? ")
                    .setPositiveButton("YES", this)
                    .setNegativeButton("NO", this)
                    .create();
        }

        //?????????????????????

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    kick_out_players();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.cancel();
                    break;
                default:
                    break;
            }
        }
    }

    private void kick_out_players() {

        ArrayList<String> kick_out_player_list = new ArrayList<String>();

        ArrayList<Country> countryList = dataAdapter.countryArrayList1;
        for (int i = 0; i < countryList.size(); i++) {
            Country country = countryList.get(i);
            if (country.isSelected()) {
                kick_out_player_list.add(country.getName());
            }
        }

        String method = "ManageCrowd_kick_out_players";
        bundle = getIntent().getExtras();
        String course_name = bundle.getString("course_name");
        String crowd_name = bundle.getString("crowd_name");
        for (int i = 0; i < kick_out_player_list.size(); i++) {
            BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(this);
            backgroundTask_new_course.execute(method, kick_out_player_list.get(i), course_name, crowd_name);
        }

        restart restart = new restart(this);
        restart.start();

    }

    public void addPlayers() throws ExecutionException, InterruptedException {

        String method = "CourseDetail_student_list";
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't exist");
        String course_name = bundle.getString("course_name");
        String[] crowd_player_list = bundle.getStringArray("crowd_player_list");
        BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(this);
        String x = backgroundTask_new_course.execute(method, course_name, UID).get();
        String[] totalList = x.split(",");

        listOne = new ArrayList(Arrays.asList(totalList));
        listTwo = new ArrayList(Arrays.asList(crowd_player_list));
        List<String> sourceList = new ArrayList<String>(listOne);
        List<String> destinationList = new ArrayList<String>(listTwo);
        sourceList.removeAll(destinationList);

        String[] student_list = new String[sourceList.size()];
        student_list = sourceList.toArray(student_list);

        intent.setClass(this, ChoosePlayer.class);
        bundle.putStringArray("student_list", student_list);
        intent.putExtras(bundle);
        startActivityForResult(intent, resultCode);

    }

    // ?????? ChoosePlayer ?????????????????? , ???????????????????????????
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            Bundle bundleResult = data.getExtras();
            ArrayList<String> arrayList = bundleResult.getStringArrayList("selected_player");
            String[] selected_player = new String[arrayList.size()];        // ?????? ArrayList ???????????? ??? ?????? stockArr[]??????
            selected_player = arrayList.toArray(selected_player);           // ??? ArrayList ?????? stockArr[]??????

            String method = "UpdateGroup_add_new_member";
            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID", "UID doesn't exist");
            String course_name = bundle.getString("course_name");
            String crowd_name = bundle.getString("crowd_name");

            for (int i = 0; i < selected_player.length; i++) {
                BackgroundTask_add backgroundTask_add = new BackgroundTask_add(this);
                try {
                    String result = backgroundTask_add.execute(method, UID, course_name, crowd_name, selected_player[i]).get();
                    if (result.equals("1")) {  Toast.makeText(this, selected_player[i] + "???????????????????????????????????????", Toast.LENGTH_SHORT).show();  }
                    if (result.equals("2")) {  Toast.makeText(this, "?????????????????? , " + selected_player[i] + "??????", Toast.LENGTH_SHORT).show();  }
                } catch (InterruptedException | ExecutionException e) {  e.printStackTrace();  }
            }

            restart restart = new restart(this);
            restart.start();

        }
    }

    public class restart extends Thread {

        Context context;

        public restart(Context context) {
            this.context = context;
        }

        @Override
        public void run() {

            super.run();

            String method = "StudentList_group_detail";
            String crowd_name = bundle.getString("crowd_name");
            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            String UID = sharedPreferences.getString("UID", "UID doesn't exist");
            String course_name = bundle.getString("course_name");
            BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(getApplicationContext());
                try {
                    String x = backgroundTask_new_course.execute(method, UID, course_name, crowd_name).get();
                    String[] crowd_player_list = x.split("&");

                    bundle.putStringArray("crowd_player_list", crowd_player_list);  // ?????? bundle ??? , ??????????????????????????????????????????
                    handler.obtainMessage(1, 4, -1, crowd_player_list).sendToTarget();
                    sleep(1000);
                } catch (InterruptedException | ExecutionException e) {  e.printStackTrace();  }

        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 1) {

                String[] str = (String[]) msg.obj;
                displayListView(str);
            }
            super.handleMessage(msg);
        }
    };

    public void seeDialog(View view) throws ExecutionException, InterruptedException {

        ArrayList<String> alter_player_list = new ArrayList<String>();

        ArrayList<Country> countryList = dataAdapter.countryArrayList1;
        for(int i=0;i<countryList.size();i++){
            Country country = countryList.get(i);
            if(country.isSelected()){
                alter_player_list.add(country.getName());
            }
        }

        String method = "get existed crowd";    // ??????????????????
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't exist");
        String course_name = bundle.getString("course_name");   // ?????????????????????
        String crowd_name = bundle.getString("crowd_name");     // ?????????????????????
        BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(this);
        String x = backgroundTask_new_course.execute(method, UID, course_name).get();   // ??????????????????
        String[] existed_crowd = x.split(",");
        SingleChoiceClass singleChoiceClass = new SingleChoiceClass(existed_crowd, course_name, crowd_name, alter_player_list);
        singleChoiceClass.show(getSupportFragmentManager(), "my dialog");

    }

    public class SingleChoiceClass extends DialogFragment {

        String[] existed_crowd = null;
        String course_name = null;
        String crowd_name = null;
        ArrayList<String> NAME = null;
        String selection;

        public SingleChoiceClass(String[] existed_crowd, String course_name, String crowd_name, ArrayList<String> NAME)
        {
            this.existed_crowd = existed_crowd;
            this.course_name = course_name;
            this.crowd_name = crowd_name;
            this.NAME = NAME;
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle saveInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("??????????????????????????? : ");
            builder.setIcon(R.drawable.ic_certificate_box);
            builder.setSingleChoiceItems(existed_crowd, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    selection = existed_crowd[which];

                }
            }).setPositiveButton("??????", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // selection(??????????????????), course_name(????????????), crowd_name(????????????)
                    String method = "alter players crowd";
                    // ??????????????????
                    if (NAME.size() == 0) {
                        Toast.makeText(getActivity(), "??????????????????,????????????????????????", Toast.LENGTH_SHORT).show();
                    }
                    else {
                            for (int i = 0; i < NAME.size(); i++)
                            {
                                BackgroundTask_new_course backgroundTask_new_course = new BackgroundTask_new_course(getActivity());
                                try {
                                    String d = backgroundTask_new_course.execute(method, course_name, crowd_name, selection, NAME.get(i)).get();
                                    System.out.println(d);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }

                            }

                        restart restart = new restart(getApplicationContext());
                        restart.start();

                        Toast.makeText(getActivity(), "??????????????????????????? : " + selection, Toast.LENGTH_SHORT).show();
                    }

                }
            }).setNegativeButton("??????", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            return builder.create();
        }
    }

}
