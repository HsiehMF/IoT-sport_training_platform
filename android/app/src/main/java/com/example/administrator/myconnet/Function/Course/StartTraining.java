package com.example.administrator.myconnet.Function.Course;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.MainActivity;
import com.example.administrator.myconnet.R;
import com.example.administrator.myconnet.Setting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")

public class StartTraining extends AppCompatActivity {

    ListView lv;
    Button btnStart,btnStop;
    TextView textViewTime;
    ArrayList list = new ArrayList();
    Map<String, String> Detect = new HashMap<String,String>();
    private BluetoothAdapter mBluetoothAdapter = null;
    private ConnectThread connectThread = null;
    private ConnectThread connectThread2 = null;
    private ConnectThread connectThread3 = null;
    private BluetoothDevice findDevice = null;
    private BluetoothDevice findDevice2 = null;
    private BluetoothDevice findDevice3 = null;
    //    final UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    //    private String filename=null;
    String str,connect;
    int count=0;
    String Locatin1="";
    String Locatin2="";
    String Locatin3="";
    String UID;
    String curDay;
    String filename;

    boolean flag=false;

    SQLiteDatabase db;
    static final String db_name="bluetooth";
    static final String tb_name="Setting";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_training);

        Bundle bundle = getIntent().getExtras();
        UID = bundle.getString("UID");
        curDay=bundle.getString("curDay");
//        System.out.println("StartTarain \t"+curDay);

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
// If there are paired devices

//        ======================================================================================================設定藍芽
        String [] zz=new String[3];
        try {
            db=openOrCreateDatabase(db_name, Context.MODE_PRIVATE,null);
            Cursor c=db.rawQuery("SELECT * FROM "+tb_name+" ORDER BY B_ID ASC  ",null);
            String [][] z=new String[3][3];
            int i=0;
            if(c.getCount()>0){
                c.moveToFirst();
                if(i==0){
                    z[0][0]=c.getString(1).trim();
                    z[0][1]=c.getString(2).trim();
                    System.out.println("z[0][0]\t"+z[0][0]);
                    zz[0]=z[0][0];
                }
                do{
                    if(i==1){

                        z[1][0]=c.getString(1).trim();
                        z[1][1]=c.getString(2).trim();
                        System.out.println("z[1][0]\t"+z[1][0]+"\t"+z[1][1]);
                        zz[1]=z[1][1];
                    }
                    if (i==2){
                        z[2][0]=c.getString(1).trim();
                        z[2][1]=c.getString(2).trim();
                        System.out.println("z[2][0]\t"+z[2][0]+"\t"+z[2][1]);
                        zz[2]=z[2][2];
                    }
                    i++;
                } while (c.moveToNext());
            }

            if (pairedDevices.size() > 0) {
                // Loop through paired devices
                for (BluetoothDevice device : pairedDevices) {
                    if (z[0][0].equals(device.getAddress())){
                        findDevice=device;
                        list.add("Device1\t"+ device.getName()+"\t"+z[0][1]+"\t"+device.getAddress());
                    }
                    if (z[1][0].equals(device.getAddress())){
                        findDevice2=device;
                        list.add("Device2\t"+ device.getName()+"\t"+z[1][1]+"\t"+device.getAddress());
                    }
                    if (z[2][0].equals(device.getAddress())){
                        findDevice3=device;
                        list.add("Device3\t"+ device.getName()+"\t"+z[2][1]+"\t"+device.getAddress());

                    }
                    String str=String.valueOf(i);
                    Detect.put(str, device.getName());
                    i++;
                }
            }
            db.close();
        }catch (Exception e){

        }


//        =====================================================================================================
        lv = (ListView) findViewById(R.id.listView2);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
        if(!list.isEmpty()) {
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
//                    Search(position);
                }
            });
        }
//        Bundle bundle = getIntent().getExtras();
        String x = bundle.getString("training_content");
        String[] training_content = x.split(",");
        String Filename=training_content[0];
        Filename=Filename.replace(".跑步",",running");
        String [] y=Filename.split(",");
//        filename=UID+"_"+curDay+"_"+y[0]+"_"+y[1];
        filename=UID+"_"+curDay+"_"+y[0]+"_"+y[1];// 後面的檔名
//        filename=training_content[1]+"_"+training_content[2]+"_"+training_content[3];//檔名
//        System.out.println("testfilename= \t"+filename_Final);

//        -----------------------------------------------------------------------------------------  藍芽
        String filename;
        connect="OK";
        String[] ConvertcurDay = curDay.split("-");


        String filename3;
        connect="OK";
        filename3=ConvertcurDay[0]+"_"+ConvertcurDay[1]+"_"+ConvertcurDay[2]+"third.txt";
        Locatin3=filename3;
        try {
            connectThread3=new ConnectThread(findDevice3,"Beginging ,"+getDateTime()+",\n",filename3);
            connectThread3.start();
        }catch (Exception e){
            Toast.makeText(StartTraining.this, "心跳沒開", Toast.LENGTH_SHORT).show();
            System.out.println("E:\t" + e.toString());
        }

        if(!zz[0].equals("empty")){

            filename=ConvertcurDay[0]+"_"+ConvertcurDay[1]+"_"+ConvertcurDay[2]+ ".txt";
            Locatin1=filename;
//        connectThread=new ConnectThread(findDevice,"Beginging ,"+getDateTime()+",\n",filename);
            try {
                connectThread=new ConnectThread(findDevice,"Beginging ,"+getDateTime()+",\n",filename);
                connectThread.start();
            }catch (Exception e){
                Toast.makeText(StartTraining.this, "右腳開沒成功", Toast.LENGTH_SHORT).show();
            }

        }else {
            String filename2;
            connect="OK";
            filename2=ConvertcurDay[0]+"_"+ConvertcurDay[1]+"_"+ConvertcurDay[2]+"sec.txt";
            Locatin2=filename2;
            try {
                connectThread2=new ConnectThread(findDevice2,"Beginging ,"+getDateTime()+",\n",filename2);
                connectThread2.start();
            }catch (Exception e){
                Toast.makeText(StartTraining.this, "左腳開沒成功", Toast.LENGTH_SHORT).show();
            }
        }

        training_content = training_content[3].split("秒");     // 把字串中的"秒"去掉
        int time = Integer.parseInt(training_content[0]) * 1000;    // 單位為微秒

        btnStart = (Button)findViewById(R.id.btnStart);
        btnStop = (Button)findViewById(R.id.btnStop);
        textViewTime = (TextView)findViewById(R.id.textViewTime);

        final CounterClass timer = new CounterClass(time,1000); // 真正設定的時間

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timer.start();
                flag=true;


                btnStart.setVisibility(btnStart.INVISIBLE);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timer.cancel();
            }
        });

    }


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture,long countDownInterval){
            super(millisInFuture, countDownInterval);
        }

        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @SuppressLint("NewApi")

        @Override
        public void onTick(long millisUntilFinished) {

            long total = millisUntilFinished;  // millisUntilFinished 為傳進來的時間
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(total),
                    TimeUnit.MILLISECONDS.toMinutes(total) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(total)),
                    TimeUnit.MILLISECONDS.toSeconds(total) - TimeUnit.MINUTES.toSeconds((TimeUnit.MILLISECONDS.toMinutes(total))));
            System.out.println(hms);
            textViewTime.setText(hms);
        }

        @Override
        public void onFinish()
        {
            connect="STOP";
            textViewTime.setText("Completed");
            try {
                connectThread.cancel();
            }catch (Exception e){
//                Toast.makeText(StartTraining.this, "右腳沒開", Toast.LENGTH_SHORT).show();
            }
            try {
                connectThread2.cancel();
            }catch (Exception e){
//                Toast.makeText(StartTraining.this, "左腳沒開", Toast.LENGTH_SHORT).show();
            }
            try {
                connectThread3.cancel();
            }catch (Exception e){
//                Toast.makeText(StartTraining.this, "心跳沒開", Toast.LENGTH_SHORT).show();
            }

            String s=Locatin1+"@"+Locatin2+"@"+curDay+"@"+filename+"@"+Locatin3+"@"+"Y";
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), NewCourse.class);
            Bundle bundle1 = new Bundle();
            bundle1.putString("Location", s);
            intent.putExtras(bundle1);

            Line_judgement line_judgement=new Line_judgement(filename);
//            System.out.println("Locatin1= \t" + Locatin1);
//            System.out.println("Locatin2= \t" + Locatin2);


            startActivity(intent);
            finish();
//
        }

    }


    private  class write extends Thread {

        FileWriter mFileWriter = null;
        BufferedWriter bw = null;
        String data = null;

        public write(String Str,String filename) {

            File dir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
            File file = new File( dir.getParent() + "/" + dir.getName() + "/MyAndroid");
            if( !file.exists()) {

                file.mkdir();
            }
            try {
                mFileWriter = new FileWriter( dir.getParent() + "/" + dir.getName() + "/MyAndroid/" + filename, true );
                // os=new FileOutputStream(file);
            } catch (IOException e) {
                Log.e("IOException",e.toString());
            }
            data = Str;
        }
        @Override
        public void run() {

            try{
                bw = new BufferedWriter(mFileWriter);
                bw.write(data);
                bw.close();

            } catch (IOException e){
                Log.e("Write", e.toString());
            }
        }
        public void cancel(String string) throws IOException {

            mFileWriter.write(string);
            mFileWriter.close();
        }
    }

    private class ConnectThread extends Thread {

        final UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        ConnectedThread manageConnectedSocket = null;
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
        //        Map<String, Object> condition4 = new HashMap<String,Object>();
        String str;
        String filename;

        public ConnectThread ( BluetoothDevice device , String string , String filename1 ) {

            BluetoothSocket tmp = null;
            str = string;
            filename = filename1;
            mmDevice = device;

            try {
                tmp = device.createRfcommSocketToServiceRecord(uuid);
            } catch (IOException e) { }
            mmSocket = tmp;
        }

        public void run() {

            mBluetoothAdapter.cancelDiscovery();
            try {
                mmSocket.connect();
            } catch (IOException connectException) {
                // Unable to connect; close the socket and get out
                try {
                    mmSocket.close();
                } catch (IOException closeException) { }

            }

            // Do work to manage the connection (in a separate thread)
            manageConnectedSocket = new ConnectedThread( mmSocket , str , filename );
            manageConnectedSocket.start();
//          manageConnectedSocket(mmSocket);
        }
        /** Will cancel an in-progress connection, and close the socket */
        public void cancel() {

            manageConnectedSocket.cancel();
        }
    }

    private class ConnectedThread extends Thread {

        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        //        write write=null;
        Date date = null;
        String filename;
        String Str;

        public ConnectedThread ( BluetoothSocket socket , String str , String filename1 ) {

            filename=filename1;
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;

            Str=str;
//            write write=new write(str,filename);
//            write.start();
//            write=new write(string,filename);

        }

        public void run() {

            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes;// bytes returned from read()
            int i=1;
            // Keep listening to the InputStream until an exception occurs
            // write write =null;
            while (true) {
                try {
                    InputStream inputtStr =  mmSocket.getInputStream();
                    bytes = mmInStream.read(buffer);
                    final  String showValue =  new String(buffer, 0,bytes);
//                    final String showValue1=showValue.trim();
                    if(flag){
                        if(i==1){
                            write write=new write(Str,filename);
                            write.start();
                            i++;
                        }
                        write write=new write(showValue,filename);
                        write.start();
                    }
//                    System.out.println(filename);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(showValue);
                        }
                    });
                } catch (IOException e) {
                    break;
                }
                if(connect.equals("STOP")){break;}
            }
        }
        /* Call this from the main activity to shutdown the connection */
        public void cancel() {
            try {
                write   write=new write("\n"+"THE END ,"+getDateTime()+",\n",filename);
                write.start();
                mmSocket.close();
            } catch (IOException e) { }
        }
    }

    public void out( View view ) {

        Intent intent = new Intent();
        //intent.setClass(this, MainActivity2.class);
        Bundle bundle = new Bundle();
        bundle.putString("path",str);
        intent.putExtra("Dirpath",str);
        startActivity(intent);

    }

    public int getDateTime() {
        int x,y;
        int time=0;
        SimpleDateFormat sdFormat = new SimpleDateFormat("ss");
        SimpleDateFormat sdFormat1 = new SimpleDateFormat("mm");
        Date current = new Date();
        String now=sdFormat.format(current);
        String now2=sdFormat1.format(current);
        x=Integer.valueOf(now);//秒
        y=Integer.valueOf(now2);//分
        time=y*60+x;
        return time;
    }

    public  void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        Bundle bundle = new Bundle();
        bundle.putString("curDay", curDay);
        intent.putExtras(bundle);
        intent.setClass(this, NewCourse.class);
        startActivity(intent);
        connect="STOP";
//        textViewTime.setText("Completed");
        try {
            connectThread.cancel();
        }catch (Exception e){
//                Toast.makeText(StartTraining.this, "右腳沒開", Toast.LENGTH_SHORT).show();
        }
        try {
            connectThread2.cancel();
        }catch (Exception e){
//                Toast.makeText(StartTraining.this, "左腳沒開", Toast.LENGTH_SHORT).show();
        }
        try {
            connectThread3.cancel();
        }catch (Exception e){
//                Toast.makeText(StartTraining.this, "心跳沒開", Toast.LENGTH_SHORT).show();
        }
        finish();
        super.onBackPressed();
    }

}
