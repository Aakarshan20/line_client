package com.line.client.view;
/*
 * 與好友聊天的借面
* client 要處於讀取狀態 所以要實作runnable界面
*/
import javax.swing.*;

import com.line.client.model.*;
import com.line.client.tools.*;
import com.line.common.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;

public class LineChat extends JFrame implements ActionListener ,KeyListener{
	
	JPanel jp1;
	JScrollPane jsp;
	JTextArea jta;
	JTextField jtf;
	JButton jb;
	
	//寄件人 , 收件人
	String sender, recipient;
	
	private void initLayout(String ownerId, String friendName) {
		
		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		
		jtf = new JTextField(15);
		
		jb = new JButton("送出");
		jb.addActionListener(this);
		jb.setActionCommand("send");
		
		jp1 = new JPanel();
		jp1.add(jtf);
		jp1.add(jb);
		
		
		this.add(jsp, "Center");
		this.add(jp1, "South");
		this.setSize(400,400);
		this.setVisible(true);
		
		jtf.addKeyListener(this);
		this.setIconImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/ninja_icon_30x30.png")))).getImage());
		this.setTitle(ownerId + "正與" + friendName + "對話中...");
		this.setLocationRelativeTo(null);
		
	}
	
	public LineChat(String ownerId, String friendName) {
		
		this.sender = ownerId;
		this.recipient = friendName;
		
		initLayout(ownerId, friendName);
	}
	
	//public static void main(String[] args) {
		//LineChat lc = new LineChat("", "");

	//}
	
	//寫一個方法讓他顯示對話
	public void showMessage(Message m) {
		
		String msgStr = m.getSender() + " 在"+m.getSendTime()+" 對您 說: "+m.getContent();
		this.jta.append(msgStr+"\r\n");
	}
		
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(!arg0.getActionCommand().equals("")) {
			//判斷command
			switch(arg0.getActionCommand()) {
				case "send"://如果用戶點擊了發送按鈕
					sendMessageToServer();
					break;
				default:
					break;
			}
		}
	}
	
	//訊息發給server
	private void sendMessageToServer() {
		//組裝message包
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = formatter.format(new java.util.Date());
		
		System.out.println("send message: " + nowTime);
		Message m = new Message();
		//初始化以ㄒ內容
		
		m.setSender(this.sender);
		m.setRecipient(this.recipient);
		m.setContent(this.jtf.getText());
		m.setSendTime(nowTime);
		m.setMesType(MessageType.messageNormalMsg);
		//發送給server
		try {
			
			//從客戶的線程管理類中 取thread, 取socket 最後再取出輸出流 
			ObjectOutputStream oos = new ObjectOutputStream(
					ManageClientConServerThread.getClientConServerThread(this.sender).getS().getOutputStream());
			oos.writeObject(m);//把文件送出
			
			String content = "您在 " + nowTime + " 對 " + m.getRecipient() + " 說: " + m.getContent();
			
			//訊息加到對話框內
			jta.append(content);
			jta.append("\n");
			//清空輸入框
			jtf.setText("");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			sendMessageToServer();
		}
			//tf.setText("");
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
