package com.example.hades.androidpo._2_memory_op._2_forbid_fast_click;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hades.androidpo.R;


public class ForbidFastClickExampleFragment extends Fragment {
    private static final String TAG = "ForbidFastClickFragment";

    private View loadingView;
    private View btn3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forbid_user_repeat_click, container, false);
        view.findViewById(R.id.btn).setOnClickListener(v -> clickBtn());
        view.findViewById(R.id.btn2).setOnClickListener(new NoRepeatClickListener() {
            @Override
            protected void onFilterRepeatClick(View v) {
                clickBtn2();
            }
        });
        btn3 = view.findViewById(R.id.btn3);
        btn3.setOnClickListener(v -> clickBtn3());
        loadingView = view.findViewById(R.id.progressBar);
        return view;
    }

    // Way 1
    private void clickBtn() {
        if (ButtonUtils.isFastClick()) {
            Log.d(TAG, "User is fast click,so ignore this click");
            return;
        }
        doHeavyWork();
    }

    private void doHeavyWork() {
        loadingView.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Do heavy work ...
                try {
                    for (int i = 1; i <= 2; i++) {
                        Log.d(TAG, "run: i:" + i);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(() -> {
                    loadingView.setVisibility(View.GONE);
                    btn3.setEnabled(true);
                    Toast.makeText(getContext(), "Click this button", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }

    // Way 2 :
    private void clickBtn2() {
        Log.d(TAG, "clickBtn2: ");
        doHeavyWork();
    }

    // Way 3 :
    private void clickBtn3() {
        if (loadingView.getVisibility() == View.VISIBLE) {
            Log.d(TAG, "clickBtn3: return");
            return;
        }
        Log.d(TAG, "clickBtn3: ");
        // Way 4 :
//        btn3.setEnabled(false);
        doHeavyWork();
    }
}
