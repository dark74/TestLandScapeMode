package com.upuphone.testlandscapemode;

import android.graphics.PixelFormat;
import android.view.WindowManager;

/**
 * @Description :
 * @Author : ke.dong
 * @Date : 2023/1/3 16:23
 * @Email : ke.dong@xjsd.com
 */
public class CustomLayout extends WindowManager.LayoutParams {
    public CustomLayout(int orientation) {
        super(0, 0, TYPE_APPLICATION_OVERLAY, FLAG_FULLSCREEN | FLAG_NOT_FOCUSABLE, PixelFormat.RGBX_8888);
        this.screenOrientation = orientation;
    }
}
