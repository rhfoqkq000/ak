package com.donga.examples.boomin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.donga.examples.boomin.fragment.Manage_AttendFragment;
import com.donga.examples.boomin.fragment.Manage_LetterFragment;
import com.donga.examples.boomin.fragment.Manage_MemberFragment;
import com.donga.examples.boomin.fragment.Manage_SendFragment;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class TabPagerAdapter_Manage extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter_Manage(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                return new Manage_MemberFragment();
            case 1:
                return new Manage_LetterFragment();
            case 2:
                return new Manage_AttendFragment();
            case 3:
                return new Manage_SendFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}