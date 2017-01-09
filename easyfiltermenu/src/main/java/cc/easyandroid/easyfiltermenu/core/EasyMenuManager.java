package cc.easyandroid.easyfiltermenu.core;


import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import android.util.SparseArray;
import android.view.View;

import java.util.ArrayList;

import cc.easyandroid.easyfiltermenu.widget.EasyFilterMenu;

public class EasyMenuManager implements EasyFilterListener.OnMenuShowListener, EasyFilterListener.OnEasyMenuParasChangedListener {
    private ArrayMap<String, String> easyMenuAllParas = new ArrayMap<>();//保存全部参数

    public EasyMenuManager() {
        menus = new ArrayList<>();
    }

    private ArrayList<EasyFilterMenu> menus;

    public void addMenu(EasyFilterMenu menu) {
        menus.add(menu);
        menu.addOnMenuShowListener(this);
        menu.setOnEasyMenuParasChangedListener(this);
    }

    @Override
    public void onMenuShowBefore(EasyFilterMenu clickMenu, View view) {
        if (clickMenu != null && clickMenu.isShowing()) {
            return;
        }
        if (menus != null && !menus.isEmpty()) {
            for (EasyFilterMenu menu : menus) {
                if (menu.isShowing()) {
                    menu.dismiss();
                }
            }
        }
    }

    public void clear() {
        if (menus != null) {
            menus.clear();
            menus = null;
        }
    }

    public SparseArray<EasyMenuStates> getAllMenuStates() {
        SparseArray<EasyMenuStates> sparseArray = new SparseArray<>();
        if (menus != null && menus.size() > 0) {
            for (int i = 0; i < menus.size(); i++) {
                EasyFilterMenu easyFilterMenu = menus.get(i);
                EasyMenuStates easyMenuStates = easyFilterMenu.getMenuStates();
                int menuSerialNumber = easyFilterMenu.getMenuSerialNumber();
                sparseArray.put(menuSerialNumber, easyMenuStates);
            }
        }
        return sparseArray;
    }

    public void setMenusStates(SparseArray<EasyMenuStates> menusStates) {
        if (menus != null && menus.size() > 0) {
            for (int i = 0; i < menus.size(); i++) {
                EasyFilterMenu easyFilterMenu = menus.get(i);
                int menuSerialNumber = easyFilterMenu.getMenuSerialNumber();
                EasyMenuStates sparseArray = menusStates.get(menuSerialNumber);
                if (sparseArray != null) {
                    easyFilterMenu.setMenuStates(sparseArray);
                }
            }
        }
    }

    private EasyFilterListener.OnEasyMenuParasChangedListener onEasyMenuParasChangedListener;

    public void setOnEasyMenuParasChangedListener(EasyFilterListener.OnEasyMenuParasChangedListener onEasyMenuParasChangedListener) {
        this.onEasyMenuParasChangedListener = onEasyMenuParasChangedListener;
        if (this.easyMenuAllParas != null && this.easyMenuAllParas.size() > 0) {//第一次，回调给监听者
            notifyChanged();
        }
    }

    @Override
    public void onChanged(SimpleArrayMap<String, String> easyMenuParas) {
        this.easyMenuAllParas.putAll(easyMenuParas);
        notifyChanged();
    }

    private void notifyChanged() {
        if (onEasyMenuParasChangedListener != null) {//如果menu中有数据传递。请将监听设置是接收数据的后面，防止多次调用
            onEasyMenuParasChangedListener.onChanged(this.easyMenuAllParas);
        }
    }

    public ArrayMap<String, String> getAllMenuParas() {
        return this.easyMenuAllParas;
    }

    public void clearAllMenuStates() {
        easyMenuAllParas.clear();
        if (menus != null && menus.size() > 0) {
            for (int i = 0; i < menus.size(); i++) {
                EasyFilterMenu easyFilterMenu = menus.get(i);
                easyFilterMenu.cleanMenuStates();
            }
        }
    }

    public void dismissAllMenuContent() {
        if (menus != null && !menus.isEmpty()) {
            for (EasyFilterMenu menu : menus) {
                if (menu.isShowing()) {
                    menu.dismiss();
                }
            }
        }
    }
}
