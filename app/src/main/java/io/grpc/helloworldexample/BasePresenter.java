package io.grpc.helloworldexample;

/**
 * 基础 Presenter
 *
 * @author yinlei
 * @since 2017-8-29
 */
public interface BasePresenter<T> {

    /**
     * 加载列表数据，首次加载
     *
     * @param refresh 是否下拉刷新，如果是不应该再显示加载界面和异常界面
     */
    void getData(boolean refresh);

    /**
     * 加载列表数据
     *
     * @param model 请求参数
     */
    void list(T model);

    /**
     * 加载单个数据
     *
     * @param model 请求参数
     */
    void get(T model);
}
