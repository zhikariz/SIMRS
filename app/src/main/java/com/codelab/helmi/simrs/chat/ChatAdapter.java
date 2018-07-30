package com.codelab.helmi.simrs.chat;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codelab.helmi.simrs.R;
import com.codelab.helmi.simrs.api.SharedPrefManager;

import java.util.List;


public class ChatAdapter extends BaseAdapter {
	private List<ChatMessage> mMessages;
	private Context mContext;
	private SharedPrefManager set;

	public ChatAdapter(Context context, List<ChatMessage> messages) {
		super();
		this.mContext = context;
		this.mMessages = messages;
	}

	@Override
	public int getCount() {
		return mMessages.size();
	}

	@Override
	public Object getItem(int position) {
		return mMessages.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ChatMessage msg = (ChatMessage) getItem(position);
		set=new SharedPrefManager(mContext);

		ViewHolder holder;
		if(convertView == null){
			holder 				= new ViewHolder();
			convertView			= LayoutInflater.from(mContext).inflate(R.layout.list_chat_details, parent, false);
			holder.time 		= convertView.findViewById(R.id.text_time);
			holder.message 		= convertView.findViewById(R.id.text_content);
			holder.lyt_thread 	= convertView.findViewById(R.id.lyt_thread);
			holder.lyt_parent 	= convertView.findViewById(R.id.lyt_parent);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}

		holder.message.setText(msg.getText());
		holder.time.setText(msg.getReadableTime());

		if(msg.getReceiver().getNo_rm().equals(set.getSpNoRm())){
			holder.lyt_parent.setPadding(15, 0, 100, 0);
			holder.lyt_parent.setGravity(Gravity.LEFT);
			holder.lyt_thread.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
			//holder.image_status.setImageResource(android.R.color.transparent);
		}else{
			holder.lyt_parent.setPadding(100, 0, 15, 0);
			holder.lyt_parent.setGravity(Gravity.RIGHT);
			holder.lyt_thread.setCardBackgroundColor(Color.parseColor("#e1ffc7"));
		}
		return convertView;
	}

	public void add(ChatMessage msg){
		mMessages.add(msg);
	}

	private static class ViewHolder{
		TextView time;
		TextView message;
		LinearLayout lyt_parent;
		CardView lyt_thread;
	}
}
