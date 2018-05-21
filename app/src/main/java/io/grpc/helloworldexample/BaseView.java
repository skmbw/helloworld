package io.grpc.helloworldexample;

//import com.trello.rxlifecycle.LifecycleTransformer;

/**
 * 基础 BaseView 接口
 *
 * @author yinlei
 * @since 2017-8-27
 */
public interface BaseView {

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示网络错误，modify 对网络异常在 BaseActivity 和 BaseFragment 统一处理
     */
    void showError();

    /**
     * 完成刷新, 新增控制刷新
     */
    void finishRefresh();
}
