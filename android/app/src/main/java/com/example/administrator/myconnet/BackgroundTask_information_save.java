package com.example.administrator.myconnet;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;

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

public class BackgroundTask_information_save extends AsyncTask<String,Void,String> {  // AsyncTask<params,progress,result>

    Context ctx;
    EditText name,birth,unit,gender,info;

    BackgroundTask_information_save(Context ctx ,EditText name ,EditText birth ,EditText unit ,EditText gender ,EditText info) {

        this.ctx = ctx;
        this.name = name;
        this.birth = birth;
        this.unit = unit;
        this.gender = gender;
        this.info = info;
    }

    @Override
    protected void onPreExecute() {

    }
    @Override
    protected String doInBackground(String... params) {

        String new_url = "http://140.127.218.198:8080/webapp/save_my_information.php";
        String name = params[0];
        String birth = params[1];
        String unit = params[2];
        String gender = params[3];
        String info = params[4];
        String UID = params[5];

        System.out.println(name+birth+unit+gender+info+UID);
        try {
            URL url = new URL(new_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data = URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8") + "&" +
                    URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                    URLEncoder.encode("birth", "UTF-8") + "=" + URLEncoder.encode(birth, "UTF-8") + "&" +
                    URLEncoder.encode("unit", "UTF-8") + "=" + URLEncoder.encode(unit, "UTF-8") + "&" +
                    URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode(gender, "UTF-8") + "&" +
                    URLEncoder.encode("info", "UTF-8") + "=" + URLEncoder.encode(info, "UTF-8");
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

        String[] new_data = result.split(",");

        name.setText(new_data[0]);
        birth.setText(new_data[2]);
        unit.setText(new_data[3]);
        gender.setText(new_data[4]);
        info.setText(new_data[5]);

    }
}