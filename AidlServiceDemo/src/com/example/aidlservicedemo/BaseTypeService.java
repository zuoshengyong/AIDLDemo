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
	private String[] names=new String[]{"С��","����","С��"};
	private int[] ages=new int[]{1,2,3};
	
	/**
	 * Stub��ʵ���࣬Stub�ڲ�ʵ����Binder
	 * �ڲ�ʵ��AIDL����ķ���
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
		// ʵ����Binder����
		binder=new DogBinder();
		Log.i(TAG, "��������ɹ�");
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, "�󶨷���ɹ�");
		// ����Binder����
		return binder;
	}
}
