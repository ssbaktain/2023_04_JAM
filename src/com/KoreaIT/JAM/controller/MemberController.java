package com.KoreaIT.JAM.controller;

import java.sql.Connection;
import java.util.Scanner;

import com.KoreaIT.JAM.Member;
import com.KoreaIT.JAM.container.Container;
import com.KoreaIT.JAM.service.MemberService;
import com.KoreaIT.JAM.session.Session;
import com.KoreaIT.JAM.util.SecSql;

public class MemberController extends Container {
	private Scanner sc;
	private MemberService memberService;
	
	public MemberController(Scanner sc, Connection conn) {
		this.sc = sc;
		this.memberService = new MemberService(conn);
	}

	public void doAction(String cmd) {
		String cmdBit[] = cmd.split(" ");
		
		switch (cmdBit[1]) {
		case "join" :
			doJoin(cmd);
			break;
		case "login":
			doLogin(cmd);
			break;
		}
	}

	private void doJoin(String cmd) {
		
		if (Session.loginedMemberId == -1) {
			System.out.println("로그아웃 후 이용해주세요.");
			return;
		}
		
		String loginId;
		String loginPw;
		String name;
		
		System.out.println("== 회원 가입 ==");
		while (true) {
			System.out.print("로그인 아이디 : ");
			loginId = sc.nextLine().trim();
			
			if (loginId.length() == 0) {
				System.out.println("아이디는 필수입력정보입니다.");
				continue;
			}
			
			int memberCount = memberService.selectRowIntValue(loginId);
			
			if (memberCount == 1) {
				System.out.println(loginId + "(은)는 이미 존재하는 아이디입니다.");
				continue;
			}
			
			System.out.println(loginId + "(은)는 사용 가능한 아이디입니다.");
			
			while (true) {
				System.out.print("로그인 비밀번호 : ");
				loginPw = sc.nextLine().trim();
				
				if (loginPw.length() == 0) {
					System.out.println("비밀번호는 필수입력정보입니다.");
					continue;
				}
				
				System.out.print("로그인 비밀번호 확인 : ");
				String checkLoginPw = sc.nextLine().trim();
				
				if (!checkLoginPw.equals(loginPw)) {
					System.out.println("비밀번호가 일치하지 않습니다.");
					continue;
				}
				break;
			}
			break;
		}
		while (true) {
			System.out.print("이름 : ");
			name = sc.nextLine().trim();
			
			if (name.length() == 0) {
				System.out.println("이름은 필수입력정보입니다.");
				continue;
			}
			break;
		}
		
		memberService.dbInsert(loginId, loginPw, name);
		
		System.out.println(name + "님, 환영합니다.");
	}
	
	private void doLogin(String cmd) {
		
		if (Session.loginedMemberId == -1) {
			System.out.println("로그아웃 후 이용해주세요.");
			return;
		}
		
		String loginId;
		String loginPw;
		
		while (true) {
			System.out.print("로그인 아이디 : ");
			loginId = sc.nextLine().trim();
			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요.");
				continue;
			}
			break;
		}
		
		while (true) {
			System.out.print("로그인 비밀번호 : ");
			loginPw = sc.nextLine().trim();
			if (loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해주세요.");
				continue;
			}
			break;
		}
		
		Member member = memberService.getMember(loginId);
		
		if (member == null) {
			System.out.printf("%s은(는) 존재하지 않는 아이디입니다.\n", loginId);
			return;
		}
		
		if (!member.loginPw.equals(loginPw)) {
			System.out.println("비밀번호가 올바르지 않습니다.");
			return;
		}
		
		System.out.printf("%s님 환영합니다.\n", member.name);
		
		Session.loginedMemberId = member.id;
		Session.loginedMember = member;
	}
}