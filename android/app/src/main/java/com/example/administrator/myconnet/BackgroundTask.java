package com.example.administrator.myconnet;
        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.widget.Toast;

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

public class BackgroundTask extends AsyncTask<String,Void,String> {
    AlertDialog alertDialog;
    Context ctx;
    BackgroundTask(Context ctx)
    {
        this.ctx =ctx;
    }
    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("登入資訊");
    }
    @Override
    protected String doInBackground(String... params) {

        String reg_url = "http://140.127.218.198:8080/web/register.php";
        String login_url = "http://140.127.218.198:8080/webapp/new_login.php";
        String method = params[0];

        if ( method.equals("register") ) {

            String id = params[1];
            String pwd = params[2];
            String name = params[3];
            String birthday = params[4];
            String height = params[5];
            String weight = params[6];
            String gender = params[7];
            String email  = params[8];
            String info   = params[9];

            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&" +
                        URLEncoder.encode("PWD", "UTF-8") + "=" + URLEncoder.encode(pwd, "UTF-8") + "&" +
                        URLEncoder.encode("NAME", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("BIRTHDAY", "UTF-8") + "=" + URLEncoder.encode(birthday, "UTF-8") + "&" +
                        URLEncoder.encode("HEIGHT", "UTF-8") + "=" + URLEncoder.encode(height, "UTF-8") + "&" +
                        URLEncoder.encode("WEIGHT", "UTF-8") + "=" + URLEncoder.encode(weight, "UTF-8") + "&" +
                        URLEncoder.encode("GENDER", "UTF-8") + "=" + URLEncoder.encode(gender, "UTF-8") + "&" +
                        URLEncoder.encode("EMAIL", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("INFO","UTF-8")+"="+ URLEncoder.encode(info, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                //httpURLConnection.connect();
                httpURLConnection.disconnect();
                return "Registration Success...";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if ( method.equals("login") ) {

            String login_name = params[1];
            String login_pass = params[2];

            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("ID","UTF-8")+"="+URLEncoder.encode(login_name,"UTF-8")+"&"+
                              URLEncoder.encode("PWD","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
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
                System.out.println(response);
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

        if ( result.equals("Registration Success...") ) {

            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();

        } else if ( result.equals("Login Fail.Please Try Again...") ) {

            alertDialog.setMessage(result);
            alertDialog.show();

        } else if (result.equals("2")) {

            Intent i = new Intent(this.ctx,Lobby2.class);
            Bundle bundle = new Bundle();
            bundle.putString( "UID" , result );
            i.putExtras(bundle);
            this.ctx.startActivity(i);

        } else {

            Intent i = new Intent(this.ctx, Lobby3.class);
            Bundle bundle = new Bundle();
            bundle.putString( "UID" , result);
            i.putExtras(bundle);
            this.ctx.startActivity(i);

        }
    }
}


