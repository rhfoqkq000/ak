package com.donga.examples.boomin.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.donga.examples.boomin.R
import com.donga.examples.boomin.Singleton.GradeSingleton
import com.donga.examples.boomin.Singleton.InfoSingleton
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_achiev.*
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * Created by pmkjkr on 2017. 7. 18..
 */
class Stu_AchievKFragment: Fragment() {

    var sharedPreferences: SharedPreferences? = null

    private var fm: FragmentManager? = null
    internal lateinit var hash: HashMap<String, Int>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_achiev, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = activity.applicationContext.getSharedPreferences(resources.getString(R.string.SFLAG), Context.MODE_PRIVATE)

        hash = HashMap<String, Int>()
        hash.put("전학기", 0)
        hash.put("전적학교인정", 0)
        hash.put("1학기", 10)
        hash.put("계절학기(하기)", 11)
        hash.put("2학기", 20)
        hash.put("계절학기(동기)", 21)
        hash.put("협정대학이수", 30)
        hash.put("협정대학이수(1학기)", 31)
        hash.put("협정대학이수(하계)", 32)
        hash.put("협정대학이수(2학기)", 33)
        hash.put("협정대학이수(동계)", 34)
        hash.put("1학기국내협정대학이수", 50)
        hash.put("1학기국외협정대학이수", 51)
        hash.put("하계국내협정대학", 52)
        hash.put("하계국외협정대학", 53)
        hash.put("2학기국내협정대학이수", 60)
        hash.put("2학기국외협정대학이수", 61)
        hash.put("동계국내협정대학", 62)
        hash.put("동계국외협정대학", 63)

        val keyList = ArrayList<String>()
        val key = hash.keys
        val iterator = key.iterator()
        while (iterator.hasNext()) {
            val keyName = iterator.next()
            keyList.add(keyName)
        }

        fm = fragmentManager

//        val layoutInflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val bottom = layoutInflater.inflate(R.layout.fragment_achiev_part, null)
//
//                below.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        achiev_bottom.setVisibility(View.GONE);
//                    }
//                });

        achiev_all.setItems("전체학기성적", "일부학기성적")

        achiev_all.setOnItemSelectedListener { _, position, _, _ ->
            if (position == 0) {
                all_ok_card.visibility = View.VISIBLE
                part_layout.visibility = View.GONE
            } else {
                val years = ArrayList<String>()
                for (i in Integer.parseInt(SimpleDateFormat("yyyy").format(Date(System.currentTimeMillis()))) downTo Integer.parseInt(InfoSingleton.getInstance().year)) {
                    years.add(i.toString())
                }
                val objectList = years.toTypedArray()
                val years2 = Arrays.copyOf<String, Any>(objectList, objectList.size, Array<String>::class.java)
                achiev_year.setItems(*years2)
                achiev_side.setItems(keyList)

                all_ok_card.visibility = View.GONE
                part_layout.visibility = View.VISIBLE
            }
        }

        all_ok.setOnClickListener {
            FuelManager.instance.basePath = getString(R.string.retrofit_url)
            FuelManager.instance.baseParams = listOf("stuId" to InfoSingleton.getInstance().stuId, "stuPw" to InfoSingleton.getInstance().stuPw)
            "donga/getAllGrade".httpPost().responseObject(Achiev.Deserializer()) { _, _, result ->
                result.fold({ (result_code, result_body) ->

                    if (result_code == 1){

                        GradeSingleton.getInstance().allResultBody2 = result_body

                        hide.visibility = View.VISIBLE
                        val ft = fm!!.beginTransaction()
                        val achiev_all_fragment = Stu_Achiev_All_K_Fragment()
                        ft.replace(R.id.frag_change, achiev_all_fragment)
                        ft.commit()
                    }else{
                        Log.i("ProKActivity", "result code not matched")
                        Toasty.error(activity.applicationContext, "불러오기 실패").show()
                    }
                }, { _ ->
                    Log.e("ERROR","데이터 통신 불가")
                    Toasty.error(activity.applicationContext, "불러오기 실패").show()
                })

            }
        }


        part_ok.setOnClickListener {
            Logger.d(achiev_year.getItems<String>()[achiev_year.selectedIndex].toString())
            Logger.d(hash[achiev_side.getItems<String>()[achiev_side.selectedIndex].toString()].toString())
            FuelManager.instance.basePath = getString(R.string.retrofit_url)
            FuelManager.instance.baseParams = listOf("stuId" to InfoSingleton.getInstance().stuId,
                    "stuPw" to InfoSingleton.getInstance().stuPw,
                    "year" to achiev_year.getItems<String>()[achiev_year.selectedIndex].toString(),
                    "smt" to hash[achiev_side.getItems<String>()[achiev_side.selectedIndex].toString()].toString())
            "donga/getSpeGrade".httpPost().responseObject(Achiev.Deserializer()) { _, _, result ->
                result.fold({ (result_code, result_body) ->

                    if (result_code == 1){
                        GradeSingleton.getInstance().allResultBody2 = result_body
                        GradeSingleton.getInstance().partGrade = result_body!!.allGrade
                        GradeSingleton.getInstance().partAvg = result_body.avgGrade
                        hide.visibility = View.VISIBLE
                        val ft = fm!!.beginTransaction()
                        val achiev_all_fragment = Stu_Achiev_Part_K_Fragment()
                        ft.replace(R.id.frag_change, achiev_all_fragment)
                        ft.commit()
                    }else{
                        Log.i("ProKActivity", "result code not matched")
                        Toasty.error(activity.applicationContext, "불러오기 실패").show()
                    }
                }, { _ ->
                    Log.e("ERROR","데이터 통신 불가")
                    Toasty.error(activity.applicationContext, "불러오기 실패").show()
                })

            }
        }
    }

    data class Result_body(val allGrade:String? = null,val avgGrade: String? = null, val detail:ArrayList<ArrayList<String>>? = null)
    data class Achiev(val result_code: Int = 0,
                      val result_body: Result_body? = null) {
        //User Deserializer
        class Deserializer : ResponseDeserializable<Achiev> {
            override fun deserialize(content: String) = Gson().fromJson(content, Achiev::class.java)
        }
    }

    // MD5 복호화
    @Throws(Exception::class)
    fun Decrypt(text: String, key: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val keyBytes = ByteArray(16)
        val b = key.toByteArray(charset("UTF-8"))
        var len = b.size
        if (len > keyBytes.size) len = keyBytes.size
        System.arraycopy(b, 0, keyBytes, 0, len)
        val keySpec = SecretKeySpec(keyBytes, "AES")
        val ivSpec = IvParameterSpec(keyBytes)
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
        val results = cipher.doFinal(Base64.decode(text, 0))
        return String(results)
    }

}