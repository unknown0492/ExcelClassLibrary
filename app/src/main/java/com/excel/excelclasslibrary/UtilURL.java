package com.excel.excelclasslibrary;

import com.excel.configuration.ConfigurationReader;

public class UtilURL {
    public static String getURLParamsFromPairs(String[][] key_value) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < key_value.length; i++) {
            sb.append(key_value[i][0]);
            sb.append("=");
            sb.append(key_value[i][1]);
            sb.append("&");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static String getWebserviceURL() {
        return getWebserviceURL(ConfigurationReader.getInstance().getCmsIp());
    }

    public static String getWebserviceURL(String ip_address) {
        ConfigurationReader configurationReader = ConfigurationReader.getInstance();
        String str = "";
        String cms_sub_directory = configurationReader.getCmsSubDirectory();
        if (cms_sub_directory.equals(Constants.TAG_SEPARATOR) || cms_sub_directory.equals("none")) {
            return String.format("%s://%s/%s", new Object[]{configurationReader.getProtocol(), ip_address, configurationReader.getWebservicePath()});
        }
        return String.format("%s://%s/%s/%s", new Object[]{configurationReader.getProtocol(), ip_address, cms_sub_directory, configurationReader.getWebservicePath()});
    }

    public static String getWebserviceURL(com.excel.digitalsignage.ConfigurationReader configurationReader, String ip_address) {
        String str = "";
        String cms_sub_directory = configurationReader.getCmsSubDirectory();
        if (cms_sub_directory.equals(Constants.TAG_SEPARATOR) || cms_sub_directory.equals("none")) {
            return String.format("%s://%s/%s", new Object[]{configurationReader.getProtocol(), ip_address, configurationReader.getWebservicePath()});
        }
        return String.format("%s://%s/%s/%s", new Object[]{configurationReader.getProtocol(), ip_address, cms_sub_directory, configurationReader.getWebservicePath()});
    }

    public static String getCMSRootPath() {
        return getCMSRootPath(ConfigurationReader.getInstance().getCmsIp());
    }

    public static String getCMSRootPath(String ip_address) {
        ConfigurationReader configurationReader = ConfigurationReader.getInstance();
        String str = "";
        String cms_sub_directory = configurationReader.getCmsSubDirectory();
        if (cms_sub_directory.equals(Constants.TAG_SEPARATOR) || cms_sub_directory.equals("none")) {
            return String.format("%s://%s/", new Object[]{configurationReader.getProtocol(), ip_address});
        }
        return String.format("%s://%s/%s/", new Object[]{configurationReader.getProtocol(), ip_address, cms_sub_directory});
    }

    public static String getCMSRootPath(com.excel.digitalsignage.ConfigurationReader configurationReader) {
        String str = "";
        String cms_sub_directory = configurationReader.getCmsSubDirectory();
        String ip_address = configurationReader.getCmsIp();
        if (cms_sub_directory.equals(Constants.TAG_SEPARATOR) || cms_sub_directory.equals("none")) {
            return String.format("%s://%s/", new Object[]{configurationReader.getProtocol(), ip_address});
        }
        return String.format("%s://%s/%s/", new Object[]{configurationReader.getProtocol(), ip_address, cms_sub_directory});
    }
}
