//  Project name: [Android] RandomConcept
//  File name   : SaturationView.java
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


public class SaturationPickerView extends ColorPickerView {


    // Class's constructors
    public SaturationPickerView(Context context) {
        super(context);
    }

    public SaturationPickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }


    // Class's override methods
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

        paint.setShader(new LinearGradient(0, 0, mWidth, 0, 0xffffffff, 0xffff0000, TileMode.CLAMP));
        paint.setStrokeWidth(mHeight - mPaddingTop - mPaddingBottom);

        canvas.drawLine(mPaddingLeft, mCenterY, (mWidth - mPaddingRight), mCenterY, paint);
        super.onDraw(canvas);
    }
}
