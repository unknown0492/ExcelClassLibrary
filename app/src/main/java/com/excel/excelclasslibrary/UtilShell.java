package com.excel.excelclasslibrary;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UtilShell {
    public static String executeShellCommandWithOp(String... strings) {
        String res = "exception occurred ";
        try {
            Process su = Runtime.getRuntime().exec("su");
            DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
            InputStream response = su.getInputStream();
            for (String s : strings) {
                StringBuilder sb = new StringBuilder();
                sb.append(s);
                sb.append("\n");
                outputStream.writeBytes(sb.toString());
                outputStream.flush();
            }
            outputStream.writeBytes("exit\n");
            outputStream.flush();
            try {
                su.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String res2 = readFully(response);
            outputStream.close();
            su.destroy();
            return res2;
        } catch (IOException e2) {
            e2.printStackTrace();
            return res;
        }
    }

    public static String executeShellCommand(String... strings) {
        String res = "exception occurred ";
        try {
            Process su = Runtime.getRuntime().exec("su");
            DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
            for (String s : strings) {
                StringBuilder sb = new StringBuilder();
                sb.append(s);
                sb.append("\n");
                outputStream.writeBytes(sb.toString());
                outputStream.flush();
            }
            outputStream.writeBytes("exit\n");
            outputStream.flush();
            outputStream.close();
            su.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String executeNonSUShellCommandWithOp(String... strings) {
        String res = "exception occurred ";
        try {
            Process su = Runtime.getRuntime().exec("");
            DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
            InputStream response = su.getInputStream();
            for (String s : strings) {
                StringBuilder sb = new StringBuilder();
                sb.append(s);
                sb.append("\n");
                outputStream.writeBytes(sb.toString());
                outputStream.flush();
            }
            outputStream.writeBytes("exit\n");
            outputStream.flush();
            try {
                su.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return readFully(response);
        } catch (IOException e2) {
            e2.printStackTrace();
            return res;
        }
    }

    public static String executeNonSUShellCommand(String... strings) {
        String res = "exception occurred ";
        try {
            Process su = Runtime.getRuntime().exec("");
            DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
            InputStream response = su.getInputStream();
            for (String s : strings) {
                StringBuilder sb = new StringBuilder();
                sb.append(s);
                sb.append("\n");
                outputStream.writeBytes(sb.toString());
                outputStream.flush();
            }
            outputStream.writeBytes("exit\n");
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String readFully(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        while (true) {
            int read = is.read(buffer);
            int length = read;
            if (read == -1) {
                return baos.toString("UTF-8");
            }
            baos.write(buffer, 0, length);
        }
    }
}
