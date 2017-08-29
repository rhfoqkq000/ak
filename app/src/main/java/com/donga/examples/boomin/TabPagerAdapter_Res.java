package com.donga.examples.boomin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.donga.examples.boomin.fragment.Res_BuminFragment;
import com.donga.examples.boomin.fragment.Res_HadanFragment;
import com.donga.examples.boomin.fragment.Stu_AchievFragment;
import com.donga.examples.boomin.fragment.Stu_GrageFragment;
import com.donga.examples.boomin.fragment.Stu_ScheFragment;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class TabPagerAdapter_Res extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter_Res(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                Res_HadanFragment hadanFragment = new Res_HadanFragment();
                return hadanFragment;
            case 1:
                Res_BuminFragment buminFragment = new Res_BuminFragment();
                return buminFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}