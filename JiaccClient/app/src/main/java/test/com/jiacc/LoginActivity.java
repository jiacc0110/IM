package test.com.jiacc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import netutils.Constant;
import netutils.JsonUtils;
import netutils.NetUtils;
import netutils.StringUtils;

public class LoginActivity extends AppCompatActivity {
    private Button bt;
    private TextView tv;
    private String result;
    private EditText et_loginname,et_password,et_sendto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bt = (Button) findViewById(R.id.bt_login);
        tv = (TextView) findViewById(R.id.tv);
        et_loginname = (EditText) findViewById(R.id.et_loginname);
        et_password = (EditText) findViewById(R.id.et_password);
        et_sendto = (EditText) findViewById(R.id.et_sendto);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_login:
                String url=Constant.HTTP_URL
                        + "/loginservlet?method=login&username="
                        + et_loginname.getText().toString()
                        + "&password="
                        + et_password.getText().toString();
                 Log.i("jcc","loginurl"+url);
                 NetUtils.requestUrl(url, new NetUtils.NetCallback() {
                     @Override
                     public void onSuccess(final String data) {
                        Log.i("jcc","requestUrl--onSuccess--data=="+data);
                         runOnUiThread(new Runnable() {
                             @Override
                             public void run() {
                                 tv.setText(data);
                                 String username=JsonUtils.getString(data,"msg");
                                 if(!StringUtils.isNullOrEmpty(username)){
                                     AccountManager.getInstance().setUserName(username);
                                     ChatManager.getInstance().initSocket();
                                     Intent intent =new Intent(LoginActivity.this,ChatActivity.class);
                                     intent.putExtra("friendname",et_sendto.getText().toString());
                                     startActivity(intent);
                                 }
                             }
                         });
                     }
                     @Override
                     public void onFailure(String msg) {
                     }
                 });
                break;
            case R.id.bt_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
        }
    }
}
