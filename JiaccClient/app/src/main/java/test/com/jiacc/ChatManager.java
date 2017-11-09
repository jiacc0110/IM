package test.com.jiacc;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import bean.Chat;
import netutils.Constant;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * Created by jiacc on 2017/11/6.
 */

public class ChatManager {
    private static ChatManager instance;
    public static ChatManager getInstance(){
        if(instance==null){
            instance=new ChatManager();
        }
        return instance;
    }
    WebSocket socket;
    public void initSocket(){
        new Thread(new InitTask()).start();
    }
    class InitTask implements Runnable {
        @Override
        public void run() {
            if (socket == null) {
                OkHttpClient client = new OkHttpClient.Builder()
                        .writeTimeout(1000, TimeUnit.SECONDS)
                        .readTimeout(1000, TimeUnit.SECONDS)
                        .connectTimeout(1000, TimeUnit.SECONDS)
                        .build();
                Request request = new Request.Builder()
                        .url(Constant.WEBSOCKET_URL)
                        .build();
                socket = client.newWebSocket(request, new WSListener());
            }
        }
    }
    class WSListener extends WebSocketListener {
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            if(response.code()==101){
                synchronized (ChatManager.class) {
                    Chat chat = new Chat();
                    chat.frm = AccountManager.getInstance().getUserName();
                    chat.msgtype = "connect";
                    socket.send(chat.toJsonString());
                }
            }
        }
        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
            handleMessage(new Chat(text));
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
            reConnect();
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            reConnect();
        }
    }
    private void handleMessage(final Chat chat){

        if("connect_ret".equals(chat.msgtype)){
            //连接成功
            Log.i("jcc","message:"+chat.messageId);
        }else if("chat".equals(chat.msgtype)){
            //加入到列表
            Log.i("jcc","chat:"+chat.msg);
            observable.notifyObservers(chat);
        }
    }
    public void reConnect(){
        OkHttpClient client = new OkHttpClient.Builder()
                .writeTimeout(1000, TimeUnit.SECONDS)
                .readTimeout(1000,TimeUnit.SECONDS)
                .connectTimeout(1000,TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(Constant.WEBSOCKET_URL)
                .build();
        socket = client.newWebSocket(request, new WSListener());
    }

    public void send(String text){
        socket.send(text);
    }
    public void onClose(){
        socket.close(1001,"onstop");
    }
    Observable observable=new Observable();
    public void addObserver(Observer observer){
        observable.addObserver(observer);
    }
}
