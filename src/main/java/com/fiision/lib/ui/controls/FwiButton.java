package com.fiision.lib.ui.controls;


import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.util.*;
import android.widget.*;

import com.fiision.lib.ui.*;


public class FwiButton extends Button {
    private static final String TAG = FwiButton.class.getSimpleName();


    // Class's constructors
    public FwiButton(Context context) {
        super(context);
    }
    public FwiButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }
    public FwiButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray typedArray = ctx.obtainStyledAttributes(attrs, R.styleable.FwiUI);
        String customFont = typedArray.getString(R.styleable.FwiUI_customFont);

        setCustomFont(ctx, customFont);
        typedArray.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        try {
            Typeface tf = Typeface.createFromAsset(ctx.getAssets(), asset);
            setTypeface(tf);
            return true;
        }
        catch (Exception ex) {
            Log.e(TAG, String.format("Could not load typeface: %s", asset), ex);
            return false;
        }
    }


}
