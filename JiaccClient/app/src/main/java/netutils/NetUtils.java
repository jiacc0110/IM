package netutils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import test.com.jiacc.App;

/**
 * Created by jiacc on 2017/10/25.
 * 网络请求工具类
 */

public class NetUtils {
    //发起Http请求
    public static void requestUrl(String url,final NetCallback cb) {
        if(!isConnected()) {
            cb.onFailure("网络断开");
            return;
        }
        OkHttpClient client=new OkHttpClient.Builder().build();
        Request request=new Request.Builder().url(url).build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                cb.onFailure("failed");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                cb.onSuccess(response.body().string());
            }
        });
    }
    public interface NetCallback{
        void onFailure(String msg);
        void onSuccess(String data);
    }

    //检查网络状态是否连接
    public static boolean isConnected(){
        ConnectivityManager cm=
                (ConnectivityManager) App.app.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=cm.getActiveNetworkInfo();
        return activeNetwork!=null&&activeNetwork.isConnected();
    }
}
