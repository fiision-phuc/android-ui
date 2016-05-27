//  Project name: [Android] RandomConcept
//  File name   : ColorView.java
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
import android.util.*;
import android.view.*;

import com.fiision.lib.ui.util.*;


public abstract class ColorPickerView extends ViewGroup {


    // Class's variables
    protected int mWidth;
    protected int mHeight;

    protected int mSquare;
    protected int mCenterX;
    protected int mCenterY;

    protected int mPaddingBottom;
    protected int mPaddingLeft;
    protected int mPaddingRight;
    protected int mPaddingTop;

    private float mValue;
    private ColorPickerViewListener mColorPickerViewListener;


    // Class's constructors
    public ColorPickerView(Context context) {
        super(context);
        setBackgroundColor(0x00000000);
    }
    public ColorPickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackgroundColor(0x00000000);
    }


    // Class's properties
    public void setColorPickerViewListener(ColorPickerViewListener colorPickerViewListener) {
        this.mColorPickerViewListener = colorPickerViewListener;
    }

    public float getValue() {
        int length = mWidth - mSquare;

        float value = (float) mCenterX / (float) length;
        value *= 1000;

        value = Math.round(value);
        value /= 1000;

        return value;
    }
    public void setValue(float mValue) {
        this.mValue = mValue;
        float centerX = mPaddingLeft + (mWidth - mPaddingLeft - mPaddingRight) * mValue;

        centerX *= 1000;
        centerX = Math.round(centerX);
        centerX /= 1000;

        mCenterX = (int) centerX;

        // Redraw view
        invalidate();
    }


    // Class's override methods
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        paint.setColor(0xffffffff);

        if (mValue > 0.0f) {
            this.setValue(this.mValue);
            this.mValue = 0.0f;
        }
        canvas.drawCircle(mCenterX, mCenterY, mSquare / 2, paint);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        this.mWidth = width;
        this.mHeight = height;

        this.mSquare = Math.min(width, height);

        this.mPaddingBottom = Utils.convertDipToPixel(8);
        this.mPaddingTop = Utils.convertDipToPixel(8);
        this.mPaddingLeft = Math.round(this.mSquare / 2);
        this.mPaddingRight = Math.round(this.mSquare / 2);

        this.mCenterY = Math.round(height / 2);
        this.mCenterX = Math.min(Math.max(this.mCenterX, this.mPaddingLeft), (this.mWidth - this.mPaddingRight));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: {
                Rect rect = new Rect();
                rect.left = mCenterX - Math.round(mSquare / 2);
                rect.top = 0;
                rect.right = rect.left + mSquare;
                rect.bottom = mSquare;

                int coordX = Math.round(event.getX());
                int coordY = Math.round(event.getY());
                return rect.contains(coordX, coordY);
            }

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP: {
                return true;
            }

            case MotionEvent.ACTION_MOVE: {
                int deltaX = Math.round(event.getX());
                mCenterX = deltaX;

                // Validate min/max mValue
                this.mCenterX = Math.min(Math.max(this.mCenterX, this.mPaddingLeft), (this.mWidth - this.mPaddingRight));

                // Redraw view
                invalidate();

                // Notify callBack
                if (mColorPickerViewListener != null) {
                    int length = mWidth - mSquare;
                    float value = (float) mCenterX / (float) length;
                    value *= 1000;

                    value = Math.round(value);
                    value /= 1000;
                    mColorPickerViewListener.onValueChanged(this, value);
                }
                return true;
            }
        }
        return super.onTouchEvent(event);
    }


    // CallBack interfaces
    public interface ColorPickerViewListener {

        void onValueChanged(ColorPickerView colorPickerView, float value);
    }
}
