package io.grpc.helloworldexample;

import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.reactivex.Observable;

/**
 * @author yinlei
 * @since 2018/5/21 10:50
 */
public class HelloWorldModel extends GrpcModel {

    private GreeterGrpc.GreeterBlockingStub mStub;

    public HelloWorldModel() {
        mStub = GreeterGrpc.newBlockingStub(mChannel);
    }

    public Observable<HelloReply> sayHello(final HelloRequest request) {
        return Observable.create(e -> {
            HelloReply reply = mStub.sayHello(request);
            e.onNext(reply);
            e.onComplete();
        });
        // 这个放到执行的地方
//        return RxUtils.on(observable);
    }
}
