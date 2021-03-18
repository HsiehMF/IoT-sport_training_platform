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
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by user on 2016/9/2.
 */
public class Algo_convert {
    Context c;
    String Begining,End;
    int count=0;//計數器
    String temp[][]=new String[201][6];
    int [][]stastic=new int[201][6];
    double resultdata[][]=new double[500][6];
    String P;
//    String path,folder;

    public Algo_convert(String filename,String datapath,Context context){
        c=context;
        File dir = c.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        String path =  dir.getParent() + "/" + dir.getName() + "/MyAndroid/" + datapath;
//        System.out.println("path");
        if (datapath != null && !datapath.equals("")) {
            P=datapath;
            Draw(path);

            int l=0;
            while (l<500){
                String s="";
                s+=String.valueOf(resultdata[l][0])+","+String.valueOf(resultdata[l][1])+","+String.valueOf(resultdata[l][2])+","+String.valueOf(resultdata[l][3])+","+String.valueOf(resultdata[l][4])+","+String.valueOf(resultdata[l][5])+"\n";
//                System.out.println("\t"+s);
                write write=new write(s,filename,context);
                write.start();
                l++;
            }
        }
    }
    public String Draw(String path){
        System.out.println("DRAW \t"+path);
            int []x=count(path);//屬行數與秒數x[0]=lines ; x[1]=Secs
            int onecal=(x[0]/x[1])*5;//行數除以總秒數再以5秒計算
            int S,E;
            S=onecal*0;
            E=onecal*1;
            int i=0;//共多少筆資要加一筆
            do {
                int show[]=caculate(S, E,path);
                if(E>x[0]){
                    show=caculate(S,x[0],path);
                }
                for(int j=0;j<6;j++){
                    resultdata[i][j]=show[j];
            System.out.println("\t"+resultdata[i][j]);
                }
                i++;
                E+=onecal;
                S+=onecal;
            } while (E<x[0]);

        return "ok Convert";
    }

    public int[] caculate(int start, int end,String path){
        String string=null;
        int x=0;
        if(end<start){
            int temp=end;
            end=start;
            start=end;
        }
        try {
            String[] g = new String [6];
            FileReader FileReader = new FileReader(path);
            BufferedReader reader=new BufferedReader(FileReader);
            while ((string = reader.readLine()) != null) {
                int i = 0;
                if(start<=x && x<end && (end-start)<200) {
                    for (String a : string.split(",")) {
                        a = a.trim();
                        if (a.equals("a/g")) {
                            i = 0;
                        }
                        if (i == 1) {
                            g[0] =a;
                        } else if (i == 2) {
                            g[1] =a;
                        } else if (i == 3) {
                            g[2] =a;
                        } else if (i == 4) {
                            g[3] =a;
                        } else if (i == 5) {
                            g[4] =a;
                        } else if (i == 6) {
                            g[5] =a;
                        }
                        i++;
                    }
                    storage(end-start,g);
                }
                x++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int []fin=critical(stastic);
        return fin;
    }
    public void storage(int x,String str[]){
        if (count<200){
            for (int i=0;i<6;i++){
                if(str[i]==null){
                    str[i]="0";
                }else if(str[i].equals("")){
                    str[i]="0";
                }
                temp[count][i]=str[i];
            }
            count++;
        }
        if(count==x){
            convert();
        }
    }

    public void convert(){
        int i,j;
        for (i=0;i<count;i++){
            j=0;
            while (j<6) {
                if (isNumeric(temp[i][j])==true){
                    try {
                        stastic[i][j] = Integer.valueOf(temp[i][j]);
                    }catch (Exception e){
                        stastic[i][j]=0;
                        System.out.println("Exception\t"+e);
                    }
//                    stastic[i][j] = Integer.valueOf(temp[i][j]);
//                    System.out.println(stastic[i][j]);
                }else {
                    stastic[i][j]=0;
                }
                j++;
            }
        }
        count=0;
    }
    public boolean isNumeric(String str)
    {
//        System.out.println(str);
        Pattern pattern = Pattern.compile("^-?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() )
        {
            return false;
        }
        return true;
    }
    public int[] critical(int data[][]){
        int maxtemp[]=new int[6];
        int mintemp[]=new int[6];
        int critical[]=new int[6];
        maxtemp[0]=data[0][0];
        maxtemp[1]=data[0][1];
        maxtemp[2]=data[0][2];
        maxtemp[3]=data[0][3];
        maxtemp[4]=data[0][4];
        maxtemp[5]=data[0][5];
        for (int i=0;i<201;i++){
            if (maxtemp[0]>data[i][0]){
                maxtemp[0]=data[i][0];
            }
            if (maxtemp[1]>data[i][1]){
                maxtemp[1]=data[i][1];
            }
            if (maxtemp[2]>data[i][2]){
                maxtemp[2]=data[i][2];
            }
            if (maxtemp[3]>data[i][3]){
                maxtemp[3]=data[i][3];
            }
            if (maxtemp[4]>data[i][4]){
                maxtemp[4]=data[i][4];
            }
            if (maxtemp[5]>data[i][5]){
                maxtemp[5]=data[i][5];
            }
        }
        mintemp[0]=data[0][0];
        mintemp[1]=data[0][1];
        mintemp[2]=data[0][2];
        mintemp[3]=data[0][3];
        mintemp[4]=data[0][4];
        mintemp[5]=data[0][5];
        for (int i=0;i<201;i++){
            if (mintemp[0]<data[i][0]){
                mintemp[0]=data[i][0];
            }
            if (mintemp[1]<data[i][1]){
                mintemp[1]=data[i][1];
            }
            if (mintemp[2]<data[i][2]){
                mintemp[2]=data[i][2];
            }
            if (mintemp[3]<data[i][3]){
                mintemp[3]=data[i][3];
            }
            if (mintemp[4]<data[i][4]){
                mintemp[4]=data[i][4];
            }
            if (mintemp[5]<data[i][5]){
                mintemp[5]=data[i][5];
            }
        }
        critical[0]=maxtemp[0]-mintemp[0];
        critical[1]=maxtemp[1]-mintemp[1];
        critical[2]=maxtemp[2]-mintemp[2];
        critical[3]=maxtemp[3]-mintemp[3];
        critical[4]=maxtemp[4]-mintemp[4];
        critical[5]=maxtemp[5]-mintemp[5];
        temp=new String[201][6];
        stastic=new int[201][6];
        return critical;
    }
    public int[] count(String path){
        String string=null;
        int flag=-100;
        String str = null;
        String temp[]=new String[150];
        int lines=0;//共有幾行
        int Secs=0;//共花多少時間
        int []x=new int[2];
        try {
            String[] g = new String[6];
            FileReader FileReader = new FileReader(path);
            BufferedReader reader=new BufferedReader(FileReader);
            while ((string=reader.readLine()) != null) {
                int i = 0;
                int j=0;
                for (String a : string.split(",")) {
                    a=a.trim();
//                    System.out.println("a=\t"+a );
                    if(a.equals("Beginging")) {flag=-1;}
                    if (j == 1 ) {
                        if( flag==-1){
                            Begining=a;
                            flag=-100;
                        }
                    }
                    if(a.equals("THE END")) {flag=-2;}
                    if (j == 1 ) {
                        if( flag==-2){
                            End=a;
                            flag=-100;
                        }
                    }
                    i++;j++;
                }
                lines++;
            }
            reader.close();
            FileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Secs=Integer.valueOf(End)-Integer.valueOf(Begining);
        x[0]=lines;
        x[1]=Secs;
        return x;
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
//                str = dir.getParent() + "/" + dir.getName() + "/MyAndroid";
                // os=new FileOutputStream(file);
            } catch (IOException e) {
                Log.e("IOException",e.toString());
            }
            data = Str;
        }
        @Override
        public void run() {

            try{
//                bw = new BufferedWriter(mFileWriter);
//                bw.write(data);
//                bw.close();
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
}
