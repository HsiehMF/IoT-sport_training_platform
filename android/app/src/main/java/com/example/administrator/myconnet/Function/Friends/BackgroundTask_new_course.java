package com.example.administrator.myconnet.Function.Friends;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;

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


public class BackgroundTask_new_course extends AsyncTask<String,Void,String> {  // AsyncTask<params,progress,result>

    Context ctx;
    EditText editText1;
    EditText editText4;

    public BackgroundTask_new_course(Context ctx)
    {
        this.ctx =ctx;
    }

    BackgroundTask_new_course(Context ctx,EditText editText1,EditText editText4)
    {
        this.ctx =ctx;
        this.editText1 = editText1;
        this.editText4 = editText4;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... params) {

        String new_url1 = "http://140.127.218.198:8080/webapp/Friends/create_course.php";
        String new_url2 = "http://140.127.218.198:8080/webapp/Course/existed_course.php";
        String new_url3 = "http://140.127.218.198:8080/webapp/Course/course_detail.php";
        String new_url4 = "http://140.127.218.198:8080/webapp/Course/save_course_detail.php";
        String new_url5 = "http://140.127.218.198:8080/webapp/Course/course_detail_student_list.php";
        String new_url6 = "http://140.127.218.198:8080/webapp/Course/student_list_detail.php";
        String new_url7 = "http://140.127.218.198:8080/webapp/Course/create_group.php";
        String new_url8 = "http://140.127.218.198:8080/webapp/Course/course_detail_group_list.php";
        String new_url9 = "http://140.127.218.198:8080/webapp/Course/course_detail_delete.php";
        String new_url10 = "http://140.127.218.198:8080/webapp/Course/group_list_detail.php";   //
        String new_url11 = "http://140.127.218.198:8080/webapp/Course/update_group_crowd_remove.php";
        String new_url12 = "http://140.127.218.198:8080/webapp/Friends/delete_crowd.php";
        String new_url13 = "http://140.127.218.198:8080/webapp/Friends/kick_out_players.php";
        String new_url14 = "http://140.127.218.198:8080/webapp/Friends/alter_players_crowd.php";
        String new_url15 = "http://140.127.218.198:8080/webapp/Friends/get_existed_crowd.php";
        String new_url16 = "http://140.127.218.198:8080/webapp/Course/create_group_NP.php";

        String method = params[0];

        if (method.equals("CreateCourse")) {        // 建立課程功能

            String UID = params[1];
            String new_course_name = params[2];
            String new_course_info = params[3];
            String curDay = params[4];
            String crowd_amount = params[5];

            try {
                URL url = new URL(new_url1);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8") + "&" +
                        URLEncoder.encode("new_course_name", "UTF-8") + "=" + URLEncoder.encode(new_course_name, "UTF-8") + "&" +
                        URLEncoder.encode("new_course_info", "UTF-8") + "=" + URLEncoder.encode(new_course_info, "UTF-8") + "&" +
                        URLEncoder.encode("curDay", "UTF-8") + "=" + URLEncoder.encode(curDay, "UTF-8") + "&" +
                        URLEncoder.encode("crowd_amount", "UTF-8") + "=" + URLEncoder.encode(crowd_amount, "UTF-8");
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
        } else if (method.equals("ExistedCourse")) {    // 查詢現有課程功能

            String UID = params[1];

            try {
                URL url = new URL(new_url2);
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
        } else if (method.equals("CourseDetail")) {     // 查詢課程詳細資訊功能

            String course_name = params[1];
            String UID = params[2];

            try {
                URL url = new URL(new_url3);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8") + "&" +
                        URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8");
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
        } else if (method.equals("CourseDetail_edit")) {    // 編輯課程資訊功能

            String course_name = params[1];
            String UID = params[2];
            String name = params[3];
            String info = params[4];

            try {
                URL url = new URL(new_url4);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8") + "&" +
                            URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8") + "&" +
                            URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
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
        } else if (method.equals("CourseDetail_student_list")) {

            String course_name = params[1];
            String UID = params[2];

            try {
                URL url = new URL(new_url5);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8") + "&" +
                        URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8");
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
        } else if (method.equals("StudentList_detail")) {

            String course_name = params[1];
            String UID = params[2];
            String player_name = params[3];

            try {
                URL url = new URL(new_url6);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8") + "&" +
                        URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8") + "&" +
                        URLEncoder.encode("player_name", "UTF-8") + "=" + URLEncoder.encode(player_name, "UTF-8");
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
        } else if (method.equals("CreateGroup")) {

            String UID = params[1];
            String group_name = params[2];
            String selected_player = params[3];
            String course_name = params[4];

            try {
                URL url = new URL(new_url7);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID , "UTF-8") + "&" +
                              URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode( course_name , "UTF-8") + "&" +
                              URLEncoder.encode("selected_player", "UTF-8") + "=" + URLEncoder.encode(selected_player , "UTF-8") + "&" +
                              URLEncoder.encode("group_name", "UTF-8") + "=" + URLEncoder.encode(group_name , "UTF-8");
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
        } else if (method.equals("CourseDetail_group_list")) {

            String course_name = params[1];
            String UID = params[2];

            try {
                URL url = new URL(new_url8);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8") + "&" +
                              URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8");
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
        } else if (method.equals("CourseDetail_delete")) {      // 刪除課程功能

            String course_name = params[1];
            String UID = params[2];

            try {
                URL url = new URL(new_url9);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8") + "&" +
                        URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8");
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
        } else if (method.equals("StudentList_group_detail")) {

                String UID = params[1];
                String course_name = params[2];
                String group_name = params[3];

                try {
                    URL url = new URL(new_url10);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8") + "&" +
                                  URLEncoder.encode("group_name", "UTF-8") + "=" + URLEncoder.encode(group_name, "UTF-8") + "&" +
                                  URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8");
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
        } else if (method.equals("UpdateGroup_add_new_member")) {

            String UID = params[1];
            String course_name = params[2];
            String group_name = params[3];
            String selected_player = params[4];

            try {
                URL url = new URL(new_url10);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8") + "&" +
                        URLEncoder.encode("group_name", "UTF-8") + "=" + URLEncoder.encode(group_name, "UTF-8") + "&" +
                        URLEncoder.encode("selected_player", "UTF-8") + "=" + URLEncoder.encode(selected_player, "UTF-8") + "&" +
                        URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8");
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
        } else if (method.equals("UpdateGroup_crowd_remove")) {

            String course_name = params[1];
            String UID = params[2];
            String group_name = params[3];
            String student_delete_list = params[4];

            try {
                URL url = new URL(new_url11);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("student_delete_list", "UTF-8") + "=" + URLEncoder.encode(student_delete_list, "UTF-8") + "&" +
                              URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8") + "&" +
                              URLEncoder.encode("group_name", "UTF-8") + "=" + URLEncoder.encode(group_name, "UTF-8") + "&" +
                              URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8");
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
        } else if (method.equals("ManageCrowd_delete")) {       // 群組刪除功能

            String UID = params[1];
            String course_name = params[2];
            String crowd_name = params[3];

            try {
                URL url = new URL(new_url12);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8") + "&" +
                        URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8") + "&" +
                        URLEncoder.encode("crowd_name", "UTF-8") + "=" + URLEncoder.encode(crowd_name, "UTF-8");
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
        } else if (method.equals("ManageCrowd_kick_out_players")) {     // 踢出社團功能

            String NAME = params[1];
            String course_name = params[2];
            String crowd_name = params[3];

            try {
                URL url = new URL(new_url13);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("NAME", "UTF-8") + "=" + URLEncoder.encode(NAME, "UTF-8") + "&" +
                        URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8") + "&" +
                        URLEncoder.encode("crowd_name", "UTF-8") + "=" + URLEncoder.encode(crowd_name, "UTF-8");
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
        } else if (method.equals("alter players crowd")) {

            String course_name = params[1];
            String crowd_name = params[2];
            String alter_crowd_name = params[3];
            String NAME = params[4];

            try {
                URL url = new URL(new_url14);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8") + "&" +
                        URLEncoder.encode("crowd_name", "UTF-8") + "=" + URLEncoder.encode(crowd_name, "UTF-8") + "&" +
                        URLEncoder.encode("alter_crowd_name", "UTF-8") + "=" + URLEncoder.encode(alter_crowd_name, "UTF-8") + "&" +
                        URLEncoder.encode("NAME", "UTF-8") + "=" + URLEncoder.encode(NAME, "UTF-8");
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
        } else if (method.equals("get existed crowd")) {      // 更換選手群組功能

            String UID = params[1];
            String course_name = params[2];

            try {
                URL url = new URL(new_url15);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8") + "&" +
                        URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8");
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
        } else if (method.equals("CreateGroupNP")) {      // 更換選手群組功能

            String UID = params[1];
            String group_name = params[2];
            String course_name = params[3];

            try {
                URL url = new URL(new_url16);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("UID", "UTF-8") + "=" + URLEncoder.encode(UID, "UTF-8") + "&" +
                        URLEncoder.encode("group_name", "UTF-8") + "=" + URLEncoder.encode(group_name, "UTF-8") + "&" +
                        URLEncoder.encode("course_name", "UTF-8") + "=" + URLEncoder.encode(course_name, "UTF-8");
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




