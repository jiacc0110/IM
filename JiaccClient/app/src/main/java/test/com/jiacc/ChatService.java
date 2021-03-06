package test.com.jiacc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by jiacc on 2017/11/6.
 * 聊天服务，所有的聊天内容在Service中处理，并提供发布者供需要的页面进行订阅
 */

public class ChatService extends Service{

    @Override
    public void onCreate() {
        super.onCreate();
        ChatManager.getInstance().initSocket();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
