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
                Manage_MemberFragment memberFragment= new Manage_MemberFragment();
                return memberFragment;
            case 1:
                Manage_LetterFragment letterFragment = new Manage_LetterFragment();
                return letterFragment;
            case 2:
                Manage_AttendFragment attendFragment = new Manage_AttendFragment();
                return attendFragment;
            case 3:
                Manage_SendFragment sendFragment = new Manage_SendFragment();
                return sendFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}