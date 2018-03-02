package com.lilincpp.bottommenu;

/**
 * Created by lilin on 2018/2/27.
 */

public interface IMenu {

    void dismiss();

    void show();

    interface OnItemClickListener {
        void onItemClick(IMenu menu, int position);
    }

    interface OnDismissListener {
        void onDismiss(IMenu menu);
    }

    interface OnShowListener {
        void onShow(IMenu menu);
    }
}
