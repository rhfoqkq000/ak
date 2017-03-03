package com.donga.examples.boomin.listviewItem;

import android.graphics.drawable.Drawable;

/**
 * Created by nature on 16. 7. 19.
 */
public class HelpListViewItem {

    public String help_text;
    public Drawable help_image;

    public Drawable getHelp_image() {
        return help_image;
    }

    public void setHelp_image(Drawable help_image) {
        this.help_image = help_image;
    }

    public String getHelp_text() {
        return help_text;
    }

    public void setHelp_text(String help_text) {
        this.help_text = help_text;
    }
}