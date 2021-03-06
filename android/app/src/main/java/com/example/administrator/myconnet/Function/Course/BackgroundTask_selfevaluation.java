package com.example.administrator.myconnet.Function.Course;

import android.content.Context;
import android.os.AsyncTask;

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

/**
 * Created by user on 2016/8/29.
 */
public class BackgroundTask_selfevaluation extends AsyncTask<String,Void,String> {
    Context ctx;
    public BackgroundTask_selfevaluation(Context context){this.ctx=context;    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... params) {
        String new_url = "http://140.127.218.198:8080/webapp/self_evaluation.php";
        String UID = params[0];
        String curDay = params[1];
        String grades=params[2];
//        System.out.println("BackgroundTask_selfevaluation= "+UID+"\t"+curDay+"\t"+grades+"\t");

        try {
            URL url = new URL(new_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String data = URLEncoder.encode("UID", "UTF-8")+"="+URLEncoder.encode(UID,"UTF-8")+"&"+
                    URLEncoder.encode("curDay","UTF-8")+"="+URLEncoder.encode(curDay,"UTF-8")+"&"+
                    URLEncoder.encode("grades","UTF-8")+"="+URLEncoder.encode(grades,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));     // BufferReader????????????????????????UTF-8????????????
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
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {

    }

}
