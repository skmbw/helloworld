package io.grpc.helloworldexample;

import android.util.Log;

import io.reactivex.Observable;

/**
 * Presenter的通用代码的实现，简化编码。
 *
 * @param <IN> 表示参数值类型的泛型参数
 * @param <OUT> 表示服务端返回值类型的泛型参数
 *
 * @author yinlei
 * @since 2017/9/8 16:50
 */
public abstract class DefaultPresenter<IN, OUT> implements BasePresenter<IN> {
    private final String TAG = getClass().getSimpleName();
    protected DefaultView<OUT> mDefaultView;
    protected int mPage = 1;

    public DefaultPresenter(DefaultView<OUT> defaultView) {
        mDefaultView = defaultView;
    }

    /**
     * getData观察者通用实现，回调View.bindData
     *
     * @param observable 观察者
     * @param refresh 是否是刷新
     */
    public void doGetData(final Observable<OUT> observable, final boolean refresh) {
        RxUtils.on(observable).doOnSubscribe(disposable -> mDefaultView.showLoading())
                .subscribe(jsonBean -> {
                    Log.d(TAG, "getData: success");
                    if (jsonBean == null) {
                        Log.d(TAG, "getData: JsonBean is null.");
                        mDefaultView.showError();
                    } else {
                        // 第一次且没有拿到数据，显示空视图，nodata，BaseQuickAdapter没显示
                        if (refresh && mPage == 1) {
                            mDefaultView.noData();
                        }
                    }
                    mDefaultView.bindData(jsonBean);
                }, throwable -> {
                    Log.e(TAG, "doGetData: error.", throwable);
                    if (refresh) {
                        mDefaultView.finishRefresh();
                    }
                    mDefaultView.showError();
                }, () -> {
                    Log.d(TAG, "doGetData: complete.");
                    if (refresh) {
                        mDefaultView.finishRefresh();
                    }
                    mDefaultView.hideLoading();
                });
    }

    /**
     * list观察者通用实现，回调View.moreData
     *
     * @param observable 观察者
     */
    public void doList(final Observable<OUT> observable) {
        RxUtils.on(observable).doOnSubscribe(disposable -> mDefaultView.showLoading())
                .subscribe(response -> {
                    Log.d(TAG, "list: success");
                    if (response == null) {
                        Log.d(TAG, "list: JsonBean is null.");
                        mDefaultView.showError();
                    } else {
                        // 第一次且没有拿到数据，显示空视图，nodata，BaseQuickAdapter没显示
                        if (mPage == 1) {
                            mDefaultView.noData();
                        }
                    }
                    mDefaultView.moreData(response);
                }, throwable -> {
                    Log.e(TAG, "doList: error.", throwable);
                    mDefaultView.showError();
                }, () -> {
                    Log.d(TAG, "doList: complete.");
                    mDefaultView.hideLoading();
                });
    }

    /**
     * get观察者通用实现，回调View.detail
     *
     * @param observable 观察者
     */
    public void doGet(final Observable<OUT> observable) {
        RxUtils.on(observable).doOnSubscribe(disposable -> mDefaultView.showLoading())
                .subscribe(response -> {
                    Log.d(TAG, "detail: success");
                    if (response == null) {
                        Log.d(TAG, "detail: JsonBean is null.");
                        mDefaultView.showError();
                    } else {
                        mDefaultView.detail(response);
                    }
                }, throwable -> {
                    Log.e(TAG, "doDetail: error.", throwable);
                    mDefaultView.showError();
                }, () -> {
                    Log.d(TAG, "doDetail: complete.");
                    mDefaultView.hideLoading();
                });
    }

    /**
     * detail 观察者通用实现，回调View.detail
     *
     * @param observable 观察者
     */
    public <V> void doLoad(final Observable<V> observable) {
        RxUtils.on(observable).doOnSubscribe(disposable -> mDefaultView.showLoading())
                .subscribe(response -> {
                    Log.d(TAG, "detail: success");
                    if (response == null) {
                        Log.d(TAG, "detail: JsonBean is null.");
                        mDefaultView.showError();
                    } else {
                        mDefaultView.load(response);
                    }
                }, throwable -> {
                    Log.e(TAG, "doDetail: error.", throwable);
                    mDefaultView.showError();
                }, () -> {
                    Log.d(TAG, "doDetail: complete.");
                    mDefaultView.hideLoading();
                });
    }

    public void doAdd(final Observable<OUT> observable) {
        RxUtils.on(observable).subscribe(jsonBean -> {
            Log.d(TAG, "accept: ");
            mDefaultView.message(jsonBean);
        }, throwable -> {
            Log.e(TAG, "accept: error ", throwable);
            mDefaultView.error(throwable);
        }, () -> mDefaultView.complete());
    }
}
