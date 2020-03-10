package com.my.bookmart.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.my.bookmart.R;
import com.my.bookmart.fragments.LibraryFragment;
import com.my.bookmart.fragments.MyAccountFragment;
import com.my.bookmart.fragments.OrdersHistoryFragment;
import com.my.bookmart.fragments.ShopFragment;
import com.my.bookmart.fragments.VideosFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private FrameLayout frameLayout;
    private int currentFragment = -1;
    private TextView tv_logo;
    private BottomNavigationView bottom_navigation;
    private static final int SHOP_FRAGMENT = 0;
    private static final int LIBRARY_FRAGMENT = 1;
    private static final int VIDEOS_FRAGMENT = 2;
    private static final int ORDERS_HISTORY_FRAGMENT = 3;
    private static final int MY_ACCOUNT_FRAGMENT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ////toolbar
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ////toolbar

        ////logo
        tv_logo = findViewById(R.id.toolbar_logo_container);
        ////logo

        ////navigation drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().clear();
        navigationView.inflateMenu(R.menu.activity_drawer_menu);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        ////navigation drawer

        ////frame layout
        frameLayout = findViewById(R.id.fragment_container);
        ////frame layout

        ////bottom navigation
        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        ////bottom navigation

        setFragment(new ShopFragment(), SHOP_FRAGMENT);
    }

    ////navigation items selection
    MenuItem item;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        actionBarDrawerToggle.syncState();
        drawerLayout.closeDrawer(GravityCompat.START);
        item = menuItem;
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                int id = item.getItemId();
                switch (id) {
                    case R.id.nav_home:
                        tv_logo.setVisibility(View.VISIBLE);
                        getSupportActionBar().setDisplayShowTitleEnabled(false);
                        if (currentFragment != SHOP_FRAGMENT) {
                            setFragment(new ShopFragment(), SHOP_FRAGMENT);
                        }
                        break;
                    case R.id.nav_orders:
                        gotoFragment("My Orders", new OrdersHistoryFragment(), ORDERS_HISTORY_FRAGMENT);
                        break;
                    case R.id.nav_profile:
                        gotoFragment("My Profile", new MyAccountFragment(), MY_ACCOUNT_FRAGMENT);
                        break;
                    case R.id.nav_log_out:
                        ///todo : logout system
                        break;
                }
                drawerLayout.removeDrawerListener(this);
            }
        });
        return true;
    }
    ////navigation items selection

    ////bottom navigation
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedfragment = null;
                    switch (item.getItemId()) {
                        case R.id.shop_bottom_nav:
                            selectedfragment = new ShopFragment();
                            setFragment(selectedfragment, SHOP_FRAGMENT);
                            break;
                        case R.id.library_bottom_nav:
                            selectedfragment = new LibraryFragment();
                            setFragment(selectedfragment, LIBRARY_FRAGMENT);
                            break;
                        case R.id.video_bottom_nav:
                            selectedfragment = new VideosFragment();
                            setFragment(selectedfragment, VIDEOS_FRAGMENT);
                            break;
                        case R.id.order_history_bottom_nav:
                            selectedfragment = new OrdersHistoryFragment();
                            setFragment(selectedfragment, ORDERS_HISTORY_FRAGMENT);
                            break;
                        case R.id.profile_bottom_nav:
                            selectedfragment = new MyAccountFragment();
                            setFragment(selectedfragment, MY_ACCOUNT_FRAGMENT);
                            break;
                    }
                    return true;
                }
            };
    ////bottom navigation

    ////function for setting title for each nav fragments
    private void gotoFragment(String title, Fragment fragment, int fragmentNo) {
        tv_logo.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        invalidateOptionsMenu();
        setFragment(fragment, fragmentNo);
    }
    ////function for setting title for each nav fragments

    ////function for setting fragments
    private void setFragment(Fragment fragment, int fragmentNo) {
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorBlue));
        currentFragment = fragmentNo;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
    ////function for setting fragments

    ////backpressed handled
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (currentFragment != SHOP_FRAGMENT) {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                tv_logo.setVisibility(View.VISIBLE);
                navigationView.getMenu().getItem(0).setChecked(true);
                setFragment(new ShopFragment(), SHOP_FRAGMENT);
            } else {
                finish();
            }
        }
        return false;
    }
    ////backpressed handled
}
