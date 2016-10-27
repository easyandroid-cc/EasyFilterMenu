package cc.easyandroid.easyfiltermenu.core;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;

/**
 * Created by cgpllx on 2016/10/25.
 */
public class SimpleEasyItem implements IEasyItem {
    @Override
    public EasyItemManager getEasyItemManager() {
        return null;
    }

    @Override
    public CharSequence getEasyItemTag() {
        return null;
    }

    @Override
    public HashMap<String, String> getEasyParameter() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void setEnabled(boolean b) {

    }

    @Override
    public boolean isSelectable() {
        return false;
    }

    @Override
    public void setSelectable(boolean b) {

    }

    @Override
    public int getLayoutRes() {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(EasyFlexibleAdapter easyFlexibleAdapter, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public void bindViewHolder(EasyFlexibleAdapter easyFlexibleAdapter, RecyclerView.ViewHolder viewHolder, int i, List list) {

    }
}
