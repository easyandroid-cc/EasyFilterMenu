package cc.easyandroid.easyfiltermenu.simple.no;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;
import cc.easyandroid.easyrecyclerview.holders.FlexibleViewHolder;
import cc.easyandroid.easyfiltermenu.core.EasyItemManager;
import cc.easyandroid.easyfiltermenu.core.IEasyItem;
import cc.easyandroid.easyfiltermenu.simple.R;

/**
 * Created by cgpllx on 2016/10/17.
 */
public class NoLimitItem1 implements IEasyItem<NoLimitItem1.ListViewHolder>,Parcelable {

    private String displayName;


    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public NoLimitItem1(CharSequence displayName) {
        if(displayName!=null){
            this.displayName = displayName.toString();
        }
    }

    public NoLimitItem1() {
    }

    @Override
    public EasyItemManager getEasyItemManager() {
        return new EasyItemManager(null);
    }
//    IEasyItem.MENUMULTI_NOLIMITITEM_TAG
    @Override
    public CharSequence getEasyItemTag() {
        return displayName;
    }

    @Override
    public HashMap<String, String> getEasyParameter() {
        return new HashMap<>();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setEnabled(boolean b) {

    }

    @Override
    public boolean isSelectable() {
        return true;
    }

    @Override
    public void setSelectable(boolean b) {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.list_item_nolimit;
    }

    @Override
    public ListViewHolder createViewHolder(EasyFlexibleAdapter easyFlexibleListViewAdapter, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return new ListViewHolder(layoutInflater.inflate(getLayoutRes(), viewGroup, false), easyFlexibleListViewAdapter);

    }

    @Override
    public void bindViewHolder(EasyFlexibleAdapter easyFlexibleListViewAdapter, ListViewHolder listViewHolder, int i, List list) {
        listViewHolder.setData();
    }

    public class ListViewHolder extends FlexibleViewHolder {
        TextView textView;

        public ListViewHolder(final View header_ad, EasyFlexibleAdapter adapter) {
            super(header_ad, adapter);
            textView = (TextView) header_ad.findViewById(R.id.easyListFilter_ItemDisplayName);
        }

        @Override
        public void onClick(View view) {
            super.onClick(view);
            Toast.makeText(view.getContext(), "po" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        }

        public void setData() {
            textView.setText("不限");
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.displayName);
    }

    protected NoLimitItem1(Parcel in) {
        this.displayName = in.readString();
    }

    public static final Creator<NoLimitItem1> CREATOR = new Creator<NoLimitItem1>() {
        public NoLimitItem1 createFromParcel(Parcel source) {
            return new NoLimitItem1(source);
        }

        public NoLimitItem1[] newArray(int size) {
            return new NoLimitItem1[size];
        }
    };
}
