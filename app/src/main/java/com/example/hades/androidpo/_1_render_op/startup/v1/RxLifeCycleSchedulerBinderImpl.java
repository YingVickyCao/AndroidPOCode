package com.example.hades.androidpo._1_render_op.startup.v1;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;

public class RxLifeCycleSchedulerBinderImpl extends RxLifeCycleBinderImpl implements RxLifeCycleSchedulerBinder {
    protected Scheduler scheduler;

    public RxLifeCycleSchedulerBinderImpl(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public <T> ObservableTransformer<T, T> bindWithScheduler() {
        return new ObservableTransformer<T, T>() {
            @NotNull
            @Override
            public ObservableSource<T> apply(@NotNull Observable<T> upstream) {
                return upstream.compose(bind())
                        .observeOn(scheduler);
            }
        };
//        return upstream -> upstream
//                .compose(bind())
//                .observeOn(scheduler);
    }
}
