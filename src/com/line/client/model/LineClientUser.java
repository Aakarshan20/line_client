package com.line.client.model;
import com.line.common.*;

public class LineClientUser {
	public LineClientUser() {
		
	}
	public boolean checkUser(User u) {
		return  new LineClientConServer().sendLoginInfoToServer(u);
	}
}
