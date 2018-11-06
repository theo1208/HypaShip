package com.hypa.hypaship.activitites;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.hypa.hypaship.Constants;
import com.hypa.hypaship.fragments.AboutFragment;
import com.hypa.hypaship.fragments.BaseFragment;
import com.hypa.hypaship.fragments.JobsFragment;
import com.hypa.hypaship.fragments.LoadVehicleFragment;
import com.hypa.hypaship.fragments.MemosFragment;
import com.hypa.hypaship.fragments.NavigationFragment;
import com.hypa.hypaship.R;
import com.hypa.hypaship.fragments.ReturnToDepotFragment;
import com.hypa.hypaship.fragments.SetupFragment;
import com.hypa.hypaship.fragments.SummaryFragment;
import com.hypa.hypaship.interfaces.FragmentListener;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener,FragmentListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    NavigationFragment mNavigationDrawerFragment;

    SharedPreferences sharedPreferences;

    BaseFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mNavigationDrawerFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.nav_view);


        View setup=findViewById(R.id.menu_home);

        onMenuItemClicked(setup);

        onDrawerClosed(null);

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
        if(currentFragment!=null)
        {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.animator.fade_in, R.animator.fade_out)
                    .replace(R.id.content_frame, currentFragment)
                    .commit();
            currentFragment=null;
        }

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }


    public void onMenuItemClicked(View view) {


        switch (view.getId()) {

            case R.id.menu_logout:
                displayLogoutAlert();
                return;
            case R.id.menu_home:
               currentFragment =new SetupFragment();
                break;
            case R.id.menu_jobs:
                currentFragment =new JobsFragment();
                break;
            case R.id.menu_memos:
                currentFragment =new MemosFragment();
                break;
            case R.id.menu_load_vehicle:
                currentFragment =new LoadVehicleFragment();
                break;
            case R.id.menu_return_depot:
                currentFragment =new ReturnToDepotFragment();
                break;
            case R.id.menu_summary:
                currentFragment =new SummaryFragment();
                break;
            case R.id.menu_about:
                currentFragment =new AboutFragment();
                break;

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        mNavigationDrawerFragment.setCurrentItem(view);
    }

    @Override
    public void setTitle(String title) {

        ActionBar actionbar = getSupportActionBar();

        if (actionbar != null)
            actionbar.setTitle(title);
    }

    void displayLogoutAlert() {

        String message=getResources().getString(R.string.logout_confirm);
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        logout();

                        goToLoginActivity();

                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void goToLoginActivity() {

        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();

    }

    private void logout() {

        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.putBoolean(Constants.LOGGED_IN,false);
        editor.putString(Constants.LOGGED_USERNAME,null);
        editor.putString(Constants.LOGGED_DEPO,null);

        editor.commit();
    }

}
