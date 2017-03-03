package com.donga.examples.boomin.listviewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.donga.examples.boomin.R;
import com.donga.examples.boomin.listviewItem.MemberListViewItem;

import java.util.ArrayList;

/**
 * Created by rhfoq on 2017-02-08.
 */
public class MemberListViewAdapter extends BaseAdapter {
    private static final int ITEM_VIEW_TYPE_DIVI = 0;
    private static final int ITEM_VIEW_TYPE_LIST = 1;
    private static final int ITEM_VIEW_TYPE_MAX = 3;

    CustomViewHolder holder;

//    boolean[] checkBoxState;

    // 아이템 데이터 리스트.
    private ArrayList<MemberListViewItem> listViewItemList = new ArrayList<MemberListViewItem>();

    public MemberListViewAdapter() {
//        checkBoxState = new boolean[listViewItemList.size()];
    }

    public String getStuId(int position){
        return listViewItemList.get(position).getS_id();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        int viewType = getItemViewType(position);


        convertView = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            holder = new CustomViewHolder();

            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            MemberListViewItem listViewItem = listViewItemList.get(position);

            switch (viewType) {
                case ITEM_VIEW_TYPE_DIVI:
                    convertView = inflater.inflate(R.layout.listview_manage_divi,
                            parent, false);
                    holder.s_divi = (TextView) convertView.findViewById(R.id.manage_member_divi);

                    holder.s_divi.setText(listViewItem.getS_divi());
                    break;
                case ITEM_VIEW_TYPE_LIST:
                    convertView = inflater.inflate(R.layout.listview_manage_member,
                            parent, false);
                    holder.s_check = (CheckBox) convertView.findViewById(R.id.manage_member_check);
                    holder.s_id = (TextView) convertView.findViewById(R.id.manage_member_id);
                    holder.s_name = (TextView) convertView.findViewById(R.id.manage_member_name);

                    holder.s_id.setText(listViewItem.getS_id());
                    holder.s_name.setText(listViewItem.getS_name());

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
    public void addItem(String s_divi) {
        MemberListViewItem item = new MemberListViewItem();

        item.setType(ITEM_VIEW_TYPE_DIVI);
        item.setS_divi(s_divi);

        listViewItemList.add(item);
    }

    // 두 번째 아이템 추가를 위한 함수.
    public void addItem1(String s_id, String s_name) {
        MemberListViewItem item = new MemberListViewItem();

        item.setType(ITEM_VIEW_TYPE_LIST);
        item.setS_id(s_id);
        item.setS_name(s_name);

        listViewItemList.add(item);
    }

    public class CustomViewHolder {
        public TextView s_divi, s_id, s_name;
        public CheckBox s_check;
    }
}