package com.binaryic.beinsync.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import static com.binaryic.beinsync.common.Constants.AUTHORITY;
import static com.binaryic.beinsync.common.Constants.PATH_DASHBOARD;
import static com.binaryic.beinsync.common.Constants.PATH_SECTOR;
import static com.binaryic.beinsync.common.Constants.PATH_SETTING;
import static com.binaryic.beinsync.common.Constants.PATH_TAGS;
import static com.binaryic.beinsync.common.Constants.PATH_USER;
import static com.binaryic.beinsync.common.Constants.TABLE_DASHBOARD;
import static com.binaryic.beinsync.common.Constants.TABLE_SECTOR;
import static com.binaryic.beinsync.common.Constants.TABLE_SETTING;
import static com.binaryic.beinsync.common.Constants.TABLE_TAGS;
import static com.binaryic.beinsync.common.Constants.TABLE_USER;


/**
 * Created by HP on 19-Jul-17.
 */

public class KyaContentProvider extends ContentProvider {
    private static final int CODE_DASHBOARD = 1;
    private static final int CODE_SECTOR = 2;
    private static final int CODE_USER = 3;
    private static final int CODE_SETTING = 4;
    private static final int CODE_TAGS = 5;

    private MyDBHelper helper;
    private SQLiteDatabase database;
    private UriMatcher matcher;

    @Override
    public boolean onCreate() {
        helper = new MyDBHelper(getContext());
        database = helper.getWritableDatabase();
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, PATH_DASHBOARD, CODE_DASHBOARD);
        matcher.addURI(AUTHORITY, PATH_SECTOR, CODE_SECTOR);
        matcher.addURI(AUTHORITY, PATH_USER, CODE_USER);
        matcher.addURI(AUTHORITY, PATH_SETTING, CODE_SETTING);
        matcher.addURI(AUTHORITY, PATH_TAGS, CODE_TAGS);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int code = matcher.match(uri);
        Cursor cursor = null;
        if (code == CODE_DASHBOARD) {
            cursor = database.query(TABLE_DASHBOARD, projection, selection, null, null, null, null);
        } else if (code == CODE_SECTOR) {
            cursor = database.query(TABLE_SECTOR, projection, selection, null, null, null, null);
        } else if (code == CODE_USER) {
            cursor = database.query(TABLE_USER, projection, selection, null, null, null, null);
        } else if (code == CODE_SETTING) {
            cursor = database.query(TABLE_SETTING, projection, selection, null, null, null, null);
        } else if (code == CODE_TAGS) {
            cursor = database.query(TABLE_TAGS, projection, selection, null, null, null, null);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int code = matcher.match(uri);
        if (code == CODE_DASHBOARD) {
            database.insert(TABLE_DASHBOARD, null, values);
        } else if (code == CODE_SECTOR) {
            database.insert(TABLE_SECTOR, null, values);
        } else if (code == CODE_USER) {
            database.insert(TABLE_USER, null, values);
        } else if (code == CODE_SETTING) {
            database.insert(TABLE_SETTING, null, values);
        } else if (code == CODE_TAGS) {
            database.insert(TABLE_TAGS, null, values);
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int code = matcher.match(uri);
        int delete = 0;
        if (code == CODE_DASHBOARD) {
            delete = database.delete(TABLE_DASHBOARD, selection, selectionArgs);
        } else if (code == CODE_SECTOR) {
            delete = database.delete(TABLE_SECTOR, selection, selectionArgs);
        } else if (code == CODE_USER) {
            delete = database.delete(TABLE_USER, selection, selectionArgs);
        } else if (code == CODE_SETTING) {
            delete = database.delete(TABLE_SETTING, selection, selectionArgs);
        } else if (code == CODE_TAGS) {
            delete = database.delete(TABLE_TAGS, selection, selectionArgs);
        }
        return delete;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int code = matcher.match(uri);
        int row = 0;
        if (code == CODE_DASHBOARD) {
            row = database.update(TABLE_DASHBOARD, values, selection, selectionArgs);
        } else if (code == CODE_SECTOR) {
            row = database.update(TABLE_SECTOR, values, selection, selectionArgs);
        } else if (code == CODE_USER) {
            row = database.update(TABLE_USER, values, selection, selectionArgs);
        } else if (code == CODE_SETTING) {
            row = database.update(TABLE_SETTING, values, selection, selectionArgs);
        } else if (code == CODE_TAGS) {
            row = database.update(TABLE_TAGS, values, selection, selectionArgs);
        }
        return row;
    }
}
