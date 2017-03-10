package com.rappi.test.redditapp.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rappi.test.redditapp.fragments.CommunityFragment;
import com.rappi.test.redditapp.fragments.FeedFragment;
import com.rappi.test.redditapp.fragments.InboxFragment;
import com.rappi.test.redditapp.fragments.UserFragment;

/**
 * Adapter to handle the actions when moving through "tabs" (i.e. Fragments).
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "Feed", "Community", "Inbox", "User" };
    private Context context;


    public MainFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) { //Depending on the selected fragment we create an instance of it.
        if (position==0)
            return FeedFragment.newInstance("","");
        else if (position==1)
            return CommunityFragment.newInstance("","");
        else if (position==2)
            return InboxFragment.newInstance("","");
        else
            return UserFragment.newInstance("","");

    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }




}