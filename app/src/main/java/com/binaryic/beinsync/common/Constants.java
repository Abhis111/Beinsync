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
    public static final String PATH_SETTING = "PATH_SETTING";
    public static final String PATH_TAGS = "PATH_TAGS";
    public static final String PATH_TOPICS = "PATH_TOPICS";
    public static final String URL = "http://www.beinsync.in/";
    public static final String URL_DASHBOARD = URL + "?json=1";
    public static final String SEND_PHONE_DETAILS = "";

    public static final Uri CONTENT_DASHBOARD = Uri.parse(CONTENT_PROTOCOL + AUTHORITY + "/" + PATH_DASHBOARD);
    public static final Uri CONTENT_SECTOR = Uri.parse(CONTENT_PROTOCOL + AUTHORITY + "/" + PATH_SECTOR);
    public static final Uri CONTENT_USER = Uri.parse(CONTENT_PROTOCOL + AUTHORITY + "/" + PATH_USER);
    public static final Uri CONTENT_SETTING = Uri.parse(CONTENT_PROTOCOL + AUTHORITY + "/" + PATH_SETTING);
    public static final Uri CONTENT_TAGS = Uri.parse(CONTENT_PROTOCOL + AUTHORITY + "/" + PATH_TAGS);
    public static final Uri CONTENT_TOPICS = Uri.parse(CONTENT_PROTOCOL + AUTHORITY + "/" + PATH_TOPICS);

    public static String TABLE_DASHBOARD = "TABLE_DASHBOARD";
    public static String TABLE_CATEGORY = "TABLE_CATEGORY";
    public static String COLUMN_ID = "COLUMN_ID";
    public static String COLUMN_TITLE = "COLUMN_TITLE";
    public static String COLUMN_SLUG = "COLUMN_SLUG";
    public static String COLUMN_TAGS = "COLUMN_TAGS";
    public static String COLUMN_CATEGORY = "COLUMN_CATEGORY";
    public static String COLUMN_PARENT = "COLUMN_PARENT";
    public static String COLUMN_DESCRIPTION = "COLUMN_DESCRIPTION";
    public static String COLUMN_LINK = "COLUMN_LINK";
    public static String COLUMN_IMAGE = "COLUMN_IMAGE";
    public static String COLUMN_INFO = "COLUMN_INFO";
    public static String COLUMN_POST_COUNT = "COLUMN_POST_COUNT";

    public static String TABLE_SECTOR = "TABLE_SECTOR";
    public static String SECTOR_ID = "SECTOR_ID";
    public static String SECTOR = "SECTOR";
    public static String AREA = "AREA";
    public static String LATITUDE = "LATITUDE";
    public static String LONGITUDE = "LONGITUDE";

    public static String TABLE_USER = "TABLE_USER";
    public static String TABLE_TAGS = "TABLE_TAGS";
    public static final String STORY_ID = "STORY_ID";
    public static final String COLUMN_USER_NAME = "COLUMN_USER_NAME";
    public static final String COLUMN_AGE = "COLUMN_AGE";
    public static final String COLUMN_AHARCARDNO = "COLUMN_AHARCARDNO";
    public static final String COLUMN_OCCUPATION = "COLUMN_OCCUPATION";
    public static final String COLUMN_LOCATION_OF_WORK = "COLUMN_LOCATION_OF_WORK";
    public static final String COLUMN_TIME_WORK = "COLUMN_TIME_WORK";
    public static final String COLUMN_TRANSPORT_MODE = "COLUMN_TRANSPORT_MODE";
    public static final String COLUMN_VEHICLE_USED = "COLUMN_VEHICLE_USED";
    public static final String COLUMN_MOBILE_NO = "COLUMN_MOBILE_NO";


     public static String TABLE_SETTING = "TABLE_SETTING";
    public static final String COLUMN_TEXT_SIZE = "COLUMN_TEXT_SIZE";
    public static final String COLUMN_TEXT_STYLE = "COLUMN_TEXT_STYLE";
    public static final String COLUMN_TEXT_MODE = "COLUMN_TEXT_MODE";
    public static final String COLUMN_TEXT_ALIGNMENT = "COLUMN_TEXT_ALIGNMENT";
    public static final String COLUMN_LINE_SPACING = "COLUMN_LINE_SPACING";
    public static final String COLUMN_BACKGROUND_COLOR = "COLUMN_BACKGROUND_COLOR";
    public static final String COLUMN_FONT_NAME = "COLUMN_FONT_NAME";
    public static final String COLUMN_TEXT_COLOR = "COLUMN_TEXT_COLOR";

    public static String TABLE_TOPICS = "TABLE_TOPICS";
    public static String TOPIC_ID = "TOPIC_ID";
    public static String TOPIC_SLUG = "TOPIC_SLUG";
    public static String TOPIC_TITLE = "TOPIC_TITLE";
    public static String TOPIC_COUNT = "TOPIC_COUNT";


}
