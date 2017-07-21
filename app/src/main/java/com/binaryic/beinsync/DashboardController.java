package com.binaryic.beinsync;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.binaryic.beinsync.Constants.URL_DASHBOARD;

/**
 * Created by Binary_Apple on 7/21/17.
 */

public class DashboardController {


    public static void getDashboardApiCall(final Activity context, final ApiCallBack callback) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DASHBOARD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("getAccessTokenApiCall", "response =" + response);

                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("status").matches("ok")) {

                        JSONArray jsonArray = object.getJSONArray("posts");
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            jsonObject.getString("id");
                            jsonObject.getString("title");
                            jsonObject.getString("content");
                            if (jsonObject.has("thumbnail_images")) {
                                JSONObject imags_Object = jsonObject.getJSONObject("thumbnail_images");
                                if (imags_Object.has("medium_large")) {
                                    JSONObject medium_Images_Object = imags_Object.getJSONObject("medium_large");
                                    if (medium_Images_Object.has("url")) {
                                        String image_Url = medium_Images_Object.getString("url");

                                    }
                                }
                            }
                        }

                        callback.onSuccess(object.getInt("success"));
                    } else {
                        callback.onError("success value is 0. please check responce");
                    }

                } catch (Exception e) {
                    callback.onError(e.getMessage());
                    Log.e("errrorrrr", e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("getAccessTokenApiCall", error.toString());
                callback.onError(error.getMessage());

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("client_name", "Seller App");
                params.put("package_name", "com.binaryic.sellerapp");
                params.put("company_name", "BinaryIC");

                Log.e("getAccessTokenApiCall", "params" + params.toString());
                return params;
            }
        };
        MyApplication.getInstance().addToRequestQueue(stringRequest, "getAccessTokenApiCall");


    }

}
