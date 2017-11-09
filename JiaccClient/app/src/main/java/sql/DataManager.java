package sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import bean.Chat;

/**
 * Created by jiacc on 2017/10/31.
 */

public class DataManager {
    public static SQLiteDatabase database;
    public DataManager(Context context){
        SqliteHelper helper=new SqliteHelper(context,"chat",1);
        if(database==null) {
            database = helper.getReadableDatabase();
        }
    }


    public void insert(Chat chat){
        String sql="insert into chat (msgtype,messageId,msg,frm,sendto,extra,tim) values(?,?,?,?,?,?,?)";
        database.execSQL(sql,new Object[]{
                chat.msgtype,chat.messageId,chat.msg,chat.frm,chat.sendto,chat.extra,chat.tim
        });
        database.beginTransaction();
    }

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
