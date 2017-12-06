package com.congp.timekeeping.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.congp.timekeeping.R;
import com.congp.timekeeping.adapter.EventAdapter;
import com.congp.timekeeping.adapter.ViewPagerAdapter;
import com.congp.timekeeping.fragment.CalenderFragment;
import com.rd.PageIndicatorView;

public class CustomCalActivity extends AppCompatActivity  {
    String month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_cal);
        ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//Bind the title indicator to the adapter
        PageIndicatorView pageIndicatorView = (PageIndicatorView)findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setViewPager(viewPager);
        setMonth();
    }

    private void setMonth() {
        month = CalenderFragment.getMoth();
    }


}
