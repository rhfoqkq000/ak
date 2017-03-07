package com.donga.examples.boomin.listviewAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.donga.examples.boomin.listviewItem.HelpListViewItem;
import com.donga.examples.boomin.R;

import java.util.ArrayList;

/**
 * Created by nature on 16. 7. 19.
 */
public class HelpListViewAdapter extends BaseAdapter {

    public ArrayList<HelpListViewItem> mlistData = new ArrayList<HelpListViewItem>();

    public HelpListViewAdapter(){
    }

    public class ViewHolder{
        public TextView mtext;
        public ImageView micon;
    }

    public int getPosition(int position){
        return position;
    }

    @Override
    public int getCount() {
        return mlistData.size();
    }

    @Override
    public Object getItem(int position) {
        return mlistData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_help, parent, false);
        }

        TextView textView = (TextView)convertView.findViewById(R.id.text_help);
        ImageView iconImageView = (ImageView)convertView.findViewById(R.id.image_help);

        HelpListViewItem listData = mlistData.get(position);

        textView.setText(listData.getHelp_text());
        iconImageView.setImageDrawable(listData.getHelp_image());

        return convertView;
    }

    public void addItem(String text,Drawable icon){
        HelpListViewItem addInfo = new HelpListViewItem();
        addInfo.setHelp_text(text);
        addInfo.setHelp_image(icon);

        mlistData.add(addInfo);
    }
}