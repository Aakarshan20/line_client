/*
 * 管理 好友,黑名單, 陌生人等界面的類
 * */

package com.line.client.tools;
import java.util.*;
import java.io.*;
import com.line.client.view.*;


public class ManageLineFriendList {
	private static HashMap hm = new HashMap<String, LineFriendList>();
	
	public static void addFriendList(String uid, LineFriendList lfl) {
		hm.put(uid, lfl);
	}
	public static LineFriendList getFriendList(String uid) {
		return (LineFriendList)hm.get(uid);
	}
}
