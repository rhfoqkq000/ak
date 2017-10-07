package com.donga.examples.boomin.listviewAdapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.donga.examples.boomin.R;
import com.donga.examples.boomin.activity.NoticeDetailActivity;
import com.donga.examples.boomin.listviewItem.NoticeListViewItem;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by nature on 16. 7. 19.
 */
public class NoticeListViewAdapter extends BaseAdapter {

    private ArrayList<NoticeListViewItem> mlistData = new ArrayList<NoticeListViewItem>();

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
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_notice, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.notice_text);
        TextView timeView = convertView.findViewById(R.id.notice_time);

        final NoticeListViewItem listData = mlistData.get(position);

        textView.setText(listData.getNotice_text());
        timeView.setText(listData.getNotice_time());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NoticeDetailActivity.class);
                intent.putExtra("title", listData.getNotice_text());
                intent.putExtra("time", listData.getNotice_time());
                intent.putExtra("content", listData.getNotice_content());
                startActivity(context, intent, null);
            }
        });

        return convertView;
    }

    public void addItem(String text, String time, String content){
        NoticeListViewItem addInfo = new NoticeListViewItem();
        addInfo.setNotice_text(text);
        addInfo.setNotice_time(time);
        addInfo.setNotice_content(content);
        mlistData.add(addInfo);
    }
}