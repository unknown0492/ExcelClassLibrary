package com.excel.excelclasslibrary;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.View;

public class UtilAlert {
    public static Builder createCustomDialog(Context ct, String title, String message, View view) {
        Builder alert = new Builder(ct);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setCancelable(false);
        alert.setView(view);
        return alert;
    }
}
