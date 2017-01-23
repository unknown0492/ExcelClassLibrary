package com.excel.configuration;

import com.excel.excelclasslibrary.UtilFile;

import static com.excel.configuration.Constants.APPSTV_DATA_DIRECTORY_NAME;
import static com.excel.configuration.Constants.LAUNCHER_CONFIG_JSON_FILE_NAME;

/**
 * Created by Sohail on 03-11-2016.
 */

public class LauncherConfigManager {

    final static String TAG = "LauncherConfigManager";

    public boolean writeLauncherConfigFile( String launcher_json ){

        return UtilFile.saveFile( APPSTV_DATA_DIRECTORY_NAME, LAUNCHER_CONFIG_JSON_FILE_NAME, "", launcher_json.getBytes() );

    }



}

