package com.binaryic.beinsync;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

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

    public static void addFragment(int containerId, Fragment fragment, Activity context) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();


        transaction.add(containerId, fragment).commit();
    }

}
