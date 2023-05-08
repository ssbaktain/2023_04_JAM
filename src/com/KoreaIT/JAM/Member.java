package com.KoreaIT.JAM;

import java.time.LocalDateTime;
import java.util.Map;

public class Member {
	public static int nowLoginedUser = 0;
	int id;
	LocalDateTime regDate;
	LocalDateTime updateDate;
	String loginId;
	String loginPw;
	String name;
	
	public Member(Map<String, Object> memberMap) {
		this.id = (int) memberMap.get("id");
		this.regDate = (LocalDateTime) memberMap.get("regDate");
		this.updateDate = (LocalDateTime) memberMap.get("updateDate");
		this.loginId = (String) memberMap.get("loginId");
		this.loginPw = (String) memberMap.get("loginPw");
		this.name = (String) memberMap.get("name");
	}
}