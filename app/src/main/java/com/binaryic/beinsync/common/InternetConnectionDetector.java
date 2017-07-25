package com.binaryic.beinsync.common;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class InternetConnectionDetector {


    public static boolean isConnectingToInternet(Activity _context, View view, boolean show_Dialogue) {
        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {

                        return true;
                    }

                }
            if (show_Dialogue) {
                Utils.showSnackBar(_context, view, "Sorry..!!!!!! No Internet Connection");

            }
        }

        return false;
    }

    public boolean isInternetWorking() {

        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            Log.e("exit value===", "===" + exitValue);
            return (exitValue == 0);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }
}