package io.grpc.helloworldexample;

/**
 * 绑定数据的界面接口
 *
 * @param <T> 表示服务端返回值类型的泛型参数
 *
 * @author yinlei
 * @since 2016/9/26.
 */
public interface DefaultView<T> extends BaseView {

    /**
     * 加载数据，并绑定到视图，一般用于单数据，亦可用于列表
     *
     * @param data presenter提供的数据
     */
    void bindData(T data);

    /**
     * 加载更多数据，并绑定到视图，一般用于列表，亦可用于单数据
     *
     * @param data presenter提供的数据
     */
    void moreData(T data);

    /**
     * 没有数据，展示空布局等UI交互
     */
    void noData();

    /**
     * 详情页，并绑定数据到视图，一般用于单数据，亦可用于列表
     *
     * @param data presenter提供的数据
     */
    void detail(T data);

    /**
     * 通用的加载数据
     *
     * @param data presenter提供的数据
     */
    void load(Object data);

    void error(Throwable throwable);

    void complete();

    void message(Object bean);
}
