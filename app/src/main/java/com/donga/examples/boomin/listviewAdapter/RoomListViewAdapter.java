package com.donga.examples.boomin.listviewAdapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.donga.examples.boomin.R;
import com.donga.examples.boomin.listviewItem.RoomListViewItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rhfoq on 2017-02-08.
 */
public class RoomListViewAdapter extends BaseAdapter {

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<RoomListViewItem> listViewItemList = new ArrayList<RoomListViewItem>();

    private Map<Integer, View> myViews = new HashMap<Integer, View>();

    // ListViewAdapter의 생성자
    public RoomListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        ViewHolder viewHolder = null;
        View view = myViews.get(position);

        convertView = null;
        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_room, null);

            viewHolder = new ViewHolder();

            // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
            viewHolder.text_room1 = (TextView) convertView.findViewById(R.id.text_room1);
            viewHolder.text_room2 = (TextView) convertView.findViewById(R.id.text_room2);
            viewHolder.text_room3 = (TextView) convertView.findViewById(R.id.text_room3);
            viewHolder.text_room4 = (TextView) convertView.findViewById(R.id.text_room4);
            viewHolder.text_room5 = (TextView) convertView.findViewById(R.id.text_room5);


            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            RoomListViewItem listViewItem = listViewItemList.get(position);

            double numInt = Double.parseDouble(listViewItem.getTitle5());
            long math= Math.round(numInt);
            String change = String.valueOf(math);

            // 아이템 내 각 위젯에 데이터 반영
            viewHolder.text_room1.setText(listViewItem.getTitle1());
            viewHolder.text_room2.setText(listViewItem.getTitle2());
            viewHolder.text_room3.setText(listViewItem.getTitle3());
            viewHolder.text_room4.setText(listViewItem.getTitle4());
            viewHolder.text_room5.setText(change+"%");


            convertView.setTag(viewHolder);


            if (Float.parseFloat(listViewItem.getTitle5()) == 100.0) {
                viewHolder.text_room5.setTextColor(Color.RED);

            } else if(50.1 <= Float.parseFloat(listViewItem.getTitle5()) && Float.parseFloat(listViewItem.getTitle5()) <= 99.9){
                viewHolder.text_room5.setTextColor(Color.parseColor("#baba00"));
            } else{
                viewHolder.text_room5.setTextColor(Color.parseColor("#128c00"));
            }
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        myViews.put(position, view);


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

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String title1, String title2, String title3, String title4, String title5) {
        RoomListViewItem item = new RoomListViewItem();

        item.setTitle1(title1);
        item.setTitle2(title2);
        item.setTitle3(title3);
        item.setTitle4(title4);
        item.setTitle5(title5);

        listViewItemList.add(item);
    }

    class ViewHolder{
        TextView text_room1, text_room2, text_room3, text_room4, text_room5;
    }
}