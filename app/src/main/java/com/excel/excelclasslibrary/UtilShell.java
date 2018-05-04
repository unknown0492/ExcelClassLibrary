package com.excel.excelclasslibrary;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UtilShell {

    public static String executeShellCommandWithOp(String...strings) {
        String res = "exception occurred ";
        DataOutputStream outputStream = null;
        InputStream response = null;
        try{
            Process su = Runtime.getRuntime().exec("su");
            outputStream = new DataOutputStream(su.getOutputStream());
            response = su.getInputStream();

            for (String s : strings) {
                outputStream.writeBytes(s+"\n");
                outputStream.flush();
            }

            outputStream.writeBytes("exit\n");
            outputStream.flush();

            try {
                // su.waitFor(1, TimeUnit.MINUTES);
                //boolean lock = true;
                //su.wait( 5000 );
                su.waitFor();
                //su.destroy();
                //Thread.sleep( 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            res = readFully(response);
            outputStream.close();
            su.destroy();
        } catch (IOException e){
            e.printStackTrace();
        }
        return res;
    }

    public static String executeShellCommand(String...strings) {
        String res = "exception occurred ";
        DataOutputStream outputStream = null;
        InputStream response = null;
        try{
            Process su = Runtime.getRuntime().exec("su");
            outputStream = new DataOutputStream(su.getOutputStream());
            //response = su.getInputStream();

            for (String s : strings) {
                outputStream.writeBytes(s+"\n");
                outputStream.flush();
            }

            outputStream.writeBytes("exit\n");
            outputStream.flush();

            outputStream.close();
            su.destroy();

        } catch (IOException e){
            e.printStackTrace();
        }
        return res;
    }


    public static String executeNonSUShellCommandWithOp(String...strings) {
        String res = "exception occurred ";
        DataOutputStream outputStream = null;
        InputStream response = null;
        try{
            Process su = Runtime.getRuntime().exec( "" );
            outputStream = new DataOutputStream( su.getOutputStream() );
            response = su.getInputStream();

            for (String s : strings) {
                outputStream.writeBytes(s+"\n");
                outputStream.flush();
            }

            outputStream.writeBytes("exit\n");
            outputStream.flush();

            try {
                // su.waitFor(1, TimeUnit.MINUTES);
                //boolean lock = true;
                //su.wait( 5000 );
                su.waitFor();
                //su.destroy();
                //Thread.sleep( 1000 );
            } catch ( Exception e ) {
                e.printStackTrace();
            }
            res = readFully(response);
        } catch (IOException e){
            e.printStackTrace();
        }
        return res;
    }

    public static String executeNonSUShellCommand(String...strings) {
        String res = "exception occurred ";
        DataOutputStream outputStream = null;
        InputStream response = null;
        try{
            Process su = Runtime.getRuntime().exec( "" );
            outputStream = new DataOutputStream( su.getOutputStream() );
            response = su.getInputStream();

            for (String s : strings) {
                outputStream.writeBytes(s+"\n");
                outputStream.flush();
            }

            outputStream.writeBytes("exit\n");
            outputStream.flush();

        } catch (IOException e){
            e.printStackTrace();
        }
        return res;
    }
	
	public static String readFully(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = is.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        return baos.toString("UTF-8");
    }
}
