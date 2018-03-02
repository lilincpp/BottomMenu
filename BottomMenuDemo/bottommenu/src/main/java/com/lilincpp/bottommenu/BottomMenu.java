package com.lilincpp.bottommenu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by lilin on 2018/2/27.
 */

public final class BottomMenu implements IMenu {

    private static final String TAG = "BottomMenu";
    private static final long ANIMATION_DURATION = 200;

    private ViewGroup mRootViewGroup;
    private View mMenuView; //菜单整个布局
    private View mMenuCover; //菜单遮盖层
    private View mContentView; //用户设置的内容布局
    private MenuViewGroup mMenuViewGroup;
    private ListMenuContentLayout mDefaultMenuLayout;

    private Context mContext;
    private boolean mShowing, mAnimRunning;
    private int mContentHeight;

    private Message mMessageShow;
    private Message mMessageDismiss;

    private BottomMenu(BottomMenu.Builder builder) {
        mRootViewGroup = builder.rootViewGroup;
        mContext = builder.rootViewGroup.getContext();

        mMenuView = LayoutInflater.from(mContext).inflate(R.layout.ll_bottom_menu_layout, null, false);
        mMenuCover = mMenuView.findViewById(R.id.ll_bottom_menu_cover);
        mMenuViewGroup = mMenuView.findViewById(R.id.ll_bottom_menu_content);

        mContentView = builder.contentView;

        if (builder.contentView instanceof ListMenuContentLayout) {
            mDefaultMenuLayout = (ListMenuContentLayout) builder.contentView;
            mDefaultMenuLayout.setItems(builder.items);
            mDefaultMenuLayout.setLayoutType(builder.defaultLayoutType);
        }

        mMenuViewGroup.addView(mContentView);

        mMenuCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void dismiss() {
        if (mShowing && !mAnimRunning) {
            if (mMenuView.getParent() != null) {
                animOut();
            }
        }
    }

    @Override
    public void show() {
        if (!mShowing && !mAnimRunning) {
            if (mMenuView.getParent() == null) {

                if (mDefaultMenuLayout != null) {
                    mDefaultMenuLayout.create();
                }
                measure();
                animIn();
            }
        }
    }

    private void measure() {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        mMenuView.measure(w, h);
        mContentHeight = mMenuView.getMeasuredHeight();
    }

    private void animIn() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mMenuViewGroup,
                "translationY", mContentHeight, 0);
        animator.setDuration(ANIMATION_DURATION);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mRootViewGroup.addView(mMenuView);
                mMenuCover.setVisibility(View.VISIBLE);
                mAnimRunning = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mShowing = true;
                mAnimRunning = false;
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float value = (float) animation.getAnimatedValue();
                mMenuCover.setAlpha(1 - value / mContentHeight);
            }
        });
        animator.start();
    }

    private void animOut() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mMenuViewGroup,
                "translationY", 0, mContentHeight);
        animator.setDuration(ANIMATION_DURATION);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mAnimRunning = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mMenuCover.setVisibility(View.GONE);
                mRootViewGroup.removeView(mMenuView);
                mShowing = false;
                mAnimRunning = false;
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float value = (float) animation.getAnimatedValue();
                mMenuCover.setAlpha(1 - value / mContentHeight);
            }
        });
        animator.start();
    }

    private static class ListenerHandler extends Handler {
        WeakReference<IMenu> menu;

        private ListenerHandler(IMenu menu) {
            this.menu = new WeakReference<>(menu);
        }

        static final int MESSAGE_SHOW = 0x01;
        static final int MESSAGE_DISMISS = 0x02;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_SHOW:

                    break;
                case MESSAGE_DISMISS:

                    break;
            }
        }
    }

    public static final class Builder {
        ViewGroup rootViewGroup;
        View contentView;
        int defaultLayoutType = ListMenuContentLayout.LAYOUT_TYPE_LINEAR;
        java.util.List<MenuItem> items = new ArrayList<>();

        public Builder(View view) {
            rootViewGroup = findRootViewGroup(view);
        }

        public Builder setContentView(View contentView) {
            this.contentView = contentView;
            return this;
        }

        public Builder gridLayout() {
            defaultLayoutType = ListMenuContentLayout.LAYOUT_TYPE_GRID;
            return this;
        }

        public Builder linearLayout() {
            defaultLayoutType = ListMenuContentLayout.LAYOUT_TYPE_LINEAR;
            return this;
        }

        public Builder addItem(String name, Drawable drawable) {
            items.add(new MenuItem(name, drawable));
            return this;
        }

        public BottomMenu create() {
            if (rootViewGroup == null) {
                throw new IllegalArgumentException("# rootViewGroup == null");
            }

            if (contentView == null) {
                //渲染默认的菜单布局
                contentView = LayoutInflater.from(rootViewGroup.getContext()).inflate(
                        R.layout.ll_bottom_menu_default_content, null, false
                );
            }
            return new BottomMenu(this);
        }

    }

    public static final class MenuItem {
        Object tag;
        String name;
        Drawable drawable;

        public MenuItem(String name, Drawable drawable) {
            this.name = name;
            this.drawable = drawable;
        }

        public Object getTag() {
            return tag;
        }

        public void setTag(Object tag) {
            this.tag = tag;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Drawable getDrawable() {
            return drawable;
        }

        public void setDrawable(Drawable drawable) {
            this.drawable = drawable;
        }
    }


    private static ViewGroup findRootViewGroup(View view) {
        do {
            if (view instanceof FrameLayout) {
                if (view.getId() == android.R.id.content) {
                    return (ViewGroup) view;
                }
            }

            if (view != null) {
                ViewParent parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
        } while (view != null);

        return null;
    }
}
