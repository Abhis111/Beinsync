package com.binaryic.beinsync.common;

import android.net.Uri;

/**
 * Created by HP on 19-Jul-17.
 */

public class Constants {

    public static final String AUTHORITY = "com.binaryic.beinsync";
    public static final String CONTENT_PROTOCOL = "content://";
    public static final String PATH_DASHBOARD = "dashboard";
    public static final String PATH_SECTOR = "sector";
    public static final String PATH_USER = "user";
    public static final String URL = "http://www.beinsync.in/";
    public static final String URL_DASHBOARD = URL+"?json=1";

    public static final Uri CONTENT_DASHBOARD = Uri.parse(CONTENT_PROTOCOL + AUTHORITY + "/" + PATH_DASHBOARD);
    public static final Uri CONTENT_SECTOR = Uri.parse(CONTENT_PROTOCOL + AUTHORITY + "/" + PATH_SECTOR);
    public static final Uri CONTENT_USER = Uri.parse(CONTENT_PROTOCOL + AUTHORITY + "/" + PATH_USER);

    public static String TABLE_DASHBOARD = "TABLE_DASHBOARD";
    public static String COLUMN_ID = "COLUMN_ID";
    public static String COLUMN_TITLE = "COLUMN_TITLE";
    public static String COLUMN_IMAGE = "COLUMN_IMAGE";
    public static String COLUMN_INFO = "COLUMN_INFO";

    public static String TABLE_SECTOR = "TABLE_SECTOR";
    public static String SECTOR_ID = "SECTOR_ID";
    public static String SECTOR = "SECTOR";
    public static String AREA = "AREA";
    public static String LATITUDE = "LATITUDE";
    public static String LONGITUDE = "LONGITUDE";

    public static String TABLE_USER = "TABLE_USER";
    public static final String ID = "ID";
    public static final String USER_NAME = "USER_NAME";
    public static final String AGE = "AGE";
    public static final String AHARCARDNO = "AHARCARDNO";
    public static final String CATEGORY = "CATEGORY";
    public static final String OCCUPATION = "OCCUPATION";
    public static final String LOCATION_OF_WORK = "LOCATION_OF_WORK";
    public static final String TIME_WORK = "TIME_WORK";
    public static final String TRANSPORT_MODE = "TRANSPORT_MODE";
    public static final String VEHICLE_USED = "VEHICLE_USED";
    public static final String MOBILE_NO = "MOBILE_NO";
}