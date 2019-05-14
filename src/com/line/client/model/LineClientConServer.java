package com.line.client.model;
import com.line.client.tools.*;
import com.line.common.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class LineClientConServer {
	public  Socket s = null;

	public void sendInfoToServer(Object o) {
		
	}
	
	//發送login請求
	public boolean sendLoginInfoToServer(Object o) {
		boolean b =false;
		try {
			s = new Socket("127.0.0.1", 9999);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			
			Message ms = (Message)ois.readObject();
			//這裡就是驗證用戶登入的地方
			if(ms.getMesType().equals("1")) {//如果為1 代表成功
				//創建一個該用戶與server保持連接的thread
				//成功時 創建一個thread 並放入hashmaps
				
				
				ClientConServerThread ccst = new ClientConServerThread(s);
				//啟動該通訊thread
				ccst.start();
				ManageClientConServerThread.addClientConServerThread(((User)o).getUserId(), ccst);//把userId 與socket 加入hashmap中
				b= true;
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	/*public static void main(String args[]) {
		LineClientConServer lcc = new LineClientConServer();
	}*/
}
