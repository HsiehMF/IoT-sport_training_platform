package com.example.administrator.myconnet.Function.Records;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/14.
 */
public class DateSettings implements DatePickerDialog.OnDateSetListener {
    Context context;
    public DateSettings(Context context){
        this.context = context;
    }
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        monthOfYear = monthOfYear + 1;    // 調整成正確月份
        Toast.makeText(context,"選擇 : "+year+ " / " +monthOfYear+ " / " +dayOfMonth ,Toast.LENGTH_SHORT).show();
    }
}
