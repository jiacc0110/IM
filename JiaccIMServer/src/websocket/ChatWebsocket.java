package websocket;

import java.io.IOException;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import bean.Chat;
import utils.Constant;


@ServerEndpoint(value="/websocket/chat")
public class ChatWebsocket {

	@OnOpen
	public void onOpen(Session session,EndpointConfig config){
		session.getAsyncRemote().sendText("{'msgtype':'open'}");
	}

	@OnClose
	public void onClose(){
		System.out.println("onClose");
	}

	@OnMessage
	public void onMessage(String message,Session session){
		System.out.println("message:"+message);
		System.out.println("session:"+session.getId());
		Chat chat=new Chat(message);
		if("connect".equals(chat.msgtype)){
			OnlineUserManager.getOnlineUserManager()
			.putOnlineSession(parseMessage(message,Constant.CHAT_KEY_FROM), session);
			try {
				Chat retchat=new Chat();
				retchat.msg=chat.msg;
				retchat.frm=chat.frm;
				retchat.sendto=chat.sendto;
				retchat.messageId=chat.messageId;
				retchat.msgtype="connect_ret";
				session.getBasicRemote().sendText(retchat.toJsonString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if("chat".equals(chat.msgtype)){
			
			Session sendtoSession=OnlineUserManager
					.getOnlineUserManager()
					.getOnlineSession(chat.sendto);
			if(sendtoSession!=null){//判断是否在线
				try {
					System.out.println("before send to");
					sendtoSession.getBasicRemote().sendText(chat.toJsonString());
					System.out.println("after send to"+chat.toString());
				} catch (IOException e) {
					e.printStackTrace();
				};
			}else{
				//将数据存储到数据库中，当接者用户上线后，同步消息并从数据库中删除。
				System.out.println("不在线！！");
			}
		}
	}

	public String parseMessage(String message,String key){
		try {
			System.out.println("message="+message+"key="+key);
			JSONObject obj=new JSONObject(message);
			return obj.get(key).toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
