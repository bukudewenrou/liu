package com.zcitc.advertisement.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class FragmentStatePagerItemAdapter extends FragmentStatePagerAdapter {
    FragmentManager fm;
    private List<Fragment> fragments;

    public FragmentStatePagerItemAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
    }

    public void setDataSource(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return (fragments != null) ? fragments.size() : 0;
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
