package netutils;

import org.json.JSONObject;

/**
 * Created by jiacc on 2017/11/9.
 */

public class JsonUtils {
    public static String getString(String json,String key){
        try{
            JSONObject obj=new JSONObject(json);
            return obj.getString(key);
        }catch (Exception e){

            return null;
        }
    }

    public static int getInt(String json,String key){
        try{
            JSONObject obj=new JSONObject(json);
            return obj.getInt(key);
        }catch (Exception e){
            return -1;
        }
    }

}
