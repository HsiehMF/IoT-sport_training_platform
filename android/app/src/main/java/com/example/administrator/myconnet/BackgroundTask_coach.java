package com.example.administrator.myconnet;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.administrator.myconnet.Function.Invite.ManagePlayerInvite;
import com.example.administrator.myconnet.Function.Invite.SearchCoach;

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

public class BackgroundTask_coach extends AsyncTask<String,Void,String> {  // AsyncTask<params,progress,result>

    Context ctx;
    String method;

    public BackgroundTask_coach(Context ctx,String method)
    {
        this.ctx = ctx;
        this.method = method;
    }
    @Override
    protected void onPreExecute() {

    }
    @Override
    protected String doInBackground(String... params) {     // 字串用params[]儲存

        String new_url = "http://140.127.218.198:8080/webapp/search_coach.php";
        String new_url1 = "http://140.127.218.198:8080/webapp/coach_choose_group.php";

        if (method.equals("Student")) {
            try {
                URL url = new URL(new_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));     // BufferReader的編碼，如果不用UTF-8會變亂碼
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+=line;
                }
                response=response.trim();
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("Coach")) {

            String coach_id = params[0];

            try {
                URL url = new URL(new_url1);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("coach_id", "UTF-8") + "=" + URLEncoder.encode(coach_id, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));     // BufferReader的編碼，如果不用UTF-8會變亂碼
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+=line;
                }
                response=response.trim();
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {

        if (method.equals("Student")) {

            Intent intent = new Intent();
            intent.setClass(this.ctx, SearchCoach.class);
            Bundle bundle = new Bundle();
            bundle.putString("coach_name,coach_unit", result);     // 標題
            intent.putExtras(bundle);
            this.ctx.startActivity(intent);

        } else if (method.equals("Coach")) {

            Intent intent = new Intent();
            intent.setClass(this.ctx, ManagePlayerInvite.class);
            Bundle bundle = new Bundle();
            bundle.putString("course_name", result);
            intent.putExtras(bundle);
            this.ctx.startActivity(intent);

        }

    }
}



