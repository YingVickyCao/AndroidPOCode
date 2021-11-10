package com.example.hades.androidpo._2_memory_op._2_forbid_fast_click;

import android.view.View;

import static com.example.hades.androidpo._2_memory_op._2_forbid_fast_click.ButtonUtils.MIN_CLICK_DELAY_MS;

public abstract class NoRepeatClickListener implements View.OnClickListener {

    private int mId = -1;
    private long lastClickTs = 0;

    @Override
    public void onClick(View v) {
        long current = System.currentTimeMillis();
        int id = v.getId();
        if (mId != id) {
            mId = id;
            lastClickTs = current;
            onFilterRepeatClick(v);
            return;
        }

        long duration = current - lastClickTs;
        if ((0 < duration) && (duration < MIN_CLICK_DELAY_MS)) {
            return;
        }
        
        lastClickTs = current;
        onFilterRepeatClick(v);
    }

    protected abstract void onFilterRepeatClick(View v);
}
