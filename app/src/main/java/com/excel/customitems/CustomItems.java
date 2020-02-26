package com.excel.customitems;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.excel.excelclasslibrary.R;

public class CustomItems {
    public static void showCustomToast(Context context, String type, String text, int duration) {
        Toast t = new Toast(context);
        LayoutInflater lf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int resId = -1;
        if (type.equals("success")) {
            resId = R.layout.toast_success;
        } else if (type.equals("error")) {
            resId = R.layout.toast_error;
        } else if (type.equals("warning")) {
            resId = R.layout.toast_warning;
        }
        RelativeLayout rl = (RelativeLayout) lf.inflate(resId, null);
        ((TextView) rl.findViewById(R.id.tv_toast_text)).setText(text);
        t.setView(rl);
        t.setDuration(duration);
        t.setGravity(8388693, 20, 0);
        t.show();
    }
}
