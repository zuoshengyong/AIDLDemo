package com.example.aidlservicedemo;

import java.util.Random;

import com.example.aidlservicedemo.domain.IDog.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class BaseTypeService extends Service {
	private final String TAG="main";
	private DogBinder binder=null;
	private String[] names=new String[]{"小白","旺财","小黑"};
	private int[] ages=new int[]{1,2,3};
	
	/**
	 * Stub的实现类，Stub内部实现了Binder
	 * 内部实现AIDL定义的方法
	 */
	public class DogBinder extends Stub{

		@Override
		public String getName() throws RemoteException {
			Random random=new Random();
			int nextInt = random.nextInt(2);			
			return names[nextInt];
		}

		@Override
		public int getAge() throws RemoteException {
			Random random=new Random();
			int nextInt = random.nextInt(2);			
			return ages[nextInt];
		}		
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		// 实例化Binder对象
		binder=new DogBinder();
		Log.i(TAG, "创建服务成功");
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, "绑定服务成功");
		// 返回Binder对象
		return binder;
	}
}
