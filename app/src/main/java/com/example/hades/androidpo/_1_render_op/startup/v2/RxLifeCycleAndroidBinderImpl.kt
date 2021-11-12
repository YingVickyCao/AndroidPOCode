package com.example.hades.androidpo._1_render_op.startup.v2

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class RxLifeCycleAndroidBinderImpl @JvmOverloads constructor(scheduler: Scheduler = AndroidSchedulers.mainThread()) :
    RxLifeCycleSchedulerBinderImpl(scheduler)