package com.example.hades.androidpo._1_render_op.startup.v2

import io.reactivex.ObservableTransformer
import io.reactivex.subjects.PublishSubject

open class RxLifeCycleBinderImpl : RxLifeCycleBinder {
    protected var guard = PublishSubject.create<Unit>()
    override fun <T> bind(): ObservableTransformer<T, T>? {
//        return new ObservableTransformer<T, T>() {
//            @NotNull
//            @Override
//            public ObservableSource<T> apply(@NotNull Observable<T> upstream) {
//                return upstream.takeUntil(subject);
//            }
//        };
        return ObservableTransformer { upstream -> upstream.takeUntil(guard) }
    }

    override fun terminate() {
        guard.onNext(Unit)
    }
}