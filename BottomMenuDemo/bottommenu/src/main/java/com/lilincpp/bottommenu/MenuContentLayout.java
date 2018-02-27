package com.lilincpp.bottommenu;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * Created by lilin on 2018/2/27.
 */

class MenuContentLayout extends LinearLayout {

    private TextView mTitle;
    private TableLayout mMenuItemList;

    public MenuContentLayout(Context context) {
        super(context);
    }

    public MenuContentLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuContentLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTitle = findViewById(R.id.ll_bottom_menu_tv_title);
        mMenuItemList = findViewById(R.id.ll_bottom_menu_tl_list);
    }
}
