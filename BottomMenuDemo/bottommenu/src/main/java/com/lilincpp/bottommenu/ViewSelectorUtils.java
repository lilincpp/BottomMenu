package com.lilincpp.bottommenu;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.View;

/**
 * Created by lilin on 2017/12/28.
 */

public final class ViewSelectorUtils {

    private ViewSelectorUtils() {

    }

    public static void setDefaultSelector(View view) {
        setSelectorBackground(view, Color.GRAY);
    }

    public static void setSelectorBackground(View view, int pressedColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setBackground(new RippleDrawable(makeColorStateList(pressedColor),
                    null, new ColorDrawable(Color.WHITE)));
        } else {
            view.setBackground(makeStateListDrawable(pressedColor));
        }
    }

    public static void setSelectorBackgroundBorderless(View view, int pressedColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setBackground(new RippleDrawable(makeColorStateList(pressedColor), null, null));
        } else {
            view.setBackground(makeStateListDrawable(pressedColor));
        }
    }

    private static ColorStateList makeColorStateList(int pressedColor) {
        int[] colors = new int[]{pressedColor, pressedColor};
        int[][] states = new int[2][];
        states[0] = new int[]{android.R.attr.state_pressed};
        states[1] = new int[]{};
        return new ColorStateList(states, colors);
    }

    private static StateListDrawable makeStateListDrawable(int pressedColor) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable normalDrawable = new ColorDrawable(Color.TRANSPARENT);
        Drawable pressedDrawable = new ColorDrawable(pressedColor);
        stateListDrawable.addState(
                new int[]{android.R.attr.state_pressed},
                pressedDrawable
        );
        stateListDrawable.addState(
                new int[]{},
                normalDrawable
        );
        return stateListDrawable;
    }
}
