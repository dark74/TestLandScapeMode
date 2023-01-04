package com.upuphone.testlandscapemode;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.display.DisplayManager;
import android.os.IBinder;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * @Description : 启动横屏window在虚拟屏中
 * @Author : ke.dong
 * @Date : 2023/1/3 16:26
 * @Email : ke.dong@xjsd.com
 */
public class AddAlertWindowService extends Service {
    private static final String TAG = "AddAlertWindowService";
    private View mView;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int displayId = intent.getIntExtra("displayId", 0);
        Log.d(TAG, "service will add window in displayId:" + displayId);
        DisplayManager displayManger = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        Display virtualDisplay = displayManger.getDisplay(displayId);
        if (virtualDisplay != null) {
            Context displayContext = createDisplayContext(virtualDisplay);
            WindowManager virtualDisplayWindowManager = (WindowManager) displayContext.getSystemService(WINDOW_SERVICE);
            int orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
            if (mView == null) {
                mView = new View(this);
            }
            CustomLayout customLayout = new CustomLayout(orientation);
            virtualDisplayWindowManager.addView(mView, customLayout);
            Toast.makeText(this, "添加Window成功", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "not find virtual display");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void startService(Context context, int displayId) {
        Intent intent = new Intent(context, AddAlertWindowService.class);
        intent.putExtra("displayId", displayId);
        context.startService(intent);
    }
}
