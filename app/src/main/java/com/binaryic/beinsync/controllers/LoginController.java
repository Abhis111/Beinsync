package com.binaryic.beinsync.controllers;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.binaryic.beinsync.common.ApiCallBack;
import com.binaryic.beinsync.common.MyApplication;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.binaryic.beinsync.common.Constants.SEND_PHONE_DETAILS;

/**
 * Created by minakshi on 25/7/17.
 */

public class LoginController {
    public static void sendPhoneDetailsApi(final Activity context,final ApiCallBack callback) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SEND_PHONE_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("getDashboardApiCall", "response =" + response);

                try {
                    JSONObject object = new JSONObject(response);


                        callback.onSuccess(object);

            } catch (Exception e) {
                    callback.onError(e.getMessage());
                    Log.e("errrorrrr", e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("sendPhoneDetailsApi", error.toString());
                callback.onError(error.getMessage());

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("device_id", getDeviceID(context));
                params.put("model_no", getDeviceName());
                params.put("imei_no", getIMEI(context));

                Log.e("sendPhoneDetailsApi", "params" + params.toString());
                return params;
            }
        };
        MyApplication.getInstance().addToRequestQueue(stringRequest, "getAccessTokenApiCall");


    }


    public static String getIMEI(Activity context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    public static String getDeviceID(Activity context) {
        String m_androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return m_androidId;
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;
        String phrase = "";
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase += Character.toUpperCase(c);
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase += c;
        }
        return phrase;
    }

}
