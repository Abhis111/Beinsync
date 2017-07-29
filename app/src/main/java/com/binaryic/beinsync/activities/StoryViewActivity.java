package com.binaryic.beinsync.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.common.Utils;
import com.binaryic.beinsync.fragments.StoryViewtFragment;

public class StoryViewActivity extends AppCompatActivity {
    private FrameLayout fl_Main;
    private String category = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_view);
        init();

    }

    private void getExtra() {
        if (getIntent().hasExtra("title")) {
            Fragment fragment = new StoryViewtFragment();
            Bundle bundle = new Bundle();
            bundle.putString("id", getIntent().getStringExtra("id"));
            category = getIntent().getStringExtra("category");
            bundle.putString("category", getIntent().getStringExtra("category"));
            Log.e("StoryViewActivity","category=="+category);

            bundle.putString("title", getIntent().getStringExtra("title"));
            bundle.putString("image", getIntent().getStringExtra("image"));
            bundle.putString("content", getIntent().getStringExtra("content"));
            bundle.putString("url", getIntent().getStringExtra("url"));
            fragment.setArguments(bundle);
            Utils.addFragment(this, fragment, R.id.fl_Main);
        }

    }

    private void init() {
        fl_Main = (FrameLayout) findViewById(R.id.fl_Main);
        getExtra();
        setToolBar();
    }


    private void setToolBar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
      //  setActionBarTitle(category.toString());
    }

    void setActionBarTitle(String title) {
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
