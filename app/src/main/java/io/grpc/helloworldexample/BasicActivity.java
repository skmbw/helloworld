package io.grpc.helloworldexample;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Activity的基类，方便查找当前执行的是哪个Activity。以及一些公共方法。
 *
 * @author yinlei
 * @since 17-7-15 下午9:50
 */
public abstract class BasicActivity<T> extends AppCompatActivity implements DefaultView<T> {
    private static final String TAG = "BasicActivity";

    protected AppCompatActivity mActivity;
    protected View mRootView;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        // 必须要所有的activity都使用依赖注入才可以
//        // java.lang.IllegalArgumentException: No injector factory bound for Class<com.vteba.iterq.MainActivity>
////        AndroidInjection.inject(this); // 要在super.onCreate(savedInstanceState);之前调用
//        super.onCreate(savedInstanceState);
//        this.mActivity = this;
//        Log.d(TAG, "onCreate: 当前Activity是=[" + getClass().getName() + "]");
//        int resId = layoutResId();
//        if (resId != 0) {
//            mRootView = getLayoutInflater().inflate(resId, null, false);
//            setContentView(mRootView);
//            initView();
//            updateView(false);
//        }
//    }

    protected void hideBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    protected void setTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
//            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(title);
        }
    }

    protected void startInputMethod(final Context context) {
        Handler handler = new Handler();
        //直接启动输入法，会弹出后又消失，延迟0.5s
        handler.postDelayed(() -> {
            InputMethodManager inManager = (InputMethodManager)
                    context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }, 50);
    }

    // Home返回回调
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }


    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showError() {
    }


    /**
     * 更新视图数据
     * @param refresh 是否是刷新
     */
    protected void updateView(boolean refresh) {
    }

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 子类一定要覆盖这个方法，之所以没有将其设为抽象，是因为要改的太多
     *
     * @return 默认空
     */
    @LayoutRes
    public abstract int layoutResId();

    @Override
    public void bindData(T data) {
    }

    @Override
    public void moreData(T bean) {

    }

    @Override
    public void noData() {
        loadMoreEnd();
    }

    public void loadMoreEnd() {

    }

    @Override
    public void detail(T data) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void error(Throwable throwable) {

    }

    @Override
    public void message(Object bean) {

    }

    @Override
    public void complete() {

    }
}
