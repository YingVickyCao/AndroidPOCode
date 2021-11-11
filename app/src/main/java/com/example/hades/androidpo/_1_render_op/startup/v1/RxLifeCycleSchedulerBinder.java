package com.example.hades.androidpo._1_render_op.startup.v1;

import io.reactivex.ObservableTransformer;

public interface RxLifeCycleSchedulerBinder extends RxLifeCycleBinder {
    <T> ObservableTransformer<T, T> bindWithScheduler();
}