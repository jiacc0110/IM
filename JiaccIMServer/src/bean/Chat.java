package bean;

import org.json.JSONException;
import org.json.JSONObject;

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
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	public String messageId="";
	public String msgtype="";
	public String frm="";
	public String sendto="";
	public String msg="";
	public String extra="";
	public String tim="";
	public String toJsonString(){
		try {
			JSONObject obj=new JSONObject();
			obj.put("messageId", messageId);
			obj.put("msgtype", msgtype);
			obj.put("frm", frm);
			obj.put("sendto", sendto);
			obj.put("msg", msg);
			obj.put("extra", extra);
			obj.put("tim", tim);
			return obj.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
