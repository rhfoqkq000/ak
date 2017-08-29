package com.donga.examples.boomin.listviewAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.ChangeSingleton;
import com.donga.examples.boomin.listviewItem.ChangeListViewItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rhfoq on 2017-02-08.
 */
public class ChangeListViewAdapter extends BaseAdapter {

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ChangeListViewItem> listViewItemList = new ArrayList<ChangeListViewItem>();

    private Map<Integer, View> myViews = new HashMap<Integer, View>();

    // ListViewAdapter의 생성자
    public ChangeListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        final ViewHolder viewHolder;
        View view = myViews.get(position);

//        convertView = null;
        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_change, null);

            viewHolder = new ViewHolder();

            // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
            viewHolder.text_title = convertView.findViewById(R.id.change_text);
            viewHolder.change_none_id = convertView.findViewById(R.id.change_none_id);
            viewHolder.checkBox = convertView.findViewById(R.id.change_checkbox);

            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            ChangeListViewItem listViewItem = listViewItemList.get(position);

            // 아이템 내 각 위젯에 데이터 반영
            viewHolder.text_title.setText(listViewItem.getTitle());
            viewHolder.change_none_id.setText(listViewItem.getCircle_id());

            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    ArrayList<String> changeArray = ChangeSingleton.getInstance().getmArray();
                    ArrayList<String> changeIdarray = ChangeSingleton.getInstance().getChangeIdList();
                    if(!isChecked){
                        changeArray.remove(viewHolder.text_title.getText().toString());
                        changeIdarray.remove(viewHolder.change_none_id.getText().toString());
                    }else {
                        changeArray.add(viewHolder.text_title.getText().toString());
                        changeIdarray.add(viewHolder.change_none_id.getText().toString());
                    }
                    ChangeSingleton.getInstance().setmArray(changeArray);
                    Log.e("ChangeAdapter", changeArray.toString()+", "+changeIdarray.toString());
                }
            });

            convertView.setTag(viewHolder);

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
    public void addItem(String title, String circle_id) {
        ChangeListViewItem item = new ChangeListViewItem();

        item.setTitle(title);
        item.setCircle_id(circle_id);

        listViewItemList.add(item);
    }

    class ViewHolder{
        TextView text_title, change_none_id;
        CheckBox checkBox;
    }
}