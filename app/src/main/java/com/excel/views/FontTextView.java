package com.excel.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.excel.excelclasslibrary.R;
import com.excel.util.FontManager;

/**
 * TextView subclass which allows the user to define a truetype font file to use as the view's typeface.
 */
public class FontTextView extends androidx.appcompat.widget.AppCompatTextView {

    public FontTextView(Context context) {
        this(context, null);
    }

    public FontTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (isInEditMode())
            return;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FontTextView );

        if (ta != null) {
            String fontAsset = ta.getString( R.styleable.FontTextView_typefaceAsset );

            if (!TextUtils.isEmpty(fontAsset)) {
                Typeface tf = FontManager.getInstance( context ).getFont(fontAsset);
                int style = Typeface.NORMAL;
                float size = getTextSize();

                if (getTypeface() != null)
                    style = getTypeface().getStyle();

                if (tf != null)
                    setTypeface(tf, style);
                else
                    Log.d("FontText", String.format("Could not create a font from asset: %s", fontAsset));
            }
        }
    }
}