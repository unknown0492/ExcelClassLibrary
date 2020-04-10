package com.excel.configuration;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import com.excel.excelclasslibrary.UtilShell;
import com.excel.util.MD5;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ConfigurationReader {
    public static final String AIRPLAY_ENABLED = "airplay_enabled";
    public static final String AIRPLAY_PASSWORD = "airplay_password";
    public static final String AIRPLAY_SSID = "airplay_ssid";
    public static final String CHROMECAST_MODE_ON = "chromecast_mode_on";
    public static final String CLOCK_WEATHER_FLIP_INTERVAL = "clock_weather_flip_interval";
    public static final String CMS_IP = "cms_ip";
    public static final String CMS_SUB_DIRECTORY = "cms_sub_directory";
    public static final String COLLAR_TEXT_SPEED = "collar_text_speed";
    public static final String COUNTRY = "country";
    public static final String DATE_TIME_FLIP_INTERVAL = "date_time_flip_interval";
    public static final String DIGITAL_SIGNAGE_INTERVAL = "digital_signage_interval";
    public static final String FIRMWARE_NAME = "firmware_name";
    public static final String HAS_HOTEL_LOGO_DISPLAY = "has_hotel_logo_display";
    public static final String HAS_RANDOM_HOTSPOT_PASSWORD = "has_random_hotspot_password";
    public static final String HOTEL_LOGO_FLIP_INTERVAL = "hotel_logo_flip_interval";
    public static final String HOTSPOT_ENABLED = "hotspot_enabled";
    public static final String HOTSPOT_PASSWORD = "hotspot_password";
    public static final String IDLE_TIMEOUT_INTERVAL = "idle_timeout_interval";
    public static final String IS_AIRPLAY_SSID_SAME_AS_TETHERING_SSID = "is_airplay_ssid_same_as_tethering_ssid";
    public static final String IS_OTS_COMPLETED = "is_ots_completed";
    public static final String IS_REBOOT_SCHEDULED = "is_reboot_scheduled";
    public static final String IS_WEATHER_ENABLED = "is_weather_enabled";
    public static final String IS_WELCOME_SCREEN_ENABLED = "is_welcome_screen_enabled";
    static int KEY = 0;
    public static final String LANGUAGE = "language";
    public static final String LOADING_SCREEN_TIME = "loading_screen_time";
    public static final String LOCATION = "location";
    public static final String PROTOCOL = "protocol";
    public static final String REBOOT_TIME = "reboot_time";
    public static final String ROOM_NO = "room_no";
    public static final String SSID = "ssid";
    public static final String TAG = "ConfigurationReader";
    public static final String TETHERING_INFO_FLIP_INTERVAL = "tethering_info_flip_interval";
    public static final String TIMEZONE = "timezone";
    static int VALUE = 1;
    public static final String WEATHER_REFRESH_INTERVAL = "weather_refresh_interval";
    public static final String WEATHER_RETRY_INTERVAL = "weather_retry_interval";
    public static final String WEBSERVICE_PATH = "webservice_path";
    public static final String WELCOME_SCREEN_TYPE = "welcome_screen_type";
    public static final String HOTSPOT_SECURITY = "hotspot_security";
    public static final String AUTO_OTS_ENABLED = "auto_ots_enabled";

    static volatile ConfigurationReader configurationReader = null;
    static String file_md5 = "";
    public static SQLiteDatabase sqldb;
    String airplay_enabled;
    String airplay_password;
    String airplay_ssid;
    String chromecast_mode_on = "0";
    String clock_weather_flip_interval;
    String cms_ip;
    String cms_sub_directory;
    String collar_text_speed;
    String country;
    String date_time_flip_interval;
    String digital_signage_interval;
    String firmware_name;
    String has_hotel_logo_display;
    String has_random_hotspot_password;
    String hotel_logo_flip_interval;
    String hotspot_enabled;
    String hotspot_password;
    String idle_timeout_interval;
    String is_airplay_ssid_same_as_tethering_ssid;
    String is_ots_completed;
    String is_reboot_scheduled;
    String is_weather_enabled;
    String is_welcome_screen_enabled;
    String language;
    String loading_screen_time;
    String location;
    String protocol;
    String reboot_time;
    String room_no;
    String ssid;
    String tethering_info_flip_interval;
    String timezone;
    String weather_refresh_interval;
    String weather_retry_interval;
    String web_service_url;
    String webservice_path;
    String welcome_screen_type;
    String hotspot_security;
    String auto_ots_enabled;

    public ConfigurationReader() {
        String str = "1";
        this.is_weather_enabled = str;
        this.is_welcome_screen_enabled = str;
        this.has_random_hotspot_password = str;
    }

    public String getCollarTextSpeed() {
        return this.collar_text_speed;
    }

    public void setCollarTextSpeed(String collar_text_speed2) {
        this.collar_text_speed = collar_text_speed2;
    }

    public String getIdleTimeoutInterval() {
        return this.idle_timeout_interval;
    }

    public void setIdleTimeoutInterval(String idle_timeout_interval2) {
        this.idle_timeout_interval = idle_timeout_interval2;
    }

    public File getPreinstallAppsFile(boolean is_system) {
        if (is_system) {
            return new File(Constants.PATH_PREINSTALL_APPS_FILE_SYSTEM);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append(Constants.PATH_PREINSTALL_APPS_FILE);
        return new File(sb.toString());
    }

    public File getConfigurationFile(boolean is_system) {
        if (is_system) {
            return new File(Constants.PATH_CONFIGURATION_FILE_SYSTEM);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append(Constants.PATH_CONFIGURATION_FILE);
        return new File(sb.toString());
    }

    public File getLauncherConfigFile(boolean is_system) {
        if (is_system) {
            return new File(Constants.PATH_LAUNCHER_CONFIG_FILE_SYSTEM);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append(Constants.PATH_LAUNCHER_CONFIG_FILE);
        return new File(sb.toString());
    }

    public String getDigitalSignageDirectoryPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append(File.separator);
        sb.append(Constants.DIR_DIGITAL_SIGNAGE);
        return new File(sb.toString()).getAbsolutePath();
    }

    public String getRatingsThumbnailDirecotryPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append(File.separator);
        sb.append(Constants.DIR_RATINGS_THUMBNAIL);
        return new File(sb.toString()).getAbsolutePath();
    }

    public String getHotelLogoDirectoryPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append(File.separator);
        sb.append(Constants.DIR_HOTEL_LOGO);
        return new File(sb.toString()).getAbsolutePath();
    }

    public String getTvChannelsDirectoryPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append(File.separator);
        sb.append(Constants.DIR_TV_CHANNELS_FILE);
        return new File(sb.toString()).getAbsolutePath();
    }

    public String getPreinstallApksDirectoryPath(boolean is_system) {
        File file;
        if (is_system) {
            file = new File(Constants.DIR_PREINSTALL_SYSTEM);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(Environment.getExternalStorageDirectory());
            sb.append(File.separator);
            sb.append(Constants.DIR_PREINSTALL_EXT);
            file = new File(sb.toString());
        }
        return file.getAbsolutePath();
    }

    public String getFirmwareDirectoryPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append(File.separator);
        sb.append(Constants.DIR_FIRMWARE);
        return new File(sb.toString()).getAbsolutePath();
    }

    public static ConfigurationReader getInstance() {
        String str = "ConfigurationReader";
        if (configurationReader == null) {
            configurationReader = new ConfigurationReader();
            StringBuilder sb = new StringBuilder();
            sb.append(Environment.getExternalStorageDirectory());
            sb.append(File.separator);
            sb.append(Constants.PATH_CONFIGURATION_FILE);
            String configuration_file_path = sb.toString();
            Log.d(str, configuration_file_path);
            File configuration = new File(configuration_file_path);
            if (!configuration.exists()) {
                configuration = new File(Constants.PATH_CONFIGURATION_FILE_SYSTEM);
            }
            if (getFileMD5().equals("no_md5") || getFileMD5().equals("")) {
                setFileMD5(configuration);
            }
            processConfigurationData(getConfigurationFileData(configuration));
            Log.i(str, "Giving new ConfigurationReader");
        } else {
            Log.d(str, "Giving same ConfigurationReader");
        }
        return configurationReader;
    }

    public static ConfigurationReader reInstantiate() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append(File.separator);
        sb.append(Constants.PATH_CONFIGURATION_FILE);
        String configuration_file_path = sb.toString();
        Log.d("ConfigurationReader", configuration_file_path);
        File configuration = new File(configuration_file_path);
        if (!configuration.exists()) {
            configuration = new File(Constants.PATH_CONFIGURATION_FILE_SYSTEM);
        }
        if (getFileMD5().equals(getNewFileMD5(configuration))) {
            return getInstance();
        }
        setFileMD5(configuration);
        configurationReader = null;
        return getInstance();
    }

    public static String getConfigurationFileData(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder configuration_data = new StringBuilder();
            while (true) {
                String readLine = reader.readLine();
                String line = readLine;
                if (readLine != null) {
                    configuration_data.append(line);
                    configuration_data.append("\n");
                } else {
                    reader.close();
                    fis.close();
                    return configuration_data.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void processConfigurationData(String data) {
        String[] lines = data.split("\n");
        for (String split : lines) {
            String[] line = split.split("=");
            if (line.length < 2) {
                line = new String[]{line[0], ""};
            }
            if (line[KEY].equals("country")) {
                configurationReader.setCountry(line[VALUE]);
            } else if (line[KEY].equals("timezone")) {
                configurationReader.setTimezone(line[VALUE]);
            } else if (line[KEY].equals("cms_ip")) {
                configurationReader.setCmsIp(line[VALUE]);
            } else if (line[KEY].equals("location")) {
                configurationReader.setLocation(line[VALUE]);
            } else if (line[KEY].equals("firmware_name")) {
                configurationReader.setFirmwareName(line[VALUE]);
            } else if (line[KEY].equals("is_reboot_scheduled")) {
                configurationReader.setIsRebootScheduled(line[VALUE]);
            } else if (line[KEY].equals("reboot_time")) {
                configurationReader.setRebootTime(line[VALUE]);
            } else if (line[KEY].equals(DIGITAL_SIGNAGE_INTERVAL)) {
                configurationReader.setDigitalSignageInterval(line[VALUE]);
            } else if (line[KEY].equals(WEATHER_RETRY_INTERVAL)) {
                configurationReader.setWeatherRetryInterval(line[VALUE]);
            } else if (line[KEY].equals(WEATHER_REFRESH_INTERVAL)) {
                configurationReader.setWeatherRefreshInterval(line[VALUE]);
            } else if (line[KEY].equals(CLOCK_WEATHER_FLIP_INTERVAL)) {
                configurationReader.setClockWeatherFlipInterval(line[VALUE]);
            } else if (line[KEY].equals("cms_sub_directory")) {
                configurationReader.setCmsSubDirectory(line[VALUE]);
            } else if (line[KEY].equals("protocol")) {
                configurationReader.setProtocol(line[VALUE]);
            } else if (line[KEY].equals(HOTSPOT_ENABLED)) {
                configurationReader.setHotspotEnabled(line[VALUE]);
            } else if (line[KEY].equals(SSID)) {
                configurationReader.setSSID(line[VALUE]);
            } else if (line[KEY].equals(HOTSPOT_PASSWORD)) {
                configurationReader.setHotspotPassword(line[VALUE]);
            } else if (line[KEY].equals(ROOM_NO)) {
                configurationReader.setRoomNo(line[VALUE]);
            } else if (line[KEY].equals("is_ots_completed")) {
                configurationReader.setIsOtsCompleted(line[VALUE]);
            } else if (line[KEY].equals(LANGUAGE)) {
                configurationReader.setLanguage(line[VALUE]);
            } else if (line[KEY].equals("webservice_path")) {
                configurationReader.setWebservicePath(line[VALUE]);
            } else if (line[KEY].equals(AIRPLAY_ENABLED)) {
                configurationReader.setAirplayEnabled(line[VALUE]);
            } else if (line[KEY].equals(DATE_TIME_FLIP_INTERVAL)) {
                configurationReader.setDateTimeFlipInterval(line[VALUE]);
            } else if (line[KEY].equals(TETHERING_INFO_FLIP_INTERVAL)) {
                configurationReader.setTetheringInfoFlipInterval(line[VALUE]);
            } else if (line[KEY].equals(HOTEL_LOGO_FLIP_INTERVAL)) {
                configurationReader.setHotelLogoFlipInterval(line[VALUE]);
            } else if (line[KEY].equals(HAS_HOTEL_LOGO_DISPLAY)) {
                configurationReader.setHasHotelLogoDisplay(line[VALUE]);
            } else if (line[KEY].equals(IS_AIRPLAY_SSID_SAME_AS_TETHERING_SSID)) {
                configurationReader.setIsAirplaySSIDSameAsTetheringSSID(line[VALUE]);
            } else if (line[KEY].equals(AIRPLAY_PASSWORD)) {
                configurationReader.setAirplayPassword(line[VALUE]);
            } else if (line[KEY].equals(AIRPLAY_SSID)) {
                configurationReader.setAirplaySSID(line[VALUE]);
            } else if (line[KEY].equals("loading_screen_time")) {
                configurationReader.setLoadingScreenTime(line[VALUE]);
            } else if (line[KEY].equals(WELCOME_SCREEN_TYPE)) {
                configurationReader.setWelcomeScreenType(line[VALUE]);
            } else if (line[KEY].equals("collar_text_speed")) {
                configurationReader.setCollarTextSpeed(line[VALUE]);
            } else if (line[KEY].equals(IDLE_TIMEOUT_INTERVAL)) {
                configurationReader.setIdleTimeoutInterval(line[VALUE]);
            } else if (line[KEY].equals(IS_WEATHER_ENABLED)) {
                configurationReader.setIsWeatherEnabled(line[VALUE]);
            } else if (line[KEY].equals(IS_WELCOME_SCREEN_ENABLED)) {
                configurationReader.setIsWelcomeScreenEnabled(line[VALUE]);
            } else if (line[KEY].equals(HAS_RANDOM_HOTSPOT_PASSWORD)) {
                configurationReader.setHasRandomHotspotPassword(line[VALUE]);
            } else if (line[KEY].equals(CHROMECAST_MODE_ON)) {
                configurationReader.setChromecastModeOn(line[VALUE]);
            } else if (line[KEY].equals(HOTSPOT_SECURITY)) {
                configurationReader.setHotspotSecurity(line[VALUE]);
            } else if (line[KEY].equals(AUTO_OTS_ENABLED)) {
                configurationReader.setAutoOtsEnabled(line[VALUE]);
            }
        }
    }

    public static String getAppstvDataRootDirectoryPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append(Constants.APPSTV_DATA_DIRECTORY_NAME);
        return new File(sb.toString()).getAbsolutePath();
    }

    public static String getAppstvDatabasePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getAppstvDataRootDirectoryPath());
        sb.append(File.separator);
        sb.append(Constants.APPSTV_DB_NAME);
        return sb.toString();
    }

    public SQLiteDatabase getAppstvDatabase() {
        return sqldb;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country2) {
        this.country = country2;
    }

    public String getTimezone() {
        return this.timezone;
    }

    public void setTimezone(String timezone2) {
        this.timezone = timezone2;
    }

    public String getCmsIp() {
        return this.cms_ip;
    }

    public void setCmsIp(String cms_ip2) {
        this.cms_ip = cms_ip2;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location2) {
        this.location = location2;
    }

    public String getFirmwareName() {
        return UtilShell.executeShellCommandWithOp("getprop ro.build.display.id").trim();
    }

    public void setFirmwareName(String firmware_name2) {
        this.firmware_name = firmware_name2;
    }

    public String getIsRebootScheduled() {
        return this.is_reboot_scheduled;
    }

    public void setIsRebootScheduled(String is_reboot_scheduled2) {
        this.is_reboot_scheduled = is_reboot_scheduled2;
    }

    public String getRebootTime() {
        return this.reboot_time;
    }

    public void setRebootTime(String reboot_time2) {
        this.reboot_time = reboot_time2;
    }

    public String getDigitalSignageInterval() {
        return this.digital_signage_interval;
    }

    public void setDigitalSignageInterval(String digital_signage_interval2) {
        this.digital_signage_interval = digital_signage_interval2;
    }

    public String getWeatherRetryInterval() {
        return this.weather_retry_interval;
    }

    public void setWeatherRetryInterval(String weather_retry_interval2) {
        this.weather_retry_interval = weather_retry_interval2;
    }

    public String getWeatherRefreshInterval() {
        return this.weather_refresh_interval;
    }

    public void setWeatherRefreshInterval(String weather_refresh_interval2) {
        this.weather_refresh_interval = weather_refresh_interval2;
    }

    public String getClockWeatherFlipInterval() {
        return this.clock_weather_flip_interval;
    }

    public void setClockWeatherFlipInterval(String clock_weather_flip_interval2) {
        this.clock_weather_flip_interval = clock_weather_flip_interval2;
    }

    public String getCmsSubDirectory() {
        return this.cms_sub_directory;
    }

    public void setCmsSubDirectory(String cms_sub_directory2) {
        this.cms_sub_directory = cms_sub_directory2;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public void setProtocol(String protocol2) {
        this.protocol = protocol2;
    }

    public String getHotspotEnabled() {
        return this.hotspot_enabled;
    }

    public void setHotspotEnabled(String hotspot_enabled2) {
        this.hotspot_enabled = hotspot_enabled2;
    }

    public String getSSID() {
        return this.ssid;
    }

    public void setSSID(String ssid2) {
        this.ssid = ssid2;
    }

    public String getHotspotPassword() {
        return this.hotspot_password;
    }

    public void setHotspotPassword(String hotspot_password2) {
        this.hotspot_password = hotspot_password2;
    }

    public String getRoomNo() {
        return this.room_no;
    }

    public void setRoomNo(String room_no2) {
        this.room_no = room_no2;
    }

    public String getAirplayEnabled() {
        return this.airplay_enabled;
    }

    public void setAirplayEnabled(String airplay_enabled2) {
        this.airplay_enabled = airplay_enabled2;
    }

    public void setDateTimeFlipInterval(String date_time_flip_interval2) {
        this.date_time_flip_interval = date_time_flip_interval2;
    }

    public String getDateTimeFlipInterval() {
        return this.date_time_flip_interval;
    }

    public void setTetheringInfoFlipInterval(String tethering_info_flip_interval2) {
        this.tethering_info_flip_interval = tethering_info_flip_interval2;
    }

    public void setIsOtsCompleted(String is_ots_completed2) {
        this.is_ots_completed = is_ots_completed2;
    }

    public String getIsOtsCompleted() {
        return this.is_ots_completed;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language2) {
        this.language = language2;
    }

    public String getWebservicePath() {
        return this.webservice_path;
    }

    public void setWebservicePath(String webservice_path2) {
        this.webservice_path = webservice_path2;
    }

    public String getTetheringInfoFlipInterval() {
        return this.tethering_info_flip_interval;
    }

    public void setHasHotelLogoDisplay(String has_hotel_logo_display2) {
        this.has_hotel_logo_display = has_hotel_logo_display2;
    }

    public String getHasHotelLogoDisplay() {
        return this.has_hotel_logo_display;
    }

    public void setHotelLogoFlipInterval(String hotel_logo_flip_interval2) {
        this.hotel_logo_flip_interval = hotel_logo_flip_interval2;
    }

    public String getHotelLogoFlipInterval() {
        return this.hotel_logo_flip_interval;
    }

    public void setIsAirplaySSIDSameAsTetheringSSID(String is_airplay_ssid_same_as_tethering_ssid2) {
        this.is_airplay_ssid_same_as_tethering_ssid = is_airplay_ssid_same_as_tethering_ssid2;
    }

    public String getIsAirplaySSIDSameAsTetheringSSID() {
        return this.is_airplay_ssid_same_as_tethering_ssid;
    }

    public void setAirplaySSID(String airplay_ssid2) {
        this.airplay_ssid = airplay_ssid2;
    }

    public String getAirplaySSID() {
        return this.airplay_ssid;
    }

    public void setAirplayPassword(String airplay_password2) {
        this.airplay_password = airplay_password2;
    }

    public String getAirplayPassword() {
        return this.airplay_password;
    }

    public void setLoadingScreenTime(String loading_screen_time2) {
        this.loading_screen_time = loading_screen_time2;
    }

    public String getLoadingScreenTime() {
        return this.loading_screen_time;
    }

    public void setWelcomeScreenType(String welcome_screen_type2) {
        this.welcome_screen_type = welcome_screen_type2;
    }

    public String getWelcomeScreenType() {
        return this.welcome_screen_type;
    }

    public static void setFileMD5(File config_file_path) {
        try {
            file_md5 = MD5.getMD5Checksum(config_file_path);
        } catch (Exception e) {
            e.printStackTrace();
            file_md5 = "no_md5";
        }
    }

    public static String getFileMD5() {
        return file_md5;
    }

    public static String getNewFileMD5(File file_path) {
        try {
            return MD5.getMD5Checksum(file_path);
        } catch (Exception e) {
            e.printStackTrace();
            return "no_md5";
        }
    }

    public boolean getIsWelcomeScreenEnabled() {
        return this.is_welcome_screen_enabled.equals("1");
    }

    public void setIsWelcomeScreenEnabled(String is_welcome_screen_enabled2) {
        this.is_welcome_screen_enabled = is_welcome_screen_enabled2;
    }

    public String getHasRandomHotspotPassword() {
        return this.has_random_hotspot_password;
    }

    public void setHasRandomHotspotPassword(String has_random_hotspot_password2) {
        this.has_random_hotspot_password = has_random_hotspot_password2;
    }

    public boolean getIsWeatherEnabled() {
        return this.is_weather_enabled.equals("1");
    }

    public void setIsWeatherEnabled(String is_weather_enabled2) {
        this.is_weather_enabled = is_weather_enabled2;
    }

    public String getChromecastModeOn() {
        return this.chromecast_mode_on;
    }

    public void setChromecastModeOn(String chromecast_mode_on2) {
        this.chromecast_mode_on = chromecast_mode_on2;
    }

    public String getHotspotSecurity() {
        return hotspot_security;
    }

    public void setHotspotSecurity(String hotspot_security) {
        this.hotspot_security = hotspot_security;
    }

    public String getAutoOtsEnabled() {
        return auto_ots_enabled;
    }

    public void setAutoOtsEnabled(String auto_ots_enabled) {
        this.auto_ots_enabled = auto_ots_enabled;
    }
}
