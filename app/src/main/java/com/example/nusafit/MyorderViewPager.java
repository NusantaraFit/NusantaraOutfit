package com.example.nusafit;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.nusafit.order.OrderCancelFrag;
import com.example.nusafit.order.OrderProcessFrag;
import com.example.nusafit.order.OrderReturnFrag;
import com.example.nusafit.order.OrderWayFrag;

public class MyorderViewPager
        extends FragmentPagerAdapter {

    public MyorderViewPager(
            @NonNull FragmentManager fm)
    {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment = null;
        if (position == 0)
            fragment = new OrderProcessFrag();
        else if (position == 1)
            fragment = new OrderWayFrag();
        else if (position == 2)
            fragment = new OrderReturnFrag();
        else if (position == 3)
            fragment = new OrderCancelFrag();

        return fragment;
    }

    @Override
    public int getCount()
    {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if (position == 0)
            title = "In the process";
        else if (position == 1)
            title = "On the way";
        else if (position == 2)
            title = "Return of goods";
        else if (position == 3)
            title = "Cancellation";
        return title;
    }
}
