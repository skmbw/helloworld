package com.fuuzii.grpc;

import android.os.AsyncTask;

import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @author yinlei
 * @since 2017/8/3 14:51
 */
public abstract class GenericGrpcTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
    private String mHost;
    private int mPort;
    protected ManagedChannel mChannel;

    public GenericGrpcTask() {
        mHost = "10.70.7.244";
        mPort = 50015;
    }

    public GenericGrpcTask(String host, int port) {
        mHost = host;
        mPort = port;
    }

    @Override
    protected void onPreExecute() {
        preExecute();
    }

    /**
     * AsyncTask.onPreExecute
     */
    public abstract void preExecute();

    @Override
    protected Result doInBackground(Params... params) {
        mChannel = ManagedChannelBuilder.forAddress(mHost, mPort)
                .usePlaintext(true)
                .build();
        return background(params);
    }

    /**
     * AsyncTask.doInBackground
     * @param params
     * @return
     */
    public abstract Result background(Params... params);

    @Override
    protected void onPostExecute(Result result) {
        try {
            mChannel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        postExecute(result);
    }

    /**
     * AsyncTask.onPostExecute
     * @param result
     */
    public abstract void postExecute(Result result);


}
