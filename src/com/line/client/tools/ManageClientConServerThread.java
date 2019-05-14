/*
 * 管理client與server通訊線程的類
 * */

package com.line.client.tools;
import java.util.*;
import java.net.*;
public class ManageClientConServerThread {
	private static HashMap hm = new HashMap<String, ClientConServerThread>();
	
	//加線程
	public static void addClientConServerThread(String uid, ClientConServerThread ccst) {
		hm.put(uid, ccst);
	}
	
	//取線程
	public static ClientConServerThread getClientConServerThread(String uid) {
		return (ClientConServerThread)hm.get(uid);
	}
}
