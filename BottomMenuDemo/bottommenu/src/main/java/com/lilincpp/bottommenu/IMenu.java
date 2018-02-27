package com.lilincpp.bottommenu;

/**
 * Created by lilin on 2018/2/27.
 */

public interface IMenu {

    void dismiss();

    void show();

    interface OnItemClickListener {
        void onClick();
    }

    interface OnDismissListener {
        void onDismiss();
    }

    interface OnShowListener {
        void onShow();
    }
}
