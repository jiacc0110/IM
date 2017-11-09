package netutils;

import java.io.IOException;
import java.net.CookieManager;
import java.util.Iterator;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * Created by jiacc on 2017/10/25.
 */

public class NetUtils {
    public static void requestUrl(String url,final NetCallback cb) {

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
}
