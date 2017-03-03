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
public class PartListViewAdapter extends BaseAdapter {
    private static final int ITEM_VIEW_TYPE_TITLE = 0 ;
    private static final int ITEM_VIEW_TYPE_NAME = 1 ;
    private static final int ITEM_VIEW_TYPE_CONTENT = 2 ;
    private static final int ITEM_VIEW_TYPE_MAX = 3 ;

    // 아이템 데이터 리스트.
    private ArrayList<PartListViewItem> listViewItemList = new ArrayList<PartListViewItem>() ;

    public PartListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_VIEW_TYPE_MAX ;
    }

    // position 위치의 아이템 타입 리턴.
    @Override
    public int getItemViewType(int position) {
        return listViewItemList.get(position).getType() ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        int viewType = getItemViewType(position) ;

        CustomViewHolder holder;

        convertView = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

            holder = new CustomViewHolder();

            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            PartListViewItem listViewItem = listViewItemList.get(position);

            switch (viewType) {
                case ITEM_VIEW_TYPE_TITLE:
                    convertView = inflater.inflate(R.layout.listview_part_title,
                            parent, false);
                    holder.year = (TextView) convertView.findViewById(R.id.part_title_year) ;
                    holder.term = (TextView) convertView.findViewById(R.id.part_title_term) ;

                    holder.year.setText(listViewItem.getTitle_year());
                    holder.term.setText(listViewItem.getTitle_term());
                    break;
                case ITEM_VIEW_TYPE_NAME:
                    convertView = inflater.inflate(R.layout.listview_part_name,
                            parent, false);
                    holder.subject = (TextView) convertView.findViewById(R.id.part_name_subject) ;
                    holder.grade = (TextView) convertView.findViewById(R.id.part_name_grade) ;

                    holder.subject.setText(listViewItem.getName_subject());
                    holder.grade.setText(listViewItem.getName_grade());
                    break;
                case ITEM_VIEW_TYPE_CONTENT:
                    convertView = inflater.inflate(R.layout.listview_part_content,
                            parent, false);

                    holder.subject2 = (TextView) convertView.findViewById(R.id.part_content_subject) ;
                    holder.grade2 = (TextView) convertView.findViewById(R.id.part_content_grade) ;
                    holder.distin = (TextView) convertView.findViewById(R.id.part_content_distin_none);
                    holder.grade_number = (TextView) convertView.findViewById(R.id.part_content_grade_number_none);

                    holder.subject2.setText(listViewItem.getContent_subject());
                    holder.grade2.setText(listViewItem.getContent_grade());
                    holder.distin.setText(listViewItem.getDistin());
                    holder.grade_number.setText(listViewItem.getGrade_number());
                    break;
            }
        }

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    public ArrayList<String> getItems(int position){
        ArrayList<String> getItems = new ArrayList<>();
        getItems.add(listViewItemList.get(position).getContent_subject());
        getItems.add(listViewItemList.get(position).getDistin());
        getItems.add(listViewItemList.get(position).getGrade_number());
        return getItems;
    }

    // 첫 번째 아이템 추가를 위한 함수.
    public void addItem(String year, String term) {
        PartListViewItem item = new PartListViewItem() ;

        item.setType(ITEM_VIEW_TYPE_TITLE) ;
        item.setTitle_year(year);
        item.setTitle_term(term);

        listViewItemList.add(item) ;
    }
    // 두 번째 아이템 추가를 위한 함수.
    public void addItem1(String subject, String grade) {
        PartListViewItem item = new PartListViewItem() ;

        item.setType(ITEM_VIEW_TYPE_NAME) ;
        item.setName_subject(subject);
        item.setName_grade(grade);

        listViewItemList.add(item);
    }

//    // 세 번째 아이템 추가를 위한 함수.
//    public void addItem2(String subject, String grade) {
//        PartListViewItem item = new PartListViewItem() ;
//
//        item.setType(ITEM_VIEW_TYPE_CONTENT) ;
//        item.setContent_subject(subject);
//        item.setContent_grade(grade);
//
//        listViewItemList.add(item);
//    }

    // 세 번째 아이템 추가를 위한 함수.
    public void addItem2(String none, String subject, String grade, String distin, String grade_number) {
        PartListViewItem item = new PartListViewItem() ;

        item.setType(ITEM_VIEW_TYPE_CONTENT) ;
        item.setContent_none(none);
        item.setContent_subject(subject);
        item.setContent_grade(grade);
        item.setDistin(distin);
//        Log.i("setDistin", distin);
        item.setGrade_number(grade_number);
//        Log.i("setGrade_number", grade_number);

        listViewItemList.add(item);
    }

    public class CustomViewHolder {
        public TextView year, term, subject, grade, subject2, grade2, distin, grade_number;
    }
}