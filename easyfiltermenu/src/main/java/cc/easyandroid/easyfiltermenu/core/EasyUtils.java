package cc.easyandroid.easyfiltermenu.core;

import android.view.View;

/**
 * Created by chenguoping on 16/5/22.
 */
public class EasyUtils {
    public static void showView(View listView) {
        if (listView != null) {
            if (listView.getVisibility() != View.VISIBLE) {
                listView.setVisibility(View.VISIBLE);
            }
            listView.setTag(null);//tag 是位了防重复点击
        }

    }

    public static void hideView(View listView) {
        if (listView != null) {
            if (listView.getVisibility() != View.GONE) {
                listView.setVisibility(View.GONE);
            }
        }

    }
}
