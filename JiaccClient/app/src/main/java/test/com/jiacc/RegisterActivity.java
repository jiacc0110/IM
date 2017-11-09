package test.com.jiacc;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import netutils.Constant;
import netutils.NetUtils;
import netutils.StringUtils;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_loginname,et_password;
    String result;
    Handler handler;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_loginname=(EditText)findViewById(R.id.et_loginname);
        et_password=(EditText)findViewById(R.id.et_password);
        tv=(TextView)findViewById(R.id.tv);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                tv.setText(result);
            }
        };
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_checkName:

                break;
            case R.id.bt_register:
                if(StringUtils.isNullOrEmpty(et_loginname.getText().toString())){
                    return;
                }
                if(StringUtils.isNullOrEmpty(et_password.getText().toString())){
                    return;
                }
               final String url= Constant.HTTP_URL+Constant.HOST_APP+"/loginservlet?method=register" +
                        "&username="+et_loginname.getText().toString()+
                        "&password="+et_password.getText().toString();
                        Log.i("jcc",url);
                        NetUtils.requestUrl(url, new NetUtils.NetCallback() {
                            @Override
                            public void onSuccess(final String data) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv.setText(data);
                                    }
                                });
                            }
                            @Override
                            public void onFailure(String msg) {

                            }
                        });
                break;
        }
    }
}
