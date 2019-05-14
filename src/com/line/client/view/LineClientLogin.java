/*2018-12-02 line 登入界面*/

package com.line.client.view;

import javax.swing.*;
import javax.swing.border.*;//邊框物件

import com.line.client.model.*;
import com.line.client.tools.*;
import com.line.common.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class LineClientLogin extends JFrame implements ActionListener, MouseListener, KeyListener{
	draw d;
	JButton jClose, jSetting, jMinilize, jLogin, jRegi;//關閉, 設定, 最小化, 登入, 註冊新帳號
	JTextField jName;//username
	JPasswordField jPasswd;//password
	JCheckBox jRemember;//記住我
	ImageIcon settingIcon, closeIcon, loginIcon, regiIcon;
	Border border ;//邊框物件
	String userId, passwd;//待驗字串
	
	//JTabbedPane jtp;//選項卡窗口 可切換jpanel //失敗 勿用
	//JPanel emailLogin, qrcodeLogin;//兩個panel 電子郵件登入與行動條碼登入// 失敗 勿用
	
	
	public LineClientLogin() {
		Container ct = this.getContentPane();
		this.setLayout(null);
		
		//左上setting的按鈕
		jSetting = new JButton();
		settingIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/setting_btn.png")));//關閉用button
		jSetting.setBounds(0,0,29,32);//指定位置
		jSetting.setIcon(settingIcon);
		jSetting.addActionListener(this);//註冊監聽
		jSetting.setActionCommand("setting");//發送指令
		//消除按鈕邊框
		jSetting.setBorderPainted(false);
		jSetting.setFocusPainted(false);
		jSetting.setContentAreaFilled(false);
		jSetting.addMouseListener(this);//滑鼠監聽
		
		
		//關閉的按鈕
		jClose = new JButton();
		closeIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/close_btn.png")));//關閉用button
		jClose.setBounds(272,0,29,32);//指定位置
		jClose.setIcon(closeIcon);
		jClose.addActionListener(this);//註冊監聽
		jClose.setActionCommand("exit");//發送指令
		//消除按鈕邊框
		jClose.setBorderPainted(false);
		jClose.setFocusPainted(false);
		jClose.setContentAreaFilled(false);
		jClose.addMouseListener(this);//滑鼠監聽
		
		
		//中間登入的選項卡 //失敗勿用
		//emailLogin = new JPanel();
		
		//邊框樣式
		border = BorderFactory.createLineBorder(Color.gray);
		
		//帳號的textfield
		jName = new JTextField("電子郵件帳號");//指定位置
		jName.setBounds(28, 152, 239, 32);//信箱框
		jName.setBorder(border);
		jName.addKeyListener(this);
		
		
		//密碼jPasswd
		jPasswd = new JPasswordField();
		jPasswd.setBounds(28, 183, 239, 32);
		jPasswd.setBorder(border);
		jPasswd.addKeyListener(this);
		
		//登入
		jLogin = new JButton();
		loginIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/login_btn.png")));//關閉用button
		jLogin.setBounds(28,220,240,38);//指定位置
		jLogin.setIcon(loginIcon);
		jLogin.addActionListener(this);//註冊監聽
		jLogin.setActionCommand("login");//發送指令
		//消除按鈕邊框
		jLogin.setBorderPainted(false);
		jLogin.setFocusPainted(false);
		jLogin.setContentAreaFilled(false);
		jLogin.addMouseListener(this);//滑鼠監聽
		
		//註冊
		jRegi = new JButton();
		regiIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/regi_btn.png")));//關閉用button
		jRegi.setBounds(28,380,240,34);//指定位置
		jRegi.setIcon(regiIcon);
		jRegi.addActionListener(this);//註冊監聽
		jRegi.setActionCommand("regi");//發送指令
		//消除按鈕邊框
		jRegi.setBorderPainted(false);
		jRegi.setFocusPainted(false);
		jRegi.setContentAreaFilled(false);
		jRegi.addMouseListener(this);//滑鼠監聽
		
		
	
		d = new draw();//背景
		d.setBounds(0, 0, 295, 463);
		d.setOpaque(false);// 設置透明度
		
		
		ct.add(jSetting);
		ct.add(jClose);
		ct.add(jName);
		ct.add(jPasswd);
		ct.add(jLogin);
		ct.add(jRegi);
		
		ct.add(d);//加入背景圖
		this.setUndecorated(true);//不使用上下框
		this.setSize(300,463);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	
	public static void main(String[] args) {
		LineClientLogin lcl = new LineClientLogin();
    }

    
	
	class draw extends JPanel {//JPanel作用: 畫背景
		
		public void paintComponent(Graphics g) {
			Image image;
			image=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/login.png"));
			g.drawImage(image, 0, 0,295,463, this);
		}

	}



	@Override
	//響應各種事件
	public void actionPerformed(ActionEvent arg0) {
		String actionEventCommand = arg0.getActionCommand();
		
		if(actionEventCommand != null) {
			switch(actionEventCommand) {
				case "exit":
					this.dispose();
					break;
				case "login":
					System.out.println("登入請求中...");
					
					LineClientUser lculc = new LineClientUser();
					User u = new User();
					u.setUserId(jName.getText().trim());
					u.setUserPasswd(new String(jPasswd.getPassword()).trim());

					if(lculc.checkUser(u)) {//如果登入成功
						//發送一個要求返回在線好友的包
						try {
							//打開好友列表
							LineFriendList lfl = new LineFriendList(u.getUserId());
							ManageLineFriendList.addFriendList(u.getUserId(), lfl);
							
							//創建物件(為了取得好友列表)輸出流
							ObjectOutputStream oos = new ObjectOutputStream(
									ManageClientConServerThread.getClientConServerThread(
											u.getUserId()).getS().getOutputStream());
							
							//封裝一個Message class 並向Server發送請求
							Message msg = new Message();
							msg.setMesType(MessageType.messageGetOnlineFriend);//message類型: 請求上線中的好友列表
							msg.setSender(u.getUserId());//索取人的id
							
							oos.writeObject(msg);//往流裡面寫
							
							
						}catch(Exception e) {
							e.printStackTrace();
						}
						
						
						//同時關閉登入界面
						this.dispose();
					}else{
						JOptionPane.showMessageDialog(this,"帳號密碼錯誤", "錯誤", JOptionPane.WARNING_MESSAGE);
					}
					break;
				default:
					JOptionPane.showMessageDialog(this,"假的");
					break;
			}
		}
		
	}

	//強調邊框
	private void showBorder(JButton jb) {
		jb.setBorderPainted(true);
		jb.setFocusPainted(true);
		jb.setContentAreaFilled(true);
	}
	
	//隱藏邊框
	private void hideBorder(JButton jb) {
		jb.setBorderPainted(false);
		jb.setFocusPainted(false);
		jb.setContentAreaFilled(false);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	//滑鼠進入 更改樣式
	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getSource() == jClose || arg0.getSource() == jSetting || arg0.getSource() == jLogin ||arg0.getSource()== jRegi) {
			this.showBorder((JButton)arg0.getSource());
		}
	}
	


	@Override
	//滑鼠離開 更改樣式
	public void mouseExited(MouseEvent arg0) {
		if(arg0.getSource() == jClose || arg0.getSource() == jSetting || arg0.getSource() == jLogin ||arg0.getSource()== jRegi) {
			this.hideBorder((JButton)arg0.getSource());
		}
		
	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		
	}



	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
		      System.out.println("Check for key codes: " + arg0.getKeyCode());
		      System.out.println("登入請求中...");
				
				LineClientUser lculc = new LineClientUser();
				User u = new User();
				u.setUserId(jName.getText().trim());
				u.setUserPasswd(new String(jPasswd.getPassword()).trim());

				if(lculc.checkUser(u)) {//如果登入成功
					//打開好友列表
					 new LineFriendList(u.getUserId());
					 //同時關閉登入界面
					 this.dispose();
				}else {
					JOptionPane.showMessageDialog(this,"帳號密碼錯誤", "錯誤", JOptionPane.WARNING_MESSAGE);
				}
		}
		
		
	}



	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}
}
