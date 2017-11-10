# IM 服务端+客户端

一个使用Tomcat +Okhttp 作为Websocket的服务端和客户端框架，写成的一个聊天项目的雏形。</br></br>

## 1.框架的选择
    即时通讯必须是长连接，我们完全可以应用Socket从零开始实现客户端和服务端的逻辑。</br>
    而此处选择了使用WebSocket协议，WebSocket是http协议为了满足长连接，代替过去Web开发中的轮询、提高性能而出现的协议。</br>
    服务端使用Tomcat提供的WebSocket框架，客户端使用的Okhttp提供的WebSocket实现。使用成熟的框架，免去了部分底层维护系统健壮性的工作，尤其是服务端可以通过Tomcat强大的功能避免了一次错误请求到这服务崩溃的尴尬。这为我这个主做App开发的来说节省精力用在App的开发上。
## 2.架构设计
</br>
ClientSocket----------request---------------->ServerSocket</br>
ClientSocket<-------onOpen ack_code-----------ServerSocket</br>
ClientSocket-------onMessage connect--------->ServerSocket</br>
ClientSocket<-----onMessage connect_ret-------ServerSocket</br>
ClientSOcket-----onMessage msg_XXXXX msg_id-->ServerSocket</br>
CLientSocket<---onMessage msg_ok_ret msg_id---ServerSocket</br>
 
## 3.目前实现功能
到2017年11月9日,目前完成功能有：</br></br>
1.客户端和服务端的账号注册逻辑；</br></br>
 2.客户端和服务端的登录逻辑；</br></br>
3.客户端和服务端的私聊功能；</br></br>
 聊天页面如图：</br></br>
![image](phone1.png) 
![iamge](phone2.png)

