package com.binaryic.beinsync.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.Utils;

public class StoryViewActivity extends AppCompatActivity {
private FrameLayout fl_Main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_view);
        init();
        getExtra();

    }

    private void getExtra() {
        if(getIntent().hasExtra("title")){
            Fragment fragment =new  StoryViewtFragment();
            Bundle bundle=new Bundle();
            bundle.putString("title",getIntent().getStringExtra("title"));
            bundle.putString("image",getIntent().getStringExtra("image"));
            bundle.putString("content",getIntent().getStringExtra("content"));
            fragment.setArguments(bundle);
            Utils.addFragment(R.id.fl_Main, fragment, this);
        }

    }



    private void init() {
        fl_Main= (FrameLayout) findViewById(R.id.fl_Main);
    }
}
