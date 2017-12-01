package com.congp.timekeeping.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.congp.timekeeping.R;
import com.congp.timekeeping.fragment.CalenderFragment;
import com.congp.timekeeping.fragment.SalaryFragment;

/**
 * Created by congp on 11/24/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return CalenderFragment.newInstance();
        }else {
            return SalaryFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
