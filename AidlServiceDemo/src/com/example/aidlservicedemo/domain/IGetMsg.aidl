package com.example.aidlservicedemo.domain;

// 这是两个自定义类
import com.example.aidlservicedemo.domain.Message;
import com.example.aidlservicedemo.domain.User;

interface IGetMsg{
	// 在AIDL接口中定义一个getMes方法
	List<Message> getMes(in User us);
}