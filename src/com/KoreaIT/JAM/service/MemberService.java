package com.KoreaIT.JAM.service;

import java.sql.Connection;

import com.KoreaIT.JAM.dao.MemberDao;

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
}