package test.com.jiacc;

import android.app.Application;

/**
 * Created by jiacc on 2017/11/9.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        this.app=this;
    }
    public static Application app;
}
