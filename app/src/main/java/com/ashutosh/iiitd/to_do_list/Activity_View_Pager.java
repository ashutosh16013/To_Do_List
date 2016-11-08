package com.ashutosh.iiitd.to_do_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import helperClasses.ListItem;

public class Activity_View_Pager extends AppCompatActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static int NUM_PAGES;

    ArrayList<ListItem> list_of_todo;

    int position;

    /**
            * The pager widget, which handles animation and allows swiping horizontally to access previous
    * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__view__pager);
        //Initialise Toolbar for hierarchical navigation
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_for_pager);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Initialise toolbar end
        Bundle bundle_from_main = getIntent().getExtras();
        list_of_todo = (ArrayList<ListItem>)bundle_from_main.getSerializable("list");
        position = bundle_from_main.getInt("pos");
        NUM_PAGES = bundle_from_main.getInt("num");
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(position);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
                invalidateOptionsMenu();
            }
        });
        mPagerAdapter.notifyDataSetChanged();
    }



    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {

            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //This method is to be utilised to send the position for array list
            String details = list_of_todo.get(position).getDetails();
            String title = list_of_todo.get(position).getTitle();
            String date = list_of_todo.get(position).getDate();
            return View_Pager_Fragment.create(position,details,title,date);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
