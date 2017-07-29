package com.binaryic.beinsync.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ImageViewBannerSplash extends ImageView {
    public ImageViewBannerSplash(Context paramContext) {
        super(paramContext);
    }

    public ImageViewBannerSplash(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public ImageViewBannerSplash(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    protected void onMeasure(int paramInt1, int paramInt2) {

        super.onMeasure(paramInt1, paramInt2);
        int i = getMeasuredWidth();
        if (i == getMeasuredHeight()) {
            setMeasuredDimension(i, i);
            return;
        }
        Double h = (i*0.26);
        setMeasuredDimension(i, h.intValue());
    }
}
