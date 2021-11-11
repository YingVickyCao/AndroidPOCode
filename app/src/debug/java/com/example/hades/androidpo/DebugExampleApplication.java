package com.example.hades.androidpo;

import android.content.Context;
import android.os.StrictMode;

import com.example.hades.androidpo.monitor.time.TimeMonitorConfig;
import com.example.hades.androidpo.monitor.time.TimeMonitorManager;
/*

Logcat Filter = Displayed
I/ActivityManager: Displayed com.example.hades.androidpo/._1_render_op.DrawLayoutOPActivity: +385ms

APPLoginActivity: +2s154ms
APPMainActivity: +1s607m

 */

/*
 adb shell am start -S -W com.example.hades.androidpo/._1_render_op.DrawLayoutOPActivity
 ThisTime: 385
 TotalTime: 385
 WaitTime: 430
 Complete

APPLoginActivity:
    ThisTime: 2141
TotalTime: 2141
WaitTime: 2165
Complete
 */
public class DebugExampleApplication extends ExampleApplication {
    private static final String TAG = DebugExampleApplication.class.getSimpleName();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        TimeMonitorManager.getInstance().resetTimeMonitor(TimeMonitorConfig.TIME_MONITOR_ID_APPLICATION_START);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        TimeMonitorManager.getInstance().getTimeMonitor(TimeMonitorConfig.TIME_MONITOR_ID_APPLICATION_START).recodingTimeTag(TAG + "_" + TimeMonitorConfig.ON_START);
    }

    protected void setStrictMode() {
        StrictMode.setThreadPolicy(
                new StrictMode.ThreadPolicy.Builder()
                        .detectDiskReads()
                        .detectDiskWrites()
                        .detectNetwork()
                        //.detectAll() for all detectable problems
                        .penaltyLog()
//                        .penaltyDeath()
                        .penaltyDialog()
//                        .penaltyFlashScreen()
//                        .penaltyDeathOnNetwork()
//                        .penaltyDropBox()
                        .build());
    }
}
