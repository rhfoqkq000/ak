package com.donga.examples.boomin.listviewItem;

/**
 * Created by rhfoq on 2017-02-08.
 */
public class ChangeListViewItem {
    private String text, circle_id;

    public void setTitle(String title) {
        text = title;
    }

    public String getTitle() {
        return this.text;
    }

    public String getCircle_id() {
        return circle_id;
    }

    public void setCircle_id(String circle_id) {
        this.circle_id = circle_id;
    }
}
