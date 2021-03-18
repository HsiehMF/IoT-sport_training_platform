package com.example.administrator.myconnet;
        import android.app.Activity;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Switch;

        import com.example.administrator.myconnet.Function.Public.Public;

        import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity{

    EditText ET_NAME,ET_PASS;
    String login_name,login_pass;
    String checkLogin = null;
    Intent intent = new Intent();
    Bundle bundle = new Bundle();
    public static MainActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/wind.ttf");
        Typeface noto = Typeface.createFromAsset(getAssets(),"fonts/notosanscjktcmedium.ttf");

        SharedPreferences pref = getSharedPreferences("prefs", MODE_PRIVATE);

        checkLogin = pref.getString("UID", "UID doesn't exist");
            if (!checkLogin.equals("UID doesn't exist")) {

                if (checkLogin.equals("2") || checkLogin.equals("8")) {
                    intent.setClass(this, Lobby2.class);  }
                else {
                    intent.setClass(this, Lobby3.class);
                }

                bundle.putString("UID", checkLogin);   // 將以往儲存的資料放入 bundle 讓下一頁讀取
                intent.putExtras(bundle);
                startActivity(intent);
                MainActivity.instance.finish();

            }

        ET_NAME = (EditText)findViewById(R.id.user_name);
        ET_PASS = (EditText)findViewById(R.id.user_pass);
        ET_NAME.setTypeface(typeface);
        ET_PASS.setTypeface(typeface);

        Button button1 = (Button) findViewById(R.id.button2);
        Button button2 = (Button) findViewById(R.id.button3);
        button1.setTypeface(noto);
        button2.setTypeface(noto);

    }

    public void userReg(View view) {  startActivity(new Intent(this, Register.class));  }

    public void userLogin(View view) throws ExecutionException, InterruptedException {

        login_name = ET_NAME.getText().toString();
        login_pass = ET_PASS.getText().toString();
        String method = "login";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        String result = backgroundTask.execute(method,login_name,login_pass).get();

        if ( result.equals("Registration Success...") ){

        } else if ( result.equals("Login Fail.Please Try Again...") ){

        } else {
            finish();
        }
    }
}

