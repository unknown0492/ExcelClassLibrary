package com.excel.configuration;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.excel.excelclasslibrary.UtilSQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by Sohail on 03-11-2016.
 */

public class LauncherJSONReader {
    String main_items_count, item_name, item_thumbnail, item_thumbnail_url,
            item_type, package_name, clickable, sub_items_count, sub_items, web_view_url, params;

    JSONObject highest_level_object;
    JSONArray highest_level_array;

    ConfigurationReader cr;

    final static String TAG = "LauncherJSONReader";


    public LauncherJSONReader( String json_string ){

        cr = ConfigurationReader.getInstance();

        try{
            highest_level_object = new JSONObject( json_string );
            highest_level_array = highest_level_object.getJSONArray( "main_items" );

            //storeCollarText();

            //int total_main_items = getMainItemsCount();

        }
        catch( Exception e ){
            e.printStackTrace();
        }
    }

    private JSONArray removeElementFromJSONArray( int index )throws Exception{
        JSONArray list = new JSONArray();
        int len = highest_level_array.length();
        if ( highest_level_array != null ) {
            for ( int i = 0 ; i < len ; i++ ){
                //Excluding the item at position
                if ( i != index ) {
                    list.put( highest_level_array.get( i ) );
                }
            }
        }
        return list;
    }

    public int getMainItemsCount(){
        try {
            return highest_level_array.length();//Integer.parseInt( /*highest_level_object.getString( "main_items_count" )*/  );
        } catch ( NumberFormatException e ) {
            e.printStackTrace();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return -1;
    }

    public void storeCollarText(){


        SQLiteDatabase sqldb = cr.getAppstvDatabase();

        String SQL_DROP_COLLAR_TEXT_TABLE = "DROP collar_text";
        String SQL_CREATE_COLLAR_TEXT_TABLE = "CREATE TABLE collar_text( ";

        try {
            JSONObject languages = new JSONObject( getCollarTextTranslated() );
            Iterator<String> iter = languages.keys();
            while ( iter.hasNext() ) {
                String key = iter.next();
                try {
                    String value = (String) languages.get( key );
                    SQL_CREATE_COLLAR_TEXT_TABLE += key + " text,";
                } catch ( JSONException e ) {
                    e.printStackTrace();
                }
            }
        }
        catch ( Exception e ){
            e.printStackTrace();
        }

        SQL_CREATE_COLLAR_TEXT_TABLE = SQL_CREATE_COLLAR_TEXT_TABLE.substring( 0, SQL_CREATE_COLLAR_TEXT_TABLE.length() - 1 );
        SQL_CREATE_COLLAR_TEXT_TABLE = SQL_CREATE_COLLAR_TEXT_TABLE + " )";

        Log.d( TAG, SQL_CREATE_COLLAR_TEXT_TABLE );

        UtilSQLite.executeQuery( sqldb, SQL_CREATE_COLLAR_TEXT_TABLE, true );

    }

    public String getCollarText(){
        try{
            return highest_level_object.getString( "collar_text" );
        }catch ( Exception e ){
            return "Error occurred while retrieving collar text";
        }
    }

    public String getCollarTextTranslated(){
        try{
            return highest_level_object.getString( "collar_text_languages" );
        }catch ( Exception e ){
            return "Error occurred while retrieving collar text languages";
        }
    }

    public int getSubItemsCount( int main_menu_item_index ){
        try {
            JSONObject jsonObject = highest_level_array.getJSONObject( main_menu_item_index );
            return Integer.parseInt( jsonObject.getString( "sub_items_count" ) );
        } catch ( NumberFormatException e ) {
            e.printStackTrace();
        } catch ( JSONException e ) {
            e.printStackTrace();
        }
        return -1;
    }

    public JSONObject getMainItemJSON( int index ){
        try {
            return highest_level_array.getJSONObject( index );
        } catch ( JSONException e ) {
            e.printStackTrace();
        }
        return null;
    }

    public String getMainItemValue( int index, String key ){
        try {
            return getMainItemJSON( index ).getString( key );
        } catch ( JSONException e ) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getSubItemJSON( int main_item_index, int sub_item_index ){
        JSONObject jsonObject = getMainItemJSON( main_item_index );
        try {
            JSONArray jsonArray	  = jsonObject.getJSONArray( "sub_items" );
            return jsonArray.getJSONObject( sub_item_index );
        } catch ( JSONException e ) {
            e.printStackTrace();
        }
        return null;
    }

    public String getSubItemValue( int main_item_index, int sub_item_index, String key ){
        JSONObject jsonObject = getMainItemJSON( main_item_index );
        try {
            JSONArray jsonArray	  = jsonObject.getJSONArray( "sub_items" );
            jsonObject =  jsonArray.getJSONObject( sub_item_index );
            return jsonObject.getString( key );
        } catch ( JSONException e ) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getSubMenuItemNames( int main_menu_item_index ){
        String arr[] = new String[ getSubItemsCount( main_menu_item_index ) ];
        String temp = "";
        for( int i = 0 ; i < arr.length ; i++ ){
            temp = getSubItemValue( main_menu_item_index, i, "item_name" );
            //temp[ i ] = getSubItemValue( main_menu_item_index, i, "item_name_translated" );

            /*if( temp.equals( "{SSID}" ) ){
                // Log.d( TAG, ","+temp+"," );
                arr[ i ] = "SSID : " + cr.getSSID();
                continue;
            }
            else if( temp.equals( "{PASSWORD}" ) ){
                // Log.d( TAG, ","+temp+"," );
                arr[ i ] = "Pass : " + cr.getHotspotPassword();
                continue;
            }*/

            arr[ i ] = temp;
        }
        return arr;
    }

    public String[] getSubMenuItemNames( int main_menu_item_index, String language_code ){
        String arr[] = new String[ getSubItemsCount( main_menu_item_index ) ];
        String temp = "";
        for( int i = 0 ; i < arr.length ; i++ ){
            //temp = getSubItemValue( main_menu_item_index, i, "item_name" );
            temp = getSubItemValue( main_menu_item_index, i, "item_name_translated" );
            // String item_name_json = ljr.getSubItemValue( last_index_of_main_menu, i, "item_name_translated" );
            try {
                JSONObject jso = new JSONObject( temp );
                Log.d( TAG, temp );
                temp = jso.getString( language_code );
            }
            catch ( Exception e ){
                //e.printStackTrace();
            }
            /*if( temp.equals( "{SSID}" ) ){
                // Log.d( TAG, ","+temp+"," );
                arr[ i ] = "SSID : " + cr.getSSID();
                continue;
            }
            else if( temp.equals( "{PASSWORD}" ) ){
                // Log.d( TAG, ","+temp+"," );
                arr[ i ] = "Pass : " + cr.getHotspotPassword();
                continue;
            }*/

            arr[ i ] = temp;
        }
        return arr;
    }

    public String[] getSubMenuItemNamesVector( int main_menu_item_index ){
        String arr[] = new String[ getSubItemsCount( main_menu_item_index ) ];
        String temp = "";
        for( int i = 0 ; i < arr.length ; i++ ){
            temp = getSubItemValue( main_menu_item_index, i, "item_name" );
            //temp[ i ] = getSubItemValue( main_menu_item_index, i, "item_name_translated" );

            /*if( temp.equals( "{SSID}" ) ){
                // Log.d( TAG, ","+temp+"," );
                arr[ i ] = "SSID : " + cr.getSSID();
                continue;
            }
            else if( temp.equals( "{PASSWORD}" ) ){
                // Log.d( TAG, ","+temp+"," );
                arr[ i ] = "Pass : " + cr.getHotspotPassword();
                continue;
            }*/

            arr[ i ] = temp;
        }
        return arr;
    }
}