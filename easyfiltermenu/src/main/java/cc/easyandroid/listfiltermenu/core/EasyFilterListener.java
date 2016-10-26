package cc.easyandroid.listfiltermenu.core;

import android.support.v4.util.SimpleArrayMap;
import android.view.View;
import android.view.ViewGroup;

import cc.easyandroid.listfiltermenu.widget.EasyFilterMenu;

/**
 * Created by Administrator on 2016/5/26.
 */
public interface EasyFilterListener {
    interface OnMenuShowListener<T extends EasyFilterMenu> {
        /**
         * 显示之前的监听
         *
         * @param menu EasyFilterMenu
         * @param view pop的view
         */
        void onMenuShowBefore(T menu, View view);
    }

    interface OnMenuListItemClickListener<T extends EasyFilterMenu> {
        void onClick(T easyFilterMenu, IEasyItem iEasyItem);
    }

    interface OnCustomViewConfirmClickListener<T extends EasyFilterMenu> {
        void onClick(View clickView, ViewGroup viewGroupParent);
    }

    interface OnEasyMenuParasChangedListener {
        void onChanged(SimpleArrayMap<String, String> easyMenuParas);
    }
}
