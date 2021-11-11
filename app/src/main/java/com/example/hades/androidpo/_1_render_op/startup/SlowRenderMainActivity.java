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

    private RxLifeCycleSchedulerBinder mGuideSubject = new RxLifeCycleAndroidBinderImpl();

    protected <T> Observable<T> bindObservable(final Observable<T> source) {
        return source.compose(mGuideSubject.bindWithScheduler());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

        setContentView(R.layout.activity_slow_render_main_page);
        tip1 = this.findViewById(R.id.tip1);
        tip2 = this.findViewById(R.id.tip2);

        findViewById(R.id.btn).setOnClickListener(view -> click());

        //mStartHandler.sendEmptyMessageDelayed(0,1000);
//        way1();
        way2();
        bindObservable(tip1Subject).subscribe(new EndlessObserverRx2<String>() {
            @Override
            public void onNext(@NotNull String s) {
                Log.d(TAG, "onNext: bindObservable,tip1Subject  onNext:" + s);
                if (tip1 != null) {
                    tip1.setText(s);
                }
            }
        });
        bindObservable(tip2Subject).subscribe(new EndlessObserverRx2<String>() {
            @Override
            public void onNext(@NotNull String s) {
                Log.d(TAG, "onNext: bindObservable,tip2Subject  onNext:" + s);
                if (tip2 != null) {
                    tip2.setText(s);
                }
            }
        });
    }


    private void way1() {
        Log.d(TAG, "way1: ");
        Log.d(TAG, "way1,tip1 setText");
        tip1.setText("Text 1");
        new Thread(new Runnable() { // mock heavy work
            @Override
            public void run() {
                Log.d(TAG, "way1,run --->: ");
                for (int i = 0; i < 15; i++) {
                    Log.d(TAG, "way1,run: on,i:" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, "run: <----");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "way1,tip setText");
                        tip2.setText("Text 2");
                    }
                });
            }
        }).start();
    }

    private void way2() { // mock heavy work
        Log.d(TAG, "way2: ");
        Log.d(TAG, "way2:tip setText ");
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            Log.d(TAG, "way2,run:--->");
            Thread.sleep(1000);
            tip1Subject.onNext("Text 1");
            for (int i = 0; i < 15; i++) {
                Log.d(TAG, "way2,run:on,i:" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Log.d(TAG, "way2,run: ");
            }
            Log.d(TAG, "way2,run:<---");
            e.onNext(15);
            e.onComplete();
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "way2, accept: " + integer);
                        Log.d(TAG, "way2, tip2 setText:");
                        tip2.setText("Text 2");
                    }
                });
    }

    private void way3() {
        Log.d(TAG, "way3: ");
        Log.d(TAG, "way3:tip setText ");
        tip1.setText("Text 1");
        bindObservable(Observable.create((ObservableOnSubscribe<Integer>) e -> {
            Log.d(TAG, "way3,run:--->");
            for (int i = 0; i < 15; i++) {
                Log.d(TAG, "way3,run:on,i:" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Log.d(TAG, "way3,run: ");
            }
            Log.d(TAG, "way3,run:<---");
            e.onNext(15);
            e.onComplete();
        }))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "way3, accept: " + integer);
                        tip2.setText("Text 2");
                    }
                });
    }

    private void click() {
        way2();
        way3();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }
}