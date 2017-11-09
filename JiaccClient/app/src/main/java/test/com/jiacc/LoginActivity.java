package test.com.jiacc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import netutils.NetUtils;

public class LoginActivity extends AppCompatActivity {
    private Button bt;
    public static Handler handler;
    private TextView tv;
    private String result;
    private EditText et_loginname,et_password,et_sendto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bt=(Button)findViewById(R.id.bt_login);
        tv=(TextView)findViewById(R.id.tv);
        et_loginname=(EditText)findViewById(R.id.et_loginname);
        et_password=(EditText)findViewById(R.id.et_password);
        et_sendto=(EditText)findViewById(R.id.et_sendto);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                tv.setText(result);
                if(msg.what==0){
                    startService(new Intent(LoginActivity.this,ChatService.class));
                }else if(msg.what==100){
                    try {
                        String json=msg.getData().getString("result");
                        JSONObject obj=new JSONObject(json);
                        int retCode=obj.getInt("retCode");
                        String m=obj.getString("msg");
                        switch (retCode){
                            case 1:
                                tv.setText(m);
                                AccountManager.getInstance().setUserName(m);
                                Intent intent=new Intent(LoginActivity.this,ChatActivity.class);
                                intent.putExtra("friendname",et_sendto.getText().toString());
                                ChatManager.getInstance().initSocket();
                                startActivity(intent);
                                break;

                            default:
                                tv.setText(m);
                                break;
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_login:
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            result= NetUtils.requestUrl("http://192.168.18.180:8080/JiaccServer/loginservlet?method=login&username="
                                    +et_loginname.getText().toString()
                                    +"&password="
                                    +et_password.getText().toString());
                            Log.i("jcc",result);
                            AccountManager.getInstance()
                                    .setUserName(et_loginname.getText().toString());
                            Message msg=new Message();
                            Bundle b=new Bundle();
                            b.putString("result",result);
                            msg.setData(b);
                            msg.what=100;
                            handler.sendMessage(msg);
                        }catch (Exception e){
                            result=e.getMessage();
                            handler.sendEmptyMessage(new Message().what=1);
                        }
                    }
                }.start();
                break;
            case R.id.bt_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
        }
    }
}
