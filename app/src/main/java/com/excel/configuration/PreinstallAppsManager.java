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

    public PreinstallAppsManager(){}

    /*public PreinstallAppsManager( Context context ){
        configurationReader = ConfigurationReader.getInstance();
        String preinstall_apps = UtilFile.readData( "appstv_data", "preinstall_apps" );
        //Log.d( TAG, "preinstall_apps : "+preinstall_apps );
        String temp[] = preinstall_apps.split( "," );
        //Log.d( TAG, "temp.length: " + temp.length );
        String t[] = new String[ TOTAL_OPTIONS ];
        int j = 0, k = 0;
        papps_arr = new String[ temp.length/TOTAL_OPTIONS ][ TOTAL_OPTIONS ];
        for( int i = 0 ; i < temp.length ; i++ ){

            //Log.d( TAG, String.format( "Putting %s in t[ %d ]", temp[ i ], j ) );
            t[ j++ ] = temp[ i ];
            if( (i + 1)%TOTAL_OPTIONS == 0 ){
                //Log.d( TAG, "" + (i+1) + " reached " );
                j = 0;
                //Log.d( TAG, String.format( "putting papps_arr[ %d ] = t", k ) );
                papps_arr[ k++ ] = t;
                t = new String[ TOTAL_OPTIONS ];
            }

        }

        *//*for( int l = 0 ; l < papps_arr.length ; l++ ){
            Log.d( TAG, "" + l + "->" );
            for( int m = 0 ; m < papps_arr[ l ].length ; m++ ){
                Log.d( TAG, "   " + m + "->" + papps_arr[ l ][ m ]);
            }
        }*//*
    }*/

    public boolean writePreinstallAppsFile( JSONArray jsonArray ){
        JSONObject jsonObject = null;


        try{
            //jsonArray = jsonObject.getJSONArray( "info" );
            String preinstall_apps = "";
            String package_name, md5, show, wipe_cache, button_id, force_kill;
            for( int i = 0 ; i < jsonArray.length() ; i++ ){
                jsonObject = jsonArray.getJSONObject( i );
                package_name = jsonObject.getString( "package_name" );
                md5          = jsonObject.getString( "md5" );
                button_id    = jsonObject.getString( "button_id" );
                show         = jsonObject.getString( "show" ).equals( "0" )?"dont_show":"show";
                wipe_cache   = jsonObject.getString( "wipe_cache" ).equals( "0" )?"dont_clear":"clear";
                force_kill   = jsonObject.getString( "force_kill" ).equals( "0" )?"dont_force_kill":"force_kill";

                // preinstall_apps += package_name + "," + button_id + "," show + "," + wipe_cache + ",";
                preinstall_apps += String.format( "%s,%s,%s,%s,%s,%s,", package_name, md5, button_id, show, wipe_cache, force_kill );
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

