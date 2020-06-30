package com.example.hades.androidpo;

import android.app.Application;
import android.util.Log;


public class ExampleApplication extends Application {
    private static final String TAG = ExampleApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        // PO:StrictMode
//        setStrictMode();
        super.onCreate();

//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//
//        installLeakCanary();
    }

    protected void setStrictMode() {

    }

    protected void installLeakCanary() {
        // no-op, LeakCanary is disabled in production.
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(TAG, "onTrimMemory: level=" + level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "onLowMemory: ");
    }
}