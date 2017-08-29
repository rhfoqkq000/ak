package com.donga.examples.boomin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.donga.examples.boomin.R
import com.donga.examples.boomin.Singleton.GradeSingleton
import com.donga.examples.boomin.listviewAdapter.PartListViewKAdapter
import kotlinx.android.synthetic.main.fragment_achiev_part.*
import java.util.ArrayList

/**
 * Created by pmkjkr on 2017. 7. 19..
 */
class Stu_Achiev_Part_K_Fragment: Fragment() {
    private var adapter: PartListViewKAdapter? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        below2.setOnClickListener{ achiev_bottom.visibility = View.GONE }

        adapter = PartListViewKAdapter()
        list_part.adapter = adapter

        list_part.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            if (adapter!!.getItems(position)[1] != null) {
                distin.text = adapter!!.getItems(position)[1]
                grade_number.text = adapter!!.getItems(position)[2]
                achiev_bottom.visibility = View.VISIBLE
            } else {
                achiev_bottom.visibility = View.GONE
            }
        }

        get.text = GradeSingleton.getInstance().partGrade
        aver.text = GradeSingleton.getInstance().partAvg

//        val GradeDetailSize = GradeSingleton.getInstance().detail2.size
        val GradeDetailSize = GradeSingleton.getInstance().allResultBody2.detail!!.size

//        val DetailList = GradeSingleton.getInstance().detail2
        val DetailList = GradeSingleton.getInstance().allResultBody2.detail!!
        val yearList = ArrayList<String>()

        for (i in 1..GradeDetailSize - 1) {
//            if (GradeSingleton.getInstance().detail2[i][0].length == 4) {
            if (DetailList[i][0].length == 4) {
                yearList.add(i.toString())
            }
        }
        val fTitle = ArrayList<String>(yearList.size)
        val sTitle = ArrayList<String>(yearList.size)
        val position = ArrayList<Int>()
        for (i in 1..GradeDetailSize - 1) {
            if (DetailList[i][0].length == 4) {
                fTitle.add(DetailList[i][0])
                sTitle.add(DetailList[i][1])
            }

        }
        for (q in yearList.indices) {
            adapter!!.addItem(fTitle[q], sTitle[q])
            adapter!!.addItem1("교과목명", "성적")
            if (q < yearList.size - 1) {
                for (j in Integer.parseInt(yearList[q])..Integer.parseInt(yearList[q + 1]) - 1) {
                    adapter!!.addItem2(j.toString(), DetailList[j][3], DetailList[j][6], DetailList[j][4], DetailList[j][5])
                    position.add(j)
                }
            } else {
                for (k in Integer.parseInt(yearList[q])..GradeDetailSize - 1) {
                    adapter!!.addItem2(k.toString(), DetailList[k][3], DetailList[k][6], DetailList[k][4], DetailList[k][5])
                    position.add(k)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_achiev_part, container, false)
    }
}