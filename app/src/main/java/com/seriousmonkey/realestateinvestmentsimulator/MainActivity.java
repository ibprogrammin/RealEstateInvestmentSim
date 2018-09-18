package com.seriousmonkey.realestateinvestmentsimulator;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.seriousmonkey.realestateinvestmentsimulator.adapters.PagerAdapter;
import com.seriousmonkey.realestateinvestmentsimulator.assets.InvestmentSimulationCalculator;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ArrayAdapter<String> mDrawerAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    private TabLayout mMainTabLayout;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    private LinearLayout mTabSection;
    private LinearLayout mFormSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });

        final Toolbar mMainToolbar = (Toolbar) findViewById(R.id.MainToolbar);

        setupDrawer();
        addDrawerItems();

        setSupportActionBar(mMainToolbar);

        if ( getSupportActionBar() != null ) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        mMainTabLayout = (TabLayout) findViewById(R.id.mainTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), 4);

        mViewPager.setAdapter(mPagerAdapter);

        mMainTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition() == 3) {
                    hideSoftKeyboard();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int state) {
                //Called when the scroll state changes.
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //This method will be invoked when the current page is scrolled,
                //either as part of a programmatically initiated smooth scroll
                //or a user initiated touch scroll.
            }

            @Override
            public void onPageSelected(int position) {
                mMainTabLayout.getTabAt(position).select();
                if(position == 3) {
                    hideSoftKeyboard();
                }
            }
        });

        mTabSection = (LinearLayout) findViewById(R.id.tabSection);
        mFormSection = (LinearLayout) findViewById(R.id.formSection);

    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void addDrawerItems() {
        String[] osArray = { "Offer Calculator", "Maintenance Fund", "Projected Cashflow", "Projected ROI", "Potential Earnings", "Earnings Graph" };
        mDrawerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mDrawerAdapter);
    }

    public void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);

        if (imm != null) {
            if (getCurrentFocus() == null)
                return;
            if (getCurrentFocus().getWindowToken() == null)
                return;

            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
    }

    public void processCalculations() {
        InvestmentSimulationCalculator isc = new InvestmentSimulationCalculator(
                mPagerAdapter.mPropertyFragment.getPurchasePrice(),
                mPagerAdapter.mPropertyFragment.getInsurance(),
                mPagerAdapter.mPropertyFragment.getUtilities(),
                mPagerAdapter.mPropertyFragment.getTaxes(),
                mPagerAdapter.mLenderFragment.getMortgageRate(),
                mPagerAdapter.mLenderFragment.getDownpayment(),
                mPagerAdapter.mLenderFragment.getAmortization());

        isc.setRooms(mPagerAdapter.mRoomFragment.getRooms());

        mPagerAdapter.mDetailsFragment.setPurchasePrice(mPagerAdapter.mPropertyFragment.getPurchasePrice());
        mPagerAdapter.mDetailsFragment.setDownPayment(isc.getDownPayment());
        mPagerAdapter.mDetailsFragment.setIncome(isc.getIncomeFromRooms());
        mPagerAdapter.mDetailsFragment.setExpenses(isc.getExpenses());
        mPagerAdapter.mDetailsFragment.setMortgagePayment(isc.getMortgagePayment());
        mPagerAdapter.mDetailsFragment.setCashflow(isc.getCashflow());
        mPagerAdapter.mDetailsFragment.setROI(isc.getROI());
        mPagerAdapter.mDetailsFragment.setCAPRate(isc.getCAPRate());

        mPagerAdapter.mDetailsFragment.RefreshView();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
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
        String title = item.getTitle().toString();
        //noinspection SimplifiableIfStatement
        switch (title) {
            case "Offer Calculator":
                mTabSection.setVisibility(View.GONE);
                mFormSection.setVisibility(View.GONE);
        }

        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
