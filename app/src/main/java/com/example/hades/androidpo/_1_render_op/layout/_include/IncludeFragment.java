package com.example.hades.androidpo._1_render_op.layout._include;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hades.androidpo.R;

public class IncludeFragment extends Fragment {
    private static final String TAG = IncludeFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.include_layout, container, false);
    }
}