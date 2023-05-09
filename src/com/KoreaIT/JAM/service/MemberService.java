package com.KoreaIT.JAM.service;

import java.sql.Connection;
import java.util.Map;

import com.KoreaIT.JAM.dao.MemberDao;
import com.KoreaIT.JAM.dto.Member;

public class MemberService {
	private MemberDao memberDao;
	public MemberService(Connection conn) {
		memberDao = new MemberDao(conn);
	}

	public int selectRowIntValue(String loginId) {
		return memberDao.selectRowIntValue(loginId);
	}

	public void dbInsert(String loginId, String loginPw, String name) {
		memberDao.dbInsert(loginId, loginPw, name);
	}

	public Member getMember(String loginId) {
		
		Map<String,Object> memberMap = memberDao.getMember(loginId);
		
		if (memberMap.isEmpty()) {
			return null;
		}
		
		return new Member(memberMap);
	}
}