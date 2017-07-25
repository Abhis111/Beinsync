package com.binaryic.beinsync.common;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

/**
 * Created by Binary_Apple on 7/20/17.
 */

public class Utils {

    public static void addFragmentBackHome(Activity context, Fragment fragment, int containerId) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            while (fragmentManager.popBackStackImmediate()) ;
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerId, fragment).addToBackStack(fragment.getClass().getName()).commit();
    }

    public static void addFragmentBack(int containerId, Fragment fragment, Activity context) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(containerId, fragment).addToBackStack(fragment.getClass().getName()).commit();
    }

    public static void addFragment(Activity context, Fragment fragment,int containerId ) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();


        transaction.add(containerId, fragment).commit();
    }

    public static void showSnackBar(Activity context, View view, String text) {
        Log.e("InternetConnection", "showSnackBar");

        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .setActionTextColor(context.getResources().getColor(android.R.color.holo_red_light))
                .show();
    }


}
