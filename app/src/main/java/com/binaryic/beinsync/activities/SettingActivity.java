package com.binaryic.beinsync.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.common.Utils;
import com.binaryic.beinsync.fragments.SettingNewFragment;


public class SettingActivity extends AppCompatActivity {

    private FrameLayout lay_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();

    }

    private void init() {
        lay_main = (FrameLayout) findViewById(R.id.fl_Main);
        Utils.addFragment(SettingActivity.this, SettingNewFragment.newInstance(), lay_main.getId());
        setToolBar();
    }

    private void setToolBar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_group_chat);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setActionBarTitle(R.string.app_name);
    }

    void setActionBarTitle(int title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
