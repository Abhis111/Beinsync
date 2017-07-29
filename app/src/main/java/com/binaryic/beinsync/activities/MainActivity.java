package com.binaryic.beinsync.activities;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.common.ApiCallBack;
import com.binaryic.beinsync.common.Constants;
import com.binaryic.beinsync.common.Utils;
import com.binaryic.beinsync.controllers.DashboardController;
import com.binaryic.beinsync.fragments.FilterFragment;
import com.binaryic.beinsync.fragments.FragmentDrawer;
import com.binaryic.beinsync.fragments.FragmentHome;
import com.binaryic.beinsync.fragments.MainFragment;
import com.binaryic.beinsync.fragments.SettingNewFragment;

import static com.binaryic.beinsync.common.Constants.COLUMN_BACKGROUND_COLOR;
import static com.binaryic.beinsync.common.Constants.COLUMN_FONT_NAME;
import static com.binaryic.beinsync.common.Constants.COLUMN_LINE_SPACING;
import static com.binaryic.beinsync.common.Constants.COLUMN_TEXT_ALIGNMENT;
import static com.binaryic.beinsync.common.Constants.COLUMN_TEXT_COLOR;
import static com.binaryic.beinsync.common.Constants.COLUMN_TEXT_MODE;
import static com.binaryic.beinsync.common.Constants.COLUMN_TEXT_SIZE;
import static com.binaryic.beinsync.common.Constants.COLUMN_TEXT_STYLE;
import static com.binaryic.beinsync.common.Constants.CONTENT_SETTING;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SearchView.OnQueryTextListener {
    public static DrawerLayout drawer;
    public static FrameLayout fl_Main;
    public FrameLayout fl_Main_Drawer;
    public TextView toolbarTitle;
    RelativeLayout btnCart;
    RelativeLayout rl_Setting;
    RelativeLayout rl_Filter;
    public static LinearLayout ll_textFormatOptions;
    private ImageView iv_Add;
    FragmentHome fragmentHome;
    MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.createDialog(this);
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

        ll_textFormatOptions.setVisibility(View.VISIBLE);
        rl_Filter.setOnClickListener(this);
        rl_Setting.setOnClickListener(this);
        toolbarTitle.setVisibility(View.GONE);
        defaultSetting();
        addTabsFragment();
        //addHomeFragment();
        addDrawerFragment();

    }



    private void defaultSetting() {
        Cursor cursor = getContentResolver().query(CONTENT_SETTING, null, null, null, null);
        if (cursor.getCount() <= 0) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TEXT_SIZE, "16");
            values.put(COLUMN_LINE_SPACING, "1.5");
            values.put(COLUMN_TEXT_STYLE, "normal");
            values.put(COLUMN_TEXT_MODE, "day");
            values.put(COLUMN_TEXT_ALIGNMENT, "justify");
            values.put(COLUMN_FONT_NAME, "Roboto");
            values.put(COLUMN_TEXT_COLOR, "" + "#2D292B");
            values.put(COLUMN_BACKGROUND_COLOR, "" + "#EEEAF0");
            getContentResolver().insert(CONTENT_SETTING, values);
        }
        cursor.close();
    }

    public void addHomeFragment() {
        fragmentHome = new FragmentHome();
        Bundle bundle = new Bundle();
        bundle.putString("link", Constants.URL_DASHBOARD);
        fragmentHome.setArguments(bundle);
        Utils.addFragment(MainActivity.this, fragmentHome, R.id.fl_Main);
    }

    public void addTabsFragment() {
        mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("link", Constants.URL_DASHBOARD);
        mainFragment.setArguments(bundle);
        Utils.addFragment(MainActivity.this, mainFragment, R.id.fl_Main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.rate_us) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + MainActivity.this.getPackageName())));
        } else if (item.getItemId() == R.id.share_app) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.share_msg));
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getResources().getString(R.string.share_app)));
        } else if (item.getItemId() == R.id.about_us) {
            aboutDialog();
        } else if (item.getItemId() == R.id.sync) {
            Utils.downloading_Dialog.show();
           addTabsFragment();
        }
        return super.onOptionsItemSelected(item);
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
                //
                FilterFragment filterFragment = new FilterFragment();
                filterFragment.setCloseListner(new FilterFragment.CloseListner() {
                    @Override
                    public void onClose(String tags) {
                        if (mainFragment != null)
                            mainFragment.filter(tags);
                        onBackPressed();
                    }
                });
                Utils.addFragmentBack(R.id.fl_Main, filterFragment, this);
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

    @Override
    public void onBackPressed() {

        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fl_Main);
        ll_textFormatOptions.setVisibility(View.VISIBLE);
        if (f instanceof MainFragment) {
            alertForExit();
        } else {

            super.onBackPressed();
        }

    }

    private void alertForExit() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to Exit ?")
                .setCancelable(false)
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void aboutDialog() {
        final Dialog msgDialog = new Dialog(MainActivity.this);
        msgDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        msgDialog.setContentView(R.layout.dialog_about);
        WindowManager.LayoutParams wmlp = msgDialog.getWindow().getAttributes();
        wmlp.gravity = Gravity.CENTER | Gravity.CENTER;
        wmlp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        msgDialog.setCanceledOnTouchOutside(true);
        msgDialog.show();
    }
}
