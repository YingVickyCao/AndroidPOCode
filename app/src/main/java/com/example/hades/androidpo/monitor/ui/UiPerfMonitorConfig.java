package com.example.hades.androidpo.monitor.ui;

import android.os.Environment;
import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface UiPerfMonitorConfig {
    //time level
    public final int TIME_WARNING_LEVEL_1 = 100;   //
    public final int TIME_WARNING_LEVEL_2 = 300;//
    //
    public final int UI_PERF_LEVEL_0 = 0;
    public final int UI_PERF_LEVEL_1 = 1;
    public final int UI_PERF_LEVEL_2 = 2;

    //
    @IntDef({UI_PERF_LEVEL_0, UI_PERF_LEVEL_1, UI_PERF_LEVEL_2})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PER_LEVEL {
    }

    public final int UI_PERF_MONITER_START = 0x01;
    public final int UI_PERF_MONITER_STOP = 0x01 << 1;

    public final String LOG_PATH = Environment.getExternalStorageDirectory().getPath() + "/androidPO";
    public final String FILENAME = "UiMonitorLog";
}
