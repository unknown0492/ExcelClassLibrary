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

import static com.excel.configuration.Constants.APPSTV_DATA_DIRECTORY_NAME;
import static com.excel.configuration.Constants.APPSTV_DB_NAME;
import static com.excel.configuration.Constants.DIR_DIGITAL_SIGNAGE;
import static com.excel.configuration.Constants.DIR_FIRMWARE;
import static com.excel.configuration.Constants.DIR_HOTEL_LOGO;
import static com.excel.configuration.Constants.DIR_PREINSTALL_EXT;
import static com.excel.configuration.Constants.DIR_PREINSTALL_SYSTEM;
import static com.excel.configuration.Constants.DIR_TV_CHANNELS_FILE;
import static com.excel.configuration.Constants.PATH_CONFIGURATION_FILE;
import static com.excel.configuration.Constants.PATH_CONFIGURATION_FILE_SYSTEM;
import static com.excel.configuration.Constants.PATH_LAUNCHER_CONFIG_FILE;
import static com.excel.configuration.Constants.PATH_LAUNCHER_CONFIG_FILE_SYSTEM;
import static com.excel.configuration.Constants.PATH_PREINSTALL_APPS_FILE;
import static com.excel.configuration.Constants.PATH_PREINSTALL_APPS_FILE_SYSTEM;

public class ConfigurationReader {
    String country, timezone, cms_ip, location, firmware_name,
            is_reboot_scheduled, reboot_time, digital_signage_interval, weather_retry_interval, weather_refresh_interval,
            clock_weather_flip_interval, cms_sub_directory, protocol, hotspot_enabled, ssid, hotspot_password, room_no, web_service_url,
            is_ots_completed, language, webservice_path, airplay_enabled, date_time_flip_interval, tethering_info_flip_interval, has_hotel_logo_display,
            hotel_logo_flip_interval, is_airplay_ssid_same_as_tethering_ssid, airplay_ssid, airplay_password, loading_screen_time, welcome_screen_type,
            collar_text_speed, idle_timeout_interval;

    static String file_md5 = "";
    static ConfigurationReader configurationReader;
    public final static String TAG = "ConfigurationReader";
    static int KEY = 0;
    static int VALUE = 1;
    public final static String COUNTRY = "country";
    public final static String TIMEZONE = "timezone";
    public final static String CMS_IP = "cms_ip";
    public final static String LOCATION = "location";
    public final static String FIRMWARE_NAME = "firmware_name";
    public final static String IS_REBOOT_SCHEDULED = "is_reboot_scheduled";
    public final static String REBOOT_TIME = "reboot_time";
    public final static String DIGITAL_SIGNAGE_INTERVAL = "digital_signage_interval";
    public final static String WEATHER_RETRY_INTERVAL = "weather_retry_interval";
    public final static String WEATHER_REFRESH_INTERVAL = "weather_refresh_interval";
    public final static String CLOCK_WEATHER_FLIP_INTERVAL = "clock_weather_flip_interval";
    public final static String CMS_SUB_DIRECTORY = "cms_sub_directory";
    public final static String PROTOCOL = "protocol";
    public final static String HOTSPOT_ENABLED = "hotspot_enabled";
    public final static String SSID = "ssid";
    public final static String HOTSPOT_PASSWORD = "hotspot_password";
    public final static String ROOM_NO = "room_no";
    public final static String IS_OTS_COMPLETED = "is_ots_completed";
    public final static String LANGUAGE = "language";
    public final static String WEBSERVICE_PATH = "webservice_path";
    public final static String AIRPLAY_ENABLED = "airplay_enabled";
    public final static String DATE_TIME_FLIP_INTERVAL = "date_time_flip_interval";
    public final static String TETHERING_INFO_FLIP_INTERVAL = "tethering_info_flip_interval";
    public final static String HAS_HOTEL_LOGO_DISPLAY = "has_hotel_logo_display";
    public final static String HOTEL_LOGO_FLIP_INTERVAL = "hotel_logo_flip_interval";
    public final static String IS_AIRPLAY_SSID_SAME_AS_TETHERING_SSID = "is_airplay_ssid_same_as_tethering_ssid";
    public final static String AIRPLAY_SSID = "airplay_ssid";
    public final static String AIRPLAY_PASSWORD = "airplay_password";
    public final static String LOADING_SCREEN_TIME = "loading_screen_time";
    public final static String WELCOME_SCREEN_TYPE = "welcome_screen_type";
    public final static String COLLAR_TEXT_SPEED = "collar_text_speed";
    public final static String IDLE_TIMEOUT_INTERVAL = "idle_timeout_interval";

    public static SQLiteDatabase sqldb;

    public ConfigurationReader() {
    }

    public String getCollarTextSpeed() {
        return collar_text_speed;
    }

    public void setCollarTextSpeed( String collar_text_speed ) {
        this.collar_text_speed = collar_text_speed;
    }

    public String getIdleTimeoutInterval() {
        return idle_timeout_interval;
    }

    public void setIdleTimeoutInterval( String idle_timeout_interval ) {
        this.idle_timeout_interval = idle_timeout_interval;
    }

    /*public String getWebServiceUrl() {
        web_service_url = getProtocol() + "://" + getCmsIp() + File.separator + getCmsSubDirectory() + File.separator + configurationReader.getWebservicePath();
        return web_service_url;
    }*/

    /*public void setWebServiceUrl( String web_service_url ) {
        this.web_service_url = web_service_url;
    }*/



    public File getPreinstallAppsFile( boolean is_system ){
        if( is_system )
            return new File( PATH_PREINSTALL_APPS_FILE_SYSTEM );
        else
            return new File( Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator + PATH_PREINSTALL_APPS_FILE );
    }

    public File getConfigurationFile( boolean is_system ){
        if( is_system )
            return new File( PATH_CONFIGURATION_FILE_SYSTEM );
        else
            return new File( Environment.getExternalStorageDirectory().getAbsolutePath() +
                    File.separator + PATH_CONFIGURATION_FILE );
    }

    public File getLauncherConfigFile( boolean is_system ){
        if( is_system )
            return new File( PATH_LAUNCHER_CONFIG_FILE_SYSTEM );
        else
            return new File( Environment.getExternalStorageDirectory().getAbsolutePath() +
                    File.separator + PATH_LAUNCHER_CONFIG_FILE );
    }

    public String getDigitalSignageDirectoryPath(){
        File file = new File( Environment.getExternalStorageDirectory() + File.separator +
            DIR_DIGITAL_SIGNAGE );
        return file.getAbsolutePath();
    }

    public String getHotelLogoDirectoryPath(){
        File file = new File( Environment.getExternalStorageDirectory() + File.separator +
                DIR_HOTEL_LOGO );
        return file.getAbsolutePath();
    }

    public String getTvChannelsDirectoryPath(){
        File file = new File( Environment.getExternalStorageDirectory() + File.separator +
                DIR_TV_CHANNELS_FILE );
        return file.getAbsolutePath();
    }

    public String getPreinstallApksDirectoryPath( boolean is_system ){
        File file;
        if( is_system )
            file = new File( DIR_PREINSTALL_SYSTEM );
        else
            file = new File( Environment.getExternalStorageDirectory() + File.separator +
                    DIR_PREINSTALL_EXT );

        return file.getAbsolutePath();
    }

    public String getFirmwareDirectoryPath(){
        File file = new File( Environment.getExternalStorageDirectory() + File.separator +
                DIR_FIRMWARE );
        return file.getAbsolutePath();
    }

    /**
     * @return Instance of ConfigurationReader class. This instance contains all the data from
     * the configuration file.
     */
    public static ConfigurationReader getInstance() {
        if (configurationReader == null) {
            configurationReader = new ConfigurationReader();
            /**
             *
             * 1. Check if configuration file exist on sdcard
             * 2. If does not exist, then read the configuration file from the /system
             *
             */
            String configuration_file_path = Environment.getExternalStorageDirectory() + File.separator + PATH_CONFIGURATION_FILE;
            Log.d(TAG, configuration_file_path);
            File configuration = new File( configuration_file_path );

            // Step-1
            if ( !configuration.exists() ) {
                // Step-2
                configuration_file_path = PATH_CONFIGURATION_FILE_SYSTEM;
                configuration = new File( configuration_file_path );
            }

            // if no md5 exist or empty, then generate md5 and store
            if( getFileMD5().equals( "no_md5" ) || getFileMD5().equals( "" ) ){
                setFileMD5( configuration );
            }

            String configuration_data = getConfigurationFileData( configuration );
            processConfigurationData( configuration_data );


        }

        /*
        configurationReader = new ConfigurationReader();
        *//*
        * 1. Check if the database already exist on sdcard
        * 2. If does not exist, create it
        *
        *//*
        sqldb = SQLiteDatabase.openOrCreateDatabase( getAppstvDatabasePath(), null );
        // Create table for launcher config
        //String CREATE_LAUNCHER_CONFIG_TABLE = "CREATE TABLE launcher_config( `` )";
        // Create table for box config
        // Create table for digital signage
        // Create table for preinstall apps
*/

        return configurationReader;
    }

    public static ConfigurationReader reInstantiate() {
        //configurationReader = null;
        // Verify first, if there is a need for reinstantiate, then only reinstantiate
        String configuration_file_path = Environment.getExternalStorageDirectory() + File.separator + PATH_CONFIGURATION_FILE;
        Log.d(TAG, configuration_file_path);
        File configuration = new File( configuration_file_path );

        // Step-1
        if ( !configuration.exists() ) {
            // Step-2
            configuration_file_path = PATH_CONFIGURATION_FILE_SYSTEM;
            configuration = new File( configuration_file_path );
        }

        // If previous md5 and new md5 is equal, dont need to reinstantiate
        if( getFileMD5().equals( getNewFileMD5( configuration ) ) ){
            return getInstance();
        }

        // if no md5 exist or empty, then generate md5 and store
        // if previous and new md5 dont match, then store
        setFileMD5( configuration );
        configurationReader = null;

        /*// if no md5 exist or empty, then generate md5 and store
        if( getFileMD5().equals( "no_md5" ) || getFileMD5().equals( "" ) ){
            setFileMD5( configuration );
            configurationReader = null;
        }
        else if( ! getFileMD5().equals( getNewFileMD5( configuration ) ) ){
            setFileMD5( configuration );
            configurationReader = null;
        }*/

        return getInstance();
    }

    /**
     * @param file The full path of the configuration file.
     *
     * @return All the content from the configuration file
     */
    public static String getConfigurationFileData( File file ) {
        FileInputStream fis = null;
        BufferedReader reader = null;
        StringBuilder configuration_data;
        try {
            fis = new FileInputStream( file );
            reader = new BufferedReader( new InputStreamReader( fis ) );
            configuration_data = new StringBuilder();
            String line = null;

            while ( ( line = reader.readLine() ) != null ) {
                configuration_data.append( line ).append( "\n" );
            }
            reader.close();
            fis.close();
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
        return configuration_data.toString();
    }

    private static void processConfigurationData(String data) {

        String lines[] = data.split("\n");
        String line[];
        for (int i = 0; i < lines.length; i++) {
            line = lines[i].split("=");
            if( line.length < 2 ){
                line = new String[]{ line[ 0 ], "" };
            }
            if (line[KEY].equals(COUNTRY)) {
                configurationReader.setCountry(line[VALUE]);
            } else if (line[KEY].equals(TIMEZONE)) {
                configurationReader.setTimezone(line[VALUE]);
            } else if (line[KEY].equals(CMS_IP)) {
                configurationReader.setCmsIp(line[VALUE]);
            } else if (line[KEY].equals(LOCATION)) {
                configurationReader.setLocation(line[VALUE]);
            } else if (line[KEY].equals(FIRMWARE_NAME)) {
                configurationReader.setFirmwareName(line[VALUE]);
            } else if (line[KEY].equals(IS_REBOOT_SCHEDULED)) {
                configurationReader.setIsRebootScheduled(line[VALUE]);
            } else if (line[KEY].equals(REBOOT_TIME)) {
                configurationReader.setRebootTime(line[VALUE]);
            } else if (line[KEY].equals(DIGITAL_SIGNAGE_INTERVAL)) {
                configurationReader.setDigitalSignageInterval(line[VALUE]);
            } else if (line[KEY].equals(WEATHER_RETRY_INTERVAL)) {
                configurationReader.setWeatherRetryInterval(line[VALUE]);
            } else if (line[KEY].equals(WEATHER_REFRESH_INTERVAL)) {
                configurationReader.setWeatherRefreshInterval(line[VALUE]);
            } else if (line[KEY].equals(CLOCK_WEATHER_FLIP_INTERVAL)) {
                configurationReader.setClockWeatherFlipInterval(line[VALUE]);
            } else if ( line[ KEY ].equals( CMS_SUB_DIRECTORY ) ) {
                configurationReader.setCmsSubDirectory( line[ VALUE ] );
            } else if ( line[ KEY ].equals( PROTOCOL ) ) {
                configurationReader.setProtocol( line[ VALUE ] );
            } else if ( line[ KEY ].equals( HOTSPOT_ENABLED ) ) {
                configurationReader.setHotspotEnabled( line[ VALUE ] );
            } else if ( line[ KEY ].equals( SSID ) ) {
                configurationReader.setSSID( line[ VALUE ] );
            } else if ( line[ KEY ].equals( HOTSPOT_PASSWORD ) ) {
                configurationReader.setHotspotPassword(line[ VALUE ] );
            } else if ( line[ KEY ].equals( ROOM_NO ) ) {
                configurationReader.setRoomNo( line[ VALUE ] );
            } else if ( line[ KEY ].equals( IS_OTS_COMPLETED ) ) {
                configurationReader.setIsOtsCompleted( line[ VALUE ] );
            } else if ( line[ KEY ].equals( LANGUAGE ) ) {
                configurationReader.setLanguage( line[ VALUE ] );
            } else if ( line[ KEY ].equals( WEBSERVICE_PATH ) ) {
                configurationReader.setWebservicePath( line[ VALUE ] );
            } else if ( line[ KEY ].equals( AIRPLAY_ENABLED ) ) {
                configurationReader.setAirplayEnabled( line[ VALUE ] );
            } else if ( line[ KEY ].equals( DATE_TIME_FLIP_INTERVAL ) ) {
                configurationReader.setDateTimeFlipInterval( line[ VALUE ] );
            } else if ( line[ KEY ].equals( TETHERING_INFO_FLIP_INTERVAL ) ) {
                configurationReader.setTetheringInfoFlipInterval( line[ VALUE ] );
            } else if ( line[ KEY ].equals( HOTEL_LOGO_FLIP_INTERVAL ) ) {
                configurationReader.setHotelLogoFlipInterval( line[ VALUE ] );
            } else if ( line[ KEY ].equals( HAS_HOTEL_LOGO_DISPLAY ) ) {
                configurationReader.setHasHotelLogoDisplay( line[ VALUE ] );
            } else if ( line[ KEY ].equals( IS_AIRPLAY_SSID_SAME_AS_TETHERING_SSID ) ) {
                configurationReader.setIsAirplaySSIDSameAsTetheringSSID( line[ VALUE ] );
            } else if ( line[ KEY ].equals( AIRPLAY_PASSWORD ) ) {
                configurationReader.setAirplayPassword( line[ VALUE ] );
            } else if ( line[ KEY ].equals( AIRPLAY_SSID ) ) {
                configurationReader.setAirplaySSID( line[ VALUE ] );
            } else if ( line[ KEY ].equals( LOADING_SCREEN_TIME ) ) {
                configurationReader.setLoadingScreenTime( line[ VALUE ] );
            } else if ( line[ KEY ].equals( WELCOME_SCREEN_TYPE ) ) {
                configurationReader.setWelcomeScreenType( line[ VALUE ] );
            } else if ( line[ KEY ].equals( COLLAR_TEXT_SPEED ) ) {
                configurationReader.setCollarTextSpeed( line[ VALUE ] );
            } else if ( line[ KEY ].equals( IDLE_TIMEOUT_INTERVAL ) ) {
                configurationReader.setIdleTimeoutInterval( line[ VALUE ] );
            }


        }
    }

    public static String getAppstvDataRootDirectoryPath(){
        return new File( Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + APPSTV_DATA_DIRECTORY_NAME ).getAbsolutePath();
    }

    public static String getAppstvDatabasePath(){
        return getAppstvDataRootDirectoryPath() + File.separator + APPSTV_DB_NAME;
    }

    public SQLiteDatabase getAppstvDatabase(){
        return sqldb;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCmsIp() {
        return cms_ip;
    }

    public void setCmsIp(String cms_ip) {
        this.cms_ip = cms_ip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFirmwareName() {
        String fname = UtilShell.executeShellCommandWithOp("getprop ro.build.display.id");
        return fname.trim();
    }

    public void setFirmwareName(String firmware_name) {
        this.firmware_name = firmware_name;
    }

    public String getIsRebootScheduled() {
        return is_reboot_scheduled;
    }

    public void setIsRebootScheduled(String is_reboot_scheduled) {
        this.is_reboot_scheduled = is_reboot_scheduled;
    }

    public String getRebootTime() {
        return reboot_time;
    }

    public void setRebootTime(String reboot_time) {
        this.reboot_time = reboot_time;
    }

    public String getDigitalSignageInterval() {
        return digital_signage_interval;
    }

    public void setDigitalSignageInterval(String digital_signage_interval) {
        this.digital_signage_interval = digital_signage_interval;
    }

    public String getWeatherRetryInterval() {
        return weather_retry_interval;
    }

    public void setWeatherRetryInterval(String weather_retry_interval) {
        this.weather_retry_interval = weather_retry_interval;
    }

    public String getWeatherRefreshInterval() {
        return weather_refresh_interval;
    }

    public void setWeatherRefreshInterval(String weather_refresh_interval) {
        this.weather_refresh_interval = weather_refresh_interval;
    }

    public String getClockWeatherFlipInterval() {
        return clock_weather_flip_interval;
    }

    public void setClockWeatherFlipInterval(String clock_weather_flip_interval) {
        this.clock_weather_flip_interval = clock_weather_flip_interval;
    }

    public String getCmsSubDirectory() {
        return cms_sub_directory;
    }

    public void setCmsSubDirectory(String cms_sub_directory) {
        this.cms_sub_directory = cms_sub_directory;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHotspotEnabled() {
        return hotspot_enabled;
    }

    public void setHotspotEnabled(String hotspot_enabled) {
        this.hotspot_enabled = hotspot_enabled;
    }

    public String getSSID() {
        return ssid;
    }

    public void setSSID(String ssid) {
        this.ssid = ssid;
    }

    public String getHotspotPassword() {
        return hotspot_password;
    }

    public void setHotspotPassword(String hotspot_password) {
        this.hotspot_password = hotspot_password;
    }

    public String getRoomNo() {
        return room_no;
    }

    public void setRoomNo(String room_no) {
        this.room_no = room_no;
    }








    public String getAirplayEnabled() {
        return airplay_enabled;
    }

    public void setAirplayEnabled( String airplay_enabled ) {
        this.airplay_enabled = airplay_enabled;
    }

    public void setDateTimeFlipInterval( String date_time_flip_interval ){
        this.date_time_flip_interval = date_time_flip_interval;
    }

    public String getDateTimeFlipInterval(){
        return date_time_flip_interval;
    }

    public void setTetheringInfoFlipInterval( String tethering_info_flip_interval ){
        this.tethering_info_flip_interval = tethering_info_flip_interval;
    }

    public void setIsOtsCompleted( String is_ots_completed ) {
        this.is_ots_completed = is_ots_completed;
    }

    public String getIsOtsCompleted() {
        return is_ots_completed;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getWebservicePath() {
        return webservice_path;
    }

    public void setWebservicePath(String webservice_path) {
        this.webservice_path = webservice_path;
    }

    public String getTetheringInfoFlipInterval(){
        return tethering_info_flip_interval;
    }

    public void setHasHotelLogoDisplay( String has_hotel_logo_display ){
        this.has_hotel_logo_display = has_hotel_logo_display;
    }

    public String getHasHotelLogoDisplay(){
        return has_hotel_logo_display;
    }

    public void setHotelLogoFlipInterval( String hotel_logo_flip_interval ){
        this.hotel_logo_flip_interval = hotel_logo_flip_interval;
    }

    public String getHotelLogoFlipInterval(){
        return hotel_logo_flip_interval;
    }

    public void setIsAirplaySSIDSameAsTetheringSSID( String is_airplay_ssid_same_as_tethering_ssid ){
        this.is_airplay_ssid_same_as_tethering_ssid = is_airplay_ssid_same_as_tethering_ssid;
    }

    public String getIsAirplaySSIDSameAsTetheringSSID(){
        return is_airplay_ssid_same_as_tethering_ssid;
    }

    public void setAirplaySSID( String airplay_ssid ){
        this.airplay_ssid = airplay_ssid;
    }

    public String getAirplaySSID() {
        return airplay_ssid;
    }

    public void setAirplayPassword( String airplay_password ){
        this.airplay_password = airplay_password;
    }

    public String getAirplayPassword(){
        return airplay_password;
    }

    public void setLoadingScreenTime( String loading_screen_time ){
        this.loading_screen_time = loading_screen_time;
    }

    public String getLoadingScreenTime(){
        return loading_screen_time;
    }

    public void setWelcomeScreenType( String welcome_screen_type ){
        this.welcome_screen_type = welcome_screen_type;
    }

    public String getWelcomeScreenType(){
        return welcome_screen_type;
    }





    public static void setFileMD5( File config_file_path ){
        try {
            file_md5 = MD5.getMD5Checksum(config_file_path);
        }
        catch ( Exception e ){
            e.printStackTrace();
            file_md5 = "no_md5";
        }
    }

    public static String getFileMD5(){
        return file_md5;
    }


    public static String getNewFileMD5( File file_path ){
        try {
            return MD5.getMD5Checksum( file_path );
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        return "no_md5";
    }


}
