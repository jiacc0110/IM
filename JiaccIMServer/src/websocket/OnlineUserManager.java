package websocket;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

public class OnlineUserManager {
	private Map<String,Session> onlines=new HashMap<String,Session>();
	private static OnlineUserManager instance;
	public static OnlineUserManager getOnlineUserManager(){
	 if(instance==null){
		 instance=new OnlineUserManager();
	 }
	 return instance;
	}
	
	public void putOnlineSession(String username,Session session){
		onlines.put(username, session);
		System.out.println("session size:"+onlines.size());
	}
	
	public Session getOnlineSession(String username){
		System.out.println("getOnlineSession:"+username);
		return onlines.get(username);
	}
}
