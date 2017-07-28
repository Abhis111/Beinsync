package com.binaryic.beinsync.common;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.binaryic.beinsync.R;

/**
 * Created by Binary_Apple on 7/20/17.
 */

public class Utils {
    public static Dialog downloading_Dialog;

    public static void addFragmentBackHome(Activity context, Fragment fragment, int containerId) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            while (fragmentManager.popBackStackImmediate()) ;
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerId, fragment).addToBackStack(fragment.getClass().getName()).commit();
    }

    public static int getScreenWidth(Activity context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static void addFragmentBack(int containerId, Fragment fragment, Activity context) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(containerId, fragment).addToBackStack(fragment.getClass().getName()).commit();
    }

    public static void addFragment(Activity context, Fragment fragment, int containerId) {
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

    public static void createDialog(Activity context) {
        // Create custom dialog object
        downloading_Dialog = new Dialog(context);
        // Include dialog.xml file
        downloading_Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        downloading_Dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        downloading_Dialog.setContentView(R.layout.downloading_dialogue);
        TextView textDialog = (TextView) downloading_Dialog.findViewById(R.id.textDialog);
        ProgressBar progressBar_Downloading = (ProgressBar) downloading_Dialog.findViewById(R.id.progressBar_Downloading);
        // textDialog.setText("Downloading...");
        DisplayMetrics displaymetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = (int) ((int) displaymetrics.widthPixels);
        int height = (int) ((int) displaymetrics.heightPixels);
        downloading_Dialog.getWindow().setLayout(width, height);

    }
}
