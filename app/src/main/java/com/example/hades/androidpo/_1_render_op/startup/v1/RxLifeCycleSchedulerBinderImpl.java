package com.example.hades.androidpo._1_render_op.startup.v1;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;

public class RxLifeCycleSchedulerBinderImpl extends RxLifeCycleBinderImpl implements RxLifeCycleSchedulerBinder {
    private static final String TAG = "RxLifeCycleSchedulerBinderImpl";
    protected Scheduler scheduler;

    public RxLifeCycleSchedulerBinderImpl(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    // 对Observabl进行变换，
    @Override
    public <T> ObservableTransformer<T, T> bindWithScheduler() {
        return new ObservableTransformer<T, T>() {
            @NotNull
            @Override
            public ObservableSource<T> apply(@NotNull Observable<T> upstream) {
                ObservableSource<T> source = upstream.compose(bind()).observeOn(scheduler);
                Log.e(TAG, "apply:upstream@" + upstream.toString() + ",dest@" + source.hashCode());
                return source;
            }
        };
//        return upstream -> upstream
//                .compose(bind())
//                .observeOn(scheduler);
    }
}
