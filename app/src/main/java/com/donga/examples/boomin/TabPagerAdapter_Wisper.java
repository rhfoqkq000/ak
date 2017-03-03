package com.donga.examples.boomin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.donga.examples.boomin.fragment.Wisper_noticeFragment;
import com.donga.examples.boomin.fragment.Wisper_okFragment;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class TabPagerAdapter_Wisper extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter_Wisper(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                Wisper_noticeFragment noticeFragment = new Wisper_noticeFragment();
                return noticeFragment;
            case 1:
                Wisper_okFragment okFragment = new Wisper_okFragment();
                return okFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}