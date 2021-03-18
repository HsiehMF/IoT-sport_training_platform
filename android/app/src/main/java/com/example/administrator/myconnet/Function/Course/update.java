package com.example.administrator.myconnet.Function.Course;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myconnet.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class update extends AppCompatActivity {
    ListView lv;
    ArrayList list = new ArrayList();
    File file=null;
    File[] fList=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        lv=(ListView)findViewById(R.id.listView);
        File dir = this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        file = new File( dir.getParent() + "/" + dir.getName() + "/Record");
        fList=file.listFiles();
        for (File file1:fList){
            list.add(file1.getName());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);

    }
    public void upload(View v) {
        for (File file1:fList){
            update(file1.getName());
        }
    }

    private String getMimeType(String path) {

        String extension = MimeTypeMap.getFileExtensionFromUrl(path);

        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }
    private void update(final String filename){

        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Uploading");
        progress.setMessage("Please wait...");
        progress.show();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                File dir = update.this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                String path =  dir.getParent() + "/" + dir.getName() + "/Record/" + filename;
                File f= new File(path);
                String content_type  = getMimeType(path);

                System.out.println("filename\t" + filename);
//                String file_path = path+".txt";
                System.out.println("file_path\t"+path);
                OkHttpClient client = new OkHttpClient();
                RequestBody file_body = RequestBody.create(MediaType.parse(content_type),f);
                RequestBody request_body = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
//                            .addFormDataPart("type",content_type)
                        .addFormDataPart("filename", filename)
                        .addFormDataPart("upload", path.substring(path.lastIndexOf("/") + 1), file_body)
                        .build();

                Request request = new Request.Builder()
                        .url("http://140.127.218.198:8080/webapp/uploads/upload2.php")
                        .post(request_body)
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    String res = response.body().string();
                    System.out.println("res\t" + res);
                    if(!response.isSuccessful()){
                        throw new IOException("Error : "+response);
                    }
                    f.delete();

//                    Line_judgement line_judgement=new Line_judgement(filename);
                    handler.obtainMessage(1, 4, -1, filename).sendToTarget();


                } catch (IOException e) {
                    handler.obtainMessage(0, 4, -1, filename).sendToTarget();
                    e.printStackTrace();

                }
            }
        });
        t.start();
        progress.dismiss();
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            //’|AiIuI￠
            if (msg.what==1) {
                String str = (String)msg.obj;
                str = str.trim();
                ArrayList list1 = new ArrayList();
//                list.clear();
                File dir = update.this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                file = new File( dir.getParent() + "/" + dir.getName() + "/Record");
                fList=file.listFiles();
                if (fList.length != 0){
                    for (File file1:fList){
                        list1.add(file1.getName());
                    }
                }else {
                    list1.clear();
                }
                Toast.makeText(update.this, str+"\t\t上傳成功", Toast.LENGTH_SHORT).show();
                lv=(ListView)findViewById(R.id.listView);
                ArrayAdapter adapter = new ArrayAdapter(update.this, android.R.layout.simple_list_item_1, list1);
                lv.setAdapter(adapter);

            }else{
                String str = (String)msg.obj;
                str = str.trim();
                Toast.makeText(update.this,str+"\t\t上傳失敗，請在試一次", Toast.LENGTH_SHORT).show();

            }
            super.handleMessage(msg);
        }

    };
}
