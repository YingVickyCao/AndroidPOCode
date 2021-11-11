package com.example.hades.androidpo._1_render_op.startup.v1;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class RxLifeCycleAndroidBinderImpl extends RxLifeCycleSchedulerBinderImpl {
    public RxLifeCycleAndroidBinderImpl() {
        this(AndroidSchedulers.mainThread());
    }

    public RxLifeCycleAndroidBinderImpl(Scheduler scheduler) {
        super(scheduler);
    }
}
