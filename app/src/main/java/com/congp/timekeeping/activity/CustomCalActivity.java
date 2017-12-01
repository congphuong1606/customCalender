package com.congp.timekeeping.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.congp.timekeeping.R;
import com.congp.timekeeping.adapter.EventAdapter;
import com.congp.timekeeping.adapter.ViewPagerAdapter;
import com.rd.PageIndicatorView;

public class CustomCalActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_cal);
        ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

//Bind the title indicator to the adapter
        PageIndicatorView pageIndicatorView = (PageIndicatorView)findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setViewPager(viewPager);
    }


}
