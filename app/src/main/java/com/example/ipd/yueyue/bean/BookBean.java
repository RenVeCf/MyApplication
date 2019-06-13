package com.example.ipd.yueyue.bean;

import java.util.ArrayList;
import java.util.List;

public class BookBean {
    public List<String> list = new ArrayList<String>();
    private String Texttest;

    public BookBean() {
        list.add("fdsaf");
    }

    public String getTexttest() {
        return Texttest;
    }

    public void setTexttest(String texttest) {
        Texttest = texttest;
    }
}
