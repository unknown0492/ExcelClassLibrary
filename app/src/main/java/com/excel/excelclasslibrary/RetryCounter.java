package com.excel.excelclasslibrary;

/**
 * Created by Sohail on 02-11-2016.
 */

public class RetryCounter{

    String property_name;

    public RetryCounter( String property_name ){
        this.property_name = property_name;
    }

    public void setRetryCount( int count ){
        UtilShell.executeShellCommandWithOp( "setprop "+property_name+" "+count );
    }

    public int getRetryCount(){
        String c = UtilShell.executeShellCommandWithOp( "getprop "+property_name );
        c = c.trim();
        int count = 1;
        if( c.equals( "" ) || c.equals( "0" ) ){
            count = 1;
            setRetryCount( count );
        }
        else{
            count = Integer.parseInt( c );
            count += 1;
            setRetryCount( count );
        }
        return count;
    }

    public long getRetryTime(){
        return retryTimerIncrementer( getRetryCount() );
    }

    public void reset(){
        UtilShell.executeShellCommandWithOp( "setprop "+property_name+" 0" );
    }

    /**
     *
     * Generates retry time milliseconds for functionalities which needs to retry again and again due to failures
     * @param try_count current retry count
     * @return milliseconds of the retry interval
     *
     */
    public static long retryTimerIncrementer( int try_count ){

        switch ( try_count ){
            case 0:
                return 10000;
            case 1:
                return 60000;
            case 2:
                return 120000;
            case 3:
                return 300000;
            default:
                return 600000;
        }

    }

}