package com.example.olivier.myapplication;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by olivier on 16/07/2015.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {


    protected Context mContext;

    public MyPagerAdapter(FragmentManager fm, Context context) {
        super (fm);
        mContext = context;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MainPage();
            case 1:
               return new RecordPage();
        }
        return null;
    }

    public int getCount() {
        return 2;
    }

}