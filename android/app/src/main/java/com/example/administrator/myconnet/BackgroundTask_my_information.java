package com.example.administrator.myconnet;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.administrator.myconnet.Function.Course.NewCourse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask_my_information extends AsyncTask<String,Void,String> {  // AsyncTask<params,progress,result>

    Context ctx;
    public BackgroundTask_my_information(Context ctx)
    {
        this.ctx =ctx;
    }
    @Override
    protected void onPreExecute() {

    }
    @Override
    protected String doInBackground(String... params) {

        String new_url = "http://140.127.218.198:8080/webapp/my_information.php";
        String UID = params[0];

            try {
                URL url = new URL(new_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));     // BufferReader的編碼，如果不用UTF-8會變亂碼
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                response = response.trim();
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {

        Intent intent = new Intent();
        intent.setClass(this.ctx, MyInformation.class);
        Bundle bundle = new Bundle();
        bundle.putString("my_information" , result);
        intent.putExtras(bundle);
        this.ctx.startActivity(intent);
    }
}




