package cc.easyandroid.easyfiltermenu.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.ArrayMap;
import android.util.SparseBooleanArray;

/**
 * menu 数据封装对象
 */
public class EasyMenuStates implements Parcelable {
    private EasyItemManager easyItemManager;//item的数据
    private String menuTitle;
    private SparseBooleanArray menuStatesArray;//保存被选中的状态的EasyFilterMenuMulti专用
    private ArrayMap<Integer, String> multiTitles;//多选时候存放被选择的标题的集合EasyFileterMenuMore专用
    private ArrayMap<String, String> easyMenuParas;

    public ArrayMap<String, String> getEasyMenuParas() {
        return easyMenuParas;
    }

    public ArrayMap<Integer, String> getMultiTitles() {
        return multiTitles;
    }

    public SparseBooleanArray getMenuStatesArray() {
        return menuStatesArray;
    }

    public EasyItemManager getEasyItemManager() {
        return easyItemManager;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    private EasyMenuStates(Builder builder) {
        easyItemManager = builder.easyItemManager;
        menuTitle = builder.menuTitle;
        menuStatesArray = builder.menuStatesArray;
        multiTitles = builder.multiTitles;
        easyMenuParas = builder.easyMenuParas;
    }


    public static class Builder {
        EasyItemManager easyItemManager;
        String menuTitle;
        SparseBooleanArray menuStatesArray;
        ArrayMap<Integer, String> multiTitles;//多选时候存放被选择的标题的集合
        ArrayMap<String, String> easyMenuParas;//多选时候存放被选择的标题的集合

        public Builder setEasyMenuParas(ArrayMap<String, String> easyMenuParas) {
            this.easyMenuParas = easyMenuParas;
            return this;
        }

        public Builder setMenuStatesArray(SparseBooleanArray menuStatesArray) {
            this.menuStatesArray = menuStatesArray;
            return this;
        }

        public Builder setMultiTitles(ArrayMap<Integer, String> multiTitles) {
            this.multiTitles = multiTitles;
            return this;
        }


        public Builder setMenuTitle(String title) {
            this.menuTitle = title;
            return this;
        }


        public Builder setEasyItemManager(EasyItemManager easyItemManager) {
            this.easyItemManager = easyItemManager;
            return this;
        }

        public EasyMenuStates build() {
            return new EasyMenuStates(this);
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.easyItemManager);
        dest.writeString(this.menuTitle);
        dest.writeSparseBooleanArray(this.menuStatesArray);
        dest.writeMap(this.multiTitles);
        dest.writeMap(this.easyMenuParas);

    }

    protected EasyMenuStates(Parcel in) {
        this.easyItemManager = (EasyItemManager) in.readSerializable();
        this.menuTitle = in.readString();
        this.menuStatesArray = in.readSparseBooleanArray();
        this.multiTitles = new ArrayMap<Integer, String>();
        in.readMap(this.multiTitles, multiTitles.getClass().getClassLoader());
        this.easyMenuParas = new ArrayMap<String, String>();
        in.readMap(this.easyMenuParas, easyMenuParas.getClass().getClassLoader());
    }

    public static final Creator<EasyMenuStates> CREATOR = new Creator<EasyMenuStates>() {
        public EasyMenuStates createFromParcel(Parcel source) {
            return new EasyMenuStates(source);
        }

        public EasyMenuStates[] newArray(int size) {
            return new EasyMenuStates[size];
        }
    };
}