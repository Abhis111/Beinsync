package com.binaryic.beinsync.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.binaryic.beinsync.common.Constants.AREA;
import static com.binaryic.beinsync.common.Constants.COLUMN_AGE;
import static com.binaryic.beinsync.common.Constants.COLUMN_AHARCARDNO;
import static com.binaryic.beinsync.common.Constants.COLUMN_BACKGROUND_COLOR;
import static com.binaryic.beinsync.common.Constants.COLUMN_CATEGORY;
import static com.binaryic.beinsync.common.Constants.COLUMN_FONT_NAME;
import static com.binaryic.beinsync.common.Constants.COLUMN_ID;
import static com.binaryic.beinsync.common.Constants.COLUMN_IMAGE;
import static com.binaryic.beinsync.common.Constants.COLUMN_INFO;
import static com.binaryic.beinsync.common.Constants.COLUMN_LINE_SPACING;
import static com.binaryic.beinsync.common.Constants.COLUMN_LINK;
import static com.binaryic.beinsync.common.Constants.COLUMN_LOCATION_OF_WORK;
import static com.binaryic.beinsync.common.Constants.COLUMN_MOBILE_NO;
import static com.binaryic.beinsync.common.Constants.COLUMN_OCCUPATION;
import static com.binaryic.beinsync.common.Constants.COLUMN_TAGS;
import static com.binaryic.beinsync.common.Constants.COLUMN_TEXT_ALIGNMENT;
import static com.binaryic.beinsync.common.Constants.COLUMN_TEXT_COLOR;
import static com.binaryic.beinsync.common.Constants.COLUMN_TEXT_MODE;
import static com.binaryic.beinsync.common.Constants.COLUMN_TEXT_SIZE;
import static com.binaryic.beinsync.common.Constants.COLUMN_TEXT_STYLE;
import static com.binaryic.beinsync.common.Constants.COLUMN_TIME_WORK;
import static com.binaryic.beinsync.common.Constants.COLUMN_TITLE;
import static com.binaryic.beinsync.common.Constants.COLUMN_TRANSPORT_MODE;
import static com.binaryic.beinsync.common.Constants.COLUMN_USER_NAME;
import static com.binaryic.beinsync.common.Constants.COLUMN_VEHICLE_USED;
import static com.binaryic.beinsync.common.Constants.LATITUDE;
import static com.binaryic.beinsync.common.Constants.LONGITUDE;
import static com.binaryic.beinsync.common.Constants.SECTOR;
import static com.binaryic.beinsync.common.Constants.SECTOR_ID;
import static com.binaryic.beinsync.common.Constants.STORY_ID;
import static com.binaryic.beinsync.common.Constants.TABLE_DASHBOARD;
import static com.binaryic.beinsync.common.Constants.TABLE_SECTOR;
import static com.binaryic.beinsync.common.Constants.TABLE_SETTING;
import static com.binaryic.beinsync.common.Constants.TABLE_TAGS;
import static com.binaryic.beinsync.common.Constants.TABLE_TOPICS;
import static com.binaryic.beinsync.common.Constants.TABLE_USER;
import static com.binaryic.beinsync.common.Constants.TOPIC_COUNT;
import static com.binaryic.beinsync.common.Constants.TOPIC_ID;
import static com.binaryic.beinsync.common.Constants.TOPIC_SLUG;
import static com.binaryic.beinsync.common.Constants.TOPIC_TITLE;


/**
 * Created by HP on 19-Jul-17.
 */

public class MyDBHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "com.binaryic.beinsync";
    public static int DATABASE_VERSION = 10;

    static String DATABASE_SETTING = "create table " + TABLE_SETTING + "( " + COLUMN_TEXT_SIZE + " text, " + COLUMN_TEXT_STYLE + " text, " + COLUMN_TEXT_MODE + " text, " + COLUMN_TEXT_ALIGNMENT + " text, " + COLUMN_LINE_SPACING + " text, " + COLUMN_BACKGROUND_COLOR + " text, " + COLUMN_FONT_NAME + " text, " + COLUMN_TEXT_COLOR + " text );";
    static String CREATE_DASHBOARD = "create table " + TABLE_DASHBOARD + "( " + COLUMN_ID + " text, " + COLUMN_TITLE + " text, " + COLUMN_LINK + " text, " + COLUMN_IMAGE + " text, " + COLUMN_CATEGORY + " text, " + COLUMN_INFO + " text );";
    static String CREATE_TAGS = "create table " + TABLE_TAGS + "( " + COLUMN_ID + " text, " + COLUMN_TITLE + " text, " + COLUMN_TAGS + " text, " + STORY_ID + " text );";
    static String CREATE_TOPIC = "create table " + TABLE_TOPICS + "( " + TOPIC_ID + " text, " + TOPIC_SLUG + " text, " + TOPIC_TITLE + " text, " + TOPIC_COUNT + " text );";
    static String CREATE_SECTOR = "create table " + TABLE_SECTOR + "( " + SECTOR_ID + " text, " + SECTOR + " text, " + AREA + " text, " + LATITUDE + " text, " + LONGITUDE + " text );";
    static String CREATE_USER = "create table " + TABLE_USER + "( " + COLUMN_ID + " text, " + COLUMN_USER_NAME + " text, " + COLUMN_AHARCARDNO + " text, " + COLUMN_AGE + " text, " + COLUMN_CATEGORY + " text, " + COLUMN_OCCUPATION + " text, " + COLUMN_LOCATION_OF_WORK + " text, " + COLUMN_TIME_WORK + " text, " + COLUMN_TRANSPORT_MODE + " text, " + COLUMN_VEHICLE_USED + " text, " + COLUMN_MOBILE_NO + " text );";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_SETTING);
        db.execSQL(CREATE_DASHBOARD);
        Log.e("CREATE_DASHBOARD", "==" + CREATE_DASHBOARD);
        db.execSQL(CREATE_SECTOR);
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_TAGS);
        db.execSQL(CREATE_TOPIC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DASHBOARD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECTOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOPICS);
        onCreate(db);
    }
}
