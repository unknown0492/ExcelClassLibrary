package com.excel.configuration;

import com.excel.excelclasslibrary.UtilFile;

/**
 * Created by Sohail on 03-11-2016.
 */

public class LauncherConfigManager {

    final static String TAG = "LauncherConfigManager";

    public boolean writeLauncherConfigFile( String launcher_json ){
        ConfigurationReader configurationReader = ConfigurationReader.getInstance();
        return UtilFile.saveDataToFile( configurationReader.getLauncherConfigFile( false ), launcher_json );

    }



}

