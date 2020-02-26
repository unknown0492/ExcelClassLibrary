package com.excel.configuration;

import com.excel.excelclasslibrary.UtilFile;

public class LauncherConfigManager {
    static final String TAG = "LauncherConfigManager";

    public boolean writeLauncherConfigFile(String launcher_json) {
        return UtilFile.saveDataToFile(ConfigurationReader.getInstance().getLauncherConfigFile(false), launcher_json);
    }
}
