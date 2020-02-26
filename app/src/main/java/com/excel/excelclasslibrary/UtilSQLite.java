package com.excel.excelclasslibrary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.io.File;

public class UtilSQLite {
    public static SQLiteDatabase makeDatabase(String dbname, Context context) {
        try {
            return context.openOrCreateDatabase(dbname, 0, null);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append(" Opening DB error : ");
            sb.append(e.toString());
            Log.i(null, sb.toString());
            return null;
        }
    }

    public static SQLiteDatabase makeExternalDatabase(String directory, String dbname, Context context) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(directory);
            sb.append(File.separator);
            sb.append(dbname);
            return SQLiteDatabase.openDatabase(sb.toString(), null, 268435456);
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(" Opening DB error : ");
            sb2.append(e.toString());
            Log.i(null, sb2.toString());
            return null;
        }
    }

    public static Cursor executeQuery(SQLiteDatabase sqldb, String sql, boolean insert) {
        if (!insert) {
            return sqldb.rawQuery(sql, null);
        }
        try {
            sqldb.execSQL(sql);
            return null;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error executing query : ");
            sb.append(e.toString());
            Log.i(null, sb.toString());
            return null;
        }
    }
}
