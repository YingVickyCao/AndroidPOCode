package com.example.hades.androidpo._1_render_op.startup.v2

import io.reactivex.ObservableTransformer

interface RxLifeCycleBinder {
    fun <T> bind(): ObservableTransformer<T, T>?
    fun terminate()
}