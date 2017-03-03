package com.donga.examples.boomin.listviewItem;

import android.graphics.drawable.Drawable;

/**
 * Created by rhfoq on 2017-02-08.
 */
public class ProListViewItem {
    private int type;

    private String pro_main_title;

    private String pro_main_name;
    private Drawable pro_main_arrow;

    public Drawable getPro_main_arrow() {
        return pro_main_arrow;
    }

    public void setPro_main_arrow(Drawable pro_main_arrow) {
        this.pro_main_arrow = pro_main_arrow;
    }

    public String getPro_main_name() {
        return pro_main_name;
    }

    public void setPro_main_name(String pro_main_name) {
        this.pro_main_name = pro_main_name;
    }

    public String getPro_main_title() {
        return pro_main_title;
    }

    public void setPro_main_title(String pro_main_title) {
        this.pro_main_title = pro_main_title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}