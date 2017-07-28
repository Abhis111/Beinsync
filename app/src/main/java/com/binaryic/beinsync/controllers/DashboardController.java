package com.binaryic.beinsync.controllers;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.binaryic.beinsync.common.ApiCallBack;
import com.binaryic.beinsync.common.MyApplication;
import com.binaryic.beinsync.database.MyDBHelper;
import com.binaryic.beinsync.models.CategoryModel;
import com.binaryic.beinsync.models.HomeModel;
import com.binaryic.beinsync.models.TagModel;
import com.binaryic.beinsync.models.TopicModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.binaryic.beinsync.common.Constants.COLUMN_CATEGORY;
import static com.binaryic.beinsync.common.Constants.COLUMN_ID;
import static com.binaryic.beinsync.common.Constants.COLUMN_IMAGE;
import static com.binaryic.beinsync.common.Constants.COLUMN_INFO;
import static com.binaryic.beinsync.common.Constants.COLUMN_LINK;
import static com.binaryic.beinsync.common.Constants.COLUMN_TAGS;
import static com.binaryic.beinsync.common.Constants.COLUMN_TITLE;
import static com.binaryic.beinsync.common.Constants.CONTENT_DASHBOARD;
import static com.binaryic.beinsync.common.Constants.CONTENT_TAGS;
import static com.binaryic.beinsync.common.Constants.CONTENT_TOPICS;
import static com.binaryic.beinsync.common.Constants.STORY_ID;
import static com.binaryic.beinsync.common.Constants.TOPIC_COUNT;
import static com.binaryic.beinsync.common.Constants.TOPIC_ID;
import static com.binaryic.beinsync.common.Constants.TOPIC_SLUG;
import static com.binaryic.beinsync.common.Constants.TOPIC_TITLE;

/**
 * Created by Binary_Apple on 7/21/17.
 */

public class DashboardController {


    public static void getCategoriesApiCall(final Activity context, String url, final ApiCallBack callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("getDashboardApiCall", "response =" + response);

                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("status").matches("ok")) {
                        JSONArray jsonArray_Categorues = object.getJSONArray("categories");
                        for (int i = 0; i < jsonArray_Categorues.length(); i++) {
                            JSONObject jsonObject = jsonArray_Categorues.getJSONObject(i);
                            CategoryModel categoryModel = new CategoryModel();
                            categoryModel.setId(jsonObject.getString("id"));
                            categoryModel.setSlug(jsonObject.getString("slug"));
                            categoryModel.setTitle(jsonObject.getString("title"));
                            categoryModel.setDescription(jsonObject.getString("description"));
                            categoryModel.setParent(jsonObject.getString("parent"));
                            categoryModel.setPost_count(jsonObject.getString("post_count"));
                        }
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
    public static void getTopicsApiCall(final Activity context, String url, final ApiCallBack callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("getDashboardApiCall", "response =" + response);
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("status").matches("ok")) {
                        JSONArray jsonArray_Categorues = object.getJSONArray("categories");
                        for (int i = 0; i < jsonArray_Categorues.length(); i++) {
                            JSONObject jsonObject = jsonArray_Categorues.getJSONObject(i);
                            TopicModel topicModel = new TopicModel();
                            topicModel.setId(jsonObject.getString("id"));
                            topicModel.setPost_count(jsonObject.getString("post_count"));
                            topicModel.setSlug(jsonObject.getString("slug"));
                            topicModel.setTitle(jsonObject.getString("title"));
                            addTopicInDatabase(context,topicModel);
                        }
                        callback.onSuccess("success");
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
    public static void addTopicInDatabase(Activity context, TopicModel topicModel) {
        ContentValues values = new ContentValues();
        String selection = TOPIC_ID + "  = '" + topicModel.getId() + "'";
        Cursor cursor = context.getContentResolver().query(CONTENT_TOPICS, null, selection, null, null);
        values.put(TOPIC_ID, topicModel.getId());
        values.put(TOPIC_SLUG, topicModel.getSlug());
        values.put(TOPIC_TITLE, topicModel.getTitle());
        values.put(TOPIC_COUNT, topicModel.getPost_count());
        if (cursor.getCount() > 0) {
            context.getContentResolver().update(CONTENT_TOPICS, values, selection, null);
        } else {
            context.getContentResolver().insert(CONTENT_TOPICS, values);
        }
    }

    public static ArrayList<TopicModel> getTopics(Activity context){
        ArrayList<TopicModel> list = new ArrayList<>();
        try{
            Cursor cursor = context.getContentResolver().query(CONTENT_TOPICS, null, null, null, null);
            if(cursor.getCount() > 0){
                for(int i = 0;i < cursor.getCount();i++){
                    cursor.moveToNext();
                    TopicModel topicModel = new TopicModel();
                    topicModel.setSlug(cursor.getString(cursor.getColumnIndex(TOPIC_SLUG)));
                    topicModel.setId(cursor.getString(cursor.getColumnIndex(TOPIC_ID)));
                    topicModel.setTitle(cursor.getString(cursor.getColumnIndex(TOPIC_TITLE)));
                    topicModel.setPost_count(cursor.getString(cursor.getColumnIndex(TOPIC_COUNT)));
                    list.add(topicModel);
                }
            }
        }catch (Exception ex){}
        return list;
    }

    public static void getDashboardApiCall(final Activity context, String url, final ApiCallBack callback) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("getDashboardApiCall", "response =" + response);

                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("status").matches("ok")) {
                        ArrayList<HomeModel> array_Data = new ArrayList<HomeModel>();
                        if (object.has("posts")) {
                            JSONArray jsonArray = object.getJSONArray("posts");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                HomeModel homeModel = new HomeModel();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                if (jsonObject.has("id"))
                                    homeModel.setId(jsonObject.getString("id"));
                                if (jsonObject.has("title"))
                                    homeModel.setTitle(jsonObject.getString("title"));
                                if (jsonObject.has("url"))
                                    homeModel.setUrl(jsonObject.getString("url"));
                                if (jsonObject.has("content"))
                                    homeModel.setContent(jsonObject.getString("content"));

                                if (jsonObject.has("categories")) {
                                    JSONArray array_Categories = jsonObject.getJSONArray("categories");
                                    String title = "";
                                    homeModel.setTitle_Category(array_Categories.toString());
                                    /*for (int j = 0; j < array_Categories.length(); j++) {
                                        JSONObject jsonObject_Categories = array_Categories.getJSONObject(j);
                                        if (j == 0) {
                                            title = jsonObject_Categories.getString("title");
                                            arrayList.add(jsonObject_Categories.getString("title"));
                                        } else {
                                            arrayList.add(jsonObject_Categories.getString("title"));
                                            title = title + "," + jsonObject_Categories.getString("title");
                                        }
                                        homeModel.setTitle_Category(arrayList.toString());
                                        //homeModel.setTitle_Category(title);
                                    }*/
                                    Log.e("category_Title", "==" + array_Categories.toString());

                                }

                                if (jsonObject.has("tags")) {
                                    JSONArray array_Tags = jsonObject.getJSONArray("tags");
                                    for (int j = 0; j < array_Tags.length(); j++) {
                                        JSONObject jsonObject_Tags = array_Tags.getJSONObject(j);
                                        TagModel tagModel = new TagModel();
                                        tagModel.setId(jsonObject.getString("id"));
                                        tagModel.setTag(jsonObject_Tags.getString("title"));
                                        tagModel.setTitle(jsonObject.getString("title"));
                                        tagModel.setStory_id(homeModel.getId());
                                        addTagsDataInDatabase(context, tagModel);

                                    }
                                }
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
                        } else {
                            JSONArray jsonArray = object.getJSONArray("page");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                HomeModel homeModel = new HomeModel();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                if (jsonObject.has("id"))
                                    homeModel.setId(jsonObject.getString("id"));
                                if (jsonObject.has("title"))
                                    homeModel.setTitle(jsonObject.getString("title"));
                                if (jsonObject.has("url"))
                                    homeModel.setUrl(jsonObject.getString("url"));
                                if (jsonObject.has("content"))
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
                        }
                        callback.onSuccess(array_Data);
                    } else {
                        callback.onError("success value is 0. please check responce");
                    }

                } catch (Exception e) {
                    callback.onError(e.getMessage());
                    Log.e("errroeerrrrr", e.toString());
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

    public static void getNewsApiCall(final Activity context, String url, final ApiCallBack callback) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("getDashboardApiCall", "response =" + response);

                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("status").matches("ok")) {
                        ArrayList<HomeModel> array_Data = new ArrayList<HomeModel>();
                        if (object.has("posts")) {

                            JSONArray jsonArray = object.getJSONArray("posts");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                HomeModel homeModel = new HomeModel();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                if (jsonObject.has("id"))
                                    homeModel.setId(jsonObject.getString("id"));
                                if (jsonObject.has("title"))
                                    homeModel.setTitle(jsonObject.getString("title"));
                                if (jsonObject.has("url"))
                                    homeModel.setUrl(jsonObject.getString("url"));
                                if (jsonObject.has("content"))
                                    homeModel.setContent(jsonObject.getString("content"));
                                if (jsonObject.has("thumbnail_images")) {
                                    try {
                                        JSONObject imags_Object = jsonObject.getJSONObject("thumbnail_images");
                                        if (imags_Object.has("medium_large")) {
                                            JSONObject medium_Images_Object = imags_Object.getJSONObject("medium_large");
                                            if (medium_Images_Object.has("url")) {
                                                homeModel.setImage(medium_Images_Object.getString("url"));

                                            }
                                        }
                                    } catch (Exception e) {
                                    }
                                }

                                array_Data.add(homeModel);
                            }
                        } else {
                            JSONArray jsonArray = object.getJSONArray("page");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                HomeModel homeModel = new HomeModel();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                if (jsonObject.has("id"))
                                    homeModel.setId(jsonObject.getString("id"));
                                if (jsonObject.has("title"))
                                    homeModel.setTitle(jsonObject.getString("title"));
                                if (jsonObject.has("url"))
                                    homeModel.setUrl(jsonObject.getString("url"));
                                if (jsonObject.has("content"))
                                    homeModel.setContent(jsonObject.getString("content"));
                                if (jsonObject.has("thumbnail_images")) {
                                    try {
                                        JSONObject imags_Object = jsonObject.getJSONObject("thumbnail_images");
                                        if (imags_Object.has("medium_large")) {
                                            JSONObject medium_Images_Object = imags_Object.getJSONObject("medium_large");
                                            if (medium_Images_Object.has("url")) {
                                                homeModel.setImage(medium_Images_Object.getString("url"));

                                            }
                                        }
                                    } catch (Exception e) {
                                    }
                                }

                                array_Data.add(homeModel);
                            }
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

                return params;
            }
        };
        MyApplication.getInstance().addToRequestQueue(stringRequest, "getAccessTokenApiCall");


    }


    public static ArrayList<HomeModel> getDashboardDataFromDatabase(Activity context, String category) {
        String selection = COLUMN_CATEGORY + " like  '" + "%" + category + "%" + "'";
        MyDBHelper helper = new MyDBHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select COLUMN_ID,COLUMN_TITLE,COLUMN_LINK,COLUMN_CATEGORY,COLUMN_IMAGE,COLUMN_INFO from TABLE_DASHBOARD where lower(COLUMN_CATEGORY) like '%" + category + "%'", null);

        ArrayList<HomeModel> array_Data = new ArrayList<HomeModel>();
        //Cursor cursor_test = context.getContentResolver().query(CONTENT_DASHBOARD, null, null, null, null);
        // Log.e("DashboardContro1ller", "cursor==" + cursor_test.getCount());


        // Cursor cursor = context.getContentResolver().query(CONTENT_DASHBOARD, null, selection, null, null);
        // Log.e("DashboardContro1ller", "cursor==" + cursor.getCount());
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();

            HomeModel homeModel = new HomeModel();
            homeModel.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
            homeModel.setContent(cursor.getString(cursor.getColumnIndex(COLUMN_INFO)));
            homeModel.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
            homeModel.setUrl(cursor.getString(cursor.getColumnIndex(COLUMN_LINK)));
            homeModel.setTitle_Category(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
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
        values.put(COLUMN_CATEGORY, homeModel.getTitle_Category());
        Log.e("Insert == ", "Insert into TABLE_DASHBOARD(COLUMN_CATEGORY) values ('" + homeModel.getTitle_Category() + "')");

        if (cursor.getCount() > 0) {
            context.getContentResolver().update(CONTENT_DASHBOARD, values, selection, null);

        } else {
            context.getContentResolver().insert(CONTENT_DASHBOARD, values);
        }
    }

    public static void addTagsDataInDatabase(Activity context, TagModel tagModel) {
        ContentValues values = new ContentValues();
        String selection = COLUMN_ID + "  = '" + tagModel.getId() + "' AND " + COLUMN_TAGS + " = '" + tagModel.getTag() + "'";
        Cursor cursor = context.getContentResolver().query(CONTENT_TAGS, null, selection, null, null);
        values.put(COLUMN_ID, tagModel.getId());
        values.put(COLUMN_TAGS, tagModel.getTag());
        values.put(COLUMN_TITLE, tagModel.getTitle());
        values.put(STORY_ID, tagModel.getStory_id());
        if (cursor.getCount() > 0) {
            context.getContentResolver().update(CONTENT_TAGS, values, selection, null);
        } else {
            context.getContentResolver().insert(CONTENT_TAGS, values);
        }
    }

    public static ArrayList<TagModel> getDistinctTag(Activity context) {
        ArrayList<TagModel> list = new ArrayList<>();
        try {
            MyDBHelper helper = new MyDBHelper(context);
            SQLiteDatabase database = helper.getWritableDatabase();
            Cursor cursor = database.rawQuery("Select distinct COLUMN_TAGS from TABLE_TAGS Order by COLUMN_TAGS", null);
            if (cursor.getCount() > 0) {
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToNext();
                    list.add(new TagModel(cursor.getString(cursor.getColumnIndex(COLUMN_TAGS)), cursor.getString(cursor.getColumnIndex(COLUMN_TAGS)), "", ""));
                }
            }
        } catch (Exception ex) {
            Log.e("getDistinctTag", "error ==" + ex.getMessage());
        }
        return list;
    }

    public static ArrayList<HomeModel> filter(Context context, String filter) {
        ArrayList<HomeModel> array_Data = new ArrayList<HomeModel>();
        try {
            MyDBHelper helper = new MyDBHelper(context);
            SQLiteDatabase database = helper.getWritableDatabase();
            Cursor cursor = database.rawQuery("Select TABLE_DASHBOARD.COLUMN_ID,TABLE_DASHBOARD.COLUMN_TITLE,TABLE_DASHBOARD.COLUMN_LINK,TABLE_DASHBOARD.COLUMN_CATEGORY,TABLE_DASHBOARD.COLUMN_IMAGE,TABLE_DASHBOARD.COLUMN_INFO from TABLE_DASHBOARD Inner join TABLE_TAGS on TABLE_DASHBOARD.COLUMN_ID = TABLE_TAGS.STORY_ID where COLUMN_TAGS in (" + filter + ") Group By TABLE_DASHBOARD.COLUMN_ID", null);
            //Cursor cursor = context.getContentResolver().query(CONTENT_DASHBOARD, null, selection, null, null);
            if (cursor.getCount() > 0) {
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToNext();
                    HomeModel homeModel = new HomeModel();
                    homeModel.setContent(cursor.getString(cursor.getColumnIndex(COLUMN_INFO)));
                    homeModel.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                    homeModel.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE)));
                    homeModel.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                    homeModel.setUrl(cursor.getString(cursor.getColumnIndex(COLUMN_LINK)));
                    homeModel.setTitle_Category(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
                    array_Data.add(homeModel);
                    Log.e("id", cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                }
            }
        } catch (Exception ex) {
            Log.e("DashboardController", "error ==" + ex.getMessage());
        }
        return array_Data;

    }


    public static ArrayList<String> getSpecificTagStories(Activity context, String tag) {

        String selection = COLUMN_TAGS + "  = '" + tag + "'";
        ArrayList<String> array_Data = new ArrayList<String>();
        Cursor cursor = context.getContentResolver().query(CONTENT_TAGS, null, selection, null, null);

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();

            array_Data.add(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));


        }

        return array_Data;
    }

    public static ArrayList<HomeModel> search(Context context, String search_text) {
        ArrayList<HomeModel> array_Data = new ArrayList<HomeModel>();
        try {
            MyDBHelper helper = new MyDBHelper(context);
            SQLiteDatabase database = helper.getWritableDatabase();
            Cursor cursor = database.rawQuery("Select COLUMN_ID,COLUMN_TITLE,COLUMN_LINK,COLUMN_CATEGORY,COLUMN_IMAGE,COLUMN_INFO from TABLE_DASHBOARD where lower(COLUMN_TITLE) like '%" + search_text + "%'", null);
            //Cursor cursor = context.getContentResolver().query(CONTENT_DASHBOARD, null, selection, null, null);
            if (cursor.getCount() > 0) {
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToNext();
                    HomeModel homeModel = new HomeModel();
                    homeModel.setContent(cursor.getString(cursor.getColumnIndex(COLUMN_INFO)));
                    homeModel.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                    homeModel.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE)));
                    homeModel.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                    homeModel.setUrl(cursor.getString(cursor.getColumnIndex(COLUMN_LINK)));
                    homeModel.setTitle_Category(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
                    array_Data.add(homeModel);
                }
            }
        } catch (Exception ex) {
            Log.e("DashboardController", "error ==" + ex.getMessage());
        }
        return array_Data;

    }

}
