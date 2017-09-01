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
import android.view.Menu
import android.view.MenuItem
import butterknife.ButterKnife
import com.donga.examples.boomin.R
import com.donga.examples.boomin.Singleton.ProSingleton
import com.donga.examples.boomin.listviewAdapter.ProSubListViewAdapter
import kotlinx.android.synthetic.main.activity_prosub.*
import kotlinx.android.synthetic.main.content_prosub.*

/**
 * Created by pmkjkr on 2017. 7. 18..
 */
class ProSubKActivity: AppCompatActivity(),  NavigationView.OnNavigationItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prosub)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar_prosub)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout_prosub, toolbar_prosub, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout_prosub.setDrawerListener(toggle)
        toggle.syncState()

        val i1 = intent

        nav_view_prosub.setNavigationItemSelectedListener(this)
        pro_sub.text = i1.getStringExtra("name")

        val adapter = ProSubListViewAdapter()
        list_prosub.adapter = adapter

        val professorJSONArray = ProSingleton.getInstance().professorJSONArray

        for (i in 0..professorJSONArray.length()-1) {
            val jsonObj = professorJSONArray.getJSONObject(i)
            if (jsonObj.getString("email") == ".") {
                if (jsonObj.getString("tel") == ".") {
                    // 둘 다 없을 때
                    adapter.addItem(jsonObj.getString("name"), jsonObj.getString("major"),
                            resources.getDrawable(R.drawable.call_empty), resources.getDrawable(R.drawable.mail_empty),
                            jsonObj.getString("tel"), jsonObj.getString("email"))
                } else {
                    // 이메일은 없고 전화번호가 있을 때
                    adapter.addItem(jsonObj.getString("name"), jsonObj.getString("major"),
                            resources.getDrawable(R.drawable.call_fill), resources.getDrawable(R.drawable.mail_empty),
                            jsonObj.getString("tel"), jsonObj.getString("email"))
                }
            } else if (jsonObj.getString("tel") == ".") {
                if (jsonObj.getString("email") == ".") {
                    // 둘 다 없을 때
                    adapter.addItem(jsonObj.getString("name"), jsonObj.getString("major"),
                            resources.getDrawable(R.drawable.call_empty), resources.getDrawable(R.drawable.mail_empty),
                            jsonObj.getString("tel"), jsonObj.getString("email"))
                } else {
                    // 전화번호는 없고 이메일이 있을 때
                    adapter.addItem(jsonObj.getString("name"), jsonObj.getString("major"),
                            resources.getDrawable(R.drawable.call_empty), resources.getDrawable(R.drawable.mail_fill),
                            jsonObj.getString("tel"), jsonObj.getString("email"))
                }
            } else {
                // 둘 다 있을 때
                adapter.addItem(jsonObj.getString("name"), jsonObj.getString("major"),
                        resources.getDrawable(R.drawable.call_fill), resources.getDrawable(R.drawable.mail_fill),
                        jsonObj.getString("tel"), jsonObj.getString("email"))
            }
        }
    }


    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout_prosub) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            val intent = Intent(baseContext, ProKActivity::class.java)
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


        val drawer = findViewById(R.id.drawer_layout_prosub) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

}