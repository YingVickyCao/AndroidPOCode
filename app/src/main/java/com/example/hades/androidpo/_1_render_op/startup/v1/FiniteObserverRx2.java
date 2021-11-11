package com.example.hades.androidpo._1_render_op.startup.v1;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import io.reactivex.observers.DisposableObserver;

public abstract class FiniteObserverRx2<T> extends DisposableObserver<T> {
    private static final String TAG = "FiniteObserverRx2";

    @Override
    public void onError(@NotNull Throwable e) {
        Log.e(TAG, "onError: ex:" + e.getMessage());
    }
}
