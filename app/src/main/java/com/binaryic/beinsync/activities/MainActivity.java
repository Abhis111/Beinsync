package com.binaryic.beinsync.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.common.Utils;
import com.binaryic.beinsync.fragments.FragmentDrawer;
import com.binaryic.beinsync.fragments.FragmentHome;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public DrawerLayout drawer;
    public FrameLayout fl_Main;
    public FrameLayout fl_Main_Drawer;
    public TextView toolbarTitle;
    RelativeLayout btnCart;

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

    private void init() {

        fl_Main = (FrameLayout) findViewById(R.id.fl_Main);
        fl_Main_Drawer = (FrameLayout) findViewById(R.id.fl_Main_Drawer);

        toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);
        btnCart = (RelativeLayout) findViewById(R.id.btnCart);

        btnCart.setOnClickListener(this);

        toolbarTitle.setVisibility(View.GONE);
        addHomeFragment();
        addDrawerFragment();

    }

    public void addHomeFragment() {

        Utils.addFragment(MainActivity.this, new FragmentHome(), R.id.fl_Main);
    }

    public void addDrawerFragment() {

        Utils.addFragment(MainActivity.this, new FragmentDrawer(), R.id.fl_Main_Drawer);
    }

    @Override
    public void onClick(View v) {

    }
}
