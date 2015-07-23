package com.example.olivier.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.example.olivier.myapplication.Menu.BugReportFragment;
import com.example.olivier.myapplication.Menu.CreditFragment;
import com.example.olivier.myapplication.Menu.FAQFragment;
import com.example.olivier.myapplication.Menu.LicensesFragment;

@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity{

    @SuppressWarnings("unused")
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    @SuppressWarnings("all")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
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
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_licenses:
                openLicenses();
                return true;
            case R.id.action_credits:
                openCredits();
                return true;
            case R.id.action_bug_report:
                openBugReport();
                return true;
            case R.id.action_faq:
                openFAQ();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openLicenses(){
        LicensesFragment licensesFragment = new LicensesFragment();
        licensesFragment.show(getSupportFragmentManager().beginTransaction(), "dialog_licenses");
    }

    public void openCredits(){
        CreditFragment creditFragment = new CreditFragment();
        creditFragment.show(getSupportFragmentManager().beginTransaction(), "dialog_credits");
    }

    public void openBugReport(){
        BugReportFragment bugReportFragment = new BugReportFragment();
        bugReportFragment.show(getSupportFragmentManager().beginTransaction(), "dialog_bug_report");
    }

    public void openFAQ(){
        FAQFragment faqFragment = new FAQFragment();
        faqFragment.show(getSupportFragmentManager().beginTransaction(), "dialog_faq");
    }

    public class MyAdapter extends FragmentPagerAdapter {
        private String[] titles = { getString(R.string.tab_title_record),
                getString(R.string.tab_title_saved_recordings) };

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:{
                    return RecordFragment.newInstance(position);
                }
                case 1:{
                    return FileViewerFragment.newInstance(position);
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    public MainActivity() {
    }
}
