package com.donga.examples.boomin.listviewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.donga.examples.boomin.listviewItem.PartListViewItem;
import com.donga.examples.boomin.R;

import java.util.ArrayList;

/**
 * Created by rhfoq on 2017-02-08.
 */
public class AllListViewAdapter extends BaseAdapter {
    private static final int ITEM_VIEW_TYPE_TITLE = 0;
    private static final int ITEM_VIEW_TYPE_NAME = 1;
    private static final int ITEM_VIEW_TYPE_CONTENT = 2;
    private static final int ITEM_VIEW_TYPE_MAX = 3;

    // 아이템 데이터 리스트.
    private ArrayList<PartListViewItem> listViewItemList = new ArrayList<PartListViewItem>();

//    public AllListViewAdapter() {
//
//    }

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
            PartListViewItem listViewItem = listViewItemList.get(position);

            switch (viewType) {
                case ITEM_VIEW_TYPE_TITLE:
                    convertView = inflater.inflate(R.layout.listview_part_title,
                            parent, false);
                    TextView part_title_year = convertView.findViewById(R.id.part_title_year);
                    TextView part_title_term = convertView.findViewById(R.id.part_title_term);

                    part_title_year.setText(listViewItem.getTitle_year());
                    part_title_term.setText(listViewItem.getTitle_term());
                    break;
                case ITEM_VIEW_TYPE_NAME:
                    convertView = inflater.inflate(R.layout.listview_part_name,
                            parent, false);
                    TextView part_name_subject = convertView.findViewById(R.id.part_name_subject);
                    TextView part_name_grade = convertView.findViewById(R.id.part_name_grade);

                    part_name_subject.setText(listViewItem.getName_subject());
                    part_name_grade.setText(listViewItem.getName_grade());
                    break;
                case ITEM_VIEW_TYPE_CONTENT:
                    convertView = inflater.inflate(R.layout.listview_part_content,
                            parent, false);

                    TextView part_content_subject = convertView.findViewById(R.id.part_content_subject);
                    TextView part_content_grade = convertView.findViewById(R.id.part_content_grade);

                    part_content_subject.setText(listViewItem.getContent_subject());
                    part_content_grade.setText(listViewItem.getContent_grade());
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
    public void addItem(String year, String term) {
        PartListViewItem item = new PartListViewItem();

        item.setType(ITEM_VIEW_TYPE_TITLE);
        item.setTitle_year(year);
        item.setTitle_term(term);

        listViewItemList.add(item);
    }

    // 두 번째 아이템 추가를 위한 함수.
    public void addItem1(String subject, String grade) {
        PartListViewItem item = new PartListViewItem();

        item.setType(ITEM_VIEW_TYPE_NAME);
        item.setName_subject(subject);
        item.setName_grade(grade);

        listViewItemList.add(item);
    }

    // 세 번째 아이템 추가를 위한 함수.
    public void addItem2(String subject, String grade) {
        PartListViewItem item = new PartListViewItem();

        item.setType(ITEM_VIEW_TYPE_CONTENT);
        item.setContent_subject(subject);
        item.setContent_grade(grade);

        listViewItemList.add(item);
    }
}