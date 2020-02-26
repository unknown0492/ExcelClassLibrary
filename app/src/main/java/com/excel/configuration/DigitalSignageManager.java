package com.excel.configuration;

import android.content.Context;
import com.excel.excelclasslibrary.UtilFile;
import java.io.File;
import java.io.IOException;

public class DigitalSignageManager {
    public DigitalSignageManager() {
    }

    public DigitalSignageManager(Context context) {
    }

    public static String getDigitalSignageConfig() {
        return UtilFile.readData(new File(getDigitalSignageConfigFilePath()));
    }

    public static String getDigitalSignageConfigFilePath() {
        File appstv_data = new File(ConfigurationWriter.getAppstvDataDirectorypath());
        StringBuilder sb = new StringBuilder();
        sb.append(appstv_data.getAbsolutePath());
        sb.append(File.separator);
        sb.append(Constants.DIGITAL_SIGNAGE_CONFIG_FILE_NAME);
        File digital_signage = new File(sb.toString());
        if (!digital_signage.exists()) {
            try {
                digital_signage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }
        return digital_signage.getAbsolutePath();
    }

    public static boolean writeDigitalSignageConfig(String content) {
        return UtilFile.saveDataToFile(new File(getDigitalSignageConfigFilePath()), content);
    }
}
