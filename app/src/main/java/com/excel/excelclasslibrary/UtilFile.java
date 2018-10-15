package com.excel.excelclasslibrary;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import static com.excel.excelclasslibrary.Constants.APP_TAG;
import static com.excel.excelclasslibrary.Constants.SDCARD_PATH;
import static com.excel.excelclasslibrary.Constants.TAG_SEPARATOR;

public class UtilFile {
	
	final public static String TAG = APP_TAG + TAG_SEPARATOR + "UtilFile";
	
	// Create file if Not Exist
    public static File createFileIfNotExist( String dir_name, String file_name ){
    	File dir = Environment.getExternalStoragePublicDirectory( dir_name );
    	if( ! dir.exists() )
    		dir.mkdirs();

    	File file = new File( dir.getAbsolutePath() + File.separator + file_name );
    	try{
    		if( ! file.exists() )
    			file.createNewFile();
    	}
    	catch( Exception e ){
    		e.printStackTrace();
    	}

    	return file;
    }
    
    // Get file
    public static File getFile( String dir_name, String file_name ){
    	File dir = new File( SDCARD_PATH + dir_name );
    	return new File( dir.getAbsolutePath() + File.separator + file_name );
    }
    
    public static boolean saveDataToFile( File file, String data ){
		try{
			FileOutputStream fos = new FileOutputStream( file );
			fos.write( data.getBytes() );
			fos.close();
			return true;
		}
		catch( Exception e ){
			e.printStackTrace();
		}
		return false;
	}

    public static boolean saveFile( String directory, String file_name, String file_extension, byte[] file_data ){
		String sdcard_path = Environment.getExternalStorageDirectory() + File.separator;

		String command = "mkdir " + sdcard_path + directory;
		UtilShell.executeShellCommandWithOp( command );

		command = "mkfile " +file_name+ "." +file_extension;
		UtilShell.executeShellCommandWithOp( command );
		
		try{

			File f_file;
			if( ( file_extension == null ) || ( file_extension.equals( "" ) ) )
				f_file = new File( sdcard_path + directory + File.separator + file_name );
			else
				f_file = new File( sdcard_path + directory + File.separator + file_name + "." + file_extension );

			FileOutputStream fos = new FileOutputStream( f_file );
			fos.write( file_data );
			fos.close();
			return true;
		}
		catch( Exception e ){
			e.printStackTrace();
		}
		
		return false;
	}
    
    public static String readData( String dir, String file_name ){
    	String data = "";
		try{
			FileInputStream fis = new FileInputStream( UtilFile.getFile( dir, file_name ) );
			int ch;
			data = "";
			while( ( ch = fis.read() ) != -1 ){
				data += ( char ) ch;
			}
		}
		catch( Exception e ){
			e.printStackTrace();
			data = "";
		}
		return data;
    }
    
    public static String readData( File file ){
		FileInputStream fis = null;
		BufferedReader reader = null;
		StringBuilder data;
		
		try{
			fis = new FileInputStream( file );
			reader = new BufferedReader( new InputStreamReader( fis ) );
			data = new StringBuilder();
		    String line = null;
		    
		    while ( ( line = reader.readLine() ) != null ){
		    	data.append( line ).append( "\n" );
		    }
		    reader.close();
		    fis.close();
		}
		catch ( Exception e ) {
			e.printStackTrace();
			return null;
		}
		return data.toString();
	}
    
    public static String[] readSingleLineCSVData( String dir, String file_name ){
    	String data = "";
		String arr[] = null;
    	try{
			FileInputStream fis = new FileInputStream( UtilFile.getFile( dir, file_name ) );
			int ch;
			data = "";
			while( ( ch = fis.read() ) != -1 ){
				data += ( char ) ch;
			}
			
			// if the file is empty, return null
			if( data.trim().equals( "" ) ){
				return null;
			}
			// break the csv data
			arr = data.split( "," );
		}
		catch( Exception e ){
			e.printStackTrace();
			arr = null;
		}
		return arr;
    } 

    public static String getCMSIpFromTextFile(){
    	String ip = "";
		try{
			FileInputStream fis = new FileInputStream( UtilFile.getFile( "OTS", "ip.txt" ) );
			int ch;
			ip = "";
			while( ( ch = fis.read() ) != -1 ){
				ip += (char)ch;
			}
		}
		catch( Exception e ){
			e.printStackTrace();
		}
		return ip;
    }
    
    public static String getRoomNoFromTextFile(){
    	String room_no = "";
		try{
			FileInputStream fis = new FileInputStream( UtilFile.getFile( "OTS", "room_no.txt" ) );
			int ch;
			room_no = "";
			while( ( ch = fis.read() ) != -1 ){
				room_no += (char)ch;
			}
		}
		catch( Exception e ){
			e.printStackTrace();
		}
		return room_no;
    }

	public static void copyFolder( File src, File dest ) throws IOException {

		if( src.isDirectory() ){

			//if directory not exists, create it
			if( !dest.exists() ){
				dest.mkdir();
				System.out.println( "Directory copied from "
						+ src + "  to " + dest);
			}

			//list all the directory contents
			String files[] = src.list();

			for ( String file : files ) {
				//construct the src and dest file structure
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				//recursive copy
				copyFolder(srcFile,destFile);
			}

		}else{
			//if file, then copy it
			//Use bytes stream to support all file types
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];

			int length;
			//copy the file content in bytes
			while ((length = in.read(buffer)) > 0){
				out.write(buffer, 0, length);
			}

			in.close();
			out.close();
			System.out.println("File copied from " + src + " to " + dest);
		}
	}

	public static void deleteDirectory( String path ){
		File dir = new File( path );
		if (dir.isDirectory())
		{
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++)
			{
				new File(dir, children[i]).delete();
			}
		}
	}
}
