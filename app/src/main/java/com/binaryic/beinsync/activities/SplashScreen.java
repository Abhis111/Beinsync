package com.binaryic.beinsync.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.common.ApiCallBack;
import com.binaryic.beinsync.common.InternetConnectionDetector;
import com.binaryic.beinsync.controllers.DashboardController;
import com.binaryic.beinsync.controllers.LoginController;

public class SplashScreen extends AppCompatActivity {
    RelativeLayout rl_Splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        rl_Splash = (RelativeLayout) findViewById(R.id.rl_Splash);
        getTopics();
    }
    private void getTopics(){
        DashboardController.getTopicsApiCall(SplashScreen.this, "http://www.beinsync.in/?json=get_category_index", new ApiCallBack() {
            @Override
            public void onSuccess(Object success) {
                downTimer.start();
            }
            @Override
            public void onError(String error) {
                downTimer.start();
            }
        });
    }

    CountDownTimer downTimer = new CountDownTimer(3000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
        }
        @Override
        public void onFinish() {

            if (InternetConnectionDetector.isConnectingToInternet(SplashScreen.this, rl_Splash, true)) {

                if (ContextCompat.checkSelfPermission(SplashScreen.this,
                        Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(SplashScreen.this,
                            Manifest.permission.READ_PHONE_STATE)) {
                        ActivityCompat.requestPermissions(SplashScreen.this, new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_NETWORK_STATE}, 0);
                    } else {
                        ActivityCompat.requestPermissions(SplashScreen.this,
                                new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_NETWORK_STATE}, 0);
                    }
                } else {
                    // sendPhoneDetails();
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                }
            }else {
                // sendPhoneDetails();
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }
    };

    public void sendPhoneDetails() {
        LoginController.sendPhoneDetailsApi(SplashScreen.this, new ApiCallBack() {
            @Override
            public void onSuccess(Object success) {
                Log.e("SplashScreen", "success ==");

                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }

            @Override
            public void onError(String error) {
                Log.e("SplashScreen", "error ==" + error);
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length == 2
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //sendPhoneDetails();
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            } else
                Toast.makeText(SplashScreen.this, "Please accept permission for go ahead.", Toast.LENGTH_LONG).show();
        }
    }

}
