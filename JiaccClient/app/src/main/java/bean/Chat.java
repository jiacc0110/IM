package bean;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jiacc on 2017/10/27.
 */
public class Chat {
    public Chat(){
    }
    public Chat(String message){
        try {
            JSONObject json=new JSONObject(message);
            this.messageId=json.getString("messageId");
            this.msgtype=json.getString("msgtype");
            this.msg=json.getString("msg");
            this.frm=json.getString("frm");
            this.sendto=json.getString("sendto");
            this.extra=json.getString("extra");
            this.tim=json.getString("tim");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String msgtype="";
    public String messageId="";
    public String msg="";
    public String frm="";
    public String sendto="";
    public String tim="";
    public String extra="";

    public String toJsonString(){
        try{
            JSONObject obj=new JSONObject();
            obj.put("msgtype",msgtype);
            obj.put("messageId",messageId);
            obj.put("msg",msg);
            obj.put("frm",frm);
            obj.put("sendto",sendto);
            obj.put("extra",extra);
            obj.put("tim",tim);
            return obj.toString();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("jcc","chat parse error");
        }
        return null;
    }
}
