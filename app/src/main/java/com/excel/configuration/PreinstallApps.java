package com.excel.configuration;

import com.excel.excelclasslibrary.UtilFile;
import java.io.File;
import java.util.Enumeration;
import java.util.Vector;

public class PreinstallApps {
    static int OPTION_BUTTON_ID = 2;
    static int OPTION_FORCE_KILL = 5;
    static int OPTION_MD5 = 1;
    static int OPTION_PACKAGE_NAME = 0;
    static int OPTION_SHOW = 3;
    static int OPTION_WIPE_CACHE = 4;
    static final String TAG = "PreinstallApks";
    static int TOTAL_OPTIONS = 6;
    String button_id;
    String force_kill;
    String md5;
    String package_name;
    String show;
    String wipe_cache;

    public PreinstallApps() {
    }

    public PreinstallApps(String package_name2, String md52, String show2, String wipe_cache2, String button_id2, String force_kill2) {
        this.package_name = package_name2;
        this.md5 = md52;
        this.show = show2;
        this.wipe_cache = wipe_cache2;
        this.button_id = button_id2;
        this.force_kill = force_kill2;
    }

    public static PreinstallApps[] getPreinstallApps() {
        ConfigurationReader configurationReader = ConfigurationReader.getInstance();
        File preinstall_apps_file = configurationReader.getPreinstallAppsFile(false);
        if (!preinstall_apps_file.exists()) {
            preinstall_apps_file = configurationReader.getPreinstallAppsFile(true);
        }
        String preinstall_apps = UtilFile.readData(preinstall_apps_file);
        if (preinstall_apps.trim().equals("")) {
            preinstall_apps = UtilFile.readData(configurationReader.getPreinstallAppsFile(true));
        }
        String[] temp = preinstall_apps.split(",");
        int i = TOTAL_OPTIONS;
        String[] strArr = new String[i];
        PreinstallApps[] papps = new PreinstallApps[(temp.length / i)];
        Vector vector = new Vector(temp.length / i);
        int i2 = 0;
        while ( i2 < temp.length / TOTAL_OPTIONS ) {
            int iterator = i2 * 6;
            ConfigurationReader configurationReader2 = configurationReader;
            //PreinstallApps preinstallApps = new PreinstallApps();
            PreinstallApps preinstallApps = new PreinstallApps(temp[OPTION_PACKAGE_NAME + iterator], temp[OPTION_MD5 + iterator], temp[OPTION_SHOW + iterator], temp[OPTION_WIPE_CACHE + iterator], temp[OPTION_BUTTON_ID + iterator], temp[OPTION_FORCE_KILL + iterator]);
            vector.add(preinstallApps);
            i2++;
            configurationReader = configurationReader2;
        }
        Enumeration<PreinstallApps> e = vector.elements();
        int cc = 0;
        while (e.hasMoreElements()) {
            int cc2 = cc + 1;
            papps[cc] = (PreinstallApps) e.nextElement();
            cc = cc2;
        }
        return papps;
    }

    private void setPackageName(String package_name2) {
        this.package_name = package_name2;
    }

    private void setMD5(String md52) {
        this.md5 = md52;
    }

    private void setShow(String show2) {
        this.show = show2;
    }

    private void setWipeCache(String wipe_cache2) {
        this.wipe_cache = wipe_cache2;
    }

    private void setButtonID(String button_id2) {
        this.button_id = button_id2;
    }

    private void setForceKill(String force_kill2) {
        this.force_kill = force_kill2;
    }

    public String getPackageName() {
        return this.package_name;
    }

    public String getMD5() {
        return this.md5;
    }

    public String getShow() {
        return this.show;
    }

    public String getWipeCache() {
        return this.wipe_cache;
    }

    public String getButtonID() {
        return this.button_id;
    }

    public String getForceKill() {
        return this.force_kill;
    }
}
