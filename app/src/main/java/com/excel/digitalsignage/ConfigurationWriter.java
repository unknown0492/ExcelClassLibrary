package com.excel.digitalsignage;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.excel.digitalsignage.ConfigurationReader;
import com.excel.excelclasslibrary.UtilFile;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.excel.digitalsignage.Constants.PATH_CONFIGURATION_FILE;

public class ConfigurationWriter {
	
	static ConfigurationWriter configurationWriter;
	final static String TAG = "ConfigurationWriter";

	String country;
	String timezone;
	String cms_ip;
	String location;
	String is_reboot_scheduled;
	String reboot_time;
	String cms_sub_directory;
	String protocol;
	String is_ots_completed;
	String webservice_path;
	String loading_screen_time;
	String collar_text_speed;
	static String file_md5 = "";

	static int KEY = 0;
	static int VALUE = 1;
	
	public static ConfigurationWriter getInstance( Context context ){
		if( configurationWriter == null ){
			configurationWriter = new ConfigurationWriter();
			/**
			 * 
			 * 1. Check if configuration file exist on sdcard
			 * 2. If does not exist, then show error and do not proceed 
			 * 
			 */
			
			File configuration = new File( configurationWriter.getConfigurationFilePath() );

			// Step-1
			if( ! configuration.exists() ){
				// Step-2
				try {
					configuration.createNewFile();
                    Log.i( TAG, "Created empty Configuration file !" );
				} catch ( IOException e ) {
					e.printStackTrace();
				}
				//CustomItems.showCustomToast( context, "warning", "Configuration file is empty !", 6000 );
				Log.e( TAG, "Configuration file is empty !" );
				// return null;
			}
			
		}
		return configurationWriter;
	}
	
	private String getConfigurationFilePath(){
		String configuration_file_path = Environment.getExternalStorageDirectory() + File.separator + PATH_CONFIGURATION_FILE;
		// Log.d( TAG, configuration_file_path );
		return configuration_file_path;
	}

	public boolean setTimeZone( String time_zone ){
		return amendConfigurationFile( ConfigurationReader.TIMEZONE, time_zone );
	}

	public boolean setLocation( String location ){
		return amendConfigurationFile( ConfigurationReader.LOCATION, location );
	}

	private boolean amendConfigurationFile( String key, String value ){
		FileInputStream fis = null;
		BufferedReader reader = null;
		StringBuilder configuration_data;
		try{
			fis = new FileInputStream( new File( configurationWriter.getConfigurationFilePath() ) );
			reader = new BufferedReader( new InputStreamReader( fis ) );
		    configuration_data = new StringBuilder();
		    String line = null;

		    while ( ( line = reader.readLine() ) != null ){

		    	if( line.contains( key ) ){
		    		line = key + "=" + value;
		    	}

		    	configuration_data.append( line ).append( "\n" );
		    }

		    UtilFile.saveDataToFile( new File( configurationWriter.getConfigurationFilePath() ), configuration_data.toString().trim() );

		    reader.close();
		    fis.close();
		}
		catch ( Exception e ) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static boolean writeAllConfigurations( Context context, String json ){
		configurationWriter = ConfigurationWriter.getInstance( context );
        File configuration = new File( configurationWriter.getConfigurationFilePath() );
        try {
            configuration.createNewFile();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
		try {
			JSONObject jsonObject = new JSONObject( json );
			String country = jsonObject.getString( ConfigurationReader.COUNTRY );
			String timezone = jsonObject.getString( ConfigurationReader.TIMEZONE );
			String cms_ip = jsonObject.getString( ConfigurationReader.CMS_IP );
			String cms_sub_directory = jsonObject.getString( ConfigurationReader.CMS_SUB_DIRECTORY );
			String protocol = jsonObject.getString( ConfigurationReader.PROTOCOL );
			String location = jsonObject.getString( ConfigurationReader.LOCATION );
			String is_reboot_scheduled = jsonObject.getString( ConfigurationReader.IS_REBOOT_SCHEDULED );
			String reboot_time = jsonObject.getString( ConfigurationReader.REBOOT_TIME );
			String is_ots_completed = jsonObject.getString( ConfigurationReader.IS_OTS_COMPLETED );
			String webservice_path = jsonObject.getString( ConfigurationReader.WEBSERVICE_PATH );
			String loading_screen_time = jsonObject.getString( ConfigurationReader.LOADING_SCREEN_TIME );
			String collar_text_speed = jsonObject.getString( ConfigurationReader.COLLAR_TEXT_SPEED );

			StringBuilder sb = new StringBuilder();
			sb.append( ConfigurationReader.COUNTRY ).append( "=" ).append( country ).append( "\n" );
			sb.append( ConfigurationReader.TIMEZONE ).append( "=" ).append( timezone ).append( "\n" );
			sb.append( ConfigurationReader.CMS_IP ).append( "=" ).append( cms_ip ).append( "\n" );
			sb.append( ConfigurationReader.CMS_SUB_DIRECTORY ).append( "=" ).append( cms_sub_directory ).append( "\n" );
			sb.append( ConfigurationReader.PROTOCOL ).append( "=" ).append( protocol ).append( "\n" );
			sb.append( ConfigurationReader.LOCATION ).append( "=" ).append( location ).append( "\n" );
			sb.append( ConfigurationReader.IS_REBOOT_SCHEDULED ).append( "=" ).append( is_reboot_scheduled ).append( "\n" );
			sb.append( ConfigurationReader.REBOOT_TIME ).append( "=" ).append( reboot_time ).append( "\n" );
			sb.append( ConfigurationReader.IS_OTS_COMPLETED ).append( "=" ).append( is_ots_completed ).append( "\n" );
			sb.append( ConfigurationReader.WEBSERVICE_PATH ).append( "=" ).append( webservice_path ).append( "\n" );
			sb.append( ConfigurationReader.LOADING_SCREEN_TIME ).append( "=" ).append( loading_screen_time ).append( "\n" );
			sb.append( ConfigurationReader.COLLAR_TEXT_SPEED ).append( "=" ).append( collar_text_speed ).append( "\n" );

			File file = new File( configurationWriter.getConfigurationFilePath() );
			if( ! file.exists() )
				file.createNewFile();
			FileInputStream fis = new FileInputStream( file );
			BufferedReader reader = new BufferedReader( new InputStreamReader( fis ) );

			UtilFile.saveDataToFile( new File( configurationWriter.getConfigurationFilePath() ), sb.toString().trim() );

			reader.close();
			fis.close();

		} catch ( Exception e ) {
			e.printStackTrace();
			return false;
		}

		return true;

	}
	
}
