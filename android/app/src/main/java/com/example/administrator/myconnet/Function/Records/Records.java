package com.example.administrator.myconnet.Function.Records;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.myconnet.R;

public class Records extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
    }
    public void setDate(View view) {
        PickerDialogs pickerDialogs = new PickerDialogs();
        pickerDialogs.show(getSupportFragmentManager(),"date_picker");
    }
}
