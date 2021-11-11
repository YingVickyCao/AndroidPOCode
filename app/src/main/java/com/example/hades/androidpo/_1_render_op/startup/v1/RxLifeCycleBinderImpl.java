package com.example.hades.androidpo._1_render_op.startup.v1;

import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.PublishSubject;
import kotlin.Unit;

public class RxLifeCycleBinderImpl implements RxLifeCycleBinder {
    protected PublishSubject<Unit> guard = PublishSubject.create();

    @Override
    public <T> ObservableTransformer<T, T> bind() {
//        return new ObservableTransformer<T, T>() {
//            @NotNull
//            @Override
//            public ObservableSource<T> apply(@NotNull Observable<T> upstream) {
//                return upstream.takeUntil(guard);
//            }
//        };
        return upstream -> upstream.takeUntil(guard);
    }
    
    @Override
    public void terminate() {
        guard.onNext(Unit.INSTANCE);
    }
}