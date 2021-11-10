package com.example.hades.androidpo._1_render_op.startup;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hades.androidpo.R;

public class SlowRenderMainActivity extends AppCompatActivity {
    private static final String TAG = WelcomeActivity.class.getSimpleName();

    private TextView tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_slow_render_main_page);
        tip = this.findViewById(R.id.tip);

        //mStartHandler.sendEmptyMessageDelayed(0,1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tip.setText("Set text after heavy work");
                        }
                    });
                }
            }
        }).start();
    }

    //    mStartHandler.sendEmptyMessageDelayed(0, 0);
    // SESSION:Handler
    private Handler mStartHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };
}