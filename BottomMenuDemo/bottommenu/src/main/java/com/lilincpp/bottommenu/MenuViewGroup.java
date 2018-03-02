package com.lilincpp.bottommenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2018/3/1.
 */

public final class MenuViewGroup extends FrameLayout {

    private static final String TAG = "MenuViewGroup";

    public MenuViewGroup(@NonNull Context context) {
        super(context);
    }

    public MenuViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG, "onLayout: " );
        if (mListener != null) {
            mListener.onLayoutChange();
        }
    }

    private OnLayoutChangeListener mListener;

    public void addLayoutChangeListener(OnLayoutChangeListener listener) {
        mListener = listener;
    }

    public void removeLayoutChangeListener() {
        mListener = null;
    }

    interface OnLayoutChangeListener {
        void onLayoutChange();
    }
}
