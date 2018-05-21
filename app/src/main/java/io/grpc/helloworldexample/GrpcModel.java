package io.grpc.helloworldexample;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

/**
 *
 *
 * @author yinlei
 * @since 2018/5/21 10:19
 */
public class GrpcModel {
    protected Channel mChannel;

    public GrpcModel() {
        mChannel = ManagedChannelBuilder.forAddress("127.0.0.1", 50051)
                .usePlaintext(true)
                .build();
    }

    public GrpcModel(Channel channel) {
        mChannel = channel;
    }
}
