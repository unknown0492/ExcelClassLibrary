package com.excel.digitalsignage;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;

import com.excel.excelclasslibrary.UtilShell;
import com.excel.util.MD5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import static com.excel.configuration.Constants.APPSTV_DATA_DIRECTORY_NAME;
import static com.excel.configuration.Constants.DIR_PREINSTALL_SYSTEM;
import static com.excel.digitalsignage.Constants.CONTENT_DOWNLOAD_STARTED;
import static com.excel.digitalsignage.Constants.DIR_FIRMWARE;
import static com.excel.digitalsignage.Constants.DIR_GRAPHICS;
import static com.excel.digitalsignage.Constants.DS_DATA_DIRECTORY_NAME;
import static com.excel.digitalsignage.Constants.PATH_CONFIGURATION_FILE;
import static com.excel.digitalsignage.Constants.PATH_CONFIGURATION_FILE_SYSTEM;
import static com.excel.digitalsignage.Constants.DIR_IMAGES;
import static com.excel.digitalsignage.Constants.DIR_VIDEOS;
import static com.excel.digitalsignage.Constants.TEMPLATE_FILE_NAME;

public class ConfigurationReader {
    String country;
    String timezone;
    String cms_ip;
    String location;
    String firmware_name;
    String is_reboot_scheduled;
    String reboot_time;
    String cms_sub_directory;
    String protocol;
    String is_ots_completed;
    String webservice_path;
    String loading_screen_time;
    String collar_text_speed;


    static String file_md5 = "";
    static volatile ConfigurationReader configurationReader = null;
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
    public final static String CMS_SUB_DIRECTORY = "cms_sub_directory";
    public final static String PROTOCOL = "protocol";
    public final static String IS_OTS_COMPLETED = "is_ots_completed";
    public final static String WEBSERVICE_PATH = "webservice_path";
    public final static String LOADING_SCREEN_TIME = "loading_screen_time";
    public final static String COLLAR_TEXT_SPEED = "collar_text_speed";



    public static SQLiteDatabase sqldb;

    public ConfigurationReader() {
    }

    // Country
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    // Country





    // Timezone
    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    // Timezone





    // CMS IP
    public String getCmsIp() {
        return cms_ip;
    }

    public void setCmsIp(String cms_ip) {
        this.cms_ip = cms_ip;
    }
    // CMS IP





    // Location
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    // Location





    // Firmware Name
    public String getFirmwareName() {
        String fname = UtilShell.executeShellCommandWithOp("getprop ro.build.display.id");
        return fname.trim();
    }

    public void setFirmwareName(String firmware_name) {
        this.firmware_name = firmware_name;
    }
    // Firmware Name





    // IS REBOOT SCHEDULED
    public String getIsRebootScheduled() {
        return is_reboot_scheduled;
    }

    public void setIsRebootScheduled(String is_reboot_scheduled) {
        this.is_reboot_scheduled = is_reboot_scheduled;
    }
    // IS REBOOT SCHEDULED





    // REBOOT TIME
    public String getRebootTime() {
        return reboot_time;
    }

    public void setRebootTime(String reboot_time) {
        this.reboot_time = reboot_time;
    }
    // REBOOT TIME





    // CMS SUB DIRECTORY
    public String getCmsSubDirectory() {
        return cms_sub_directory;
    }

    public void setCmsSubDirectory(String cms_sub_directory) {
        this.cms_sub_directory = cms_sub_directory;
    }
    // CMS SUB DIRECTORY





    // PROTOCOL
    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    // PROTOCOL





    // IS OTS COMPLETED
    public void setIsOtsCompleted( String is_ots_completed ) {
        this.is_ots_completed = is_ots_completed;
    }

    public boolean isOtsCompleted() {
        return (is_ots_completed.equals( "1" ))?true:false;
    }
    // IS OTS COMPLETED





    // WEBSERVICE PATH
    public String getWebservicePath() {
        return webservice_path;
    }

    public void setWebservicePath(String webservice_path) {
        this.webservice_path = webservice_path;
    }
    // WEBSERVICE PATH





    // LOADING SCREEN TIME
    public void setLoadingScreenTime( String loading_screen_time ){
        this.loading_screen_time = loading_screen_time;
    }

    public String getLoadingScreenTime(){
        return loading_screen_time;
    }
    // LOADING SCREEN TIME





    // COLLAR TEXT SPEED
    public String getCollarTextSpeed() {
        return collar_text_speed;
    }

    public void setCollarTextSpeed( String collar_text_speed ) {
        this.collar_text_speed = collar_text_speed;
    }
    // COLLAR TEXT SPEED





    // Configuration File MD5
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
    // Configuration File MD5





    // Sync Process
    public void setSyncStarted( boolean isStarted ){
        String is_started = isStarted?"1":"0";
        UtilShell.executeShellCommandWithOp( "setprop " + Constants.SYNC_IN_PROGRESS + " " + is_started );
    }

    public boolean isSyncStarted(){
        String is_started = UtilShell.executeShellCommandWithOp( "getprop " + Constants.SYNC_IN_PROGRESS );
        is_started = is_started.trim();
        return is_started.equals( "1" )?true:false;
    }
    // Sync Process





    // Content Download Started/Finished
    public void setContentDownloadStarted( boolean isStarted ){
        String is_started = isStarted?"1":"0";
        UtilShell.executeShellCommandWithOp( "setprop " + CONTENT_DOWNLOAD_STARTED + " " + is_started );
    }

    public boolean isContentDownloadStarted(){
        String is_started = UtilShell.executeShellCommandWithOp( "getprop " + CONTENT_DOWNLOAD_STARTED );
        is_started = is_started.trim();
        return is_started.equals( "1" )?true:false;
    }
    // Content Download Started/Finished



    // PATHS
    public static String getDigitalSignageDataRootDirectoryPath(){
        return new File( Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + DS_DATA_DIRECTORY_NAME ).getAbsolutePath();
    }

    public String getGraphicsDirectoryPath(){
        File file = new File( Environment.getExternalStorageDirectory() + File.separator + DIR_GRAPHICS );
        return file.getAbsolutePath();
    }

    public String getImagesDirectoryPath(){
        File file = new File( Environment.getExternalStorageDirectory() + File.separator +
                DIR_IMAGES );
        return file.getAbsolutePath();
    }

    public String getVideosDirectoryPath(){
        File file = new File( Environment.getExternalStorageDirectory() + File.separator +
                DIR_VIDEOS );
        return file.getAbsolutePath();
    }

    public String getFirmwareDirectoryPath(){
        File file = new File( Environment.getExternalStorageDirectory() + File.separator +
                DIR_FIRMWARE );
        return file.getAbsolutePath();
    }

    public static String getTemplateFilePath(){
        return getDigitalSignageDataRootDirectoryPath() + File.separator + TEMPLATE_FILE_NAME;
    }

    public String getDownloadsGraphhicsDirectoryPath(){
        File file = new File( Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DOWNLOADS ).getAbsolutePath() + File.separator + DIR_GRAPHICS );
        return file.getAbsolutePath();
    }

    public String getDownloadsDirectoryPath(){
        File file = new File( Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DOWNLOADS ).getAbsolutePath() );
        return file.getAbsolutePath();
    }

    public String getDownloadsDigitalSignageDataRootDirectoryPath(){
        return new File( getDownloadsDirectoryPath() + File.separator + DS_DATA_DIRECTORY_NAME ).getAbsolutePath();
    }

    public String getDownloadsImagesDirectoryPath(){
        return new File( getDownloadsDirectoryPath() + File.separator + DIR_IMAGES ).getAbsolutePath();
    }

    public String getDownloadsVideosDirectoryPath(){
        return new File( getDownloadsDirectoryPath() + File.separator + DIR_VIDEOS ).getAbsolutePath();
    }

    public String getDownloadsTemplateFilePath(){
        return new File( getDownloadsDigitalSignageDataRootDirectoryPath() + File.separator + TEMPLATE_FILE_NAME ).getAbsolutePath();
    }
    // PATHS








    public File getConfigurationFile( boolean is_system ){
        if( is_system )
            return new File( PATH_CONFIGURATION_FILE_SYSTEM );
        else
            return new File( Environment.getExternalStorageDirectory().getAbsolutePath() +
                    File.separator + PATH_CONFIGURATION_FILE );
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

            //Log.i( TAG, "Giving new ConfigurationReader" );
        }


        return configurationReader;
    }

    public static ConfigurationReader reInstantiate() {
        //configurationReader = null;
        // Verify first, if there is a need for reinstantiate, then only reinstantiate
        String configuration_file_path = Environment.getExternalStorageDirectory() + File.separator + PATH_CONFIGURATION_FILE;
        //Log.d(TAG, configuration_file_path);
        File configuration = new File( configuration_file_path );

        // Step-1
        if ( !configuration.exists() ) {
            // Step-2
            configuration_file_path = PATH_CONFIGURATION_FILE_SYSTEM;
            configuration = new File( configuration_file_path );
        }

        // If previous md5 and new md5 is equal, dont need to reinstantiate
        if( getFileMD5().equals( getNewFileMD5( configuration ) ) ){
            //configurationReader = null;
            return getInstance();
        }

        // if no md5 exist or empty, then generate md5 and store
        // if previous and new md5 dont match, then store
        setFileMD5( configuration );
        configurationReader = null;

        // if no md5 exist or empty, then generate md5 and store
        /*if( getFileMD5().equals( "no_md5" ) || getFileMD5().equals( "" ) ){
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
            } else if ( line[ KEY ].equals( CMS_SUB_DIRECTORY ) ) {
                configurationReader.setCmsSubDirectory( line[ VALUE ] );
            } else if ( line[ KEY ].equals( PROTOCOL ) ) {
                configurationReader.setProtocol( line[ VALUE ] );
            } else if ( line[ KEY ].equals( IS_OTS_COMPLETED ) ) {
                configurationReader.setIsOtsCompleted( line[ VALUE ] );
            } else if ( line[ KEY ].equals( WEBSERVICE_PATH ) ) {
                configurationReader.setWebservicePath( line[ VALUE ] );
            } else if ( line[ KEY ].equals( LOADING_SCREEN_TIME ) ) {
                configurationReader.setLoadingScreenTime( line[ VALUE ] );
            } else if ( line[ KEY ].equals( COLLAR_TEXT_SPEED ) ) {
                configurationReader.setCollarTextSpeed( line[ VALUE ] );
            }



        }
    }



}
