package netutils;

import java.io.IOException;
import java.net.CookieManager;
import java.util.Iterator;
import java.util.Set;

import okhttp3.Cookie;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jiacc on 2017/10/25.
 */

public class NetUtils {

    public static String requestUrl(String url) throws IOException{

        OkHttpClient client=new OkHttpClient.Builder().build();
        Request request=new Request.Builder().url(url).build();
        Response response=client.newCall(request).execute();
        if(response.isSuccessful()){
            Headers headers=response.headers();
            Set<String> sets= headers.names();
            StringBuilder sb=new StringBuilder();
            sb.append(response.body().string());
            return sb.toString();
        }else{
            return "connected fail";
        }
    }
}
