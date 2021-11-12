package com.example.hades.androidpo._1_render_op.startup;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hades.androidpo.R;
import com.example.hades.androidpo._1_render_op.startup.v1.EndlessObserverRx2;
import com.example.hades.androidpo._1_render_op.startup.v1.RxLifeCycleAndroidBinderImpl;
import com.example.hades.androidpo._1_render_op.startup.v1.RxLifeCycleSchedulerBinder;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class SlowRenderMainActivity extends AppCompatActivity {
    private static final String TAG = "SlowRenderMainActivity";

    private TextView tip1;
    private TextView tip2;
    private PublishSubject<String> tip1Subject = PublishSubject.create();
    private PublishSubject<String> tip2Subject = PublishSubject.create();
    private static final int MOCK_HEAVY_WORK_SECONDS = 5;
    private RxLifeCycleSchedulerBinder mGuideSubject = new RxLifeCycleAndroidBinderImpl();

    protected <T> Observable<T> bindObservable(final Observable<T> source) {
        Observable<T> dest = source.compose(mGuideSubject.bindWithScheduler());
        Log.e(TAG, "bindObservable:source@" + source.toString() + ",dest@" + source.hashCode());
        return dest;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");

        setContentView(R.layout.activity_slow_render_main_page);
        tip1 = this.findViewById(R.id.tip1);
        tip2 = this.findViewById(R.id.tip2);

        findViewById(R.id.btn).setOnClickListener(view -> click());

        //mStartHandler.sendEmptyMessageDelayed(0,1000);
//        way1();
//        way2();

        bindObservable(tip1Subject).subscribe(new EndlessObserverRx2<String>() {
            @Override
            public void onNext(@NotNull String s) {
                Log.e(TAG, "onNext: bindObservable,tip1Subject  onNext:" + s);
                if (tip1 != null) {
                    tip1.setText(s);
                }
            }
        });
        bindObservable(tip2Subject).subscribe(new EndlessObserverRx2<String>() {
            @Override
            public void onNext(@NotNull String s) {
                Log.e(TAG, "onNext: bindObservable,tip2Subject  onNext:" + s);
                if (tip2 != null) {
                    tip2.setText(s);
                }
            }
        });
    }


    private void way1() {
        Log.e(TAG, "way1: ");
        Log.e(TAG, "way1,tip1 setText");
        tip1.setText("Text 1");
        new Thread(new Runnable() { // mock heavy work
            @Override
            public void run() {
                Log.e(TAG, "way1,run --->: ");
                for (int i = 0; i < MOCK_HEAVY_WORK_SECONDS; i++) {
                    Log.e(TAG, "way1,run: on,i:" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.e(TAG, "run: <----");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "way1,tip setText");
                        tip2.setText("Text 2");
                    }
                });
            }
        }).start();
    }

    private void way2() { // mock heavy work
        Log.e(TAG, "way2:tip setText ");
        tip1.setText("Text 1");
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            Log.e(TAG, "way2,run:--->");
            for (int i = 0; i < MOCK_HEAVY_WORK_SECONDS; i++) {
                Log.e(TAG, "way2,run:on,i:" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Log.e(TAG, "way2,run: ");
            }
            Log.e(TAG, "way2,run:<---");
            e.onNext(MOCK_HEAVY_WORK_SECONDS);
            e.onComplete();
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                               @Override
                               public void accept(@NonNull Integer integer) throws Exception {
                                   Log.e(TAG, "way2, accept: " + integer);
                                   Log.e(TAG, "way2, tip2 setText:");
                                   tip2.setText("Text 2");
                               }
                           }
                );
    }

    private void way3() {
        Log.e(TAG, "way3: ");
        Observable observable = Observable.create((ObservableOnSubscribe<Integer>) e -> {
            // Mock heavy work of 1 seconds
            Thread.sleep(1000);
            Log.e(TAG, "way3,run:--->");
            tip1Subject.onNext("Text 1");

            for (int i = 0; i < MOCK_HEAVY_WORK_SECONDS; i++) {
                Log.e(TAG, "way3,run:on,i:" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            Log.e(TAG, "way3,run:<---");
            e.onNext(MOCK_HEAVY_WORK_SECONDS);
            e.onComplete();
        });
        Log.e(TAG, "way3: observable@" + observable.hashCode());
        bindObservable(observable)
                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "way3, accept: " + integer);
                        tip2Subject.onNext("Text 2");
                    }
                });
    }

    private void click() {
//        way2();
        way3();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
        way3();
    }
}