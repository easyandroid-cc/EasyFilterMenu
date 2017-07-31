package cc.easyandroid.easyfiltermenu.core;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.util.ArrayList;


/**
 * 一个列表的数据就是用一个EasyItemManager填充，里面的mEasyItem是第二级列表的数据
 * 维护当前item的状态
 */
public class EasyItemManager implements Parcelable {
    private ArrayList<? extends Parcelable> mEasyItems;
    private int mChildSelectPosition = -1;
    private int mChildSelectTempPositon = -1;//临时记住，点击确认后将临时-->确定
    private boolean mChildSelected;//子列表中如果有被选中的，用户可能需要将当前item的样式改变
    private String mTag;//用来重写 hashCode
    private int mDefaultSelectPosition = -1;//希望的默认选择位置，一般是0

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

    public EasyItemManager(ArrayList easyItems, String tag) {
        this.mEasyItems = easyItems;
        this.mTag = tag;
    }

    public EasyItemManager(ArrayList easyItems) {
        this.mEasyItems = easyItems;
    }

    public ArrayList getEasyItems() {
        return mEasyItems;
    }


    public boolean isChildSelected() {
        return mChildSelected;
    }

    public void setChildSelected(boolean childSelected) {
        this.mChildSelected = childSelected;
    }

    public int getChildSelectPosition() {
        return mChildSelectPosition;
    }

    public void setChildSelectPosition(int posion) {
        this.mChildSelectPosition = posion;
    }

    @Override
    public int hashCode() {
        if (!TextUtils.isEmpty(mTag)) return mTag.hashCode();
        return super.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ARRAYLISTDATAKEY, mEasyItems);
        dest.writeBundle(bundle);
        dest.writeInt(this.mChildSelectPosition);
        dest.writeInt(this.mChildSelectTempPositon);
        dest.writeByte(mChildSelected ? (byte) 1 : (byte) 0);
        dest.writeString(this.mTag);
        dest.writeInt(this.mDefaultSelectPosition);
    }

    private static final String ARRAYLISTDATAKEY = "ArrayListDataKey";

    protected EasyItemManager(Parcel in) {
        Bundle bundle = in.readBundle(getClass().getClassLoader());
        mEasyItems = bundle.getParcelableArrayList(ARRAYLISTDATAKEY);
        this.mChildSelectPosition = in.readInt();
        this.mChildSelectTempPositon = in.readInt();
        this.mChildSelected = in.readByte() != 0;
        this.mTag = in.readString();
        this.mDefaultSelectPosition = in.readInt();
    }

    public static final Creator<EasyItemManager> CREATOR = new Creator<EasyItemManager>() {
        public EasyItemManager createFromParcel(Parcel source) {
            return new EasyItemManager(source);
        }

        public EasyItemManager[] newArray(int size) {
            return new EasyItemManager[size];
        }
    };
}
