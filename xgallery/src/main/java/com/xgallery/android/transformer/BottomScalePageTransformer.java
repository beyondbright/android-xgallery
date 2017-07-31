package com.xgallery.android.transformer;

import android.os.Build;
import android.util.Log;
import android.view.View;

/**
 * @author wuzhen
 * @since 2017/07/24
 */
public class BottomScalePageTransformer extends BasePageTransformer {

  private static final float MAX_SCALE = 1.75f;
  private static final float MIN_SCALE = 1.0f;
  public static int DISTANCE = 15; //偏移量

  @Override
  public void transformPage(View page, float position) {
    float pos = position;
    if (position < -1) {
      pos = -1;
    } else if (position > 1) {
      pos = 1;
    }

    float tempScale = pos < 0 ? 1 + pos : 1 - pos;
    float slope = (MAX_SCALE - MIN_SCALE) / 1;
    float scaleValue = MIN_SCALE + tempScale * slope;

    page.setScaleX(scaleValue);
    page.setScaleY(scaleValue);

    float translationX = ((MAX_SCALE - scaleValue) / (MAX_SCALE - MIN_SCALE));
    if (position < 0) {
      translationX = -translationX;
    }
    DISTANCE = (int) ((MAX_SCALE - 1) * page.getWidth() / 2);
    page.setTranslationX(translationX * DISTANCE);
    Log.d("TAG", page.getTag()
        + ", (" + page.getX() + ", " + page.getY()
        + "), (" + page.getScaleX() + ", " + page.getScaleY()
        + "), (" + page.getTranslationX() + ", " + page.getTranslationY()
        + "), (" + page.getPivotX() + ", " + page.getPivotY()
        + "), (" + page.getWidth() + ", " + page.getHeight()
        + "), " + position);
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
      page.getParent().requestLayout();
    }
  }
}
