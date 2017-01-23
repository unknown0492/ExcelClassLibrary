package com.excel.excelclasslibrary;

import android.content.Context;
import android.content.Intent;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtilMisc {
	
	/**
	 * 
	 * Start an application using its package name
	 * @param context application context
	 * @param package_name The name of the package
	 * @return true if no exception is raised, false otherwise with stack trace of the exception occurred in the Logs
	 * 
	 */
	public static boolean startApplicationUsingPackageName( Context context, String package_name ){
		try{
			Intent app_intent = context.getPackageManager().getLaunchIntentForPackage( package_name );
			context.startActivity( app_intent );
			return true;
		}
		catch( Exception e ){
			e.printStackTrace();
		}
		return false;
	}

	/**
	 *
	 * Generate MD5 Hash of String
	 * @param text The String whose md5 needs to be generated
	 * @return md5 of the supplied string
	 *
	 */
	public static String md5String( String text ){
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance( "MD5" );
		} catch ( NoSuchAlgorithmException e ) {
			e.printStackTrace();
			return null;
		}
		m.reset();
		m.update( text.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger( 1, digest );
		String hashtext = bigInt.toString( 16 );
		// Now we need to zero pad it if you actually want the full 32 chars.
		while( hashtext.length() < 32 ){
			hashtext = "0" + hashtext;
		}
		return hashtext;
	}




}
