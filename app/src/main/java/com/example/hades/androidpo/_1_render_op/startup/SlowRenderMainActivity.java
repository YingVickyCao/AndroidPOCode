package com.example.hades.androidpo._1_render_op.startup;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hades.androidpo.R;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SlowRenderMainActivity extends AppCompatActivity {
    private static final String TAG = "SlowRenderMainActivity";

    private TextView tip;
    private TextView tip2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

        setContentView(R.layout.activity_slow_render_main_page);
        tip = this.findViewById(R.id.tip);
        tip2 = this.findViewById(R.id.tip2);

        //mStartHandler.sendEmptyMessageDelayed(0,1000);
//        way1();
        way2();
    }

    private void way1() {
        Log.d(TAG, "way1: ");
        Log.d(TAG, "way1,tip1 setText");
        tip.setText("Text 1");
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
        tip.setText("Text 1");
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            Log.d(TAG, "way2,run:--->");
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