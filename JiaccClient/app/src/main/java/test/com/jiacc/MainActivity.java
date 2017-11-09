package test.com.jiacc;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {
    FragmentTabHost tabhost;
    private int images[]={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher_round};
    private String mFragmentTags[]={"a","b","c","d"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabhost=(FragmentTabHost)findViewById(android.R.id.tabhost);
        tabhost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);
        tabhost.getTabWidget().setDividerDrawable(null);

        TabHost.TabSpec tabSpec=tabhost.newTabSpec(mFragmentTags[0]).setIndicator(getImageView(0));
        tabhost.addTab(tabSpec,FragmentTab.class,null);
        TabHost.TabSpec tabSpec2=tabhost.newTabSpec(mFragmentTags[1]).setIndicator(getImageView(1));
        tabhost.addTab(tabSpec2,FragmentTab.class,null);
        TabHost.TabSpec tabSpec3=tabhost.newTabSpec(mFragmentTags[2]).setIndicator(getImageView(2));
        tabhost.addTab(tabSpec3,FragmentTab.class,null);
        TabHost.TabSpec tabSpec4=tabhost.newTabSpec(mFragmentTags[3]).setIndicator(getImageView(3));
        tabhost.addTab(tabSpec4,FragmentTab.class,null);

    }
    private View getImageView(int index){
        View view=getLayoutInflater().inflate(R.layout.indicator,null);
        ImageView iv=(ImageView)view.findViewById(R.id.iv);
        iv.setImageResource(images[index]);
        return view;
    }
}
