package com.KoreaIT.JAM.session;

import com.KoreaIT.JAM.Member;

public class Session {
	public static int loginedMemberId;
	public static Member loginedMember;
	
	static {
		loginedMemberId = -1;
		loginedMember = null;
	}
}