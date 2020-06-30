package com.example.hades.androidpo._1_render_op.layout._viewstub;

import android.app.Fragment;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.example.hades.androidpo.R;

public class ViewStubFragment extends Fragment {
    private static final String TAG = ViewStubFragment.class.getSimpleName();

    private ViewStub viewStub;
    private View mapView;
    private View mRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewstub_layout, container, false);
        view.findViewById(R.id.showMap).setOnClickListener(view1 -> showMap());
        view.findViewById(R.id.hideMap).setOnClickListener(view1 -> hideMap());
        mRoot = view;
        viewStub = view.findViewById(R.id.map_stub);
        mapView = mRoot.findViewById(R.id.map_view);
        return view;
    }

    /*
    // Click hide
    hideMap: START, viewStub=GONE,mapView=null
    hideMap: END, viewStub=GONE,mapView=null

    // Click hide
    hideMap: START, viewStub=GONE,mapView=null
    hideMap: END, viewStub=GONE,mapView=null

    // Click show
    showMap:START viewStub=GONE,mapView=null
    showMap: viewStub.setVisibility(View.VISIBLE)
    showMap: before second time find id, viewStub=GONE
    showMap: after second time find id, viewStub=null
    showMap:END viewStub=null,mapView=VISIBLE

    // Click show
    showMap:START viewStub=null,mapView=VISIBLE
    showMap: mapView.setVisibility(View.VISIBLE)
    showMap:END viewStub=null,mapView=VISIBLE
     */

    // 加载布局的方式1：
    // mViewStub.inflate();
    // 加载布局的方式2：
    //mViewStub.setVisibility(View.VISIBLE);
    private void showMap() {
        Log.d(TAG, "showMap:START viewStub=" + convertVisibility(viewStub) + ",mapView=" + convertVisibility(mapView));
        if (null != mapView) {
            Log.d(TAG, "showMap: mapView.setVisibility(View.VISIBLE)");
            mapView.setVisibility(View.VISIBLE);
        } else {
            if (null != viewStub) {
                Log.d(TAG, "showMap: viewStub.setVisibility(View.VISIBLE)");
                mapView = viewStub.inflate();
                Log.d(TAG, "showMap: before second time find id, viewStub=" + convertVisibility(viewStub));
                viewStub = mRoot.findViewById(R.id.map_stub);
                Log.d(TAG, "showMap: after second time find id, viewStub=" + convertVisibility(viewStub));
            }
        }
        Log.d(TAG, "showMap:END viewStub=" + convertVisibility(viewStub) + ",mapView=" + convertVisibility(mapView));
    }

    private void hideMap() {
        Log.d(TAG, "hideMap: START, viewStub=" + convertVisibility(viewStub) + ",mapView=" + convertVisibility(mapView));
        if (null != mapView) {
            Log.d(TAG, "hideMap: mapView.setVisibility(View.VISIBLE)");
            mapView.setVisibility(View.GONE);
        }
        Log.d(TAG, "hideMap: END, viewStub=" + convertVisibility(viewStub) + ",mapView=" + convertVisibility(mapView));
    }

    private String convertVisibility(View view) {
        if (null == view) {
            return "null";
        }
        if (view.getVisibility() == View.VISIBLE) {
            return "VISIBLE";
        }
        if (view.getVisibility() == View.INVISIBLE) {
            return "INVISIBLE";
        }
        if (view.getVisibility() == View.GONE) {
            return "GONE";
        }
        return "Invalid visibility";
    }
}