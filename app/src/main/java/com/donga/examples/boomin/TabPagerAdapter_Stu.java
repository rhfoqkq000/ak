package com.donga.examples.boomin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.donga.examples.boomin.fragment.Stu_AchievKFragment;
import com.donga.examples.boomin.fragment.Stu_GrageFragment;
import com.donga.examples.boomin.fragment.Stu_ScheFragment;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class TabPagerAdapter_Stu extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter_Stu(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                return new Stu_ScheFragment();
            case 1:
                return new Stu_GrageFragment();
            case 2:
                return new Stu_AchievKFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}