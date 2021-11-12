package com.example.hades.androidpo._1_render_op.startup.v1;

import android.util.Log;

public abstract class EndlessObserverRx2<T> extends FiniteObserverRx2<T> {
    private static final String TAG = "EndlessObserverRx2";
    @Override
    public void onComplete() {
        Log.e(TAG, "onComplete: ");
    }
}
