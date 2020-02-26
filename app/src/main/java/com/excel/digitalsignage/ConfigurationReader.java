package com.excel.digitalsignage;

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
    public static final String CMS_IP = "cms_ip";
    public static final String CMS_SUB_DIRECTORY = "cms_sub_directory";
    public static final String COLLAR_TEXT_SPEED = "collar_text_speed";
    public static final String COUNTRY = "country";
    public static final String FIRMWARE_NAME = "firmware_name";
    public static final String IS_OTS_COMPLETED = "is_ots_completed";
    public static final String IS_REBOOT_SCHEDULED = "is_reboot_scheduled";
    static int KEY = 0;
    public static final String LOADING_SCREEN_TIME = "loading_screen_time";
    public static final String LOCATION = "location";
    public static final String PROTOCOL = "protocol";
    public static final String REBOOT_TIME = "reboot_time";
    public static final String TAG = "ConfigurationReader";
    public static final String TIMEZONE = "timezone";
    static int VALUE = 1;
    public static final String WEBSERVICE_PATH = "webservice_path";
    static volatile ConfigurationReader configurationReader = null;
    static String file_md5 = "";
    public static SQLiteDatabase sqldb;
    String cms_ip;
    String cms_sub_directory;
    String collar_text_speed;
    String country;
    String firmware_name;
    String is_ots_completed;
    String is_reboot_scheduled;
    String loading_screen_time;
    String location;
    String protocol;
    String reboot_time;
    String timezone;
    String webservice_path;

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

    public void setIsOtsCompleted(String is_ots_completed2) {
        this.is_ots_completed = is_ots_completed2;
    }

    public boolean isOtsCompleted() {
        return this.is_ots_completed.equals("1");
    }

    public String getWebservicePath() {
        return this.webservice_path;
    }

    public void setWebservicePath(String webservice_path2) {
        this.webservice_path = webservice_path2;
    }

    public void setLoadingScreenTime(String loading_screen_time2) {
        this.loading_screen_time = loading_screen_time2;
    }

    public String getLoadingScreenTime() {
        return this.loading_screen_time;
    }

    public String getCollarTextSpeed() {
        return this.collar_text_speed;
    }

    public void setCollarTextSpeed(String collar_text_speed2) {
        this.collar_text_speed = collar_text_speed2;
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

    public void setSyncStarted(boolean isStarted) {
        String is_started = isStarted ? "1" : "0";
        StringBuilder sb = new StringBuilder();
        sb.append("setprop sync_in_progress ");
        sb.append(is_started);
        UtilShell.executeShellCommandWithOp(sb.toString());
    }

    public boolean isSyncStarted() {
        return UtilShell.executeShellCommandWithOp("getprop sync_in_progress").trim().equals("1");
    }

    public void setContentDownloadStarted(boolean isStarted) {
        String is_started = isStarted ? "1" : "0";
        StringBuilder sb = new StringBuilder();
        sb.append("setprop cntnt_dwnld_strtd ");
        sb.append(is_started);
        UtilShell.executeShellCommandWithOp(sb.toString());
    }

    public boolean isContentDownloadStarted() {
        return UtilShell.executeShellCommandWithOp("getprop cntnt_dwnld_strtd").trim().equals("1");
    }

    public static String getDigitalSignageDataRootDirectoryPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append(Constants.DS_DATA_DIRECTORY_NAME);
        return new File(sb.toString()).getAbsolutePath();
    }

    public String getGraphicsDirectoryPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append(File.separator);
        sb.append(Constants.DIR_GRAPHICS);
        return new File(sb.toString()).getAbsolutePath();
    }

    public String getImagesDirectoryPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append(File.separator);
        sb.append(Constants.DIR_IMAGES);
        return new File(sb.toString()).getAbsolutePath();
    }

    public String getVideosDirectoryPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append(File.separator);
        sb.append(Constants.DIR_VIDEOS);
        return new File(sb.toString()).getAbsolutePath();
    }

    public String getFirmwareDirectoryPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append(File.separator);
        sb.append(Constants.DIR_FIRMWARE);
        return new File(sb.toString()).getAbsolutePath();
    }

    public static String getTemplateFilePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDigitalSignageDataRootDirectoryPath());
        sb.append(File.separator);
        sb.append(Constants.TEMPLATE_FILE_NAME);
        return sb.toString();
    }

    public String getDownloadsGraphhicsDirectoryPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        sb.append(File.separator);
        sb.append(Constants.DIR_GRAPHICS);
        return new File(sb.toString()).getAbsolutePath();
    }

    public String getDownloadsDirectoryPath() {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()).getAbsolutePath();
    }

    public String getDownloadsDigitalSignageDataRootDirectoryPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDownloadsDirectoryPath());
        sb.append(File.separator);
        sb.append(Constants.DS_DATA_DIRECTORY_NAME);
        return new File(sb.toString()).getAbsolutePath();
    }

    public String getDownloadsImagesDirectoryPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDownloadsDirectoryPath());
        sb.append(File.separator);
        sb.append(Constants.DIR_IMAGES);
        return new File(sb.toString()).getAbsolutePath();
    }

    public String getDownloadsVideosDirectoryPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDownloadsDirectoryPath());
        sb.append(File.separator);
        sb.append(Constants.DIR_VIDEOS);
        return new File(sb.toString()).getAbsolutePath();
    }

    public String getDownloadsTemplateFilePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDownloadsDigitalSignageDataRootDirectoryPath());
        sb.append(File.separator);
        sb.append(Constants.TEMPLATE_FILE_NAME);
        return new File(sb.toString()).getAbsolutePath();
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

    public static ConfigurationReader getInstance() {
        if (configurationReader == null) {
            configurationReader = new ConfigurationReader();
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
            if (getFileMD5().equals("no_md5") || getFileMD5().equals("")) {
                setFileMD5(configuration);
            }
            processConfigurationData(getConfigurationFileData(configuration));
        }
        return configurationReader;
    }

    public static ConfigurationReader reInstantiate() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append(File.separator);
        sb.append(Constants.PATH_CONFIGURATION_FILE);
        File configuration = new File(sb.toString());
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
            } else if (line[KEY].equals("cms_sub_directory")) {
                configurationReader.setCmsSubDirectory(line[VALUE]);
            } else if (line[KEY].equals("protocol")) {
                configurationReader.setProtocol(line[VALUE]);
            } else if (line[KEY].equals("is_ots_completed")) {
                configurationReader.setIsOtsCompleted(line[VALUE]);
            } else if (line[KEY].equals("webservice_path")) {
                configurationReader.setWebservicePath(line[VALUE]);
            } else if (line[KEY].equals("loading_screen_time")) {
                configurationReader.setLoadingScreenTime(line[VALUE]);
            } else if (line[KEY].equals("collar_text_speed")) {
                configurationReader.setCollarTextSpeed(line[VALUE]);
            }
        }
    }
}
