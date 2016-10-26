package cc.easyandroid.listfiltermenu.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;
import cc.easyandroid.listfiltermenu.R;
import cc.easyandroid.listfiltermenu.core.EasyItemManager;
import cc.easyandroid.listfiltermenu.core.EasyMenuStates;
import cc.easyandroid.listfiltermenu.core.IEasyItem;
import cc.easyandroid.listfiltermenu.core.ListFilterAdapter;

/**
 * 单列表的多项选择
 */
public class EasyFilterMenuMulti extends EasyFilterMenu {
    private RecyclerView mRecyclerView1;
    private SparseBooleanArray mMenuStatesArray = new SparseBooleanArray();//保存被选中的状态的

    public EasyFilterMenuMulti(Context context) {
        super(context);
    }

    public EasyFilterMenuMulti(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EasyFilterMenuMulti(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMenuContentViewCreated(ViewGroup menuContentView, EasyFilterMenu easyFilterMenu) {
        //listview--1
        mRecyclerView1 = (RecyclerView) menuContentView.findViewById(R.id.easyListFilter_MenuContent_List_1);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        ListFilterAdapter adapter_List1 = new ListFilterAdapter(LayoutInflater.from(menuContentView.getContext()));
        adapter_List1.setMode(ListFilterAdapter.MODE_MULTI);
        setList1Adapter(adapter_List1);
        mRecyclerView1.setVisibility(View.VISIBLE);
        setupCustomView(menuContentView, R.id.easyListFilter_CustomViewConfirm_View1);//设置带有确定按钮的view
        setupCustomView(menuContentView, R.id.easyListFilter_CustomViewConfirm_View2);//设置带有确定按钮的view
        setupCustomView(menuContentView, R.id.easyListFilter_CustomViewConfirm_View3);//设置带有确定按钮的view
        adapter_List1.initializeListeners(new EasyFlexibleAdapter.OnItemClickListener() {
            @Override
            public boolean onItemClick(int position) {
                handleMultipleChoiceMode(mRecyclerView1, position);
                return true;
            }
        });
    }

    /**
     * 数据准备好了  直接传送的是父item
     *
     * @param easyItemManager 数据
     */
    @Override
    protected void onMenuDataPrepared(EasyItemManager easyItemManager) {
        super.onMenuDataPrepared(easyItemManager);
        addList1Items(easyItemManager);//创建一个父容器
    }

    void handleMultipleChoiceMode(RecyclerView listView, int position) {
        ListFilterAdapter listFilterAdapter = (ListFilterAdapter) listView.getAdapter();
        IEasyItem iEasyItem = (IEasyItem) listFilterAdapter.getItem(position);
        if (iEasyItem == null || IEasyItem.MENUMULTI_NOLIMITITEM_TAG.equals(iEasyItem.getEasyItemTag())) {//为null 或者是不限制
            listFilterAdapter.clearChoices();
            listFilterAdapter.notifyDataSetChanged();
            listFilterAdapter.setItemChecked(position, true);
        } else {
            if (listFilterAdapter.getSelectedPositions().size() > 0) {//检测是否有选中项，如果有就讲第一个的选中状态改变
                for (int i = 0; i < listFilterAdapter.getItemCount(); i++) {
                    IEasyItem iEasyItem_child = (IEasyItem) listFilterAdapter.getItem(i);
                    if (iEasyItem_child == null || IEasyItem.MENUMULTI_NOLIMITITEM_TAG.equals(iEasyItem_child.getEasyItemTag())) {//为null 或者是不限制
                        listFilterAdapter.setItemChecked(i, false);
                    }
                }
            } else {
                for (int i = 0; i < listFilterAdapter.getItemCount(); i++) {
                    IEasyItem iEasyItem_child = (IEasyItem) listFilterAdapter.getItem(i);
                    if (iEasyItem_child == null || IEasyItem.MENUMULTI_NOLIMITITEM_TAG.equals(iEasyItem_child.getEasyItemTag())) {//为null 或者是不限制
                        listFilterAdapter.setItemChecked(i, true);
                    }
                }
            }
        }
    }

    @Override
    protected boolean isEmpty() {
        ListFilterAdapter listFilterAdapter = (ListFilterAdapter) mRecyclerView1.getAdapter();
        return listFilterAdapter.isEmpty();
    }

    public void setList1Adapter(ListFilterAdapter adapter) {
        mRecyclerView1.setAdapter(adapter);
    }

    @Override
    protected void onShowMenuContent() {
        super.onShowMenuContent();
        ListFilterAdapter listFilterAdapter = (ListFilterAdapter) mRecyclerView1.getAdapter();
        listFilterAdapter.clearChoices();
        if (mMenuStatesArray != null && mMenuStatesArray.size() > 0) {
            for (int i = 0; i < mMenuStatesArray.size(); i++) {
                int posion = mMenuStatesArray.keyAt(i);
                boolean value = mMenuStatesArray.valueAt(i);
                listFilterAdapter.setItemChecked(posion, value);
            }
        } else {
            int childSelectPosion = listFilterAdapter.getEasyItemManager().getChildSelectPosion();
            if (childSelectPosion >= 0 && childSelectPosion < listFilterAdapter.getItemCount()) {
                listFilterAdapter.setItemChecked(childSelectPosion, true);//用户自己设置的选择
            }
        }
    }

    /**
     * 清除Menu状态
     */
    @Override
    public void onCleanMenuStatus() {
        if (mMenuStatesArray != null) {
            mMenuStatesArray.clear();
        }
    }

    /**
     * 添加数据到第一个列表
     *
     * @param easyItemManager 父IEasyItem
     */
    protected void addList1Items(EasyItemManager easyItemManager) {
        ListFilterAdapter listFilterAdapter = (ListFilterAdapter) mRecyclerView1.getAdapter();
        listFilterAdapter.setEasyItemManager(easyItemManager);
    }

    @Override
    public EasyItemManager getMenuData() {
        ListFilterAdapter listFilterAdapter = (ListFilterAdapter) mRecyclerView1.getAdapter();
        return listFilterAdapter.getEasyItemManager();
    }


    //Save state
    public void saveStates() {//点击确认健手动触发
        ListFilterAdapter listFilterAdapter = (ListFilterAdapter) mRecyclerView1.getAdapter();
        List<Integer> selectedPosition = listFilterAdapter.getSelectedPositions();
        mMenuStatesArray.clear();
        for (int position : selectedPosition) {
            mMenuStatesArray.put(position, true);
        }
    }

    @Override
    protected void onDismissMenuContent() {
        super.onDismissMenuContent();
        // saveStates();
    }

    public void setMenuStates(EasyMenuStates easyMenuStates) {
        super.setMenuStates(easyMenuStates);
        mMenuStatesArray.clear();
        SparseBooleanArray receiveMenuStatesArray = easyMenuStates.getMenuStatesArray().clone();
        for (int i = 0; i < receiveMenuStatesArray.size(); i++) {
            int posion = receiveMenuStatesArray.keyAt(i);
            boolean value = receiveMenuStatesArray.valueAt(i);
            mMenuStatesArray.put(posion, value);
        }

    }

    protected EasyMenuStates onCreateMenuStates(EasyItemManager easyItemManager) {
        return new EasyMenuStates.Builder()//
                .setMenuStatesArray(mMenuStatesArray)
                .setEasyItemManager(easyItemManager)
                .setEasyMenuParas(getEasyMenuParas())
                .setMenuTitle(getMenuTitle())
                .build();
    }
}
