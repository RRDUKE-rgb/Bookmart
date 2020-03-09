package com.my.bookmart.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.my.bookmart.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawer_layout);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        actionBarDrawerToggle.syncState();
        drawerLayout.closeDrawer(GravityCompat.START);
        item = menuItem;
        if (currentUser != null) {
            drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                    int id = item.getItemId();
                    switch (id) {
                        case R.id.nav_home:
                            invalidateOptionsMenu();
                            logoContainer.setVisibility(View.VISIBLE);
                            if (currentFragment != HOME_FRAGMENT) {
                                setFragment(new MarketHomeFragment(), HOME_FRAGMENT);
                            }
                            break;
                        case R.id.nav_my_cart:
                            gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.nav_my_orders:
                            gotoFragment("My Orders", new MyOrdersFragment(), ORDER_FRAGMENT);
                            drawerLayout.closeDrawer(GravityCompat.START);

                            break;
//                        case R.id.nav_my_rewards:
//                            gotoFragment("My Rewards", new MyRewardsFragment(), REWARD_FRAGMENT);
//
//                            break;
                        case R.id.nav_my_wishlist:
                            gotoFragment("My Wishlist", new MyWishlistFragment(), WISHLIST_FRAGMENT);
                            drawerLayout.closeDrawer(GravityCompat.START);

                            break;

                        case R.id.nav_my_account:
                            gotoFragment("My Profile", new MyAccountFragment(), PROFILE_FRAGMENT);
                            drawerLayout.closeDrawer(GravityCompat.START);

                            break;
                        case R.id.nav_help:
                            gotoFragment("Request a book / Book", new HelpFragment(), HELP_FRAGMENT);
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.nav_sign_out:
                            FirebaseAuth.getInstance().signOut();
                            DBquaries.clearData();
                            Intent regesterIntent = new Intent(MainActivity.this, RegisterActivity.class);
                            startActivity(regesterIntent);
                            drawerLayout.closeDrawer(GravityCompat.START);
                            finish();
                            break;
                    }
                    drawerLayout.removeDrawerListener(this);
                }
            });
            return true;
        } else {
            signInDialog.show();
            return false;
        }

    }
}
