package com.lilincpp.bottommenu;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lilin on 2018/2/27.
 */

class ListMenuContentLayout extends LinearLayout {

    static final int LAYOUT_TYPE_GRID = 0x01; //网格模式
    static final int LAYOUT_TYPE_LINEAR = 0x02; //线性模式

    private TextView mTitle;
    private TableLayout mMenuItemList;
    private List<BottomMenu.MenuItem> mItems;
    private int mLayoutType = LAYOUT_TYPE_LINEAR;

    public ListMenuContentLayout(Context context) {
        super(context);
    }

    public ListMenuContentLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ListMenuContentLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTitle = findViewById(R.id.ll_bottom_menu_tv_title);
        mMenuItemList = findViewById(R.id.ll_bottom_menu_tl_list);
    }

    TextView getTitleView() {
        return mTitle;
    }

    void setItems(List<BottomMenu.MenuItem> items) {
        mItems = items;
    }

    void setLayoutType(int type) {
        mLayoutType = type;
    }
}
