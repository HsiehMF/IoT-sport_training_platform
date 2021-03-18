package com.example.administrator.myconnet.Function.Course;


import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by user on 2016/11/8.
 */
public class Line_judgement {
    public Line_judgement(String filename){
        String[] x=filename.split("_");
        final String UID=x[0];
        final String Date=x[1];
        final String item_list_num=x[2];
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                RequestBody request_body = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("UID", UID)
                        .addFormDataPart("Date", Date)
                        .addFormDataPart("item_list_num", item_list_num)
                        .build();

                Request request = new Request.Builder()
                        .url("http://140.127.218.198:8080/webapp/Course/update_line_judgement.php")
                        .post(request_body)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String res = response.body().string();
//                    System.out.println("res\t" + res);
                    if(!response.isSuccessful()){
                        throw new IOException("Error : "+response);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}
