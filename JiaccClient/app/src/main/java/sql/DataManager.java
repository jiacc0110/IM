package sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import bean.Chat;
import test.com.jiacc.App;

/**
 * Created by jiacc on 2017/10/31.
 * 数据库工具类
 */

public class DataManager {
    public static SQLiteDatabase database;
    public DataManager(Context context){
        SqliteHelper helper=new SqliteHelper(context,"chat",1);
        if(database==null) {
            database = helper.getReadableDatabase();
        }
    }
    public static DataManager instance;
    public static DataManager getInstance(){
        if(instance==null){
            instance=new DataManager(App.app);
        }
        return instance;
    }

    /**
     *
     * @param chat 将Chat实体插入到本地数据库
     */
    public synchronized void insert(final Chat chat){
        new Thread(){
            @Override
            public void run() {
                String sql="insert into chat (msgtype,messageId,msg,frm,sendto,extra,tim) values(?,?,?,?,?,?,?)";
                database.execSQL(sql,new Object[]{
                        chat.msgtype,chat.messageId,chat.msg,chat.frm,chat.sendto,chat.extra,chat.tim
                });
            }
        }.start();

    }

    /**
     * 读取对话双方的消息
     * @param from
     * @param sendto
     * @return
     */
    public List<Chat> getList(String from,String sendto){
        String sql="select msgtype,messageId,msg,frm,sendto,extra,tim from chat where frm=? and sendto=? or frm=? and sendto=?";
        Cursor cursor = database.rawQuery(sql, new String[]{
                from,sendto,sendto,from
        });
        List<Chat> chats=new ArrayList<Chat>();
        while(cursor.moveToNext()){
            Chat c=new Chat();
            c.msgtype=cursor.getString(0);
            c.messageId=cursor.getString(1);
            c.msg=cursor.getString(2);
            c.frm=cursor.getString(3);
            c.sendto=cursor.getString(4);
            c.extra=cursor.getString(5);
            c.tim=cursor.getString(6);
            chats.add(c);
        }
        return chats;
    }

}
