/* 
好友列表 陌生人 黑名單 
 */ 
package com.line.client.view; 
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import com.line.client.tools.*;
import com.line.common.*;
 
public class LineFriendList  extends JFrame implements ActionListener, MouseListener{ 
    //處理第一張卡片 
    JPanel  jpFriend1, jpFriend2, jpFriend3; 
    JButton jpFriendBtn1, jpFriendBtn2, jpFriendBtn3; 
    JScrollPane jsp1; 
     
    //處理第二張卡片 
    JPanel  jpStranger1, jpStranger2, jpStranger3; 
    JButton jpStrangerBtn1, jpStrangerBtn2, jpStrangerBtn3; 
    JScrollPane jsp2; 
     
    //處理第三張卡片 
    JPanel  jpBlock1, jpBlock2, jpBlock3; 
    JButton jpBlockBtn1, jpBlockBtn2, jpBlockBtn3; 
    JScrollPane jsp3; 
     
    //卡片布局樣式 
    CardLayout c1 ; 
    
    //自己的編號 
    String ownerId;
    
    //各種列表: 好友, 陌生人, 黑名單等
    JLabel [] jlbFriends, jlbStrangers, jlbBlocks;
    
    public LineFriendList() {
    	this.ownerId = "初始id";
    	initLayout();
    } 
    
    
    public LineFriendList(String ownerId){ 
        this.ownerId = ownerId;
    	initLayout(); 
    } 
     
    private void initLayout(){//初始化布局 
         
        initCardOne();//處理第一張卡片 
        initCardTwo();//處裡第二張卡片         
        initCardThree();//處裡第三張卡片 
        
        c1 = new CardLayout(); 
        this.setLayout(c1); 
         
        //設置卡片並編號 
         
        this.add(jpFriend1, "1"); 
        this.add(jpStranger1, "2"); 
        this.add(jpBlock1, "3"); 
         
        this.setTitle(ownerId+"的好友列表");//初始title為好友名單
		this.setSize(300,400); 
        this.setVisible(true); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setIconImage((new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/ninja_icon_30x30.png")))).getImage());
        //this.setLocationRelativeTo(null);
    } 
    
    //更新在線好友列表
    public void updateFriendList(String[] onlineFriendList) {
    	
    	for(int i=0; i<onlineFriendList.length; i++) {
    		jlbFriends[Integer.parseInt(onlineFriendList[i])-1].setEnabled(true);
    		System.out.println("更新:" + onlineFriendList[i] + " 上線");
    	}
    	
    }
    
    
    private void initCardOne(){//好友名單 
        jpFriendBtn1 = new JButton("好友列表"); 
         
        jpFriendBtn2 = new JButton("陌生人"); 
        jpFriendBtn2.addActionListener(this); 
         
        jpFriendBtn3 = new JButton("黑名單"); 
        jpFriendBtn3.addActionListener(this); 
         
        jpFriend1 = new JPanel(new BorderLayout());//處理第一張卡片 
        jpFriend2 = new JPanel(new GridLayout(50,1,4,4));//好友列表 假定有50個 4 4 為間隔 
        jpFriend2.setLayout(new GridLayout(50, 1)); 
         
        //給jpFriend2初始化50 個好友 
        //JLabel [] jlbs = new JLabel[50]; 
        jlbFriends = new JLabel[50];
         
        for(int i=0; i<jlbFriends.length; i++){ 
        	/*jlbFriends[i] = new JLabel( "好友"+(i+1) + "號",  
                                        new ImageIcon(getClass().getResource("/images/user_icon30x30.png")), 
                                        JLabel.LEFT);//圖在左邊 字在右邊*/
        	jlbFriends[i] = new JLabel( (i+1) +"",  
                    new ImageIcon(getClass().getResource("/images/user_icon30x30.png")), 
                    JLabel.LEFT);
        	
        	jlbFriends[i].setEnabled(false);
            if(jlbFriends[i].getText().equals(ownerId)) {
            	jlbFriends[i].setEnabled(true);
            }
            jlbFriends[i].addMouseListener(this); 
            jpFriend2.add(jlbFriends[i]); 
        } 
         
        jpFriend3 = new JPanel(new GridLayout(2,1));//存放下方按鈕 
         
        //把2個按鈕加入jpFriend3中 
        jpFriend3.add(jpFriendBtn2); 
        jpFriend3.add(jpFriendBtn3); 
        
        jsp1 = new JScrollPane(jpFriend2); 
         
        jpFriend1.add(jpFriendBtn1, "North"); 
        jpFriend1.add(jsp1, "Center"); 
        jpFriend1.add(jpFriend3, "South"); 
    } 
     
    private void initCardTwo(){//陌生人列表 
         jpStrangerBtn1 = new JButton("好友列表"); 
         jpStrangerBtn1.addActionListener(this); 
                 jpStrangerBtn2 = new JButton("陌生人"); 
         
        jpStrangerBtn3 = new JButton("黑名單"); 
        jpStrangerBtn3.addActionListener(this); 
         
        jpStranger1 = new JPanel(new BorderLayout());//處理第2張卡片 
        jpStranger2 = new JPanel(new GridLayout(2,1));//存放上方按鈕 
         
        //把2個按鈕加入jpStranger2中 
        jpStranger2.add(jpStrangerBtn1); 
        jpStranger2.add(jpStrangerBtn2); 
         
        jpStranger3 = new JPanel(new GridLayout(20,1,4,4));//好友列表 假定有20個 4 4 為間隔 
         
        //給jpStranger3初始化20 個陌生人 
        //JLabel [] jlbs = new JLabel[20];
        jlbStrangers = new JLabel[20];
         
        for(int i=0; i<jlbStrangers.length; i++){ 
        	jlbStrangers[i] = new JLabel( "陌生人"+(i+1) + "號",  
                                        new ImageIcon(getClass().getResource("/images/user_icon30x30.png")), 
                                        JLabel.LEFT);//圖在左邊 字在右邊 
        	jlbStrangers[i].setEnabled(false);
        	jlbStrangers[i].addMouseListener(this); 
            jpStranger3.add(jlbStrangers[i]); 
        } 
         
         
        jsp2 = new JScrollPane(jpStranger3); 
         
        jpStranger1.add(jpStranger2, "North"); 
        jpStranger1.add(jsp2, "Center"); 
        jpStranger1.add(jpStrangerBtn3, "South"); 
    } 
     
    private void initCardThree(){//黑名單列表 
        jpBlockBtn1 = new JButton("好友列表"); 
        jpBlockBtn1.addActionListener(this); 
         
        jpBlockBtn2 = new JButton("陌生人"); 
        jpBlockBtn2.addActionListener(this); 
         
        jpBlockBtn3 = new JButton("黑名單"); 
         
        jpBlock1 = new JPanel(new BorderLayout());//處理第3張卡片 
        jpBlock2 = new JPanel(new GridLayout(3,1));//存放上方按鈕
		//把3個按鈕加入jpBlock2中 
        jpBlock2.add(jpBlockBtn1); 
        jpBlock2.add(jpBlockBtn2); 
        jpBlock2.add(jpBlockBtn3); 
         
        jpBlock3 = new JPanel(new GridLayout(10,1,4,4));//好友列表 假定有20個 4 4 為間隔 
         
        //給jpBlock3初始化10 個黑名單 
        //JLabel [] jlbs = new JLabel[10]; 
        jlbBlocks = new JLabel[10];
         
        for(int i=0; i<jlbBlocks.length; i++){ 
        	jlbBlocks[i] = new JLabel( "黑名單"+(i+1) + "號",  
                                        new ImageIcon(getClass().getResource("/images/user_icon30x30.png")), 
                                        JLabel.LEFT);//圖在左邊 字在右邊 
        	jlbBlocks[i].setEnabled(false);
        	jlbBlocks[i].addMouseListener(this); 
            jpBlock3.add(jlbBlocks[i]); 
        } 
         
         
        jsp3 = new JScrollPane(jpBlock3); 
         
        jpBlock1.add(jpBlock2, "North"); 
        jpBlock1.add(jsp3, "Center"); 
    } 
     
     
    public static void main(String args[]){ 
    	LineFriendList cfl = new LineFriendList(); 
    } 
 
    @Override 
    public void actionPerformed(ActionEvent e) { 
        //如果在好友列表或黑名單列表點了陌生人按鈕 就顯示第二張卡片 
        if(e.getSource() == jpFriendBtn2 || e.getSource()==jpBlockBtn2){ 
            c1.show(this.getContentPane(), "2"); 
            this.setTitle(ownerId+"的陌生人列表"); 
         
        //如果在好友列表或陌生人列表點了黑名單按鈕 就顯示第三張卡片 
        }else if(e.getSource() == jpFriendBtn3 || e.getSource()==jpStrangerBtn3){ 
            c1.show(this.getContentPane(), "3");
            this.setTitle(ownerId+"的黑名單列表"); 
             
        //如果在陌生人列表或黑名單列表點了好友清單按鈕 就顯示第一張卡片     
        }else if(e.getSource() == jpStrangerBtn1 || e.getSource()==jpBlockBtn1){ 
            c1.show(this.getContentPane(), "1"); 
            this.setTitle(ownerId+"的好友列表"); 
        } 
    } 
 
    @Override 
    public void mouseClicked(MouseEvent e) { 
        //響應雙擊事件 並得到好友編號 
        if(e.getClickCount() ==2){//捕獲雙擊 
            //取得好友編號 
            String friendNo = ((JLabel) e.getSource()).getText(); 
            System.out.println("你希望和" + friendNo+"聊天");
            LineChat lc = new LineChat(ownerId, friendNo);
            
            //把聊天界面加入到管理類中
            ManageLineChat.addLineChat(ownerId+"_"+friendNo, lc);
            
        } 
    } 
 
    @Override 
    public void mousePressed(MouseEvent e) { 
         
    } 
 
    @Override 
    public void mouseReleased(MouseEvent e) { 
         
    } 
 
    @Override 
    public void mouseEntered(MouseEvent e) { 
        JLabel jl = (JLabel) e.getSource(); 
        jl.setForeground(Color.red); 
    } 
 
    @Override 
    public void mouseExited(MouseEvent e) { 
        JLabel jl = (JLabel) e.getSource(); 
        jl.setForeground(Color.black); 
    } 
} 
 