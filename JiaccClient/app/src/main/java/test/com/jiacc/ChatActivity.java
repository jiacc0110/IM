package test.com.jiacc;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import bean.Chat;
import sql.DataManager;

/**
 * Created by jiacc on 2017/10/27.
 */
public class ChatActivity extends Activity{
    RecyclerView rv;
    EditText et_msg;
    List<Chat> chats;
    RVAdapter adapter;
    String friendName;
    private DataManager dataManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        dataManager=DataManager.getInstance();
        friendName= getIntent().getStringExtra("friendname");
        initViews();
        ChatManager.getInstance().addObserver(chatObserver);
    }
    private Observer chatObserver= new Observer(){
        @Override
        public void update(Observable o,final Object chat) {
            Log.i("jcc","ChatActivity===Observer update");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(chat instanceof Chat){
                        chats.add((Chat)chat);
                        adapter.notifyDataSetChanged();
                        rv.scrollToPosition(adapter.getItemCount()-1);
                    }
                }
            });

        }
    };
    private void initViews(){
        rv=(RecyclerView)findViewById(R.id.rv);
        et_msg=(EditText)findViewById(R.id.et_msg);
        rv.setLayoutManager(new LinearLayoutManager(this));
        chats=dataManager.getList(AccountManager.getInstance().getUserName(),friendName);
        adapter=new RVAdapter(this,chats);
        rv.setAdapter(adapter);
        rv.scrollToPosition(adapter.getItemCount()-1);
    }
    public void onClick(View view){
        Chat chat=new Chat();
        chat.msgtype="chat";
        chat.msg=et_msg.getText().toString();
        chat.frm=AccountManager.getInstance().getUserName();
        chat.sendto=friendName;
        chat.messageId=System.currentTimeMillis()+"";
        chats.add(chat);
        dataManager.insert(chat);
        adapter.notifyDataSetChanged();
        rv.scrollToPosition(adapter.getItemCount()-1);
        ChatManager.getInstance().send(chat.toJsonString());
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onStop() {
        ChatManager.getInstance().onClose();
        ChatManager.getInstance().removeObserver(chatObserver);
        super.onStop();
    }
}
