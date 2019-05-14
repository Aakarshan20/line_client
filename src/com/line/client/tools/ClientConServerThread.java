/*
 * 客戶端與server端保持通訊的thread
 * */

package com.line.client.tools;
import com.line.client.view.*;
import java.net.*;
import java.io.*;
import com.line.common.*;
public class ClientConServerThread extends Thread{
	private Socket s = null;
	
	
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public ClientConServerThread(Socket s) {
		this.s = s;
	}
	
	public void run() {
		while(true) {
			//不停讀取從server發來的消息
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				
				//此處要判斷message的類型(因為不止一種message type)
				switch(m.getMesType()){
					case MessageType.messageNormalMsg://如果是普通訊息類型
						System.out.println("sender: " + m.getSender());
						System.out.println("recipient: " + m.getRecipient());
						System.out.println("content: " + m.getContent());
						System.out.println("sendTime: " + m.getSendTime());
						//把從server取得的消息顯示到該顯示的聊天界面
						LineChat lc = ManageLineChat.getLineChat(m.getRecipient() +"_" + m.getSender());
						//顯示
						lc.showMessage(m);
						break;
					case MessageType.messageReturnOnlineFriend://如果是sever返回在線好友
						
						
						String [] friendList = m.getContent().split(" ");//使用空白拆分
						String recipient = m.getRecipient();
						
						System.out.println("client " +recipient+"的 收收收:" + m.getContent());
						
						//從manager處 取得該用戶好友列表
						LineFriendList lfl = ManageLineFriendList.getFriendList(recipient);
						
						//更新在線好友
						lfl.updateFriendList(friendList);
						
						break;
					default:
						break;
				}
				
				
				
			}catch(Exception e) {
				
			}
		
		}
	}
}
