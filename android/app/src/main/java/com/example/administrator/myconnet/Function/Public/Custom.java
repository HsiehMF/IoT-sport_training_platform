package com.example.administrator.myconnet.Function.Public;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myconnet.R;

import java.util.ArrayList;

public class Custom extends ArrayAdapter<String> {

    Bundle bundle = new Bundle();
    View customView;
    ArrayList<String> O_CHOOSE = new ArrayList<>();

    public Custom(Context context, ArrayList<String> O_CHOOSE, Bundle bundle) {

        super(context, R.layout.listview_for_create_group, O_CHOOSE);
        this.O_CHOOSE = O_CHOOSE;
        this.bundle = bundle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        customView = inflater.inflate(R.layout.listview_for_create_group, parent, false);

        final TextView textView = (TextView) customView.findViewById(R.id.tv_style_for_create_group);
        final ImageView imageView = (ImageView) customView.findViewById(R.id.iv_style_for_create_group);
        final LinearLayout linearLayout = (LinearLayout) customView.findViewById(R.id.LL_style_for_create_group);

        textView.setId(position);
        imageView.setId(position);
        linearLayout.setId(position);

        String selectedItem = getItem(position);
        textView.setText(selectedItem);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selected = textView.getText().toString();       // 選手名稱
                linearLayout.setVisibility(View.GONE);                // 將選手列表去除
                O_CHOOSE.remove(selected);                             // 移除在陣列中的選手
                String[] selected_player = new String[O_CHOOSE.size()];
                selected_player = O_CHOOSE.toArray(selected_player);
                bundle.putStringArray("selected_player", selected_player);  // 儲存至 bundle , 否則該 bundle 永遠是外面所儲存的變數

            }
        });

        return customView;
    }
}