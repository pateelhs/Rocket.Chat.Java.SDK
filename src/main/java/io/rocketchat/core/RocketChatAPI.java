package io.rocketchat.core;
import io.rocketchat.common.data.rpc.RPC;
import io.rocketchat.common.network.Socket;
import io.rocketchat.core.rpc.BasicRPC;
import io.rocketchat.livechat.callback.ConnectListener;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sachin on 8/6/17.
 */
public class RocketChatAPI extends Socket {

    AtomicInteger integer;
    String sessionId;

    ConnectListener connectListener;


    public RocketChatAPI(String url) {
        super(url);
        integer=new AtomicInteger(1);
    }


    public void setConnectListener(ConnectListener connectListener) {
        this.connectListener = connectListener;
    }

    public void connect(ConnectListener connectListener){
        createSocket();
        this.connectListener = connectListener;
        super.connectAsync();
    }

    @Override
    protected void onConnected() {
        integer.set(1);
        sendDataInBackground(BasicRPC.ConnectObject());
        super.onConnected();
    }

    @Override
    protected void onTextMessage(String text) throws Exception {
        JSONObject object = new JSONObject(text);
        switch (RPC.parse(object.optString("msg"))) {
            case PING:
                sendDataInBackground("{\"msg\":\"pong\"}");
                break;
            case CONNECTED:
                sessionId = object.optString("session");
                if (connectListener != null) {
                    connectListener.onConnect(sessionId);
                }
                break;
            case ADDED:
                break;
            case RESULT:
                break;
            case READY:
                break;
            case CHANGED:
                break;
            case OTHER:
                break;
        }

        super.onTextMessage(text);
    }

    @Override
    protected void onConnectError(Exception websocketException) {
        if (connectListener!=null) {
            connectListener.onConnectError(websocketException);
        }
        super.onConnectError(websocketException);
    }

    @Override
    protected void onDisconnected(boolean closedByServer) {
        if (connectListener!=null) {
            connectListener.onDisconnect(closedByServer);
        }
        super.onDisconnected(closedByServer);
    }
}
