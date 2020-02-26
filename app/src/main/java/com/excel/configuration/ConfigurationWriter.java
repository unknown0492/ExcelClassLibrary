package com.excel.configuration;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.excel.excelclasslibrary.UtilFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class ConfigurationWriter {
    static int KEY = 0;
    static final String TAG = "ConfigurationWriter";
    static int VALUE = 1;
    static ConfigurationWriter configurationWriter;
    String clock_weather_flip_interval;
    String cms_ip;
    String cms_sub_directory;
    String country;
    String digital_signage_interval;
    String file_md5;
    String firmware_name;
    String hotspot_enabled;
    String hotspot_password;
    String is_reboot_scheduled;
    String location;
    String protocol;
    String reboot_time;
    String room_no;
    String ssid;
    String timezone;
    String weather_refresh_interval;
    String weather_retry_interval;
    String chromecast_mode_on;

    public static ConfigurationWriter getInstance(Context context) {
        if (configurationWriter == null) {
            configurationWriter = new ConfigurationWriter();
            if (!new File(configurationWriter.getConfigurationFilePath()).exists()) {
                Log.e(TAG, "Configuration file does not exist !");
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

    public static String getAppstvDataDirectorypath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append(File.separator);
        sb.append(Constants.APPSTV_DATA_DIRECTORY_NAME);
        return sb.toString();
    }

    public boolean setRoomNumber(String room_no2) {
        return amendConfigurationFile(ConfigurationReader.ROOM_NO, room_no2);
    }

    public boolean setSSID(String ssid2) {
        return amendConfigurationFile(ConfigurationReader.SSID, ssid2);
    }

    public boolean setHotspotPassword(String hotspot_password2) {
        return amendConfigurationFile(ConfigurationReader.HOTSPOT_PASSWORD, hotspot_password2);
    }

    public boolean setAirplayEnabled(String airplay_enabled) {
        return amendConfigurationFile(ConfigurationReader.AIRPLAY_ENABLED, airplay_enabled);
    }

    public boolean setTimeZone(String time_zone) {
        return amendConfigurationFile("timezone", time_zone);
    }

    public boolean setLocation(String location2) {
        return amendConfigurationFile("location", location2);
    }

    public boolean setDateTimeFlipInterval(String date_time_flip_interval) {
        return amendConfigurationFile(ConfigurationReader.DATE_TIME_FLIP_INTERVAL, date_time_flip_interval);
    }

    public boolean setTetheringInfoFlipInterval(String tethering_info_flip_interval) {
        return amendConfigurationFile(ConfigurationReader.TETHERING_INFO_FLIP_INTERVAL, tethering_info_flip_interval);
    }

    public boolean setHasHotelLogoDisplay(String has_hotel_logo_display) {
        return amendConfigurationFile(ConfigurationReader.HAS_HOTEL_LOGO_DISPLAY, has_hotel_logo_display);
    }

    public boolean setHotelLogoFlipInterval(String hotel_logo_flip_interval) {
        return amendConfigurationFile(ConfigurationReader.HOTEL_LOGO_FLIP_INTERVAL, hotel_logo_flip_interval);
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
        String str = ConfigurationReader.HOTSPOT_ENABLED;
        String str2 = ConfigurationReader.CLOCK_WEATHER_FLIP_INTERVAL;
        String str3 = ConfigurationReader.WEATHER_REFRESH_INTERVAL;
        String str4 = ConfigurationReader.WEATHER_RETRY_INTERVAL;
        String str5 = ConfigurationReader.DIGITAL_SIGNAGE_INTERVAL;
        String str6 = "reboot_time";
        String str7 = "is_reboot_scheduled";
        String str8 = "location";
        String str9 = "protocol";
        String str10 = "cms_sub_directory";
        String str11 = "cms_ip";
        String str12 = "timezone";
        String str13 = "country";
        String str14 = "\n";
        String str15 = "=";
        configurationWriter = getInstance(context);
        try {
            String str16 = str14;
            JSONObject jsonObject = new JSONObject(json);
            String country2 = jsonObject.getString(str13);
            String timezone2 = jsonObject.getString(str12);
            String cms_ip2 = jsonObject.getString(str11);
            String cms_sub_directory2 = jsonObject.getString(str10);
            String protocol2 = jsonObject.getString(str9);
            String location2 = jsonObject.getString(str8);
            String is_reboot_scheduled2 = jsonObject.getString(str7);
            String reboot_time2 = jsonObject.getString(str6);
            String digital_signage_interval2 = jsonObject.getString(str5);
            String weather_retry_interval2 = jsonObject.getString(str4);
            String weather_refresh_interval2 = jsonObject.getString(str3);
            String clock_weather_flip_interval2 = jsonObject.getString(str2);
            String hotspot_enabled2 = jsonObject.getString(str);
            String ssid2 = jsonObject.getString(ConfigurationReader.SSID);
            String hotspot_password2 = jsonObject.getString(ConfigurationReader.HOTSPOT_PASSWORD);
            String room_no2 = jsonObject.getString(ConfigurationReader.ROOM_NO);
            String is_ots_completed = jsonObject.getString("is_ots_completed");
            String language = jsonObject.getString(ConfigurationReader.LANGUAGE);
            String webservice_path = jsonObject.getString("webservice_path");
            String airplay_enabled = jsonObject.getString(ConfigurationReader.AIRPLAY_ENABLED);
            String date_time_flip_interval = jsonObject.getString(ConfigurationReader.DATE_TIME_FLIP_INTERVAL);
            String tethering_info_flip_interval = jsonObject.getString(ConfigurationReader.TETHERING_INFO_FLIP_INTERVAL);
            String has_hotel_logo_display = jsonObject.getString(ConfigurationReader.HAS_HOTEL_LOGO_DISPLAY);
            String hotel_logo_flip_interval = jsonObject.getString(ConfigurationReader.HOTEL_LOGO_FLIP_INTERVAL);
            String is_airplay_ssid_same_as_tethering_ssid = jsonObject.getString(ConfigurationReader.IS_AIRPLAY_SSID_SAME_AS_TETHERING_SSID);
            String airplay_ssid = jsonObject.getString(ConfigurationReader.AIRPLAY_SSID);
            String airplay_password = jsonObject.getString(ConfigurationReader.AIRPLAY_PASSWORD);
            String loading_screen_time = jsonObject.getString("loading_screen_time");
            String welcome_screen_type = jsonObject.getString(ConfigurationReader.WELCOME_SCREEN_TYPE);
            String collar_text_speed = jsonObject.getString("collar_text_speed");
            String idle_timeout_interval = jsonObject.getString(ConfigurationReader.IDLE_TIMEOUT_INTERVAL);
            String is_weather_enabled = jsonObject.getString(ConfigurationReader.IS_WEATHER_ENABLED);
            String is_welcome_screen_enabled = jsonObject.getString(ConfigurationReader.IS_WELCOME_SCREEN_ENABLED);
            String chromecast_mode_on = jsonObject.getString(ConfigurationReader.CHROMECAST_MODE_ON);
            String hotspot_security = jsonObject.getString(ConfigurationReader.HOTSPOT_SECURITY);
            StringBuilder sb = new StringBuilder();
            JSONObject jSONObject = jsonObject;
            StringBuilder sb2 = sb;
            sb2.append(str13);
            sb2.append(str15);
            String country3 = country2;
            sb2.append(country3);
            String str17 = country3;
            String country4 = str16;
            sb2.append(country4);
            sb2.append(str12);
            sb2.append(str15);
            sb2.append(timezone2);
            sb2.append(country4);
            sb2.append(str11);
            sb2.append(str15);
            sb2.append(cms_ip2);
            sb2.append(country4);
            sb2.append(str10);
            sb2.append(str15);
            sb2.append(cms_sub_directory2);
            sb2.append(country4);
            sb2.append(str9);
            sb2.append(str15);
            sb2.append(protocol2);
            sb2.append(country4);
            sb2.append(str8);
            sb2.append(str15);
            sb2.append(location2);
            sb2.append(country4);
            sb2.append(str7);
            sb2.append(str15);
            sb2.append(is_reboot_scheduled2);
            sb2.append(country4);
            sb2.append(str6);
            sb2.append(str15);
            sb2.append(reboot_time2);
            sb2.append(country4);
            sb2.append(str5);
            sb2.append(str15);
            sb2.append(digital_signage_interval2);
            sb2.append(country4);
            sb2.append(str4);
            sb2.append(str15);
            sb2.append(weather_retry_interval2);
            sb2.append(country4);
            sb2.append(str3);
            sb2.append(str15);
            sb2.append(weather_refresh_interval2);
            sb2.append(country4);
            sb2.append(str2);
            sb2.append(str15);
            sb2.append(clock_weather_flip_interval2);
            sb2.append(country4);
            sb2.append(str);
            sb2.append(str15);
            String hotspot_enabled3 = hotspot_enabled2;
            sb2.append(hotspot_enabled3);
            sb2.append(country4);
            String str18 = hotspot_enabled3;
            sb2.append(ConfigurationReader.SSID);
            sb2.append(str15);
            String ssid3 = ssid2;
            sb2.append(ssid3);
            sb2.append(country4);
            String str19 = ssid3;
            sb2.append(ConfigurationReader.HOTSPOT_PASSWORD);
            sb2.append(str15);
            String hotspot_password3 = hotspot_password2;
            sb2.append(hotspot_password3);
            sb2.append(country4);
            String str20 = hotspot_password3;
            sb2.append(ConfigurationReader.ROOM_NO);
            sb2.append(str15);
            String room_no3 = room_no2;
            sb2.append(room_no3);
            sb2.append(country4);
            String str21 = room_no3;
            sb2.append("is_ots_completed");
            sb2.append(str15);
            String is_ots_completed2 = is_ots_completed;
            sb2.append(is_ots_completed2);
            sb2.append(country4);
            String str22 = is_ots_completed2;
            sb2.append(ConfigurationReader.LANGUAGE);
            sb2.append(str15);
            String language2 = language;
            sb2.append(language2);
            sb2.append(country4);
            String str23 = language2;
            sb2.append("webservice_path");
            sb2.append(str15);
            String webservice_path2 = webservice_path;
            sb2.append(webservice_path2);
            sb2.append(country4);
            String str24 = webservice_path2;
            sb2.append(ConfigurationReader.AIRPLAY_ENABLED);
            sb2.append(str15);
            String airplay_enabled2 = airplay_enabled;
            sb2.append(airplay_enabled2);
            sb2.append(country4);
            String str25 = airplay_enabled2;
            sb2.append(ConfigurationReader.DATE_TIME_FLIP_INTERVAL);
            sb2.append(str15);
            String date_time_flip_interval2 = date_time_flip_interval;
            sb2.append(date_time_flip_interval2);
            sb2.append(country4);
            String str26 = date_time_flip_interval2;
            sb2.append(ConfigurationReader.TETHERING_INFO_FLIP_INTERVAL);
            sb2.append(str15);
            String tethering_info_flip_interval2 = tethering_info_flip_interval;
            sb2.append(tethering_info_flip_interval2);
            sb2.append(country4);
            String str27 = tethering_info_flip_interval2;
            sb2.append(ConfigurationReader.HAS_HOTEL_LOGO_DISPLAY);
            sb2.append(str15);
            String has_hotel_logo_display2 = has_hotel_logo_display;
            sb2.append(has_hotel_logo_display2);
            sb2.append(country4);
            String str28 = has_hotel_logo_display2;
            sb2.append(ConfigurationReader.HOTEL_LOGO_FLIP_INTERVAL);
            sb2.append(str15);
            String hotel_logo_flip_interval2 = hotel_logo_flip_interval;
            sb2.append(hotel_logo_flip_interval2);
            sb2.append(country4);
            String str29 = hotel_logo_flip_interval2;
            sb2.append(ConfigurationReader.IS_AIRPLAY_SSID_SAME_AS_TETHERING_SSID);
            sb2.append(str15);
            String is_airplay_ssid_same_as_tethering_ssid2 = is_airplay_ssid_same_as_tethering_ssid;
            sb2.append(is_airplay_ssid_same_as_tethering_ssid2);
            sb2.append(country4);
            String str30 = is_airplay_ssid_same_as_tethering_ssid2;
            sb2.append(ConfigurationReader.AIRPLAY_SSID);
            sb2.append(str15);
            String airplay_ssid2 = airplay_ssid;
            sb2.append(airplay_ssid2);
            sb2.append(country4);
            String str31 = airplay_ssid2;
            sb2.append(ConfigurationReader.AIRPLAY_PASSWORD);
            sb2.append(str15);
            String airplay_password2 = airplay_password;
            sb2.append(airplay_password2);
            sb2.append(country4);
            String str32 = airplay_password2;
            sb2.append("loading_screen_time");
            sb2.append(str15);
            String loading_screen_time2 = loading_screen_time;
            sb2.append(loading_screen_time2);
            sb2.append(country4);
            String str33 = loading_screen_time2;
            sb2.append(ConfigurationReader.WELCOME_SCREEN_TYPE);
            sb2.append(str15);
            String welcome_screen_type2 = welcome_screen_type;
            sb2.append(welcome_screen_type2);
            sb2.append(country4);
            String str34 = welcome_screen_type2;
            sb2.append("collar_text_speed");
            sb2.append(str15);
            String collar_text_speed2 = collar_text_speed;
            sb2.append(collar_text_speed2);
            sb2.append(country4);
            String str35 = collar_text_speed2;
            sb2.append(ConfigurationReader.IDLE_TIMEOUT_INTERVAL);
            sb2.append(str15);
            String idle_timeout_interval2 = idle_timeout_interval;
            sb2.append(idle_timeout_interval2);
            sb2.append(country4);
            String str36 = idle_timeout_interval2;
            sb2.append(ConfigurationReader.IS_WEATHER_ENABLED);
            sb2.append(str15);
            String is_weather_enabled2 = is_weather_enabled;
            sb2.append(is_weather_enabled2);
            sb2.append(country4);
            String str37 = is_weather_enabled2;
            sb2.append(ConfigurationReader.IS_WELCOME_SCREEN_ENABLED);
            sb2.append(str15);
            String is_welcome_screen_enabled2 = is_welcome_screen_enabled;
            sb2.append(is_welcome_screen_enabled2);
            sb2.append(country4);
            String str38 = is_welcome_screen_enabled2;
            sb2.append(ConfigurationReader.CHROMECAST_MODE_ON);
            sb2.append(str15);
            sb2.append(chromecast_mode_on);
            sb2.append(country4);
            sb2.append(ConfigurationReader.HOTSPOT_SECURITY + "=" + hotspot_security + "\n");
            File file = new File(configurationWriter.getConfigurationFilePath());
            if (!file.exists()) {
                file.createNewFile();
            }
            UtilFile.saveDataToFile(file, sb2.toString().trim());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
