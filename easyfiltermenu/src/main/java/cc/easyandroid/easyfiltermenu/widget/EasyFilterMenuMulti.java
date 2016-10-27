package cc.easyandroid.easyfiltermenu.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cc.easyandroid.easyfiltermenu.core.EasyFilterAdapter;
import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;
import cc.easyandroid.easyfiltermenu.R;
import cc.easyandroid.easyfiltermenu.core.EasyItemManager;
import cc.easyandroid.easyfiltermenu.core.EasyMenuStates;
import cc.easyandroid.easyfiltermenu.core.IEasyItem;

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
        EasyFilterAdapter adapter_List1 = new EasyFilterAdapter(LayoutInflater.from(menuContentView.getContext()));
        adapter_List1.setMode(EasyFilterAdapter.MODE_MULTI);
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
        EasyFilterAdapter easyFilterAdapter = (EasyFilterAdapter) listView.getAdapter();
        IEasyItem iEasyItem = (IEasyItem) easyFilterAdapter.getItem(position);
        if (iEasyItem == null || IEasyItem.MENUMULTI_NOLIMITITEM_TAG.equals(iEasyItem.getEasyItemTag())) {//为null 或者是不限制
            easyFilterAdapter.clearChoices();
            easyFilterAdapter.notifyDataSetChanged();
            easyFilterAdapter.setItemChecked(position, true);
        } else {
            if (easyFilterAdapter.getSelectedPositions().size() > 0) {//检测是否有选中项，如果有就讲第一个的选中状态改变
                for (int i = 0; i < easyFilterAdapter.getItemCount(); i++) {
                    IEasyItem iEasyItem_child = (IEasyItem) easyFilterAdapter.getItem(i);
                    if (iEasyItem_child == null || IEasyItem.MENUMULTI_NOLIMITITEM_TAG.equals(iEasyItem_child.getEasyItemTag())) {//为null 或者是不限制
                        easyFilterAdapter.setItemChecked(i, false);
                    }
                }
            } else {
                for (int i = 0; i < easyFilterAdapter.getItemCount(); i++) {
                    IEasyItem iEasyItem_child = (IEasyItem) easyFilterAdapter.getItem(i);
                    if (iEasyItem_child == null || IEasyItem.MENUMULTI_NOLIMITITEM_TAG.equals(iEasyItem_child.getEasyItemTag())) {//为null 或者是不限制
                        easyFilterAdapter.setItemChecked(i, true);
                    }
                }
            }
        }
    }

    @Override
    protected boolean isEmpty() {
        EasyFilterAdapter easyFilterAdapter = (EasyFilterAdapter) mRecyclerView1.getAdapter();
        return easyFilterAdapter.isEmpty();
    }

    public void setList1Adapter(EasyFilterAdapter adapter) {
        mRecyclerView1.setAdapter(adapter);
    }

    @Override
    protected void onShowMenuContent() {
        super.onShowMenuContent();
        EasyFilterAdapter easyFilterAdapter = (EasyFilterAdapter) mRecyclerView1.getAdapter();
        easyFilterAdapter.clearChoices();
        if (mMenuStatesArray != null && mMenuStatesArray.size() > 0) {
            for (int i = 0; i < mMenuStatesArray.size(); i++) {
                int posion = mMenuStatesArray.keyAt(i);
                boolean value = mMenuStatesArray.valueAt(i);
                easyFilterAdapter.setItemChecked(posion, value);
            }
        } else {
            int childSelectPosion = easyFilterAdapter.getEasyItemManager().getChildSelectPosition();
            if (childSelectPosion >= 0 && childSelectPosion < easyFilterAdapter.getItemCount()) {
                easyFilterAdapter.setItemChecked(childSelectPosion, true);//用户自己设置的选择
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

    public List<Integer> getSelectedPositions() {
        EasyFilterAdapter easyFilterAdapter = (EasyFilterAdapter) mRecyclerView1.getAdapter();
        return easyFilterAdapter.getSelectedPositions();
    }

    /**
     * 添加数据到第一个列表
     *
     * @param easyItemManager 父IEasyItem
     */
    protected void addList1Items(EasyItemManager easyItemManager) {
        EasyFilterAdapter easyFilterAdapter = (EasyFilterAdapter) mRecyclerView1.getAdapter();
        easyFilterAdapter.setEasyItemManager(easyItemManager);
    }

    @Override
    public EasyItemManager getMenuData() {
        EasyFilterAdapter easyFilterAdapter = (EasyFilterAdapter) mRecyclerView1.getAdapter();
        return easyFilterAdapter.getEasyItemManager();
    }


    //Save state
    public void saveStates() {//点击确认健手动触发
        EasyFilterAdapter easyFilterAdapter = (EasyFilterAdapter) mRecyclerView1.getAdapter();
        List<Integer> selectedPosition = easyFilterAdapter.getSelectedPositions();
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
