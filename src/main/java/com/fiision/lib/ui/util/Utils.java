//  Project name: .get(Android] RandomConcept
//  File name   : Utils.java
//
//  Author      : phuc
//  Created date: 11/15/14
//  Version     : 1.00
//  --------------------------------------------------------------
//  Copyright (C) 2014 Fiision Studio. All rights reserved.
//  --------------------------------------------------------------

package com.fiision.lib.ui.util;


import android.content.res.*;


public class Utils {


    /** Metric converters. */
    static public int convertDipToPixel(int dip) {
        return Math.round(dip * Resources.getSystem().getDisplayMetrics().density);
    }
}
