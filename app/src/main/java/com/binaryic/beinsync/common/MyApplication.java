package com.binaryic.beinsync.common;

import android.app.Application;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.Volley;
import com.binaryic.beinsync.R;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by HP on 18-Jul-17.
 */

public class MyApplication extends Application {
    private static MyApplication mInstance;
    private RequestQueue mRequestQueue;
    public static String ApiUrl="http://shoporganikos.binaryicdirect.com/KYA/";
    public static final String TAG = MyApplication.class.getSimpleName();
    public static SharedPreferences setting;
    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        setting = getSharedPreferences("kya", MODE_PRIVATE);
        sAnalytics = GoogleAnalytics.getInstance(this);
    }
    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
    synchronized public Tracker getDefaultTracker() {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
        }

        return sTracker;
    }
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        RetryPolicy policy = new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(policy);
        getRequestQueue().add(req);
    }
}
