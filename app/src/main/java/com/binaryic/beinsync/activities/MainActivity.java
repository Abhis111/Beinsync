package com.binaryic.beinsync.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.common.Constants;
import com.binaryic.beinsync.common.Utils;
import com.binaryic.beinsync.fragments.FilterFragment;
import com.binaryic.beinsync.fragments.FragmentDrawer;
import com.binaryic.beinsync.fragments.FragmentHome;
import com.binaryic.beinsync.fragments.SettingNewFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SearchView.OnQueryTextListener {
    public static DrawerLayout drawer;
    public FrameLayout fl_Main;
    public FrameLayout fl_Main_Drawer;
    public TextView toolbarTitle;
    RelativeLayout btnCart;
    RelativeLayout rl_Setting;
    RelativeLayout rl_Filter;
    public static LinearLayout ll_textFormatOptions;
    private ImageView iv_Add;
    FragmentHome fragmentHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSideMenu();
    }

    private void setSideMenu() {

        Toolbar toolbar = ((Toolbar) findViewById(R.id.toolbar));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (Build.VERSION.SDK_INT >= 14)
            drawer.setFitsSystemWindows(true);
        init();

    }

    private void openLink() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://binaryic.com/"));
        startActivity(browserIntent);
    }

    private void init() {

        ll_textFormatOptions = (LinearLayout) findViewById(R.id.ll_textFormatOptions);
        fl_Main = (FrameLayout) findViewById(R.id.fl_Main);
        fl_Main_Drawer = (FrameLayout) findViewById(R.id.fl_Main_Drawer);
        iv_Add = (ImageView) findViewById(R.id.iv_Add);
        iv_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink();
            }
        });
        toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);
        btnCart = (RelativeLayout) findViewById(R.id.btnCart);
        rl_Setting = (RelativeLayout) findViewById(R.id.rl_Setting);
        rl_Filter = (RelativeLayout) findViewById(R.id.rl_Filter);

//        btnCart.setOnClickListener(this);
        rl_Filter.setOnClickListener(this);
        rl_Setting.setOnClickListener(this);

        toolbarTitle.setVisibility(View.GONE);
        addHomeFragment();
        addDrawerFragment();

    }

    public void addHomeFragment() {
        fragmentHome = new FragmentHome();
        Bundle bundle = new Bundle();
        bundle.putString("link", Constants.URL_DASHBOARD);
        fragmentHome.setArguments(bundle);
        Utils.addFragment(MainActivity.this, fragmentHome, R.id.fl_Main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
       /* switch (menuItem.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_filter:
                Toast.makeText(this, "filter", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_setting:
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
                break;
        }*/
        return true;
    }

    public void addDrawerFragment() {
        Utils.addFragment(MainActivity.this, new FragmentDrawer(), R.id.fl_Main_Drawer);
    }

    @Override
    public void onClick(View v) {
        Fragment fragment;

        switch (v.getId()) {
            case R.id.rl_Setting:
                fragment = SettingNewFragment.newInstance();
                if (fragment != null) {
                    Utils.addFragmentBack(R.id.fl_Main, fragment, this);
                }
                break;
            case R.id.rl_Filter:

                fragment = FilterFragment.newInstance();
                if (fragment != null) {
                    Utils.addFragmentBack(R.id.fl_Main, fragment, this);
                }
                break;
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (fragmentHome != null) {
            fragmentHome.search(newText);
        }
        return false;
    }
}
