package com.excel.configuration;

import java.io.File;

/**
 * Created by Sohail on 01-09-2016.
 */
public class Constants {
    public final static String APPSTV_DATA_DIRECTORY_NAME = "appstv_data";

    public final static String CONFIGURATION_FILE_NAME = "configuration";
    public final static String PATH_CONFIGURATION_FILE = APPSTV_DATA_DIRECTORY_NAME + "/" + CONFIGURATION_FILE_NAME;
    public final static String PATH_CONFIGURATION_FILE_SYSTEM = "/system/"+ APPSTV_DATA_DIRECTORY_NAME +"/" + CONFIGURATION_FILE_NAME;

    public final static String LAUNCHER_CONFIG_JSON_FILE_NAME = "launcher_config.json";
    public final static String PATH_LAUNCHER_CONFIG_FILE = APPSTV_DATA_DIRECTORY_NAME + "/" + LAUNCHER_CONFIG_JSON_FILE_NAME;
    public final static String PATH_LAUNCHER_CONFIG_FILE_SYSTEM = "/system/"+ APPSTV_DATA_DIRECTORY_NAME +"/" + LAUNCHER_CONFIG_JSON_FILE_NAME;

    public final static String PREINSTALL_APPS_FILE_NAME = "preinstall_apps";
    public final static String PATH_PREINSTALL_APPS_FILE = APPSTV_DATA_DIRECTORY_NAME + "/" + PREINSTALL_APPS_FILE_NAME;
    public final static String PATH_PREINSTALL_APPS_FILE_SYSTEM = "/system/"+APPSTV_DATA_DIRECTORY_NAME+"/" + PREINSTALL_APPS_FILE_NAME;

    public final static String DIR_DIGITAL_SIGNAGE = APPSTV_DATA_DIRECTORY_NAME + "/graphics/digital_signage";
    public final static String DIR_HOTEL_LOGO = APPSTV_DATA_DIRECTORY_NAME + "/graphics/hotel_logo";
    public final static String DIR_TV_CHANNELS_FILE = APPSTV_DATA_DIRECTORY_NAME + "/tv_channels";
    public final static String DIR_PREINSTALL = "preinstall";
    public final static String DIR_PREINSTALL_EXT = APPSTV_DATA_DIRECTORY_NAME + File.separator + DIR_PREINSTALL;
    public final static String DIR_PREINSTALL_SYSTEM = "/system/" + DIR_PREINSTALL;
    public final static String DIR_FIRMWARE = APPSTV_DATA_DIRECTORY_NAME + "/firmware";

    public final static String DIGITAL_SIGNAGE_CONFIG_FILE_NAME = "digital_signage";

    // Global Database containing all the data migrated from the raw text file
    final public static String APPSTV_DB_NAME 	= "appstv_data.db";



}

