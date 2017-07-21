package com.binaryic.beinsync.common;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by User on 08-09-2016.
 */
public class TextViewPrimary extends AppCompatTextView {
    public TextViewPrimary(Context paramContext) {
        super(paramContext);
        a();
    }

    public TextViewPrimary(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        a();
    }

    public TextViewPrimary(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        a();
    }

    public void a() {
        if (Build.VERSION.SDK_INT < 21) {
            setTypeface(Typeface.createFromAsset(getContext().getAssets(), "ProximaNova-Regular.ttf"), 0);
            return;
        }
        int i = getTypeface().getStyle();
        if (i == 1) {
            setTypeface(Typeface.createFromAsset(getContext().getAssets(), "ProximaNova-Regular.ttf"), i);
            return;
        }
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "ProximaNova-Regular.ttf"), 0);
    }
}
