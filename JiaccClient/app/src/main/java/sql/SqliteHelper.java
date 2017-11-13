package sql;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jiacc on 2017/10/31.
 * 本地数据库的创建和升级辅助类
 */

public class SqliteHelper extends SQLiteOpenHelper{

    public SqliteHelper(Context context,String name,int version){
    this(context,name,null,version);
    }
    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usr(id integer,name varchar(20))");
        db.execSQL("create table chat(id integer primary key autoincrement," +
                "msgtype varchar(50),messageId varchar(50)," +
                "msg varchar(200),frm varchar(50)," +
                "sendto varchar(50),extra varchar(200)," +
                "tim integer" +
                ");");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
