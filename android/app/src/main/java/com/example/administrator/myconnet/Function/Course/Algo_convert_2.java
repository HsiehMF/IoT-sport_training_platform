package com.example.administrator.myconnet.Function.Course;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 2016/11/16.
 */
public class Algo_convert_2 extends  Thread{
    Context c;
    String Filename,Datapath;
    public Algo_convert_2(String filename,String datapath,Context context){
        c=context;
        Filename=filename;
        Datapath=datapath;
    }
    public void run() {
        File dir = c.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        String path =  dir.getParent() + "/" + dir.getName() + "/MyAndroid/" + Datapath;
        try {
            String string;
            FileReader FileReader = new FileReader(path);
            BufferedReader reader=new BufferedReader(FileReader);
            while ((string=reader.readLine()) != null) {
//                int pluse;
                int i=-1;
                for (String a : string.split(",")) {
                    String a_1=a.trim();
                    System.out.printf("a1"+a_1);
                    if (a_1.equals("bpm")){
                        i=0;
                    }
                    if(i==1){
                        if (isNumeric(a_1)){
                                System.out.printf("a1 EO4"+a_1);
                                write write=new write(a_1,Filename,c);
                                write.start();
                        }else {
                            i=-1;
                            continue;
                        }
                    }
                    i++;
                }
            }
        }catch (Exception e){

        }
    }
    private  class write extends Thread {

        FileWriter mFileWriter = null;
        BufferedWriter bw = null;
        String data = null;

        public write(String Str,String filename,Context context) {

            File dir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
            File file = new File( dir.getParent() + "/" + dir.getName() + "/Record");
            if( !file.exists()) {
                file.mkdir();
            }

            try {
                mFileWriter = new FileWriter( dir.getParent() + "/" + dir.getName() + "/Record/" + filename+".txt", true );
            } catch (IOException e) {
                Log.e("IOException", e.toString());
            }
            data = Str+"\n";
        }
        @Override
        public void run() {

            try{
                mFileWriter.write(data);
                mFileWriter.close();
            } catch (IOException e){
                Log.e("Write", e.toString());
            }
        }
        public void cancel(String string) throws IOException {

            mFileWriter.write(string);
            mFileWriter.close();
        }
    }
    public boolean isNumeric(String str)
    {
//        System.out.println(str);
        Pattern pattern = Pattern.compile("^-?[0-9]*[.][0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() )
        {
            return false;
        }
        return true;
    }
}
