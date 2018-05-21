package io.grpc.helloworldexample;

import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;

/**
 * @author yinlei
 * @since 2018/5/21 10:12
 */
public class HelloWorldPresenter extends DefaultPresenter<HelloRequest, HelloReply> {
    private HelloWorldView mHelloWorldView;
    private HelloWorldModel mHelloWorldModel;

    public HelloWorldPresenter(HelloWorldView helloWorldView, HelloWorldModel helloWorldModel) {
        super(helloWorldView);
        mHelloWorldView = helloWorldView;
        mHelloWorldModel = helloWorldModel;
    }

    @Override
    public void getData(boolean refresh) {
//        mHelloWorldModel.sayHello(null);
    }

    @Override
    public void list(HelloRequest model) {
        doList(mHelloWorldModel.sayHello(model));
    }

    @Override
    public void get(HelloRequest model) {
//        mHelloWorldModel.get(model);
    }
}
