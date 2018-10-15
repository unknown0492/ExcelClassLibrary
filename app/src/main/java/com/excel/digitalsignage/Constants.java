package com.excel.digitalsignage;

import java.io.File;

/**
 * Created by Sohail on 01-09-2016.
 */
public class Constants {
    public final static String DS_DATA_DIRECTORY_NAME = "ds_data";

    public final static String CONFIGURATION_FILE_NAME = "configuration";
    public final static String PATH_CONFIGURATION_FILE = DS_DATA_DIRECTORY_NAME + "/" + CONFIGURATION_FILE_NAME;
    public final static String PATH_CONFIGURATION_FILE_SYSTEM = "/system/"+ DS_DATA_DIRECTORY_NAME +"/" + CONFIGURATION_FILE_NAME;

    public final static String DIR_GRAPHICS = DS_DATA_DIRECTORY_NAME + "/graphics";
    public final static String DIR_IMAGES = DS_DATA_DIRECTORY_NAME + "/graphics/images";
    public final static String DIR_VIDEOS = DS_DATA_DIRECTORY_NAME + "/graphics/videos";
    public final static String DIR_FIRMWARE = DS_DATA_DIRECTORY_NAME + "/firmware";

    public final static String TEMPLATE_FILE_NAME = "template";

    // GLOBAL SHELL VARIABLES
    public final static String SYNC_IN_PROGRESS = "sync_in_progress";
    public final static String CONTENT_DOWNLOAD_STARTED = "cntnt_dwnld_strtd";

}