package com.excel.configuration;

import android.util.Log;
import com.excel.excelclasslibrary.UtilFile;
import org.json.JSONArray;
import org.json.JSONObject;

public class PreinstallAppsManager {
    static final String TAG = "PreinstallAppsManager";

    public boolean writePreinstallAppsFile(JSONArray jsonArray) {
        String str = "force_kill";
        String str2 = "show";
        String str3 = "0";
        String preinstall_apps = "";
        int i = 0;
        while (i < jsonArray.length()) {
            try {
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String package_name = jsonObject.getString("package_name");
                    String md5 = jsonObject.getString("md5");
                    String button_id = jsonObject.getString("button_id");
                    String show = jsonObject.getString(str2).equals(str3) ? "dont_show" : str2;
                    String wipe_cache = jsonObject.getString("wipe_cache").equals(str3) ? "dont_clear" : "clear";
                    String force_kill = jsonObject.getString(str).equals(str3) ? "dont_force_kill" : str;
                    StringBuilder sb = new StringBuilder();
                    sb.append(preinstall_apps);
                    sb.append(String.format("%s,%s,%s,%s,%s,%s,", new Object[]{package_name, md5, button_id, show, wipe_cache, force_kill}));
                    preinstall_apps = sb.toString();
                    i++;
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    return false;
                }
            } catch (Exception e2) {
                JSONArray jSONArray = jsonArray;
                e2.printStackTrace();
                return false;
            }
        }
        JSONArray jSONArray2 = jsonArray;
        String preinstall_apps2 = preinstall_apps.substring(0, preinstall_apps.length() - 1);
        String str4 = TAG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("preinstall_apps : ");
        sb2.append(preinstall_apps2);
        Log.i(str4, sb2.toString());
        return UtilFile.saveFile(Constants.APPSTV_DATA_DIRECTORY_NAME, Constants.PREINSTALL_APPS_FILE_NAME, null, preinstall_apps2.getBytes());
    }
}
