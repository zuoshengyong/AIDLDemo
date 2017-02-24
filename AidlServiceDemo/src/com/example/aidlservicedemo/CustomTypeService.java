package com.example.aidlservicedemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.aidlservicedemo.domain.IGetMsg.Stub;
import com.example.aidlservicedemo.domain.Message;
import com.example.aidlservicedemo.domain.User;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class CustomTypeService extends Service {
	private static final String TAG = "main";
	private MsgBinder msgBinder=null;
	private static Map<User, List<Message>> map=new HashMap<User, List<Message>>();
	static{
		for(int i=0;i<3;i++){
			User user=new User(i, "jack"+i, "9999999999"+i);
			List<Message> messages=new ArrayList<Message>();
			Message msg=null;
			if(i==0)
			{
				msg=new Message(i, "这两天好吗？", "Jerry", new Date().toGMTString());
				messages.add(msg);
			}else if(i==1)
			{
				msg=new Message(i, "周天去逛街吧！", "Tim", new Date().toGMTString());
				messages.add(msg);
				msg=new Message(i, "好无聊！", "Wesley", new Date().toGMTString());
				messages.add(msg);
			}
			else
			{
				msg=new Message(i, "上次的问题解决了吗？", "Bonnie", new Date().toGMTString());
				messages.add(msg);
				msg=new Message(i, "明天一起吃饭吧？", "Matt", new Date().toGMTString());
				messages.add(msg);
				msg=new Message(i, "在哪里？", "Caroline", new Date().toGMTString());
				messages.add(msg);
			}
			map.put(user, messages);
		}
	}
	
	public class MsgBinder extends Stub{

		@Override
		public List<Message> getMes(User us) throws RemoteException {			
			for(Map.Entry<User, List<Message>> msgs:map.entrySet()){
				if(msgs.getKey().getUsername().equals(us.getUsername())&&msgs.getKey().getPassword().equals(us.getPassword())){
					Log.i(TAG, "找到信息了");
					return msgs.getValue();
				}
			}
			Log.i(TAG, "没找到信息了");
			return map.get(us);
		}
		
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return msgBinder;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		msgBinder=new MsgBinder();
	}

	@Override
	public void onDestroy() {
		msgBinder=null;
		super.onDestroy();
	}

}
