package com.excel.configuration;

import com.excel.excelclasslibrary.UtilFile;

import java.io.File;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by Sohail on 03-02-2017.
 */
public class PreinstallApps{

    String package_name, md5, show, wipe_cache, button_id, force_kill;
    //ConfigurationReader configurationReader;
    static int TOTAL_OPTIONS = 6;
    static int OPTION_PACKAGE_NAME =   0;
    static int OPTION_MD5          =   1;
    static int OPTION_BUTTON_ID    =   2;
    static int OPTION_SHOW         =   3;
    static int OPTION_WIPE_CACHE   =   4;
    static int OPTION_FORCE_KILL   =   5;
    //String papps_arr[][];
    final static String TAG = "PreinstallApks";


    public PreinstallApps(){}

    public PreinstallApps( String package_name, String md5, String show, String wipe_cache, String button_id, String force_kill ){
        this.package_name = package_name;
        this.md5 = md5;
        this.show = show;
        this.wipe_cache = wipe_cache;
        this.button_id = button_id;
        this.force_kill = force_kill;
    }

    public static PreinstallApps[] getPreinstallApps(){
        ConfigurationReader configurationReader = ConfigurationReader.getInstance();
        File preinstall_apps_file = configurationReader.getPreinstallAppsFile( false );
        if( ! preinstall_apps_file.exists() ){
            preinstall_apps_file = configurationReader.getPreinstallAppsFile( true );
        }
        String preinstall_apps = UtilFile.readData( preinstall_apps_file );
        if( preinstall_apps.trim().equals( "" ) ){  // When preinstall_apps does not exist on /mnt/sdcard/appstv_data/
            // the read from the /system/appstv_Data/
            preinstall_apps = UtilFile.readData( configurationReader.getPreinstallAppsFile( true ) );
        }
        //Log.d( TAG, "preinstall_apps : "+preinstall_apps );
        String temp[] = preinstall_apps.split( "," );
       // Log.d( TAG, "temp.length: " + temp.length );
        String t[] = new String[ TOTAL_OPTIONS ];
        PreinstallApps[] papps = new PreinstallApps[ temp.length/TOTAL_OPTIONS ];
        Vector<PreinstallApps> v = new Vector<PreinstallApps>( temp.length/TOTAL_OPTIONS );
        int j = 0, k = 0;
        int iterator = 0;
        //papps_arr = new String[ temp.length/TOTAL_OPTIONS ][ TOTAL_OPTIONS ];
        for( int i = 0 ; i < temp.length/TOTAL_OPTIONS ; i++ ){
            iterator = i * 6;
            v.add( new PreinstallApps( temp[ OPTION_PACKAGE_NAME + iterator ],
                    temp[ OPTION_MD5 + iterator],
                    temp[ OPTION_SHOW + iterator ],
                    temp[ OPTION_WIPE_CACHE + iterator ],
                    temp[ OPTION_BUTTON_ID + iterator ],
                    temp[ OPTION_FORCE_KILL + iterator ] ) );

        }

        Enumeration<PreinstallApps> e = v.elements();
        int cc = 0 ;
        while(e.hasMoreElements()){
            PreinstallApps pp = e.nextElement();
            //Log.d( TAG, "" + pp.getPackageName() );
            papps[ cc++ ] = pp;
        }

        /*for( int i = 0 ; i < papps.length ; i++ ){
            Log.d( TAG, "" + i + " : " + papps[ i ].getPackageName() );
        }*/


        return papps;
    }

   /* public static PreinstallApps[] getPreinstallApps( JSONObject jsonObject ){
        String package_name, md5, show, wipe_cache, button_id, force_kill;
        //preinstall_apps = UtilFile.readData( configurationReader.getPreinstallAppsFile( true ) );
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
        //Log.d( TAG, "preinstall_apps : "+preinstall_apps );
        String temp[] = preinstall_apps.split( "," );
        // Log.d( TAG, "temp.length: " + temp.length );
        String t[] = new String[ TOTAL_OPTIONS ];
        PreinstallApps[] papps = new PreinstallApps[ temp.length/TOTAL_OPTIONS ];
        int j = 0, k = 0;
        //papps_arr = new String[ temp.length/TOTAL_OPTIONS ][ TOTAL_OPTIONS ];
        for( int i = 0 ; i < temp.length ; i++ ){

            //Log.d( TAG, String.format( "Putting %s in t[ %d ]", temp[ i ], j ) );
            t[ j++ ] = temp[ i ];

            if( (i + 1)%TOTAL_OPTIONS == 0 ){

                j = 0;

                papps[ k ] = new PreinstallApps( t[ OPTION_PACKAGE_NAME ], t[ OPTION_MD5 ], t[ OPTION_SHOW ], t[ OPTION_WIPE_CACHE ], t[ OPTION_BUTTON_ID ], t[ OPTION_FORCE_KILL ] );

                k++;
                t = new String[ TOTAL_OPTIONS ];
            }

        }


        return papps;
    }*/

    private void setPackageName( String package_name ){
        this.package_name = package_name;
    }

    private void setMD5( String md5 ){
        this.md5 = md5;
    }

    private void setShow( String show ){
        this.show = show;
    }

    private void setWipeCache( String wipe_cache ){
        this.wipe_cache = wipe_cache;
    }

    private void setButtonID( String button_id ){
        this.button_id = button_id;
    }

    private void setForceKill( String force_kill ){
        this.force_kill = force_kill;
    }






    public String getPackageName(){
        return package_name;
    }

    public String getMD5(){
        return md5;
    }

    public String getShow(){
        return show;
    }

    public String getWipeCache(){
        return wipe_cache;
    }

    public String getButtonID(){
        return button_id;
    }

    public String getForceKill(){
        return force_kill;
    }

}
