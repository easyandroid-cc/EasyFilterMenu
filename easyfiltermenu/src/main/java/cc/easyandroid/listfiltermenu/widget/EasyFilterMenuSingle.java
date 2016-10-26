package cc.easyandroid.listfiltermenu.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;
import cc.easyandroid.easyrecyclerview.SelectableAdapter;
import cc.easyandroid.listfiltermenu.R;
import cc.easyandroid.listfiltermenu.core.EasyItemManager;
import cc.easyandroid.listfiltermenu.core.EasyMenuStates;
import cc.easyandroid.listfiltermenu.core.EasyUtils;
import cc.easyandroid.listfiltermenu.core.IEasyItem;
import cc.easyandroid.listfiltermenu.core.ListFilterAdapter;

/**
 * 最多3个列表的单选
 */
public class EasyFilterMenuSingle extends EasyFilterMenu {

    protected RecyclerView mRecyclerView1;
    protected RecyclerView mRecyclerView2;
    protected RecyclerView mRecyclerView3;

    /**
     * 有时候list 会有比较复杂的布局，用这个装在里面（list1要一直现实，list1就不用了，不影响效果）
     */
    private View list2Box;//装list1的盒子
    private View list3Box;//装list1的盒子

    public EasyFilterMenuSingle(Context context) {
        this(context, null);
    }

    public EasyFilterMenuSingle(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.EasyFilterMenuStyle);
    }

    public EasyFilterMenuSingle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMenuContentViewCreated(ViewGroup menuContentView, EasyFilterMenu easyFilterMenu) {
        //listview--1
        mRecyclerView1 = (RecyclerView) menuContentView.findViewById(R.id.easyListFilter_MenuContent_List_1);
        final ListFilterAdapter list1Adapter = new ListFilterAdapter(LayoutInflater.from(menuContentView.getContext()));
        list1Adapter.setMode(SelectableAdapter.MODE_SINGLE);
        mRecyclerView1.setAdapter(list1Adapter);
        setupListView(mRecyclerView1);
        mRecyclerView1.setVisibility(View.VISIBLE);
        setupCustomView(menuContentView, R.id.easyListFilter_CustomViewConfirm_View1);//设置带有确定按钮的view
        setupCustomView(menuContentView, R.id.easyListFilter_CustomViewConfirm_View2);//设置带有确定按钮的view
        setupCustomView(menuContentView, R.id.easyListFilter_CustomViewConfirm_View3);//设置带有确定按钮的view
        list1Adapter.initializeListeners(new EasyFlexibleAdapter.OnItemClickListener() {
            @Override
            public boolean onItemClick(final int position) {
                boolean nextListIsVisible = mRecyclerView2 != null && mRecyclerView2.getVisibility() == View.VISIBLE;
                if (!clickPositionIsChanged(mRecyclerView1, position) && nextListIsVisible) {//下一个listview 是显示的，且当前点击的位置是上次记住的位置
                    return true;
                }
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setMenuList1State(position, true);
                        mRecyclerView1.setTag(position);//tag记录的就是上一次被点击的位置
                    }
                }, 10);
                return false;
            }
        });

        //listview--2
        mRecyclerView2 = (RecyclerView) menuContentView.findViewById(R.id.easyListFilter_MenuContent_List_2);
        if (mRecyclerView2 != null) {
            list2Box = menuContentView.findViewById(R.id.easyListFilter_MenuContent_List_2Box);
            ListFilterAdapter list2Adapter = new ListFilterAdapter(LayoutInflater.from(getContext()));
            list2Adapter.setMode(SelectableAdapter.MODE_SINGLE);
            mRecyclerView2.setAdapter(list2Adapter);
            setupListView(mRecyclerView2);
            EasyUtils.hideView(list2Box);
            EasyUtils.hideView(mRecyclerView2);
            setupCustomView(menuContentView, R.id.easyListFilter_CustomViewConfirm_View2);//设置带有确定按钮的view
            list2Adapter.initializeListeners(new EasyFlexibleAdapter.OnItemClickListener() {
                @Override
                public boolean onItemClick(final int position) {
                    boolean nextListIsVisible = mRecyclerView3 != null && mRecyclerView3.getVisibility() == View.VISIBLE;
                    if (!clickPositionIsChanged(mRecyclerView2, position) && nextListIsVisible) {//下一个listview 是显示的，且当前点击的位置是上次记住的位置
                        return true;
                    }
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setMenuList2State(position, true);
                            mRecyclerView2.setTag(position);
                        }
                    }, 10);
                    return true;
                }
            });

            //listview--3
            mRecyclerView3 = (RecyclerView) menuContentView.findViewById(R.id.easyListFilter_MenuContent_List_3);
            if (mRecyclerView3 != null) {
                list3Box = menuContentView.findViewById(R.id.easyListFilter_MenuContent_List_3Box);
                ListFilterAdapter list3Adapter = new ListFilterAdapter(LayoutInflater.from(getContext()));
                list3Adapter.setMode(SelectableAdapter.MODE_SINGLE);
                mRecyclerView3.setAdapter(list3Adapter);
                setupListView(mRecyclerView3);
                EasyUtils.hideView(mRecyclerView3);
                EasyUtils.hideView(mRecyclerView3);
                setupCustomView(menuContentView, R.id.easyListFilter_CustomViewConfirm_View3);//设置带有确定按钮的view
                list3Adapter.initializeListeners(new EasyFlexibleAdapter.OnItemClickListener() {
                    @Override
                    public boolean onItemClick(final int position) {
                        postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                setMenuList3State(position, true);
                            }
                        }, 10);
                        return true;
                    }
                });
            }
        }
    }

    //Save state
    public void saveStates() {//点击确认健手动触发
        ListFilterAdapter listFilterAdapter = (ListFilterAdapter) mRecyclerView1.getAdapter();
        listFilterAdapter.saveSelectTempPosition();

    }

    @Override
    protected boolean isEmpty() {
        ListFilterAdapter adapter = (ListFilterAdapter) mRecyclerView1.getAdapter();
        return adapter.isEmpty();
    }

    void setupListView(RecyclerView listView) {
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void onShowMenuContent() {
        super.onShowMenuContent();
        ListFilterAdapter listFilterAdapter = (ListFilterAdapter) mRecyclerView1.getAdapter();
        setMenuList1State(listFilterAdapter.getEasyItemManager().getChildSelectPosion(), false);//pop显示的时候去检查看是要现实哪一个

    }

    @Override
    protected void onDismissMenuContent() {
        super.onDismissMenuContent();
        ListFilterAdapter listFilterAdapter = (ListFilterAdapter) mRecyclerView1.getAdapter();
        listFilterAdapter.resetAllChildSelectTempPosition();
        listFilterAdapter.notifyDataSetChanged();
    }

    /**
     * 设置第一个列表的选中项
     *
     * @param position 位置
     */
    public void setMenuList1State(final int position, final boolean fromUserClick) {
        final ListFilterAdapter listFilterAdapter = (ListFilterAdapter) mRecyclerView1.getAdapter();
        if (position < 0) {
            listFilterAdapter.clearChoices();
            return;
        }
//        final ListFilterAdapter listFilterAdapter = (ListFilterAdapter) mRecyclerView1.getAdapter();
        IEasyItem iEasyItem = (IEasyItem) listFilterAdapter.getItem(position);
        if (position >= 0) {
            listFilterAdapter.setItemChecked(position, true);//标记选中项
        }
        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                rememberPosion(listFilterAdapter, position, fromUserClick);//让父 记住被选中的子的位置
            }
        });

        if (iEasyItem != null) {
            EasyItemManager easyItemManager = iEasyItem.getEasyItemManager();//如果child不是null，就吧第二个现实出来
            if (easyItemManager.isHasEasyItems()) {
                addList2Items(easyItemManager);//传的是父类的IEasyItem ，适配器自己去里面找

                if (mRecyclerView2 != null) {
                    EasyUtils.showView(mRecyclerView2);
                    EasyUtils.showView(list2Box);
                    EasyUtils.hideView(mRecyclerView3);
                    EasyUtils.hideView(list3Box);
                    setMenuList2State(easyItemManager.getChildSelectPosion(), false);
                } else {
                    handleSelectEnd(fromUserClick, iEasyItem);
                }
            } else {
                ((ListFilterAdapter) mRecyclerView1.getAdapter()).clearAllChildPosition(); // 点击lise1中的不限制，清除list1中记录的childselected 记录，
                EasyUtils.hideView(mRecyclerView2);
                EasyUtils.hideView(list2Box);

                EasyUtils.hideView(mRecyclerView3);
                EasyUtils.hideView(list3Box);
                handleSelectEnd(fromUserClick, iEasyItem);
            }

        }
    }

    private void handleSelectEnd(boolean fromUserClick, IEasyItem iEasyItem) {
        changMenuText(iEasyItem);
        if (fromUserClick) {
            menuListItemClick(iEasyItem);//点击后会关闭pop
        }
    }

    /**
     * 设置第二个列表
     *
     * @param position 要选择的位置
     */
    public void setMenuList2State(final int position, final boolean fromUserClick) {
        final ListFilterAdapter listFilterAdapter = (ListFilterAdapter) mRecyclerView2.getAdapter();
        if (position < 0) {
            listFilterAdapter.clearChoices();
            return;
        }
        if (mRecyclerView2 == null) {
            return;
        }
        IEasyItem iEasyItem = (IEasyItem) listFilterAdapter.getItem(position);
        if (position >= 0) {
            listFilterAdapter.setItemChecked(position, true);//标记选中项
        }
        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if (fromUserClick) {//防止第一次显示时候执行，主要是在list1的item被点击后
                    ((ListFilterAdapter) mRecyclerView1.getAdapter()).clearAllChildPosition(); // 清除list1中记录的childposion，
                }
                rememberPosion(listFilterAdapter, position, fromUserClick);//MULTI状态才会记住

                post(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView1.getAdapter().notifyDataSetChanged();//刷新列表1
                    }
                });
            }
        });
        if (iEasyItem != null) {
            EasyItemManager easyItemManager = iEasyItem.getEasyItemManager();
            if (easyItemManager.isHasEasyItems()) {
                if (mRecyclerView3 != null) {
                    addList3Items(easyItemManager);
                    EasyUtils.showView(mRecyclerView3);
                    EasyUtils.showView(list3Box);
                    setMenuList3State(easyItemManager.getChildSelectPosion(), false);
                } else {
                    if (fromUserClick) {
                        changMenuText(iEasyItem);//这里和listview1有区别
                    }
                    if (fromUserClick) {
                        menuListItemClick(iEasyItem);//点击后会关闭pop
                    }
                }
            } else {
                threadExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (fromUserClick) {//防止第一次显示时候执行，主要是在list1的item被点击后
                            ((ListFilterAdapter) mRecyclerView2.getAdapter()).clearAllChildPosition(); // 清除list1中记录的childposion，
                        }
                    }
                });

                EasyUtils.hideView(mRecyclerView3);
                EasyUtils.hideView(list3Box);
                if (fromUserClick) {
                    changMenuText(iEasyItem);//这里和listview1有区别
                }
                if (fromUserClick) {
                    menuListItemClick(iEasyItem);//点击后会关闭pop
                }
            }
        }
    }

    /**
     * 设置第三个列表
     *
     * @param position      要选择的位置
     * @param fromUserClick 是否来自用户点击
     */
    public void setMenuList3State(final int position, final boolean fromUserClick) {
        final ListFilterAdapter listFilterAdapter = (ListFilterAdapter) mRecyclerView1.getAdapter();
        if (position < 0) {
            listFilterAdapter.clearSelection();
            return;
        }
        if (mRecyclerView3 == null) {
            return;
        }
        IEasyItem iEasyItem = (IEasyItem) listFilterAdapter.getItem(position);
        if (position >= 0) {
            listFilterAdapter.setItemChecked(position, true);//标记选中项
        }
        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if (fromUserClick) {
                    ((ListFilterAdapter) mRecyclerView2.getAdapter()).clearAllChildPosition(); // 清除list1中记录的chil dposion，
                }
                rememberPosion(listFilterAdapter, position, fromUserClick);//MULTI状态才会记住
                post(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView2.getAdapter().notifyDataSetChanged();
                        mRecyclerView1.getAdapter().notifyDataSetChanged();
                    }
                });
            }
        });

        if (fromUserClick) {
            changMenuText(iEasyItem);//这里和listview1有区别
        }
        if (fromUserClick) {
            menuListItemClick(iEasyItem);//点击后会关闭pop
        }
    }


    /**
     * 如果是第一个列表点击不限，就现实默认的，然后是其他，就现实上一层的选中项
     *
     * @param iEasyItem 点击的item
     */
    private void changMenuText(IEasyItem iEasyItem) {
        if (iEasyItem != null) {
            CharSequence displayName = iEasyItem.getEasyItemTag();
            if (!TextUtils.isEmpty(displayName)) {
                setMenuTitle(displayName);
            } else {
                setMenuTitle(defultMenuText);
            }
        }
    }


    /**
     * 数据准备好了  直接传送的是父item
     *
     * @param easyItemManager easyItemManager
     */
    @Override
    protected void onMenuDataPrepared(EasyItemManager easyItemManager) {
        addList1Items(easyItemManager);//
    }

    /**
     * 添加数据到第一个列表
     *
     * @param easyItemManager 父IEasyItem
     */
    private void addList1Items(EasyItemManager easyItemManager) {
        ListFilterAdapter listFilterAdapter = (ListFilterAdapter) mRecyclerView1.getAdapter();
        listFilterAdapter.setEasyItemManager(easyItemManager);
    }

    /**
     * 添加数据到第二个列表
     *
     * @param easyItemManager 父IEasyItem
     */
    private void addList2Items(EasyItemManager easyItemManager) {
        if (mRecyclerView2 != null) {
            ListFilterAdapter listFilterAdapter = (ListFilterAdapter) mRecyclerView2.getAdapter();
            listFilterAdapter.setEasyItemManager(easyItemManager);
        }
    }

    /**
     * 添加数据到第三个列表
     *
     * @param easyItemManager 父IEasyItem
     */
    private void addList3Items(EasyItemManager easyItemManager) {
        if (mRecyclerView3 != null) {
            ListFilterAdapter listFilterAdapter = (ListFilterAdapter) mRecyclerView3.getAdapter();
            listFilterAdapter.setEasyItemManager(easyItemManager);
        }
    }

    /**
     * 检测改listview 的item 是否被选择的
     *
     * @param listView 检查的列表
     * @param position 位置
     * @return 点击位置是否改变
     */
    private boolean clickPositionIsChanged(RecyclerView listView, int position) {
        boolean clickIsHasChosen = false;//被选择的item不再重复执行逻辑
        Object tag = listView.getTag();
        if (tag == null || !(tag instanceof Integer)) {
            clickIsHasChosen = true;
        } else {
            int selectedItemId = (int) tag;
            if (selectedItemId != position) {
                clickIsHasChosen = true;
            }
        }
        return clickIsHasChosen;
    }


    /**
     * 让ParentIEasyItem记住ChildIEasyItem被选中的位置
     *
     * @param adapter  适配器
     * @param position 位置
     */

    public void rememberPosion(ListFilterAdapter adapter, int position, boolean fromUserClick) {
        EasyItemManager easyItemManager = adapter.getEasyItemManager();
        if (easyItemManager != null) {
            adapter.getEasyItemManager().setChildSelectPosion(position);
            if (fromUserClick) {
                adapter.getEasyItemManager().setChildSelected(true);
            }
        }
    }

    public void setMenuStates(EasyMenuStates easyMenuStates) {
        super.setMenuStates(easyMenuStates);
    }

    @Override
    protected void onCleanMenuStatus() {
        final ListFilterAdapter listFilterAdapter = (ListFilterAdapter) mRecyclerView1.getAdapter();
        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                listFilterAdapter.clearAllChildPosition();
            }
        });
        setMenuList1State(0, false);
    }

    @Override
    public EasyItemManager getMenuData() {
        ListFilterAdapter listFilterAdapter = (ListFilterAdapter) mRecyclerView1.getAdapter();
        return listFilterAdapter.getEasyItemManager();
    }

    protected EasyMenuStates onCreateMenuStates(EasyItemManager easyItemManager) {
        return new EasyMenuStates.Builder()//
                .setEasyItemManager(easyItemManager)
                .setEasyMenuParas(getEasyMenuParas())
                .setMenuTitle(getMenuTitle())
                .build();
    }

    @Override
    public boolean hasSelectedValues() {
        ListFilterAdapter adapter = (ListFilterAdapter) mRecyclerView1.getAdapter();
        List list = adapter.getSelectedPositions();
        if (list == null || list.size() <= 0) {
            return false;
        } else {
            return true;
        }
    }
}
