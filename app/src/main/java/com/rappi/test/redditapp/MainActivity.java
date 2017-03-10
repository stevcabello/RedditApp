package com.rappi.test.redditapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.rappi.test.redditapp.adapters.MainFragmentPagerAdapter;
import com.rappi.test.redditapp.fragments.CommunityFragment;
import com.rappi.test.redditapp.fragments.DescriptionFragment;
import com.rappi.test.redditapp.fragments.FeedFragment;
import com.rappi.test.redditapp.fragments.InboxFragment;
import com.rappi.test.redditapp.fragments.UserFragment;
import com.rappi.test.redditapp.utils.Globals;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    TabLayout tabLayout;
    Fragment visibleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Globals.mainActivity = this;

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setLogo(R.drawable.reddit_text_logo);
        getSupportActionBar().setTitle(null);

        visibleFragment = new FeedFragment(); //the Default first visible Fragment

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MainFragmentPagerAdapter pagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this);
        viewPager.setAdapter(pagerAdapter);


        // Give the TabLayout the ViewPager
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //Change the toolbar according to the Fragment

                if (position == 0) {
                    getSupportActionBar().setTitle(null);
                    getSupportActionBar().setLogo(R.drawable.reddit_text_logo);
                    tabLayout.setVisibility(View.VISIBLE);
                    visibleFragment = new FeedFragment();
                } else if (position == 1) {
                    getSupportActionBar().setTitle("Community");
                    tabLayout.setVisibility(View.VISIBLE);
                    visibleFragment = new CommunityFragment();
                } else if (position == 2) {
                    getSupportActionBar().setTitle("Inbox");
                    tabLayout.setVisibility(View.VISIBLE);
                    visibleFragment = new InboxFragment();
                } else {
                    getSupportActionBar().setTitle("User");
                    tabLayout.setVisibility(View.VISIBLE);
                    visibleFragment = new UserFragment();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //Set the icons of the main tabs

        TextView tab_feed= (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab,null);
        tab_feed.setBackgroundResource(R.drawable.btn_feed_state);
        tabLayout.getTabAt(0).setCustomView(tab_feed);

        TextView tab_community = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab,null);
        tab_community.setBackgroundResource(R.drawable.btn_community_state);
        tabLayout.getTabAt(1).setCustomView(tab_community);

        TextView tab_inbox = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab,null);
        tab_inbox.setBackgroundResource(R.drawable.btn_inbox_state);
        tabLayout.getTabAt(2).setCustomView(tab_inbox);

        TextView tab_user = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab,null);
        tab_user.setBackgroundResource(R.drawable.btn_user_state);
        tabLayout.getTabAt(3).setCustomView(tab_user);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if (id == android.R.id.home) { //To handle the behaviour of the back button depending on the visibleFragment
            onBackPressed(); //has the same behaviour as the back button has
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Override of the onBackPressed() method to manage the behaviour of the back button depending on
     * which fragment the user is.
     */
    @Override
    public void onBackPressed(){

        Fragment whichFragment = getVisibleFragment();
        String shareVisible = whichFragment.getClass().toString();

        //if in descriptionFragment just go back to feedFragment
        if (shareVisible.equals(DescriptionFragment.class.toString()) )  {

            FragmentManager fm = getSupportFragmentManager();
            Fragment f = fm.findFragmentById(R.id.fly_feed_fragment);
            fm.popBackStack();

            tabLayout.setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle(null);
            getSupportActionBar().setLogo(R.drawable.reddit_text_logo);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false); //hide the back button

            setVisibleFragment(new FeedFragment());
        } else { //any other fragment
            moveTaskToBack(true);
        }

    }

    /**
     * Get the visible fragemnt
     * @return the fragment where the user is
     */
    public Fragment getVisibleFragment() {
        return visibleFragment;
    }

    /**
     * Set the visible fragment
     * @param fragment where the user is
     */
    public void setVisibleFragment(Fragment fragment) {
        this.visibleFragment = fragment;
    }





}
