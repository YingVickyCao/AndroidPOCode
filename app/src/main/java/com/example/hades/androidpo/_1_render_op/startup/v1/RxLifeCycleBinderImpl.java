package com.example.hades.androidpo._1_render_op.startup.v1;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.PublishSubject;
import kotlin.Unit;

public class RxLifeCycleBinderImpl implements RxLifeCycleBinder {
    private static final String TAG = "RxLifeCycleBinderImpl";
    protected PublishSubject<Unit> guard = PublishSubject.create();

    @Override
    public <T> ObservableTransformer<T, T> bind() {
        return new ObservableTransformer<T, T>() {
            @NotNull
            @Override
            public ObservableSource<T> apply(@NotNull Observable<T> upstream) {
                ObservableSource<T> dest = upstream.takeUntil(guard);
                Log.e(TAG, "apply:upstream@" + upstream.toString() + ",dest@" + dest.hashCode());
                return dest;
            }
        };
//        return upstream -> upstream.takeUntil(guard);
    }

    @Override
    public void terminate() {
        Log.e(TAG, "terminate: ");
        guard.onNext(Unit.INSTANCE);
    }
}