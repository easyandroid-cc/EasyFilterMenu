package cc.easyandroid.easyfiltermenu.core;


import android.util.SparseArray;
import android.view.LayoutInflater;

import java.util.List;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;

public class ListFilterAdapter extends EasyFlexibleAdapter<IEasyItem> {
    private EasyItemManager easyItemManager;

    public void setEasyItemManager(EasyItemManager easyItemManager) {
        this.easyItemManager = easyItemManager;
        List list = easyItemManager.getEasyItems();
        setItems(list);
    }

    public ListFilterAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    /**
     * clean data
     */
    public void cleanData() {
        easyItemManager = null;
        clearItems();
    }

    public EasyItemManager getEasyItemManager() {
        return easyItemManager;
    }

    /**
     * 删除所以的child 位置记录信息
     */
    public void clearAllChildPosition() {
        int count = getItemCount();
        for (int i = 0; i < count; i++) {
            IEasyItem easyItem = (IEasyItem) getItem(i);
            EasyItemManager easyItemManager = easyItem.getEasyItemManager();
            easyItemManager.setChildSelectPosion(easyItemManager.getDefaultSelectPosition());
            easyItemManager.setChildSelectTempPosition(easyItemManager.getDefaultSelectPosition());
            easyItemManager.setChildSelected(false);
        }

    }

    /**
     * 重新设置临时变量
     */
    public void resetAllChildSelectTempPosition() {
        int count = this.getItemCount();
        for (int i = 0; i < count; ++i) {
            IEasyItem easyItem = (IEasyItem) this.getItem(i);
            EasyItemManager easyItemManager = easyItem.getEasyItemManager();
            easyItemManager.setChildSelectTempPosition(easyItemManager.getChildSelectPosion());
        }
    }

    /**
     * 将临时变量保存
     */
    public void saveSelectTempPosition() {
        int count = this.getItemCount();
        for (int i = 0; i < count; ++i) {
            IEasyItem easyItem = (IEasyItem) this.getItem(i);
            EasyItemManager easyItemManager = easyItem.getEasyItemManager();
            easyItemManager.setChildSelectPosion(easyItemManager.getChildSelectTempPosition());
        }
    }

    public void clearSelectTempPosition() {
        int count = this.getItemCount();
        for (int i = 0; i < count; ++i) {
            IEasyItem easyItem = (IEasyItem) this.getItem(i);
            EasyItemManager easyItemManager = easyItem.getEasyItemManager();
            easyItemManager.setChildSelectTempPosition(easyItemManager.getDefaultSelectPosition());
        }
        notifyDataSetChanged();
    }

    //获取当前列表中所有item的easyitemmanager的tag
    public SparseArray<CharSequence> getAllTags() {
        SparseArray<CharSequence> tags = new SparseArray<>();//多选择时候，记住标题的容器
        int count = getItemCount();
        for (int i = 0; i < count; i++) {
            IEasyItem easyItem = (IEasyItem) getItem(i);
            easyItem.getEasyItemManager().setChildSelectPosion(0);
            easyItem.getEasyItemManager().setChildSelected(false);
        }
        return tags;
    }

}
