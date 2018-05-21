package io.grpc.helloworldexample;

import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

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
        Observable<HelloReply> observable = Observable.create(new ObservableOnSubscribe<HelloReply>() {
            @Override
            public void subscribe(ObservableEmitter<HelloReply> e) throws Exception {
                HelloReply reply = mStub.sayHello(request);
                e.onNext(reply);
                e.onComplete();
            }
        });
        return RxUtils.on(observable);
    }
}
