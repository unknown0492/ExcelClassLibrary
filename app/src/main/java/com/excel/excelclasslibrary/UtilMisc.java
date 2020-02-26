package com.excel.excelclasslibrary;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class UtilMisc {
    public static boolean startApplicationUsingPackageName(Context context, String package_name) {
        try {
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(package_name));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String md5String(String text) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(text.getBytes());
            String hashtext = new BigInteger(1, m.digest()).toString(16);
            while (hashtext.length() < 32) {
                StringBuilder sb = new StringBuilder();
                sb.append("0");
                sb.append(hashtext);
                hashtext = sb.toString();
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Locale getCustomLocaleLanguageConstant() {
        String language_code = UtilShell.executeShellCommandWithOp("getprop language_code").trim();
        if (language_code.equals("")) {
            return new Locale("en");
        }
        return new Locale(language_code);
    }

    public static void sendExplicitInternalBroadcast( Context context, String action, Class clas ){
        Intent in = new Intent( context, clas );
        in.setAction( action );
        context.sendBroadcast( in );
    }

    public static void sendExplicitInternalBroadcast( Context context, Intent in, String action, Class clas ){
        in.setAction( action );
        context.sendBroadcast( in );
    }

    public static void sendExplicitExternalBroadcast( Context context, String action, String packageName, String fullyQualifiedClassName ){
        Intent in = new Intent();
        ComponentName cn = new ComponentName( packageName, fullyQualifiedClassName );
        in.setComponent( cn );
        in.setAction( action );
        context.sendBroadcast( in );
    }

    public static void sendExplicitExternalBroadcast( Context context, Intent in, String action, String packageName, String fullyQualifiedClassName ){
        ComponentName cn = new ComponentName( packageName, fullyQualifiedClassName );
        in.setComponent( cn );
        in.setAction( action );
        context.sendBroadcast( in );
    }
}
