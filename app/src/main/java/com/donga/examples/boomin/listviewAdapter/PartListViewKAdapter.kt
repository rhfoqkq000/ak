package com.donga.examples.boomin.listviewAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.donga.examples.boomin.R
import com.donga.examples.boomin.listviewItem.PartListViewItem
import java.util.ArrayList

/**
 * Created by pmkjkr on 2017. 7. 19..
 */
class PartListViewKAdapter: BaseAdapter() {
    private val ITEM_VIEW_TYPE_TITLE = 0
    private val ITEM_VIEW_TYPE_NAME = 1
    private val ITEM_VIEW_TYPE_CONTENT = 2
    private val ITEM_VIEW_TYPE_MAX = 3

    // 아이템 데이터 리스트.
    private val listViewItemList = ArrayList<PartListViewItem>()

    fun PartListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    override fun getCount(): Int {
        return listViewItemList.size
    }

    override fun getViewTypeCount(): Int {
        return ITEM_VIEW_TYPE_MAX
    }

    // position 위치의 아이템 타입 리턴.
    override fun getItemViewType(position: Int): Int {
        return listViewItemList[position].type
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val context = parent.context
        val viewType = getItemViewType(position)

        val holder: CustomViewHolder

        convertView = null
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            holder = CustomViewHolder()

            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            val listViewItem = listViewItemList[position]

            when (viewType) {
                ITEM_VIEW_TYPE_TITLE -> {
                    convertView = inflater.inflate(R.layout.listview_part_title,
                            parent, false)
                    holder.year = convertView!!.findViewById<View>(R.id.part_title_year) as TextView
                    holder.term = convertView.findViewById<View>(R.id.part_title_term) as TextView

                    holder.year!!.text = listViewItem.title_year
                    holder.term!!.text = listViewItem.title_term
                }
                ITEM_VIEW_TYPE_NAME -> {
                    convertView = inflater.inflate(R.layout.listview_part_name,
                            parent, false)
                    holder.subject = convertView!!.findViewById<View>(R.id.part_name_subject) as TextView
                    holder.grade = convertView.findViewById<View>(R.id.part_name_grade) as TextView

                    holder.subject!!.text = listViewItem.name_subject
                    holder.grade!!.text = listViewItem.name_grade
                }
                ITEM_VIEW_TYPE_CONTENT -> {
                    convertView = inflater.inflate(R.layout.listview_part_content,
                            parent, false)

                    holder.subject2 = convertView!!.findViewById<View>(R.id.part_content_subject) as TextView
                    holder.grade2 = convertView.findViewById<View>(R.id.part_content_grade) as TextView
                    holder.distin = convertView.findViewById<View>(R.id.part_content_distin_none) as TextView
                    holder.grade_number = convertView.findViewById<View>(R.id.part_content_grade_number_none) as TextView

                    holder.subject2!!.text = listViewItem.content_subject
                    holder.grade2!!.text = listViewItem.content_grade
                    holder.distin!!.text = listViewItem.distin
                    holder.grade_number!!.text = listViewItem.grade_number
                }
            }
        }

        return convertView!!
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    override fun getItem(position: Int): Any {
        return listViewItemList[position]
    }

    fun getItems(position: Int): ArrayList<String> {
        val getItems = ArrayList<String>()
        getItems.add(listViewItemList[position].content_subject)
        getItems.add(listViewItemList[position].distin)
        getItems.add(listViewItemList[position].grade_number)
        return getItems
    }

    // 첫 번째 아이템 추가를 위한 함수.
    fun addItem(year: String, term: String) {
        val item = PartListViewItem()

        item.type = ITEM_VIEW_TYPE_TITLE
        item.title_year = year
        item.title_term = term

        listViewItemList.add(item)
    }

    // 두 번째 아이템 추가를 위한 함수.
    fun addItem1(subject: String, grade: String) {
        val item = PartListViewItem()

        item.type = ITEM_VIEW_TYPE_NAME
        item.name_subject = subject
        item.name_grade = grade

        listViewItemList.add(item)
    }

    // 세 번째 아이템 추가를 위한 함수.
    fun addItem2(none: String, subject: String, grade: String, distin: String, grade_number: String) {
        val item = PartListViewItem()

        item.type = ITEM_VIEW_TYPE_CONTENT
        item.content_none = none
        item.content_subject = subject
        item.content_grade = grade
        item.distin = distin
        item.grade_number = grade_number

        listViewItemList.add(item)
    }

    inner class CustomViewHolder {
        var year: TextView? = null
        var term: TextView? = null
        var subject: TextView? = null
        var grade: TextView? = null
        var subject2: TextView? = null
        var grade2: TextView? = null
        var distin: TextView? = null
        var grade_number: TextView? = null
    }
}