/*
 * 管理用戶聊天界面的類
 * */
package com.line.client.tools;
import java.util.*;
import com.line.client.view.*;

public class ManageLineChat {
	
	//key為代表發信人與收信人的唯一字串 例: 用戶A_用戶B
	private static HashMap hm = new HashMap<String, LineChat>();
	
	//加入
	public static void addLineChat(String senderToRecipient, LineChat lc) {
		hm.put(senderToRecipient, lc);
	}
	
	//取出
	public static LineChat getLineChat(String senderToRecipient) {
		return (LineChat)hm.get(senderToRecipient);
	}
}
