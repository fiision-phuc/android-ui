//  Project name: [Android] RandomConcept
//  File name   : HueView.java
//
//  Author      : phuc
//  Created date: 11/25/14
//  Version     : 1.00
//  --------------------------------------------------------------
//  Copyright (C) 2014 Fiision Studio. All rights reserved.
//  --------------------------------------------------------------

package com.fiision.lib.ui.colorpicker;


import android.content.*;
import android.graphics.*;
import android.graphics.Shader.*;
import android.util.*;


public class HuePickerView extends ColorPickerView {

    static private float METRIC_STEP = 0.16667f;


    // Class's constructors
    public HuePickerView(Context context) {
        super(context);
    }

    public HuePickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }


    // Class's override methods
    @Override
    protected void onDraw(Canvas canvas) {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

        float[] locations = new float[]{0.00f, METRIC_STEP, METRIC_STEP * 2, METRIC_STEP * 3, METRIC_STEP * 4, METRIC_STEP * 5, 1.0f};
        int[] colors = new int[]{0xffff0000,
                0xffffff00,
                0xff00ff00,
                0xff00ffff,
                0xff0000ff,
                0xffff00ff,
                0xffff0000};
        p.setShader(new LinearGradient(0, 0, mWidth, 0, colors, locations, TileMode.CLAMP));
        p.setStrokeWidth(mHeight - mPaddingTop - mPaddingBottom);

        canvas.drawLine(mPaddingLeft, mCenterY, (mWidth - mPaddingRight), mCenterY, p);
        super.onDraw(canvas);
    }
}
