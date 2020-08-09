package com.excel.excelclasslibrary;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class UtilNetwork {
    public static final String TAG = "UtilNetwork";

    public static String getMacAddress(Context context) {
        String str = TAG;
        String address = "";
        try {
            NetworkInfo ni = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (ni == null) {
                Log.i(str, "ni is Null");
            }
            if (!ni.isConnected()) {
                return address;
            }
            int network_type = ni.getType();
            if (network_type == 1) {
                String address2 = trimMac(UtilShell.executeShellCommandWithOp("ip addr show wlan0"));
                StringBuilder sb = new StringBuilder();
                sb.append("WiFi mac : ");
                sb.append(address2);
                Log.i(str, sb.toString());
                return address2;
            }
            if (network_type == 9) {
                return trimMac(UtilShell.executeShellCommandWithOp("ip addr show eth0"));
            }
            return address;
        } catch (Exception e) {
            Log.i(str, e.getMessage().toString());
        }
        return "error";
    }

    public static String trimMac(String op) {
        String[] arr = op.split("\n");
        for (int i = 0; i < arr.length; i++) {
            String str = "link/ether";
            if (arr[i].contains(str)) {
                String s = arr[i].trim();
                String s2 = s.substring(s.indexOf(str) + 11, 28).trim();
                StringBuilder sb = new StringBuilder();
                sb.append("Trimmed Mac : ");
                sb.append(s2);
                Log.i(TAG, sb.toString());
                return s2.trim();
            }
        }
        return "error";
    }

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo state : info) {
                    if (state.getState() == State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static String mapToUrlParams( Map<String,String> url_params ){
        Set<String> set = url_params.keySet();
        Iterator<String> iterator = set.iterator();
        String params = "";
        while( iterator.hasNext() ){
            String key = iterator.next();
            String value = url_params.get( key );
            params += key + "=" + value + "&";
        }
        return params.substring( 0, params.length() - 1 );
    }

    public static String makeRequestForData( String url, String request_method, Map<String,String> url_parameters ){
        HttpURLConnection con;

        try{
            if ( request_method.equals( "GET" ) ) {
                StringBuilder sb = new StringBuilder();
                sb.append(url);
                sb.append("?");
                sb.append(mapToUrlParams( url_parameters ) );
                con = (HttpURLConnection) new URL(sb.toString()).openConnection();
                con.setRequestMethod(request_method);
                con.setDoOutput(true);
                con.setConnectTimeout( 10000 );
            } else {
                con = (HttpURLConnection) new URL(url).openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                con.setConnectTimeout( 10000 );
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(mapToUrlParams( url_parameters ));
                wr.flush();
                wr.close();
            }
            if ( con.getResponseCode() == 200 ) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer response = new StringBuffer();
                while (true) {
                    String readLine = in.readLine();
                    String inputLine = readLine;
                    if ( readLine != null ){
                        response.append( inputLine );
                    }
                    else {
                        in.close();
                        return response.toString();
                    }
                }
            } else {
                throw new Exception("No Response from server.");
            }
        }
        catch ( Exception e ){
            e.printStackTrace();
            return null;
        }
    }



    public static String makeRequestForData(String url, String request_method, String urlParameters) {
        HttpURLConnection con;
        String str = "GET";
        String str2 = "";
        try {
            if (request_method.equals(str)) {
                StringBuilder sb = new StringBuilder();
                sb.append(url);
                sb.append("?");
                sb.append(urlParameters);
                con = (HttpURLConnection) new URL(sb.toString()).openConnection();
                con.setRequestMethod(str);
                con.setDoOutput(true);
                con.setConnectTimeout( 10000 );
            } else {
                con = (HttpURLConnection) new URL(url).openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                con.setConnectTimeout( 10000 );
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();
            }
            if (con.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String resp = "";
                StringBuffer response = new StringBuffer();
                char[] cArr = new char[65535];
                FileOutputStream fos = new FileOutputStream(UtilFile.createFileIfNotExist("Launcher", "temp.txt"));
                while (true) {
                    String readLine = in.readLine();
                    String inputLine = readLine;
                    if (readLine != null) {
                        response.append(inputLine);
                    } else {
                        fos.close();
                        in.close();
                        return response.toString();
                    }
                }
            } else {
                throw new Exception("No Response from server.");
            }
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Exception : ");
            sb2.append(e.toString());
            Log.i(TAG, sb2.toString());
            return null;
        }
    }

    public static String getLocalIpAddressIPv4(Context ct) {
        String str = TAG;
        try {
            NetworkInfo ni = ((ConnectivityManager) ct.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (ni == null) {
                Log.i(str, "ni is null");
                return null;
            }
            int network_type = -1;
            if (ni.isConnected()) {
                network_type = ni.getType();
            }
            if (network_type == 1) {
                WifiInfo connectionInfo = ((WifiManager) ct.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo();
                String ip = trimIp(UtilShell.executeShellCommandWithOp("ip addr show wlan0"));
                StringBuilder sb = new StringBuilder();
                sb.append("wlan0 IP : ");
                sb.append(ip);
                Log.i(str, sb.toString());
                return ip;
            }
            if (network_type == 9) {
                String ip2 = trimIp(UtilShell.executeShellCommandWithOp("ip addr show eth0"));
                if (ip2 == null) {
                    ip2 = trimIp(UtilShell.executeShellCommandWithOp("ip addr show eth1"));
                    if (ip2 == null) {
                        ip2 = "error";
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("eth1 IP : ");
                        sb2.append(ip2);
                        Log.i(str, sb2.toString());
                    }
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("eth0 IP : ");
                    sb3.append(ip2);
                    Log.i(str, sb3.toString());
                }
                return ip2;
            }
            return null;
        } catch (Exception e) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Exception : ");
            sb4.append(e.getMessage());
            Log.i(str, sb4.toString());
        }
        return "error";
    }

    public static String getConnectedNetworkInterfaceName(Context context) {
        NetworkInfo ni = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (ni == null) {
            Log.i(TAG, "ni is null");
            return null;
        }
        int network_type = -1;
        if (ni.isConnected()) {
            network_type = ni.getType();
        }
        if (network_type == 1) {
            return "WiFi";
        }
        if (network_type == 9) {
            return "Ethernet";
        }
        return null;
    }

    public static String trimIp(String op) {
        String[] arr = op.split("\n");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].contains("inet")) {
                String s = arr[i].trim();
                String s2 = s.substring(s.indexOf("index") + 5, s.indexOf("/")).trim();
                StringBuilder sb = new StringBuilder();
                sb.append("Output : ");
                sb.append(s2);
                String sb2 = sb.toString();
                String str = TAG;
                Log.i(str, sb2);
                String[] splitted = s2.split("[.]");
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Splitted length : ");
                sb3.append(splitted.length);
                Log.i(str, sb3.toString());
                if (splitted.length == 4) {
                    return s2;
                }
                return null;
            }
        }
        return "error";
    }

    public static boolean isThisInterfaceActive(String op) {
        String[] arr = op.split("\n");
        for (String contains : arr) {
            if (contains.contains("inet ")) {
                return true;
            }
        }
        return false;
    }

    public static String getActiveInterfaceShortName(Context context) {
        String str = TAG;
        String interfaceName = "error";
        try {
            NetworkInfo ni = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            String i_f = "error";
            if (ni == null) {
                Log.i(str, "ni is null");
                return i_f;
            }
            int network_type = -1;
            if (ni.isConnected()) {
                network_type = ni.getType();
            }
            if (network_type == 1) {
                WifiInfo connectionInfo = ((WifiManager) context.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo();
                if (isThisInterfaceActive(UtilShell.executeShellCommandWithOp("ip addr show wlan0"))) {
                    i_f = "wlan0";
                }
                StringBuilder sb = new StringBuilder();
                sb.append("WiFi i/f is : ");
                sb.append(i_f);
                Log.i(str, sb.toString());
                return i_f;
            }
            if (network_type == 9) {
                String i_f2 = isThisInterfaceActive(UtilShell.executeShellCommandWithOp("ip addr show eth0")) ? "eth0" : i_f;
                if (i_f2.equals(i_f)) {
                    i_f2 = isThisInterfaceActive(UtilShell.executeShellCommandWithOp("ip addr show eth1")) ? "eth1" : i_f;
                    if (i_f2.equals(i_f)) {
                        i_f2 = i_f;
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("eth1 i/f is : ");
                        sb2.append(i_f2);
                        Log.i(str, sb2.toString());
                    }
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("eth0 i/f is : ");
                    sb3.append(i_f2);
                    Log.i(str, sb3.toString());
                }
                return i_f2;
            }
            return interfaceName;
        } catch (Exception e) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Exception : ");
            sb4.append(e.getMessage());
            Log.i(str, sb4.toString());
        }
        return "error";
    }
}
