package com.example.administrator.myconnet.Function.Public;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

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

public class BackgroundTask_public extends AsyncTask<String,Void,String> {  // AsyncTask<params,progress,result>

    Context ctx;
    BackgroundTask_public(Context ctx)
    {
        this.ctx = ctx;
    }
    @Override
    protected void onPreExecute() {

    }
    @Override
    protected String doInBackground(String... params) {     // 字串用params[]儲存

        String new_url1 = "http://140.127.218.198:8080/webapp/Public/insert_schedule.php";              // 新增課表
        String new_url2 = "http://140.127.218.198:8080/webapp/Public/insert_line_and_item.php";         // 連結項目與之間關係
        String new_url3 = "http://140.127.218.198:8080/webapp/Public/query_schedule_list.php";          // 查詢訓練課表
        String new_url4 = "http://140.127.218.198:8080/webapp/Public/query_item_list.php";              // 訓練課表細項-套用
        String new_url5 = "http://140.127.218.198:8080/webapp/Public/query_saved_schedule_list.php";    // 查詢已儲存訓練課表
        String new_url6 = "http://140.127.218.198:8080/webapp/Public/query_apply_item_list.php";
        String new_url7 = "http://140.127.218.198:8080/webapp/Public/apply_query_old_schedule.php";     // 查詢舊課表編號-套用
        String new_url8 = "http://140.127.218.198:8080/webapp/Public/insert_apply_line_and_item.php";   // 連結舊課表項目與新課表之間的關係
        String new_url9 = "http://140.127.218.198:8080/webapp/Public/change_apply_item.php";
        String new_url10 = "http://140.127.218.198:8080/webapp/Public/apply_add_new_item.php";
        String new_url11 = "http://140.127.218.198:8080/webapp/Public/insert_schedule_apply.php";       // 新增課表-套用
        String new_url12 = "http://140.127.218.198:8080/webapp/Public/check_schedule_name.php";
        String new_url13 = "http://140.127.218.198:8080/webapp/Public/insert_history.php";

        String method = params[0];

        if (method.equals("新增課表")) {

            String UID = params[1];
            String schedule_name = params[2];
            String course_name = params[3];
            String crowd_name = params[4];
            String nowTime = params[5];
            String SAVE = params[6];

            try {
                URL url = new URL(new_url1);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8") + "&" +
                        URLEncoder.encode("schedule_name", "UTF-8") + "=" + URLEncoder.encode(schedule_name, "UTF-8") + "&" +
                        URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8") + "&" +
                        URLEncoder.encode("crowd_name", "UTF-8") + "=" + URLEncoder.encode(crowd_name, "UTF-8") + "&" +
                        URLEncoder.encode("nowTime", "UTF-8") + "=" + URLEncoder.encode(nowTime, "UTF-8") + "&" +
                        URLEncoder.encode("SAVE", "UTF-8") + "=" + URLEncoder.encode(SAVE, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
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
        } else if (method.equals("連結項目與之間關係")) {

            String schedule_num = params[1];
            String main_item = params[2];
            String sub_item = params[3];
            String item_times = params[4];
            String time = params[5];
            String note = params[6];

            try {
                URL url = new URL(new_url2);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("schedule_num", "UTF-8") + "=" + URLEncoder.encode(schedule_num, "UTF-8") + "&" +
                        URLEncoder.encode("main_item", "UTF-8") + "=" + URLEncoder.encode(main_item, "UTF-8") + "&" +
                        URLEncoder.encode("sub_item", "UTF-8") + "=" + URLEncoder.encode(sub_item, "UTF-8") + "&" +
                        URLEncoder.encode("item_times", "UTF-8") + "=" + URLEncoder.encode(item_times, "UTF-8") + "&" +
                        URLEncoder.encode("time", "UTF-8") + "=" + URLEncoder.encode(time, "UTF-8") + "&" +
                        URLEncoder.encode("note", "UTF-8") + "=" + URLEncoder.encode(note, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
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
        } else if (method.equals("查詢訓練課表")) {

            String UID = params[1];
            String course_name = params[2];
            String crowd_name = params[3];
            String date = params[4];

            try {
                URL url = new URL(new_url3);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8") + "&" +
                        URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8") + "&" +
                        URLEncoder.encode("crowd_name", "UTF-8") + "=" + URLEncoder.encode(crowd_name, "UTF-8") + "&" +
                        URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
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
        } else if (method.equals("訓練課表細項")) {

            String schedule_name = params[1];
            String date = params[2];

            try {
                URL url = new URL(new_url4);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("schedule_name", "UTF-8") + "=" + URLEncoder.encode(schedule_name, "UTF-8") + "&" +
                              URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
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
        } else if (method.equals("查詢已儲存訓練課表")) {

            String UID = params[1];
            String course_name = params[2];
            String selected = params[3];

            try {
                URL url = new URL(new_url5);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8") + "&" +
                              URLEncoder.encode("selected", "UTF-8") + "=" + URLEncoder.encode(selected, "UTF-8") + "&" +
                              URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
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
        } else if (method.equals("訓練課表細項-套用")) {

            String UID = params[1];
            String schedule_name = params[2];
            String course_name = params[3];

            try {
                URL url = new URL(new_url6);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8") + "&" +
                        URLEncoder.encode("schedule_name", "UTF-8") + "=" + URLEncoder.encode(schedule_name, "UTF-8") + "&" +
                        URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
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
        } else if (method.equals("查詢舊課表編號-套用")) {

            String UID = params[1];
            String schedule_name = params[2];
            String course_name = params[3];

            try {
                URL url = new URL(new_url7);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8") + "&" +
                        URLEncoder.encode("schedule_name", "UTF-8") + "=" + URLEncoder.encode(schedule_name, "UTF-8") + "&" +
                        URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
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
        } else if (method.equals("連結舊課表項目與新課表之間的關係")) {

            String old_schedule_num = params[1];
            String schedule_num = params[2];

            try {
                URL url = new URL(new_url8);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("old_schedule_num", "UTF-8") + "=" + URLEncoder.encode(old_schedule_num, "UTF-8") + "&" +
                              URLEncoder.encode("schedule_num", "UTF-8") + "=" + URLEncoder.encode(schedule_num, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
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
        } else if (method.equals("更改套用課表之項目")) {

            String main_item = params[1];
            String sub_item = params[2];
            String item_times = params[3];
            String item_time = params[4];
            String item_note = params[5];
            String item_id = params[6];

            try {
                URL url = new URL(new_url9);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("item_id", "UTF-8") + "=" + URLEncoder.encode(item_id, "UTF-8") + "&" +
                        URLEncoder.encode("main_item", "UTF-8") + "=" + URLEncoder.encode(main_item, "UTF-8") + "&" +
                        URLEncoder.encode("sub_item", "UTF-8") + "=" + URLEncoder.encode(sub_item, "UTF-8") + "&" +
                        URLEncoder.encode("item_times", "UTF-8") + "=" + URLEncoder.encode(item_times, "UTF-8") + "&" +
                        URLEncoder.encode("item_time", "UTF-8") + "=" + URLEncoder.encode(item_time, "UTF-8") + "&" +
                        URLEncoder.encode("item_note", "UTF-8") + "=" + URLEncoder.encode(item_note, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
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
        } else if (method.equals("增加新項目-套用")) {

            String schedule_num = params[1];
            String main_item = params[2];
            String sub_item = params[3];
            String item_times = params[4];
            String item_time = params[5];
            String item_note = params[6];

            try {
                URL url = new URL(new_url10);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("schedule_num", "UTF-8") + "=" + URLEncoder.encode(schedule_num, "UTF-8") + "&" +
                        URLEncoder.encode("main_item", "UTF-8") + "=" + URLEncoder.encode(main_item, "UTF-8") + "&" +
                        URLEncoder.encode("sub_item", "UTF-8") + "=" + URLEncoder.encode(sub_item, "UTF-8") + "&" +
                        URLEncoder.encode("item_times", "UTF-8") + "=" + URLEncoder.encode(item_times, "UTF-8") + "&" +
                        URLEncoder.encode("item_time", "UTF-8") + "=" + URLEncoder.encode(item_time, "UTF-8") + "&" +
                        URLEncoder.encode("item_note", "UTF-8") + "=" + URLEncoder.encode(item_note, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
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
        } else if (method.equals("新增課表-套用")) {

            String UID = params[1];
            String schedule_name = params[2];
            String course_name = params[3];
            String crowd_name = params[4];
            String nowTime = params[5];

            try {
                URL url = new URL(new_url11);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8") + "&" +
                        URLEncoder.encode("schedule_name", "UTF-8") + "=" + URLEncoder.encode(schedule_name, "UTF-8") + "&" +
                        URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8") + "&" +
                        URLEncoder.encode("crowd_name", "UTF-8") + "=" + URLEncoder.encode(crowd_name, "UTF-8") + "&" +
                        URLEncoder.encode("nowTime", "UTF-8") + "=" + URLEncoder.encode(nowTime, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
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
        } else if (method.equals("檢查課表機制")) {

            String UID = params[1];
            String schedule_name = params[2];
            String course_name = params[3];
            String crowd_name = params[4];
            String nowTime = params[5];

            try {
                URL url = new URL(new_url12);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8") + "&" +
                        URLEncoder.encode("schedule_name", "UTF-8") + "=" + URLEncoder.encode(schedule_name, "UTF-8") + "&" +
                        URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8") + "&" +
                        URLEncoder.encode("crowd_name", "UTF-8") + "=" + URLEncoder.encode(crowd_name, "UTF-8") + "&" +
                        URLEncoder.encode("nowTime", "UTF-8") + "=" + URLEncoder.encode(nowTime, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
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
        } else if (method.equals("建立歷史紀錄")) {

            String schedule_num = params[1];
            String crowd_name = params[2];
            String UID = params[3];

            try {
                URL url = new URL(new_url13);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("schedule_num", "UTF-8") + "=" + URLEncoder.encode(schedule_num, "UTF-8") + "&" +
                        URLEncoder.encode("crowd_name", "UTF-8") + "=" + URLEncoder.encode(crowd_name, "UTF-8") + "&" +
                        URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
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
        }
        return null;
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {
        // 取得新增課表的 schedule_num
    }
}




