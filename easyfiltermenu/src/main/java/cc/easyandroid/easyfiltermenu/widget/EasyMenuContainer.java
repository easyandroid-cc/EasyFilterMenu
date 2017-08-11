package cc.easyandroid.easyfiltermenu.widget;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cc.easyandroid.easyfiltermenu.core.EasyFilterListener;
import cc.easyandroid.easyfiltermenu.core.EasyMenuManager;
import cc.easyandroid.easyfiltermenu.core.EasyMenuStates;

/**
 *
 */
public class EasyMenuContainer extends LinearLayout {
    EasyMenuManager easyMenuManager;

    public EasyMenuContainer(Context context) {
        super(context);
        init();

    }

    public EasyMenuContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EasyMenuContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        easyMenuManager = new EasyMenuManager();
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("super_data", super.onSaveInstanceState());
        if (easyMenuManager != null) {
            easyMenuManager.getAllMenuParas();
            easyMenuManager.saveInstanceState(bundle);
        }
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;
        super.onRestoreInstanceState(bundle.getParcelable("super_data"));
        if (easyMenuManager != null && state != null) {
            easyMenuManager.restoreInstanceState(bundle);
        }
    }

    @Override
    public void addView(View childView, int index, ViewGroup.LayoutParams params) {
        super.addView(childView, index, params);
        if (childView != null && childView instanceof EasyFilterMenu) {
            easyMenuManager.addMenu(((EasyFilterMenu) childView));
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (easyMenuManager != null) {
            easyMenuManager.clear();
        }
    }

    public SparseArray<EasyMenuStates> getAllMenuStates() {
        return easyMenuManager.getAllMenuStates();
    }

    public ArrayMap<String, String> getAllMenuParas() {
        return easyMenuManager.getAllMenuParas();
    }

    public void setAllMenuStates(SparseArray<EasyMenuStates> sparseArray) {
        easyMenuManager.setMenusStates(sparseArray);
    }

    public void setOnEasyMenuParasChangedListener(EasyFilterListener.OnEasyMenuParasChangedListener onEasyMenuParasChangedListener) {
        easyMenuManager.setOnEasyMenuParasChangedListener(onEasyMenuParasChangedListener);
    }

    public void clearAllMenuStates() {
        easyMenuManager.clearAllMenuStates();
    }

    public void dismissAllMenuContent() {
        easyMenuManager.dismissAllMenuContent();
    }

}
