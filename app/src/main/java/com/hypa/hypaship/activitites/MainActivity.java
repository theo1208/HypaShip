package com.hypa.hypaship.activitites;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.hypa.hypaship.fragments.NavigationFragment;
import com.hypa.hypaship.R;
import com.hypa.hypaship.interfaces.FragmentListener;


public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener,FragmentListener {

    private DrawerLayout mDrawerLayout;

    public NavigationFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        mNavigationDrawerFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.nav_view);

        mNavigationDrawerFragment.setCurrentItem(findViewById(R.id.menu_home));

        setToolbarListener();

    }

    private void setToolbarListener() {

        ActionBar actionbar = getSupportActionBar();

        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.hamburger);

        }
        mDrawerLayout.addDrawerListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START))
                {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }
                else
                {
                mDrawerLayout.openDrawer(GravityCompat.START);
                }

                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }


    public void onMenuItemClicked(View view) {

        mDrawerLayout.closeDrawer(GravityCompat.START);

        mNavigationDrawerFragment.setCurrentItem(view);

        switch (view.getId()) {

           /* case R.id.menu_home:
                currentFragment = new HomeFragment();
                fragment_shown=SHOWING_HOME_FRAGMENT;
                break;*/
        }

    }

    @Override
    public void setTitle(String title) {

        ActionBar actionbar = getSupportActionBar();

        if (actionbar != null)
            actionbar.setTitle(title);
    }
}
