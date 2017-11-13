package test.com.jiacc;

/**
 * Created by jiacc on 2017/11/2.
 * 登录账号管理类，用于将当前登录账号储存到内存中
 */

public class AccountManager {
    public static AccountManager instance;
    private AccountManager(){}
    public static AccountManager getInstance(){
        if(instance==null){
            synchronized (AccountManager.class){
                if(instance==null){
                    instance=new AccountManager();
                }
            }
        }
        return instance;
    }
    private String username;
    public String getUserName(){
        return username;
    }
    public void setUserName(String username){
        this.username=username;
    }
}
