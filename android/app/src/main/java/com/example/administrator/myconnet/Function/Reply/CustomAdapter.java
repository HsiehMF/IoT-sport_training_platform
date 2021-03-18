package com.example.administrator.myconnet.Function.Reply;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.administrator.myconnet.R;


public class CustomAdapter extends ArrayAdapter<String> {

    Bundle bundle = new Bundle();
    Menu menu;
    View customView;
    String[] student_list;

    public CustomAdapter(Context context, String[] student_list) {

        super(context, R.layout.spinner_style_for_replyselectcrowd, student_list);
        this.student_list = student_list;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        customView = inflater.inflate(R.layout.spinner_style_for_replyselectcrowd, parent, false);

        TextView textView = (TextView) customView.findViewById(R.id.tv_reply_select_crowd_schedule_name);
        TextView reply_select_crowd_name = (TextView) customView.findViewById(R.id.tv_reply_select_crowd_name);

        String selectedItem = getItem(position);
        String[] name = selectedItem.split(",");
        textView.setText(name[0]);
        reply_select_crowd_name.setText(name[1]);

        return customView;
    }
}
