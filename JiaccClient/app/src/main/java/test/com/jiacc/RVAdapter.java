package test.com.jiacc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import bean.Chat;

/**
 * Created by jiacc on 2017/10/27.
 */

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<Chat> chats;
    public RVAdapter(Context context, List<Chat> chats){
        this.context=context;
        this.chats=chats;
    }
    @Override
    public int getItemViewType(int position) {
        return AccountManager
                .getInstance()
                .getUserName()
                .equals(chats.get(position).frm)?
                0:1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        RecyclerView.ViewHolder holder=null;
        if(viewType==0) {
            view = View.inflate(context, R.layout.chat_item_right, null);
            holder=new RightHolder(view);
        }else if(viewType==1){
            view=View.inflate(context,R.layout.chat_item_left,null);
            holder=new LeftHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof RightHolder) {
            long t=0;
            try{
                t=Long.valueOf(chats.get(position).messageId);
            }catch (Exception e){
            }
            Long.valueOf(chats.get(position).messageId);
            ((RightHolder) holder).tv.setText(chats.get(position).msg + " "
                    + new SimpleDateFormat("hh:mm:ss").format(
                    new Date(t)));
        }
        else if(holder instanceof LeftHolder)
            ((LeftHolder)holder).tv.setText(chats.get(position).msg);
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    class RightHolder extends RecyclerView.ViewHolder{
        public TextView tv;
        public RightHolder(View itemView) {
            super(itemView);
            tv=(TextView)itemView.findViewById(R.id.tv_chatcontent);
        }
    }
    class LeftHolder extends RecyclerView.ViewHolder{
        public TextView tv;
        public LeftHolder(View itemView){
            super(itemView);
            tv=(TextView)itemView.findViewById(R.id.tv_chatcontent);
        }
    }
}
