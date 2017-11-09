package netutils;

/**
 * Created by jiacc on 2017/10/25.
 */

public class StringUtils {
    public static boolean isNullOrEmpty(String str){
        if(str==null)return true;
        if(str.equals("")) return true;
        return false;
    }
}
