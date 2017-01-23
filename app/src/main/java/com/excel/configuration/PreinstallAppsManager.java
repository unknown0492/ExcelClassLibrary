package com.excel.configuration;

import android.util.Log;

import com.excel.excelclasslibrary.UtilFile;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.excel.configuration.Constants.APPSTV_DATA_DIRECTORY_NAME;
import static com.excel.configuration.Constants.PREINSTALL_APPS_FILE_NAME;

/**
 * Created by Sohail on 02-11-2016.
 */

public class PreinstallAppsManager {

    final static String TAG = "PreinstallAppsManager";


    public boolean writePreinstallAppsFile( JSONArray jsonArray ){
        JSONObject jsonObject = null;

        try{
            //jsonArray = jsonObject.getJSONArray( "info" );
            String preinstall_apps = "";
            String package_name, show, wipe_cache, button_id;
            for( int i = 0 ; i < jsonArray.length() ; i++ ){
                jsonObject = jsonArray.getJSONObject( i );
                package_name = jsonObject.getString( "package_name" );
                button_id    = jsonObject.getString( "button_id" );
                show         = jsonObject.getString( "show" ).equals( "0" )?"dont_show":"show";
                wipe_cache   = jsonObject.getString( "wipe_cache" ).equals( "0" )?"dont_clear":"clear";

                // preinstall_apps += package_name + "," + button_id + "," show + "," + wipe_cache + ",";
                preinstall_apps += String.format( "%s,%s,%s,%s,", package_name, button_id, show, wipe_cache );
            }
            preinstall_apps = preinstall_apps.substring( 0, preinstall_apps.length() - 1 );
            Log.i( TAG, "preinstall_apps : "+preinstall_apps );

            return UtilFile.saveFile( APPSTV_DATA_DIRECTORY_NAME, PREINSTALL_APPS_FILE_NAME, null, preinstall_apps.getBytes() );
        }
        catch ( Exception e ){
            e.printStackTrace();
            return false;
        }
    }
}
