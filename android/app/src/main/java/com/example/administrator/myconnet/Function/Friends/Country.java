package com.example.administrator.myconnet.Function.Friends;

import java.util.ArrayList;

public class Country {

    String name = null;
    ArrayList arL_name = null;
    boolean selected = false;

    public Country(String name, boolean selected) {
        super();
        this.name = name;   // null , 將傳進來的 name 設給 this.name
        this.selected = selected;   // false , 將傳進來的狀態設給 this.selected
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public boolean isSelected() { return selected; }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public void setRemove() { this.name = null; }

}
