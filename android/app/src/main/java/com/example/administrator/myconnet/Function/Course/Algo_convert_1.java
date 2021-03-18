package com.example.administrator.myconnet.Function.Course;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by user on 2016/11/7.
 */
public class Algo_convert_1 extends Thread {
    Context c;
    String Datapath,Path,Filename;

    public Algo_convert_1(String filename,String datapath,Context context){
        c=context;
        File dir = c.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        String path =  dir.getParent() + "/" + dir.getName() + "/MyAndroid/" + datapath;
        Datapath=datapath;
        Path=path;
        Filename=filename;

    }

    @Override
    public void run() {
        if (Datapath != null && !Datapath.equals("")) {
            try {
                int []x=count(Path,Filename);
                int sum_of_row=(x[0]/x[1]);
                ;
            }catch (Exception e){}
        }
    }

    public int[] count(String path,String filename){
        String Begining="0";
        String End="0";
        String string=null;
        int flag=-100;
        int lines=0;//共有幾行
        int Secs=0;//共花多少時間
        int []x=new int[2];
        try {
//            String[] g = new String[6];
            FileReader FileReader = new FileReader(path);
            BufferedReader reader=new BufferedReader(FileReader);
            while ((string=reader.readLine()) != null) {
                String[] g = new String[6];
                int i = 0;
                int j=0;
                int k=0;
                int flag_1=0;
                for (String a : string.split(",")) {
                    a=a.trim();
//                    System.out.println("a=\t"+a );
                    if(a.equals("Beginging")) {flag=-1;}
                    if (j == 1 ) {
                        if( flag==-1){
                            Begining=a;
                            flag=-3;
                        }
                    }
                    if(flag==-3){
                        if (a.equals("a/g")) {
                            k = 0;
                            flag_1++;
                        }
                        if (k == 1 && isNumeric(a) ) {
                            g[0] =a;
                            flag_1++;
                        } else if (k == 2 && isNumeric(a) ) {
                            g[1] =a;
                            flag_1++;
                        } else if (k == 3 && isNumeric(a) ) {
                            g[2] =a;
                            flag_1++;
                        } else if (k == 4 && isNumeric(a) ) {
                            g[3] =a;
                            flag_1++;
                        } else if (k == 5 && isNumeric(a)) {
                            g[4] =a;
                            flag_1++;
                        } else if (k == 6 && isNumeric(a)) {
                            g[5] =a;
                            flag_1++;
                        }
                        if (flag_1==7){
                            String txt;
//                            System.out.println("g[0]\t,g[1]\t,g[2]\t,g[3]\t,g[4]\t,g[5]=\t"+g[0]+g[1]+g[2]+g[3]+g[4]+g[5]);
                            txt=g[0]+","+g[1]+","+g[2]+","+g[3]+","+g[4]+","+g[5]+"\n";
//                            System.out.println(txt);
                            write write=new write(txt,filename,c);
                            write.start();
//                            g=null;
                        }
                    }
                    k++;

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
//        System.out.println("update\t"+Filename);
//        update(Filename);
        return x;
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
    public boolean check(String str)
    {
        try {
            if(Integer.valueOf(str)>30000 && Integer.valueOf(str)<-30000){
                return false;
            }
            return true;
        }catch (Exception e){
            return  false;
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
            data = Str;
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
    private String getMimeType(String path) {

        String extension = MimeTypeMap.getFileExtensionFromUrl(path);

        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }
    private void update(final String filename){

        ProgressDialog progress = new ProgressDialog(c);
        progress.setTitle("Uploading");
        progress.setMessage("Please wait...");
        progress.show();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                File dir = c.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                String path =  dir.getParent() + "/" + dir.getName() + "/Record/" + filename+".txt";
                File f= new File(path);
                String content_type  = getMimeType(path);

                System.out.println("filename\t" + filename);
//                String file_path = path+".txt";
                System.out.println("file_path\t"+path);
                OkHttpClient client = new OkHttpClient();
                RequestBody file_body = RequestBody.create(MediaType.parse(content_type),f);
                RequestBody request_body = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
//                            .addFormDataPart("type",content_type)
                        .addFormDataPart("filename", filename)
                        .addFormDataPart("upload", path.substring(path.lastIndexOf("/") + 1), file_body)
                        .build();

                Request request = new Request.Builder()
                        .url("http://140.127.218.198:8080/webapp/uploads/upload2.php")
                        .post(request_body)
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    String res = response.body().string();
                    System.out.println("res\t" + res);
                    if(!response.isSuccessful()){
                        throw new IOException("Error : "+response);
                    }
                    f.delete();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        progress.dismiss();
    }
}
