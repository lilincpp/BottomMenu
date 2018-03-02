package com.lilincpp.bottommenu;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lilin on 2018/2/27.
 */

class ListMenuContentLayout extends LinearLayout implements View.OnClickListener {

    static final int LAYOUT_TYPE_GRID = 0x01; //网格模式
    static final int LAYOUT_TYPE_LINEAR = 0x02; //线性模式

    private static final int LAYOUT_HORIZONTAL_MAX_ITEM = 4; //横项最大子项数量
    private static final int LAYOUT_SHOW_MAX_ITEM_ROW = 4; //菜单列表，最多显示4行，多余的需要滑动查看


    private int PADDING16, PADDING32, PADDING64;
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
        PADDING16 = (int) dp2px(16, getDisplayMetrics());
        PADDING32 = (int) dp2px(32, getDisplayMetrics());
        PADDING64 = (int) dp2px(64, getDisplayMetrics());
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

    void create() {
        if (mItems.size() > 0) {
            if (mMenuItemList.getChildCount() > 0) {
                mMenuItemList.removeAllViews();
            }

            if (TextUtils.isEmpty(mTitle.getText())) {
                mTitle.setVisibility(GONE);
            }

            //网格布局的时候，增加边距
            if (mLayoutType == LAYOUT_TYPE_GRID) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mMenuItemList.getLayoutParams();
                params.leftMargin = PADDING16;
                params.rightMargin = PADDING16;
                params.bottomMargin = PADDING16;
                if (mTitle.getVisibility() == GONE) {
                    params.topMargin = PADDING16;
                }
            }

            //添加子项
            for (int i = 0; i < mItems.size(); ++i) {
                //获取当前行
                final TableRow row = getRow(i);
                final View item = inflateMenuItem(mItems.get(i));
                item.setTag(i);

                row.addView(item);

            }
        }
        measure();
    }

    @Override
    public void onClick(View v) {

    }

    private TableRow getRow(int inflatePosition) {
        if (mLayoutType == LAYOUT_TYPE_LINEAR) {
            TableRow row = inflateRow();
            mMenuItemList.addView(row);
            return row;
        } else {
            int rowIndex = inflatePosition / LAYOUT_HORIZONTAL_MAX_ITEM;
            TableRow row = (TableRow) mMenuItemList.getChildAt(rowIndex);
            if (row == null) {
                //如果为空，创建一个TableRow，并加到父布局
                row = inflateRow();
                mMenuItemList.addView(row);
            }
            return row;
        }
    }

    private TableRow inflateRow() {
        return new TableRow(getContext());
    }

    private TextView inflateMenuItem(BottomMenu.MenuItem item) {
        TextView tv = new TextView(getContext());
        tv.setText(TextUtils.isEmpty(item.name) ? " " : item.name);
        tv.setTextColor(Color.BLACK);
        tv.setPadding(PADDING16, PADDING16, PADDING16, PADDING16);
        if (item.drawable != null) {
            //控制绘制图片宽高
            final int width = item.drawable.getMinimumWidth() > PADDING32 ?
                    PADDING32 : item.drawable.getMinimumWidth();
            final int height = item.drawable.getMinimumHeight() > PADDING32 ?
                    PADDING32 : item.drawable.getMinimumHeight();
            item.drawable.setBounds(0, 0, width, height);
            if (mLayoutType == LAYOUT_TYPE_LINEAR) {
                //线性布局
                tv.setGravity(Gravity.LEFT | Gravity.START | Gravity.CENTER_VERTICAL);
                tv.setCompoundDrawablePadding(PADDING16);
                tv.setCompoundDrawables(item.drawable, null, null, null);
            } else {
                //网格布局
                tv.setGravity(Gravity.CENTER);
                tv.setCompoundDrawablePadding((PADDING16 / 2));
                tv.setCompoundDrawables(null, item.drawable, null, null);
            }
        }
        tv.setOnClickListener(this);
        //设置水波纹点击效果
        ViewSelectorUtils.setSelectorBackground(tv, Color.GRAY);
        return tv;
    }

    private void measure() {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        this.measure(w, h);

        if (mMenuItemList.getChildCount() > LAYOUT_SHOW_MAX_ITEM_ROW) {
            //调整菜单栏的高度，菜单中显示的子项只显示其4项
            final ScrollView view = (ScrollView) mMenuItemList.getParent();
            final int finalItemsHeight = mMenuItemList.getChildAt(0).getMeasuredHeight() * LAYOUT_SHOW_MAX_ITEM_ROW;
            LinearLayout.LayoutParams params = (LayoutParams) view.getLayoutParams();
            params.height = finalItemsHeight + mMenuItemList.getPaddingTop() + mMenuItemList.getPaddingBottom();
        }

    }

    private DisplayMetrics getDisplayMetrics() {
        return getContext().getResources().getDisplayMetrics();
    }

    public static float dp2px(@NonNull float dp, @NonNull DisplayMetrics metrics) {
        return dp * metrics.density;
    }
}
