package com.binaryic.beinsync.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.binaryic.beinsync.R;
import com.binaryic.beinsync.common.ApiCallBack;
import com.binaryic.beinsync.controllers.LoginController;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        downTimer.start();
    }

    CountDownTimer downTimer = new CountDownTimer(3000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {


            LoginController.sendPhoneDetailsApi(SplashScreen.this, new ApiCallBack() {
                @Override
                public void onSuccess(Object success) {
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
        public void onFinish() {


        }
    };


}
