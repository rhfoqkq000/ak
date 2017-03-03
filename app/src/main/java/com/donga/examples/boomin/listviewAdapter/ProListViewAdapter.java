package com.donga.examples.boomin.listviewAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.donga.examples.boomin.listviewItem.ProListViewItem;
import com.donga.examples.boomin.R;

import java.util.ArrayList;

/**
 * Created by rhfoq on 2017-02-08.
 */
public class ProListViewAdapter extends BaseAdapter {
    private static final int ITEM_VIEW_TYPE_TITLE = 0;
    private static final int ITEM_VIEW_TYPE_NAME = 1;
    private static final int ITEM_VIEW_TYPE_MAX = 2;

    // 아이템 데이터 리스트.
    private ArrayList<ProListViewItem> listViewItemList = new ArrayList<ProListViewItem>();

    public ProListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_VIEW_TYPE_MAX;
    }

    // position 위치의 아이템 타입 리턴.
    @Override
    public int getItemViewType(int position) {
        return listViewItemList.get(position).getType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        int viewType = getItemViewType(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            ProListViewItem listViewItem = listViewItemList.get(position);

            switch (viewType) {
                case ITEM_VIEW_TYPE_TITLE:
                    convertView = inflater.inflate(R.layout.listview_pro_main_title,
                            parent, false);
                    TextView pro_main_title = (TextView) convertView.findViewById(R.id.pro_main_title);

                    pro_main_title.setText(listViewItem.getPro_main_title());

                    break;
                case ITEM_VIEW_TYPE_NAME:
                    convertView = inflater.inflate(R.layout.listview_pro_main_name,
                            parent, false);
                    TextView pro_main_name = (TextView) convertView.findViewById(R.id.pro_main_name);
                    ImageView pro_main_arrow = (ImageView) convertView.findViewById(R.id.pro_main_arrow);

                    pro_main_name.setText(listViewItem.getPro_main_name());
                    pro_main_arrow.setImageDrawable(listViewItem.getPro_main_arrow());
                    break;
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
    public void addItem(String title) {
        ProListViewItem item = new ProListViewItem();

        item.setType(ITEM_VIEW_TYPE_TITLE);
        item.setPro_main_title(title);

        listViewItemList.add(item);
    }

    // 두 번째 아이템 추가를 위한 함수.
    public void addItem1(String name, Drawable arrow) {
        ProListViewItem item = new ProListViewItem();

        item.setType(ITEM_VIEW_TYPE_NAME);
        item.setPro_main_name(name);
        item.setPro_main_arrow(arrow);

        listViewItemList.add(item);
    }
}