package com.seriousmonkey.realestateinvestmentsimulator.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.seriousmonkey.realestateinvestmentsimulator.fragments.DetailsFragment;
import com.seriousmonkey.realestateinvestmentsimulator.fragments.LenderFragment;
import com.seriousmonkey.realestateinvestmentsimulator.fragments.PropertyFragment;
import com.seriousmonkey.realestateinvestmentsimulator.fragments.RoomFragment;

/**
 * Created by Daniel on 2017-08-30.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public PropertyFragment mPropertyFragment;
    public LenderFragment mLenderFragment;
    public RoomFragment mRoomFragment;
    public DetailsFragment mDetailsFragment;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;

        mPropertyFragment = new PropertyFragment();
        mLenderFragment = new LenderFragment();
        mRoomFragment = new RoomFragment();
        mDetailsFragment = new DetailsFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mPropertyFragment;
            case 1:
                return mLenderFragment;
            case 2:
                return mRoomFragment;
            case 3:
                return mDetailsFragment;
            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    public PropertyFragment getPropertyFragment() {
        return mPropertyFragment;
    }

    public LenderFragment getLenderFragment() {
        return mLenderFragment;
    }

    public RoomFragment getRoomFragment() {
        return mRoomFragment;
    }

    public DetailsFragment getDetailsFragment() {
        return mDetailsFragment;
    }

}
