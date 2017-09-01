package com.donga.examples.boomin.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import com.donga.examples.boomin.AppendLog
import com.donga.examples.boomin.R
import com.donga.examples.boomin.TabPagerAdapter_Res

import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_res.*
import kotlinx.android.synthetic.main.content_res.*

/**
 * Created by rhfoq on 2017-02-08.
 */
class ResKActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    internal var log = AppendLog()

//    private var tabLayout: TabLayout? = null
//    private var viewPager: ViewPager? = null
//
//    @BindView(R.id.toolbar_res)
//    internal var toolbar: Toolbar? = null
//    @BindView(R.id.drawer_layout_res)
//    internal var drawer: DrawerLayout? = null
//    @BindView(R.id.nav_view_res)
//    internal var navigationView: NavigationView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar_res)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout_res, toolbar_res, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
//        drawer!!.addDrawerListener(toggle)
        drawer_layout_res.setDrawerListener(toggle)
        toggle.syncState()

        nav_view_res.setNavigationItemSelectedListener(this)

        // Initializing the TabLayout
//        tabLayout = findViewById(R.id.tabLayout) as TabLayout
        tabLayout.addTab(tabLayout.newTab().setText("하단캠퍼스"))
        tabLayout.addTab(tabLayout.newTab().setText("부민캠퍼스"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        // Initializing ViewPager
//        viewPager = findViewById(R.id.pager) as ViewPager

        // Creating TabPagerAdapter_Stu adapter
        val pagerAdapter = TabPagerAdapter_Res(supportFragmentManager, tabLayout!!.tabCount)
        pager.adapter = pagerAdapter
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        // Set TabSelectedListener
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout_res) as DrawerLayout
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

        val drawer = findViewById(R.id.drawer_layout_res) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

}