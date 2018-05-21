/*
 * Copyright 2015, gRPC Authors All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.grpc.helloworldexample;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fuuzii.grpc.GenericGrpcTask;

import java.io.PrintWriter;
import java.io.StringWriter;

import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;

public class HelloworldActivity extends BasicActivity<HelloReply> implements HelloWorldView {
    private static final String TAG = "HelloworldActivity";

    private Button mSendButton;
    private EditText mHostEdit;
    private EditText mPortEdit;
    private EditText mMessageEdit;
    private TextView mResultText;

    private HelloWorldPresenter mHelloWorldPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        HelloWorldModel helloWorldModel = new HelloWorldModel();
        mHelloWorldPresenter = new HelloWorldPresenter(this, helloWorldModel);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helloworld);
        // 这个button没有绑定单击事件，实在布局文件中绑定的android:onClick="sendMessage"
        mSendButton = (Button) findViewById(R.id.send_button);
        mHostEdit = (EditText) findViewById(R.id.host_edit_text);
        mPortEdit = (EditText) findViewById(R.id.port_edit_text);
        mMessageEdit = (EditText) findViewById(R.id.message_edit_text);
        mResultText = (TextView) findViewById(R.id.grpc_response_text);
        mResultText.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    protected void initView() {

    }

    @Override
    public int layoutResId() {
        return 0;
    }

    // 布局文件中绑定的button的响应事件
    public void sendMessage(View view) {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(mHostEdit.getWindowToken(), 0);
        mSendButton.setEnabled(false);
//        new GrpcTask().execute();
        HelloRequest.Builder builder = HelloRequest.newBuilder()
                .setName("尹雷啊123");
        mHelloWorldPresenter.list(builder.build());
    }

    @Override
    public void finishRefresh() {

    }

    private class GrpcTask extends GenericGrpcTask<Void, Void, String> {
        private String mMessage;

        @Override
        public void preExecute() {
//            mHost = mHostEdit.getText().toString();
            mMessage = mMessageEdit.getText().toString();
//            String portStr = mPortEdit.getText().toString();
//            mPort = TextUtils.isEmpty(portStr) ? 0 : Integer.valueOf(portStr);
            mResultText.setText("");
        }

        @Override
        public String background(Void... nothing) {
            try {
                GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(mChannel);
                HelloRequest message = HelloRequest.newBuilder().setName(mMessage).build();
                HelloReply reply = stub.sayHello(message);
                return reply.getMessage();
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                pw.flush();
                return String.format("Failed... : %n%s", sw);
            }
        }

        @Override
        public void postExecute(String result) {
            mResultText.setText(result);
            mSendButton.setEnabled(true);
        }
    }

    @Override
    public void moreData(HelloReply bean) {
        Log.d(TAG, bean.getMessage());
        mResultText.setText(bean.getMessage());
        mSendButton.setEnabled(true);

    }
}
