package com.lilincpp.bottommenu;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
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

    private ViewGroup mRootViewGroup;
    private View mContentView;
    private MenuContentLayout mDefaultMenuLayout;

    private BottomMenu(BottomMenu.Builder builder) {
        mRootViewGroup = builder.rootViewGroup;
        mContentView = builder.contentView;
        if (builder.contentView instanceof MenuContentLayout) {
            mDefaultMenuLayout = (MenuContentLayout) builder.contentView;
            mDefaultMenuLayout.setItems(builder.items);
            mDefaultMenuLayout.setLayoutType(builder.defaultLayoutType);
        }
    }

    @Override
    public void dismiss() {

    }

    @Override
    public void show() {

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

            }
        }
    }

    public static final class Builder {
        ViewGroup rootViewGroup;
        View contentView;
        int defaultLayoutType = MenuContentLayout.LAYOUT_TYPE_LINEAR;
        java.util.List<MenuItem> items = new ArrayList<>();

        public Builder(View view) {
            rootViewGroup = findRootViewGroup(view);
        }

        public Builder setContentView(View contentView) {
            this.contentView = contentView;
            return this;
        }

        public Builder gridLayout() {
            defaultLayoutType = MenuContentLayout.LAYOUT_TYPE_GRID;
            return this;
        }

        public Builder linearLayout() {
            defaultLayoutType = MenuContentLayout.LAYOUT_TYPE_LINEAR;
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
