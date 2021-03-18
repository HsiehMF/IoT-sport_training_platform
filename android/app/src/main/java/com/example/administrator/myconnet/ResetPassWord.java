package com.example.administrator.myconnet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class ResetPassWord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass_word);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(typeface);
        toolbar_title.setText("重設密碼");

        TextView textView1 = (TextView)findViewById(R.id.textView48);
        TextView textView2 = (TextView)findViewById(R.id.textView49);
        TextView textView3 = (TextView)findViewById(R.id.textView50);
        textView1.setTypeface(typeface);
        textView2.setTypeface(typeface);
        textView3.setTypeface(typeface);

        Button button1 = (Button) findViewById(R.id.button17);
        Button button2 = (Button) findViewById(R.id.button18);
        button1.setTypeface(typeface);
        button2.setTypeface(typeface);

        EditText editText1 = (EditText) findViewById(R.id.et_old_pwd);
        EditText editText2 = (EditText) findViewById(R.id.et_new_pwd);
        EditText editText3 = (EditText) findViewById(R.id.et_new_check_pwd);
        editText1.setTypeface(typeface);
        editText2.setTypeface(typeface);
        editText3.setTypeface(typeface);

    }

    public void resetDone(View view) throws ExecutionException, InterruptedException {

        EditText editText1 = (EditText) findViewById(R.id.et_old_pwd);
        EditText editText2 = (EditText) findViewById(R.id.et_new_pwd);
        EditText editText3 = (EditText) findViewById(R.id.et_new_check_pwd);
        String old_pwd = editText1.getText().toString();
        String new_pwd = editText2.getText().toString();
        String new_check_pwd = editText3.getText().toString();

        String method1 = "o_pwd";
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String UID = sharedPreferences.getString("UID", "UID doesn't exist");
        BackgroundTask_PWD backgroundTask_pwd1 = new BackgroundTask_PWD(this);
        String OldPass = backgroundTask_pwd1.execute(method1, UID).get();

        if ( OldPass.equals(old_pwd) && new_pwd.equals(new_check_pwd) && !old_pwd.equals(new_pwd) ) {

            String method2 = "update_pwd";
            BackgroundTask_PWD backgroundTask_pwd2 = new BackgroundTask_PWD(this);
            backgroundTask_pwd2.execute(method2, new_pwd , UID);
            Toast.makeText(this, "密碼變更成功", Toast.LENGTH_SHORT).show();
            finish();

        } else if (!OldPass.equals(old_pwd)) {
            Toast.makeText(this, "修改失敗,密碼錯誤", Toast.LENGTH_SHORT).show();
        } else if (!new_pwd.equals(new_check_pwd)) {
            Toast.makeText(this, "兩次輸入的密碼不一致", Toast.LENGTH_SHORT).show();
        } else if (new_pwd.equals("") && new_check_pwd.equals("")) {
            Toast.makeText(this, "注意，密碼不能為空", Toast.LENGTH_SHORT).show();
        }

    }
}
