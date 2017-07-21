package com.binaryic.beinsync.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.os.Build.ID;
import static android.provider.MediaStore.Video.VideoColumns.CATEGORY;
import static com.binaryic.beinsync.common.Constants.AGE;
import static com.binaryic.beinsync.common.Constants.AHARCARDNO;
import static com.binaryic.beinsync.common.Constants.AREA;
import static com.binaryic.beinsync.common.Constants.COLUMN_ID;
import static com.binaryic.beinsync.common.Constants.COLUMN_IMAGE;
import static com.binaryic.beinsync.common.Constants.COLUMN_INFO;
import static com.binaryic.beinsync.common.Constants.COLUMN_LINK;
import static com.binaryic.beinsync.common.Constants.COLUMN_TITLE;
import static com.binaryic.beinsync.common.Constants.LATITUDE;
import static com.binaryic.beinsync.common.Constants.LOCATION_OF_WORK;
import static com.binaryic.beinsync.common.Constants.LONGITUDE;
import static com.binaryic.beinsync.common.Constants.MOBILE_NO;
import static com.binaryic.beinsync.common.Constants.OCCUPATION;
import static com.binaryic.beinsync.common.Constants.SECTOR;
import static com.binaryic.beinsync.common.Constants.SECTOR_ID;
import static com.binaryic.beinsync.common.Constants.TABLE_DASHBOARD;
import static com.binaryic.beinsync.common.Constants.TABLE_SECTOR;
import static com.binaryic.beinsync.common.Constants.TABLE_USER;
import static com.binaryic.beinsync.common.Constants.TIME_WORK;
import static com.binaryic.beinsync.common.Constants.TRANSPORT_MODE;
import static com.binaryic.beinsync.common.Constants.USER_NAME;
import static com.binaryic.beinsync.common.Constants.VEHICLE_USED;


/**
 * Created by HP on 19-Jul-17.
 */

public class MyDBHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "com.binaryic.beinsync";
    public static int DATABASE_VERSION = 2;

    static String CREATE_DASHBOARD = "create table " + TABLE_DASHBOARD + "( " + COLUMN_ID + " text, " + COLUMN_TITLE + " text, " + COLUMN_LINK + " text, " + COLUMN_IMAGE + " text, " + COLUMN_INFO + " text );";
    static String CREATE_SECTOR = "create table " + TABLE_SECTOR + "( " + SECTOR_ID + " text, " + SECTOR + " text, " + AREA + " text, " + LATITUDE + " text, " + LONGITUDE + " text );";
    static String CREATE_USER = "create table " + TABLE_USER + "( " + ID + " text, " + USER_NAME + " text, " + AHARCARDNO + " text, " + AGE + " text, " + CATEGORY + " text, " + OCCUPATION + " text, " + LOCATION_OF_WORK + " text, " + TIME_WORK + " text, " + TRANSPORT_MODE + " text, " + VEHICLE_USED + " text, " + MOBILE_NO + " text );";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DASHBOARD);
        db.execSQL(CREATE_SECTOR);
        db.execSQL(CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DASHBOARD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECTOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}
