package com.example.aidlservicedemo.domain;

// ���������Զ�����
import com.example.aidlservicedemo.domain.Message;
import com.example.aidlservicedemo.domain.User;

interface IGetMsg{
	// ��AIDL�ӿ��ж���һ��getMes����
	List<Message> getMes(in User us);
}