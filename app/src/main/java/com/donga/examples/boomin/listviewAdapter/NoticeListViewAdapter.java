package com.donga.examples.boomin.listviewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.donga.examples.boomin.R;
import com.donga.examples.boomin.listviewItem.NoticeListViewItem;

import java.util.ArrayList;

/**
 * Created by nature on 16. 7. 19.
 */
public class NoticeListViewAdapter extends BaseAdapter {

    public ArrayList<NoticeListViewItem> mlistData = new ArrayList<NoticeListViewItem>();

    public NoticeListViewAdapter(){
    }

    public class ViewHolder{
        public TextView mtext;
    }

    public String getMenuText(int position){
        return mlistData.get(position).getNotice_text();
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
            convertView = inflater.inflate(R.layout.listview_notice, parent, false);
        }

        TextView textView = (TextView)convertView.findViewById(R.id.notice_text);

        NoticeListViewItem listData = mlistData.get(position);

        textView.setText(listData.getNotice_text());

        return convertView;
    }

    public void addItem(String text){
        NoticeListViewItem addInfo = new NoticeListViewItem();
        addInfo.setNotice_text(text);

        mlistData.add(addInfo);
    }
}