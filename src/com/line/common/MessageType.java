package com.line.common;

/*
 * 定義各種mesType
 * */

public interface MessageType {
	String messageSucceed 	= "1";//表明登入成功
	String messageLoginFail = "2";//表明登入失敗
	String messageNormalMsg = "3";//表明普通訊息
	String messageGetOnlineFriend = "4";//表明要求取得在線好友列表
	String messageReturnOnlineFriend = "5";//返回在線好友
}
