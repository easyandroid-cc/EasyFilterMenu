package cc.easyandroid.easyfiltermenu.core;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * 一个列表的数据就是用一个EasyItemManager填充，里面的mEasyItem是第二季列表的数据
 * 维护当前item的状态
 */
public class EasyItemManager implements Serializable {
    protected ArrayList<? extends IEasyItem> mEasyItems;
    protected int mChildSelectPosition = -1;
    protected int mChildSelectTempPositon = -1;//临时记住，点击确认后将临时-->确定
    protected boolean mChildSelected;//子列表中如果有被选中的，用户可能需要将当前item的样式改变
    protected String mTag;//用来重写 hashCode
    protected int mDefaultSelectPosition = -1;//希望的默认选择位置，一般是0

    public boolean isHasEasyItems() {
        return mEasyItems != null && mEasyItems.size() > 0;
    }

    public void setDefaultSelectPosition(int defaultSelectPosition) {
        this.mDefaultSelectPosition = defaultSelectPosition;
        this.mChildSelectPosition = defaultSelectPosition;
        this.mChildSelectTempPositon = defaultSelectPosition;
    }

    public int getDefaultSelectPosition() {
        return mDefaultSelectPosition;
    }

    public void setChildSelectTempPosition(int mChildSelectTempPostiion) {
        this.mChildSelectTempPositon = mChildSelectTempPostiion;
    }

    public int getChildSelectTempPosition() {
        return mChildSelectTempPositon;
    }

    public EasyItemManager(ArrayList<? extends IEasyItem> easyItems, String tag) {
        this.mEasyItems = easyItems;
        this.mTag = tag;
    }

    public EasyItemManager(ArrayList<? extends IEasyItem> easyItems) {
        this.mEasyItems = easyItems;
    }

    public ArrayList<? extends IEasyItem> getEasyItems() {
        return mEasyItems;
    }


    public boolean isChildSelected() {
        return mChildSelected;
    }

    public void setChildSelected(boolean childSelected) {
        this.mChildSelected = childSelected;
    }

    public int getChildSelectPosion() {
        return mChildSelectPosition;
    }

    public void setChildSelectPosion(int posion) {
        this.mChildSelectPosition = posion;
    }

    @Override
    public int hashCode() {
        if (!TextUtils.isEmpty(mTag)) return mTag.hashCode();
        return super.hashCode();
    }
}
