package com.binaryic.beinsync.controllers;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.binaryic.beinsync.common.ApiCallBack;
import com.binaryic.beinsync.common.MyApplication;
import com.binaryic.beinsync.models.HomeModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.binaryic.beinsync.common.Constants.COLUMN_ID;
import static com.binaryic.beinsync.common.Constants.COLUMN_IMAGE;
import static com.binaryic.beinsync.common.Constants.COLUMN_INFO;
import static com.binaryic.beinsync.common.Constants.COLUMN_LINK;
import static com.binaryic.beinsync.common.Constants.COLUMN_TITLE;
import static com.binaryic.beinsync.common.Constants.CONTENT_DASHBOARD;
import static com.binaryic.beinsync.common.Constants.URL_DASHBOARD;

/**
 * Created by Binary_Apple on 7/21/17.
 */

public class DashboardController {


    public static void getDashboardApiCall(final Activity context, final ApiCallBack callback) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DASHBOARD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("getDashboardApiCall", "response =" + response);

                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("status").matches("ok")) {
                        ArrayList<HomeModel> array_Data = new ArrayList<HomeModel>();
                        JSONArray jsonArray = object.getJSONArray("posts");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            HomeModel homeModel = new HomeModel();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            homeModel.setId(jsonObject.getString("id"));
                            homeModel.setTitle(jsonObject.getString("title"));
                            homeModel.setUrl(jsonObject.getString("url"));
                            homeModel.setContent(jsonObject.getString("content"));
                            if (jsonObject.has("thumbnail_images")) {
                                JSONObject imags_Object = jsonObject.getJSONObject("thumbnail_images");
                                if (imags_Object.has("medium_large")) {
                                    JSONObject medium_Images_Object = imags_Object.getJSONObject("medium_large");
                                    if (medium_Images_Object.has("url")) {
                                        homeModel.setImage(medium_Images_Object.getString("url"));

                                    }
                                }
                            }
                            addDashboardDataInDatabase(context, homeModel);

                            array_Data.add(homeModel);
                        }
                        callback.onSuccess(array_Data);
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

                Log.e("getAccessTokenApiCall", "params" + params.toString());
                return params;
            }
        };
        MyApplication.getInstance().addToRequestQueue(stringRequest, "getAccessTokenApiCall");


    }


    public static ArrayList<HomeModel> getDashboardDataFromDatabase(Activity context) {
        ArrayList<HomeModel> array_Data = new ArrayList<HomeModel>();
        Cursor cursor = context.getContentResolver().query(CONTENT_DASHBOARD, null, null, null, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            HomeModel homeModel = new HomeModel();

            homeModel.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
            homeModel.setContent(cursor.getString(cursor.getColumnIndex(COLUMN_INFO)));
            homeModel.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
            homeModel.setUrl(cursor.getString(cursor.getColumnIndex(COLUMN_LINK)));
            homeModel.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE)));

            array_Data.add(homeModel);
        }
        return array_Data;
    }

    public static void addDashboardDataInDatabase(Activity context, HomeModel homeModel) {
        ContentValues values = new ContentValues();
        String selection = COLUMN_ID + "  = '" + homeModel.getId() + "'";
        Cursor cursor = context.getContentResolver().query(CONTENT_DASHBOARD, null, selection, null, null);


        values.put(COLUMN_ID, homeModel.getId());
        values.put(COLUMN_IMAGE, homeModel.getImage());
        values.put(COLUMN_TITLE, homeModel.getTitle());
        values.put(COLUMN_INFO, homeModel.getContent());
        values.put(COLUMN_LINK, homeModel.getUrl());

        if (cursor.getCount() > 0) {
            context.getContentResolver().update(CONTENT_DASHBOARD, values, selection, null);

        } else {
            context.getContentResolver().insert(CONTENT_DASHBOARD, values);
        }
    }

}
