package com.excel.configuration;

import java.io.File;

public class Constants {
    public static final String APPSTV_DATA_DIRECTORY_NAME = "appstv_data";
    public static final String APPSTV_DB_NAME = "appstv_data.db";
    public static final String CONFIGURATION_FILE_NAME = "configuration";
    public static final String DIGITAL_SIGNAGE_CONFIG_FILE_NAME = "digital_signage";
    public static final String DIR_DIGITAL_SIGNAGE = "appstv_data/graphics/digital_signage";
    public static final String DIR_FIRMWARE = "appstv_data/firmware";
    public static final String DIR_HOTEL_LOGO = "appstv_data/graphics/hotel_logo";
    public static final String DIR_PREINSTALL = "preinstall";
    public static final String DIR_PREINSTALL_EXT;
    public static final String DIR_PREINSTALL_SYSTEM = "/system/preinstall";
    public static final String DIR_RATINGS_THUMBNAIL = "appstv_data/graphics/ratings_thumbnail";
    public static final String DIR_TV_CHANNELS_FILE = "appstv_data/tv_channels";
    public static final String LAUNCHER_CONFIG_JSON_FILE_NAME = "launcher_config.json";
    public static final String PATH_CONFIGURATION_FILE = "appstv_data/configuration";
    public static final String PATH_CONFIGURATION_FILE_SYSTEM = "/system/appstv_data/configuration";
    public static final String PATH_LAUNCHER_CONFIG_FILE = "appstv_data/launcher_config.json";
    public static final String PATH_LAUNCHER_CONFIG_FILE_SYSTEM = "/system/appstv_data/launcher_config.json";
    public static final String PATH_PREINSTALL_APPS_FILE = "appstv_data/preinstall_apps";
    public static final String PATH_PREINSTALL_APPS_FILE_SYSTEM = "/system/appstv_data/preinstall_apps";
    public static final String PREINSTALL_APPS_FILE_NAME = "preinstall_apps";

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(APPSTV_DATA_DIRECTORY_NAME);
        sb.append(File.separator);
        sb.append(DIR_PREINSTALL);
        DIR_PREINSTALL_EXT = sb.toString();
    }
}
