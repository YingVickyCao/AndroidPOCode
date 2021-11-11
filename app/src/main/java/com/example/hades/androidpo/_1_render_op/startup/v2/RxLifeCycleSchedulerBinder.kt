package com.example.hades.androidpo._1_render_op.startup.v2

import io.reactivex.ObservableTransformer

interface RxLifeCycleSchedulerBinder : RxLifeCycleBinder {
    fun <T> bindWithScheduler(): ObservableTransformer<T, T>?
}