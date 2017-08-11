package cc.easyandroid.easyfiltermenu.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import cc.easyandroid.easyfiltermenu.R;
import cc.easyandroid.easyfiltermenu.core.AnimatorPopup;
import cc.easyandroid.easyfiltermenu.core.EasyFilterListener;
import cc.easyandroid.easyfiltermenu.core.EasyItemManager;
import cc.easyandroid.easyfiltermenu.core.EasyMenuStates;
import cc.easyandroid.easyfiltermenu.core.IEasyItem;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

/**
 * 下拉筛选控件
 * <pre>
 * 子类通过调用setMenuContentView 传递view
 * </pre>
 */
public abstract class EasyFilterMenu extends LinearLayout implements Runnable {
    protected CharSequence defultMenuText = "easyMenuText";
    protected PopupWindow pupupWindow;
    private int xoff = 0;//x的偏移量
    private int yoff = 0;//y的偏移量
    private TextView mTitleTextView;//现实标题的textview控件
    //    private EasyItemManager easyItemManager;
    static final Executor threadExecutor = defaultExecutor();

    static Executor defaultExecutor() {
        return Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(final Runnable r) {
                return new Thread(new Runnable() {
                    @Override
                    public void run() {
                        android.os.Process.setThreadPriority(THREAD_PRIORITY_BACKGROUND);
                        r.run();
                    }
                }, "");
            }
        });
    }

    protected ArrayMap<String, String> easyMenuParas = new ArrayMap<>();


    public EasyFilterMenu(Context context) {
        this(context, null);
    }

    public EasyFilterMenu(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.EasyFilterMenuStyle);
    }

    public EasyFilterMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        int menuTitleViewResourceId = R.layout.menu_title_layout;//title是公用模块，肯定都会有
        int menuContentLayoutResourceId = R.layout.menu_content_single_layout;// menu contentView  id

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EasyFilterMenu, defStyle, 0);

        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.EasyFilterMenu_menuTitle) {//menu title
                defultMenuText = a.getText(attr);
            } else if (attr == R.styleable.EasyFilterMenu_menuTitleLayout) {// menu title 的资源id
                menuTitleViewResourceId = a.getResourceId(attr, R.layout.menu_title_layout);
            } else if (attr == R.styleable.EasyFilterMenu_menuContentLayout) {// menucontent 的资源id
                menuContentLayoutResourceId = a.getResourceId(attr, R.layout.menu_content_single_layout);
            } else if (attr == R.styleable.EasyFilterMenu_menuPupupXoff) {//x的偏移量
                xoff = a.getDimensionPixelSize(attr, 0);
            } else if (attr == R.styleable.EasyFilterMenu_menuPupupYoff) {// y的偏移量
                yoff = a.getDimensionPixelSize(attr, 0);
            }
        }

        a.recycle();

        if (menuTitleViewResourceId != -1) {
            setMenuTitleViewResourceId(menuTitleViewResourceId);
        }
        if (menuContentLayoutResourceId != -1) {
            setMenuContentViewResourceId(menuContentLayoutResourceId);
        }
    }

    /**
     * 设置弹出的view
     *
     * @param menuContentLayoutResourceId pop中的view
     */
    public void setMenuContentViewResourceId(int menuContentLayoutResourceId) {
        ViewGroup menuContentView = (ViewGroup) View.inflate(getContext(), menuContentLayoutResourceId, null);
        if (pupupWindow == null) {
            pupupWindow = new AnimatorPopup(menuContentView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, false);
            setupPupupWindow(pupupWindow);
        } else {
            pupupWindow.setContentView(menuContentView);
        }
        onMenuContentViewCreated(menuContentView, this);
    }

    protected void onMenuContentViewCreated(ViewGroup menuContentView, EasyFilterMenu easyFilterMenu) {
    }

    /**
     * setupCustomView//设置带有确定按钮的view
     *
     * @param menuContentView     父容器
     * @param customViewConfirmId 确定按钮的id
     */
    void setupCustomView(final ViewGroup menuContentView, final int customViewConfirmId) {
        final View easyListFilter_CustomViewConfirm_List = menuContentView.findViewById(customViewConfirmId);//多选择时候的确定按钮的id
        if (easyListFilter_CustomViewConfirm_List != null) {
            easyListFilter_CustomViewConfirm_List.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (customViewConfirmClickListener != null) {
                        customViewConfirmClickListener.onClick(v, menuContentView);
                        onCustomViewConfirmBuntonClicked(v, menuContentView);
                        //title 的设置请在回调中设置
                    }
                }
            });
        }
    }

    protected void onCustomViewConfirmBuntonClicked(View clickView, ViewGroup viewGroupParent) {

    }

    private EasyItemManager easyItemManager;

    public void setMenuData(boolean show, EasyItemManager easyItemManager) {
        onMenuDataPrepared(easyItemManager);
        this.easyItemManager = easyItemManager;
        if (show && easyItemManager != null && easyItemManager.isHasEasyItems()) {
            toggle();
        }
    }


    public EasyItemManager getMenuData() {
        return easyItemManager;
    }

    ;


    protected void onMenuDataPrepared(EasyItemManager easyItemManager) {

    }

    public void setMenuTitle(CharSequence menuTitle) {
        setMenuTitle(menuTitle, false);
    }

    public void setMenuTitle(CharSequence menuTitle, boolean forceHighlight) {
        mTitleTextView.setText(menuTitle);
        if (!defultMenuText.equals(menuTitle) || forceHighlight) {
            mTitleTextView.setEnabled(true);
        } else {//title 内容 相同
            mTitleTextView.setEnabled(false);
        }
    }

    public CharSequence getDefultMenuText() {
        return defultMenuText;
    }

    public String getMenuTitle() {
        return mTitleTextView.getText().toString();
    }

    /**
     * init menuTitle
     *
     * @param menuTitleViewResourceId menuTitleViewResourceId
     */
    void setMenuTitleViewResourceId(int menuTitleViewResourceId) {
        View menuTitleView = View.inflate(getContext(), menuTitleViewResourceId, this);
        mTitleTextView = (TextView) menuTitleView.findViewById(R.id.easyListFilter_MenuTitleDisplayName);
        mTitleTextView.setEnabled(false);
        mTitleTextView.setText(defultMenuText);
        mTitleTextView.setSaveEnabled(false);
        mTitleTextView.setFreezesText(false);
        menuTitleView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onMenuClick();

            }
        });
        onMenuTitleViewCreated(menuTitleView, this);

    }

    protected void onMenuTitleViewCreated(View menuTitleView, EasyFilterMenu easyFilterMenu) {

    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        Parcelable superData = super.onSaveInstanceState();
        bundle.putParcelable("super_data", superData);
        bundle.putParcelable(EASYFILTERMENU_STATE_KEY, getMenuStates());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;
        Parcelable superData = bundle.getParcelable("super_data");
        EasyMenuStates easyMenuStates = bundle.getParcelable(EASYFILTERMENU_STATE_KEY);
        if (easyMenuStates != null) {
            setMenuStates(easyMenuStates);
        }
        super.onRestoreInstanceState(superData);
    }

    public static final String EASYFILTERMENU_STATE_KEY = "EasyFilterMenuStateKey";


    /**
     * 设置pop
     *
     * @param pupupWindow pop
     */
    void setupPupupWindow(final PopupWindow pupupWindow) {
        pupupWindow.setBackgroundDrawable(new ColorDrawable(Color.argb(0, 0, 0, 0)));
        pupupWindow.setOutsideTouchable(false);
        pupupWindow.setFocusable(true);
        pupupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        pupupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        pupupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                onDismissMenuContent();

            }
        });
    }

    /**
     * Menu 被点击后，一般这里现实pop
     */
    protected void onMenuClick() {
        toggle();
    }

    /**
     * 是否是空
     *
     * @return isEmpty
     */
    public abstract boolean isEmpty();

    /**
     * 控制现实nemu的现实和关闭
     */
    public void toggle() {
        postDelayed(this, 1);
    }

    void showMenuContent() {
        if (isEmpty()) {//检查数据是否是null，
            if (menuWithoutDataClickLinstener != null) {
                menuWithoutDataClickLinstener.withoutData(this);
            }
            return;
        }
        if (pupupWindow != null && !pupupWindow.isShowing()) {
            ViewGroup parent = (ViewGroup) this.getParent();
            if (parent != null && parent.getId() == R.id.easyListFilter_MenuParent) {//如果父容器的id是easyListFilter_MenuParent，就现实在父容器上面
                pupupWindow.showAsDropDown(parent, xoff, yoff);
            } else {
                pupupWindow.showAsDropDown(this, xoff, yoff);
            }
            onShowMenuContent();
            if (menuShowListeners.size() > 0) {
                for (EasyFilterListener.OnMenuShowListener menuShowListener : this.menuShowListeners) {
                    menuShowListener.onMenuShowBefore(this, pupupWindow.getContentView());
                }
            }
        }

    }

    /**
     * pop显示后调用
     */
    protected void onShowMenuContent() {
        setFocusableInTouchMode(false);
        mTitleTextView.setSelected(true);
        setSelected(true);//当前layout 设置setSelected 位true
    }

    /**
     * pop消失后调用
     */
    protected void onDismissMenuContent() {
        mTitleTextView.setSelected(false);
        setFocusableInTouchMode(false);
        setFocusable(false);//当前layout
        if (dismissListener != null) {
            dismissListener.onDismiss();
        }
    }


    /**
     * dismiss pop
     */
    void dismissMenuContent() {
        if (pupupWindow != null && pupupWindow.isShowing()) {
            pupupWindow.dismiss();
        }
    }

    /**
     * MenuContent是否现实
     *
     * @return show
     */
    public boolean isShowing() {

        return pupupWindow != null && pupupWindow.isShowing();
    }

    public void dismiss() {
        if (pupupWindow != null && pupupWindow.isShowing()) {
            pupupWindow.dismiss();
        }
    }

    @Override
    public void run() {
        if (!isShowing()) {
            showMenuContent();
        } else {
            dismissMenuContent();
        }
    }

    /**
     * serial number
     */
    public int getMenuSerialNumber() {
        return defultMenuText.toString().hashCode();
    }

    /**
     * 设置pop小时后的监听
     *
     * @param dismissListener dismissListener
     */
    public void setOnDismissListener(OnDismissListener dismissListener) {
        this.dismissListener = dismissListener;
    }

    /**
     * 设置没有数据时候的监听回掉
     *
     * @param menuWithoutDataClickLinstener menuWithoutDataClickLinstener
     */
    public void setOnMenuWithoutDataClickLinstener(OnMenuWithoutDataClickLinstener menuWithoutDataClickLinstener) {
        this.menuWithoutDataClickLinstener = menuWithoutDataClickLinstener;
    }


    private OnDismissListener dismissListener;
    private OnMenuWithoutDataClickLinstener menuWithoutDataClickLinstener;

    /**
     * pop消失监听
     */

    public interface OnDismissListener {
        void onDismiss();
    }

    /**
     * 没有数据时候的回掉借口
     */
    public interface OnMenuWithoutDataClickLinstener {
        void withoutData(EasyFilterMenu menu);
    }

    EasyFilterListener.OnCustomViewConfirmClickListener customViewConfirmClickListener;

    public <T extends EasyFilterMenu> void setOnCustomViewConfirmClickListener(EasyFilterListener.OnCustomViewConfirmClickListener<T> customViewConfirmClickListener) {
        this.customViewConfirmClickListener = customViewConfirmClickListener;
    }


    public void setOnMenuListItemClickListener(EasyFilterListener.OnMenuListItemClickListener menuListItemClickListener) {
        this.menuListItemClickListener = menuListItemClickListener;
    }

    protected EasyFilterListener.OnMenuListItemClickListener menuListItemClickListener;

    public void setMenuStates(EasyMenuStates easyMenuStates) {
        setMenuData(false, easyMenuStates.getEasyItemManager());
        setMenuParas(easyMenuStates.getEasyMenuParas());//

        boolean forceHighlight = false;
        if (easyMenuParas != null && !easyMenuParas.isEmpty()) {
            for (String value : easyMenuParas.values()) {
                if (!TextUtils.isEmpty(value)) {
                    forceHighlight = true;//只要有一个参数不是null，就高亮显示
                    break;
                }
            }
        }
        setMenuTitle(easyMenuStates.getMenuTitle(), forceHighlight);
    }

    public void cleanMenuStates() {
        clearEasyMenuParas();//清除参数
        setMenuTitle(defultMenuText);
        onCleanMenuStatus();
    }

    protected abstract void onCleanMenuStatus();

    /**
     * 获取当前menu的数据状态，可以传给其他menu使用
     *
     * @return
     */
    public EasyMenuStates getMenuStates() {
        EasyItemManager easyItemManager = getMenuData();
        if (easyItemManager == null) {
            return null;
        } else {
            return onCreateMenuStates(easyItemManager);
        }
    }

    protected abstract EasyMenuStates onCreateMenuStates(EasyItemManager easyItemManager);

    ;

    //    EasyFilterListener.OnMenuShowListener menuShowListener;
    /**
     * 可能有多个地方需要监听
     */
    ArrayList<EasyFilterListener.OnMenuShowListener> menuShowListeners = new ArrayList<>();

    public void addOnMenuShowListener(EasyFilterListener.OnMenuShowListener menuShowListener) {
        this.menuShowListeners.add(menuShowListener);
    }

    public void menuListItemClick(IEasyItem iEasyItem) {
        addParaFromIEasyItem(iEasyItem);//记录参数
        if (this.menuListItemClickListener != null) {
            this.menuListItemClickListener.onClick(this, iEasyItem);
        }
        this.dismiss();
    }

    private void addParaFromIEasyItem(IEasyItem iEasyItem) {
        this.easyMenuParas.putAll(iEasyItem.getEasyParameter());
        if (onEasyMenuParasChangedListener != null) {//
            onEasyMenuParasChangedListener.onChanged(this.easyMenuParas);
        }
    }

    private EasyFilterListener.OnEasyMenuParasChangedListener onEasyMenuParasChangedListener;

    public void setOnEasyMenuParasChangedListener(EasyFilterListener.OnEasyMenuParasChangedListener onEasyMenuParasChangedListener) {
        this.onEasyMenuParasChangedListener = onEasyMenuParasChangedListener;
    }

    /**
     * 恢复参数，恢复数据不会调用changed
     *
     * @param easyMenuParas
     */
    public void setMenuParas(Map<String, String> easyMenuParas) {
        this.easyMenuParas.putAll(easyMenuParas);
    }

    public ArrayMap<String, String> getEasyMenuParas() {
        return this.easyMenuParas;
    }

    public void deleteEasyMenuPara(String key) {
        this.easyMenuParas.remove(key);
    }

    /**
     * 清除参数
     */
    public void clearEasyMenuParas() {
        this.easyMenuParas.clear();
    }

    public boolean hasSelectedValues() {
        return true;
    }

}
