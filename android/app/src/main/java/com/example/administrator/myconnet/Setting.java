package com.example.administrator.myconnet;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.myconnet.Function.Course.NewCourse;

import java.util.Set;

public class Setting extends AppCompatActivity {

    static final String db_name="bluetooth";
    static final String tb_name="Setting";
    SQLiteDatabase db;
    private String B_Devie="";
    private Spinner spinner_L,spinner_R,spinner_H;
    String[] list;
    private BluetoothAdapter mBluetoothAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//------------------------------------------------------------------------------------------------------------------DB
        db=openOrCreateDatabase(db_name, Context.MODE_PRIVATE,null);
        String creatTable="CREATE TABLE  IF NOT EXISTS Setting(" + "   B_ID INT PRIMARY KEY     NOT NULL," + "   B_ADDRESS        CHAR(50)," + "   function        CHAR(50)" + ");";

        db.execSQL(creatTable);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            System.out.println("yes");
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 0);
        }

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                B_Devie=B_Devie+device.getName()+"_"+device.getAddress()+",";
            }
        }
        B_Devie=B_Devie.trim()+"empty_empty";
        String []B_D=B_Devie.split(",");
        spinner_L=(Spinner)findViewById(R.id.L_S);
        spinner_R=(Spinner)findViewById(R.id.R_S);
        spinner_H=(Spinner)findViewById(R.id.H_S);
        ArrayAdapter B_Dlist = new ArrayAdapter<String>(Setting.this, android.R.layout.simple_spinner_item, B_D);
        spinner_L.setAdapter(B_Dlist);
        spinner_R.setAdapter(B_Dlist);
        spinner_H.setAdapter(B_Dlist);
        list=B_D;


        Cursor c=db.rawQuery("SELECT * FROM "+tb_name+" ORDER BY B_ID ASC  ",null);


        if(c.getCount()<3) {

            int index_L = spinner_L.getSelectedItemPosition();
            int index_R = spinner_R.getSelectedItemPosition();
            int index_H = spinner_H.getSelectedItemPosition();
            ContentValues cv = new ContentValues(3);
            String[] addressL = list[index_L].split("_");
            String[] addressR = list[index_R].split("_");
            String[] addressH = list[index_H].split("_");
            cv.put("B_ID", 1);
            cv.put("B_ADDRESS", "empty");
            cv.put("function", "running_0");
            db.insert(tb_name, null, cv);
            cv.put("B_ID", 2);
            cv.put("B_ADDRESS", "empty");
            cv.put("function", "running_1");
            db.insert(tb_name, null, cv);
            cv.put("B_ID", 3);
            cv.put("B_ADDRESS", "empty");
            cv.put("function", "heartrate");
            db.insert(tb_name, null, cv);
        }else{

        }


        c=db.rawQuery("SELECT * FROM "+tb_name+" ORDER BY B_ID ASC  ",null);
        int i=0;
        if(c.getCount()>0){
            c.moveToFirst();
            if(i==0){
                TextView textViewL=(TextView)findViewById(R.id.Left_sensor_txt);
                textViewL.setText(c.getString(1));
            }
            do{
                if(i==1){
                    TextView textViewR=(TextView)findViewById(R.id.Right_sensor_txt);
                    textViewR.setText(c.getString(1));
                }
                if (i==2){
                    TextView textViewH=(TextView)findViewById(R.id.Heart_sensor_txt);
                    textViewH.setText(c.getString(1));
                }
                i++;
            } while (c.moveToNext());

        }


    }
    public void Set_submit(View view){
        int index_L=spinner_L.getSelectedItemPosition();
        int index_R=spinner_R.getSelectedItemPosition();
        int index_H=spinner_H.getSelectedItemPosition();
        String []addressL=list[index_L].split("_");
        String []addressR=list[index_R].split("_");
        String []addressH=list[index_H].split("_");
        db.execSQL("UPDATE Setting" + " SET B_ID = 1"+", B_ADDRESS =' "+addressL[1]+"', function = ' running_0' "+ "WHERE B_ID=1 ");
        db.execSQL("UPDATE Setting" + " SET B_ID = 2"+", B_ADDRESS =' "+addressR[1]+"', function = ' running_1 '"+ "WHERE B_ID=2 ");
        db.execSQL("UPDATE Setting" + " SET B_ID = 3"+", B_ADDRESS =' "+addressH[1]+"', function = ' heartrate' "+ "WHERE B_ID=3 ");
        Cursor c=db.rawQuery("SELECT * FROM "+tb_name+" ORDER BY B_ID ASC  ",null);
        int i=0;
        if(c.getCount()>0){
            c.moveToFirst();
            if(i==0){
                TextView textViewL=(TextView)findViewById(R.id.Left_sensor_txt);
                textViewL.setText(c.getString(1));
            }
            do{
                if(i==1){
                    TextView textViewR=(TextView)findViewById(R.id.Right_sensor_txt);
                    textViewR.setText(c.getString(1));
                }
                if (i==2){
                    TextView textViewH=(TextView)findViewById(R.id.Heart_sensor_txt);
                    textViewH.setText(c.getString(1));
                }
                i++;
            } while (c.moveToNext());

        }
    }
    public  void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        Bundle bundle = getIntent().getExtras();
        String UID = bundle.getString("UID");


        Bundle bundle1 = new Bundle();
        bundle1.putString("UID",UID);
        intent.putExtras(bundle1);
        intent.setClass(this, Lobby3.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

}