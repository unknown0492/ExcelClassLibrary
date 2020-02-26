package com.excel.configuration;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.excel.excelclasslibrary.UtilSQLite;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LauncherJSONReader {
    static final String TAG = "LauncherJSONReader";
    String clickable;

    /* renamed from: cr */
    ConfigurationReader f49cr = ConfigurationReader.getInstance();
    JSONArray highest_level_array;
    JSONObject highest_level_object;
    String item_name;
    String item_thumbnail;
    String item_thumbnail_url;
    String item_type;
    String main_items_count;
    String package_name;
    String params;
    String sub_items;
    String sub_items_count;
    String web_view_url;

    public LauncherJSONReader(String json_string) {
        try {
            this.highest_level_object = new JSONObject(json_string);
            this.highest_level_array = this.highest_level_object.getJSONArray("main_items");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONArray removeElementFromJSONArray(int index) throws Exception {
        JSONArray list = new JSONArray();
        int len = this.highest_level_array.length();
        if (this.highest_level_array != null) {
            for (int i = 0; i < len; i++) {
                if (i != index) {
                    list.put(this.highest_level_array.get(i));
                }
            }
        }
        return list;
    }

    public int getMainItemsCount() {
        try {
            return this.highest_level_array.length();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public void storeCollarText() {
        SQLiteDatabase sqldb = this.f49cr.getAppstvDatabase();
        String str = "DROP collar_text";
        String SQL_CREATE_COLLAR_TEXT_TABLE = "CREATE TABLE collar_text( ";
        try {
            JSONObject languages = new JSONObject(getCollarTextTranslated());
            Iterator<String> iter = languages.keys();
            while (iter.hasNext()) {
                String key = (String) iter.next();
                try {
                    String str2 = (String) languages.get(key);
                    StringBuilder sb = new StringBuilder();
                    sb.append(SQL_CREATE_COLLAR_TEXT_TABLE);
                    sb.append(key);
                    sb.append(" text,");
                    SQL_CREATE_COLLAR_TEXT_TABLE = sb.toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        String SQL_CREATE_COLLAR_TEXT_TABLE2 = SQL_CREATE_COLLAR_TEXT_TABLE.substring(0, SQL_CREATE_COLLAR_TEXT_TABLE.length() - 1);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(SQL_CREATE_COLLAR_TEXT_TABLE2);
        sb2.append(" )");
        String SQL_CREATE_COLLAR_TEXT_TABLE3 = sb2.toString();
        Log.d(TAG, SQL_CREATE_COLLAR_TEXT_TABLE3);
        UtilSQLite.executeQuery(sqldb, SQL_CREATE_COLLAR_TEXT_TABLE3, true);
    }

    public String getCollarText() {
        try {
            return this.highest_level_object.getString("collar_text");
        } catch (Exception e) {
            return "Error occurred while retrieving collar text";
        }
    }

    public String getCollarTextTranslated() {
        try {
            return this.highest_level_object.getString("collar_text_languages");
        } catch (Exception e) {
            return "Error occurred while retrieving collar text languages";
        }
    }

    public int getSubItemsCount(int main_menu_item_index) {
        try {
            return Integer.parseInt(this.highest_level_array.getJSONObject(main_menu_item_index).getString("sub_items_count"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public JSONObject getMainItemJSON(int index) {
        try {
            return this.highest_level_array.getJSONObject(index);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getMainItemValue(int index, String key) {
        try {
            return getMainItemJSON(index).getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject getSubItemJSON(int main_item_index, int sub_item_index) {
        try {
            return getMainItemJSON(main_item_index).getJSONArray("sub_items").getJSONObject(sub_item_index);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getSubItemValue(int main_item_index, int sub_item_index, String key) {
        try {
            return getMainItemJSON(main_item_index).getJSONArray("sub_items").getJSONObject(sub_item_index).getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] getSubMenuItemNames(int main_menu_item_index) {
        String[] arr = new String[getSubItemsCount(main_menu_item_index)];
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            arr[i] = getSubItemValue(main_menu_item_index, i, "item_name");
        }
        return arr;
    }

    public String[] getSubMenuItemNames(int main_menu_item_index, String language_code) {
        String[] arr = new String[getSubItemsCount(main_menu_item_index)];
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            String temp = getSubItemValue(main_menu_item_index, i, "item_name_translated");
            try {
                JSONObject jso = new JSONObject(temp);
                Log.d(TAG, temp);
                temp = jso.getString(language_code);
            } catch (Exception e) {
            }
            arr[i] = temp;
        }
        return arr;
    }

    public String[] getSubMenuItemNamesVector(int main_menu_item_index) {
        String[] arr = new String[getSubItemsCount(main_menu_item_index)];
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            arr[i] = getSubItemValue(main_menu_item_index, i, "item_name");
        }
        return arr;
    }
}
