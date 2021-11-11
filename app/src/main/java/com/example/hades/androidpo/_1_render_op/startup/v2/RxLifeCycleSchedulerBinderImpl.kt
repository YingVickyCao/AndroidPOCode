package com.example.hades.androidpo._1_render_op.startup.v2

import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler

open class RxLifeCycleSchedulerBinderImpl(protected var scheduler: Scheduler) : RxLifeCycleBinderImpl(), RxLifeCycleSchedulerBinder {
    override fun <T> bindWithScheduler(): ObservableTransformer<T, T>? {
        return ObservableTransformer { upstream ->
            upstream.compose(super.bind())
                .observeOn(scheduler)
        }
        //        return upstream -> upstream
//                .compose(bind())
//                .observeOn(scheduler);
    }
}