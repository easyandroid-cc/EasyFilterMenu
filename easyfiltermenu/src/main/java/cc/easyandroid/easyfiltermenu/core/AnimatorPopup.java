package cc.easyandroid.easyfiltermenu.core;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.PopupWindow;


public class AnimatorPopup extends PopupWindow {
    private Animator animator_Show;
    private Animator animator_Dismiss;
    private static final int ALPHA = 100;

    public AnimatorPopup(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
        setAnimationStyle(0);
        creatAnimator(contentView);
    }


    private void creatAnimator(View contentView) {
        int[] location = new int[2];
        int screenHeight = contentView.getResources().getDisplayMetrics().heightPixels;
        int rootViewHeight = screenHeight - location[1];
        int paddingBottom = contentView.getPaddingBottom();
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        animator_Show = createShowAnimator(contentView, -rootViewHeight + paddingBottom - 5);
        animator_Dismiss = createDismissAnimator(contentView, -rootViewHeight + paddingBottom - 5);
        animator_Show.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (animator_Dismiss != null) {
                    animator_Dismiss.cancel();
                }
            }
        });
        /**
         * 这里是重点：两次调用dismiss，如果直接使用super方法是没有办法显示动画的，
         * 所以这里的做法是，通过一个boolean变量进行控制，第一次的dismiss的时候先显示动画，
         * 动画结束后，再调用自身的dismiss方法，将整个window消失掉
         */
        animator_Dismiss.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (animator_Show != null && animator_Show.isRunning()) {
                    animator_Show.cancel();
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                AnimatorPopup.super.dismiss();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                AnimatorPopup.super.dismiss();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                AnimatorPopup.super.dismiss();
            }
        });
    }

    private Animator createShowAnimator(final View together, int translationValue) {
        int duration = together.getResources().getInteger(android.R.integer.config_shortAnimTime);
        ObjectAnimator animShow = ObjectAnimator.ofFloat(getContentView(), View.TRANSLATION_Y, translationValue, 0).setDuration(duration);
        animShow.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedFraction = animation.getAnimatedFraction();
                int h = (int) (ALPHA * animatedFraction);
                together.getRootView().setBackgroundColor(Color.argb(h, 0, 0, 0));
            }
        });
        animShow.setInterpolator(new LinearInterpolator());
        return animShow;
    }


    private ObjectAnimator createDismissAnimator(View together, int translationValue) {
        int duration = together.getResources().getInteger(android.R.integer.config_shortAnimTime);
        ObjectAnimator animShow = ObjectAnimator.ofFloat(getContentView(), View.TRANSLATION_Y, 0, translationValue).setDuration(duration);
        animShow.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedFraction = animation.getAnimatedFraction();
                int h = (int) (ALPHA - ALPHA * animatedFraction);
                getContentView().getRootView().setBackgroundColor(Color.argb(h, 0, 0, 0));
            }
        });
        animShow.setInterpolator(new LinearInterpolator());
        return animShow;
    }

    @Override
    public void dismiss() {
        if (animator_Dismiss == null || animator_Dismiss.isRunning()) {
            return;
        }
        animator_Dismiss.start();
    }

    //    　情景描述：在andorid7.0及以上系统，点击某个view，本来期待有一个Popuwindow在该view下面弹出（调用PopuWindow.showAsDropDown(view)方法）但结果PopuWindow却弹出在view上方，顶在系统状态栏下面。
//            　　原因：在android7.0上，如果不主动约束PopuWindow的大小，比如，设置布局大小为 MATCH_PARENT,那么PopuWindow会变得尽可能大，以至于 view下方无空间完全显示PopuWindow，而且view又无法向上滚动，此时PopuWindow会主动上移位置，直到可以显示完全。
//    解决办法：主动约束PopuWindow的内容大小，重写showAsDropDown方法：
    @Override
    public void showAsDropDown(View anchor) {
//        if (Build.VERSION.SDK_INT >= 24) {
//            Rect visibleFrame = new Rect();
//            anchor.getGlobalVisibleRect(visibleFrame);
//            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
//            setHeight(height);
//        }
//        super.showAsDropDown(anchor);
        if (Build.VERSION.SDK_INT < 24) {
            super.showAsDropDown(anchor);
        } else {
            // 获取控件的位置，安卓系统>7.0
            int[] location = new int[2];
            anchor.getLocationOnScreen(location);
            showAtLocation(anchor, Gravity.NO_GRAVITY, 0, location[1] + anchor.getHeight());
        }
    }

    public void showAsDropDown(View anchor, int xoff, int yoff) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
        }
        super.showAsDropDown(anchor, xoff, yoff);
        animator_Show.start();

//        if (Build.VERSION.SDK_INT >= 24) {
//            int[] a = new int[2];
//            anchor.getLocationInWindow(a);
//            showAtLocation(anchor, Gravity.NO_GRAVITY, xoff, a[1] + anchor.getHeight() + yoff);
//        } else {
//            super.showAsDropDown(anchor, xoff, yoff);
//        }
//        animator_Show.start();
    }

//    @Override
//    public void showAsDropDown(View anchor, int xoff, int yoff) {
//        if (Build.VERSION.SDK_INT < 24) {
//            super.showAsDropDown(anchor);
//        } else {
//            // 获取控件的位置，安卓系统>7.0
//            int[] location = new int[2];
//            anchor.getLocationOnScreen(location);
//            showAtLocation(anchor, Gravity.NO_GRAVITY, 0, location[1] + anchor.getHeight());
//        }
//    }
}
