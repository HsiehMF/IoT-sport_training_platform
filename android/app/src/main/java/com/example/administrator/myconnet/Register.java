package com.example.administrator.myconnet;
        import android.app.Activity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;

public class Register extends Activity {
    EditText ET_ID,ET_PWD,ET_NAME,ET_BIRTHDAY,ET_HEIGHT,ET_WEIGHT,ET_GENDER,ET_EMAIL,ET_INFO;
    String id,pwd,name,birthday,height,weight,gender,email,info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ET_ID = (EditText)findViewById(R.id.id);
        ET_PWD = (EditText)findViewById(R.id.pwd);
        ET_NAME = (EditText)findViewById(R.id.name);
        ET_BIRTHDAY = (EditText)findViewById(R.id.birthday);
        ET_HEIGHT = (EditText)findViewById(R.id.height);
        ET_WEIGHT = (EditText)findViewById(R.id.weight);
        ET_GENDER = (EditText)findViewById(R.id.gender);
        ET_EMAIL = (EditText)findViewById(R.id.email);
        ET_INFO = (EditText)findViewById(R.id.information);
    }
    public void userReg(View view){
        id = ET_ID.getText().toString();
        pwd = ET_PWD.getText().toString();
        name = ET_NAME.getText().toString();
        birthday = ET_BIRTHDAY.getText().toString();
        height = ET_HEIGHT.getText().toString();
        weight = ET_WEIGHT.getText().toString();
        gender = ET_GENDER.getText().toString();
        email = ET_EMAIL.getText().toString();
        info = ET_INFO.getText().toString();
        String method = "register";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,id,pwd,name,birthday,height,weight,gender,email,info);
        finish();
    }
}

