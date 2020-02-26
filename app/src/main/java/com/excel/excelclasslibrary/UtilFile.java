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
import java.io.PrintStream;

public class UtilFile {
    public static final String TAG = "ExcelClassLibrary-UtilFile";

    public static File createFileIfNotExist(String dir_name, String file_name) {
        File dir = Environment.getExternalStoragePublicDirectory(dir_name);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(dir.getAbsolutePath());
        sb.append(File.separator);
        sb.append(file_name);
        File file = new File(sb.toString());
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public static File getFile(String dir_name, String file_name) {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.SDCARD_PATH);
        sb.append(dir_name);
        File dir = new File(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(dir.getAbsolutePath());
        sb2.append(File.separator);
        sb2.append(file_name);
        return new File(sb2.toString());
    }

    public static boolean saveDataToFile(File file, String data) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data.getBytes());
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean saveFile(String directory, String file_name, String file_extension, byte[] file_data) {
        File f_file;
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append(File.separator);
        String sdcard_path = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("mkdir ");
        sb2.append(sdcard_path);
        sb2.append(directory);
        UtilShell.executeShellCommandWithOp(sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append("mkfile ");
        sb3.append(file_name);
        String str = ".";
        sb3.append(str);
        sb3.append(file_extension);
        UtilShell.executeShellCommandWithOp(sb3.toString());
        if (file_extension != null) {
            try {
                if (!file_extension.equals("")) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(sdcard_path);
                    sb4.append(directory);
                    sb4.append(File.separator);
                    sb4.append(file_name);
                    sb4.append(str);
                    sb4.append(file_extension);
                    f_file = new File(sb4.toString());
                    FileOutputStream fos = new FileOutputStream(f_file);
                    fos.write(file_data);
                    fos.close();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        StringBuilder sb5 = new StringBuilder();
        sb5.append(sdcard_path);
        sb5.append(directory);
        sb5.append(File.separator);
        sb5.append(file_name);
        f_file = new File(sb5.toString());
        try {
            FileOutputStream fos2 = new FileOutputStream(f_file);
            fos2.write(file_data);
            fos2.close();
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        return true;
    }

    public static String readData(String dir, String file_name) {
        String str = "";
        try {
            FileInputStream fis = new FileInputStream(getFile(dir, file_name));
            String str2 = "";
            while (true) {
                String data = str2;
                int read = fis.read();
                int ch = read;
                if (read == -1) {
                    return data;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(data);
                sb.append((char) ch);
                str2 = sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String readData(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder data = new StringBuilder();
            while (true) {
                String readLine = reader.readLine();
                String line = readLine;
                if (readLine != null) {
                    data.append(line);
                    data.append("\n");
                } else {
                    reader.close();
                    fis.close();
                    return data.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] readSingleLineCSVData(String dir, String file_name) {
        String[] arr;
        String str = "";
        try {
            FileInputStream fis = new FileInputStream(getFile(dir, file_name));
            String str2 = "";
            String data = str2;
            while (true) {
                int read = fis.read();
                int ch = read;
                if (read == -1) {
                    break;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(data);
                sb.append((char) ch);
                data = sb.toString();
            }
            if (data.trim().equals(str2)) {
                return null;
            }
            arr = data.split(",");
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
            arr = null;
        }
        return null;
    }

    public static String getCMSIpFromTextFile() {
        String ip = "";
        try {
            FileInputStream fis = new FileInputStream(getFile("OTS", "ip.txt"));
            String str = "";
            while (true) {
                ip = str;
                int read = fis.read();
                int ch = read;
                if (read == -1) {
                    break;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(ip);
                sb.append((char) ch);
                str = sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }

    public static String getRoomNoFromTextFile() {
        String room_no = "";
        try {
            FileInputStream fis = new FileInputStream(getFile("OTS", "room_no.txt"));
            String str = "";
            while (true) {
                room_no = str;
                int read = fis.read();
                int ch = read;
                if (read == -1) {
                    break;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(room_no);
                sb.append((char) ch);
                str = sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return room_no;
    }

    public static void copyFolder(File src, File dest) throws IOException {
        String[] files;
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append("Directory copied from ");
                sb.append(src);
                sb.append("  to ");
                sb.append(dest);
                printStream.println(sb.toString());
            }
            for (String file : src.list()) {
                copyFolder(new File(src, file), new File(dest, file));
            }
            return;
        }
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        while (true) {
            int read = in.read(buffer);
            int length = read;
            if (read > 0) {
                out.write(buffer, 0, length);
            } else {
                in.close();
                out.close();
                PrintStream printStream2 = System.out;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("File copied from ");
                sb2.append(src);
                sb2.append(" to ");
                sb2.append(dest);
                printStream2.println(sb2.toString());
                return;
            }
        }
    }

    public static void deleteDirectory(String path) {
        File dir = new File(path);
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (String file : children) {
                new File(dir, file).delete();
            }
        }
    }

    public static void copyFile(File src, File dest) {
        try {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            while (true) {
                int read = in.read(buffer);
                int length = read;
                if (read > 0) {
                    out.write(buffer, 0, length);
                } else {
                    in.close();
                    out.close();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
