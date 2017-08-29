package com.donga.examples.boomin.listviewAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.donga.examples.boomin.R;
import com.donga.examples.boomin.listviewItem.AttendListViewItem;

import java.util.ArrayList;

/**
 * Created by rhfoq on 2017-02-08.
 */
public class AttendListViewAdapter extends BaseAdapter {
    // 아이템 데이터 리스트.
    private ArrayList<AttendListViewItem> listViewItemList = new ArrayList<AttendListViewItem>();

    public AttendListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        CustomViewHolder holder;

        convertView = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            holder = new CustomViewHolder();

            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            AttendListViewItem listViewItem = listViewItemList.get(position);


            convertView = inflater.inflate(R.layout.listview_attend, parent, false);
            holder.attend_id = convertView.findViewById(R.id.attend_id);
            holder.attend_name = convertView.findViewById(R.id.attend_name);
            holder.attend_on = convertView.findViewById(R.id.attend_on);

            holder.attend_id.setText(listViewItem.getAttend_id());
            holder.attend_name.setText(listViewItem.getAttend_name());
            holder.attend_on.setText(listViewItem.getAttend_on());

            String on = holder.attend_on.getText().toString();
            if(on.equals("불참")||on.equals("무응답")){
                convertView.setBackgroundColor(Color.parseColor("#D8D8D8"));
            }

        }

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    // 첫 번째 아이템 추가를 위한 함수.
    public void addItem(String attend_id, String attend_name, String attend_on) {
        AttendListViewItem item = new AttendListViewItem();

        item.setAttend_id(attend_id);
        item.setAttend_name(attend_name);
        item.setAttend_on(attend_on);

        listViewItemList.add(item);
    }

    private class CustomViewHolder {
        private TextView attend_id, attend_name, attend_on;
    }
}