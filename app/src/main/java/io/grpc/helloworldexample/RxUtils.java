package io.grpc.helloworldexample;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava工具代码
 *
 * @author yinlei
 * @since 2017/9/8 17:00
 */
public class RxUtils {

    /**
     * Do on rx subscribe
     *
     * @param observable 观察者
     * @param <T> 数据泛型
     * @return 结果
     */
    public static <T> Observable<T> on(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

//    /**
//     * 倒计时
//     *
//     * @param seconds 秒
//     * @return 可观察者
//     */
//    public static Observable<Integer> countdown(int seconds) {
//        if (seconds < 0) {
//            seconds = 0;
//        }
//        final int countTime = seconds;
//        return on(Observable.interval(0, 1, TimeUnit.SECONDS)
//                .map(increaseTime -> countTime - increaseTime.intValue()).take(countTime + 1));
//    }
}
