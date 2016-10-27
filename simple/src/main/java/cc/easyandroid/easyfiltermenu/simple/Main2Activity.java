package cc.easyandroid.easyfiltermenu.simple;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import cc.easyandroid.easyfiltermenu.core.EasyFilterListener;
import cc.easyandroid.easyfiltermenu.core.EasyItemManager;
import cc.easyandroid.easyfiltermenu.core.EasyMenuStates;
import cc.easyandroid.easyfiltermenu.core.IEasyItem;
import cc.easyandroid.easyfiltermenu.core.SimpleEasyItem;
import cc.easyandroid.easyfiltermenu.simple.no.NoLimitItem1;
import cc.easyandroid.easyfiltermenu.widget.EasyFileterMenuCustom;
import cc.easyandroid.easyfiltermenu.widget.EasyFilterMenu;
import cc.easyandroid.easyfiltermenu.widget.EasyFilterMenuMulti;
import cc.easyandroid.easyfiltermenu.widget.EasyFilterMenuSingle;
import cc.easyandroid.easyfiltermenu.widget.EasyMenuContainer;

public class Main2Activity extends AppCompatActivity {
    EasyFilterMenuSingle menuFilter1;
    EasyFilterMenuSingle menuFilter2;
    EasyFilterMenuMulti menuFilter3;
    EasyFileterMenuCustom menuFilter4;

    EasyMenuContainer easyMenuContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        menuFilter1 = (EasyFilterMenuSingle) findViewById(R.id.menuFilter1);
        menuFilter2 = (EasyFilterMenuSingle) findViewById(R.id.menuFilter2);
        menuFilter3 = (EasyFilterMenuMulti) findViewById(R.id.menuFilter3);
        menuFilter4 = (EasyFileterMenuCustom) findViewById(R.id.menuFilter4);

        easyMenuContainer = (EasyMenuContainer) findViewById(R.id.easyMenuContainer);

        final ArrayList lists1 = dd();
        final ArrayList lists2 = dd();
        final ArrayList lists3 = dd();
        final ArrayList<Item2> lists4 = dd2();
        for (int i = 0; i < lists1.size(); i++) {
            Item1 item1 = (Item1) lists1.get(i);
            ArrayList lists5 = item1.getEasyItemManager().getEasyItems();
            lists5.add(0, new NoLimitItem1(item1.getEasyItemTag().toString()));
        }
        lists1.add(0, new NoLimitItem1());
        EasyItemManager easyItemManager1 = new EasyItemManager(lists1);
        easyItemManager1.setChildSelectPosion(2);
//        menuFilter1.setMenuData(false, easyItemManager1);
        lists2.add(0, new NoLimitItem1());
//        menuFilter2.setMenuData(false, new EasyItemManager(lists2));
        lists3.add(0, new NoLimitItem1());
//        menuFilter3.setMenuData(false, new EasyItemManager(lists3));

//        QfangResult<PagingResult<Area>> qfangResult;

//        menuFilter4.setMenuData(false, new EasyItemManager(lists4));

        menuFilter1.setOnMenuWithoutDataClickLinstener(new EasyFilterMenu.OnMenuWithoutDataClickLinstener() {
            @Override
            public void withoutData(EasyFilterMenu menu) {
                Toast.makeText(getApplicationContext(), "没有数据,马上加载数据...", Toast.LENGTH_SHORT).show();
                menuFilter1.setMenuData(true, new EasyItemManager(lists1));
            }
        });
        menuFilter2.setOnMenuWithoutDataClickLinstener(new EasyFilterMenu.OnMenuWithoutDataClickLinstener() {
            @Override
            public void withoutData(EasyFilterMenu menu) {
                Toast.makeText(getApplicationContext(), "没有数据,马上加载数据...", Toast.LENGTH_SHORT).show();
                menuFilter2.setMenuData(true, new EasyItemManager(lists2));
            }
        });
        menuFilter3.setOnMenuWithoutDataClickLinstener(new EasyFilterMenu.OnMenuWithoutDataClickLinstener() {
            @Override
            public void withoutData(EasyFilterMenu menu) {
                Toast.makeText(getApplicationContext(), "没有数据,马上加载数据...", Toast.LENGTH_SHORT).show();
                menuFilter3.setMenuData(true, new EasyItemManager(lists3));
            }
        });
        menuFilter4.setOnMenuWithoutDataClickLinstener(new EasyFilterMenu.OnMenuWithoutDataClickLinstener() {
            @Override
            public void withoutData(EasyFilterMenu menu) {
                Toast.makeText(getApplicationContext(), "没有数据,马上加载数据...", Toast.LENGTH_SHORT).show();
                menuFilter4.setMenuData(true, new EasyItemManager(lists4));
            }
        });
        menuFilter1.setOnMenuListItemClickListener(new EasyFilterListener.OnMenuListItemClickListener() {
            @Override
            public void onClick(EasyFilterMenu easyFilterMenu, IEasyItem iEasyItem) {
                Toast.makeText(getApplicationContext(), iEasyItem.getEasyItemTag(), Toast.LENGTH_SHORT).show();
            }
        });



        menuFilter3.setOnCustomViewConfirmClickListener(new EasyFilterListener.OnCustomViewConfirmClickListener() {
            @Override
            public void onClick(View listview, ViewGroup viewGroup) {
                menuFilter3.saveStates();
//                listview.clearChoices();
                Toast.makeText(getApplicationContext(), "确定按钮被点击", Toast.LENGTH_SHORT).show();
                menuFilter3.dismiss();
                menuFilter3.setMenuTitle("多选");
            }
        });
        menuFilter4.setOnCustomViewConfirmClickListener(new EasyFilterListener.OnCustomViewConfirmClickListener() {
            @Override
            public void onClick(View v, ViewGroup viewGroup) {
                switch (v.getId()) {
                    case R.id.easyListFilter_CustomViewConfirm_View2:
                        menuFilter4.saveStates();
                        EasyItemManager easyItemManager = menuFilter4.getMenuData();
                        ArrayMap<String, String> easyParameter = menuFilter4.getEasyMenuParas();
                        Set<String> keySet = easyParameter.keySet();
                        for (String key : keySet) {
                            easyParameter.put(key, null);
                        }
                        if (easyItemManager != null) {
                            IEasyItem iEasyItem = new MenuCustomIEasyItem(easyItemManager);//构造一个item
                            menuFilter4.menuListItemClick(iEasyItem);

                            menuFilter4.handleMenuTitle();
                        }
//                        menuFilter4.dismiss();
                        break;
                    case R.id.easyListFilter_CustomViewConfirm_View1:
                        menuFilter4.clearTempSelect();
                        break;
                }

//                listview.clearChoices();
//                menuFilter4.
//                Toast.makeText(getApplicationContext(), "确定按钮被点击", Toast.LENGTH_SHORT).show();
//                menuFilter3.dismiss();
//                menuFilter3.setMenuTitle("多选");
            }
        });


//        menuFilter2.addOnMenuShowListener(new EasyFilterListener.OnMenuShowListener() {
//            @Override
//            public void onMenuShowBefore(EasyFilterMenu menu, View view) {
//                System.out.println("onMenuShowBefore  hasSelectedValues=" + menu.hasSelectedValues());
//                EditText editText = (EditText) view.findViewById(R.id.editText);
//                if( menu.hasSelectedValues()){
////                    editText.clearComposingText();
//                    editText.setText("");
//                }
////                menu.cleanMenuStates();
//            }
//        });

//        menuFilter2.setOnCustomViewConfirmClickListener(new EasyFilterListener.OnCustomViewConfirmClickListener<EasyFilterMenuSingle>() {
//            @Override
//            public void onClick(ListView listview, ViewGroup viewGroup, EasyFilterMenuSingle easyFilterMenu) {
////                easyFilterMenu.rememberPosion((ListFilterAdapter) listview.getAdapter(), ListView.INVALID_POSITION, false);
//            }
//        });
        SparseArray<EasyMenuStates> singleSelectionMenuStates = getIntent().getExtras().getSparseParcelableArray("sparseArray");
        if (singleSelectionMenuStates != null) {
            easyMenuContainer.setAllMenuStates(singleSelectionMenuStates);
        }
    }

    private void setupListItemClickListener(EasyFilterMenu easyFilterMenu) {
        easyFilterMenu.setOnMenuListItemClickListener(new EasyFilterListener.OnMenuListItemClickListener() {
            @Override
            public void onClick(EasyFilterMenu easyFilterMenu, IEasyItem iEasyItem) {
                Toast.makeText(getApplicationContext(), iEasyItem.getEasyItemTag(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupBuntonClickListener(EasyMenuContainer easyMenuContainer) {
//        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
//        Bundle bundle = new Bundle();
//        if (easyMenuContainer != null) {
//            SparseArray<EasyMenuStates> sparseArray = easyMenuContainer.getAllMenuStates();
//            bundle.putSparseParcelableArray("sparseArray", sparseArray);
//        }
//        intent.putExtras(bundle);
//        startActivity(intent);
    }


    public ArrayList<Item1> dd() {
        Text1 text1 = new Gson().fromJson(Text.text, Text1.class);
        final ArrayList<Item1> lists = text1.getResult();
        return lists;
    }

    public ArrayList<Item2> dd2() {
        Text2 text1 = new Gson().fromJson(Text.text, Text2.class);
        final ArrayList<Item2> lists = text1.getResult();
        return lists;
    }


    @Override
    protected void onDestroy() {
//        easyListFilterMenu.dismissMenuContent();
        super.onDestroy();
    }

    public class MenuCustomIEasyItem extends SimpleEasyItem {
        private final EasyItemManager mEasyItemManager;//= getMenuData();
        HashMap<String, String> easyParameter = new HashMap<>();

        public MenuCustomIEasyItem(EasyItemManager easyItemManager) {
            this.mEasyItemManager = easyItemManager;
        }

        @Override
        public HashMap<String, String> getEasyParameter() {
            if (mEasyItemManager != null) {
                ArrayList<? extends IEasyItem> iEasyItems = mEasyItemManager.getEasyItems();
                for (IEasyItem iEasyItem : iEasyItems) {
                    ArrayList<? extends IEasyItem> childEasyItems = iEasyItem.getEasyItemManager().getEasyItems();
                    int childSelectPosion = iEasyItem.getEasyItemManager().getChildSelectPosion();
                    if (childSelectPosion >= 0 && childSelectPosion < childEasyItems.size()) {
                        easyParameter.putAll(childEasyItems.get(iEasyItem.getEasyItemManager().getChildSelectPosion()).getEasyParameter());
                    }
                }
            }
            return easyParameter;
        }

        @Override
        public EasyItemManager getEasyItemManager() {
            return mEasyItemManager;
        }
    }

}
