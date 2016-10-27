package cc.easyandroid.easyfiltermenu.core;

import java.io.Serializable;
import java.util.HashMap;

import cc.easyandroid.easyrecyclerview.holders.FlexibleViewHolder;
import cc.easyandroid.easyrecyclerview.items.IFlexible;


public interface IEasyItem<VH extends FlexibleViewHolder> extends IFlexible<VH>, Serializable {

    EasyItemManager getEasyItemManager();

    CharSequence getEasyItemTag();//显示的名称

    HashMap<String, String> getEasyParameter();//参数的封装

    /**
     * 如果在menuMulti中需要一个不限的选项，可以返回NOLIMITITEM,他被选中后会将其他的选择清除，其他的选择后会将他的选择状态清除
     */
    String MENUMULTI_NOLIMITITEM_TAG = "menumulti_nolimititem_tag";
}
