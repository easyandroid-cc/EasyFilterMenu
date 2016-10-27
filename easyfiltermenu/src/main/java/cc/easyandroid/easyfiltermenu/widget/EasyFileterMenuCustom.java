package cc.easyandroid.easyfiltermenu.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import java.util.Collection;

import cc.easyandroid.easyfiltermenu.core.ListFilterAdapter;

/**
 * 1点击gradview的item时候，将选择的item记录在list item的临时变量中，
 * 2 点击确认时候，将临时变量中的值赋值到正式变量
 * 3 打开的时候，讲正式变量赋值到临时变量中
 * 4.gridview中被选择的item就是临时变量的值
 */
public class EasyFileterMenuCustom extends EasyFilterMenuSingle {

    public EasyFileterMenuCustom(Context context) {
        super(context);
    }

    public EasyFileterMenuCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EasyFileterMenuCustom(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void handleMenuTitle() {
        setMenuTitle(defultMenuText);
    }

    public void setMenuTitle(CharSequence menuTitle) {
        setMenuTitle(menuTitle, false);
        Collection<String> strings = getEasyMenuParas().values();
        for (String para : strings) {
            if (!TextUtils.isEmpty(para)) {
                setMenuTitle(defultMenuText, true);
                break;
            }
        }
    }

    public void clearTempSelect() {
        ListFilterAdapter adapter = (ListFilterAdapter) mRecyclerView1.getAdapter();
        adapter.clearSelectTempPosition();
    }

    @Override
    public void cleanMenuStates() {
        super.cleanMenuStates();
        setMenuTitle(defultMenuText, false);
    }
}
