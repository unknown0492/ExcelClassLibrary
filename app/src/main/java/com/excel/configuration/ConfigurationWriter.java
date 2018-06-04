package com.excel.configuration;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.excel.excelclasslibrary.UtilFile;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.excel.configuration.Constants.APPSTV_DATA_DIRECTORY_NAME;
import static com.excel.configuration.Constants.PATH_CONFIGURATION_FILE;

public class ConfigurationWriter {
	
	static ConfigurationWriter configurationWriter;
	final static String TAG = "ConfigurationWriter";
	
	String country, timezone, cms_ip, location, firmware_name, 
	is_reboot_scheduled, reboot_time, digital_signage_interval, weather_retry_interval, weather_refresh_interval,
	clock_weather_flip_interval, cms_sub_directory, protocol, hotspot_enabled, ssid, hotspot_password, room_no;
	String file_md5;
	static int KEY = 0;
	static int VALUE = 1;
	/*final static String COUNTRY = "country";
	final static String TIMEZONE = "timezone";
	final static String CMS_IP = "cms_ip";
	final static String LOCATION = "location";
	final static String FIRMWARE_NAME = "firmware_name";
	final static String IS_REBOOT_SCHEDULED = "is_reboot_scheduled";
	final static String REBOOT_TIME = "reboot_time";
	final static String DIGITAL_SIGNAGE_INTERVAL = "digital_signage_interval";
	final static String WEATHER_RETRY_INTERVAL = "weather_retry_interval";
	final static String WEATHER_REFRESH_INTERVAL = "weather_refresh_interval";
	final static String CLOCK_WEATHER_FLIP_INTERVAL = "clock_weather_flip_interval";
	final static String CMS_SUB_DIRECTORY = "cms_sub_directory";
	final static String PROTOCOL = "protocol";
	final static String HOTSPOT_ENABLED = "hotspot_enabled";
	final static String SSID = "ssid";
	final static String HOTSPOT_PASSWORD = "hotspot_password";
	final static String ROOM_NO = "room_no";*/
	
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
				/*try {
					configuration.createNewFile();
				} catch ( IOException e ) {
					e.printStackTrace();
				}*/
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

	public static String getAppstvDataDirectorypath(){
		String path = Environment.getExternalStorageDirectory() + File.separator + APPSTV_DATA_DIRECTORY_NAME;
		// Log.d( TAG, configuration_file_path );
		return path;
	}

	public boolean setRoomNumber( String room_no ){
		return amendConfigurationFile( ConfigurationReader.ROOM_NO, room_no );
	}

	public boolean setSSID( String ssid ){
		return amendConfigurationFile( ConfigurationReader.SSID, ssid );
	}

	public boolean setHotspotPassword( String hotspot_password ){
		return amendConfigurationFile( ConfigurationReader.HOTSPOT_PASSWORD, hotspot_password );
	}

	public boolean setAirplayEnabled( String airplay_enabled ){
		return amendConfigurationFile( ConfigurationReader.AIRPLAY_ENABLED, airplay_enabled );
	}

	public boolean setTimeZone( String time_zone ){
		return amendConfigurationFile( ConfigurationReader.TIMEZONE, time_zone );
	}

	public boolean setLocation( String location ){
		return amendConfigurationFile( ConfigurationReader.LOCATION, location );
	}

	public boolean setDateTimeFlipInterval( String date_time_flip_interval ){
		return amendConfigurationFile( ConfigurationReader.DATE_TIME_FLIP_INTERVAL, date_time_flip_interval );
	}

    public boolean setTetheringInfoFlipInterval( String tethering_info_flip_interval ){
        return amendConfigurationFile( ConfigurationReader.TETHERING_INFO_FLIP_INTERVAL, tethering_info_flip_interval );
    }

    public boolean setHasHotelLogoDisplay( String has_hotel_logo_display ){
        return amendConfigurationFile( ConfigurationReader.HAS_HOTEL_LOGO_DISPLAY, has_hotel_logo_display );
    }

    public boolean setHotelLogoFlipInterval( String hotel_logo_flip_interval ){
        return amendConfigurationFile( ConfigurationReader.HOTEL_LOGO_FLIP_INTERVAL, hotel_logo_flip_interval );
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
			String digital_signage_interval = jsonObject.getString( ConfigurationReader.DIGITAL_SIGNAGE_INTERVAL );
			String weather_retry_interval = jsonObject.getString( ConfigurationReader.WEATHER_RETRY_INTERVAL );
			String weather_refresh_interval = jsonObject.getString( ConfigurationReader.WEATHER_REFRESH_INTERVAL );
			String clock_weather_flip_interval = jsonObject.getString( ConfigurationReader.CLOCK_WEATHER_FLIP_INTERVAL );
			String hotspot_enabled = jsonObject.getString( ConfigurationReader.HOTSPOT_ENABLED );
			String ssid = jsonObject.getString( ConfigurationReader.SSID );
			String hotspot_password = jsonObject.getString( ConfigurationReader.HOTSPOT_PASSWORD );
			String room_no = jsonObject.getString( ConfigurationReader.ROOM_NO );
			String is_ots_completed = jsonObject.getString( ConfigurationReader.IS_OTS_COMPLETED );
			String language = jsonObject.getString( ConfigurationReader.LANGUAGE );
			String webservice_path = jsonObject.getString( ConfigurationReader.WEBSERVICE_PATH );
			String airplay_enabled = jsonObject.getString( ConfigurationReader.AIRPLAY_ENABLED );
            String date_time_flip_interval = jsonObject.getString( ConfigurationReader.DATE_TIME_FLIP_INTERVAL );
            String tethering_info_flip_interval = jsonObject.getString( ConfigurationReader.TETHERING_INFO_FLIP_INTERVAL );
            String has_hotel_logo_display = jsonObject.getString( ConfigurationReader.HAS_HOTEL_LOGO_DISPLAY );
			String hotel_logo_flip_interval = jsonObject.getString( ConfigurationReader.HOTEL_LOGO_FLIP_INTERVAL );
			String is_airplay_ssid_same_as_tethering_ssid = jsonObject.getString( ConfigurationReader.IS_AIRPLAY_SSID_SAME_AS_TETHERING_SSID );
			String airplay_ssid = jsonObject.getString( ConfigurationReader.AIRPLAY_SSID );
			String airplay_password = jsonObject.getString( ConfigurationReader.AIRPLAY_PASSWORD );
			String loading_screen_time = jsonObject.getString( ConfigurationReader.LOADING_SCREEN_TIME );
			String welcome_screen_type = jsonObject.getString( ConfigurationReader.WELCOME_SCREEN_TYPE );
			String collar_text_speed = jsonObject.getString( ConfigurationReader.COLLAR_TEXT_SPEED );
			String idle_timeout_interval = jsonObject.getString( ConfigurationReader.IDLE_TIMEOUT_INTERVAL );
			String is_weather_enabled = jsonObject.getString( ConfigurationReader.IS_WEATHER_ENABLED );
			String is_welcome_screen_enabled = jsonObject.getString( ConfigurationReader.IS_WELCOME_SCREEN_ENABLED );
			//String has_random_hotspot_password = jsonObject.getString( ConfigurationReader.HAS_RANDOM_HOTSPOT_PASSWORD );

			StringBuilder sb = new StringBuilder();
			sb.append( ConfigurationReader.COUNTRY ).append( "=" ).append( country ).append( "\n" );
			sb.append( ConfigurationReader.TIMEZONE ).append( "=" ).append( timezone ).append( "\n" );
			sb.append( ConfigurationReader.CMS_IP ).append( "=" ).append( cms_ip ).append( "\n" );
			sb.append( ConfigurationReader.CMS_SUB_DIRECTORY ).append( "=" ).append( cms_sub_directory ).append( "\n" );
			sb.append( ConfigurationReader.PROTOCOL ).append( "=" ).append( protocol ).append( "\n" );
			sb.append( ConfigurationReader.LOCATION ).append( "=" ).append( location ).append( "\n" );
			sb.append( ConfigurationReader.IS_REBOOT_SCHEDULED ).append( "=" ).append( is_reboot_scheduled ).append( "\n" );
			sb.append( ConfigurationReader.REBOOT_TIME ).append( "=" ).append( reboot_time ).append( "\n" );
			sb.append( ConfigurationReader.DIGITAL_SIGNAGE_INTERVAL ).append( "=" ).append( digital_signage_interval ).append( "\n" );
			sb.append( ConfigurationReader.WEATHER_RETRY_INTERVAL ).append( "=" ).append( weather_retry_interval ).append( "\n" );
			sb.append( ConfigurationReader.WEATHER_REFRESH_INTERVAL ).append( "=" ).append( weather_refresh_interval ).append( "\n" );
			sb.append( ConfigurationReader.CLOCK_WEATHER_FLIP_INTERVAL ).append( "=" ).append( clock_weather_flip_interval ).append( "\n" );
			sb.append( ConfigurationReader.HOTSPOT_ENABLED ).append( "=" ).append( hotspot_enabled ).append( "\n" );
			sb.append( ConfigurationReader.SSID ).append( "=" ).append( ssid ).append( "\n" );
			sb.append( ConfigurationReader.HOTSPOT_PASSWORD ).append( "=" ).append( hotspot_password ).append( "\n" );
			sb.append( ConfigurationReader.ROOM_NO ).append( "=" ).append( room_no ).append( "\n" );
			sb.append( ConfigurationReader.IS_OTS_COMPLETED ).append( "=" ).append( is_ots_completed ).append( "\n" );
			sb.append( ConfigurationReader.LANGUAGE ).append( "=" ).append( language ).append( "\n" );
			sb.append( ConfigurationReader.WEBSERVICE_PATH ).append( "=" ).append( webservice_path ).append( "\n" );
			sb.append( ConfigurationReader.AIRPLAY_ENABLED ).append( "=" ).append( airplay_enabled ).append( "\n" );
            sb.append( ConfigurationReader.DATE_TIME_FLIP_INTERVAL ).append( "=" ).append( date_time_flip_interval ).append( "\n" );
            sb.append( ConfigurationReader.TETHERING_INFO_FLIP_INTERVAL ).append( "=" ).append( tethering_info_flip_interval ).append( "\n" );
            sb.append( ConfigurationReader.HAS_HOTEL_LOGO_DISPLAY ).append( "=" ).append( has_hotel_logo_display ).append( "\n" );
			sb.append( ConfigurationReader.HOTEL_LOGO_FLIP_INTERVAL ).append( "=" ).append( hotel_logo_flip_interval ).append( "\n" );
			sb.append( ConfigurationReader.IS_AIRPLAY_SSID_SAME_AS_TETHERING_SSID ).append( "=" ).append( is_airplay_ssid_same_as_tethering_ssid ).append( "\n" );
			sb.append( ConfigurationReader.AIRPLAY_SSID ).append( "=" ).append( airplay_ssid ).append( "\n" );
			sb.append( ConfigurationReader.AIRPLAY_PASSWORD ).append( "=" ).append( airplay_password ).append( "\n" );
			sb.append( ConfigurationReader.LOADING_SCREEN_TIME ).append( "=" ).append( loading_screen_time ).append( "\n" );
			sb.append( ConfigurationReader.WELCOME_SCREEN_TYPE ).append( "=" ).append( welcome_screen_type ).append( "\n" );
			sb.append( ConfigurationReader.COLLAR_TEXT_SPEED ).append( "=" ).append( collar_text_speed ).append( "\n" );
			sb.append( ConfigurationReader.IDLE_TIMEOUT_INTERVAL ).append( "=" ).append( idle_timeout_interval ).append( "\n" );

			sb.append( ConfigurationReader.IS_WEATHER_ENABLED ).append( "=" ).append( is_weather_enabled ).append( "\n" );
			sb.append( ConfigurationReader.IS_WELCOME_SCREEN_ENABLED ).append( "=" ).append( is_welcome_screen_enabled ).append( "\n" );
			//sb.append( ConfigurationReader.HAS_RANDOM_HOTSPOT_PASSWORD ).append( "=" ).append( has_random_hotspot_password ).append( "\n" );


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
