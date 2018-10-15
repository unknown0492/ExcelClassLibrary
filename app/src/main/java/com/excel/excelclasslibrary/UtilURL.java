package com.excel.excelclasslibrary;

import com.excel.configuration.ConfigurationReader;

/**
 * Created by Sohail on 03-09-2016.
 */
public class UtilURL {

    /**
     *
     * @param key_value String array 2-dimensional containing name-value pairs of URL Parameters
     * @return parameter string concatenated in URL parameter form key=value&k1=value1&... so on
     */
    public static String getURLParamsFromPairs( String[][] key_value ){
        StringBuilder sb = new StringBuilder();
        for( int i = 0 ; i < key_value.length ; i++ ){
            sb.append( key_value[ i ][ 0 ] ).append( "=" ).append( key_value[ i ][ 1 ] ).append( "&" );
        }

        return sb.substring( 0, sb.length() - 1 );
    }

    /**
     *
     * Returns the Well formed URL for the Webservice File of CMS
     *
     * @return Well formed URL for webservice
     *
     *
     */
    public static String getWebserviceURL(){
        ConfigurationReader configurationReader = ConfigurationReader.getInstance();
        String ip_address = configurationReader.getCmsIp();
        return getWebserviceURL( ip_address );
    }

    /**
     *
     * Returns the Well formed URL for the Webservice File of CMS
     *
     * @param ip_address which needs to be substituted in the IP field in the URL
     * @return Well formed URL for webservice
     *
     *
     */
    public static String getWebserviceURL( String ip_address ){
        ConfigurationReader configurationReader = ConfigurationReader.getInstance();
        String URL = "";
        String cms_sub_directory = configurationReader.getCmsSubDirectory();

        if( cms_sub_directory.equals( "-" ) || cms_sub_directory.equals( "none" ) )
            URL = String.format( "%s://%s/%s", configurationReader.getProtocol(), ip_address, configurationReader.getWebservicePath() );
        else
            URL = String.format( "%s://%s/%s/%s", configurationReader.getProtocol(), ip_address, cms_sub_directory, configurationReader.getWebservicePath() );

        return URL;
    }

    /**
     *
     * Returns the Well formed URL for the Webservice File of CMS
     *
     * @param ip_address which needs to be substituted in the IP field in the URL
     * @param configurationReader which contains the configuration
     * @return Well formed URL for webservice
     *
     *
     */
    public static String getWebserviceURL( com.excel.digitalsignage.ConfigurationReader configurationReader, String ip_address ){
        String URL = "";
        String cms_sub_directory = configurationReader.getCmsSubDirectory();

        if( cms_sub_directory.equals( "-" ) || cms_sub_directory.equals( "none" ) )
            URL = String.format( "%s://%s/%s", configurationReader.getProtocol(), ip_address, configurationReader.getWebservicePath() );
        else
            URL = String.format( "%s://%s/%s/%s", configurationReader.getProtocol(), ip_address, cms_sub_directory, configurationReader.getWebservicePath() );

        return URL;
    }


    /**
     *
     * Returns the Well formed URL for the Root path of CMS
     *
     * @return Well formed URL for CMS Root path
     *
     *
     */
    public static String getCMSRootPath(){
        ConfigurationReader configurationReader = ConfigurationReader.getInstance();
        String ip_address = configurationReader.getCmsIp();
        return getCMSRootPath( ip_address );
    }


    /**
     *
     * Returns the Well formed URL for the Root path of CMS
     *
     * @param ip_address which needs to be substituted in the IP field in the URL
     * @return Well formed URL for CMS Root path
     *
     *
     */
    public static String getCMSRootPath( String ip_address ){
        ConfigurationReader configurationReader = ConfigurationReader.getInstance();
        String URL = "";
        String cms_sub_directory = configurationReader.getCmsSubDirectory();

        if( cms_sub_directory.equals( "-" ) || cms_sub_directory.equals( "none" ) )
            URL = String.format( "%s://%s/", configurationReader.getProtocol(), ip_address );
        else
            URL = String.format( "%s://%s/%s/", configurationReader.getProtocol(), ip_address, cms_sub_directory );

        return URL;
    }

    /**
     *
     * Returns the Well formed URL for the Root path of CMS
     *
     * @param configurationReader which needs to be used to retrieve the config info
     * @return Well formed URL for CMS Root path
     *
     *
     */
    public static String getCMSRootPath( com.excel.digitalsignage.ConfigurationReader configurationReader ){
        String URL = "";
        String cms_sub_directory = configurationReader.getCmsSubDirectory();
        String ip_address = configurationReader.getCmsIp();

        if( cms_sub_directory.equals( "-" ) || cms_sub_directory.equals( "none" ) )
            URL = String.format( "%s://%s/", configurationReader.getProtocol(), ip_address );
        else
            URL = String.format( "%s://%s/%s/", configurationReader.getProtocol(), ip_address, cms_sub_directory );

        return URL;
    }

}
