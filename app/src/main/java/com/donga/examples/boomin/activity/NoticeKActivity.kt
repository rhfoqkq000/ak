package com.donga.examples.boomin.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import butterknife.ButterKnife
import com.donga.examples.boomin.R
import com.donga.examples.boomin.listviewAdapter.NoticeListViewAdapter
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import kotlinx.android.synthetic.main.activity_notice.*
import kotlinx.android.synthetic.main.content_notice.*

/**
 * Created by pmkjkr on 2017. 7. 18..
 */
class NoticeKActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    internal var adapter: NoticeListViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar_notice)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout_notice, toolbar_notice, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout_notice.addDrawerListener(toggle)
        toggle.syncState()

        nav_view_notice.setNavigationItemSelectedListener(this)

        FuelManager.instance.basePath = "http://dongaboomin.xyz:8000/"
        "api/notice".httpGet().responseJson { _, _, result ->
            result.fold({ d ->
                val result_object = d.obj()
                val result_code = result_object.getString("result_code")
                if (result_code == "200"){
                    val result_body =  result_object.getJSONArray("result_body")
                    adapter = NoticeListViewAdapter()
                    list_notice.adapter = adapter
                    for (i in 0..result_body.length()-1){
                        adapter!!.addItem(result_body.getJSONObject(i).getString("title"), result_body.getJSONObject(i).getString("updated_at"))
                    }
                }else{
                    Log.i("ProKActivity", "result code not matched")
                }
            }, { _ ->
                Log.e("ERROR","데이터 통신 불가")
            })
        }

    }


    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout_notice) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            val intent = Intent(baseContext, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_res) {
            val intent = Intent(applicationContext, ResKActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_room) {
            val intent = Intent(applicationContext, RoomActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_pro) {
            val intent = Intent(applicationContext, ProKActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_stu) {
            val intent = Intent(applicationContext, StudentActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_empty) {
            val intent = Intent(applicationContext, EmptyActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_wisper) {
            val intent = Intent(applicationContext, WisperActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_site) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.donga.ac.kr"))
            startActivity(intent)
        } else if (id == R.id.nav_noti) {
            val intent = Intent(applicationContext, NoticeKActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_change) {
            val intent = Intent(applicationContext, ChangeActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_help) {
            val intent = Intent(applicationContext, HelpActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_logout) {
            val sharedPreferences = getSharedPreferences(resources.getString(R.string.SFLAG), Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

        } else if (id == R.id.nav_manage) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://booadmin.xyz"))
            startActivity(intent)
        }


        val drawer = findViewById(R.id.drawer_layout_notice) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}