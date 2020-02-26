package com.excel.digitalsignage;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.excel.excelclasslibrary.UtilFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class ConfigurationWriter {
    static int KEY = 0;
    static final String TAG = "ConfigurationWriter";
    static int VALUE = 1;
    static ConfigurationWriter configurationWriter;
    static String file_md5 = "";
    String cms_ip;
    String cms_sub_directory;
    String collar_text_speed;
    String country;
    String is_ots_completed;
    String is_reboot_scheduled;
    String loading_screen_time;
    String location;
    String protocol;
    String reboot_time;
    String timezone;
    String webservice_path;

    public static ConfigurationWriter getInstance(Context context) {
        String str = TAG;
        if (configurationWriter == null) {
            configurationWriter = new ConfigurationWriter();
            File configuration = new File(configurationWriter.getConfigurationFilePath());
            if (!configuration.exists()) {
                try {
                    configuration.createNewFile();
                    Log.i(str, "Created empty Configuration file !");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e(str, "Configuration file is empty !");
            }
        }
        return configurationWriter;
    }

    private String getConfigurationFilePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append(File.separator);
        sb.append(Constants.PATH_CONFIGURATION_FILE);
        return sb.toString();
    }

    public boolean setTimeZone(String time_zone) {
        return amendConfigurationFile("timezone", time_zone);
    }

    public boolean setLocation(String location2) {
        return amendConfigurationFile("location", location2);
    }

    private boolean amendConfigurationFile(String key, String value) {
        try {
            FileInputStream fis = new FileInputStream(new File(configurationWriter.getConfigurationFilePath()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder configuration_data = new StringBuilder();
            while (true) {
                String readLine = reader.readLine();
                String line = readLine;
                if (readLine != null) {
                    if (line.contains(key)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(key);
                        sb.append("=");
                        sb.append(value);
                        line = sb.toString();
                    }
                    configuration_data.append(line);
                    configuration_data.append("\n");
                } else {
                    UtilFile.saveDataToFile(new File(configurationWriter.getConfigurationFilePath()), configuration_data.toString().trim());
                    reader.close();
                    fis.close();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean writeAllConfigurations(Context context, String json) {
        String str = "collar_text_speed";
        String str2 = "loading_screen_time";
        String str3 = "webservice_path";
        String str4 = "is_ots_completed";
        String str5 = "reboot_time";
        String str6 = "is_reboot_scheduled";
        String str7 = "location";
        String str8 = "protocol";
        String str9 = "cms_sub_directory";
        String str10 = "cms_ip";
        String str11 = "timezone";
        String str12 = "country";
        String str13 = "\n";
        String str14 = "=";
        configurationWriter = getInstance(context);
        File configuration = new File(configurationWriter.getConfigurationFilePath());
        try {
            configuration.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File file = configuration;
            try {
                JSONObject jsonObject = new JSONObject(json);
                String country2 = jsonObject.getString(str12);
                String timezone2 = jsonObject.getString(str11);
                String cms_ip2 = jsonObject.getString(str10);
                String cms_sub_directory2 = jsonObject.getString(str9);
                String protocol2 = jsonObject.getString(str8);
                String location2 = jsonObject.getString(str7);
                String is_reboot_scheduled2 = jsonObject.getString(str6);
                String reboot_time2 = jsonObject.getString(str5);
                String is_ots_completed2 = jsonObject.getString(str4);
                String webservice_path2 = jsonObject.getString(str3);
                String loading_screen_time2 = jsonObject.getString(str2);
                String collar_text_speed2 = jsonObject.getString(str);
                StringBuilder sb = new StringBuilder();
                JSONObject jSONObject = jsonObject;
                StringBuilder sb2 = sb;
                sb2.append(str12);
                sb2.append(str14);
                sb2.append(country2);
                sb2.append(str13);
                sb2.append(str11);
                sb2.append(str14);
                sb2.append(timezone2);
                sb2.append(str13);
                sb2.append(str10);
                sb2.append(str14);
                sb2.append(cms_ip2);
                sb2.append(str13);
                sb2.append(str9);
                sb2.append(str14);
                sb2.append(cms_sub_directory2);
                sb2.append(str13);
                sb2.append(str8);
                sb2.append(str14);
                sb2.append(protocol2);
                sb2.append(str13);
                sb2.append(str7);
                sb2.append(str14);
                sb2.append(location2);
                sb2.append(str13);
                sb2.append(str6);
                sb2.append(str14);
                sb2.append(is_reboot_scheduled2);
                sb2.append(str13);
                sb2.append(str5);
                sb2.append(str14);
                sb2.append(reboot_time2);
                sb2.append(str13);
                sb2.append(str4);
                sb2.append(str14);
                sb2.append(is_ots_completed2);
                sb2.append(str13);
                sb2.append(str3);
                sb2.append(str14);
                String webservice_path3 = webservice_path2;
                sb2.append(webservice_path3);
                sb2.append(str13);
                sb2.append(str2);
                sb2.append(str14);
                String loading_screen_time3 = loading_screen_time2;
                sb2.append(loading_screen_time3);
                sb2.append(str13);
                sb2.append(str);
                sb2.append(str14);
                String collar_text_speed3 = collar_text_speed2;
                sb2.append(collar_text_speed3);
                sb2.append(str13);
                File file2 = new File(configurationWriter.getConfigurationFilePath());
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                FileInputStream fis = new FileInputStream(file2);
                String str15 = collar_text_speed3;
                String str16 = loading_screen_time3;
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                String str17 = webservice_path3;
                UtilFile.saveDataToFile(new File(configurationWriter.getConfigurationFilePath()), sb2.toString().trim());
                reader.close();
                fis.close();
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        } catch (Exception e3) {
            File file3 = configuration;
            String str18 = json;
            e3.printStackTrace();
            return false;
        }
    }
}
